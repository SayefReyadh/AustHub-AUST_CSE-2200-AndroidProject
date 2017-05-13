package com.example.sayefreyadh.austhub.model.routinemodel;

/**
 * Created by SayefReyadh on 1/7/2017.
 */

public class RoutineModel {
    public String time;
    public String courseNumber;
    public String courseName;
    public String roomNumber;
    public String teachersName;

    public RoutineModel(String time, String courseNumber, String courseName, String roomNumber, String teachersName) {
        this.time = time;
        this.courseNumber = courseNumber;
        this.courseName = courseName;
        this.roomNumber = roomNumber;
        this.teachersName = teachersName;
    }
}
