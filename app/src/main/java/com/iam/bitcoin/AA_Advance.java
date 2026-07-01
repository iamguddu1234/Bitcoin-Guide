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

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.iam.bitcoin.Advance.A1;
import com.iam.bitcoin.Advance.A10;
import com.iam.bitcoin.Advance.A11;
import com.iam.bitcoin.Advance.A12;
import com.iam.bitcoin.Advance.A13;
import com.iam.bitcoin.Advance.A14;
import com.iam.bitcoin.Advance.A15;
import com.iam.bitcoin.Advance.A16;
import com.iam.bitcoin.Advance.A17;
import com.iam.bitcoin.Advance.A18;
import com.iam.bitcoin.Advance.A19;
import com.iam.bitcoin.Advance.A2;
import com.iam.bitcoin.Advance.A20;
import com.iam.bitcoin.Advance.A21;
import com.iam.bitcoin.Advance.A3;
import com.iam.bitcoin.Advance.A4;
import com.iam.bitcoin.Advance.A5;
import com.iam.bitcoin.Advance.A6;
import com.iam.bitcoin.Advance.A7;
import com.iam.bitcoin.Advance.A8;
import com.iam.bitcoin.Advance.A9;
import com.iam.bitcoin.InAppPurchase.BillingManager;
import com.iam.bitcoin.InAppPurchase.PremiumStatusListener;
import com.iam.bitcoin.Intermediate.I10;
import com.iam.bitcoin.Intermediate.I11;
import com.iam.bitcoin.Intermediate.I12;
import com.iam.bitcoin.Intermediate.I9;
import com.iam.bitcoin.databinding.FragmentAAAdvanceBinding;


public class AA_Advance extends Fragment implements PremiumStatusListener {

    FragmentAAAdvanceBinding binding;

    private InterstitialAd interstitialAd;

    BillingManager billingManager;
    private PremiumStatusListener premiumStatusListener;
    // Add these variables for time tracking
    private static final long AD_COOLDOWN_MS = 30000; // 30 seconds
    private long lastAdShownTime = 0;



    private BillingManager.BillingListener billingListener = new BillingManager.BillingListener() {
        @Override
        public void onPremiumStatusChanged(boolean isPremium) {
            updateAdVisibility();
//            if (isPremium) {
//                Toast.makeText(getContext(), "Thank you for purchasing ad removal!", Toast.LENGTH_SHORT).show();
//            }

            if (isPremium && isAdded() && getContext() != null) {  // Add context checks
//                Toast.makeText(getContext(), "Thank you for purchasing ad removal!", Toast.LENGTH_SHORT).show();
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

        binding = FragmentAAAdvanceBinding.inflate(inflater, container, false);
        loadInterstitialAd();
        billingManager = new BillingManager(getActivity(), billingListener);

        View view = binding.getRoot();

        View[] sections = {
                view.findViewById(R.id.btnSec1),
                view.findViewById(R.id.btnSec2),
                view.findViewById(R.id.btnSec3),
                view.findViewById(R.id.btnSec4),
                view.findViewById(R.id.btnSec5),
                view.findViewById(R.id.btnSec6),
        };

        ImageView[] arrows = {
                view.findViewById(R.id.arrow1),
                view.findViewById(R.id.arrow2),
                view.findViewById(R.id.arrow3),
                view.findViewById(R.id.arrow4),
                view.findViewById(R.id.arrow5),
                view.findViewById(R.id.arrow6),
        };

        int[] buttonIds = {
                R.id.btnGoSection1,
                R.id.btnGoSection2,
                R.id.btnGoSection3,
                R.id.btnGoSection4,
                R.id.btnGoSection5,
                R.id.btnGoSection6,
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


        binding.unb1.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), com.iam.bitcoin.Update.A6.class);
            startActivity(intent);
        });
        binding.unb2.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), com.iam.bitcoin.Update.A7.class);
            startActivity(intent);
        });

        binding.unb3.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), com.iam.bitcoin.Update.A8.class);
            startActivity(intent);
        });

        binding.unb4.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), com.iam.bitcoin.Update.A9.class);
            startActivity(intent);
        });

        binding.unb5.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), com.iam.bitcoin.Update.A10.class);
            startActivity(intent);
        });


        binding.ad1.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), I9.class);
            startActivity(intent);
        });
        binding.ad2.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), I10.class);
            startActivity(intent);
        });
        binding.ad3.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), I11.class);
            startActivity(intent);
        });
        binding.ad4.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), I12.class);
            startActivity(intent);
        });

        binding.ad5.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), A1.class);
            startActivity(intent);
        });

        binding.ad6.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), A2.class);
            startActivity(intent);
        });

        binding.ad7.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), A3.class);
            startActivity(intent);
        });

        binding.ad8.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), A4.class);
            startActivity(intent);
        });
        binding.ad9.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), A5.class);
            startActivity(intent);
        });

        binding.ad10.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), A6.class);
            startActivity(intent);
        });
        binding.ad11.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), A7.class);
            startActivity(intent);
        });
        binding.ad12.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), A8.class);
            startActivity(intent);
        });
        binding.ad13.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), A9.class);
            startActivity(intent);
        });
        binding.ad14.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), A10.class);
            startActivity(intent);
        });
        binding.ad15.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), A11.class);
            startActivity(intent);
        });

        binding.ad16.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), A12.class);
            startActivity(intent);
        });
        binding.ad17.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), A13.class);
            startActivity(intent);
        });
        binding.ad18.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), A14.class);
            startActivity(intent);
        });
        binding.ad19.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), A15.class);
            startActivity(intent);
        });
        binding.ad20.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), A16.class);
            startActivity(intent);
        });
        binding.ad21.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), A17.class);
            startActivity(intent);
        });
        binding.ad22.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), A18.class);
            startActivity(intent);
        });
        binding.ad23.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), A19.class);
            startActivity(intent);
        });
        binding.ad24.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), A20.class);
            startActivity(intent);
        });
        binding.ad25.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), A21.class);
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