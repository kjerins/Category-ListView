package lv.kjerins.categorylistview.api;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import lv.kjerins.categorylistview.model.Category;
import lv.kjerins.categorylistview.model.CategoryItem;

public final class CategoryProvider {

    public Observable<List<Category>> getCategories() {
        List<Category> categories = new ArrayList<>();

        categories.add(new Category("1", "Cat 1",
                new CategoryItem("Cat 1 item 1", 1),
                new CategoryItem("Cat 1 item 2", 2),
                new CategoryItem("Cat 1 item 3", 3)));

        categories.add(new Category("1", "Cat 2",
                new CategoryItem("Cat 2 item 1", 4),
                new CategoryItem("Cat 2 item 2", 5)));

        categories.add(new Category("1", "Cat 3",
                new CategoryItem("Cat 3 item 1", 6),
                new CategoryItem("Cat 3 item 2", 7),
                new CategoryItem("Cat 3 item 3", 8)));

        categories.add(new Category("2", "Cat 4",
                new CategoryItem("Cat 4 item 1", 9),
                new CategoryItem("Cat 4 item 2", 10)));

        categories.add(new Category("2", "Cat 5",
                new CategoryItem("Cat 5 item 1", 11),
                new CategoryItem("Cat 5 item 2", 12),
                new CategoryItem("Cat 5 item 3", 13),
                new CategoryItem("Cat 5 item 4", 14)));

        return Observable.just(categories);
    }

}
