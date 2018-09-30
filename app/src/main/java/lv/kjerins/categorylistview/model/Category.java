package lv.kjerins.categorylistview.model;

import java.util.Arrays;
import java.util.List;

public class Category {

    private String day;
    private String title;
    private List<CategoryItem> items;

    public Category(String day, String title, CategoryItem... items) {
        this.day = day;
        this.title = title;
        this.items = Arrays.asList(items);
    }

    public String getDay() {
        return day;
    }

    public String getTitle() {
        return title;
    }

    public List<CategoryItem> getItems() {
        return items;
    }
}
