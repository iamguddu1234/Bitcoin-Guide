package com.iam.bitcoin.Basic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.iam.bitcoin.Adapters.AdapterTwo;
import com.iam.bitcoin.Model.Model;
import com.iam.bitcoin.R;

import java.util.ArrayList;
import java.util.List;

public class B5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b5);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Proof of Work (Mining and Transaction Verification)");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources()
                .getColor(R.color.black)));

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.black));
        }


        RecyclerView b5Rec = findViewById(R.id.b5rec);
        b5Rec.setHasFixedSize(true);
        b5Rec.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        List<Model> models = new ArrayList<>();
        models.add(new Model(R.string.e3, R.string.e4));
        models.add(new Model(R.string.e5, R.string.e6));
        models.add(new Model(R.string.e7, R.string.e8));
        models.add(new Model(R.string.e9, R.string.e10));
        models.add(new Model(R.string.e11, R.string.e12));
        models.add(new Model(R.string.e13, R.string.e14));
        AdapterTwo adapterTwo = new AdapterTwo(models);
        b5Rec.setAdapter(adapterTwo);


        //Banner
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdView mAdView = findViewById(R.id.adViewb5);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        Context context = this; // or use getContext() if inside a fragment

        // Call the animateSlideRight method directly on the class
        Animatoo.INSTANCE.animateSlideRight(context);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.INSTANCE.animateSlideRight(B5.this);

    }
}