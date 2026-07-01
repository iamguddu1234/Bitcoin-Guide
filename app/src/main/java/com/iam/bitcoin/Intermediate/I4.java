package com.iam.bitcoin.Intermediate;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
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
import com.iam.bitcoin.CustomTypefaceSpan;
import com.iam.bitcoin.InAppPurchase.BillingManager;
import com.iam.bitcoin.InAppPurchase.PremiumStatusListener;
import com.iam.bitcoin.Model.Model;
import com.iam.bitcoin.Multilanguage.LocaleHelper;
import com.iam.bitcoin.R;

import java.util.ArrayList;
import java.util.List;

public class I4 extends BaseActivity implements PremiumStatusListener {

    private AdView mAdView;
    private BillingManager billingManager;
    private FrameLayout adContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LocaleHelper.onAttach(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i4);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_circle);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0f);
        }
//        getSupportActionBar().setTitle(getString(R.string.interm21));
        String title = getString(R.string.interm21);
        SpannableString spannableTitle = new SpannableString(title);

        Typeface customTypeface = ResourcesCompat.getFont(this, R.font.roboto_medium);

        spannableTitle.setSpan(
                new AbsoluteSizeSpan(18, true),
                0, title.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        spannableTitle.setSpan(
                new CustomTypefaceSpan(customTypeface),
                0, title.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        getSupportActionBar().setTitle(spannableTitle);

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


        RecyclerView i4Rec = findViewById(R.id.i4rec);
        i4Rec.setHasFixedSize(true);
        i4Rec.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        List<Model> models = new ArrayList<>();
        models.add(new Model(R.string.m4, R.string.m5));
        models.add(new Model(R.string.m6, R.string.m7));
        models.add(new Model(R.string.m8, R.string.m9));
        AdapterTwo adapterTwo = new AdapterTwo(models);
        i4Rec.setAdapter(adapterTwo);

        RecyclerView i4Rec2 = findViewById(R.id.i4rec2);
        i4Rec2.setHasFixedSize(true);
        i4Rec.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        List<Model> models2 = new ArrayList<>();
        models2.add(new Model(R.string.m11, R.string.m12));
        models2.add(new Model(R.string.m13, R.string.m14));
        models2.add(new Model(R.string.m15, R.string.m16));
        models2.add(new Model(R.string.m17, R.string.m18));
        models2.add(new Model(R.string.m19, R.string.m20));
        AdapterTwo adapterTwo2 = new AdapterTwo(models2);
        i4Rec2.setAdapter(adapterTwo2);


        RecyclerView i4Rec4 = findViewById(R.id.i4rec4);
        i4Rec4.setHasFixedSize(true);
        i4Rec4.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        List<Model> models4 = new ArrayList<>();
        models4.add(new Model(R.string.m22, R.string.m23));
        models4.add(new Model(R.string.m24, R.string.m25));
        models4.add(new Model(R.string.m26, R.string.m27));
        AdapterTwo adapterTwo4 = new AdapterTwo(models4);
        i4Rec4.setAdapter(adapterTwo4);


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