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

public class A11 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a11);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Mining Pools (Combining Resources for More Efficient Mining)");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources()
                .getColor(R.color.black)));

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.black));
        }



        RecyclerView a11Rec = findViewById(R.id.a11rec);
        a11Rec.setHasFixedSize(true);
        a11Rec.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        List<Model> models = new ArrayList<>();
        models.add(new Model(R.string.ab11,R.string.ab12));
        models.add(new Model(R.string.ab13,R.string.ab14));
        models.add(new Model(R.string.ab15,R.string.ab16));
        models.add(new Model(R.string.ab17,R.string.ab18));
        AdapterTwo adapterTwo = new AdapterTwo(models);
        a11Rec.setAdapter(adapterTwo);

        RecyclerView a11Reci = findViewById(R.id.a11reci);
        a11Reci.setHasFixedSize(true);
        a11Reci.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        List<Model> models1 = new ArrayList<>();
        models1.add(new Model(R.string.ab20,R.string.ab21));
        models1.add(new Model(R.string.ab22,R.string.ab23));
        models1.add(new Model(R.string.ab24,R.string.ab25));
        AdapterTwo adapterTwo1 = new AdapterTwo(models);
        a11Reci.setAdapter(adapterTwo1);


        RecyclerView a11Recii = findViewById(R.id.a11recii);
        a11Recii.setHasFixedSize(true);
        a11Recii.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        List<Model> models2 = new ArrayList<>();
        models2.add(new Model(R.string.ab27,R.string.ab28));
        models2.add(new Model(R.string.ab29,R.string.ab30));
        models2.add(new Model(R.string.ab31,R.string.ab32));
        AdapterTwo adapterTwo2 = new AdapterTwo(models2);
        a11Recii.setAdapter(adapterTwo2);


        //Banner
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdView mAdView = findViewById(R.id.adViewa11);
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
        Animatoo.INSTANCE.animateSlideRight(A11.this);

    }
}