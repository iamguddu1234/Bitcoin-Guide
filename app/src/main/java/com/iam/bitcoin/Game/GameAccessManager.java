package com.iam.bitcoin.Game;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import com.iam.bitcoin.InAppPurchase.BillingManager;

import java.util.concurrent.TimeUnit;

/**
 * Quiz access — 24h window with a 5-play cap (shared across Home & ResultActivity).
 *
 *   - Premium                 -> unlimited
 *   - 1st play of a window    -> FREE (no ad)
 *   - Plays 2..5              -> require ONE rewarded ad (the single ad unlocks the rest)
 *   - After 5 plays           -> locked until the 24h window ends
 *
 * The 24h clock starts on the first play of a window. After it expires the
 * window resets (plays = 0, ad-unlock cleared) and the next play is free again.
 */
public class GameAccessManager {

    private static final String PREFS_NAME      = "QuizGamePrefs";
    private static final String KEY_WINDOW_START = "window_start";
    private static final String KEY_PLAYS_USED   = "plays_used";
    private static final String KEY_AD_UNLOCK    = "ad_unlocked";
    private static final String KEY_TOTAL_ADS    = "total_ads_watched";
    private static final String KEY_TOTAL_PLAYS  = "total_games_played";

    /** Max plays allowed per 24h window (1 free + 4 after one ad). */
    public static final int MAX_PLAYS = 5;

    private static final long WINDOW_MS = TimeUnit.HOURS.toMillis(24);

    private final Context context;
    private final BillingManager billingManager;

    public GameAccessManager(Context context, BillingManager billingManager) {
        this.context = context.getApplicationContext();
        this.billingManager = billingManager;
    }

    private SharedPreferences prefs() {
        return context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
    }

    private boolean isPremium() {
        return billingManager != null && billingManager.isPremium();
    }

    /** Reset the window once 24h have passed since it started. */
    private void normalize() {
        SharedPreferences p = prefs();
        long start = p.getLong(KEY_WINDOW_START, 0L);
        if (start != 0L && System.currentTimeMillis() - start >= WINDOW_MS) {
            p.edit()
                    .putLong(KEY_WINDOW_START, 0L)
                    .putInt(KEY_PLAYS_USED, 0)
                    .putBoolean(KEY_AD_UNLOCK, false)
                    .apply();
        }
    }

    /** Plays already used in the current window (0..5). */
    public int getPlaysUsed() {
        normalize();
        return prefs().getInt(KEY_PLAYS_USED, 0);
    }

    /** Plays still available in the current window. */
    public int getRemainingPlays() {
        return Math.max(MAX_PLAYS - getPlaysUsed(), 0);
    }

    private boolean adUnlocked() {
        normalize();
        return prefs().getBoolean(KEY_AD_UNLOCK, false);
    }

    /** First play of the window is free. */
    public boolean isFirstPlayFree() {
        return getPlaysUsed() == 0;
    }

    /** All 5 plays used for this window. */
    public boolean isLimitReached() {
        return getPlaysUsed() >= MAX_PLAYS;
    }

    /** Premium, or first-free, or already unlocked by an ad (and under the cap). */
    public boolean canPlayGame() {
        if (isPremium()) return true;
        int used = getPlaysUsed();
        if (used >= MAX_PLAYS) return false;   // capped
        if (used == 0) return true;            // first play free
        return adUnlocked();                   // plays 2..5 need the ad
    }

    /** Free user who used the free play, hasn't watched the ad yet, and isn't capped. */
    public boolean needsAd() {
        if (isPremium()) return false;
        int used = getPlaysUsed();
        if (used == 0) return false;           // first free
        if (used >= MAX_PLAYS) return false;   // capped, ad won't help
        return !adUnlocked();
    }

    /** Call after a rewarded ad completes: unlocks plays 2..5 for this window. */
    public void grantPlaysForAd() {
        SharedPreferences p = prefs();
        p.edit()
                .putBoolean(KEY_AD_UNLOCK, true)
                .putInt(KEY_TOTAL_ADS, p.getInt(KEY_TOTAL_ADS, 0) + 1)
                .apply();
    }

    /** Call ONCE when a quiz STARTS. Counts the play and starts the 24h clock on the first one. */
    public void recordGamePlayed() {
        if (isPremium()) return;
        normalize();
        SharedPreferences p = prefs();
        long start = p.getLong(KEY_WINDOW_START, 0L);
        int used = p.getInt(KEY_PLAYS_USED, 0);

        SharedPreferences.Editor e = p.edit();
        if (start == 0L) {
            e.putLong(KEY_WINDOW_START, System.currentTimeMillis()); // window starts now
        }
        if (used < MAX_PLAYS) used++;
        e.putInt(KEY_PLAYS_USED, used)
                .putInt(KEY_TOTAL_PLAYS, p.getInt(KEY_TOTAL_PLAYS, 0) + 1)
                .apply();
    }

    /** e.g. "Try again in 5h 12m" (empty if no window is running). */
    public String getRemainingTime() {
        normalize();
        long start = prefs().getLong(KEY_WINDOW_START, 0L);
        if (start == 0L) return "";
        long left = WINDOW_MS - (System.currentTimeMillis() - start);
        if (left <= 0) return "";
        long hours = TimeUnit.MILLISECONDS.toHours(left);
        long mins  = TimeUnit.MILLISECONDS.toMinutes(left) % 60;
        return "Try again in " + hours + "h " + mins + "m";
    }

    public int getTotalAdsWatched()  { return prefs().getInt(KEY_TOTAL_ADS, 0); }
    public int getTotalGamesPlayed() { return prefs().getInt(KEY_TOTAL_PLAYS, 0); }
}