package com.iam.bitcoin.Advance;

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

public class A3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a3);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Bubbles and Crashes (Understanding Market Volatility)");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources()
                .getColor(R.color.black)));

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.black));
        }



        RecyclerView a3Rec = findViewById(R.id.a3rec);
        a3Rec.setHasFixedSize(true);
        a3Rec.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        List<Model> models = new ArrayList<>();   models.add(new Model(R.string.w4,R.string.w5));
        models.add(new Model(R.string.v4,R.string.v5));
        models.add(new Model(R.string.v6,R.string.v7));
        models.add(new Model(R.string.v8,R.string.v9));
        models.add(new Model(R.string.v10,R.string.v11));
        AdapterTwo adapterTwo = new AdapterTwo(models);
        a3Rec.setAdapter(adapterTwo);


        RecyclerView a3Reci = findViewById(R.id.a3reci);
        a3Reci.setHasFixedSize(true);
        a3Reci.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        List<Model> models1 = new ArrayList<>();   models.add(new Model(R.string.w4,R.string.w5));
        models1.add(new Model(R.string.v13,R.string.v14));
        models1.add(new Model(R.string.v15,R.string.v16));
        models1.add(new Model(R.string.v17,R.string.v18));
        models1.add(new Model(R.string.v19,R.string.v20));
        AdapterTwo adapterTwo1 = new AdapterTwo(models1);
        a3Reci.setAdapter(adapterTwo1);


        RecyclerView a3Recii = findViewById(R.id.a3recii);
        a3Recii.setHasFixedSize(true);
        a3Recii.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        List<Model> models2 = new ArrayList<>();   models.add(new Model(R.string.w4,R.string.w5));
        models2.add(new Model(R.string.v22,R.string.v23));
        models2.add(new Model(R.string.v24,R.string.v25));
        models2.add(new Model(R.string.v26,R.string.v27));
        AdapterTwo adapterTwo2 = new AdapterTwo(models2);
        a3Recii.setAdapter(adapterTwo2);




        //Banner
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdView mAdView = findViewById(R.id.adViewa3);
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
        Animatoo.INSTANCE.animateSlideRight(A3.this);

    }
}