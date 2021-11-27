package com.example.e_itmedi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.e_itmedi.Database.DataResponse;
import com.example.e_itmedi.Database.DatabaseHelper;
import com.example.e_itmedi.Database.InsertDataActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    RecyclerView recyclerView;
    Button buttonMybag;
    private static final String TAG = "MainActivity";
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar_id);
        recyclerView = findViewById(R.id.recycler);
        buttonMybag = findViewById(R.id.button_nyBag);
        navigationView = findViewById(R.id.nav_id);
        drawerLayout = findViewById(R.id.drawer_id);

        buttonMybag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });

        setSupportActionBar(toolbar);
        setTitle("");
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.grey));
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.Profile_ID:

                        Toast.makeText(MainActivity.this, "Profile", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menuSetting_id:

                        Toast.makeText(MainActivity.this, "Settings", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.menuAdmin_id:

                        startActivity(new Intent(MainActivity.this, InsertDataActivity.class));
                        break;
                }
                return true;
            }
        });

        Display();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Display();
    }

    public void Display() {
        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        Cursor cursor = databaseHelper.dsiplayData();
        ArrayList<DataResponse> Rdata = loadData(cursor);

        CustomAdapter customAdapter = new CustomAdapter(MainActivity.this, Rdata);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public ArrayList<DataResponse> loadData(Cursor cursor) {

        try {
            ArrayList<DataResponse> dataList = new ArrayList();

            if (cursor.getCount() == 0) {

            } else {
                while (cursor.moveToNext()) {

                    DataResponse dataResponse = new DataResponse();
                    dataResponse.setIID(cursor.getString(0));
                    dataResponse.setTT(cursor.getString(1));
                    dataResponse.setDD(cursor.getString(2));
                    dataResponse.setPP(cursor.getString(3));
                    dataList.add(dataResponse);
                }
            }
            return dataList;

        } catch (Exception e) {
            return null;
        }
    }
}