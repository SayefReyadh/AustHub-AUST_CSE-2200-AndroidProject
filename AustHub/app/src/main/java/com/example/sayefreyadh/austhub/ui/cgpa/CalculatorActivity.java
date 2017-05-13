package com.example.sayefreyadh.austhub.ui.cgpa;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sayefreyadh.austhub.R;
import com.example.sayefreyadh.austhub.database.CourseDatabaseCRUD;
import com.example.sayefreyadh.austhub.model.coursemodel.CourseData;
import com.example.sayefreyadh.austhub.model.coursemodel.CourseModel;
import com.example.sayefreyadh.austhub.model.coursemodel.CourseTableConstants;
import com.example.sayefreyadh.austhub.user.UserData;
import com.example.sayefreyadh.austhub.utilities.CgpaCalculator;

import java.util.ArrayList;
import java.util.List;

import static android.text.InputType.TYPE_CLASS_NUMBER;
import static android.text.InputType.TYPE_NUMBER_FLAG_SIGNED;
import static com.example.sayefreyadh.austhub.ui.cgpa.ResultDisplayActivity.RESULT_STRING;

public class CalculatorActivity extends AppCompatActivity {

    //database
    CourseDatabaseCRUD helper;

    //textInputLayout container linear layout
    LinearLayout mContainerLayout;

    //mark input edit text array
    TextInputLayout mTextInputLayout;
    List<TextInputLayout> mTextInputLayouts;
    EditText mEditText;
    List<EditText> mEditTexts;
    Button mCgpaBtn;
    LinearLayout mLayout;

    ///details showing text
    TextView semesterText;

    //course structure
    CourseModel mCourseModel;
    List<String> courses;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        helper = new CourseDatabaseCRUD(this);

        updateDatabase();

        semesterText = (TextView) findViewById(R.id.user_year_semester_section_calculator);

        mContainerLayout = (LinearLayout) findViewById(R.id.mark_taking_linear_layout);

        mLayout = (LinearLayout) findViewById(R.id.content_calculator);

        mCgpaBtn = (Button) findViewById(R.id.calculate_cgpa_btn);

        mCgpaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateCgpa();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        fetchDataFromDatabase(
                Integer.parseInt(UserData.getUserYear(this)),
                Integer.parseInt(UserData.getUserSemester(this))
        );

        setLayoutWithTextInputLayout(courses.size());
        ///displaying semester
        semesterText.setText("Semester: " +
                UserData.getUserYear(this) +
                UserData.getUserSemester(this));
    }

    @Override
    protected void onPause() {
        super.onPause();
        removeAll();
    }

    private void fetchDataFromDatabase(int stdYear, int stdSemester) {

        helper.openDatabase();

        mCourseModel = helper.getCourse(stdYear, stdSemester);

        if (mCourseModel != null) {
            updateUi(mCourseModel.getCourseTitles());
        }

        helper.closeDatabase();
    }

    private void updateUi(List<String> courses) {

        this.courses = courses;

        mTextInputLayouts = new ArrayList<>(courses.size());
        mEditTexts = new ArrayList<>(courses.size());
    }

    private void setLayoutWithTextInputLayout(int size) {


        for (int i = 0; i < size; i++) {

            mTextInputLayout = new TextInputLayout(this);
            mEditText = new EditText(this);

            //layout attributes for textinputlayout
            LinearLayout.LayoutParams params =
                    new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    );
            params.setMargins(0, 0, 0, 16);

            //applying the layout attributes to the layout
            mTextInputLayout.setLayoutParams(params);


            mEditText.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));

            mEditText.setHint(courses.get(i));
            mEditText.setTextColor(Color.parseColor("#FF3131FF"));
            mEditText.setTextSize(20f);
            mEditText.setInputType(TYPE_CLASS_NUMBER | TYPE_NUMBER_FLAG_SIGNED);


            mTextInputLayout.addView(mEditText);
            mTextInputLayout.setHintAnimationEnabled(true);
            mTextInputLayout.setBackgroundColor(Color.parseColor("#dad9f2"));
            mTextInputLayout.setPadding(0, 8, 0, 4);
            mTextInputLayout.setHintTextAppearance(android.R.style.TextAppearance_Medium);

            mContainerLayout.addView(mTextInputLayout);

            mTextInputLayouts.add(mTextInputLayout);
            mEditTexts.add(mEditText);
        }

    }


    private void removeAll() {
        mContainerLayout.removeAllViews();
        mEditTexts.clear();
        mTextInputLayouts.clear();
    }

    private void updateDatabase() {
        helper.openDatabase();
        List<CourseModel> structures = CourseData.getCourseList();
        if (helper.getTableItemCount(CourseTableConstants.TABLE_NAME) == 0)
         {
            for (CourseModel s : structures) {

                try {
                    helper.insetCourse(s);
                } catch (SQLiteException e) {
                    e.printStackTrace();
                }

            }
        }
        helper.closeDatabase();
    }


    public void calculateCgpa() {
        List<Double> marks = new ArrayList<>(courses.size());

        boolean noError = true;

        int i = 0;

        for (EditText et :
                mEditTexts) {

            String mark = et.getText().toString().trim();

            if (!TextUtils.isEmpty(mTextInputLayouts.get(i).getError()))
                mTextInputLayouts.get(i).setError(null);

            if (TextUtils.isEmpty(mark)) {
                noError = false;
            }

            if (!TextUtils.isEmpty(mark)) {

                marks.add(Double.parseDouble(mark));
            } else {
                mTextInputLayouts.get(i).setErrorEnabled(true);
                mTextInputLayouts.get(i).setError("Please Enter Your Mark!");
                final int finalI = i;
                et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (hasFocus) {
                            mTextInputLayouts.get(finalI).setError(null);
                            mTextInputLayouts.get(finalI).setErrorEnabled(false);
                        }
                    }
                });
            }

            i++;
        }

        if (!noError) {
            Snackbar.make(mLayout, "Please Fix All Errors!", Snackbar.LENGTH_LONG).show();
        } else {

            CgpaCalculator cgpaCalculation =
                    new CgpaCalculator(courses,
                            mCourseModel.getCreditDouble(),
                            marks);

            String result = cgpaCalculation.getResultInStringFormat();

            Intent resultIntent = new Intent(getApplicationContext(), ResultDisplayActivity.class);

            resultIntent.putExtra(RESULT_STRING, result);

            startActivity(resultIntent);
        }
    }

}
