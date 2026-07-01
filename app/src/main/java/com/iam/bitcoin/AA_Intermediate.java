package com.iam.bitcoin;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.helper.widget.MotionEffect;
import androidx.fragment.app.Fragment;

import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.iam.bitcoin.Basic.B1;
import com.iam.bitcoin.Basic.B10;
import com.iam.bitcoin.Basic.B11;
import com.iam.bitcoin.Basic.B12;
import com.iam.bitcoin.Basic.B2;
import com.iam.bitcoin.Basic.B3;
import com.iam.bitcoin.Basic.B4;
import com.iam.bitcoin.Basic.B5;
import com.iam.bitcoin.Basic.B6;
import com.iam.bitcoin.Basic.B7;
import com.iam.bitcoin.Basic.B8;
import com.iam.bitcoin.Basic.B9;
import com.iam.bitcoin.InAppPurchase.BillingManager;
import com.iam.bitcoin.InAppPurchase.PremiumStatusListener;
import com.iam.bitcoin.Intermediate.I1;
import com.iam.bitcoin.Intermediate.I2;
import com.iam.bitcoin.Intermediate.I3;
import com.iam.bitcoin.Intermediate.I4;
import com.iam.bitcoin.Intermediate.I5;
import com.iam.bitcoin.Intermediate.I6;
import com.iam.bitcoin.Intermediate.I7;
import com.iam.bitcoin.Intermediate.I8;
import com.iam.bitcoin.Update.A1;
import com.iam.bitcoin.Update.A2;
import com.iam.bitcoin.Update.A3;
import com.iam.bitcoin.Update.A4;
import com.iam.bitcoin.Update.A5;

import java.util.Base64;


public class AA_Intermediate extends Fragment implements PremiumStatusListener{

    private InterstitialAd interstitialAd;


    com.iam.bitcoin.databinding.FragmentAAIntermediateBinding binding;

    BillingManager billingManager;
    private PremiumStatusListener premiumStatusListener;
    // Add these variables for time tracking
    private static final long AD_COOLDOWN_MS = 30000; // 30 seconds
    private long lastAdShownTime = 0;


    private BillingManager.BillingListener billingListener = new BillingManager.BillingListener() {
        @Override
        public void onPremiumStatusChanged(boolean isPremium) {

            if (isPremium) {
                updateAdVisibility();
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

        binding = com.iam.bitcoin.databinding.FragmentAAIntermediateBinding.inflate(inflater, container, false);
        loadInterstitialAd();
        billingManager = new BillingManager(getActivity(), billingListener);


        View view = binding.getRoot();

        View[] sections = {
                view.findViewById(R.id.btnSec1),
                view.findViewById(R.id.btnSec2),
                 view.findViewById(R.id.btnSec3),
                 view.findViewById(R.id.btnSec4),
                 view.findViewById(R.id.btnSec5),
        };

        ImageView[] arrows = {
                view.findViewById(R.id.arrow1),
                view.findViewById(R.id.arrow2),
                 view.findViewById(R.id.arrow3),
                 view.findViewById(R.id.arrow4),
                 view.findViewById(R.id.arrow5)
        };

        int[] buttonIds = {
                R.id.btnGoSection1,
                R.id.btnGoSection2,
                 R.id.btnGoSection3,
                 R.id.btnGoSection4,
                 R.id.btnGoSection5
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


        binding.una1.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), A1.class);
            startActivity(intent);
        });

        binding.una2.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), A2.class);
            startActivity(intent);
        });

        binding.una3.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), A3.class);
            startActivity(intent);
        });

        binding.una4.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), A4.class);
            startActivity(intent);
        });

        binding.una5.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), A5.class);
            startActivity(intent);
        });


        binding.i1.setOnClickListener(view1 -> {
            showInterstitialAd();

            Intent intent = new Intent(getContext(), B1.class);
            startActivity(intent);

        });

        binding.i2.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), B2.class);
            startActivity(intent);
        });

        binding.i3.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), B3.class);
            startActivity(intent);
        });

        binding.i4.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), B4.class);
            startActivity(intent);
        });

        binding.i5.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), B5.class);
            startActivity(intent);
        });

        binding.i6.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), B6.class);
            startActivity(intent);
        });

        binding.i7.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), B7.class);
            startActivity(intent);
        });

        binding.i8.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), B8.class);
            startActivity(intent);
        });

        binding.i9.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), B9.class);
            startActivity(intent);
        });

        binding.i10.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), B10.class);
            startActivity(intent);
        });

        binding.i11.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), B11.class);
            startActivity(intent);
        });

        binding.i12.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), B12.class);
            startActivity(intent);
        });

        binding.i13.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), I1.class);
            startActivity(intent);
        });

        binding.i14.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), I2.class);
            startActivity(intent);
        });

        binding.i15.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), I3.class);
            startActivity(intent);
        });
        binding.i16.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), I4.class);
            startActivity(intent);
        });

        binding.i17.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), I5.class);
            startActivity(intent);
        });

        binding.i18.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), I6.class);
            startActivity(intent);
        });
        binding.i19.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), I7.class);
            startActivity(intent);
        });
        binding.i20.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), I8.class);
            startActivity(intent);
        });


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

    private void updateAdVisibility() {
        if (isPremium()) {
            interstitialAd = null;
        } else {
            loadInterstitialAd();
        }
    }

    @Override
    public boolean isPremium() {
        return billingManager != null && billingManager.isPremium();
    }

    @Override
    public void onDestroyView() {
        if (billingManager != null) {
            billingManager.destroy();
        }
        if (interstitialAd != null) {
            interstitialAd = null;
        }
        super.onDestroyView();
    }



}