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

public class I4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i4);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Scalability Challenges and Solutions (The Lightning Network)");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources()
                .getColor(R.color.black)));

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.black));
        }


        RecyclerView i4Rec = findViewById(R.id.i4rec);
        i4Rec.setHasFixedSize(true);
        i4Rec.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        List<Model> models = new ArrayList<>();
        models.add(new Model(R.string.m4,R.string.m5));
        models.add(new Model(R.string.m6,R.string.m7));
        models.add(new Model(R.string.m8,R.string.m9));
        AdapterTwo adapterTwo = new AdapterTwo(models);
        i4Rec.setAdapter(adapterTwo);

        RecyclerView i4Rec2 = findViewById(R.id.i4rec2);
        i4Rec2.setHasFixedSize(true);
        i4Rec.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        List<Model> models2 = new ArrayList<>();
        models2.add(new Model(R.string.m11,R.string.m12));
        models2.add(new Model(R.string.m13,R.string.m14));
        models2.add(new Model(R.string.m15,R.string.m16));
        models2.add(new Model(R.string.m17,R.string.m18));
        models2.add(new Model(R.string.m19,R.string.m20));
        AdapterTwo adapterTwo2 = new AdapterTwo(models2);
        i4Rec2.setAdapter(adapterTwo2);




        RecyclerView i4Rec4 = findViewById(R.id.i4rec4);
        i4Rec4.setHasFixedSize(true);
        i4Rec4.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        List<Model> models4 = new ArrayList<>();
        models4.add(new Model(R.string.m22,R.string.m23));
        models4.add(new Model(R.string.m24,R.string.m25));
        models4.add(new Model(R.string.m26,R.string.m27));
        AdapterTwo adapterTwo4 = new AdapterTwo(models4);
        i4Rec4.setAdapter(adapterTwo4);


        //Banner
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdView mAdView = findViewById(R.id.adViewi4);
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
        Animatoo.INSTANCE.animateSlideRight(I4.this);

    }
}