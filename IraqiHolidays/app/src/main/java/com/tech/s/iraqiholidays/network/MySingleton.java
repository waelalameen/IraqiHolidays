package com.tech.s.iraqiholidays.network;

import android.annotation.SuppressLint;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MySingleton {
    private Context context;
    @SuppressLint("StaticFieldLeak")
    private static MySingleton ourInstance;
    private RequestQueue requestQueue;

    private MySingleton(Context context) {
        this.context = context;
        requestQueue = getRequestQueue();
    }

    private RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context);
        }

        return requestQueue;
    }

    public static synchronized MySingleton getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new MySingleton(context);
        }

        return ourInstance;
    }

    public<T> void addToRequestQueue(Request<T> request) {
        requestQueue.add(request);
    }
}
