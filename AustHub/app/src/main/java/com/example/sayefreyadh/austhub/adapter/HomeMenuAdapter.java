package com.example.sayefreyadh.austhub.adapter;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sayefreyadh.austhub.R;
import com.example.sayefreyadh.austhub.model.homemenumodel.HomeMenuListItem;
import com.example.sayefreyadh.austhub.ui.AboutUsActivity;
import com.example.sayefreyadh.austhub.ui.SettingsActivity;
import com.example.sayefreyadh.austhub.ui.cgpa.CalculatorActivity;
import com.example.sayefreyadh.austhub.ui.homemenu.HomeMenuActivity;
import com.example.sayefreyadh.austhub.ui.homemenu.NavActivity;
import com.example.sayefreyadh.austhub.ui.results.ResultActivity;
import com.example.sayefreyadh.austhub.ui.routine.RoutineActivity;
import com.example.sayefreyadh.austhub.ui.reminder.ReminderActivity;
import com.example.sayefreyadh.austhub.utilities.CgpaCalculator;

import java.util.List;

/**
 * Created by SayefReyadh on 12/26/2016.
 */

public class HomeMenuAdapter extends RecyclerView.Adapter<HomeMenuAdapter.AdapterHolder> {

    public Context context;
    public HomeMenuActivity activity2;
    public NavActivity activity;
    private List<HomeMenuListItem> listData;
    private LayoutInflater inflater;

    ///old constructor
    public HomeMenuAdapter(List<HomeMenuListItem> listData, Context context, HomeMenuActivity activity) {
        this.listData = listData;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.activity2 = activity;
    }

    public HomeMenuAdapter(List<HomeMenuListItem> listData, Context context, NavActivity activity) {
        this.listData = listData;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.activity = activity;
    }

    @Override
    public AdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.homemenu_list_item, parent, false);
        return new AdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterHolder holder, int position) {
        HomeMenuListItem item = listData.get(position);
        holder.title.setText(item.getTitle());
        holder.icon.setBackgroundResource(item.getImageResId());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class AdapterHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //private TextView title;
        private ImageView icon;
        private TextView title;
        private View container;

        public AdapterHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.im_item_name_string);
            icon = (ImageView) itemView.findViewById(R.id.im_item_icon);
            container = itemView.findViewById(R.id.cont_item_root);
            icon.setOnClickListener(this);
            container.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            //Toast.makeText(context, "OnClick Called at position " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
            //Intent intent = new Intent(context , TestActivity.class);
            //context.startActivity(intent);
            switch (getAdapterPosition()) {
                case 0: //routine case
                    Toast.makeText(context, "Routine", Toast.LENGTH_SHORT).show();
                    routineMethod();
                    break;
                case 1: // cgpa calc case
                    cgpaMethod();
                    Toast.makeText(context, "CGPA Calculator", Toast.LENGTH_SHORT).show();
                    break;
                case 2: // reminder case
                    Toast.makeText(context, "Reminder", Toast.LENGTH_SHORT).show();
                    reminderMethod();
                    break;
                case 3: // results case
                    resultMethod();
                    Toast.makeText(context, "Results", Toast.LENGTH_SHORT).show();

                    break;
                case 4: // settings case
                    settingsMethod();
                    Toast.makeText(context, "Settings", Toast.LENGTH_SHORT).show();
                    break;
                case 5: // about us case
                    aboutUsMethod();
                    Toast.makeText(context, "About Us", Toast.LENGTH_SHORT).show();
                    break;
                case 6:
                    Toast.makeText(context, "Choose an option please" + getAdapterPosition(), Toast.LENGTH_SHORT).show();


            }

        }

        public void resultMethod()
        {
            Intent intent = new Intent(context , ResultActivity.class);
            context.startActivity(intent);
        }

        public void aboutUsMethod()
        {
            Intent intent = new Intent(context , AboutUsActivity.class);
            context.startActivity(intent);
        }

        public void routineMethod()
        {
            Intent intent = new Intent(context , RoutineActivity.class);
            context.startActivity(intent);
        }

        public void reminderMethod()
        {
            Intent intent = new Intent(context , ReminderActivity.class);
            context.startActivity(intent);
        }

        public void reminderMethod2() {
            long startMillis = 0;
            Uri.Builder builder = CalendarContract.CONTENT_URI.buildUpon();
            builder.appendPath("time");
            ContentUris.appendId(builder, startMillis);
            Intent intent = new Intent(Intent.ACTION_VIEW)
                    .setData(builder.build());
            context.startActivity(intent);
        }


        public void cgpaMethod()
        {
            Intent intent = new Intent(context , CalculatorActivity.class);
            context.startActivity(intent);
        }

        public void settingsMethod()
        {
            Intent intent = new Intent(context , SettingsActivity.class);
            context.startActivity(intent);
        }

    }
}
