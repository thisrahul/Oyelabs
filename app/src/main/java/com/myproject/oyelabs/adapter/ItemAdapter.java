package com.myproject.oyelabs.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import com.myproject.oyelabs.databinding.GroceriesBinding;
import com.myproject.oyelabs.databinding.OfferLayoutBinding;
import com.myproject.oyelabs.interfaces.ItemClickListener;
import com.myproject.oyelabs.model.Grocery;
import com.myproject.oyelabs.model.Product;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    //layout type constant variables
    public final static int EXCLUSIVE_OFFER = 0;
    public final static int GROCERIES = 1;
    public final static int BEST_SELLING = 2;
    private final ArrayList<Product> list;
    private final ArrayList<Grocery> groceriesList;
    private final int layoutType;

    private final ItemClickListener itemClickListener;

    //constructor
    public ItemAdapter(ArrayList<Product> list, ArrayList<Grocery> groceriesList, int layoutType, ItemClickListener itemClickListener) {
        this.list = list;
        this.layoutType = layoutType;
        this.groceriesList = groceriesList;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewBinding binding = null;
        if (layoutType == EXCLUSIVE_OFFER) {
            //set layout with recyclerview if layout type is EXCLUSIVE_OFFER
            binding = OfferLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        } else if (layoutType == GROCERIES) {
            //set layout with recyclerview if layout type is GROCERIES
            binding = GroceriesBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        } else if (layoutType == BEST_SELLING) {
            //set layout with recyclerview if layout type is BEST_SELLING
            binding = OfferLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        }

        return new ItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ItemViewHolder holder, int position) {
        //bind data to according to their item layout type
        if (layoutType == EXCLUSIVE_OFFER) {
            Product product = list.get(position);
            holder.bindDataInExclusiveOffer(product, itemClickListener, position, true);
        } else if (layoutType == GROCERIES) {
            Grocery grocery = groceriesList.get(position);
            holder.bindDataInGroceries(grocery, itemClickListener, position);
        } else if (layoutType == BEST_SELLING) {
            Product product = list.get(position);
            holder.bindDataInExclusiveOffer(product, itemClickListener, position, false);
        }
    }

    @Override
    public int getItemCount() {

        //return size of list according to their layout type list size
        if (layoutType == EXCLUSIVE_OFFER) {
            try {
                return list.size();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (layoutType == GROCERIES) {
            try {
                return groceriesList.size();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (layoutType == BEST_SELLING) {
            try {
                return list.size();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return 0;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        private final ViewBinding binding;
        private int count = 1;

        //constructor
        public ItemViewHolder(@NonNull ViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        //set data and listener in exclusive offer and best selling item layout
        public void bindDataInExclusiveOffer(Product product, ItemClickListener itemClickListener, int position, boolean hasSubTitle) {
            ((OfferLayoutBinding) binding).imgItem.setImageResource(product.getImage());
            ((OfferLayoutBinding) binding).txtTitle.setText(product.getTitle());
            if (hasSubTitle) {
                ((OfferLayoutBinding) binding).txtSubTitle.setText(product.getSubTitle());
            } else {
                ((OfferLayoutBinding) binding).txtSubTitle.setVisibility(View.GONE);
            }
            ((OfferLayoutBinding) binding).txtPrice.setText(product.getPrice());

            ((OfferLayoutBinding) binding).imgAdd.setOnClickListener(v -> {
                ((OfferLayoutBinding) binding).imgAdd.setVisibility(View.GONE);
                ((OfferLayoutBinding) binding).rlCounter.setVisibility(View.VISIBLE);
                ((OfferLayoutBinding) binding).txtCount.setText("1");
            });


            ((OfferLayoutBinding) binding).imgPlus.setOnClickListener(v -> ((OfferLayoutBinding) binding).txtCount.setText(String.valueOf(++count)));

            ((OfferLayoutBinding) binding).imgMinus.setOnClickListener(v -> {
                if (count > 1) {
                    ((OfferLayoutBinding) binding).txtCount.setText(String.valueOf(--count));
                } else {
                    ((OfferLayoutBinding) binding).imgAdd.setVisibility(View.VISIBLE);
                    ((OfferLayoutBinding) binding).rlCounter.setVisibility(View.GONE);
                }
            });
        }

        //set data and listener in groceries item layout
        public void bindDataInGroceries(Grocery grocery, ItemClickListener itemClickListener, int position) {
            ((GroceriesBinding) binding).imgGrocery.setImageResource(grocery.getImage());
            ((GroceriesBinding) binding).txtGrocery.setText(grocery.getTitle());
            ((GroceriesBinding) binding).rl.setBackgroundResource(grocery.getBackground());

            ((GroceriesBinding) binding).getRoot().setOnClickListener(v -> itemClickListener.onItemClick(position));
        }
    }
}
