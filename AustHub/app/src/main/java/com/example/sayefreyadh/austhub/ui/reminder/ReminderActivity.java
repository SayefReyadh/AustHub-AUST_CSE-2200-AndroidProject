package com.example.sayefreyadh.austhub.ui.reminder;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.sayefreyadh.austhub.R;
import com.example.sayefreyadh.austhub.adapter.ReminderAdapter;
import com.example.sayefreyadh.austhub.database.ReminderDatabaseHelper;
import com.example.sayefreyadh.austhub.model.remindermodel.ReminderItem;

import java.util.List;

public class ReminderActivity extends AppCompatActivity
        implements ReminderAdapter.ItemClickCallback, View.OnClickListener {

    public static ReminderDatabaseHelper REMINDER_DATABASE_HELPER;
    private static final String BUNDLE_EXTRAS = "BUNDLE_EXTRAS";
    private static final String EXTRA_SUBJECT = "EXTRA_SUBJECT";
    private static final String EXTRA_DETAILS = "EXTRA_DETAILS";
    private static final String EXTRA_DATE = "EXTRA_DATE";
    private static final String EXTRA_TIME = "EXTRA_TIME";


    private RecyclerView recyclerView;
    private ReminderAdapter adapter;
    private List<ReminderItem> listData;
    private FloatingActionButton addReminder;
    private ReminderDatabaseHelper dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        dbHandler = new ReminderDatabaseHelper(this , null , null , 1);
        REMINDER_DATABASE_HELPER = dbHandler;

        ///ArrayList dummyArrayListToCallThisFunction = (ArrayList) ReminderData.getData();
        ///for(int i = 0 ; i < dummyArrayListToCallThisFunction.size() ; i++)
        ///{
        ///    dbHandler.addReminder((ReminderItem) dummyArrayListToCallThisFunction.get(i));
        ///}

        recyclerView = (RecyclerView) findViewById(R.id.reminder_rec_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        addReminder = (FloatingActionButton) findViewById(R.id.reminder_button);
        ///adapter = new ReminderAdapter(dbHandler.getReminderData(), this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        initAdapter();

        adapter.setItemClickCallback(this);

        addReminder.setOnClickListener(this);
    }

    private void initAdapter() {

        listData = dbHandler.getReminderData();
        adapter = new ReminderAdapter(listData, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(ReminderActivity.this , AddReminderActivity.class);
        startActivity(intent);
    }

    /*
    private View.OnClickListener addReminderListiner = new View.OnClickListener() {
        public void onClick(View v) {

        }
    };
    */


    @Override
    public void onItemClick(int p) {
        ReminderItem item = (ReminderItem) listData.get(p);

//        Toast.makeText(ReminderActivity.this , "Container Listener Works" , Toast.LENGTH_LONG).show();

        Intent i = new Intent(this, ReminderDetailActivity.class);

        Bundle extras = new Bundle();
        extras.putString(EXTRA_SUBJECT, item.getSubject());
        extras.putString(EXTRA_DETAILS, item.getDetails());
        extras.putString(EXTRA_DATE , item.getDate());
        extras.putString(EXTRA_TIME , item.getTime());
        i.putExtra(BUNDLE_EXTRAS, extras);

        startActivity(i);
    }



    @Override
    public void onDeleteIconClick(int p) {
        ReminderItem item = (ReminderItem) listData.get(p);
        //update our data

        ///Delete From Database
        ///REMINDER_DATABASE_HELPER.deleteReminder(item.getSubject());
        //pass new data to adapter and update
//        Toast.makeText(ReminderActivity.this , "Delete Button Works" , Toast.LENGTH_LONG).show();

        ///ReminderData.reminderData.remove(item);
        ///adapter.setListData((ArrayList<ReminderItem>) ReminderData.getData());
        ///adapter.notifyDataSetChanged();

        dbHandler.deleteReminder(item.getSubject());
        listData.remove(item);
        adapter.notifyDataSetChanged();
    }


}
