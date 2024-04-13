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

public class A5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a5);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Financial Inclusion (Providing Banking Services to the Unbanked)");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources()
                .getColor(R.color.black)));

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.black));
        }



        RecyclerView a5Rec = findViewById(R.id.a5rec);
        a5Rec.setHasFixedSize(true);
        a5Rec.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        List<Model> models = new ArrayList<>();
        models.add(new Model(R.string.wa6,R.string.wa7));
        models.add(new Model(R.string.wa8,R.string.wa9));
        models.add(new Model(R.string.wa10,R.string.wa11));
        models.add(new Model(R.string.wa12,R.string.wa13));
        models.add(new Model(R.string.wa14,R.string.wa15));
        AdapterTwo adapterTwo = new AdapterTwo(models);
        a5Rec.setAdapter(adapterTwo);



        RecyclerView a5Reci = findViewById(R.id.a5reci);
        a5Reci.setHasFixedSize(true);
        a5Reci.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        List<Model> models1 = new ArrayList<>();
        models1.add(new Model(R.string.wa17,R.string.wa18));
        models1.add(new Model(R.string.wa19,R.string.wa20));
        models1.add(new Model(R.string.wa21,R.string.wa22));
        models1.add(new Model(R.string.wa23,R.string.wa24));
        AdapterTwo adapterTwo1 = new AdapterTwo(models);
        a5Reci.setAdapter(adapterTwo1);




        RecyclerView a5Recii = findViewById(R.id.a5recii);
        a5Recii.setHasFixedSize(true);
        a5Recii.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        List<Model> models2 = new ArrayList<>();
        models2.add(new Model(R.string.wa26,R.string.wa27));
        models2.add(new Model(R.string.wa28,R.string.wa29));
        AdapterTwo adapterTwo2 = new AdapterTwo(models2);
        a5Recii.setAdapter(adapterTwo2);


        //Banner
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdView mAdView = findViewById(R.id.adViewa5);
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
        Animatoo.INSTANCE.animateSlideRight(A5.this);

    }
}