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
import com.iam.bitcoin.Adapters.AdapterThree;
import com.iam.bitcoin.Adapters.AdapterTwo;
import com.iam.bitcoin.Model.Model;
import com.iam.bitcoin.R;

import java.util.ArrayList;
import java.util.List;

public class A8 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a8);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Quantum Computing Threats (Potential Future Challenges)");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources()
                .getColor(R.color.black)));

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.black));
        }



        RecyclerView a8Rec = findViewById(R.id.a8rec);
        a8Rec.setHasFixedSize(true);
        a8Rec.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        List<Model> models = new ArrayList<>();
        models.add(new Model(R.string.z3,R.string.z4,R.string.z5));
        models.add(new Model(R.string.z6,R.string.z7,R.string.z8));
        models.add(new Model(R.string.z9,R.string.z10,R.string.z11));
        models.add(new Model(R.string.z12,R.string.z13,R.string.z14));
        models.add(new Model(R.string.z15,R.string.z16,R.string.z17));
        AdapterThree adapterTwo = new AdapterThree(models);
        a8Rec.setAdapter(adapterTwo);


        //Banner
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdView mAdView = findViewById(R.id.adViewa8);
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
        Animatoo.INSTANCE.animateSlideRight(A8.this);

    }
}