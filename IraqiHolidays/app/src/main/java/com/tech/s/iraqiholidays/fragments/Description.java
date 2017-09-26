package com.tech.s.iraqiholidays.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tech.s.iraqiholidays.ChangeTypeface;
import com.tech.s.iraqiholidays.R;

public class Description extends Fragment {
    private static final String TAG = "Res_Description";
    ImageView pImage;
    TextView pName, pDesc;
    private String id, name, image, detail;

    public Description() {

    }

    @SuppressLint("ValidFragment")
    public Description(String pid, String pImage, String pName, String pDetail) {
        id = pid;
        image = pImage;
        name = pName;
        detail = pDetail;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_description, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        pImage = (ImageView) view.findViewById(R.id.p_image);
        pName = (TextView) view.findViewById(R.id.p_name);
        pDesc = (TextView) view.findViewById(R.id.p_desc);

        Picasso.with(getContext()).load("http://www.visamarket.net/ihm/" + image).error(R.drawable.error_logo).into(pImage);
        pName.setText(name);
        pDesc.setText(Html.fromHtml(detail));
        //replace("&nbsp;", "").replaceAll("\\<[^>]*>","").trim()
        ChangeTypeface.setTypeface(getContext(), pName);
        ChangeTypeface.setTypeface(getContext(), pDesc);

        super.onViewCreated(view, savedInstanceState);
    }
}
