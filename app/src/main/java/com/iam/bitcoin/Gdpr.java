package com.iam.bitcoin;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.ads.MobileAds;
import com.google.android.ump.ConsentForm;
import com.google.android.ump.ConsentInformation;
import com.google.android.ump.ConsentRequestParameters;
import com.google.android.ump.FormError;
import com.google.android.ump.UserMessagingPlatform;

import java.util.concurrent.atomic.AtomicBoolean;

public class Gdpr {

    private ConsentInformation consentInformation;
    private final AtomicBoolean isMobileAdsInitializeCalled = new AtomicBoolean(false);

    private String TAG = "GDPR_DEBUG_CHECK";

    private Activity activity;

    public Gdpr(Activity activity) {
        this.activity = activity;
    }

    public void setGdpr() {
//        ConsentDebugSettings debugSettings = new ConsentDebugSettings.Builder(activity)
//                .setDebugGeography(ConsentDebugSettings.DebugGeography.DEBUG_GEOGRAPHY_EEA)
//                .addTestDeviceHashedId("5aa25954e40ffb18984989b59487dfe054549e213a2e64a12187f8deb5a4cb5")
//                .build();

        ConsentRequestParameters params = new ConsentRequestParameters
                .Builder()
                .setTagForUnderAgeOfConsent(false)
                .build();

        consentInformation = UserMessagingPlatform.getConsentInformation(activity);

        consentInformation.requestConsentInfoUpdate(activity, params,
                new ConsentInformation.OnConsentInfoUpdateSuccessListener() {
                    @Override
                    public void onConsentInfoUpdateSuccess() {
                        Log.d(TAG, "Consent info update success");
                        // Load and show the consent form if required
                        UserMessagingPlatform.loadAndShowConsentFormIfRequired(activity,
                                new ConsentForm.OnConsentFormDismissedListener() {
                                    @Override
                                    public void onConsentFormDismissed(@Nullable FormError formError) {

                                        if (formError != null) {
                                            Log.w(TAG, "Consent form dismissed with error: " +
                                                    formError.getErrorCode() + ", " +
                                                    formError.getMessage());
                                        } else {
                                            Log.d(TAG, "Consent form dismissed without error");
                                        }

                                        if (consentInformation.canRequestAds()) {
                                            initializeMobileAdsSdk();
                                        }
                                    }
                                });
                    }
                }, new ConsentInformation.OnConsentInfoUpdateFailureListener() {
                    @Override
                    public void onConsentInfoUpdateFailure(@NonNull FormError formError) {


                        Log.w(TAG, "Consent info update failed: " + formError);

                        // If the consent info update fails, proceed with initialization
                        if (consentInformation.canRequestAds()) {
                            initializeMobileAdsSdk();
                        }
                    }
                });

        // Initialize Mobile Ads SDK if consent is not required
        if (!consentInformation.isConsentFormAvailable()) {
            initializeMobileAdsSdk();
        }
    }

    private void initializeMobileAdsSdk() {
        if (isMobileAdsInitializeCalled.getAndSet(true)) {
            return;
        }


        MobileAds.initialize(activity);
        Log.d(TAG, "Mobile Ads SDK initialized");
    }


}
