package com.iam.bitcoin.BaseActivity;

import android.app.Application;
import android.content.Context;

import com.iam.bitcoin.Multilanguage.LocaleHelper;


public class MyApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base));
    }
}

