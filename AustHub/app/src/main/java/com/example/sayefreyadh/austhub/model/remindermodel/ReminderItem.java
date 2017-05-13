package com.example.sayefreyadh.austhub.model.remindermodel;

public class ReminderItem {
    private int iconImage;

    private int id;
    private String subject;
    private String details;
    private String date;
    private String time;

    public ReminderItem() {
    }

    public ReminderItem(int id, String subject, String details, String date, String time) {
        this.id = id;
        this.subject = subject;
        this.details = details;
        this.date = date;
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }


    public int getIconImage() {
        return iconImage;
    }

    public void setIconImage(int iconImage) {
        this.iconImage = iconImage;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setId(int id) {
        this.id = id;
    }
}