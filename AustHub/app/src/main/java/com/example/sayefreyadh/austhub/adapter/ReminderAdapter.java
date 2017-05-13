package com.example.sayefreyadh.austhub.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sayefreyadh.austhub.R;
import com.example.sayefreyadh.austhub.model.remindermodel.ReminderItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SayefReyadh on 12/28/2016.
 */

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.ReminderHolder>{

    private List<ReminderItem> listData;
    private LayoutInflater inflater;

    private ItemClickCallback itemClickCallback;

    public interface ItemClickCallback {
        void onItemClick(int p);
        void onDeleteIconClick(int p);
    }

    public void setItemClickCallback(final ItemClickCallback itemClickCallback) {
        this.itemClickCallback = itemClickCallback;
    }

    public ReminderAdapter(List<ReminderItem> listData, Context c){
        inflater = LayoutInflater.from(c);
        this.listData = listData;
    }

    public void setListData(ArrayList<ReminderItem> exerciseList) {
        this.listData.clear();
        this.listData.addAll(exerciseList);
    }

    @Override
    public ReminderHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.reminder_list_item, parent, false);
        return new ReminderHolder(view);
    }

    @Override
    public void onBindViewHolder(ReminderHolder holder, int position) {
        ReminderItem item = listData.get(position);
        holder.subject.setText(item.getSubject());
        holder.details.setText(item.getDetails());
        holder.date.setText(item.getDate());
        holder.time.setText(item.getTime());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class ReminderHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView icon;
        ImageView deleteIcon;
        TextView subject;
        TextView details;
        TextView date;
        TextView time;
        View container;

        public ReminderHolder(View itemView) {
            super(itemView);
            icon = (ImageView)itemView.findViewById(R.id.reminder_icon);
            deleteIcon = (ImageView)itemView.findViewById(R.id.reminder_delete_icon);
            deleteIcon.setOnClickListener(this);
            details = (TextView)itemView.findViewById(R.id.reminder_details_list_item);
            subject = (TextView)itemView.findViewById(R.id.reminder_subject_list_item);
            date = (TextView)itemView.findViewById(R.id.reminder_date_list_item);
            time = (TextView)itemView.findViewById(R.id.reminder_time_list_item);
            container = itemView.findViewById(R.id.reminder_item_root);
            container.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.reminder_item_root){
                itemClickCallback.onItemClick(getAdapterPosition());
            } else {
                itemClickCallback.onDeleteIconClick(getAdapterPosition());
            }
        }
    }
}