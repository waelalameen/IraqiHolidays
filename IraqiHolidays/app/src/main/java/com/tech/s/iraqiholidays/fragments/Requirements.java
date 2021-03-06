package com.tech.s.iraqiholidays.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tech.s.iraqiholidays.BaseUrl;
import com.tech.s.iraqiholidays.ChangeTypeface;
import com.tech.s.iraqiholidays.R;
import com.tech.s.iraqiholidays.model.PackInfo;
import com.tech.s.iraqiholidays.network.ApiClient;
import com.tech.s.iraqiholidays.network.ApiInterfacePackageDetails;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Requirements extends Fragment {
    private String id, image;
    ImageView pImage;
    TextView pText;

    // TODO: Rename parameter arguments, choose names that match
    public Requirements() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public Requirements(String pid, String pImage) {
        id = pid;
        image = pImage;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_requirements, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        pImage = (ImageView) view.findViewById(R.id.p_image);
        pText = (TextView) view.findViewById(R.id.p_text);

        getInfo();

        Picasso.with(getContext()).load("http://www.visamarket.net/ihm/" + image).error(R.drawable.error_logo).into(pImage);

        ChangeTypeface.setTypeface(getContext(), pText);

        super.onViewCreated(view, savedInstanceState);
    }

    private void getInfo() {
        ApiInterfacePackageDetails api = ApiClient.getClient(BaseUrl.getBaseUrl()).create(ApiInterfacePackageDetails.class);

        Call<PackInfo> call = api.getInfo(id);
        Log.d("pppid", id);
        call.enqueue(new Callback<PackInfo>() {
            @Override
            public void onResponse(@NonNull Call<PackInfo> call, @NonNull Response<PackInfo> response) {
                if (response.isSuccessful() && response.body() != null) {
                    pText.setText(Html.fromHtml(response.body().getRequirements()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<PackInfo> call, @NonNull Throwable t) {
                Log.d("errrrr", t.getMessage() != null ? t.getMessage() : "NO");
            }
        });
    }
}
