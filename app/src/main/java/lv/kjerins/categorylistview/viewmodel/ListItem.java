package lv.kjerins.categorylistview.viewmodel;

import lv.kjerins.categorylistview.model.Category;
import lv.kjerins.categorylistview.model.CategoryItem;

// container for either a Category or a CategoryItem
public class ListItem {

    public final Category category;
    public final CategoryItem categoryItem;

    ListItem(Category category) {
        this.category = category;
        this.categoryItem = null;
    }

    ListItem(CategoryItem item) {
        this.category = null;
        this.categoryItem = item;
    }
}
