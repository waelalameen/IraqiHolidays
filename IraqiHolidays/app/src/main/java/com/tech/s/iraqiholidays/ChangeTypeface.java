package com.tech.s.iraqiholidays;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class ChangeTypeface {
    public static void setTypeface(Context context, View view) {
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "jannal.ttf");

        if (view instanceof TextView || view instanceof EditText || view instanceof Button || view instanceof RadioButton) {
            ((TextView) view).setTypeface(typeface);
        }
    }
}
