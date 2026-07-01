package com.iam.bitcoin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.iam.bitcoin.Intermediate.I12;

public class aboutA extends AppCompatActivity {


    private LinearLayout rateButton, shareButton, PrivacyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("About");
        setContentView(R.layout.activity_about);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0f);
        }


        LinearLayout PrivacyButton = findViewById(R.id.rowPrivacy);
        PrivacyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUrl("https://sites.google.com/d/1Aww6P2rhq7UHuWZR7YUic1J1AGxXMq2k/p/1AVV8zl8DN1K2qIxUcJ-QnF0HTWvevZxo/edit");

            }
        });

        findViewById(R.id.rowContact).setOnClickListener(v ->
                startActivity(new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:iamguddu37@gmail.com.com"))));


        shareButton = findViewById(R.id.sharebtn);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent("android.intent.action.SEND");
                intent2.setType("text/plain");
                intent2.putExtra("android.intent.extra.SUBJECT", "Check Out: Nmap");
                intent2.putExtra("android.intent.extra.TEXT", "Bitcoin Guide" + "\n" +
                        "https://play.google.com/store/apps/details?id=com.iam.bitcoin");
                startActivity(Intent.createChooser(intent2, "Share App"));

            }
        });

        rateButton = findViewById(R.id.ratebtn);
        rateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.iam.bitcoin"));
                startActivity(intent1);


            }
        });


        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources()
                .getColor(R.color.white)));


        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.white));
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(aboutA.this, R.color.white)); //setting bar color
        }
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

    }

    private void openUrl(String url) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        } catch (android.content.ActivityNotFoundException e) {
            Toast.makeText(this, "No browser found", Toast.LENGTH_SHORT).show();
        }
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
        Animatoo.INSTANCE.animateSlideRight(aboutA.this);

    }
}