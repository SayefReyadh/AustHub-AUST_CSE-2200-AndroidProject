package com.example.sayefreyadh.austhub.ui.homemenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.sayefreyadh.austhub.R;
import com.example.sayefreyadh.austhub.ui.SignupActivity;
import com.example.sayefreyadh.austhub.user.UserData;

public class SplashScreenActivity extends Activity {

    public static int SPLASH_TIME_OUT = 1200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent;

                if (UserData.getUserSemesterAndSection(getApplicationContext()).contains("00"))
                {
                    intent = new Intent(getApplicationContext(), SignupActivity.class);
                }
                else {
                    intent = new Intent(SplashScreenActivity.this, NavActivity.class);
                }

//                Toast.makeText(SplashScreenActivity.this, UserData.getUserSemesterAndSection(getApplicationContext()), Toast.LENGTH_SHORT).show();

                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT);

    }
}
