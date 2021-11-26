package com.example.e_itmedi.Database;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.e_itmedi.R;

public class InsertDataActivity extends AppCompatActivity {
    EditText textTitle, textID, textPrice, textDetails;
    Button buttonadd;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);


        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        textID = findViewById(R.id.edittextID_id);
        textTitle = findViewById(R.id.edittextTitle_id);
        textPrice = findViewById(R.id.edittextprice_id);
        textDetails = findViewById(R.id.edittextDtails_id);
        buttonadd = findViewById(R.id.buttonAdd_id);


        buttonadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = textTitle.getText().toString();
                String id = textID.getText().toString();
                String price = textPrice.getText().toString();
                String details = textDetails.getText().toString();

              long rowid=  databaseHelper.insertData(title,id,price,details);
              
              if (rowid > -1){

                  Toast.makeText(InsertDataActivity.this, "Serial :"+rowid+" Added", Toast.LENGTH_SHORT).show();
                  textID.setText("");
                  textTitle.setText("");
                  textPrice.setText("");
                  textDetails.setText("");

              }
              else {


                  Toast.makeText(InsertDataActivity.this, "Insert Error ", Toast.LENGTH_SHORT).show();
              }

            }
        });

    }
}