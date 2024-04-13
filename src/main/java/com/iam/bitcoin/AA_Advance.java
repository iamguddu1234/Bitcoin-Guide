package com.iam.bitcoin;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.iam.bitcoin.Intermediate.I10;
import com.iam.bitcoin.Intermediate.I11;
import com.iam.bitcoin.Intermediate.I12;
import com.iam.bitcoin.Intermediate.I9;
import com.iam.bitcoin.databinding.FragmentAAAdvanceBinding;


public class AA_Advance extends Fragment {

    private InterstitialAd interstitialAd;

    FragmentAAAdvanceBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentAAAdvanceBinding.inflate(inflater,container,false);
        loadInterstitialAd();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.ad1.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), I9.class);
            startActivity(intent);
            Animatoo.INSTANCE.animateSlideLeft(getContext());
        });
        binding.ad2.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), I10.class);
            startActivity(intent);
            Animatoo.INSTANCE.animateSlideLeft(getContext());
        });
        binding.ad3.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), I11.class);
            startActivity(intent);
            Animatoo.INSTANCE.animateSlideLeft(getContext());
        });
        binding.ad4.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), I12.class);
            startActivity(intent);
            Animatoo.INSTANCE.animateSlideLeft(getContext());
        });

        binding.ad5.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), A1.class);
            startActivity(intent);
            Animatoo.INSTANCE.animateSlideLeft(getContext());
        });

        binding.ad6.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), A2.class);
            startActivity(intent);
            Animatoo.INSTANCE.animateSlideLeft(getContext());
        });

        binding.ad7.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), A3.class);
            startActivity(intent);
            Animatoo.INSTANCE.animateSlideLeft(getContext());
        });

        binding.ad8.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), A4.class);
            startActivity(intent);
            Animatoo.INSTANCE.animateSlideLeft(getContext());
        });
        binding.ad9.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), A5.class);
            startActivity(intent);
            Animatoo.INSTANCE.animateSlideLeft(getContext());
        });

        binding.ad10.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), A6.class);
            startActivity(intent);
            Animatoo.INSTANCE.animateSlideLeft(getContext());
        });
        binding.ad11.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), A7.class);
            startActivity(intent);
            Animatoo.INSTANCE.animateSlideLeft(getContext());
        });
        binding.ad12.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), A8.class);
            startActivity(intent);
            Animatoo.INSTANCE.animateSlideLeft(getContext());
        });
        binding.ad13.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), A9.class);
            startActivity(intent);
            Animatoo.INSTANCE.animateSlideLeft(getContext());
        });
        binding.ad14.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), A10.class);
            startActivity(intent);
            Animatoo.INSTANCE.animateSlideLeft(getContext());
        });
        binding.ad15.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), A11.class);
            startActivity(intent);
            Animatoo.INSTANCE.animateSlideLeft(getContext());
        });

        binding.ad16.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), A12.class);
            startActivity(intent);
            Animatoo.INSTANCE.animateSlideLeft(getContext());
        });
        binding.ad17.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), A13.class);
            startActivity(intent);
            Animatoo.INSTANCE.animateSlideLeft(getContext());
        });
        binding.ad18.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), A14.class);
            startActivity(intent);
            Animatoo.INSTANCE.animateSlideLeft(getContext());
        });
        binding.ad19.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), A15.class);
            startActivity(intent);
            Animatoo.INSTANCE.animateSlideLeft(getContext());
        });
        binding.ad20.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), A16.class);
            startActivity(intent);
            Animatoo.INSTANCE.animateSlideLeft(getContext());
        });
        binding.ad21.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), A17.class);
            startActivity(intent);
            Animatoo.INSTANCE.animateSlideLeft(getContext());
        });
        binding.ad22.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), A18.class);
            startActivity(intent);
            Animatoo.INSTANCE.animateSlideLeft(getContext());
        });
        binding.ad23.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), A19.class);
            startActivity(intent);
            Animatoo.INSTANCE.animateSlideLeft(getContext());
        });
        binding.ad24.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), A20.class);
            startActivity(intent);
            Animatoo.INSTANCE.animateSlideLeft(getContext());
        });
        binding.ad25.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), A21.class);
            startActivity(intent);
            Animatoo.INSTANCE.animateSlideLeft(getContext());
        });















    }

    private void loadInterstitialAd() {
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(
                requireContext(),
                "ca-app-pub-5541243853026577/4063563669",
                adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd loadedInterstitialAd) {
                        // The interstitial ad is loaded and ready to be shown.
                        interstitialAd = loadedInterstitialAd;
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
//                        Toast.makeText(requireContext(), "Ad failed to load: " + loadAdError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void showInterstitialAd() {
        if (interstitialAd != null) {
            interstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdDismissedFullScreenContent() {
                    // Called when fullscreen content is dismissed
                    interstitialAd = null;
                }

                @Override
                public void onAdFailedToShowFullScreenContent(AdError adError) {
                    // Called when fullscreen content failed to show
                    interstitialAd = null;
                }

                @Override
                public void onAdShowedFullScreenContent() {
                    // Called when fullscreen content is shown
                }
            });

            interstitialAd.show(requireActivity());
        } else {
            // The ad is not ready to be shown yet, handle accordingly
//            Toast.makeText(requireContext(), "Ad is not ready yet", Toast.LENGTH_SHORT).show();
        }
    }
}