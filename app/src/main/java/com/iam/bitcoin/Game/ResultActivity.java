package com.iam.bitcoin.Game;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.BounceInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.iam.bitcoin.Home;
import com.iam.bitcoin.InAppPurchase.BillingManager;
import com.iam.bitcoin.InAppPurchase.PremiumStatusListener;
import com.iam.bitcoin.Multilanguage.LocaleHelper;
import com.iam.bitcoin.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ResultActivity extends AppCompatActivity implements PremiumStatusListener {

    // UI Components
    private ImageView achievementIcon;
    private TextView txtCongratulations, txtPerformanceMessage;
    private TextView txtFinalScore, txtPercentage, txtPreviousScore;
    private TextView txtCorrectAnswers, txtWrongAnswers, txtTimeTaken, txtImprovement;
    private View scoreCard, statsCard, comparisonCard, buttonContainer;
    private Button btnPlayAgain, btnHome;

    // Quiz Results Data
    private int finalScore;
    private int totalQuestions;
    private int previousBest;
    private String timeTaken;
    private int percentage;

    BillingManager billingManager;
    GameAccessManager gameAccessManager;

    private RewardedAd rewardedAd;
    // TODO: replace with your real rewarded ad unit id (this is Google's TEST id)
    private static final String REWARDED_AD_UNIT_ID = "ca-app-pub-3940256099942544/5224354917";

    private String selectedLanguageCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_result);
//
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//            getSupportActionBar().setElevation(0f);
//        }
//        setTitle("Result");



        setTitle("Result");
        setContentView(R.layout.activity_result);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0f);
        }


        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources()
                .getColor(R.color.white)));


        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.white));
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(ResultActivity.this, R.color.white)); //setting bar color
        }
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        billingManager = new BillingManager(this, new BillingManager.BillingListener() {
            @Override
            public void onPremiumStatusChanged(boolean isPremium) {
                runOnUiThread(() -> { /* update UI if needed */ });
            }

            @Override
            public void onBillingSetupFinished() { }
        });

        gameAccessManager = new GameAccessManager(this, billingManager);

        loadRewardedAd();

        getIntentData();
        initViews();
        setupScoreTracking();
        setupClickListeners();
        startResultAnimations();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        finalScore = intent.getIntExtra("score", 0);
        totalQuestions = intent.getIntExtra("total", 10);
        timeTaken = "0:30";
        percentage = (int) (((float) finalScore / totalQuestions) * 100);
    }

    private void initViews() {
        achievementIcon = findViewById(R.id.achievementIcon);
        txtCongratulations = findViewById(R.id.txtCongratulations);
        txtPerformanceMessage = findViewById(R.id.txtPerformanceMessage);
        txtFinalScore = findViewById(R.id.txtFinalScore);
        txtPercentage = findViewById(R.id.txtPercentage);
        txtPreviousScore = findViewById(R.id.txtPreviousScore);
        txtCorrectAnswers = findViewById(R.id.txtCorrectAnswers);
        txtWrongAnswers = findViewById(R.id.txtWrongAnswers);
        txtTimeTaken = findViewById(R.id.txtTimeTaken);
        txtImprovement = findViewById(R.id.txtImprovement);

        scoreCard = findViewById(R.id.scoreCard);
        statsCard = findViewById(R.id.statsCard);
        comparisonCard = findViewById(R.id.comparisonCard);
        buttonContainer = findViewById(R.id.buttonContainer);

        btnPlayAgain = findViewById(R.id.btnPlayAgain);

        btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(view -> {
            animateButtonClick(btnHome);
            view.postDelayed(this::finish, 200);
        });


    }

    private void setupScoreTracking() {
        SharedPreferences prefs = getSharedPreferences("QuizPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putLong("last_play_time", System.currentTimeMillis());

        previousBest = prefs.getInt("previous_best_score", 0);

        String newScore = finalScore + "/" + totalQuestions;
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(new Date());

        editor.putString("last_score", newScore);
        editor.putString("last_score_time", timestamp);

        if (finalScore > previousBest) {
            editor.putInt("previous_best_score", finalScore);
            previousBest = finalScore;
        }
        editor.apply();

        txtFinalScore.setText(newScore);
        txtPercentage.setText(percentage + "% correct");
        txtCorrectAnswers.setText(String.valueOf(finalScore));
        txtWrongAnswers.setText(String.valueOf(totalQuestions - finalScore));
        txtTimeTaken.setText(timeTaken);
        txtPreviousScore.setText("Previous best: " + previousBest + "/" + totalQuestions);

        setAchievementIcon();
        setPerformanceMessage();
        setImprovementMessage();
    }

    private void setAchievementIcon() {
        if (percentage >= 90) {
            achievementIcon.setImageResource(R.drawable.ic_trophy_gold);
        } else if (percentage >= 70) {
            achievementIcon.setImageResource(R.drawable.ic_trophy_silver);
        } else if (percentage >= 50) {
            achievementIcon.setImageResource(R.drawable.ic_trophy_bronze);
        } else {
            achievementIcon.setImageResource(R.drawable.ic_medal);
        }
    }

    private void setPerformanceMessage() {
        String message;
        if (percentage >= 90) {
            message = "Outstanding performance!";
        } else if (percentage >= 80) {
            message = "Excellent work!";
        } else if (percentage >= 70) {
            message = "Great job!";
        } else if (percentage >= 60) {
            message = "Good effort!";
        } else {
            message = "Keep practicing!";
        }
        txtPerformanceMessage.setText(message);
    }

    private void setImprovementMessage() {
        int improvement = finalScore - previousBest;
        if (improvement > 0) {
            txtImprovement.setText("Improved by " + improvement + " points!");
            txtImprovement.setTextColor(ContextCompat.getColor(this, R.color.correct_green));
        } else if (improvement < 0) {
            txtImprovement.setText(Math.abs(improvement) + " points lower");
            txtImprovement.setTextColor(ContextCompat.getColor(this, R.color.incorrect_red));
        } else {
            txtImprovement.setText("Same as your best");
            txtImprovement.setTextColor(ContextCompat.getColor(this, R.color.text_primary));
        }
    }

    // ===================== QUIZ ACCESS (Play Again) =====================

    private void setupClickListeners() {
        btnPlayAgain.setOnClickListener(v -> {
            animateButtonClick(btnPlayAgain);
            btnPlayAgain.postDelayed(this::handleQuizAccess, 200);
        });
    }

    /** premium -> play; 5 used -> limit dialog; free/unlocked -> play; else -> watch-ad dialog. */
    private void handleQuizAccess() {
        if (billingManager != null && billingManager.isPremium()) {
            startQuizGame();
            return;
        }

        if (gameAccessManager.isLimitReached()) {
            showPlayLimitDialog();
            return;
        }

        if (gameAccessManager.canPlayGame()) {
            startQuizGame();              // first play free, or ad already unlocked
        } else {
            showWatchAdDialog();          // used free play, no ad yet -> offer ad
        }
    }

    private void showWatchAdDialog() {
        View dialogView = LayoutInflater.from(this)
                .inflate(R.layout.watch_ad_dialouge, null);

        CardView watchAdButton = dialogView.findViewById(R.id.watchAdButton);
        TextView cancelButton  = dialogView.findViewById(R.id.cancelButton);

        TextView unlockText = dialogView.findViewById(R.id.unlockText);
        unlockText.setText("Unlocks up to " + GameAccessManager.MAX_PLAYS + " quizzes per day");

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(dialogView)
                .create();

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
        if (rewardedAd == null) {
            Toast.makeText(this, "Ad not ready, please try again", Toast.LENGTH_SHORT).show();
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

        rewardedAd.show(this, new OnUserEarnedRewardListener() {
            @Override public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                gameAccessManager.grantPlaysForAd();   // unlock plays 2..5 for this window
                startQuizGame();                        // play the next one now
            }
        });
    }

    private void loadRewardedAd() {
        if (billingManager != null && billingManager.isPremium()) return;
        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(this, REWARDED_AD_UNIT_ID, adRequest,
                new RewardedAdLoadCallback() {
                    @Override public void onAdLoaded(@NonNull RewardedAd ad) { rewardedAd = ad; }
                    @Override public void onAdFailedToLoad(@NonNull LoadAdError e) { rewardedAd = null; }
                });
    }

    /** Counts the play as soon as the quiz starts (survives back-out), then launches it. */
    private void startQuizGame() {
        gameAccessManager.recordGamePlayed();
        Intent intent = new Intent(this, QuizGame.class);
        startActivity(intent);
        finish();
    }

    /** Shown when all 5 plays for the 24h window are used. */
    private void showPlayLimitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_premium_upsell, null);
        builder.setView(view);

        AlertDialog dialog = builder.create();
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }
        dialog.show();

        TextView dialogMessage = view.findViewById(R.id.dialogMessage);
        dialogMessage.setText("You've played all " + GameAccessManager.MAX_PLAYS + " quizzes for today.");

        TextView timerText = view.findViewById(R.id.timerText);
        timerText.setText("Try again in " + gameAccessManager.getRemainingTime());

        CardView cancelButton = view.findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(v -> dialog.dismiss());

        CardView goPremiumButton = view.findViewById(R.id.goPremiumButton);
        goPremiumButton.setOnClickListener(v -> {
            Log.d("UPGRADE_DEBUG", "Go Premium clicked");
            dialog.dismiss();

            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                Log.d("UPGRADE_DEBUG", "Handler fired, billingManager = " + billingManager);
                if (billingManager != null) {
                    Log.d("UPGRADE_DEBUG", "Calling launchPurchaseFlow()");
                    billingManager.launchPurchaseFlow();
                } else {
                    Log.e("UPGRADE_DEBUG", "billingManager is NULL");
                }
            }, 500);
        });
    }

    // ===================== ANIMATIONS =====================

    private void startResultAnimations() {
        animateAchievementIcon();
        new Handler().postDelayed(this::animateTextElements, 400);
        new Handler().postDelayed(() -> animateCard(scoreCard, new OvershootInterpolator()), 200);
        new Handler().postDelayed(() -> animateCard(statsCard, null), 700);
        new Handler().postDelayed(() -> animateCard(comparisonCard, null), 1100);
        new Handler().postDelayed(this::animateButtons, 1500);
    }

    private void animateAchievementIcon() {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(achievementIcon, "scaleX", 0f, 1.2f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(achievementIcon, "scaleY", 0f, 1.2f, 1f);
        ObjectAnimator rotation = ObjectAnimator.ofFloat(achievementIcon, "rotation", 0f, 360f);
        AnimatorSet iconAnimation = new AnimatorSet();
        iconAnimation.playTogether(scaleX, scaleY, rotation);
        iconAnimation.setDuration(800);
        iconAnimation.setInterpolator(new BounceInterpolator());
        iconAnimation.start();
    }

    private void animateTextElements() {
        ObjectAnimator fadeInCongrats = ObjectAnimator.ofFloat(txtCongratulations, "alpha", 0f, 1f);
        ObjectAnimator slideInCongrats = ObjectAnimator.ofFloat(txtCongratulations, "translationY", -50f, 0f);
        AnimatorSet congratsAnimation = new AnimatorSet();
        congratsAnimation.playTogether(fadeInCongrats, slideInCongrats);
        congratsAnimation.setDuration(600);
        congratsAnimation.start();

        new Handler().postDelayed(() -> {
            ObjectAnimator fadeInMessage = ObjectAnimator.ofFloat(txtPerformanceMessage, "alpha", 0f, 1f);
            ObjectAnimator slideInMessage = ObjectAnimator.ofFloat(txtPerformanceMessage, "translationY", -30f, 0f);
            AnimatorSet messageAnimation = new AnimatorSet();
            messageAnimation.playTogether(fadeInMessage, slideInMessage);
            messageAnimation.setDuration(500);
            messageAnimation.start();
        }, 200);
    }

    private void animateCard(View card, android.animation.TimeInterpolator interpolator) {
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(card, "alpha", 0f, 1f);
        ObjectAnimator slideUp = ObjectAnimator.ofFloat(card, "translationY", card.getTranslationY(), 0f);
        AnimatorSet anim = new AnimatorSet();
        anim.playTogether(fadeIn, slideUp);
        anim.setDuration(500);
        if (interpolator != null) anim.setInterpolator(interpolator);
        anim.start();
    }

    private void animateButtons() {
        ObjectAnimator fadeInContainer = ObjectAnimator.ofFloat(buttonContainer, "alpha", 0f, 1f);
        ObjectAnimator slideUpContainer = ObjectAnimator.ofFloat(buttonContainer, "translationY", 50f, 0f);
        AnimatorSet containerAnimation = new AnimatorSet();
        containerAnimation.playTogether(fadeInContainer, slideUpContainer);
        containerAnimation.setDuration(500);
        containerAnimation.start();

        new Handler().postDelayed(() -> {
            ObjectAnimator fadeInHome = ObjectAnimator.ofFloat(btnHome, "alpha", 0f, 1f);
            ObjectAnimator slideUpHome = ObjectAnimator.ofFloat(btnHome, "translationY", 50f, 0f);
            AnimatorSet homeAnimation = new AnimatorSet();
            homeAnimation.playTogether(fadeInHome, slideUpHome);
            homeAnimation.setDuration(400);
            homeAnimation.start();
        }, 200);
    }

    private void animateButtonClick(View button) {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(button, "scaleX", 1f, 0.95f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(button, "scaleY", 1f, 0.95f, 1f);
        AnimatorSet clickAnimation = new AnimatorSet();
        clickAnimation.playTogether(scaleX, scaleY);
        clickAnimation.setDuration(150);
        clickAnimation.start();
    }




    @Override
    public boolean isPremium() {
        return billingManager != null && billingManager.isPremium();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (billingManager != null) {
            billingManager.destroy();
        }
    }
}