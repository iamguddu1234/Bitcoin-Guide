package com.iam.bitcoin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.helper.widget.MotionEffect;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.tabs.TabLayout;
import com.google.android.ump.ConsentInformation;
import com.iam.bitcoin.InAppPurchase.BillingManager;
import com.iam.bitcoin.InAppPurchase.PremiumStatusListener;
import com.iam.bitcoin.Multilanguage.LocaleHelper;
import com.iam.bitcoin.PrimiumDialogue.PremiumDialogFragment;

import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

public class Home extends AppCompatActivity implements PremiumStatusListener {

    private ConsentInformation consentInformation;
    private final AtomicBoolean isMobileAdsInitializeCalled = new AtomicBoolean(false);
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TextView selectedTextView;

    private BillingManager billingManager;
    private PremiumDialogFragment premiumDialog;
    private Handler handler = new Handler();
    private Runnable showDialogRunnable;
    private MyPagerAdapter adapter;

    // Holds a language the user picked but hasn't unlocked yet (premium-gated)
    private String selectedLanguageCode;

    // Dark-mode state
    private boolean isDarkModeOn;
    private AlertDialog languageDialog;
    private View languageDialogView;

    private BillingManager.BillingListener billingListener = new BillingManager.BillingListener() {
        @Override
        public void onPremiumStatusChanged(boolean isPremium) {
            updateAdVisibility();
            if (isPremium) {
                Toast.makeText(Home.this, "Thank you for purchasing ad removal!", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onBillingSetupFinished() {
            Log.d(MotionEffect.TAG, "Billing setup complete");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Language", "Current language before attach: " + Locale.getDefault().getLanguage());
        LocaleHelper.onAttach(this);
        Log.d("Language", "Current language after attach: " + Locale.getDefault().getLanguage());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();
        Gdpr gdpr = new Gdpr(this);
        gdpr.setGdpr();

        if (getIntent() != null && getIntent().hasExtra("SCROLL_TO_PREMIUM")) {
            new Handler().postDelayed(() -> {
                viewPager.setCurrentItem(0);
                Fragment fragment = adapter.getFragment(0);
                if (fragment instanceof AA_Basic) {
                    ((AA_Basic) fragment).scrollToPremiumButton();
                }
            }, 300);
        }

        billingManager = new BillingManager(this, new BillingManager.BillingListener() {
            @Override
            public void onPremiumStatusChanged(boolean isPremium) {
                runOnUiThread(() -> {
                    if (isPremium) {
                        cancelPendingDialog();
                        dismissDialogIfShowing();
                        // If a language was waiting on premium, apply it now
                        if (selectedLanguageCode != null) {
                            changeLanguage(selectedLanguageCode);
                            selectedLanguageCode = null;
                        }
                    }
                });
            }

            @Override
            public void onBillingSetupFinished() {
                runOnUiThread(() -> {
                    if (!billingManager.isPremium() && !isFinishing()) {
                        scheduleDialogShow();
                    }
                });
            }
        });

        // ViewPager + tabs
        viewPager = findViewById(R.id.viewPager);
        TextView textViewAll = findViewById(R.id.textViewAll);
        TextView textView1 = findViewById(R.id.textView1);
        TextView textView2 = findViewById(R.id.textView2);
        TextView textView3 = findViewById(R.id.textView3);

        adapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        textViewAll.setOnClickListener(view -> {
            viewPager.setCurrentItem(0);
            handleTextClick(textViewAll);
        });
        textView1.setOnClickListener(view -> {
            viewPager.setCurrentItem(1);
            handleTextClick(textView1);
        });
        textView2.setOnClickListener(view -> {
            viewPager.setCurrentItem(2);
            handleTextClick(textView2);
        });
        textView3.setOnClickListener(view -> {
            viewPager.setCurrentItem(3);
            handleTextClick(textView3);
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

            private void setUnselected(TextView textView) {
                textView.setBackgroundResource(R.drawable.unselected_tab_background);
                textView.setTextColor(Color.BLACK);
            }

            private void setSelected(TextView textView) {
                textView.setBackgroundResource(R.drawable.selected_tab);
                textView.setTextColor(Color.WHITE);
            }

            @Override
            public void onPageSelected(int position) {
                setUnselected(textViewAll);
                setUnselected(textView1);
                setUnselected(textView2);
                setUnselected(textView3);

                if (position == 0) {
                    setSelected(textViewAll);
                } else if (position == 1) {
                    setSelected(textView1);
                } else if (position == 2) {
                    setSelected(textView2);
                } else if (position == 3) {
                    setSelected(textView3);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) { }
        });

        handleTextClick(textViewAll);
        updateAdVisibility();

        // -----------------------------------------------------------
        //  HEADER + ACTION BUTTONS  (now at the activity level)
        //  These views must live in activity_home.xml
        // -----------------------------------------------------------
        setupActionButtons();
        new Handler(Looper.getMainLooper()).postDelayed(this::buildLanguageDialog, 500);

    }

    // ---------------------------------------------------------------
    //  WIRE UP changeLang / darkmode / share / about / rate
    // ---------------------------------------------------------------
    private void setupActionButtons() {
        // Restore saved dark-mode preference
        isDarkModeOn = getSharedPreferences("settings", MODE_PRIVATE)
                .getBoolean("dark_mode", false);
        AppCompatDelegate.setDefaultNightMode(
                isDarkModeOn ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);

        // Change language
        View changeLang = findViewById(R.id.changeLang);
        if (changeLang != null) {
            changeLang.setOnClickListener(v -> showLanguageDialog());
        }

        // Dark mode toggle
        View darkmode = findViewById(R.id.darkmode);
        if (darkmode != null) {
            darkmode.setOnClickListener(v -> {
                isDarkModeOn = !isDarkModeOn;
                getSharedPreferences("settings", MODE_PRIVATE).edit()
                        .putBoolean("dark_mode", isDarkModeOn).apply();
                AppCompatDelegate.setDefaultNightMode(
                        isDarkModeOn ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
            });
        }

        // Share
        View sharebtn = findViewById(R.id.sharebtn);
        if (sharebtn != null) {
            sharebtn.setOnClickListener(v -> {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Learn Bitcoin");
                intent.putExtra(Intent.EXTRA_TEXT, "Bitcoin Guide\n" +
                        "https://play.google.com/store/apps/details?id=com.iam.bitcoin");
                startActivity(Intent.createChooser(intent, "Share App"));
            });
        }

        // About
        View aboutbtn = findViewById(R.id.aboutbtn);
        if (aboutbtn != null) {
            aboutbtn.setOnClickListener(v ->
                    startActivity(new Intent(Home.this, aboutA.class)));
        }

        // Rate
        View ratebtn = findViewById(R.id.ratebtn);
        if (ratebtn != null) {
            ratebtn.setOnClickListener(v ->
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=com.iam.bitcoin"))));
        }
    }

    // ---------------------------------------------------------------
    //  LANGUAGE SELECTION DIALOG
    // ---------------------------------------------------------------
    private void showLanguageDialog() {
        if (languageDialog == null) {
            buildLanguageDialog();   // first time only
        }
        // reset the search box each time it opens
        EditText searchBox = languageDialogView.findViewById(R.id.etSearchLanguage);
        if (searchBox != null) searchBox.setText("");
        languageDialog.show();
    }

    private void buildLanguageDialog() {
        languageDialogView = LayoutInflater.from(this)
                .inflate(R.layout.dialog_language_selector, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(languageDialogView);
        languageDialog = builder.create();
        if (languageDialog.getWindow() != null) {
            languageDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }

        View view = languageDialogView;

        EditText searchBox = view.findViewById(R.id.etSearchLanguage);
        searchBox.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int a, int b, int c) { }
            @Override public void onTextChanged(CharSequence s, int a, int b, int c) {
                filterLanguages(view, s.toString());
            }
            @Override public void afterTextChanged(Editable s) { }
        });

        View.OnClickListener languageClickListener = v -> {
            String langCode = "";
            if (v.getId() == R.id.lang_english) langCode = "en";
            else if (v.getId() == R.id.lang_arabic) langCode = "ar";
            else if (v.getId() == R.id.lang_bengali) langCode = "bn";
            else if (v.getId() == R.id.lang_german) langCode = "de";
            else if (v.getId() == R.id.lang_spanish) langCode = "es";
            else if (v.getId() == R.id.lang_french) langCode = "fr";
            else if (v.getId() == R.id.lang_hindi) langCode = "hi";
            else if (v.getId() == R.id.lang_indonesian) langCode = "in";
            else if (v.getId() == R.id.lang_japanese) langCode = "ja";
            else if (v.getId() == R.id.lang_korean) langCode = "ko";
            else if (v.getId() == R.id.lang_portuguese_br) langCode = "pt";
            else if (v.getId() == R.id.lang_russian) langCode = "ru";
            else if (v.getId() == R.id.lang_tamil) langCode = "ta";
            else if (v.getId() == R.id.lang_telugu) langCode = "te";
            else if (v.getId() == R.id.lang_turkish) langCode = "tr";
            else if (v.getId() == R.id.lang_vietnamese) langCode = "vi";
            else if (v.getId() == R.id.lang_chinese) langCode = "zh";
            else if (v.getId() == R.id.lang_filipino) langCode = "tl";
            else if (v.getId() == R.id.lang_italian) langCode = "it";

            if (!langCode.isEmpty()) {
                handleLanguageSelection(langCode, languageDialog);
            }
        };

        int[] ids = {
                R.id.lang_english, R.id.lang_arabic, R.id.lang_bengali, R.id.lang_german,
                R.id.lang_spanish, R.id.lang_french, R.id.lang_hindi, R.id.lang_indonesian,
                R.id.lang_japanese, R.id.lang_korean, R.id.lang_portuguese_br, R.id.lang_russian,
                R.id.lang_tamil, R.id.lang_telugu, R.id.lang_turkish, R.id.lang_vietnamese,
                R.id.lang_chinese, R.id.lang_filipino, R.id.lang_italian
        };
        for (int id : ids) {
            View row = view.findViewById(id);
            if (row != null) row.setOnClickListener(languageClickListener);
        }
    }

    // ---------------------------------------------------------------
    //  HANDLE A SELECTION  (English free, others premium-gated)
    // ---------------------------------------------------------------
    private void handleLanguageSelection(String langCode, AlertDialog dialog) {
        dialog.dismiss();

        if ("en".equals(langCode)) {
            changeLanguage(langCode);
            return;
        }

        if (isPremium()) {
            changeLanguage(langCode);
        } else {
            selectedLanguageCode = langCode;
            showPremiumRequiredDialog();
        }
    }

    // ---------------------------------------------------------------
    //  PREMIUM UPSELL DIALOG
    // ---------------------------------------------------------------
    private void showPremiumRequiredDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.premium_required_dialog, null);
        builder.setView(dialogView);

        AlertDialog dialog = builder.create();
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }
        dialog.show();

        CardView cancelButton = dialogView.findViewById(R.id.cancelButton);
        CardView upgradeButton = dialogView.findViewById(R.id.upgradeButton);

        TextView tvPrice = dialogView.findViewById(R.id.tvOldPrice);

        tvPrice.setPaintFlags(
                tvPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG
        );

        cancelButton.setOnClickListener(v -> dialog.dismiss());

        upgradeButton.setOnClickListener(v -> {
            dialog.dismiss();
            new Handler().postDelayed(() -> {
                if (billingManager != null) {
                    billingManager.launchPurchaseFlow();
                }
            }, 500);
        });
    }

    // ---------------------------------------------------------------
    //  APPLY THE LANGUAGE AND RESTART
    // ---------------------------------------------------------------
    private void changeLanguage(String langCode) {
        try {
            LocaleHelper.setAppLocale(getApplicationContext(), langCode);

            Intent intent = new Intent(this, Home.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                    | Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finishAffinity();
        } catch (Exception e) {
            Log.e("LanguageChange", "Error changing language", e);
        }
    }

    // ---------------------------------------------------------------
    //  SEARCH FILTER FOR THE LANGUAGE LIST
    // ---------------------------------------------------------------
    private void filterLanguages(View rootView, String query) {
        int[] languageIds = {
                R.id.lang_english,
                R.id.lang_arabic,
                R.id.lang_bengali,
                R.id.lang_german,
                R.id.lang_spanish,
                R.id.lang_french,
                R.id.lang_hindi,
                R.id.lang_indonesian,
                R.id.lang_japanese,
                R.id.lang_korean,
                R.id.lang_portuguese_br,
                R.id.lang_russian,
                R.id.lang_tamil,
                R.id.lang_telugu,
                R.id.lang_turkish,
                R.id.lang_vietnamese,
                R.id.lang_chinese,
                R.id.lang_filipino,
                R.id.lang_italian
        };

        TextView tvNoResults = rootView.findViewById(R.id.tvNoResults);
        query = query.toLowerCase().trim();
        boolean found = false;

        for (int id : languageIds) {
            View card = rootView.findViewById(id);
            if (card == null) continue;

            Object tag = card.getTag();
            if (tag == null) continue;

            String language = tag.toString().toLowerCase();
            if (language.contains(query)) {
                card.setVisibility(View.VISIBLE);
                found = true;
            } else {
                card.setVisibility(View.GONE);
            }
        }

        if (tvNoResults != null) {
            tvNoResults.setVisibility(found ? View.GONE : View.VISIBLE);
        }
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        private Fragment[] fragments = new Fragment[4];

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (fragments[position] == null) {
                switch (position) {
                    case 0:
                        fragments[0] = new AllFragment();
                        break;
                    case 1:
                        fragments[1] = new AA_Basic();
                        break;
                    case 2:
                        fragments[2] = new AA_Intermediate();
                        break;
                    case 3:
                        fragments[3] = new AA_Advance();
                        break;
                }
            }
            return fragments[position];
        }

        public Fragment getFragment(int position) {
            return fragments[position];
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Fragment " + (position + 1);
        }
    }

    private void handleTextClick(TextView textView) {
        if (selectedTextView != null) {
            selectedTextView.setBackgroundResource(R.drawable.unselected_tab_background);
            selectedTextView.setTextColor(Color.BLACK);
        }

        textView.setBackgroundResource(R.drawable.selected_tab);
        textView.setTextColor(ContextCompat.getColor(this, R.color.white));
        selectedTextView = textView;

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0f);
        }
        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(getResources().getColor(R.color.white)));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.white));
            window.setNavigationBarColor(ContextCompat.getColor(this, R.color.white));
        }

        getWindow().getDecorView()
                .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    private void updateAdVisibility() {
        if (billingManager.isPremium()) {
            MobileAds.initialize(this, initializationStatus -> { });
        } else {
            MobileAds.initialize(this, new OnInitializationCompleteListener() {
                @Override
                public void onInitializationComplete(InitializationStatus initializationStatus) { }
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (billingManager != null) {
            billingManager.queryPurchases();
        }
        LocaleHelper.setAppLocale(this, LocaleHelper.getPersistedLanguage(this));
    }

    @Override
    public boolean isPremium() {
        return billingManager != null && billingManager.isPremium();
    }

//    private void scheduleDialogShow() {
//        cancelPendingDialog();
//        showDialogRunnable = new Runnable() {
//            @Override
//            public void run() {
//                if (!billingManager.isPremium() && !isFinishing()) {
//                    premiumDialog = new PremiumDialogFragment();
//                    premiumDialog.show(getSupportFragmentManager(), "PremiumDialog");
//                }
//            }
//        };
//        handler.postDelayed(showDialogRunnable, 500);
//    }

    private void cancelPendingDialog() {
        if (showDialogRunnable != null) {
            handler.removeCallbacks(showDialogRunnable);
            showDialogRunnable = null;
        }
    }

//    private void dismissDialogIfShowing() {
//        if (premiumDialog != null && premiumDialog.isVisible()) {
//            premiumDialog.dismiss();
//            premiumDialog = null;
//        }
//    }



    /** Safe to touch the FragmentManager right now? */
    private boolean canCommitFragments() {
        return !isFinishing()
                && !isDestroyed()
                && !getSupportFragmentManager().isDestroyed()
                && !getSupportFragmentManager().isStateSaved();
    }

    private void scheduleDialogShow() {
        cancelPendingDialog();
        showDialogRunnable = () -> {
            // re-check at the moment the runnable actually fires
            if (billingManager != null && !billingManager.isPremium() && canCommitFragments()) {
                premiumDialog = new PremiumDialogFragment();
                premiumDialog.show(getSupportFragmentManager(), "PremiumDialog");
            }
        };
        handler.postDelayed(showDialogRunnable, 500);
    }

    private void dismissDialogIfShowing() {
        if (premiumDialog != null && premiumDialog.isVisible() && canCommitFragments()) {
            premiumDialog.dismissAllowingStateLoss();
            premiumDialog = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelPendingDialog();
        dismissDialogIfShowing();
        if (billingManager != null) {
            billingManager.destroy();
        }
    }

    public BillingManager getBillingManager() {
        return billingManager;
    }

    @Override
    public void onBackPressed() {
    }

    public void openBasicTabAndScrollToPremium() {
        viewPager.setCurrentItem(0, true);
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Fragment fragment = adapter.getFragment(0);
            if (fragment instanceof AA_Basic) {
                ((AA_Basic) fragment).scrollToPremiumButton();
            }
        }, 200);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base));
    }

    public class NoSwipeViewPager extends ViewPager {
        public NoSwipeViewPager(Context context) {
            super(context);
        }

        public NoSwipeViewPager(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        @Override
        public boolean onTouchEvent(MotionEvent ev) {
            return false;
        }

        @Override
        public boolean onInterceptTouchEvent(MotionEvent ev) {
            return false;
        }
    }

    public void switchToTab(int index) {
        if (viewPager != null) {
            viewPager.setCurrentItem(index, true); // true = smooth scroll
        }
    }
}