package com.example.e_itmedi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.e_itmedi.Authentication.LogInActivity;
import com.example.e_itmedi.Authentication.SignUpActivity;

public class FirstActivity extends AppCompatActivity {
    Button buttonjn, buttonlg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_first);
        buttonjn = findViewById(R.id.JoinButton_id);
        buttonlg = findViewById(R.id.LoginButton_id);
        buttonjn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
        buttonlg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, LogInActivity.class);
                startActivity(intent);
            }
        });
    }
}