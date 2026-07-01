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
import androidx.constraintlayout.helper.widget.MotionEffect;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.iam.bitcoin.Game.GameAccessManager;
import com.iam.bitcoin.Game.QuizGame;
import com.iam.bitcoin.InAppPurchase.BillingManager;
import com.iam.bitcoin.InAppPurchase.PremiumStatusListener;
import com.iam.bitcoin.Multilanguage.LocaleHelper;
import com.iam.bitcoin.databinding.ActivityAboutBinding;
import com.iam.bitcoin.databinding.FragmentAABasicBinding;


public class AA_Basic extends Fragment implements PremiumStatusListener {

    private InterstitialAd interstitialAd;
    FragmentAABasicBinding binding;


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

    private BillingManager.BillingListener billingListener = new BillingManager.BillingListener() {
        @Override
        public void onPremiumStatusChanged(boolean isPremium) {


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
        // Inflate the layout for this fragment
        binding = FragmentAABasicBinding.inflate(inflater, container, false);

        loadInterstitialAd();

        initializeAdMob();

        billingManager = new BillingManager(getActivity(), billingListener);
        gameAccessManager = new GameAccessManager(requireContext(), billingManager);

        View view = binding.getRoot();

        View[] sections = {
                view.findViewById(R.id.advance),     // Part 1 content
                view.findViewById(R.id.section2)     // Part 2 content
                // view.findViewById(R.id.section3), // Part 3 content (add when XML exists)
                // view.findViewById(R.id.section4), // Part 4 content (add when XML exists)
                // view.findViewById(R.id.section5), // Part 5 content (add when XML exists)
        };

        ImageView[] arrows = {
                view.findViewById(R.id.arrow1),
                view.findViewById(R.id.arrow2)
                // view.findViewById(R.id.arrow3),
                // view.findViewById(R.id.arrow4),
                // view.findViewById(R.id.arrow5)
        };

        int[] buttonIds = {
                R.id.btnGoSection1,
                R.id.btnGoSection2
                // R.id.btnGoSection3,
                // R.id.btnGoSection4,
                // R.id.btnGoSection5
        };

        for (int i = 0; i < buttonIds.length; i++) {
            final int index = i;
            view.findViewById(buttonIds[i])
                    .setOnClickListener(v -> toggleDropdown(index, sections, arrows));
        }

        return binding.getRoot();
    }

    /**
     * Exclusive accordion toggle: opens the tapped section, closes all others.
     * Tapping an already-open section closes it.
     */
    private void toggleDropdown(int selectedIndex, View[] sections, ImageView[] arrows) {
        ViewGroup parent = (ViewGroup) sections[selectedIndex].getParent();
        TransitionManager.beginDelayedTransition(parent);

        boolean isCurrentlyVisible = sections[selectedIndex].getVisibility() == View.VISIBLE;

        for (int i = 0; i < sections.length; i++) {
            if (i == selectedIndex && !isCurrentlyVisible) {
                sections[i].setVisibility(View.VISIBLE);
                arrows[i].setRotation(180f);
            } else {
                sections[i].setVisibility(View.GONE);
                arrows[i].setRotation(0f);
            }
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.b1.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), One.class);
            startActivity(intent);
            Animatoo.INSTANCE.animateSlideLeft(getContext());
        });

        binding.b2.setOnClickListener(view1 -> {
            showInterstitialAd();

            Intent intent = new Intent(getContext(), Two.class);
            startActivity(intent);
            Animatoo.INSTANCE.animateSlideLeft(getContext());
        });
        binding.b3.setOnClickListener(view1 -> {
            showInterstitialAd();

            Intent intent = new Intent(getContext(), Whoissana.class);
            startActivity(intent);
            Animatoo.INSTANCE.animateSlideLeft(getContext());

        });
        binding.b4.setOnClickListener(view1 -> {
            showInterstitialAd();

            Intent intent = new Intent(getContext(), WhatCrypto.class);
            startActivity(intent);
            Animatoo.INSTANCE.animateSlideLeft(getContext());
        });
        binding.b5.setOnClickListener(view1 -> {
            showInterstitialAd();

            Intent intent = new Intent(getContext(), Whatblock.class);
            startActivity(intent);
            Animatoo.INSTANCE.animateSlideLeft(getContext());
        });
        binding.b6.setOnClickListener(view1 -> {
            showInterstitialAd();

            Intent intent = new Intent(getContext(), Three.class);
            startActivity(intent);
            Animatoo.INSTANCE.animateSlideLeft(getContext());
        });
        binding.b7.setOnClickListener(view1 -> {
            showInterstitialAd();

            Intent intent = new Intent(getContext(), sellBit.class);
            startActivity(intent);
            Animatoo.INSTANCE.animateSlideLeft(getContext());
        });
        binding.b8.setOnClickListener(view1 -> {
            showInterstitialAd();

            Intent intent = new Intent(getContext(), BitcoinAdvDisadv.class);
            startActivity(intent);
            Animatoo.INSTANCE.animateSlideLeft(getContext());
        });
        binding.b9.setOnClickListener(view1 -> {
            showInterstitialAd();

            Intent intent = new Intent(getContext(), ApplicationBitcoin.class);
            startActivity(intent);
            Animatoo.INSTANCE.animateSlideLeft(getContext());
        });
        binding.b10.setOnClickListener(view1 -> {
            showInterstitialAd();

            Intent intent = new Intent(getContext(), Four.class);
            startActivity(intent);
            Animatoo.INSTANCE.animateSlideLeft(getContext());
        });
        binding.b11.setOnClickListener(view1 -> {
            showInterstitialAd();

            Intent intent = new Intent(getContext(), Five.class);
            startActivity(intent);
            Animatoo.INSTANCE.animateSlideLeft(getContext());
        });

    }


    private void initializeAdMob() {
        if (!isAdded() || getContext() == null) {
            Log.w(TAG, "Fragment not attached, delaying AdMob initialization");
            // Optionally retry later using a handler if needed
            new Handler(Looper.getMainLooper()).postDelayed(this::initializeAdMob, 500);
            return;
        }

        MobileAds.initialize(requireContext(), status -> {
            Log.d(TAG, "AdMob initialized");
            if (isAdded() && !isPremium()) {  // Double-check fragment attachment
                loadInterstitialAd();
            }
        });
    }

    private void loadInterstitialAd() {

        // Check if fragment is attached first
        if (!isAdded() || getContext() == null) {
            Log.d(TAG, "Fragment not attached, skipping ad load");
            return;
        }

        // First check if user has premium (ad-free) version
        if (premiumStatusListener != null && premiumStatusListener.isPremium()) {
            interstitialAd = null; // Ensure no ad is held in memory
            return; // Skip ad loading for premium users
        }

        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(
                requireContext(),
//                "ca-app-pub-5541243853026577/4403565278",
                "ca-app-pub-5541243853026577/4063563669",
                adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd loadedInterstitialAd) {
                        // Double check premium status in case it changed during load
                        if (premiumStatusListener != null && premiumStatusListener.isPremium()) {
                            loadedInterstitialAd = null;
                            return;
                        }

                        interstitialAd = loadedInterstitialAd;
                        Log.d(TAG, "Interstitial ad loaded successfully");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        Log.d(TAG, "Ad failed to load: " + loadAdError.getMessage());
                        // Optionally retry loading after a delay
                    }
                });
    }

    private void showInterstitialAd() {

        // Check fragment attachment first
        if (!isAdded() || getActivity() == null) {
            Log.d(TAG, "Fragment not attached, skipping ad show");
            return;
        }


        // Don't show ads if premium
        if (premiumStatusListener != null && premiumStatusListener.isPremium()) {
            Log.d(TAG, "Skipping ad - user is premium");
            return;
        }

        // Check if enough time has passed since last ad
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastAdShownTime < AD_COOLDOWN_MS) {
            Log.d(TAG, "Ad skipped - within cooldown period");
            return;
        }

        if (interstitialAd != null) {
            interstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdDismissedFullScreenContent() {
                    interstitialAd = null;
                    lastAdShownTime = System.currentTimeMillis(); // Update last shown time
                    Log.d(TAG, "Ad dismissed");
                    // Load the next ad
                    loadInterstitialAd();
                }

                @Override
                public void onAdFailedToShowFullScreenContent(AdError adError) {
                    interstitialAd = null;
                    Log.d(TAG, "Ad failed to show: " + adError.getMessage());
                    loadInterstitialAd();
                }

                @Override
                public void onAdShowedFullScreenContent() {
                    lastAdShownTime = System.currentTimeMillis(); // Update last shown time
                    Log.d(TAG, "Ad showed successfully");
                }
            });

            interstitialAd.show(requireActivity());
        } else {
            Log.d(TAG, "Ad not ready, loading new one");
            loadInterstitialAd();
        }
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
            scrollToPremiumButton();
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
            Button removeAdsButton = view.findViewById(R.id.remove_ads_button);
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





    @Override
    public void onDestroy() {
        if (billingManager != null) {
            billingManager.destroy();
            billingManager = null; // Prevents memory leaks
        }
        super.onDestroy();
    }



}

