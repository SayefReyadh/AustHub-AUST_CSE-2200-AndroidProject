package com.example.sayefreyadh.austhub.model.coursemodel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asif Imtiaz Shaafi, on 9/4/2016.
 * Email: a15shaafi.209@gmail.com
 */
public class CourseData {

    private static List<CourseModel> courses;

    static {
        courses = new ArrayList<>();
        courses.add(new CourseModel(
                1, 1,
                "Mathematics-I!" +
                        "Critical Thinking & Communication!" +
                        "English Language Sessional!" +
                        "Physics!" +
                        "Physics Lab!" +
                        "Chemistry!" +
                        "Elementary Structured Programming!" +
                        "Elementary Structured Programming Lab!" +
                        "Introduction to Computer Systems",
                "3d!3d!1.5!3d!0.75!3d!3d!1.5!1.5"

        ));
        courses.add(new CourseModel(
                1, 2,
                "Mathematics-II!" +
                        "Basic Mechanical Engineering!" +
                        "Engineering Drawing!" +
                        "Basic Electrical Engineering!" +
                        "Basic Electrical Engineering Lab!" +
                        "Discrete Mathematics!" +
                        "Object Oriented Programming!" +
                        "Object Oriented Programming Lab!" +
                        "Software Development-I",
                "3d!3d!0.75!3d!1.5!3d!3d!3d!1.5!1.5"

        ));
        courses.add(new CourseModel(
                2, 1,
                "Data Structures!" +
                        "Data Structures Lab!" +
                        "Digital Logic Design!" +
                        "Digital Logic Design Lab!" +
                        "Society,Ethics and Technology!" +
                        "Mathematics-III!" +
                        "Electronic Devices & Circuits!" +
                        "Electronic Devices & Circuits Lab!" +
                        "Software Development-II",
                "3d!1.5!3d!1.5!3d!3d!3d!1.5!0.75"

        ));
        courses.add(new CourseModel(
                2, 2,
                "Mathematics - IV!" +
                        "Numerical Methods!" +
                        "Numerical Methods Lab!" +
                        "Algorithms!" +
                        "Algorithms Lab!" +
                        "Digital Electronics and Pulse Techniques!" +
                        "Digital Electronics and Pulse Techniques Lab!" +
                        "Computer Architecture!" +
                        "Assembly Language Programming!" +
                        "Software Development-III",
                "3d!3d!0.075!3d!0.75!3d!0.75!3d!0.75!0.75"

        ));
        courses.add(new CourseModel(
                3, 1,
                "Economics and Accounting!" +
                        "Mathematical Analysis for Computer Science!" +
                        "Database!" +
                        "Database Lab!" +
                        "Microprocessors!" +
                        "Microprocessors Lab!" +
                        "Digital System Design!" +
                        "Digital System Design Lab!" +
                        "Software Development-IV",
                "3d!3d!3d!0.75!3d!0.75!3d!0.75!0.75"

        ));
        courses.add(new CourseModel(
                3, 2,
                "Industrial Law and Safety Management!" +
                        "Data Communication!" +
                        "Operating System!" +
                        "Operating System Lab!" +
                        "Microcontroller Based System Design!" +
                        "Microcontroller Based System Design Lab!" +
                        "Information System Design and Software Engineering!" +
                        "Information System Design and Software Engineering Lab!" +
                        "Software Development-V",
                "3d!3d!3d!0.75!3d!0.75!3d!0.75!0.75"

        ));
        courses.add(new CourseModel(
                4, 1,
                "Industrial Management!" +
                        "Project & Thesis-I!" +
                        "Computer Networks!" +
                        "Computer Networks Lab!" +
                        "Artificial Intelligence!" +
                        "Artificial Intelligence Lab!" +
                        "Distributed Database Systems!" +
                        "Distributed Database Systems Lab!" +
                        "Formal Languages & Compilers!" +
                        "Formal Languages & Compilers Lab",
                "3d!3d!3d!1.5!3d!0.75!3d!0.75!3d!0.75"

        ));
        courses.add(new CourseModel(
                4, 2,
                "Telecommunication!" +
                        "Project and Thesis-II!" +
                        "Computer Graphics!" +
                        "Computer Graphics Lab!" +
                        "Pattern Recognition!" +
                        "Pattern Recognition Lab!" +
                        "Network Programming!" +
                        "Network Programming Lab!" +
                        "Digital Image Processing!" +
                        "Digital Image Processing Lab",
                "3d!3d!3d!1.5!3d!0.75!3d!0.75!3d!0.75"

        ));
    }

    public static List<CourseModel> getCourseList() {
        return courses;
    }

}
