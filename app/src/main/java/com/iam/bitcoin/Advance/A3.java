package com.iam.bitcoin.Advance;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.iam.bitcoin.Adapters.AdapterTwo;
import com.iam.bitcoin.BaseActivity.BaseActivity;
import com.iam.bitcoin.InAppPurchase.BillingManager;
import com.iam.bitcoin.InAppPurchase.PremiumStatusListener;
import com.iam.bitcoin.Model.Model;
import com.iam.bitcoin.Multilanguage.LocaleHelper;
import com.iam.bitcoin.R;

import java.util.ArrayList;
import java.util.List;

public class A3 extends BaseActivity implements PremiumStatusListener {

    private AdView mAdView;
    private BillingManager billingManager;
    private FrameLayout adContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LocaleHelper.onAttach(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a3);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0f);
        }
        getSupportActionBar().setTitle(getString(R.string.adve12));
//        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources()
//                .getColor(R.color.black)));
//
//        if (Build.VERSION.SDK_INT >= 21) {
//            Window window = this.getWindow();
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            window.setStatusBarColor(this.getResources().getColor(R.color.black));
//        }


        adContainer = findViewById(R.id.ad_container);

        RecyclerView a3Rec = findViewById(R.id.a3rec);
        a3Rec.setHasFixedSize(true);
        a3Rec.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        List<Model> models = new ArrayList<>();
        models.add(new Model(R.string.w4, R.string.w5));
        models.add(new Model(R.string.v4, R.string.v5));
        models.add(new Model(R.string.v6, R.string.v7));
        models.add(new Model(R.string.v8, R.string.v9));
        models.add(new Model(R.string.v10, R.string.v11));
        AdapterTwo adapterTwo = new AdapterTwo(models);
        a3Rec.setAdapter(adapterTwo);


        RecyclerView a3Reci = findViewById(R.id.a3reci);
        a3Reci.setHasFixedSize(true);
        a3Reci.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        List<Model> models1 = new ArrayList<>();
        models.add(new Model(R.string.w4, R.string.w5));
        models1.add(new Model(R.string.v13, R.string.v14));
        models1.add(new Model(R.string.v15, R.string.v16));
        models1.add(new Model(R.string.v17, R.string.v18));
        models1.add(new Model(R.string.v19, R.string.v20));
        AdapterTwo adapterTwo1 = new AdapterTwo(models1);
        a3Reci.setAdapter(adapterTwo1);


        RecyclerView a3Recii = findViewById(R.id.a3recii);
        a3Recii.setHasFixedSize(true);
        a3Recii.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        List<Model> models2 = new ArrayList<>();
        models.add(new Model(R.string.w4, R.string.w5));
        models2.add(new Model(R.string.v22, R.string.v23));
        models2.add(new Model(R.string.v24, R.string.v25));
        models2.add(new Model(R.string.v26, R.string.v27));
        AdapterTwo adapterTwo2 = new AdapterTwo(models2);
        a3Recii.setAdapter(adapterTwo2);


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