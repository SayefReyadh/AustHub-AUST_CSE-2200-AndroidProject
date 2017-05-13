package com.example.sayefreyadh.austhub.ui.cgpa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.TextView;

import com.example.sayefreyadh.austhub.R;

public class ResultDisplayActivity extends AppCompatActivity {

    public static final String RESULT_STRING = "result";

    private TextView mResultShowingTextView;
    private String mResultString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_display);


        mResultShowingTextView = (TextView) findViewById(R.id.result_text_view);

        mResultString = getIntent().getStringExtra(RESULT_STRING);
        if (TextUtils.isEmpty(mResultString)) {
            ///if result string is empty that means something is wrong! so show the user the
            ///error msg

            mResultString = "Sorry something went wrong! No result found to display!";
        } else {

            mResultShowingTextView.setText(mResultString);
        }
    }
}
