package com.example.sayefreyadh.austhub.ui.results;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.sayefreyadh.austhub.R;
import com.example.sayefreyadh.austhub.user.UserData;

public class ResultActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private WebView webView;
    private String url = "http://aust.edu/result/index.htm";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_new);

        viewPager = (ViewPager) findViewById(R.id.activity_result_new_viewpager);
        viewPager.setAdapter(new CustomViewPagerAdapter(getSupportFragmentManager() , getApplicationContext() ));

        tabLayout = (TabLayout) findViewById(R.id.activity_result_new_tablayout);

        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    protected void onStart() {
        super.onStart();

        UserData.updateUserAcademicStatus(getBaseContext());
    }

    private class CustomViewPagerAdapter extends FragmentPagerAdapter {
        private String[] fragments = {"Theory Result" , "Lab Result" , "Carry Result"};

        public CustomViewPagerAdapter(FragmentManager supportFragmentManager, Context applicationContext) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position)
            {
                case 0:
                    return new TheoryResultFragment();
                case 1:
                    return new LabResultFragment();
                case 2:
                    return new CarryResultFragment();
                default:
                    return new TheoryResultFragment();
            }
        }

        @Override
        public int getCount() {
            return fragments.length;
        }

        @Override
        public CharSequence getPageTitle(int position)
        {
            return fragments[position];
        }
    }
}

