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
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;

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
    private BillingListener listener;

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
                .enablePendingPurchases()
                .setListener(this)
                .build();

        startConnection();
    }

    private void startConnection() {
        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(@NonNull BillingResult billingResult) {
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
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

    public void queryPurchases() {
        if (billingClient.isReady()) {
            billingClient.queryPurchasesAsync(
                    BillingClient.SkuType.INAPP,
                    (billingResult, purchases) -> {
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
            if (purchase.getSkus().contains(SKU_REMOVE_ADS)) {
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
        if (billingClient.isReady()) {
            List<String> skuList = new ArrayList<>();
            skuList.add(SKU_REMOVE_ADS);

            SkuDetailsParams params = SkuDetailsParams.newBuilder()
                    .setSkusList(skuList)
                    .setType(BillingClient.SkuType.INAPP)
                    .build();

            billingClient.querySkuDetailsAsync(params, (billingResult, skuDetailsList) -> {
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK && skuDetailsList != null) {
                    for (SkuDetails skuDetails : skuDetailsList) {
                        if (skuDetails.getSku().equals(SKU_REMOVE_ADS)) {
                            BillingFlowParams flowParams = BillingFlowParams.newBuilder()
                                    .setSkuDetails(skuDetails)
                                    .build();
                            billingClient.launchBillingFlow(activity, flowParams);
                            break;
                        }
                    }
                }
            });
        }
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
