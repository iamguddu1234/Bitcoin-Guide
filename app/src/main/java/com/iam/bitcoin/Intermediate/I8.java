package com.iam.bitcoin.Intermediate;

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

public class I8 extends BaseActivity implements PremiumStatusListener {

    private AdView mAdView;
    private BillingManager billingManager;
    private FrameLayout adContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LocaleHelper.onAttach(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i8);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0f);
        }
        getSupportActionBar().setTitle(getString(R.string.interm25));
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

        RecyclerView i8Rec = findViewById(R.id.i8rec);
        i8Rec.setHasFixedSize(true);
        i8Rec.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        List<Model> models = new ArrayList<>();
        models.add(new Model(R.string.p4, R.string.p5));
        models.add(new Model(R.string.p6, R.string.p7));
        models.add(new Model(R.string.p8, R.string.p9));
        AdapterTwo adapterTwo = new AdapterTwo(models);
        i8Rec.setAdapter(adapterTwo);

        RecyclerView i8Reci = findViewById(R.id.i8reci);
        i8Reci.setHasFixedSize(true);
        i8Reci.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        List<Model> models1 = new ArrayList<>();
        models1.add(new Model(R.string.p11, R.string.p12));
        models1.add(new Model(R.string.p13, R.string.p14));
        models1.add(new Model(R.string.p15, R.string.p16));
        models1.add(new Model(R.string.p17, R.string.p18));
        models1.add(new Model(R.string.p19, R.string.p20));
        AdapterTwo adapterTwo1 = new AdapterTwo(models1);
        i8Reci.setAdapter(adapterTwo1);

        RecyclerView i8Recii = findViewById(R.id.i8recii);
        i8Recii.setHasFixedSize(true);
        i8Recii.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        List<Model> models2 = new ArrayList<>();
        models2.add(new Model(R.string.p22, R.string.p23));
        models2.add(new Model(R.string.p24, R.string.p25));
        models2.add(new Model(R.string.p26, R.string.p27));
        AdapterTwo adapterTwo2 = new AdapterTwo(models2);
        i8Recii.setAdapter(adapterTwo2);


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