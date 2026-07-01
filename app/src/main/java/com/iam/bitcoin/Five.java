package com.iam.bitcoin;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.iam.bitcoin.BaseActivity.BaseActivity;
import com.iam.bitcoin.InAppPurchase.BillingManager;
import com.iam.bitcoin.InAppPurchase.PremiumStatusListener;
import com.iam.bitcoin.Intermediate.I12;
import com.iam.bitcoin.Multilanguage.LocaleHelper;

public class Five extends BaseActivity implements PremiumStatusListener {

    private AdView mAdView;
    private BillingManager billingManager;
    private FrameLayout adContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LocaleHelper.onAttach(this);
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.bsc11));
        setContentView(R.layout.activity_five);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0f);
        }


        adContainer = findViewById(R.id.ad_container);

//        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources()
//                .getColor(R.color.colorPrimaryDark)));
//
//
//        if (Build.VERSION.SDK_INT >= 21) {
//            Window window = this.getWindow();
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
//        }
//
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().setNavigationBarColor(ContextCompat.getColor(Five.this, R.color.colorPrimaryDark)); //setting bar color
//        }
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        //Banner
        setupActionBar();
        setupStatusBar();

        // Initialize billing manager with a simple listener
        billingManager = new BillingManager(this, new BillingManager.BillingListener() {
            @Override
            public void onPremiumStatusChanged(boolean isPremium) {
                runOnUiThread(() -> updateAdVisibility());
            }

            @Override
            public void onBillingSetupFinished() {
                runOnUiThread(() -> updateAdVisibility());
            }
        });

        // Initialize ads
        initializeAds();


    }


    /// Adaptive Banner Ads
    private AdSize getAdSize() {

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();

        int adWidthPixels = displayMetrics.widthPixels;

        float density = displayMetrics.density;

        int adWidth = (int) (adWidthPixels / density);

        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(
                this,
                adWidth
        );
    }


    private void initializeAds() {
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                setupBannerAd();
            }
        });
    }

//    private void setupBannerAd() {
//        mAdView = findViewById(R.id.adViewa14);
//        updateAdVisibility();
//    }
//
//    private void updateAdVisibility() {
//        if (mAdView != null) {
//            if (isPremium()) {
//                // Hide ads for premium users
//                mAdView.setVisibility(View.GONE);
//                mAdView.pause();
//            } else {
//                // Show ads for non-premium users
//                mAdView.setVisibility(View.VISIBLE);
//                mAdView.resume();
//                AdRequest adRequest = new AdRequest.Builder().build();
//                mAdView.loadAd(adRequest);
//            }
//        }
//    }


    //Adaptive Banner Ads
    private void setupBannerAd() {

        mAdView = new AdView(this);

        mAdView.setAdUnitId(getString(R.string.banner_id_admob));
        mAdView.setAdSize(getAdSize());

        adContainer.removeAllViews();
        adContainer.addView(mAdView);

        updateAdVisibility();
    }

    private void updateAdVisibility() {

        if (mAdView == null) {
            return;
        }

        if (isPremium()) {

            adContainer.setVisibility(View.GONE);
            mAdView.pause();

        } else {

            adContainer.setVisibility(View.VISIBLE);
            mAdView.loadAd(new AdRequest.Builder().build());
            mAdView.resume();
        }
    }


    @Override
    public boolean isPremium() {
        return billingManager != null && billingManager.isPremium();
    }

    private void setupActionBar() {
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(
                getResources().getColor(R.color.white)));
    }

    private void setupStatusBar() {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.white));
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.white));
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mAdView != null && !isPremium()) {
            mAdView.resume();
        }
    }

    @Override
    protected void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (billingManager != null) {
            billingManager.destroy();
        }
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        Context context = this; // or use getContext() if inside a fragment

        return super.onOptionsItemSelected(item);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void onAttachBaseContext(Context context) {
        // This is a workaround for fragments
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }

}