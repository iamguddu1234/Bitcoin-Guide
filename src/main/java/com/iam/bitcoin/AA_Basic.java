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
import com.iam.bitcoin.databinding.FragmentAABasicBinding;


public class AA_Basic extends Fragment {

    private InterstitialAd interstitialAd;

    FragmentAABasicBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      binding = FragmentAABasicBinding.inflate(inflater,container,false);

        loadInterstitialAd();

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.b1.setOnClickListener(view1 ->{
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