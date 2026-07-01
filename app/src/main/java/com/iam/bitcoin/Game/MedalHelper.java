package com.iam.bitcoin.Game;


import com.iam.bitcoin.R;


public class MedalHelper {

    // Medal types
    public static final int PERFECT = 0;
    public static final int GOLD = 1;
    public static final int SILVER = 2;
    public static final int BRONZE = 3;
    public static final int PARTICIPATION = 4;
    public static final int TRY_AGAIN = 5;

    public static int getMedalType(int score, int totalQuestions) {
        double percentage = (score * 100.0) / totalQuestions;

        if (percentage == 100) return PERFECT;
        if (percentage >= 90) return GOLD;
        if (percentage >= 80) return SILVER;
        if (percentage >= 70) return BRONZE;
        if (percentage >= 60) return PARTICIPATION;
        return TRY_AGAIN;
    }

//    public static int getMedalIcon(int score, int totalQuestions) {
//        switch (getMedalType(score, totalQuestions)) {
//            case PERFECT: return R.drawable.ic_medal_perfect;
//            case GOLD: return R.drawable.ic_medal_gold;
//            case SILVER: return R.drawable.ic_medal_silver;
//            case BRONZE: return R.drawable.ic_medal_bronze;
//            case PARTICIPATION: return R.drawable.ic_medal_participation;
//            default: return R.drawable.ic_medal_try_again;
//        }
//    }

    public static int getMedalIcon(int score, int totalQuestions) {
        switch (getMedalType(score, totalQuestions)) {
            case PERFECT: return R.drawable.gold_medal;
            case GOLD: return R.drawable.medal_gold;
            case SILVER: return R.drawable.medal_silver;
            case BRONZE: return R.drawable.medal_bronze;
            case PARTICIPATION: return R.drawable.medal_participation;
            default: return R.drawable.refresh;
        }
    }

    public static int getMedalColor(int score, int totalQuestions) {
        switch (getMedalType(score, totalQuestions)) {
            case PERFECT: return R.color.perfect_gold;
            case GOLD: return R.color.medal_gold;
            case SILVER: return R.color.medal_silver;
            case BRONZE: return R.color.medal_bronze;
            case PARTICIPATION: return R.color.medal_participation;
            default: return R.color.medal_try_again;
        }
    }

    public static String getMedalTitle(int score, int totalQuestions) {
        switch (getMedalType(score, totalQuestions)) {
            case PERFECT: return "PERFECT SCORE!";
            case GOLD: return "GOLD MEDAL!";
            case SILVER: return "SILVER MEDAL!";
            case BRONZE: return "BRONZE MEDAL!";
            case PARTICIPATION: return "PARTICIPATION";
            default: return "TRY AGAIN";
        }
    }

    public static String getMedalDescription(int score, int totalQuestions) {
        switch (getMedalType(score, totalQuestions)) {
            case PERFECT: return "Flawless victory! You answered every question correctly!";
            case GOLD: return "Outstanding performance! You're a quiz master!";
            case SILVER: return "Excellent work! You've got great knowledge!";
            case BRONZE: return "Solid performance! You're getting better!";
            case PARTICIPATION: return "Good effort! A little more practice will help!";
            default: return "Keep trying! Review the material and try again!";
        }
    }
}
