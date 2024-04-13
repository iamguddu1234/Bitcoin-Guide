package com.iam.bitcoin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.gms.ads.MobileAds;
import com.google.android.material.tabs.TabLayout;
import com.google.android.ump.ConsentInformation;

import java.util.concurrent.atomic.AtomicBoolean;

public class Home extends AppCompatActivity {

    private ConsentInformation consentInformation;
    private final AtomicBoolean isMobileAdsInitializeCalled = new AtomicBoolean(false);



    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TextView selectedTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();
        Gdpr gdpr = new Gdpr(this);
        gdpr.setGdpr();


        // Find and initialize the ViewPager and TabLayout
        viewPager = findViewById(R.id.viewPager);

        tabLayout = findViewById(R.id.tabLayout);


        TextView textView1 = findViewById(R.id.textView1);
        TextView textView2 = findViewById(R.id.textView2);
        TextView textView3 = findViewById(R.id.textView3);
        TextView textView4 = findViewById(R.id.textView4);



        // Create the adapter
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());

        // Set the adapter to the ViewPager
        viewPager.setAdapter(adapter);


        // Set up the TabLayout with the ViewPager
        tabLayout.setupWithViewPager(viewPager);



        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(0);
                handleTextClick(textView1);
            }
        });

        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1);
                handleTextClick(textView2);
            }
        });

        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(2);
                handleTextClick(textView3);
            }
        });

        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(3);
                handleTextClick(textView4);
            }
        });




        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) { // First fragment
                    textView1.setBackgroundColor(ContextCompat.getColor(Home.this, R.color.black));
                    textView1.setTextColor(ContextCompat.getColor(Home.this, R.color.white));

                    textView2.setBackgroundColor(Color.TRANSPARENT);
                    textView2.setTextColor(Color.BLACK);

                    textView3.setBackgroundColor(Color.TRANSPARENT);
                    textView3.setTextColor(Color.BLACK);

                    textView4.setBackgroundColor(Color.TRANSPARENT);
                    textView4.setTextColor(Color.BLACK);
                } else if (position == 1) { // Second fragment
                    textView1.setBackgroundColor(Color.TRANSPARENT);
                    textView1.setTextColor(Color.BLACK);

                    textView2.setBackgroundColor(ContextCompat.getColor(Home.this, R.color.black));
                    textView2.setTextColor(ContextCompat.getColor(Home.this, R.color.white));

                    textView3.setBackgroundColor(Color.TRANSPARENT);
                    textView3.setTextColor(Color.BLACK);

                    textView4.setBackgroundColor(Color.TRANSPARENT);
                    textView4.setTextColor(Color.BLACK);
                } else if (position == 2) { // Third fragment
                    textView1.setBackgroundColor(Color.TRANSPARENT);
                    textView1.setTextColor(Color.BLACK);

                    textView2.setBackgroundColor(Color.TRANSPARENT);
                    textView2.setTextColor(Color.BLACK);

                    textView3.setBackgroundColor(ContextCompat.getColor(Home.this, R.color.black));
                    textView3.setTextColor(ContextCompat.getColor(Home.this, R.color.white));

                    textView4.setBackgroundColor(Color.TRANSPARENT);
                    textView4.setTextColor(Color.BLACK);
                } else if (position == 3) { // Fourth fragment
                    textView1.setBackgroundColor(Color.TRANSPARENT);
                    textView1.setTextColor(Color.BLACK);

                    textView2.setBackgroundColor(Color.TRANSPARENT);
                    textView2.setTextColor(Color.BLACK);

                    textView3.setBackgroundColor(Color.TRANSPARENT);
                    textView3.setTextColor(Color.BLACK);

                    textView4.setBackgroundColor(ContextCompat.getColor(Home.this, R.color.black));
                    textView4.setTextColor(ContextCompat.getColor(Home.this, R.color.white));
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }





            private void handlePageChange(int position) {
                switch (position) {
                    case 0:
                        handleTextClick(textView1);
                        break;
                    case 1:
                        handleTextClick(textView2);
                        break;
                    case 2:
                        handleTextClick(textView3);
                        break;
                    case 3:
                        handleTextClick(textView4);
                        break;
                }
            }

        });



        handleTextClick(textView1);


    }


    private void initializeMobileAdsSdk() {
        if (isMobileAdsInitializeCalled.getAndSet(true)) {
            return;
        }

        // Initialize the Google Mobile Ads SDK.
        MobileAds.initialize(this);

        // TODO: Request an ad.
        // InterstitialAd.load(...);
    }


    // Adapter class
    private class MyPagerAdapter extends FragmentPagerAdapter {

        private String[] fragmentTitles = {
                "Fragment 1",
                "Fragment 2",
                "Fragment 3",
                "Fragment 4",
        };

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // Return the appropriate fragment based on the position
            switch (position) {
                case 0:
                    return new AA_Basic();
                case 1:
                    return new AA_Intermediate();
                case 2:
                    return new AA_Advance();
                case 3:
                    return new AA_My_Apps();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Return the number of fragments
            return fragmentTitles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // Return the title of the fragment at the specified position
            return fragmentTitles[position];
        }
    }


    private void handleTextClick(TextView textView) {
        if (selectedTextView != null) {
            // Restore the background color and text color of the previously selected TextView
            selectedTextView.setBackgroundColor(Color.TRANSPARENT);
            selectedTextView.setTextColor(Color.BLACK);
        }

        // Check if the clicked TextView is the same as the previously selected TextView
        if (selectedTextView != textView) {
            // Set the background color and text color of the clicked TextView
//            textView.setBackgroundColor(Color.YELLOW);
            textView.setBackgroundColor(ContextCompat.getColor(this, R.color.black));

            textView.setTextColor(ContextCompat.getColor(this, R.color.white));
            selectedTextView = textView;  // Update the selectedTextView variable
        } else {
            // The same TextView is clicked again, restore its default color
            textView.setBackgroundColor(Color.TRANSPARENT);
            textView.setTextColor(Color.BLACK);
            selectedTextView = null;  // Clear the selectedTextView variable
        }



        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.black));
        }


//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().setNavigationBarColor(ContextCompat.getColor(Home.this, R.color.black)); //setting bar color
//        }
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

    }







}