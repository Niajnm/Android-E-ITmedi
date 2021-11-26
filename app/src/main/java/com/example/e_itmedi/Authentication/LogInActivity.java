package com.example.e_itmedi.Authentication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_itmedi.Login.LoginResponse;
import com.example.e_itmedi.MainActivity;
import com.example.e_itmedi.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogInActivity extends AppCompatActivity {
    TextView textViewCreatAc;
    EditText textLoginmail,textLoginpass;
    Button loginbutton;

    String email,pass;
    private static final String TAG = "LogInActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        textViewCreatAc = findViewById(R.id.textView_createAcc_id);
        textLoginmail= findViewById(R.id.editTextEmailAddress_Login);
        textLoginpass= findViewById(R.id.editTextPassword_Login);
        loginbutton=findViewById(R.id.button_Login);

        textViewCreatAc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(LogInActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              validationCheck();
//                loginRequest.setEmail(textLoginmail.getText().toString());
//                loginRequest.setPassword(textLoginpass.getText().toString());
                logIn();

            }
        });
    }

        public  void validationCheck(){
            email=textLoginmail.getText().toString();
            pass=textLoginpass.getText().toString();

            if (email.isEmpty()) {
                textLoginmail.setError("Enter an email address");
                textLoginmail.requestFocus();
                return;
            }

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                textLoginmail.setError("Enter a valid email address");
                textLoginmail.requestFocus();
                return;
            }

            //checking the validity of the password
            if (pass.isEmpty()) {
                textLoginpass.setError("Enter a password");
                textLoginpass.requestFocus();
                return;
            }

            if (pass.length() < 6) {

                textLoginpass.setError("Password Length Must be 6 Digits");
                textLoginpass.requestFocus();
                return;


            }}

    public void logIn() {

        Call<LoginResponse> reqcall = APIClient.ATservice().logijUser(email,pass);

        reqcall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()){
                    Log.d(TAG, "onResponse: " + response.body().getSuccess().getToken());
                    Toast.makeText(LogInActivity.this, "Success....1", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LogInActivity.this, MainActivity.class));
                    finish();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                Toast.makeText(LogInActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}