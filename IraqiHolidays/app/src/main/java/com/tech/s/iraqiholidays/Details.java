package com.tech.s.iraqiholidays;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.tech.s.iraqiholidays.fragments.Description;
import com.tech.s.iraqiholidays.fragments.Include;
import com.tech.s.iraqiholidays.fragments.NotInclude;
import com.tech.s.iraqiholidays.fragments.Requirements;
import com.tech.s.iraqiholidays.services.Booking;

public class Details extends AppCompatActivity {
    ViewPager viewPager;
    String pid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        pid = getIntent().getExtras().getString("pid");
        String pImage = getIntent().getExtras().getString("p_image");
        String pName = getIntent().getExtras().getString("p_name");
        String pDetail = getIntent().getExtras().getString("p_detail");
        String pPrice = getIntent().getExtras().getString("p_price");

        viewPager = (ViewPager) findViewById(R.id.view_pager);

        TabLayout tabLayout = (TabLayout)findViewById(R.id.main_tab);

        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragments(new Description(pid, pImage, pName, pDetail, pPrice), getString(R.string.description));
        pagerAdapter.addFragments(new NotInclude(pid, pImage), getString(R.string.not_include));
        pagerAdapter.addFragments(new Include(pid, pImage), getString(R.string.include));
        pagerAdapter.addFragments(new Requirements(pid, pImage), getString(R.string.requirements));

        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(4);
        tabLayout.setupWithViewPager(viewPager);

        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        for (int i = 0; i < vg.getChildCount(); i++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(i);
            for (int j = 0; j < vgTab.getChildCount(); j++) {
                View tabViewChild = vgTab.getChildAt(j);
                if (tabViewChild instanceof TextView) {
                    ChangeTypeface.setTypeface(this, (tabViewChild));
                }
            }
        }

        Button checkOut = (Button) findViewById(R.id.check_out);
        ChangeTypeface.setTypeface(this, checkOut);
        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Booking.class);
                intent.putExtra("pid", pid);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }
}
