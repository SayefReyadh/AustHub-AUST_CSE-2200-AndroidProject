package com.example.sayefreyadh.austhub.ui.homemenu;

import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.sayefreyadh.austhub.R;


import com.example.sayefreyadh.austhub.adapter.HomeMenuAdapter;
import com.example.sayefreyadh.austhub.model.homemenumodel.HomeMenuData;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

public class HomeMenuActivity extends AppCompatActivity {

    private RecyclerView recView;
    private HomeMenuAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homemenu_list);
        recView = (RecyclerView) findViewById(R.id.rec_list2);
        //LinearManager : GridLayoutManager or StaggeredGridLayourManager
        if(getResources().getConfiguration().orientation != ORIENTATION_LANDSCAPE)
            recView.setLayoutManager(new GridLayoutManager(this , 2));
        else
            recView.setLayoutManager(new GridLayoutManager(this , 3));

        adapter = new HomeMenuAdapter(HomeMenuData.getListData() , this, this);
        recView.setAdapter(adapter);


    }






    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 1000)
        {
            if(permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this , "Permission Granted" , Toast.LENGTH_SHORT).show();
            }
        }
    }
}
