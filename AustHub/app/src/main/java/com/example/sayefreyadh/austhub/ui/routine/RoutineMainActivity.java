package com.example.sayefreyadh.austhub.ui.routine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.sayefreyadh.austhub.R;
import com.example.sayefreyadh.austhub.adapter.RoutineAdapter;
import com.example.sayefreyadh.austhub.model.routinemodel.RoutineModel;
import com.example.sayefreyadh.austhub.user.UserData;

import java.util.ArrayList;
import java.util.List;

public class RoutineMainActivity extends AppCompatActivity {
    private static final String BUNDLE_EXTRAS = "BUNDLE_EXTRAS";
    private static final String EXTRA_DAY = "EXTRA_DAY";


    private RecyclerView recyclerView;
    private RoutineAdapter adapter;
    private List listData;
    String currentDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine_main);
        Bundle extras = getIntent().getBundleExtra(BUNDLE_EXTRAS);
        currentDay = extras.getString(EXTRA_DAY);

        ///listData = (ArrayList) UserData.getDaysRoutine().get(day);
        String userSemesterRoutineStringFull = UserData.getUserSemesterRoutine();

        listData = getListData(userSemesterRoutineStringFull);
        recyclerView = (RecyclerView) findViewById(R.id.routine_rec_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new RoutineAdapter(listData, this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    private List getListData(String userSemesterRoutineStringFull) {


        String[] days = userSemesterRoutineStringFull.split("~");
        List<RoutineModel> eachDayRoutineData = new ArrayList<>();

        for(String i : days)
            if(i.contains(currentDay.toLowerCase()))
            {
                String[] tempString = i.split("=");

                //System.out.println(tempString[1]);
                String[] classRoutine = tempString[1].split("!");
                for(String j : classRoutine)
                {
                    String[] eachClass = j.split(";");
                    RoutineModel ob = new RoutineModel(eachClass[0] , eachClass[2] , eachClass[3] , eachClass[1] , eachClass[4]);
                    eachDayRoutineData.add(ob);
                }
            }

        return eachDayRoutineData;
    }


}
