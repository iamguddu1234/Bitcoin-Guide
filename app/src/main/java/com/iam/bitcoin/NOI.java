package com.iam.bitcoin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

public class NOI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar

        setContentView(R.layout.activity_n_o_i);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(NOI.this, R.color.black)); //setting bar color
        }
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        final Button button = (Button)findViewById(R.id.retry);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(NetworkInformation.isConnected(NOI.this))
                {
                    Intent intent = new Intent(NOI.this,Splash.class);
                    startActivity(intent);

                    Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade);
                    button.startAnimation(animation1);

                }else{

                    //Toast.makeText(NOI.this,"Check internet connection",Toast.LENGTH_LONG).show();

                }

                Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
                button.startAnimation(animation1);

            }
        });

    }


}
