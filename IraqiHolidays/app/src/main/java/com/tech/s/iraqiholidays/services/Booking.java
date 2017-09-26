package com.tech.s.iraqiholidays.services;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.tech.s.iraqiholidays.BaseUrl;
import com.tech.s.iraqiholidays.ChangeTypeface;
import com.tech.s.iraqiholidays.R;
import com.tech.s.iraqiholidays.model.Days;
import com.tech.s.iraqiholidays.model.Hotels;
import com.tech.s.iraqiholidays.network.MySingleton;
import com.tech.s.iraqiholidays.spinner.adapters.SpinnerNameAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Booking extends AppCompatActivity {
    AppCompatSpinner dateSpin, hotelSpin, singleSpin, doubleSpin, tripleSpin;
    String pid;
    List<Hotels> hotelsList = new ArrayList<>();
    List<Days> daysList = new ArrayList<>();
    SpinnerNameAdapter spinnerArrayAdapter;

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

        List<Integer> nums = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            nums.add(i);
        }

        ArrayAdapter<Integer> arrAdpter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, nums);
        arrAdpter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        singleSpin.setAdapter(arrAdpter);
        doubleSpin.setAdapter(arrAdpter);
        tripleSpin.setAdapter(arrAdpter);

        //hotelsList.add()
        getInfo();

        spinnerArrayAdapter = new SpinnerNameAdapter(this, android.R.layout.simple_spinner_item, hotelsList);
        hotelSpin.setAdapter(spinnerArrayAdapter);

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

    private void getInfo() {
        StringRequest req = new StringRequest(Request.Method.POST, BaseUrl.getBaseUrl() + "get_package_information.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject ob = new JSONObject(response);
                            JSONArray arr = ob.getJSONArray("hotels");
                            JSONArray arr3 = ob.getJSONArray("days");

                            for (int i = 0; i < arr.length(); i++) {
                                JSONObject obj = arr.getJSONObject(i);
                                Log.d("rs7", obj.getString("hotel"));
                                Log.d("rs8", obj.getString("rooms"));

                                String hotel = obj.getString("hotel");
                                JSONObject obj2 = new JSONObject(hotel);

                                Hotels h = new Hotels(obj2.getString("hotelid"),
                                        obj2.getString("hotelname"),
                                        obj2.getString("hotelcity"),
                                        obj2.getString("fw"),
                                        obj2.getString("pid"),
                                        obj2.getString("status"),
                                        obj2.getString("rank"),
                                        obj2.getString("extra_bed"),
                                        obj2.getString("child_bed"),
                                        obj2.getString("child_no_bed"),
                                        obj2.getString("infant"));

                                hotelsList.add(h);

                                spinnerArrayAdapter.notifyDataSetChanged();

                                JSONArray arr2 = obj.getJSONArray("rooms");

                                for (int j = 0; j < arr2.length(); j++) {
                                    JSONObject obj3 = arr2.getJSONObject(j);
                                    Log.d("rs9", obj3.getString("roomid"));
                                }
                            }

                            for (int i = 0; i < arr3.length(); i++) {
                                JSONObject obj = arr3.getJSONObject(i);
                                String id = obj.getString("id");
                                String day = obj.getString("day");
                                String fw = obj.getString("fw");
                                String pid = obj.getString("pid");
                                String period = obj.getString("period");

                                Days d = new Days(id, day, fw, pid, period);
                                daysList.add(d);
                                requestDate(daysList);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("err5", error.getMessage() != null ? error.getMessage() : "No");
                getInfo();
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

        MySingleton.getInstance(this).addToRequestQueue(req);
    }

    private void requestDate(List<Days> daysList) {
        for (final Days day : daysList) {
            StringRequest req = new StringRequest(Request.Method.POST, BaseUrl.getBaseUrl() + "get_package_information.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("rs88", response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("err6", error.getMessage() != null ? error.getMessage() : "No");
                }
            })
            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> map = new HashMap<>();
                    map.put("pid", day.getPid());
                    return map;
                }
            };

            MySingleton.getInstance(this).addToRequestQueue(req);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }

        return true;
    }
}
