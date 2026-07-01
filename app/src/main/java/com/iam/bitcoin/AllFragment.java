package com.iam.bitcoin;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.helper.widget.MotionEffect;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.iam.bitcoin.Game.GameAccessManager;
import com.iam.bitcoin.Game.QuizGame;
import com.iam.bitcoin.InAppPurchase.BillingManager;
import com.iam.bitcoin.InAppPurchase.PremiumStatusListener;
import com.iam.bitcoin.Multilanguage.LocaleHelper;
import com.iam.bitcoin.databinding.FragmentAABasicBinding;
import com.iam.bitcoin.databinding.FragmentAllBinding;


public class AllFragment extends Fragment implements PremiumStatusListener {

    private InterstitialAd interstitialAd;
    @NonNull FragmentAllBinding binding;


    private boolean isDarkModeOn;
    BillingManager billingManager;


    private PremiumStatusListener premiumStatusListener;
    private String selectedLanguageCode; // Store selected language temporarily
    private GameAccessManager gameAccessManager;

    Button removeAds;

    private static final long AD_COOLDOWN_MS = 30000; // 30 seconds
    private long lastAdShownTime = 0;

    // Add this new field to track pending language changes
    private boolean isLanguageChangePending = false;

    private RewardedAd rewardedAd;
    // TODO: replace with your real rewarded ad unit id (this is Google's TEST id)
    private static final String REWARDED_AD_UNIT_ID = "ca-app-pub-3940256099942544/5224354917";

    private BillingManager.BillingListener billingListener = new BillingManager.BillingListener() {
        @Override
        public void onPremiumStatusChanged(boolean isPremium) {
            updateAdVisibility();

            if (isPremium && isAdded() && getContext() != null) {  // Add context checks
                Toast.makeText(getContext(), "Thank you for purchasing ad removal!", Toast.LENGTH_SHORT).show();
            }


        }

        @Override
        public void onBillingSetupFinished() {
            Log.d(MotionEffect.TAG, "Billing setup complete");
        }

    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAllBinding.inflate(inflater);

//        loadInterstitialAd();
//
//        initializeAdMob();

        billingManager = new BillingManager(getActivity(), billingListener);
        gameAccessManager = new GameAccessManager(requireContext(), billingManager);


        loadRewardedAd();   // <-- ADD THIS

        // Check saved dark mode setting
        isDarkModeOn = requireActivity().getSharedPreferences("settings", MODE_PRIVATE)
                .getBoolean("dark_mode", false);
        // Apply the saved mode
        AppCompatDelegate.setDefaultNightMode(
                isDarkModeOn ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);


        // 🔍 Scroll if argument flag is passed
        Bundle args = getArguments();
        if (args != null && args.getBoolean("SCROLL_TO_REMOVE_ADS", false)) {
            scrollToPremiumButton();
        }


//        removeAds = view.findViewById(R.id.remove_ads_button);
//        binding.removeAdsButton.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.glowing_button_bg));
//        if (binding.removeAdsButton != null) {
//            Animation glowAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.button_pulse_glow);
//            binding.removeAdsButton.startAnimation(glowAnimation);
//        }
//
        binding.removeAdsButton.setOnClickListener(v -> {
            Log.d(TAG, "Button clicked"); // Verify this appears in Logcat

            if (billingManager != null) {
                billingManager.launchPurchaseFlow();
            }
        });



        binding.gotoBasic.setOnClickListener(v -> {
            if (getActivity() instanceof Home) {
                ((Home) getActivity()).switchToTab(1); // 1 = Basic tab
            }
        });

        binding.gotoIntermediate.setOnClickListener(v -> {
            if (getActivity() instanceof  Home){
                ((Home) getActivity()).switchToTab(2);
            }
        });

        binding.gotoAdvance.setOnClickListener(v -> {
            if (getActivity() instanceof  Home){
                ((Home) getActivity()).switchToTab(3);
            }
        });

        binding.gotoMyApps.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, new AA_My_Apps())
                    .addToBackStack("myapps")
                    .commit();
        });



        binding.quizGame.setOnClickListener(v -> handleQuizAccess());







        binding.darkmode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isDarkModeOn = !isDarkModeOn;

                // Save the preference
                requireActivity().getSharedPreferences("settings", MODE_PRIVATE).edit()
                        .putBoolean("dark_mode", isDarkModeOn).apply();

                // Apply the mode
                AppCompatDelegate.setDefaultNightMode(
                        isDarkModeOn ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);

            }
        });
        binding.sharebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent("android.intent.action.SEND");
                intent2.setType("text/plain");
                intent2.putExtra("android.intent.extra.SUBJECT", "Check Out: Nmap");
                intent2.putExtra("android.intent.extra.TEXT", "Bitcoin Guide" + "\n" +
                        "https://play.google.com/store/apps/details?id=com.iam.bitcoin");
                startActivity(Intent.createChooser(intent2, "Share App"));

            }
        });
        binding.aboutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), aboutA.class);
                startActivity(intent);
//                Animatoo.INSTANCE.animateInAndOut(getContext());
            }
        });
        binding.ratebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.iam.bitcoin"));
                startActivity(intent1);


            }
        });
        updateAdVisibility();

        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



    }


//    private void initializeAdMob() {
//        if (!isAdded() || getContext() == null) {
//            Log.w(TAG, "Fragment not attached, delaying AdMob initialization");
//            // Optionally retry later using a handler if needed
//            new Handler(Looper.getMainLooper()).postDelayed(this::initializeAdMob, 500);
//            return;
//        }
//
//        MobileAds.initialize(requireContext(), status -> {
//            Log.d(TAG, "AdMob initialized");
//            if (isAdded() && !isPremium()) {  // Double-check fragment attachment
//                loadInterstitialAd();
//            }
//        });
//    }
//
//    private void loadInterstitialAd() {
//
//        // Check if fragment is attached first
//        if (!isAdded() || getContext() == null) {
//            Log.d(TAG, "Fragment not attached, skipping ad load");
//            return;
//        }
//
//        // First check if user has premium (ad-free) version
//        if (premiumStatusListener != null && premiumStatusListener.isPremium()) {
//            interstitialAd = null; // Ensure no ad is held in memory
//            return; // Skip ad loading for premium users
//        }
//
//        AdRequest adRequest = new AdRequest.Builder().build();
//
//        InterstitialAd.load(
//                requireContext(),
////                "ca-app-pub-5541243853026577/4403565278",
//                "ca-app-pub-5541243853026577/4063563669",
//                adRequest,
//                new InterstitialAdLoadCallback() {
//                    @Override
//                    public void onAdLoaded(@NonNull InterstitialAd loadedInterstitialAd) {
//                        // Double check premium status in case it changed during load
//                        if (premiumStatusListener != null && premiumStatusListener.isPremium()) {
//                            loadedInterstitialAd = null;
//                            return;
//                        }
//
//                        interstitialAd = loadedInterstitialAd;
//                        Log.d(TAG, "Interstitial ad loaded successfully");
//                    }
//
//                    @Override
//                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
//                        Log.d(TAG, "Ad failed to load: " + loadAdError.getMessage());
//                        // Optionally retry loading after a delay
//                    }
//                });
//    }

//    private void showInterstitialAd() {
//
//        // Check fragment attachment first
//        if (!isAdded() || getActivity() == null) {
//            Log.d(TAG, "Fragment not attached, skipping ad show");
//            return;
//        }
//
//
//        // Don't show ads if premium
//        if (premiumStatusListener != null && premiumStatusListener.isPremium()) {
//            Log.d(TAG, "Skipping ad - user is premium");
//            return;
//        }
//
//        // Check if enough time has passed since last ad
//        long currentTime = System.currentTimeMillis();
//        if (currentTime - lastAdShownTime < AD_COOLDOWN_MS) {
//            Log.d(TAG, "Ad skipped - within cooldown period");
//            return;
//        }
//
//        if (interstitialAd != null) {
//            interstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
//                @Override
//                public void onAdDismissedFullScreenContent() {
//                    interstitialAd = null;
//                    lastAdShownTime = System.currentTimeMillis(); // Update last shown time
//                    Log.d(TAG, "Ad dismissed");
//                    // Load the next ad
//                    loadInterstitialAd();
//                }
//
//                @Override
//                public void onAdFailedToShowFullScreenContent(AdError adError) {
//                    interstitialAd = null;
//                    Log.d(TAG, "Ad failed to show: " + adError.getMessage());
//                    loadInterstitialAd();
//                }
//
//                @Override
//                public void onAdShowedFullScreenContent() {
//                    lastAdShownTime = System.currentTimeMillis(); // Update last shown time
//                    Log.d(TAG, "Ad showed successfully");
//                }
//            });
//
//            interstitialAd.show(requireActivity());
//        } else {
//            Log.d(TAG, "Ad not ready, loading new one");
//            loadInterstitialAd();
//        }
//    }

    // Update your showLanguageDialog method
    private void showLanguageDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.dialog_language_selector, null);
        builder.setView(view);

        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();

        EditText searchBox = view.findViewById(R.id.etSearchLanguage);

        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterLanguages(view, s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        // Simplified language selection handler
        View.OnClickListener languageClickListener = v -> {
            String langCode = "";
            if (v.getId() == R.id.lang_english) {
                langCode = "en";
            } else if (v.getId() == R.id.lang_arabic) {
                langCode = "ar";

            } else if (v.getId() == R.id.lang_bengali) {
                langCode = "bn";
            } else if (v.getId() == R.id.lang_german) {
                langCode = "de";
            } else if (v.getId() == R.id.lang_spanish) {
                langCode = "es";
            } else if (v.getId() == R.id.lang_french) {
                langCode = "fr";
            } else if (v.getId() == R.id.lang_hindi) {
                langCode = "hi";
            } else if (v.getId() == R.id.lang_indonesian) {
                langCode = "in";
            }else if (v.getId() == R.id.lang_japanese) {
                langCode = "ja";
            }else if (v.getId() == R.id.lang_korean) {
                langCode = "ko";
            }else if (v.getId() == R.id.lang_portuguese_br) {
                langCode = "pt";
            }else if (v.getId() == R.id.lang_russian) {
                langCode = "ru";
            }else if (v.getId() == R.id.lang_tamil) {
                langCode = "ta";
            }else if (v.getId() == R.id.lang_telugu) {
                langCode = "te";
            }else if (v.getId() == R.id.lang_turkish) {
                langCode = "tr";
            }else if (v.getId() == R.id.lang_vietnamese) {
                langCode = "vi";
            }else if (v.getId() == R.id.lang_chinese) {
                langCode = "zh";
            }else if (v.getId() == R.id.lang_filipino) {
                langCode = "tl";
            }else if (v.getId() == R.id.lang_italian) {
                langCode = "it";
            }
//            }else if (v.getId() == R.id.lang_thai) {
//                langCode = "th";
//            }

            if (!langCode.isEmpty()) {
                handleLanguageSelection(langCode, dialog);
            }
        };

        // Set click listeners
        view.findViewById(R.id.lang_english).setOnClickListener(languageClickListener);
        view.findViewById(R.id.lang_arabic).setOnClickListener(languageClickListener);
        view.findViewById(R.id.lang_bengali).setOnClickListener(languageClickListener);
        view.findViewById(R.id.lang_german).setOnClickListener(languageClickListener);
        view.findViewById(R.id.lang_spanish).setOnClickListener(languageClickListener);
        view.findViewById(R.id.lang_french).setOnClickListener(languageClickListener);
        view.findViewById(R.id.lang_hindi).setOnClickListener(languageClickListener);
        view.findViewById(R.id.lang_indonesian).setOnClickListener(languageClickListener);
        view.findViewById(R.id.lang_japanese).setOnClickListener(languageClickListener);
        view.findViewById(R.id.lang_korean).setOnClickListener(languageClickListener);
        view.findViewById(R.id.lang_portuguese_br).setOnClickListener(languageClickListener);
        view.findViewById(R.id.lang_russian).setOnClickListener(languageClickListener);
        view.findViewById(R.id.lang_tamil).setOnClickListener(languageClickListener);
        view.findViewById(R.id.lang_telugu).setOnClickListener(languageClickListener);
        view.findViewById(R.id.lang_turkish).setOnClickListener(languageClickListener);
        view.findViewById(R.id.lang_vietnamese).setOnClickListener(languageClickListener);
        view.findViewById(R.id.lang_chinese).setOnClickListener(languageClickListener);
        view.findViewById(R.id.lang_filipino).setOnClickListener(languageClickListener);
        view.findViewById(R.id.lang_italian).setOnClickListener(languageClickListener);
//        view.findViewById(R.id.lang_thai).setOnClickListener(languageClickListener);
    }


    // Updated handleLanguageSelection method
    private void handleLanguageSelection(String langCode, AlertDialog dialog) {
        dialog.dismiss();

        // Always allow English
        if ("en".equals(langCode)) {
            changeLanguage(langCode);
            return;
        }

        // For other languages, check premium status
        if (isPremium()) {
            changeLanguage(langCode);
        } else {
            selectedLanguageCode = langCode;
            isLanguageChangePending = true;
            showPremiumRequiredDialog();
        }
    }


    private void showPremiumRequiredDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        LayoutInflater inflater = LayoutInflater.from(requireContext());
        View dialogView = inflater.inflate(R.layout.premium_required_dialog, null);
        builder.setView(dialogView);

        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent); // Transparent corners
        dialog.show();

        // Get views
        Button cancelButton = dialogView.findViewById(R.id.cancelButton);
        Button upgradeButton = dialogView.findViewById(R.id.upgradeButton);

        // Cancel action
        cancelButton.setOnClickListener(v -> dialog.dismiss());

        // Upgrade action
        upgradeButton.setOnClickListener(v -> {
            dialog.dismiss();
//            scrollToPremiumButton();
            new Handler().postDelayed(() -> {
                if (billingManager != null) {
                    billingManager.launchPurchaseFlow();
                }
            }, 500);
        });
    }

    private void changeLanguage(String langCode) {
        try {
            // Update app-wide locale
//            LocaleHelper.setAppLocale(requireContext(), langCode);
            LocaleHelper.setAppLocale(requireContext().getApplicationContext(), langCode);


            // Restart the app completely
            Intent intent = new Intent(getActivity(), Home.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            requireActivity().finishAffinity(); // 💥 closes all activities

            // Add transition animation
            if (getActivity() != null) {
                getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                getActivity().finish();
            }
        } catch (Exception e) {
            Log.e("LanguageChange", "Error changing language", e);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void onAttachBaseContext(Context context) {
        // This is a workaround for fragments
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof PremiumStatusListener) {
            premiumStatusListener = (PremiumStatusListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement PremiumStatusListener");
        }
    }

    @Override
    public void onDestroyView() {

        View view = getView();
        if (view != null) {
            CardView removeAdsButton = view.findViewById(R.id.remove_ads_button);
            if (removeAdsButton != null) {
                removeAdsButton.clearAnimation();
            }
        }


        if (billingManager != null) {
            billingManager.destroy();
        }

        if (interstitialAd != null) {
            interstitialAd = null;
        }
        super.onDestroyView();
    }


    private void updateAdVisibility() {
        if (!isAdded() || getView() == null) return;

        try {
            if (binding != null) {
                binding.removeAdsButton.setVisibility(isPremium() ? View.GONE : View.VISIBLE);

                // Also update other premium-related UI if needed
                if (isPremium()) {
                    binding.removeAdsButton.clearAnimation();
                    interstitialAd = null; // Clear any loaded ads
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "Error updating ad visibility", e);
        }
    }

    //    private void updateAdVisibility() {
//        if (binding.removeAdsButton == null) return;
//
//        binding.removeAdsButton.setVisibility(isPremium() ? View.GONE : View.VISIBLE);
//
//        if (!isPremium()) {
//            loadInterstitialAd();
//        }
//    }
    public void scrollToPremiumButton() {
        View view = getView();
        if (view != null) {
            view.post(() -> {
                NestedScrollView scrollView = view.findViewById(R.id.scrollView);
                View premiumButton = view.findViewById(R.id.remove_ads_button);
                if (scrollView != null && premiumButton != null) {
                    scrollView.smoothScrollTo(0, premiumButton.getTop());
                }
            });
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        updateAdVisibility(); // Refresh UI state

        // Check if user just became premium and we have a pending language change
        if (selectedLanguageCode != null && billingManager != null && billingManager.isPremium()) {
            changeLanguage(selectedLanguageCode);
            selectedLanguageCode = null; // Clear the pending language
        }

        // Check purchases when returning to app (in case purchase was made while app was in background)
        if (billingManager != null) {
            billingManager.queryPurchases();
        }

        Context context = LocaleHelper.onAttach(requireContext());
        Resources resources = context.getResources();
        Configuration config = resources.getConfiguration();
        resources.updateConfiguration(config, resources.getDisplayMetrics());


    }

    @Override
    public boolean isPremium() {
        return billingManager != null && billingManager.isPremium();
    }

    private void initializeManagers() {
        try {
            if (getActivity() == null) return;

            // Lazy initialization
            if (billingManager == null) {
                billingManager = new BillingManager(requireActivity(), billingListener);
            }
            if (gameAccessManager == null) {
                gameAccessManager = new GameAccessManager(requireContext(), billingManager);
            }
        } catch (Exception e) {
            Log.e("QuizGame", "Initialization failed", e);
        }
    }

    private void setupQuizButton(View rootView) {
        TextView quizButton = getView().findViewById(R.id.quizGame);
        quizButton.setOnClickListener(v -> {
            if (billingManager != null && billingManager.isPremium()) {
                // Premium users go directly to quiz
                startQuizGame();
            } else {
                // Free users check access
                if (gameAccessManager.canPlayGame()) {
                    startQuizGame();
                    gameAccessManager.recordGamePlayed(); // Track play time
                } else {
                    showQuizLimitDialog();
                }
            }
        });
    }





    private void loadRewardedAd() {
        if (billingManager != null && billingManager.isPremium()) return;

        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(requireContext(), REWARDED_AD_UNIT_ID, adRequest,
                new RewardedAdLoadCallback() {
                    @Override public void onAdLoaded(@NonNull RewardedAd ad) { rewardedAd = ad; }
                    @Override public void onAdFailedToLoad(@NonNull LoadAdError e) { rewardedAd = null; }
                });
    }

    /** Quiz button: premium -> play; has plays -> play (no ad); 0 plays -> watch ad for 5. */
    private void handleQuizAccess() {
        // Premium = unlimited
        if (billingManager != null && billingManager.isPremium()) {
            startQuizGame();
            return;
        }

        // Used all 5 for this 24h window -> must wait
        if (gameAccessManager.isLimitReached()) {
            // AllFragment:    showQuizLimitDialog();
            // ResultActivity: showPlayLimitDialog();
            showQuizLimitDialog();   // <-- rename per screen (see note above)
            return;
        }

        // First play is free, or the ad already unlocked plays 2..5
        if (gameAccessManager.canPlayGame()) {
            startQuizGame();
        } else {
            // Used the free play, no ad yet -> offer the ad
            showWatchAdDialog();
        }
    }

//    private void showWatchAdDialog() {
//        new AlertDialog.Builder(requireContext())
//                .setTitle("Keep Playing")
//                .setMessage("Watch a short video to keep playing — up to "
//                        + GameAccessManager.MAX_PLAYS + " quizzes per day.")
//                .setPositiveButton("Watch Ad", (d, w) -> showRewardedAd())
//                .setNegativeButton("Cancel", null)
//                .show();
//    }

    private void showWatchAdDialog() {
        View dialogView = LayoutInflater.from(getContext())
                .inflate(R.layout.watch_ad_dialouge, null);

        CardView watchAdButton = dialogView.findViewById(R.id.watchAdButton);
        TextView cancelButton  = dialogView.findViewById(R.id.cancelButton);

        // Optional: customize the unlock text dynamically
        TextView unlockText = dialogView.findViewById(R.id.unlockText);
        unlockText.setText("Unlocks up to " + GameAccessManager.MAX_PLAYS + " quizzes per day");

        AlertDialog dialog = new AlertDialog.Builder(getContext())
                .setView(dialogView)
                .create();

        // Make dialog background transparent so our rounded corners show correctly
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }

        watchAdButton.setOnClickListener(v -> {
            dialog.dismiss();
            showRewardedAd();
        });

        cancelButton.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }

    private void showRewardedAd() {
        if (!isAdded() || getActivity() == null) return;

        if (rewardedAd == null) {
            Toast.makeText(requireContext(), "Ad not ready, please try again", Toast.LENGTH_SHORT).show();
            loadRewardedAd();
            return;
        }

        rewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {
            @Override public void onAdDismissedFullScreenContent() {
                rewardedAd = null;
                loadRewardedAd();
            }
            @Override public void onAdFailedToShowFullScreenContent(@NonNull AdError e) {
                rewardedAd = null;
                loadRewardedAd();
            }
        });

        rewardedAd.show(requireActivity(), new OnUserEarnedRewardListener() {
            @Override public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                gameAccessManager.grantPlaysForAd();   // unlock 5 plays
                startQuizGame();                        // play the first one now
            }
        });
    }

    private void startQuizGame() {
        gameAccessManager.recordGamePlayed();
        Intent intent = new Intent(getActivity(), QuizGame.class);
        startActivity(intent);
        // Optional: Add activity transition animation
    }



    @Override
    public void onDestroy() {
        if (billingManager != null) {
            billingManager.destroy();
            billingManager = null; // Prevents memory leaks
        }
        super.onDestroy();
    }


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

            if (card == null) {
                continue;
            }

            Object tag = card.getTag();

            if (tag == null) {
                continue;
            }

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




    private void showRewardedAdThenPlay() {
        if (!isAdded() || getActivity() == null) return;

        if (rewardedAd == null) {
            Toast.makeText(getContext(), "Ad not ready, please try again", Toast.LENGTH_SHORT).show();
            loadRewardedAd();
            return;
        }

        rewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {
            @Override
            public void onAdDismissedFullScreenContent() {
                rewardedAd = null;
                loadRewardedAd(); // preload the next one
            }

            @Override
            public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                rewardedAd = null;
                Log.d(TAG, "Rewarded ad failed to show: " + adError.getMessage());
                loadRewardedAd();
            }
        });

        rewardedAd.show(requireActivity(), new OnUserEarnedRewardListener() {
            @Override
            public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                // Reward earned -> grant one play
                gameAccessManager.recordGamePlayed();
                startQuizGame();
            }
        });
    }

    private void showQuizLimitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        View view = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_quiz_limit, null);
        builder.setView(view);

        AlertDialog dialog = builder.create();
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }
        dialog.show();

//        TextView messageText = view.findViewById(R.id.messageText);
        TextView playedText  = view.findViewById(R.id.playedText);
        TextView timerText   = view.findViewById(R.id.timerText);
        TextView okButton = view.findViewById(R.id.okButton);
        CardView upgradeButton = view.findViewById(R.id.upgradeButton);

        playedText.setText("You've played all " + GameAccessManager.MAX_PLAYS + " quizzes for today.");

// Card 2 — Blue: countdown timer
        timerText.setText("Try again in " + gameAccessManager.getRemainingTime());


//        messageText.setText("You've played all " + GameAccessManager.MAX_PLAYS
//              + " quizzes for today.\n\n" + gameAccessManager.getRemainingTime()
//              + "\n\nGo Premium for unlimited play.");

        okButton.setOnClickListener(v -> dialog.dismiss());

        upgradeButton.setOnClickListener(v -> {
            dialog.dismiss();
            if (billingManager != null) {
                billingManager.launchPurchaseFlow();
            }
        });
    }
}

