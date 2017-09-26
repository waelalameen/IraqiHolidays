package com.tech.s.iraqiholidays.services;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tech.s.iraqiholidays.BaseUrl;
import com.tech.s.iraqiholidays.ChangeTypeface;
import com.tech.s.iraqiholidays.MainActivity;
import com.tech.s.iraqiholidays.R;
import com.tech.s.iraqiholidays.model.LoginInfo;
import com.tech.s.iraqiholidays.network.ApiClient;
import com.tech.s.iraqiholidays.network.ApiInterfaceLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    TextView title, actionRecovery, actionRegister;
    EditText mEmail, mPassword;
    Button actionLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        title = (TextView) findViewById(R.id.title);
        actionRecovery = (TextView) findViewById(R.id.action_recovery);
        actionRegister = (TextView) findViewById(R.id.action_register);
        mEmail = (EditText) findViewById(R.id.email);
        mPassword = (EditText) findViewById(R.id.password);
        actionLogin = (Button) findViewById(R.id.action_login);
        actionLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && (!TextUtils.isEmpty(email) || !TextUtils.isEmpty(password))) {
                    sendRequest(email, password);
                } else {
                    Snackbar.make(view, R.string.empty_fields_message, Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        (findViewById(R.id.action_register)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Register.class));
            }
        });

        setFonts();
    }

    private void sendRequest(String email, String password) {
        ApiInterfaceLogin apiInterfaceLogin = ApiClient.getClient(BaseUrl.getBaseUrl()).create(ApiInterfaceLogin.class);

        Call<LoginInfo> call = apiInterfaceLogin.postInfo(email, password);
        call.enqueue(new Callback<LoginInfo>() {
            @Override
            public void onResponse(@NonNull Call<LoginInfo> call, @NonNull Response<LoginInfo> response) {
                Log.d("response", response.body().getId());

                if (response.isSuccessful()) {
                    SharedPreferences preferences = getSharedPreferences("sid_preferences", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("sid", response.body().getId());
                    editor.apply();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginInfo> call, @NonNull Throwable t) {
                Log.d("error", t.getMessage());
            }
        });
    }

    private void setFonts() {
        ChangeTypeface.setTypeface(this, title);
        ChangeTypeface.setTypeface(this, actionRecovery);
        ChangeTypeface.setTypeface(this, actionRegister);
        ChangeTypeface.setTypeface(this, mEmail);
        ChangeTypeface.setTypeface(this, mPassword);
        ChangeTypeface.setTypeface(this, actionLogin);
    }
}
