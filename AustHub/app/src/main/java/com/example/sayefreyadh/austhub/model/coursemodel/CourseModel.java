package com.example.sayefreyadh.austhub.model.coursemodel;

import android.content.ContentValues;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Asif Imtiaz Shaafi, on 2016.
 * Email: a15shaafi.209@gmail.com
 */
public class CourseModel {

    private int year, semester;
    private String courseTitlesString;
    private String courseCreditsString;
    private List<String> courseTitles;
    private List<String> courseCredits;
    private List<Double> creditDouble;

    public CourseModel() {
    }

    public CourseModel(int year, int semester, String courseTitlesString, String courseCreditsString) {
        this.year = year;
        this.semester = semester;
        this.courseTitlesString = courseTitlesString;
        this.courseCreditsString = courseCreditsString;

        setCourseTitles(courseTitlesString);
        setCourseCredits(courseCreditsString);
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getCourseTitlesString() {
        return courseTitlesString;
    }

    public void setCourseTitlesString(String courseTitlesString) {
        this.courseTitlesString = courseTitlesString;
    }

    public String getCourseCreditsString() {
        return courseCreditsString;
    }

    public void setCourseCreditsString(String courseCreditsString) {
        this.courseCreditsString = courseCreditsString;
    }

    public List<String> getCourseTitles() {
        return courseTitles;
    }

    public void setCourseTitles(String courseTitles) {

        String[] titleNames = courseTitles.split("!");

        this.courseTitles = Arrays.asList(titleNames);
    }

    public List<String> getCourseCredits() {
        return courseCredits;
    }

    public void setCourseCredits(String courseCredits) {

        creditDouble = new ArrayList<>();

        String[] credits = courseCredits.split("!");

        for (String credit : credits) {
            creditDouble.add(Double.parseDouble(credit));
        }

        this.courseCredits = Arrays.asList(credits);
    }

    public List<Double> getCreditDouble() {
        return creditDouble;
    }

    /***********************************************************************************
        value contents for inserting data in to database
    ************************************************************************************/
    public ContentValues getCourseContentValues()
    {
        ContentValues values = new ContentValues(4);

        values.put(CourseTableConstants.COLUMN_YEAR, year);
        values.put(CourseTableConstants.COLUMN_SEMESTER, semester);
        values.put(CourseTableConstants.COLUMN_COURSE, courseTitlesString);
        values.put(CourseTableConstants.COLUMN_CREDIT, courseCreditsString);

        return values;
    }


    @Override
    public String toString() {
        return "CourseModel{" +
                "year=" + year +
                ", semester=" + semester +
                ", courseTitlesString='" + courseTitlesString + '\'' +
                ", courseCreditsString='" + courseCreditsString + '\'' +
                ", courseTitles=" + courseTitles +
                ", courseCredits=" + courseCredits +
                ", creditDouble=" + creditDouble +
                '}';
    }
}
