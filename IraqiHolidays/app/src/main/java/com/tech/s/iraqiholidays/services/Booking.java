package com.tech.s.iraqiholidays.services;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.tech.s.iraqiholidays.BaseUrl;
import com.tech.s.iraqiholidays.ChangeTypeface;
import com.tech.s.iraqiholidays.R;
import com.tech.s.iraqiholidays.network.MySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Booking extends AppCompatActivity {
    AppCompatSpinner dateSpin, hotelSpin, singleSpin, doubleSpin, tripleSpin;
    String pid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        TextView toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        toolbarTitle.setText("تفاصيل الحجز");

        pid = getIntent().getExtras().getString("pid");

        dateSpin = (AppCompatSpinner) findViewById(R.id.date_spinner);
        hotelSpin = (AppCompatSpinner) findViewById(R.id.hotel_spinner);
        singleSpin = (AppCompatSpinner) findViewById(R.id.single_spinner);
        doubleSpin = (AppCompatSpinner) findViewById(R.id.double_spinner);
        tripleSpin = (AppCompatSpinner) findViewById(R.id.trible_spinner);

        getInfo();

        Button actionContinue = (Button) findViewById(R.id.continue_process);
        actionContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        ChangeTypeface.setTypeface(this, toolbarTitle);
        ChangeTypeface.setTypeface(this, (findViewById(R.id.label1)));
        ChangeTypeface.setTypeface(this, (findViewById(R.id.label2)));
        ChangeTypeface.setTypeface(this, (findViewById(R.id.single_label)));
        ChangeTypeface.setTypeface(this, (findViewById(R.id.double_label)));
        ChangeTypeface.setTypeface(this, (findViewById(R.id.triple_label)));
        ChangeTypeface.setTypeface(this, (findViewById(R.id.label3)));
        ChangeTypeface.setTypeface(this, actionContinue);
    }
//
    private void getInfo() {
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, BaseUrl.getBaseUrl() + "get_package_information.php", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject ob = new JSONObject(response, null);
                            JSONArray booking = ob.getJSONArray("hotels");
                            Log.d("ressss", booking.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("err5", error.getMessage());
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("pid", pid);
                return map;
            }
        };
//        JsonArrayRequest req = new JsonArrayRequest(Request.Method.POST, BaseUrl.getBaseUrl() + "get_package_information.php", null,
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        for (int i = 0; i < response.length(); i++) {
//                            try {
//                                JSONObject ob = response.getJSONObject(i);
//                                JSONArray arr = ob.getJSONArray("hotels");
//                                Log.d("strre", arr.getString(0));
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        })
//        {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> map = new HashMap<>();
//                map.put("pid", pid);
//                return map;
//            }
//        };
//        StringRequest req = new StringRequest(Request.Method.POST, BaseUrl.getBaseUrl() + "get_package_information.php",
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Log.d("rs5", response.substring(response.indexOf("hotels"), response.lastIndexOf("days")));
//
//                        for (int i = 0; i < response.length(); i++) {
//                            JSONObject ob = response.getS
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.d("err5", error.getMessage());
//            }
//        })
//        {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> map = new HashMap<>();
//                map.put("pid", pid);
//                return map;
//            }
//        };

        MySingleton.getInstance(this).addToRequestQueue(req);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }

        return true;
    }
}
