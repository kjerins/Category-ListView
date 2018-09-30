package lv.kjerins.categorylistview.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.Collections;

import lv.kjerins.categorylistview.R;
import lv.kjerins.categorylistview.adapters.ItemAdapter;
import lv.kjerins.categorylistview.viewmodel.ListItem;
import lv.kjerins.categorylistview.viewmodel.ListManager;

public class MainActivity extends AppCompatActivity implements ListManager.ListManagerEventListener, ItemAdapter.ItemClickHandler {

    private ListManager listManager;

    private ItemAdapter rvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listManager = ListManager.getInstance();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        rvAdapter = new ItemAdapter(Collections.<ListItem>emptyList(), this);
        recyclerView.setAdapter(rvAdapter);

        listManager.subscribe(this);
        listManager.loadItems();
    }

    @Override
    protected void onResume() {
        super.onResume();
        listManager.subscribe(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        listManager.unsubscribe(this);
    }

    public void loadToday(View v) {
        rvAdapter.setItems(listManager.getTodayItems());
    }

    public void loadTomorrow(View v) {
        rvAdapter.setItems(listManager.getTomorrowItems());
    }

    @Override
    public void onLoadCompleted() {
        loadToday(null);
    }

    @Override
    public void onItemClicked(View v) {
        int itemId = Integer.valueOf(v.getTag().toString());
        startActivity(ItemActivity.getLoaderIntent(this, itemId));
    }
}
