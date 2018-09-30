package lv.kjerins.categorylistview.model;

public class CategoryItem {

    private String itemName;
    private int id;

    public CategoryItem(String itemName, int id) {
        this.itemName = itemName;
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public int getId() {
        return id;
    }
}
