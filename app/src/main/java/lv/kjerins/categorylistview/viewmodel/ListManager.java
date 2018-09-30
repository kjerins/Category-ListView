package lv.kjerins.categorylistview.viewmodel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;
import lv.kjerins.categorylistview.api.CategoryProvider;
import lv.kjerins.categorylistview.model.Category;
import lv.kjerins.categorylistview.model.CategoryItem;

public class ListManager {

    private static ListManager instance;

    private CategoryProvider provider;

    private List<ListItem> todayItems;
    private List<ListItem> tomorrowItems;

    private List<ListManagerEventListener> listeners;

    public static ListManager getInstance() {
        if (instance == null) {
            instance = new ListManager();
        }
        return instance;
    }

    private ListManager() {
        this.provider = new CategoryProvider();
        todayItems = new ArrayList<>();
        tomorrowItems = new ArrayList<>();
        listeners = new ArrayList<>();
    }

    public void subscribe(ListManagerEventListener listener) {
        if (!this.listeners.contains(listener)) {
            this.listeners.add(listener);
        }
    }

    public void unsubscribe(ListManagerEventListener listener) {
        this.listeners.remove(listener);
    }

    public void loadItems() {
        provider.getCategories().subscribe(new Consumer<List<Category>>() {
            @Override
            public void accept(List<Category> categories) throws Exception {
                for (Category category : categories) {
                    if ("1".equals(category.getDay())) {
                        todayItems.add(new ListItem(category));
                        for (CategoryItem categoryItem : category.getItems()) {
                            todayItems.add(new ListItem(categoryItem));
                        }
                    } else {
                        tomorrowItems.add(new ListItem(category));
                        for (CategoryItem categoryItem : category.getItems()) {
                            tomorrowItems.add(new ListItem(categoryItem));
                        }
                    }
                }
                loadCompleted();
            }
        });
    }

    private void loadCompleted() {
        for (ListManagerEventListener listener : listeners) {
            listener.onLoadCompleted();
        }
    }

    public List<ListItem> getTodayItems() {
        return todayItems;
    }

    public List<ListItem> getTomorrowItems() {
        return tomorrowItems;
    }

    public CategoryItem getCategoryItemById(int id) {
        for (ListItem listItem : todayItems) {
            if (listItem.categoryItem != null && listItem.categoryItem.getId() == id) {
                return listItem.categoryItem;
            }
        }
        for (ListItem listItem : tomorrowItems) {
            if (listItem.categoryItem != null && listItem.categoryItem.getId() == id) {
                return listItem.categoryItem;
            }
        }
        return null;
    }

    public interface ListManagerEventListener {
        void onLoadCompleted();
    }
}
