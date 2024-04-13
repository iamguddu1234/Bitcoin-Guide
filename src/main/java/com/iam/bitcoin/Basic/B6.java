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

public class B6 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b6);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Wallets and Private Keys (Securing Your Bitcoin)");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources()
                .getColor(R.color.black)));

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.black));
        }


        RecyclerView b6Rec = findViewById(R.id.b6rec);
        b6Rec.setHasFixedSize(true);
        b6Rec.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        List<Model> models = new ArrayList<>();
        models.add(new Model(R.string.f3,R.string.f4));
        models.add(new Model(R.string.f5,R.string.f6));
        models.add(new Model(R.string.f7,R.string.f8));
        models.add(new Model(R.string.f9,R.string.f10));
        models.add(new Model(R.string.f11,R.string.f12));
        models.add(new Model(R.string.f13,R.string.f14));
        models.add(new Model(R.string.f15,R.string.f16));
        AdapterTwo adapterTwo = new AdapterTwo(models);
        b6Rec.setAdapter(adapterTwo);

        RecyclerView b6Reci = findViewById(R.id.b6reci);
        b6Reci.setHasFixedSize(true);
        b6Reci.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        List<Model> models1 = new ArrayList<>();
        models1.add(new Model(R.string.f18,R.string.f19));
        models1.add(new Model(R.string.f20,R.string.f21));
        models1.add(new Model(R.string.f22,R.string.f23));
        models1.add(new Model(R.string.f24,R.string.f25));
        models1.add(new Model(R.string.f26,R.string.f27));
        models1.add(new Model(R.string.f28,R.string.f29));
        AdapterTwo adapterTwoa = new AdapterTwo(models);
        b6Reci.setAdapter(adapterTwoa);


        //Banner
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdView mAdView = findViewById(R.id.adViewb6);
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
        Animatoo.INSTANCE.animateSlideRight(B6.this);

    }
}