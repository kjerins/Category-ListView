package lv.kjerins.categorylistview.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import lv.kjerins.categorylistview.R;
import lv.kjerins.categorylistview.model.CategoryItem;
import lv.kjerins.categorylistview.viewmodel.ListManager;

public class ItemActivity extends AppCompatActivity {

    private static final String INTENT_KEY_ID = "id";

    public static Intent getLoaderIntent(Context context, int itemId) {
        Intent intent = new Intent(context, ItemActivity.class);
        intent.putExtra(INTENT_KEY_ID, itemId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        Intent loaderIntent = getIntent();
        if (loaderIntent != null) {
            int id = loaderIntent.getIntExtra(INTENT_KEY_ID, 0);

            ListManager listManager = ListManager.getInstance();

            CategoryItem categoryItem = listManager.getCategoryItemById(id);
            if (categoryItem != null) {
                TextView textView = findViewById(R.id.loadedItemText);
                textView.setText(categoryItem.getItemName());
            }
        }
    }

}
