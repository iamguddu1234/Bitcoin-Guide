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

public class I6 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i6);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Forks and Soft Forks (Upgrades and Changes to the Bitcoin Protocol)");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources()
                .getColor(R.color.black)));

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.black));
        }


        RecyclerView i6Rec = findViewById(R.id.i6rec);
        i6Rec.setHasFixedSize(true);
        i6Rec.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        List<Model> models = new ArrayList<>();
        models.add(new Model(R.string.o4,R.string.o5));
        models.add(new Model(R.string.o6,R.string.o7));
        models.add(new Model(R.string.o8,R.string.o9));
        AdapterTwo adapterTwo = new AdapterTwo(models);
        i6Rec.setAdapter(adapterTwo);

        RecyclerView i6Rec2 = findViewById(R.id.i6rec2);
        i6Rec2.setHasFixedSize(true);
        i6Rec2.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        List<Model> models2 = new ArrayList<>();
        models2.add(new Model(R.string.o11,R.string.o12));
        models2.add(new Model(R.string.o13,R.string.o14));
        models2.add(new Model(R.string.o15,R.string.o16));
        AdapterTwo adapterTwo2 = new AdapterTwo(models2);
        i6Rec2.setAdapter(adapterTwo2);

        RecyclerView i6Rec3 = findViewById(R.id.i6rec3);
        i6Rec3.setHasFixedSize(true);
        i6Rec3.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        List<Model> models3 = new ArrayList<>();
        models3.add(new Model(R.string.o18,R.string.o19));
        models3.add(new Model(R.string.o20,R.string.o21));
        AdapterTwo adapterTwo3 = new AdapterTwo(models3);
        i6Rec3.setAdapter(adapterTwo3);


        //Banner
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdView mAdView = findViewById(R.id.adViewi6);
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
        Animatoo.INSTANCE.animateSlideRight(I6.this);

    }
}