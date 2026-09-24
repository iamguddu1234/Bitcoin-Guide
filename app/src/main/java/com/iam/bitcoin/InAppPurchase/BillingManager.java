package com.iam.bitcoin.InAppPurchase;

// BillingManager.java

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import com.android.billingclient.api.AcknowledgePurchaseParams;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.PendingPurchasesParams;
import com.android.billingclient.api.ProductDetails;
import com.android.billingclient.api.QueryProductDetailsParams;
import com.android.billingclient.api.QueryPurchasesParams;

import java.util.ArrayList;
import java.util.List;

public class BillingManager implements PurchasesUpdatedListener {
    private static final String TAG = "BillingManager";
    private static final String SKU_REMOVE_ADS = "remove_adss";
    private static final String PREFS_NAME = "BillingPrefs";
    private static final String PREMIUM_PURCHASED_KEY = "premium_purchased";

    private final Activity activity;
    private BillingClient billingClient;
    private boolean isPremium = false;
    private final BillingListener listener;

    // Cache the ProductDetails we fetch, so launchPurchaseFlow() doesn't
    // need to re-query every time.
    private ProductDetails cachedProductDetails;

    public interface BillingListener {
        void onPremiumStatusChanged(boolean isPremium);
        void onBillingSetupFinished();
    }

    public BillingManager(Activity activity, BillingListener listener) {
        this.activity = activity;
        this.listener = listener;
        this.isPremium = getPremiumStatusFromPrefs();
        setupBillingClient();
    }

    private void setupBillingClient() {
        billingClient = BillingClient.newBuilder(activity)
                .enablePendingPurchases(
                        PendingPurchasesParams.newBuilder()
                                .enableOneTimeProducts()
                                .build()
                )
                .setListener(this)
                .build();

        startConnection();
    }

    private void startConnection() {
        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(@NonNull BillingResult billingResult) {
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    queryProductDetails();
                    queryPurchases();
                    listener.onBillingSetupFinished();
                }
            }

            @Override
            public void onBillingServiceDisconnected() {
                // Try to restart the connection
                startConnection();
            }
        });
    }

    /** Pre-fetch ProductDetails so launchPurchaseFlow() can fire immediately. */
    private void queryProductDetails() {
        List<QueryProductDetailsParams.Product> productList = new ArrayList<>();
        productList.add(
                QueryProductDetailsParams.Product.newBuilder()
                        .setProductId(SKU_REMOVE_ADS)
                        .setProductType(BillingClient.ProductType.INAPP)
                        .build()
        );

        QueryProductDetailsParams params = QueryProductDetailsParams.newBuilder()
                .setProductList(productList)
                .build();

        billingClient.queryProductDetailsAsync(params, (billingResult, productDetailsResult) -> {
            if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                List<ProductDetails> productDetailsList = productDetailsResult.getProductDetailsList();
                if (productDetailsList != null && !productDetailsList.isEmpty()) {
                    cachedProductDetails = productDetailsList.get(0);
                } else {
                    Log.w(TAG, "No ProductDetails returned for " + SKU_REMOVE_ADS);
                }
            } else {
                Log.w(TAG, "queryProductDetailsAsync failed: " + billingResult.getDebugMessage());
            }
        });
    }

    public void queryPurchases() {
        if (billingClient.isReady()) {
            QueryPurchasesParams params = QueryPurchasesParams.newBuilder()
                    .setProductType(BillingClient.ProductType.INAPP)
                    .build();

            billingClient.queryPurchasesAsync(params, (billingResult, purchases) -> {
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    handlePurchases(purchases);
                }
            });
        }
    }

    @Override
    public void onPurchasesUpdated(@NonNull BillingResult billingResult, List<Purchase> purchases) {
        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK && purchases != null) {
            handlePurchases(purchases);
        }
    }

    private void handlePurchases(List<Purchase> purchases) {
        boolean wasPremium = isPremium;

        for (Purchase purchase : purchases) {
            if (purchase.getProducts().contains(SKU_REMOVE_ADS)) {
                if (purchase.getPurchaseState() == Purchase.PurchaseState.PURCHASED) {
                    if (!purchase.isAcknowledged()) {
                        acknowledgePurchase(purchase);
                    }
                    isPremium = true;
                    savePremiumStatusToPrefs(true);
                }
            }
        }

        if (isPremium != wasPremium) {
            listener.onPremiumStatusChanged(isPremium);
        }
    }

    private void acknowledgePurchase(Purchase purchase) {
        AcknowledgePurchaseParams acknowledgePurchaseParams = AcknowledgePurchaseParams.newBuilder()
                .setPurchaseToken(purchase.getPurchaseToken())
                .build();

        billingClient.acknowledgePurchase(acknowledgePurchaseParams, billingResult -> {
            if (billingResult.getResponseCode() != BillingClient.BillingResponseCode.OK) {
                Log.w(TAG, "Failed to acknowledge purchase");
            }
        });
    }

    public void launchPurchaseFlow() {
        if (!billingClient.isReady()) {
            Log.w(TAG, "launchPurchaseFlow: billing client not ready");
            return;
        }

        if (cachedProductDetails != null) {
            launchFlowWithDetails(cachedProductDetails);
            return;
        }

        // Not cached yet (e.g. called too soon after setup) — query then launch.
        List<QueryProductDetailsParams.Product> productList = new ArrayList<>();
        productList.add(
                QueryProductDetailsParams.Product.newBuilder()
                        .setProductId(SKU_REMOVE_ADS)
                        .setProductType(BillingClient.ProductType.INAPP)
                        .build()
        );

        QueryProductDetailsParams params = QueryProductDetailsParams.newBuilder()
                .setProductList(productList)
                .build();

        billingClient.queryProductDetailsAsync(params, (billingResult, productDetailsResult) -> {
            if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                List<ProductDetails> productDetailsList = productDetailsResult.getProductDetailsList();
                if (productDetailsList != null && !productDetailsList.isEmpty()) {
                    cachedProductDetails = productDetailsList.get(0);
                    launchFlowWithDetails(cachedProductDetails);
                } else {
                    Log.w(TAG, "launchPurchaseFlow: no ProductDetails for " + SKU_REMOVE_ADS);
                }
            } else {
                Log.w(TAG, "launchPurchaseFlow: queryProductDetailsAsync failed: " + billingResult.getDebugMessage());
            }
        });
    }

    private void launchFlowWithDetails(ProductDetails productDetails) {
        List<BillingFlowParams.ProductDetailsParams> productDetailsParamsList = new ArrayList<>();
        productDetailsParamsList.add(
                BillingFlowParams.ProductDetailsParams.newBuilder()
                        .setProductDetails(productDetails)
                        // For INAPP one-time products you do NOT call setOfferToken().
                        // Only subscriptions need an offer token from productDetails.getSubscriptionOfferDetails().
                        .build()
        );

        BillingFlowParams flowParams = BillingFlowParams.newBuilder()
                .setProductDetailsParamsList(productDetailsParamsList)
                .build();

        billingClient.launchBillingFlow(activity, flowParams);
    }

    public boolean isPremium() {
        return isPremium;
    }

    private void savePremiumStatusToPrefs(boolean isPremium) {
        SharedPreferences prefs = activity.getSharedPreferences(PREFS_NAME, Activity.MODE_PRIVATE);
        prefs.edit().putBoolean(PREMIUM_PURCHASED_KEY, isPremium).apply();
    }

    private boolean getPremiumStatusFromPrefs() {
        SharedPreferences prefs = activity.getSharedPreferences(PREFS_NAME, Activity.MODE_PRIVATE);
        return prefs.getBoolean(PREMIUM_PURCHASED_KEY, false);
    }

    public void destroy() {
        if (billingClient != null && billingClient.isReady()) {
            billingClient.endConnection();
        }
    }
}

