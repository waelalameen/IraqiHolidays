package com.tech.s.iraqiholidays;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tech.s.iraqiholidays.model.PackInfo;
import com.tech.s.iraqiholidays.network.ApiClient;
import com.tech.s.iraqiholidays.network.ApiInterfaceInfo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity  implements SearchView.OnQueryTextListener {
    RecyclerView mRecyclerView;
    LinearLayoutManager manager;
    RecyclerView.Adapter mainAdapter;
    private List<PackInfo> list = new ArrayList<>();
    private static boolean pointer = false;
    private TextView toolbarTitle;
    private ProgressBar progressBar;
    FloatingActionButton fab;
    private static int lastFirstVisiblePosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressBar.setProgress(100);
        progressBar.setIndeterminate(true);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.requestLayout();
        setScroll();
        mRecyclerView.invalidateItemDecorations();
        mRecyclerView.setDrawingCacheEnabled(true);
        mRecyclerView.setItemViewCacheSize(20);
        mRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);

        getInfo();

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRecyclerView.smoothScrollToPosition(0);
            }
        });
    }

    private void setScroll() {
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE:
                        fab.show();

                        if (manager.findFirstCompletelyVisibleItemPosition() == 0) {
                            fab.hide();
                        }
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        final MenuItem item = menu.findItem(R.id.action_search);
//        Drawable searchIcon = menu.getItem(0).getIcon();
//        searchIcon.mutate();
//        searchIcon.setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_IN);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        TextView textView = (TextView) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        textView.setHint("ابحث عن رحلة");
        textView.setHintTextColor(Color.WHITE);
        textView.setTextColor(Color.WHITE);
        ChangeTypeface.setTypeface(this, textView);
        searchView.setQuery(searchView.getQuery(), true);
        searchView.setOnQueryTextListener(this);
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                pointer = false;
                setItemsVisibility(menu, item, true);
                toolbarTitle.setText(R.string.app_name);
                MenuItemCompat.collapseActionView(item);
                reInitiate();
                return false;
            }
        });
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setItemsVisibility(menu, item, false);
                toolbarTitle.setText("");
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                getSupportActionBar().setHomeButtonEnabled(false);
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return true;
    }

    public void getInfo() {
        ApiInterfaceInfo api = ApiClient.getClient(BaseUrl.getBaseUrl()).create(ApiInterfaceInfo.class);

        Call<List<PackInfo>> call = api.getInfo();
        call.enqueue(new Callback<List<PackInfo>>() {
            @Override
            public void onResponse(@NonNull Call<List<PackInfo>> call, @NonNull Response<List<PackInfo>> response) {
                Log.d("main_res", response.body().toString());

                if (response.body() != null) {
                    list.addAll(response.body());
                    progressBar.setVisibility(View.GONE);
                }

                mainAdapter = new MainAdapter(getApplicationContext(), list);
                mRecyclerView.setAdapter(mainAdapter);
            }

            @Override
            public void onFailure(@NonNull Call<List<PackInfo>> call, @NonNull Throwable t) {
                Log.d("main_error", t.getMessage() != null ? t.getMessage() : "NO");
            }
        });
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        //android.os.Process.killProcess(android.os.Process.myPid());
        //System.exit(1);
    }

    private void reInitiate() {
        mainAdapter = new MainAdapter(getApplicationContext(), list);
        mRecyclerView.setAdapter(mainAdapter);
    }

    private void setItemsVisibility(Menu menu, MenuItem search, boolean visible) {
        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);

            if(item != search) {
                item.setVisible(visible);
            }
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if(TextUtils.isEmpty(newText)) {
            mainAdapter.notifyDataSetChanged();
        } else {
            pointer = true;
            List<PackInfo> filtered = filter(list, newText);
            Log.d("query", newText);
            mainAdapter = new MainAdapter(getApplicationContext(), filtered);
            mRecyclerView.setAdapter(mainAdapter);
        }
        return true;
    }

    private List<PackInfo> filter(List<PackInfo> questionList, String query) {
        query = query.toLowerCase();
        List<PackInfo> filteredList = new ArrayList<>();
        if (questionList != null && questionList.size() > 0) {
            for (PackInfo question : questionList) {
                if (question.getpTo().toLowerCase().contains(query)) {
                    filteredList.add(question);
                }
            }
        }
        return filteredList;
    }

    @Override
    protected void onPause() {
        super.onPause();
        lastFirstVisiblePosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager()).findLastVisibleItemPosition();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("lastPos", String.valueOf(lastFirstVisiblePosition));
        ((LinearLayoutManager) mRecyclerView.getLayoutManager()).scrollToPositionWithOffset(lastFirstVisiblePosition, 1);
    }
}
