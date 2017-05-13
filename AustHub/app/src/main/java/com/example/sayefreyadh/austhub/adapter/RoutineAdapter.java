package com.example.sayefreyadh.austhub.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sayefreyadh.austhub.R;
import com.example.sayefreyadh.austhub.model.routinemodel.RoutineModel;

import java.util.List;

/**
 * Created by SayefReyadh on 1/7/2017.
 */
public class RoutineAdapter extends RecyclerView.Adapter<RoutineAdapter.RoutineHolder>{

    private List<RoutineModel> routineData;
    private LayoutInflater inflater;

    public RoutineAdapter(List<RoutineModel> routineData, Context context) {
        this.routineData = routineData;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RoutineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.routine_list, parent, false);
        return new RoutineHolder(view);
    }

    @Override
    public void onBindViewHolder(RoutineHolder holder, int position) {
        RoutineModel item = routineData.get(position);
        holder.time.setText(item.time);
        ///holder.endingTime.setText(item.endTime);
        holder.courseNumber.setText(item.courseNumber);
        holder.courseName.setText(item.courseName);
        holder.roomNumber.setText(item.roomNumber);
        holder.courseTeacher.setText(item.teachersName);

    }

    @Override
    public int getItemCount() {
        return routineData.size();
    }

    public class RoutineHolder extends RecyclerView.ViewHolder{

        ImageView thumbnail;
        TextView time;
        ///TextView endingTime;
        TextView courseNumber;
        TextView courseName;
        TextView roomNumber;
        TextView courseTeacher;

        View container;
        public RoutineHolder(View itemView) {
            super(itemView);
            thumbnail = (ImageView)itemView.findViewById(R.id.routine_icon);
            time = (TextView)itemView.findViewById(R.id.routine_time);
            ///endingTime = (TextView)itemView.findViewById(R.id.routine_ending_time);
            courseNumber = (TextView)itemView.findViewById(R.id.routine_course_number);
            courseName = (TextView)itemView.findViewById(R.id.routine_course_name);
            roomNumber = (TextView)itemView.findViewById(R.id.routine_room_number);
            courseTeacher = (TextView)itemView.findViewById(R.id.routine_course_teacher);
            container = (View)itemView.findViewById(R.id.cont_item_root);

        }
    }

}
