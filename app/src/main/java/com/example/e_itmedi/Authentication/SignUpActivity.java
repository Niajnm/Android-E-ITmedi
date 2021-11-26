package com.example.e_itmedi.Authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.e_itmedi.Authentication.SignUp.ResponseData;
import com.example.e_itmedi.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    EditText sname, smail, spass, sconfirmPass;
    Button buttonSign;
    private static final String TAG = "SignUpActivity";
    TextView memberLog;

    String email,password,cpassword,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        memberLog = findViewById(R.id.textView_loginMember);
        sname = findViewById(R.id.editTextSign_name);
        smail = findViewById(R.id.editTextSign_EmailAddress);
        spass = findViewById(R.id.editTextText_SignPass_id);
        sconfirmPass = findViewById(R.id.editTextText_SignPassConfirm_id);
        buttonSign = findViewById(R.id.buttonSign_id);
        memberLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LogInActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buttonSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               validationCheck();
//                RegisterRequest registerRequest = new RegisterRequest();
//                registerRequest.setEmail(smail.getText().toString());
//                registerRequest.setName(sname.getText().toString());
//                registerRequest.setPassword(spass.getText().toString());
//                registerRequest.setC_password(sconfirmPass.getText().toString());
                signUp();
            }
        });
    }
    public  void validationCheck(){
        name=sname.getText().toString();
        email=smail.getText().toString();
        password=spass.getText().toString();
        cpassword=sconfirmPass.getText().toString();

        if (name.isEmpty()) {
            sname.setError("Enter a name");
            sname.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            smail.setError("Enter an email address");
            smail.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            smail.setError("Enter a valid email address");
            smail.requestFocus();
            return;
        }

        //checking the validity of the password
        if (password.isEmpty()) {
            spass.setError("Enter a password");
            spass.requestFocus();
            return;
        }

        if (password.length() < 6) {
            spass.setError("Password Length Must be 6 Digits");
            spass.requestFocus();
            return;

        }  if (!cpassword.equals(password)) {
            sconfirmPass.setError("Password mismatched !");
            sconfirmPass.requestFocus();
            return;

        }
    }


    public void signUp() {
        Call<ResponseData> reqcall = APIClient.ATservice().registeruser(email, password, cpassword, name);

        reqcall.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.body().getSuccess().getName());
                    Log.d(TAG, "onResponse: " + response.body().getSuccess().getToken());
                    Toast.makeText(SignUpActivity.this, "Success....1", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, "error " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}