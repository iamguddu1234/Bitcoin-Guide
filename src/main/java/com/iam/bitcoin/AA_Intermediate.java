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
import com.iam.bitcoin.Intermediate.I1;
import com.iam.bitcoin.Intermediate.I2;
import com.iam.bitcoin.Intermediate.I3;
import com.iam.bitcoin.Intermediate.I4;
import com.iam.bitcoin.Intermediate.I5;
import com.iam.bitcoin.Intermediate.I6;
import com.iam.bitcoin.Intermediate.I7;
import com.iam.bitcoin.Intermediate.I8;

import java.util.Base64;


public class AA_Intermediate extends Fragment {

    private InterstitialAd interstitialAd;


    com.iam.bitcoin.databinding.FragmentAAIntermediateBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = com.iam.bitcoin.databinding.FragmentAAIntermediateBinding.inflate(inflater,container,false);
loadInterstitialAd();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.i1.setOnClickListener(view1 -> {
            showInterstitialAd();

            Intent intent = new Intent(getContext(), B1.class);
            startActivity(intent);
            Animatoo.INSTANCE.animateSlideLeft(getContext());

        });

        binding.i2.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), B2.class);
            startActivity(intent);
            Animatoo.INSTANCE.animateSlideLeft(getContext());
        });

        binding.i3.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), B3.class);
            startActivity(intent);
            Animatoo.INSTANCE.animateSlideLeft(getContext());
        });

        binding.i4.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), B4.class);
            startActivity(intent);
            Animatoo.INSTANCE.animateSlideLeft(getContext());
        });

        binding.i5.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), B5.class);
            startActivity(intent);
            Animatoo.INSTANCE.animateSlideLeft(getContext());
        });

        binding.i6.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), B6.class);
            startActivity(intent);
            Animatoo.INSTANCE.animateSlideLeft(getContext());
        });

        binding.i7.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), B7.class);
            startActivity(intent);
            Animatoo.INSTANCE.animateSlideLeft(getContext());
        });

        binding.i8.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), B8.class);
            startActivity(intent);
            Animatoo.INSTANCE.animateSlideLeft(getContext());
        });

        binding.i9.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), B9.class);
            startActivity(intent);
            Animatoo.INSTANCE.animateSlideLeft(getContext());
        });

        binding.i10.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), B10.class);
            startActivity(intent);
            Animatoo.INSTANCE.animateSlideLeft(getContext());
        });

        binding.i11.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), B11.class);
            startActivity(intent);
            Animatoo.INSTANCE.animateSlideLeft(getContext());
        });

        binding.i12.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), B12.class);
            startActivity(intent);
            Animatoo.INSTANCE.animateSlideLeft(getContext());
        });

        binding.i13.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), I1.class);
            startActivity(intent);
            Animatoo.INSTANCE.animateSlideLeft(getContext());
        });

        binding.i14.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), I2.class);
            startActivity(intent);
            Animatoo.INSTANCE.animateSlideLeft(getContext());
        });

        binding.i15.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), I3.class);
            startActivity(intent);
            Animatoo.INSTANCE.animateSlideLeft(getContext());
        });
        binding.i16.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), I4.class);
            startActivity(intent);
            Animatoo.INSTANCE.animateSlideLeft(getContext());
        });

        binding.i17.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), I5.class);
            startActivity(intent);
            Animatoo.INSTANCE.animateSlideLeft(getContext());
        });

        binding.i18.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), I6.class);
            startActivity(intent);
            Animatoo.INSTANCE.animateSlideLeft(getContext());
        });
        binding.i19.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), I7.class);
            startActivity(intent);
            Animatoo.INSTANCE.animateSlideLeft(getContext());
        });
        binding.i20.setOnClickListener(view1 -> {
            showInterstitialAd();
            Intent intent = new Intent(getContext(), I8.class);
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