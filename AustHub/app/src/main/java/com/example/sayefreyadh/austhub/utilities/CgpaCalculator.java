package com.example.sayefreyadh.austhub.utilities;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Asif Imtiaz Shaafi on February, 2017.
 * Email: a15shaafi.209@gmail.com
 */

public class CgpaCalculator {

    /*
        a class that takes the numbers and credit hours of a student and calculates them,gets the
        cgpa and returns a formatted text to show the student about all the details including the
        subjects, marks, grades and cgpa
     */

    private List<String> courses;
    private List<Double> numbers;
    private List<Double> creditHour = new ArrayList<>();
    private List<String> grades = new ArrayList<>();
    private double totalCreditHour;

    public CgpaCalculator(List<String> courses, List<Double> creditHour, List<Double> numbers) {
        this.courses = courses;
        this.numbers = numbers;
        setCreditHour(creditHour);
    }

    /*
        sets the credit hour and calculates the total credit of the semester
     */
    private void setCreditHour(List<Double> creditHour) {

        totalCreditHour = 0.0;

        this.creditHour = creditHour;

        for (double x :
                creditHour) {
            totalCreditHour += x;
        }

    }

    /*
        calculates the cgpa of a student properly using the credit hours,numbers and total credit hour
     */
    private double calculation(int i) {
        double result = 0.0;

        for (double a : numbers) {
            Log.i("index", "i = " + i);
            if (a >= 80) {
                result += 4.00 * creditHour.get(i++);
                grades.add("A+");
            } else if (a >= 75) {
                result += 3.75 * creditHour.get(i++);
                grades.add("A");
            } else if (a >= 70) {
                result += 3.50 * creditHour.get(i++);
                grades.add("A-");
            } else if (a >= 65) {
                result += 3.25 * creditHour.get(i++);
                grades.add("B+");
            } else if (a >= 60) {
                result += 3.00 * creditHour.get(i++);
                grades.add("B");
            } else if (a >= 55) {
                result += 2.75 * creditHour.get(i++);
                grades.add("B-");
            } else if (a >= 50) {
                result += 2.50 * creditHour.get(i++);
                grades.add("C+");
            } else if (a >= 45) {
                result += 2.25 * creditHour.get(i++);
                grades.add("C");
            } else if (a >= 40) {
                result += 2.00 * creditHour.get(i++);
                grades.add("D");
            } else {
                result += 0.00 * creditHour.get(i++);
                grades.add("F");
            }
        }


        result /= totalCreditHour;

        return result;
    }

    /*
        calculates the result/cgpa and returns the formatted result with subject,number, grade and cgpa
        of a student
     */
    public String getResultInStringFormat() {
        StringBuilder result = new StringBuilder("Your CGPA: " + String.format(Locale.getDefault(), "%.02f", calculation(0)));
        result.append("\n\nDetails:\n\n");

        int index = 0;

        for (String sub : courses) {
            result.append("** ");
            result.append(sub);
            result.append(" **\n::: ");
            result.append(String.valueOf(numbers.get(index)));
            result.append("     ->     ");
            result.append(grades.get(index));
            result.append("\n\n");
            index++;
        }

        return result.toString();
    }

}
