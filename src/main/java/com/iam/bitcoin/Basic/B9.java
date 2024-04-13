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

public class B9 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b9);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Security and Risks (Understanding Vulnerabilities)");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources()
                .getColor(R.color.black)));

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.black));
        }

        RecyclerView b9Rec = findViewById(R.id.b9rec);
        b9Rec.setHasFixedSize(true);
        b9Rec.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        List<Model> models = new ArrayList<>();
        models.add(new Model(R.string.ha4,R.string.ha5));
        models.add(new Model(R.string.ha6,R.string.ha7));
        models.add(new Model(R.string.ha8,R.string.ha9));
        models.add(new Model(R.string.ha10,R.string.ha11));
        models.add(new Model(R.string.ha12,R.string.ha13));
        models.add(new Model(R.string.ha14,R.string.ha15));
        models.add(new Model(R.string.ha16,R.string.ha17));
        models.add(new Model(R.string.ha18,R.string.ha19));

        AdapterTwo adapterTwo = new AdapterTwo(models);
        b9Rec.setAdapter(adapterTwo);


//        RecyclerView b9Reci = findViewById(R.id.b9reci);
//        b9Reci.setHasFixedSize(true);
//        b9Reci.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
//        List<Model> models1 = new ArrayList<>();
//        models1.add(new Model(R.string.ha21,R.string.ha22));
//        models1.add(new Model(R.string.ha23,R.string.ha24));
//        models1.add(new Model(R.string.ha25,R.string.ha26));
//        models1.add(new Model(R.string.ha27,R.string.ha28));
//        models1.add(new Model(R.string.ha29,R.string.ha30));
//        models1.add(new Model(R.string.ha31,R.string.ha32));
//        models1.add(new Model(R.string.ha33,R.string.ha34));
//
//        AdapterTwo adapterTwo1 = new AdapterTwo(models1);
//        b9Rec.setAdapter(adapterTwo1);






        //Banner
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdView mAdView = findViewById(R.id.adViewb9);
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
        Animatoo.INSTANCE.animateSlideRight(B9.this);

    }
}