package com.iam.bitcoin.PrimiumDialogue;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsetsController;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.iam.bitcoin.Home;
import com.iam.bitcoin.InAppPurchase.BillingManager;
import com.iam.bitcoin.R;

public class PremiumDialogFragment extends DialogFragment {

    private Handler autoDismissHandler;
    private Runnable autoDismissRunnable;

//    public PremiumDialogFragment() {
//        super(R.style.FullScreenDialogStyle);
//    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_premium_dialog, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        Window window = getDialog().getWindow();

        if (window != null) {

            // Make dialog full screen
            window.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
            );

            // White status bar background
            window.setStatusBarColor(
                    ContextCompat.getColor(requireContext(), R.color.white)
            );

            // Dark icons (WiFi, battery, time)
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
                window.getInsetsController().setSystemBarsAppearance(
                        WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                        WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                );
            } else {
                window.getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                );
            }

        }

        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
            );

            // Set status bar color
//            getDialog().getWindow().setStatusBarColor(
//                    getResources().getColor(R.color.black)
//            );
        }

        View view = getView();
        if (view != null) {
            CardView upgradeButton = view.findViewById(R.id.btn_upgrade_premium);
            CardView skipButton = view.findViewById(R.id.btn_skip);

            TextView tvPrice = view.findViewById(R.id.tvOldPrice);

            tvPrice.setPaintFlags(
                    tvPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG
            );



            if (upgradeButton != null) {
                upgradeButton.setOnClickListener(v -> {
                    // Cancel auto-dismiss if user interacts
                    cancelAutoDismiss();
//                    Toast.makeText(getContext(), "Redirecting to payment...", Toast.LENGTH_SHORT).show();
//                    // TODO: Start payment flow
//
//
//
//                    if (getActivity() instanceof Home) {
//                        ((Home) getActivity()).openBasicTabAndScrollToPremium();
//                    }

                    dismiss();

                    // Direct payment flow instead of redirect
                    if (getActivity() instanceof Home) {
                        BillingManager billingManager = ((Home) getActivity()).getBillingManager();
                        if (billingManager != null) {
                            billingManager.launchPurchaseFlow();
                        }
                    }

                });
            }

            if (skipButton != null) {
                skipButton.setOnClickListener(v -> {
                    cancelAutoDismiss();
                    dismiss();
                });
            }

            // Start auto-dismiss timer
            startAutoDismissTimer();
        }
    }

    private void startAutoDismissTimer() {
        autoDismissHandler = new Handler(Looper.getMainLooper());
        autoDismissRunnable = new Runnable() {
            @Override
            public void run() {
                if (isAdded() && !isRemoving()) {
                    dismiss();
                }
            }
        };
        autoDismissHandler.postDelayed(autoDismissRunnable, 3000); // 3 seconds
    }

    private void cancelAutoDismiss() {
        if (autoDismissHandler != null && autoDismissRunnable != null) {
            autoDismissHandler.removeCallbacks(autoDismissRunnable);
        }
    }

    @Override
    public void onDestroyView() {
        cancelAutoDismiss();
        super.onDestroyView();
    }

}