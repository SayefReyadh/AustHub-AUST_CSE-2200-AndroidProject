package com.example.sayefreyadh.austhub.ui.homemenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sayefreyadh.austhub.R;
import com.example.sayefreyadh.austhub.adapter.HomeMenuAdapter;
import com.example.sayefreyadh.austhub.database.RoutineDatabaseCRUD;
import com.example.sayefreyadh.austhub.model.homemenumodel.HomeMenuData;
import com.example.sayefreyadh.austhub.model.routinemodel.RoutineProvider;
import com.example.sayefreyadh.austhub.ui.AboutUsActivity;
import com.example.sayefreyadh.austhub.ui.SettingsActivity;
import com.example.sayefreyadh.austhub.ui.cgpa.CalculatorActivity;
import com.example.sayefreyadh.austhub.ui.reminder.ReminderActivity;
import com.example.sayefreyadh.austhub.ui.results.ResultActivity;
import com.example.sayefreyadh.austhub.ui.routine.RoutineActivity;
import com.example.sayefreyadh.austhub.user.UserData;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

public class NavActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private RecyclerView recView;
    private HomeMenuAdapter adapter;
    private ImageView userImage;
    private TextView userName;
    private TextView userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);

        addRoutineInDatabase();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ///Set the data from shared pref here in this variable shaafi

        View navHeader = navigationView.getHeaderView(0);

        userImage = (ImageView) navHeader.findViewById(R.id.nav_userimage);
        userName = (TextView) navHeader.findViewById(R.id.nav_username);
        userName.setText(UserData.getUserName(this, this));
        userEmail = (TextView) navHeader.findViewById(R.id.nav_useremail);
        userEmail.setVisibility(View.INVISIBLE);

        recView = (RecyclerView) findViewById(R.id.rec_list);
        //LinearManager : GridLayoutManager or StaggeredGridLayourManager
        if(getResources().getConfiguration().orientation != ORIENTATION_LANDSCAPE)
            recView.setLayoutManager(new GridLayoutManager(this , 2));
        else
            recView.setLayoutManager(new GridLayoutManager(this , 3));

        adapter = new HomeMenuAdapter(HomeMenuData.getListData(), this, this);
        recView.setAdapter(adapter);

    }

    private void addRoutineInDatabase() {

        RoutineDatabaseCRUD routineDatabaseCRUD = new RoutineDatabaseCRUD(this);

        if (routineDatabaseCRUD.isDatabaseEmpty()) {
            routineDatabaseCRUD.addRoutine("22C", RoutineProvider.getCSE22C());
            ///set all semester routine here
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_routine) {
            
        } else if (id == R.id.nav_cgpa) {

        } else if (id == R.id.nav_reminder) {

        } else if (id == R.id.nav_results) {

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_aboutus) {

        }

        switch (id) {
            case R.id.nav_routine: //routine case
                Toast.makeText(this, "Routine", Toast.LENGTH_SHORT).show();
                routineMethod();
                break;
            case R.id.nav_cgpa: // cgpa calc case
                cgpaMethod();
                Toast.makeText(this, "CGPA Calculator", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_reminder: // reminder case
                Toast.makeText(this, "Reminder", Toast.LENGTH_SHORT).show();
                reminderMethod();
                break;
            case R.id.nav_results: // results case
                resultMethod();
                Toast.makeText(this, "Results", Toast.LENGTH_SHORT).show();

                break;
            case R.id.nav_settings: // settings case
                settingsMethod();
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_aboutus: // about us case
                aboutUsMethod();
                Toast.makeText(this, "About Us", Toast.LENGTH_SHORT).show();
                break;
            case 6:
                Toast.makeText(this, "Choose an option please" + id, Toast.LENGTH_SHORT).show();


        }
        
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    public void resultMethod()
    {
        Intent intent = new Intent(this , ResultActivity.class);
        startActivity(intent);
    }

    public void aboutUsMethod()
    {
        Intent intent = new Intent(this , AboutUsActivity.class);
        startActivity(intent);
    }

    public void routineMethod()
    {
        Intent intent = new Intent(this , RoutineActivity.class);
        startActivity(intent);
    }

    public void reminderMethod()
    {
        Intent intent = new Intent(this , ReminderActivity.class);
        startActivity(intent);
    }

    public void cgpaMethod()
    {
        Intent intent = new Intent(this , CalculatorActivity.class);
        startActivity(intent);
    }

    public void settingsMethod()
    {
        Intent intent = new Intent(this , SettingsActivity.class);
        startActivity(intent);
    }
}
