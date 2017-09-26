package com.tech.s.iraqiholidays.services;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tech.s.iraqiholidays.BaseUrl;
import com.tech.s.iraqiholidays.ChangeTypeface;
import com.tech.s.iraqiholidays.MainActivity;
import com.tech.s.iraqiholidays.R;
import com.tech.s.iraqiholidays.model.RegisterInfo;
import com.tech.s.iraqiholidays.network.ApiClient;
import com.tech.s.iraqiholidays.network.ApiInterfaceRegister;

import java.util.List;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {
    TextView title, genderTitle, noteText;
    EditText mFirstName, mFatherGrandPa, mEmail, mPhoneNumber, mPassword, mPassword2;
    Button actionSignUp;
    CheckBox checkBox;
    RadioGroup radioGroup;
    RadioButton radioMale, radioFemale;
    int gender = 1;
    int agree = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        title = (TextView) findViewById(R.id.title);
        genderTitle = (TextView) findViewById(R.id.gender_title);
        noteText = (TextView) findViewById(R.id.note_text);

        mFirstName = (EditText) findViewById(R.id.first_name);
        mFatherGrandPa = (EditText) findViewById(R.id.father_grandpa_name);
        mEmail = (EditText) findViewById(R.id.email);
        mPhoneNumber = (EditText) findViewById(R.id.phone_number);
        mPassword = (EditText) findViewById(R.id.password);
        mPassword2 = (EditText) findViewById(R.id.password2);
        checkBox = (CheckBox) findViewById(R.id.checkbox);

        radioGroup = (RadioGroup) findViewById(R.id.radio_group);

        radioMale = (RadioButton) findViewById(R.id.radio_male);
        radioFemale = (RadioButton) findViewById(R.id.radio_female);

        radioMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (radioMale.isChecked()) {
                    gender = 1;
                    radioMale.setChecked(true);
                    radioFemale.setChecked(false);
                    Log.d("gender", String.valueOf(gender));
                } else {
                    gender = 1;
                    radioMale.setChecked(false);
                    radioFemale.setChecked(true);
                    Log.d("gender", String.valueOf(gender));
                }
            }
        });

        radioFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (radioFemale.isChecked()) {
                    gender = 2;
                    radioFemale.setChecked(true);
                    radioMale.setChecked(false);
                    Log.d("gender", String.valueOf(gender));
                } else {
                    gender = 1;
                    radioFemale.setChecked(false);
                    radioMale.setChecked(true);
                    Log.d("gender", String.valueOf(gender));
                }
            }
        });

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox.isChecked()) {
                    agree = 1;
                    Log.d("agree", String.valueOf(agree));
                } else {
                    agree = 0;
                    Log.d("agree", String.valueOf(agree));
                }
            }
        });

        actionSignUp = (Button) findViewById(R.id.action_signup);
        actionSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = mFirstName.getText().toString().trim();
                String lastName = mFatherGrandPa.getText().toString().trim();
                String email = mEmail.getText().toString().trim();
                String phoneNumber = mPhoneNumber.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String password2 = mPassword2.getText().toString().trim();
                int isCompany = 2;

                if (!TextUtils.isEmpty(firstName) && !TextUtils.isEmpty(lastName) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(phoneNumber) &&
                    !TextUtils.isEmpty(password) && !TextUtils.isEmpty(password2) && (!TextUtils.isEmpty(firstName) || !TextUtils.isEmpty(lastName)
                            || !TextUtils.isEmpty(email) || !TextUtils.isEmpty(phoneNumber) || !TextUtils.isEmpty(password) ||
                            !TextUtils.isEmpty(password2))) {
                    if (password.equalsIgnoreCase(password2)) {
                        if (agree == 1) {
                            if (isValidEmail(email)) {
                                if (isValidPhoneNumber(phoneNumber)) {
                                    sendRequest(firstName, lastName, email, password, agree, isCompany, gender, phoneNumber);
                                } else {
                                    Snackbar.make(view, R.string.invalid_phone_number, Snackbar.LENGTH_SHORT).show();
                                }
                            } else {
                                Snackbar.make(view, R.string.invalid_email_message, Snackbar.LENGTH_SHORT).show();
                            }
                        } else {
                            Snackbar.make(view, R.string.terms_service_message, Snackbar.LENGTH_SHORT).show();
                        }
                    } else {
                        Snackbar.make(view, R.string.password_not_match_message, Snackbar.LENGTH_SHORT).show();
                    }
                } else {
                    Snackbar.make(view, R.string.empty_fields_message, Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        setFonts();
    }

    private void sendRequest(String firstName, String lastName, String email, String password, int agree, int isCompany, int gender,
                             String phone) {
        int phoneNumber = 0;

        if (!TextUtils.isEmpty(phone)) {
            phoneNumber = Integer.parseInt(phone);
        } else {
            phoneNumber = 0;
        }

        ApiInterfaceRegister apiInterfaceRegister = ApiClient.getClient(BaseUrl.getBaseUrl()).create(ApiInterfaceRegister.class);

        Call<List<RegisterInfo>> call = apiInterfaceRegister.postInfo(firstName, lastName, email, password, agree, isCompany, gender,
                phoneNumber, "success");
        call.enqueue(new Callback<List<RegisterInfo>>() {
            @Override
            public void onResponse(@NonNull Call<List<RegisterInfo>> call, @NonNull Response<List<RegisterInfo>> response) {
                if (response.isSuccessful()) {
                    Log.d("Success", new Gson().toJson(response.body()));
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                } else {
                    Log.d("unSuccess", new Gson().toJson(response.errorBody()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<RegisterInfo>> call, @NonNull Throwable t) {
                Log.d("error", t.getMessage());
            }
        });
    }

    private void setFonts() {
        ChangeTypeface.setTypeface(this, title);
        ChangeTypeface.setTypeface(this, genderTitle);
        ChangeTypeface.setTypeface(this, radioMale);
        ChangeTypeface.setTypeface(this, radioFemale);
        ChangeTypeface.setTypeface(this, noteText);
        ChangeTypeface.setTypeface(this, mFirstName);
        ChangeTypeface.setTypeface(this, mFatherGrandPa);
        ChangeTypeface.setTypeface(this, mEmail);
        ChangeTypeface.setTypeface(this, mPhoneNumber);
        ChangeTypeface.setTypeface(this, mPassword);
        ChangeTypeface.setTypeface(this, mPassword2);
        ChangeTypeface.setTypeface(this, actionSignUp);
    }

    private boolean isValidEmail(String email){
        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }

    private boolean isValidPhoneNumber(CharSequence phoneNumber) {
        return !TextUtils.isEmpty(phoneNumber) && Patterns.PHONE.matcher(phoneNumber).matches();
    }
}
