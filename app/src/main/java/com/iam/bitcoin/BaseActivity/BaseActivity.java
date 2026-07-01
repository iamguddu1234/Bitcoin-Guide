package com.iam.bitcoin.BaseActivity;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.iam.bitcoin.Multilanguage.LocaleHelper;


public class BaseActivity extends AppCompatActivity {
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }
}
