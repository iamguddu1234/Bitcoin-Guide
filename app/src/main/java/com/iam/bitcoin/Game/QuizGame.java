package com.iam.bitcoin.Game;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.iam.bitcoin.Home;
import com.iam.bitcoin.InAppPurchase.BillingManager;
import com.iam.bitcoin.InAppPurchase.PremiumStatusListener;
import com.iam.bitcoin.Multilanguage.LocaleHelper;
import com.iam.bitcoin.R;
import com.iam.bitcoin.aboutA;
import com.iam.bitcoin.databinding.ActivityQuizGameBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizGame extends AppCompatActivity implements PremiumStatusListener {

    private ActivityQuizGameBinding binding;
    private List<Quiz> questionList;

    private int currentQuestion = 0;
    private int selectedOption = -1;
    private boolean isAnswered = false;
    private int score = 0;
    private CountDownTimer countDownTimer;
    private static final long TIME_LIMIT = 30000; // 30 seconds
    private long timeLeftInMillis = TIME_LIMIT;

    private BillingManager billingManager;
    private String selectedLanguageCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuizGameBinding.inflate(getLayoutInflater());
        setTitle(getString(R.string.quiz_game));
        setContentView(binding.getRoot());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); if (getSupportActionBar() != null) {
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
            getWindow().setNavigationBarColor(ContextCompat.getColor(QuizGame.this, R.color.white)); //setting bar color
        }
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);



        // Initialize billing manager
        billingManager = new BillingManager(this, new BillingManager.BillingListener() {
            @Override
            public void onPremiumStatusChanged(boolean isPremium) {
                // Handle premium status changes if needed
            }

            @Override
            public void onBillingSetupFinished() {
                Log.d(TAG, "Billing setup complete");
            }
        });




        // Load questions from JSON
        questionList = loadQuestionsFromStrings();
//        if (questionList.isEmpty()) {
//            Toast.makeText(this, "Error loading questions", Toast.LENGTH_SHORT).show();
//            finish();
//            return;
//        }




        setupUI();
        loadQuestion();
        startTimer();
        animateQuestionEntry();
    }

    private List<Quiz> loadQuestionsFromStrings() {
        List<Quiz> questions = new ArrayList<>();
        String[] questionArray = getResources().getStringArray(R.array.quiz_questions);

        for (String entry : questionArray) {
            String[] parts = entry.split("\\|");
            if (parts.length == 6) {
                Quiz quiz = new Quiz();
                quiz.setQuestion(parts[0]);
                quiz.setOption1(parts[1]);
                quiz.setOption2(parts[2]);
                quiz.setOption3(parts[3]);
                quiz.setOption4(parts[4]);
                quiz.setAnswer(parts[5]);
                questions.add(quiz);
            }
        }

        Collections.shuffle(questions);
        // Return only first 10 questions, or all if fewer than 10
        return questions.subList(0, Math.min(10, questions.size()));
//        return questions;
    }

    private void setupUI() {
        binding.optionA.setOnClickListener(v -> selectOption(0));
        binding.optionB.setOnClickListener(v -> selectOption(1));
        binding.optionC.setOnClickListener(v -> selectOption(2));
        binding.optionD.setOnClickListener(v -> selectOption(3));

        binding.nextButton.setOnClickListener(v -> {
            if (currentQuestion < questionList.size() - 1) {
                currentQuestion++;
                animateQuestionExit();
            } else {
                finishQuiz();
            }
        });

        binding.nextButton.setEnabled(false);
        binding.nextButton.setAlpha(0.5f);
    }

    private void selectOption(int optionIndex) {
        if (isAnswered) return;

        selectedOption = optionIndex;
        isAnswered = true;
        stopTimer();

        // Highlight selected option
        highlightSelectedOption(optionIndex);

        // Check if answer is correct
        boolean isCorrect = isAnswerCorrect(optionIndex);
        if (isCorrect) {
            score++;
            updateScore();
        }

        new Handler().postDelayed(() -> {
            showAnswerFeedback(isCorrect);
            enableNextButton();
        }, 600);
    }

    private boolean isAnswerCorrect(int optionIndex) {
        String selectedAnswer = getOptionText(optionIndex);
        String correctAnswer = questionList.get(currentQuestion).getAnswer();
        return selectedAnswer.equals(correctAnswer);
    }

    private String getOptionText(int optionIndex) {
        switch (optionIndex) {
            case 0:
                return binding.optionAText.getText().toString();
            case 1:
                return binding.optionBText.getText().toString();
            case 2:
                return binding.optionCText.getText().toString();
            case 3:
                return binding.optionDText.getText().toString();
            default:
                return "";
        }
    }

    private void highlightSelectedOption(int optionIndex) {
        CardView selectedCard = getOptionCard(optionIndex);
        selectedCard.setCardBackgroundColor(ContextCompat.getColor(this, R.color.background_color));
    }

    private void showAnswerFeedback(boolean isCorrect) {
        String correctAnswer = questionList.get(currentQuestion).getAnswer();
        int correctIndex = getOptionIndex(correctAnswer);

        // Show correct answer
        CardView correctCard = getOptionCard(correctIndex);
        ImageView correctIcon = getOptionIcon(correctIndex);
        correctCard.setCardBackgroundColor(ContextCompat.getColor(this, R.color.correct_green));
        correctIcon.setImageResource(R.drawable.ic_check);
        correctIcon.setVisibility(View.VISIBLE);
        animateCorrectAnswer(correctCard);

        // If wrong, show wrong selection
        if (!isCorrect && selectedOption != -1) {
            CardView wrongCard = getOptionCard(selectedOption);
            ImageView wrongIcon = getOptionIcon(selectedOption);
            wrongCard.setCardBackgroundColor(ContextCompat.getColor(this, R.color.incorrect_red));
            wrongIcon.setImageResource(R.drawable.ic_close);
            wrongIcon.setVisibility(View.VISIBLE);
            animateWrongAnswer(wrongCard);
        }

        // Fade other options
        for (int i = 0; i < 4; i++) {
            if (i != correctIndex && i != selectedOption) {
                ObjectAnimator fadeOut = ObjectAnimator.ofFloat(getOptionCard(i), "alpha", 1f, 0.6f);
                fadeOut.setDuration(300);
                fadeOut.start();
            }
        }
    }

    private int getOptionIndex(String answer) {
        if (binding.optionAText.getText().toString().equals(answer)) return 0;
        if (binding.optionBText.getText().toString().equals(answer)) return 1;
        if (binding.optionCText.getText().toString().equals(answer)) return 2;
        if (binding.optionDText.getText().toString().equals(answer)) return 3;
        return -1;
    }

    private CardView getOptionCard(int index) {
        switch (index) {
            case 0:
                return binding.optionA;
            case 1:
                return binding.optionB;
            case 2:
                return binding.optionC;
            case 3:
                return binding.optionD;
            default:
                return binding.optionA;
        }
    }

    private ImageView getOptionIcon(int index) {
        switch (index) {
            case 0:
                return binding.optionAIcon;
            case 1:
                return binding.optionBIcon;
            case 2:
                return binding.optionCIcon;
            case 3:
                return binding.optionDIcon;
            default:
                return binding.optionAIcon;
        }
    }

    private void animateCorrectAnswer(View view) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(view, "translationY", 0f, -20f, 0f),
                ObjectAnimator.ofFloat(view, "scaleX", 1f, 1.1f, 1f),
                ObjectAnimator.ofFloat(view, "scaleY", 1f, 1.1f, 1f)
        );
        animatorSet.setDuration(600);
        animatorSet.setInterpolator(new BounceInterpolator());
        animatorSet.start();
    }

    private void animateWrongAnswer(View view) {
        ObjectAnimator shake = ObjectAnimator.ofFloat(view, "translationX",
                0f, -15f, 15f, -10f, 10f, -5f, 5f, 0f);
        shake.setDuration(500);
        shake.start();
    }

    private void loadQuestion() {
        Quiz current = questionList.get(currentQuestion);

        binding.questionText.setText(current.getQuestion());
        binding.questionBadge.setText("Q" + (currentQuestion + 1));
        binding.questionCounter.setText((currentQuestion + 1) + "/" + questionList.size());

        binding.optionAText.setText(current.getOption1());
        binding.optionBText.setText(current.getOption2());
        binding.optionCText.setText(current.getOption3());
        binding.optionDText.setText(current.getOption4());

        updateProgress();
    }

    private void updateProgress() {
        int progress = (int) (((float) (currentQuestion + 1) / questionList.size()) * 100);
        binding.progressPercentage.setText(progress + "%");

        ValueAnimator animator = ValueAnimator.ofInt(binding.progressBar.getProgress(), progress);
        animator.setDuration(600);
        animator.addUpdateListener(animation ->
                binding.progressBar.setProgress((Integer) animation.getAnimatedValue()));
        animator.start();
    }

    private void updateScore() {
        binding.scoreText.setText("Score: " + score);

        AnimatorSet scoreAnimation = new AnimatorSet();
        scoreAnimation.playTogether(
                ObjectAnimator.ofFloat(binding.scoreText, "scaleX", 1f, 1.2f, 1f),
                ObjectAnimator.ofFloat(binding.scoreText, "scaleY", 1f, 1.2f, 1f)
        );
        scoreAnimation.setDuration(300);
        scoreAnimation.start();
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateTimerText();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                updateTimerText();
                if (!isAnswered) {
                    timeUp();
                }
            }
        }.start();
    }

    private void updateTimerText() {
        int seconds = (int) (timeLeftInMillis / 1000);
        binding.timerText.setText(seconds + "s");

        if (seconds <= 10) {
            binding.timerText.setTextColor(ContextCompat.getColor(this, R.color.incorrect_red));
            ObjectAnimator pulse = ObjectAnimator.ofFloat(binding.timerText, "alpha", 1f, 0.5f, 1f);
            pulse.setDuration(500);
            pulse.start();
        } else {
            binding.timerText.setTextColor(ContextCompat.getColor(this, android.R.color.black));
        }
    }

    private void timeUp() {
        isAnswered = true;
        showAnswerFeedback(false);
        enableNextButton();
    }

    private void stopTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    private void enableNextButton() {
        binding.nextButton.setEnabled(true);
        binding.nextButton.setAlpha(1f);

        AnimatorSet buttonAnim = new AnimatorSet();
        buttonAnim.playTogether(
                ObjectAnimator.ofFloat(binding.nextButton, "scaleX", 1f, 1.05f, 1f),
                ObjectAnimator.ofFloat(binding.nextButton, "scaleY", 1f, 1.05f, 1f)
        );
        buttonAnim.setDuration(300);
        buttonAnim.start();
    }

    private void animateQuestionEntry() {
        binding.questionCard.setTranslationX(1000f);
        binding.questionCard.setAlpha(0f);

        AnimatorSet questionAnim = new AnimatorSet();
        questionAnim.playTogether(
                ObjectAnimator.ofFloat(binding.questionCard, "translationX", 1000f, 0f),
                ObjectAnimator.ofFloat(binding.questionCard, "alpha", 0f, 1f)
        );
        questionAnim.setDuration(500);
        questionAnim.start();

        // Animate options with stagger
        CardView[] optionCards = {binding.optionA, binding.optionB, binding.optionC, binding.optionD};
        for (int i = 0; i < optionCards.length; i++) {
            optionCards[i].setAlpha(0f);
            optionCards[i].setTranslationY(100f);
            optionCards[i].setScaleX(0.8f);
            optionCards[i].setScaleY(0.8f);

            AnimatorSet optionAnim = new AnimatorSet();
            optionAnim.playTogether(
                    ObjectAnimator.ofFloat(optionCards[i], "alpha", 0f, 1f),
                    ObjectAnimator.ofFloat(optionCards[i], "translationY", 100f, 0f),
                    ObjectAnimator.ofFloat(optionCards[i], "scaleX", 0.8f, 1f),
                    ObjectAnimator.ofFloat(optionCards[i], "scaleY", 0.8f, 1f)
            );
            optionAnim.setStartDelay(200 + (i * 150));
            optionAnim.setDuration(400);
            optionAnim.start();
        }
    }

    private void animateQuestionExit() {
        AnimatorSet exitAnim = new AnimatorSet();
        exitAnim.playTogether(
                ObjectAnimator.ofFloat(binding.questionCard, "translationX", 0f, -1000f),
                ObjectAnimator.ofFloat(binding.questionCard, "alpha", 1f, 0f)
        );
        exitAnim.setDuration(300);
        exitAnim.start();

        // Fade out options
        CardView[] optionCards = {binding.optionA, binding.optionB, binding.optionC, binding.optionD};
        for (int i = 0; i < optionCards.length; i++) {
            AnimatorSet optionExit = new AnimatorSet();
            optionExit.playTogether(
                    ObjectAnimator.ofFloat(optionCards[i], "alpha", 1f, 0f),
                    ObjectAnimator.ofFloat(optionCards[i], "translationY", 0f, 50f)
            );
            optionExit.setStartDelay(i * 50);
            optionExit.setDuration(200);
            optionExit.start();
        }

        // Load next question after animation
        new Handler().postDelayed(() -> {
            resetForNextQuestion();
            loadQuestion();
            startTimer();
            animateQuestionEntry();
        }, 400);
    }

    private void resetForNextQuestion() {
        selectedOption = -1;
        isAnswered = false;
        timeLeftInMillis = TIME_LIMIT;

        // Reset all options
        CardView[] optionCards = {binding.optionA, binding.optionB, binding.optionC, binding.optionD};
        ImageView[] optionIcons = {binding.optionAIcon, binding.optionBIcon, binding.optionCIcon, binding.optionDIcon};

        for (int i = 0; i < optionCards.length; i++) {
            optionCards[i].setCardBackgroundColor(ContextCompat.getColor(this, android.R.color.white));
            optionCards[i].setAlpha(1f);
            optionCards[i].setTranslationY(0f);
            optionCards[i].setTranslationX(0f);
            optionCards[i].setScaleX(1f);
            optionCards[i].setScaleY(1f);
            optionIcons[i].setVisibility(View.GONE);
        }

        binding.nextButton.setEnabled(false);
        binding.nextButton.setAlpha(0.5f);
    }

    private void finishQuiz() {
        stopTimer();

        // Save play time
        SharedPreferences prefs = getSharedPreferences("QuizPrefs", MODE_PRIVATE);
        prefs.edit().putLong("last_play_time", System.currentTimeMillis()).apply();

        // Show results
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("score", score);
        intent.putExtra("total", questionList.size());
        startActivity(intent);
        finish();
    }

    private void showLanguageDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_language_selector, null);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();

        // Set click listeners for language buttons
        view.findViewById(R.id.lang_english).setOnClickListener(v -> handleLanguageSelection("en", dialog));
        view.findViewById(R.id.lang_hindi).setOnClickListener(v -> handleLanguageSelection("hi", dialog));
//        view.findViewById(R.id.lang_bengali).setOnClickListener(v -> handleLanguageSelection("bn", dialog));
//        view.findViewById(R.id.lang_russian).setOnClickListener(v -> handleLanguageSelection("ru", dialog));
//        view.findViewById(R.id.lang_chinese).setOnClickListener(v -> handleLanguageSelection("zh-rCN", dialog));
    }

    private void handleLanguageSelection(String langCode, AlertDialog dialog) {
        dialog.dismiss();

        // Special case for English (always allowed)
        if ("en".equals(langCode)) {
            changeLanguage(langCode);
            return;
        }

        selectedLanguageCode = langCode;

        if (isPremium()) {
            changeLanguage(langCode);
        } else {
            showPremiumRequiredDialog();
        }
    }

    private void showPremiumRequiredDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.premium_required_dialog, null);
        builder.setView(dialogView);

        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();

        Button cancelButton = dialogView.findViewById(R.id.cancelButton);
        Button upgradeButton = dialogView.findViewById(R.id.upgradeButton);

        cancelButton.setOnClickListener(v -> dialog.dismiss());

        upgradeButton.setOnClickListener(v -> {
            dialog.dismiss();
            // Navigate back to MainActivity and trigger scroll to remove_ads button
            Intent intent = new Intent(QuizGame.this, Home.class);
            intent.putExtra("SCROLL_TO_PREMIUM", true);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        });
    }

    private void changeLanguage(String langCode) {
        LocaleHelper.setLocale(this, langCode);

        // Restart activity to apply language changes
        Intent intent = getIntent();
        finish();
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public boolean isPremium() {
        return billingManager != null && billingManager.isPremium();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopTimer();
        if (billingManager != null) {
            billingManager.destroy();
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        Context context = this; // or use getContext() if inside a fragment

        return super.onOptionsItemSelected(item);
    }
}