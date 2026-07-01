package com.iam.bitcoin;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

public class MainActivity extends AppCompatActivity {


    ImageButton aboutImageButton, rateImageButton;


    int[] image = {R.drawable.introdu,
            R.drawable.hist,
            R.drawable.who,
            R.drawable.currency,
            R.drawable.chain,
            R.drawable.minin,
            R.drawable.stor,
            R.drawable.advan,
            R.drawable.appl,
            R.drawable.futur,
            R.drawable.factt,


    };


    String[] listItem =
            {" Introduction",
                    "History",
                    "Who is Satoshi Nakamoto?",
                    "What is Cryptocurrency?",
                    "What is BlockChain?",
                    "Bitcoin Mining",
                    "Bitcoin-BUY,SELL,SEND,RECEIVE",
                    "Advantages And Disadvantages",
                    "Bitcoin Application",
                    "Bitcoin Future",
                    "Unknown Bitcoin Fact",

            };

    private ListView listViewlinux;

    CardView oneCardView, twoCardView, threeCardView, fourCardView, fiveCardView, sixCardView, sevenCardView, eightCardView, nineCardView, tenCardView, elevenCardView;

    String TAG;
    private static final String AD_UNIT_ID = "ca-app-pub-5541243853026577/4063563669";
    private InterstitialAd interstitialAd;
    private RewardedAd mRewardedAd;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_main);


        Gdpr gdpr = new Gdpr(this);
        gdpr.setGdpr();

        oneCardView = findViewById(R.id.one);
        oneCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showInterstitial();
                Intent intent = new Intent(MainActivity.this, One.class);
                startActivity(intent);
            }
        });


        twoCardView = findViewById(R.id.two);
        twoCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showInterstitial();
                Intent intent = new Intent(MainActivity.this, Two.class);
                startActivity(intent);
            }
        });

        threeCardView = findViewById(R.id.three);
        threeCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showInterstitial();
                Intent intent = new Intent(MainActivity.this, Whoissana.class);
                startActivity(intent);

            }
        });

        fourCardView = findViewById(R.id.four);
        fourCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showInterstitial();
                Intent intent = new Intent(MainActivity.this, WhatCrypto.class);
                startActivity(intent);
            }
        });

        fiveCardView = findViewById(R.id.five);
        fiveCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInterstitial();
                Intent intent = new Intent(MainActivity.this, Whatblock.class);
                startActivity(intent);

            }
        });

        sixCardView = findViewById(R.id.six);
        sixCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showInterstitial();
                Intent intent = new Intent(MainActivity.this, Three.class);
                startActivity(intent);
            }
        });
        sevenCardView = findViewById(R.id.seven);
        sevenCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showInterstitial();
                Intent intent = new Intent(MainActivity.this, sellBit.class);
                startActivity(intent);

            }
        });

        eightCardView = findViewById(R.id.eight);
        eightCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showInterstitial();
                Intent intent = new Intent(MainActivity.this, BitcoinAdvDisadv.class);
                startActivity(intent);
            }
        });
        nineCardView = findViewById(R.id.nine);
        nineCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInterstitial();
                Intent intent = new Intent(MainActivity.this, ApplicationBitcoin.class);
                startActivity(intent);
            }
        });

        tenCardView = findViewById(R.id.ten);
        tenCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showInterstitial();
                Intent intent = new Intent(MainActivity.this, Four.class);
                startActivity(intent);
            }
        });

        elevenCardView = findViewById(R.id.eleven);
        elevenCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showInterstitial();
                Intent intent = new Intent(MainActivity.this, Five.class);
                startActivity(intent);
            }
        });


//        MobileAds.initialize(this, new OnInitializationCompleteListener() {
//            @Override
//            public void onInitializationComplete(InitializationStatus initializationStatus) {
//            }
//        });

        loadAd();


//        AdRequest adRequest = new AdRequest.Builder().build();
//
//        RewardedAd.load(this, "ca-app-pub-5541243853026577/3260783734",
//                adRequest, new RewardedAdLoadCallback() {
//                    @Override
//                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
//                        // Handle the error.
//                        //Log.d(TAG, loadAdError.getMessage());
//                        mRewardedAd = null;
//                    }
//
//                    @Override
//                    public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
//                        mRewardedAd = rewardedAd;
//                        //Log.d(TAG, "Ad was loaded.");
//                    }
//                });


        aboutImageButton = findViewById(R.id.image1);
        aboutImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, aboutA.class);
                startActivity(intent);

            }
        });
        rateImageButton = findViewById(R.id.image2);
        rateImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + getPackageName()));
                startActivity(intent1);

            }
        });


        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));

//        listViewlinux = findViewById(R.id.mainlist);
//        CustomList customList = new CustomList();
//        listViewlinux.setAdapter(customList);
//        listViewlinux.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                if (position == 0) {
//                    showInterstitial();
//                    Intent intent = new Intent(MainActivity.this, One.class);
//                    startActivity(intent);
//                }
//                if (position == 1) {
//                    showInterstitial();
//                    Intent intent = new Intent(MainActivity.this, Two.class);
//                    startActivity(intent);
//                }
//                if (position == 2) {
//                    showInterstitial();
//                    Intent intent = new Intent(MainActivity.this, Whoissana.class);
//                    startActivity(intent);
//                }
//                if (position == 3) {
//                    showInterstitial();
//                    Intent intent = new Intent(MainActivity.this, WhatCrypto.class);
//                    startActivity(intent);
//                }
//                if (position == 4) {
//                    showInterstitial();
//                    Intent intent = new Intent(MainActivity.this, Whatblock.class);
//                    startActivity(intent);
//                }
//
//                if (position == 5) {
//                    showInterstitial();
//                    Intent intent = new Intent(MainActivity.this, Three.class);
//                    startActivity(intent);
//                }
//                if (position == 6) {
//                    showInterstitial();
//                    Intent intent = new Intent(MainActivity.this, sellBit.class);
//                    startActivity(intent);
//                }
//                if (position == 7) {
//                    showInterstitial();
//                    Intent intent = new Intent(MainActivity.this, BitcoinAdvDisadv.class);
//                    startActivity(intent);
//                }
//                if (position == 8) {
//                    if (interstitialAd != null) {
//                        interstitialAd.show(MainActivity.this);
//                    } else {
//                        Log.d("TAG", "The interstitial ad wasn't ready yet.");
//                    }
//                    Intent intent = new Intent(MainActivity.this, ApplicationBitcoin.class);
//                    startActivity(intent);
//                }
//                if (position == 9) {
//                    showInterstitial();
//                    Intent intent = new Intent(MainActivity.this, Four.class);
//                    startActivity(intent);
//                }
//                if (position == 10) {
//                    showInterstitial();
//                    Intent intent = new Intent(MainActivity.this, Five.class);
//                    startActivity(intent);
//
//                }
//
//            }
//        });


    }

    class CustomList extends BaseAdapter {


        @Override
        public int getCount() {
            return image.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView = getLayoutInflater().inflate(R.layout.listdecorate, null);

            ImageView Image = (ImageView) convertView.findViewById(R.id.ImageL);
            TextView textView = (TextView) convertView.findViewById(R.id.MainTitle);

            Image.setImageResource(image[position]);
            textView.setText(listItem[position]);

            return convertView;


        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        menu.add("Share");
//        menu.add("Rate");
//        menu.add("About");
//
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if (item.getTitle().equals("Share")) {
//
//
//        } else if (item.getTitle().equals("Rate")) {
//
//        } else if (item.getTitle().equals("About")) {
//
//        }
//        return super.onOptionsItemSelected(item);
//    }


    public void loadAd() {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(
                this,
                AD_UNIT_ID,
                adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        MainActivity.this.interstitialAd = interstitialAd;
                        Log.i(TAG, "onAdLoaded");
                        interstitialAd.setFullScreenContentCallback(
                                new FullScreenContentCallback() {
                                    @Override
                                    public void onAdDismissedFullScreenContent() {
                                        // Called when fullscreen content is dismissed.
                                        // Make sure to set your reference to null so you don't
                                        // show it a second time.
                                        MainActivity.this.interstitialAd = null;
                                        Log.d("TAG", "The ad was dismissed.");
                                    }

                                    @Override
                                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                                        // Called when fullscreen content failed to show.
                                        // Make sure to set your reference to null so you don't
                                        // show it a second time.
                                        MainActivity.this.interstitialAd = null;
                                        Log.d("TAG", "The ad failed to show.");
                                    }

                                    @Override
                                    public void onAdShowedFullScreenContent() {
                                        // Called when fullscreen content is shown.
                                        Log.d("TAG", "The ad was shown.");
                                    }
                                });
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.i(TAG, loadAdError.getMessage());
                        interstitialAd = null;

                        String error =
                                String.format(
                                        "domain: %s, code: %d, message: %s",
                                        loadAdError.getDomain(), loadAdError.getCode(), loadAdError.getMessage());
                        Toast.makeText(
                                        MainActivity.this, "onAdFailedToLoad() with error: " + error, Toast.LENGTH_SHORT)
                                .show();
                    }
                });
    }

    private void showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and restart the game.
        if (interstitialAd != null) {
            interstitialAd.show(this);
        } else {

        }
    }
}