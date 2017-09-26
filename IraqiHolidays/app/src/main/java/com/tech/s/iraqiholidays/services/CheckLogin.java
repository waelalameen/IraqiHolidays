package com.tech.s.iraqiholidays.services;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.tech.s.iraqiholidays.BaseUrl;
import com.tech.s.iraqiholidays.MainActivity;
import com.tech.s.iraqiholidays.R;
import com.tech.s.iraqiholidays.network.ApiInterfaceCheckLogin;
import com.tech.s.iraqiholidays.network.ApiStringClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckLogin extends AppCompatActivity {
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_login);

        sharedPreferences = getSharedPreferences("sid_preferences", 0);
        String sid = sharedPreferences.getString("sid", "");
        Log.d("sid", sid);

        checkLogin(sid);
    }

    private void checkLogin(String sid) {
        ApiInterfaceCheckLogin api = ApiStringClient.getClient(BaseUrl.getBaseUrl()).create(ApiInterfaceCheckLogin.class);

        Call<String> call = api.postInfo(sid);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                Log.d("response", response.body());

                if (isNumeric(response.body().substring(1, 3))) {
                    SharedPreferences preferences = getSharedPreferences("uid_preferences", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("uid", response.body().substring(1, 3));
                    editor.apply();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                } else {
                    startActivity(new Intent(getApplicationContext(), Login.class));
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                Log.d("response", t.getMessage());
            }
        });
    }

    private boolean isNumeric(String value) {
        return value.matches("^[0-9]*$");
    }
}
