package lv.kjerins.categorylistview.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import lv.kjerins.categorylistview.R;
import lv.kjerins.categorylistview.model.CategoryItem;
import lv.kjerins.categorylistview.viewmodel.ListItem;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    // dummy superclass only used because Adapter must have one viewholder type parameter
    static class ItemViewHolder extends RecyclerView.ViewHolder {

        ItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    static class CategoryViewHolder extends ItemViewHolder {

        TextView categoryText;

        CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryText = itemView.findViewById(R.id.categoryText);
        }
    }

    static class CategoryItemViewHolder extends ItemViewHolder {

        TextView itemText;

        CategoryItemViewHolder(@NonNull View itemView) {
            super(itemView);
            itemText = itemView.findViewById(R.id.itemText);
        }
    }

    public interface ItemClickHandler {
        void onItemClicked(View v);
    }

    private static final int CATEGORY = 1;
    private static final int CATEGORY_ITEM = 2;

    private List<ListItem> itemList;

    private ItemClickHandler itemClickHandler;

    public ItemAdapter(List<ListItem> items, ItemClickHandler itemClickHandler) {
        this.itemList = items;
        this.itemClickHandler = itemClickHandler;
    }

    public void setItems(List<ListItem> items) {
        this.itemList = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        ListItem item = itemList.get(position);
        if (item.category != null) {
            return CATEGORY;
        } else {
            return CATEGORY_ITEM;
        }
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int itemType) {
        if (itemType == CATEGORY) {
            return new CategoryViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_category, viewGroup, false));
        } else {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_item, viewGroup, false);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickHandler.onItemClicked(v);
                }
            });
            return new CategoryItemViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int position) {
        int itemType = getItemViewType(position);
        if (itemType == CATEGORY) {
            String categoryText = itemList.get(position).category.getTitle();
            ((CategoryViewHolder) itemViewHolder).categoryText.setText(categoryText);
        } else {
            CategoryItem item = itemList.get(position).categoryItem;
            String itemText = item.getItemName();
            ((CategoryItemViewHolder) itemViewHolder).itemText.setText(itemText);
            itemViewHolder.itemView.setTag(item.getId());
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
