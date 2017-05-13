package com.example.sayefreyadh.austhub.model.homemenumodel;

import com.example.sayefreyadh.austhub.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SayefReyadh on 12/26/2016.
 */

public class HomeMenuData {

    private static final String[] titles = {"Routine", "CGPA Calculator", "Reminder", "Results", "Settings" , "About Us"};
    private static final int[] icons = {R.drawable.icon_routine, R.drawable.icon_calculator
            , R.drawable.icon_reminder, R.drawable.icon_results
            , R.drawable.icon_settings, R.drawable.icon_aboutus};


    public static List<HomeMenuListItem> getListData() {
        List<HomeMenuListItem> data = new ArrayList<>();

        for (int i = 0; i < icons.length; i++) {
            HomeMenuListItem item = new HomeMenuListItem();
            item.setImageResId(icons[i]);
            item.setTitle(titles[i]);
            data.add(item);
        }

        return data;
    }

}
