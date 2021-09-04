package com.myproject.oyelabs.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.myproject.oyelabs.R;
import com.myproject.oyelabs.adapter.ItemAdapter;
import com.myproject.oyelabs.adapter.SliderAdapter;
import com.myproject.oyelabs.databinding.ActivityMainBinding;
import com.myproject.oyelabs.model.Grocery;
import com.myproject.oyelabs.model.Product;

import java.util.ArrayList;
import java.util.List;

import static com.myproject.oyelabs.adapter.ItemAdapter.BEST_SELLING;
import static com.myproject.oyelabs.adapter.ItemAdapter.EXCLUSIVE_OFFER;
import static com.myproject.oyelabs.adapter.ItemAdapter.GROCERIES;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //binding instance
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set  binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //set all views
        initViews();

    }

    private void initViews() {
        //set click listener of see all exclusive offer
        binding.seellExclusiveOffer.setOnClickListener(this);
        //set click listener of see all groceries
        binding.seeAllGroceries.setOnClickListener(this);
        //set click listener of see all best selling
        binding.seeBestSelling.setOnClickListener(this);

        //setup of slider
        setUpSlider();

        //setup of exclusive offers
        setUpExclusiveOffer();

        //setup of groceries
        setUpGroceries();

        //setup of best selling products
        setUpBestSelling();
    }

    private void setUpBestSelling() {

        //product list
        ArrayList<Product> list = new ArrayList<>();
        list.add(new Product(R.drawable.bell, "Bell Pepper Red", "", "$4.99"));
        list.add(new Product(R.drawable.ginger, "Ginger", "", "$4.99"));
        list.add(new Product(R.drawable.bell, "Bell Pepper Red", "", "$4.99"));
        list.add(new Product(R.drawable.ginger, "Ginger", "", "$4.99"));
        list.add(new Product(R.drawable.bell, "Bell Pepper Red", "", "$4.99"));
        list.add(new Product(R.drawable.ginger, "Ginger", "", "$4.99"));


        //set layout manager to recyclerview
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        binding.rvBestSelling.setLayoutManager(linearLayoutManager);

        //set adapter
        ItemAdapter itemAdapter = new ItemAdapter(list, null, BEST_SELLING, null);
        binding.rvBestSelling.setAdapter(itemAdapter);
    }

    private void setUpGroceries() {

        //list of groceries
        ArrayList<Grocery> list = new ArrayList<>();
        list.add(new Grocery(R.drawable.pulse, R.drawable.rectangle_8, "Pulses"));
        list.add(new Grocery(R.drawable.rice, R.drawable.rectangle_9, "Rice"));
        list.add(new Grocery(R.drawable.pulse, R.drawable.rectangle_8, "Pulses"));
        list.add(new Grocery(R.drawable.rice, R.drawable.rectangle_9, "Rice"));
        list.add(new Grocery(R.drawable.pulse, R.drawable.rectangle_8, "Pulses"));
        list.add(new Grocery(R.drawable.rice, R.drawable.rectangle_9, "Rice"));

        // set layout manager to recyclerview
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        binding.rvGroceries.setLayoutManager(linearLayoutManager);

        //set adapter to recycler view with item click listener
        ItemAdapter itemAdapter = new ItemAdapter(null, list, GROCERIES, position -> {
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("title", list.get(position).getTitle());
            intent.putExtra("image", list.get(position).getImage());
            startActivity(intent);
        });
        binding.rvGroceries.setAdapter(itemAdapter);
    }

    private void setUpExclusiveOffer() {

        //product list
        ArrayList<Product> list = new ArrayList<>();
        list.add(new Product(R.drawable.banana, "Organic Banana", "7pcs, Priceg", "$4.99"));
        list.add(new Product(R.drawable.apple, "Red Apple", "1kg, Priceg", "$4.99"));
        list.add(new Product(R.drawable.banana, "Organic Banana", "7pcs, Priceg", "$4.99"));
        list.add(new Product(R.drawable.apple, "Red Apple", "1kg, Priceg", "$4.99"));
        list.add(new Product(R.drawable.banana, "Organic Banana", "7pcs, Priceg", "$4.99"));
        list.add(new Product(R.drawable.apple, "Red Apple", "1kg, Priceg", "$4.99"));

        //set layout manage to recyclerview
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        binding.rvExclusiveOffer.setLayoutManager(linearLayoutManager);

        //set adapter to recyclerview
        ItemAdapter itemAdapter = new ItemAdapter(list, null, EXCLUSIVE_OFFER, null);
        binding.rvExclusiveOffer.setAdapter(itemAdapter);
    }

    private void setUpSlider() {

        //list of banner
        List<Integer> list = new ArrayList<>();
        list.add(R.drawable.slider);
        list.add(R.drawable.slider);
        list.add(R.drawable.slider);

        //set adapter to view pager with indicator
        SliderAdapter sliderAdapter = new SliderAdapter(list);
        binding.viewPager.setAdapter(sliderAdapter);
        binding.indicator.setupWithViewPager(binding.viewPager);

    }

    //click function of views
    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.seellExclusiveOffer) {
            Toast.makeText(this, "see all Exclusive Offer", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.seeAllGroceries) {
            Toast.makeText(this, "see all Groceries", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.seeBestSelling) {
            Toast.makeText(this, "see all Best Selling", Toast.LENGTH_SHORT).show();
        }
    }
}