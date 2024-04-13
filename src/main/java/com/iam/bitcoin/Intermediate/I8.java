package com.iam.bitcoin.Intermediate;

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

public class I8 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i8);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Regulation and Compliance (Government Involvement in Crypto)");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources()
                .getColor(R.color.black)));

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.black));
        }


        RecyclerView i8Rec = findViewById(R.id.i8rec);
        i8Rec.setHasFixedSize(true);
        i8Rec.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        List<Model> models = new ArrayList<>();
        models.add(new Model(R.string.p4,R.string.p5));
        models.add(new Model(R.string.p6,R.string.p7));
        models.add(new Model(R.string.p8,R.string.p9));
        AdapterTwo adapterTwo = new AdapterTwo(models);
        i8Rec.setAdapter(adapterTwo);

        RecyclerView i8Reci = findViewById(R.id.i8reci);
        i8Reci.setHasFixedSize(true);
        i8Reci.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        List<Model> models1 = new ArrayList<>();
        models1.add(new Model(R.string.p11,R.string.p12));
        models1.add(new Model(R.string.p13,R.string.p14));
        models1.add(new Model(R.string.p15,R.string.p16));
        models1.add(new Model(R.string.p17,R.string.p18));
        models1.add(new Model(R.string.p19,R.string.p20));
        AdapterTwo adapterTwo1 = new AdapterTwo(models1);
        i8Reci.setAdapter(adapterTwo1);

        RecyclerView i8Recii = findViewById(R.id.i8recii);
        i8Recii.setHasFixedSize(true);
        i8Recii.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        List<Model> models2 = new ArrayList<>();
        models2.add(new Model(R.string.p22,R.string.p23));
        models2.add(new Model(R.string.p24,R.string.p25));
        models2.add(new Model(R.string.p26,R.string.p27));
        AdapterTwo adapterTwo2 = new AdapterTwo(models2);
        i8Recii.setAdapter(adapterTwo2);



        //Banner
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdView mAdView = findViewById(R.id.adViewi8);
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
        Animatoo.INSTANCE.animateSlideRight(I8.this);

    }
}