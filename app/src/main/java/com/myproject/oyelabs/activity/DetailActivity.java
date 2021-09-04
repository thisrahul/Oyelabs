package com.myproject.oyelabs.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.myproject.oyelabs.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {

    //binding instance
    private ActivityDetailBinding binding;

    //variable for count of product or quantity of product
    private int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set binding
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //get product detail from previous activity
        String title = getIntent().getExtras().getString("title");
        int image = getIntent().getExtras().getInt("image");

        //set all views text and click listener
        initViews(title, image);
    }

    private void initCounter() {

        //add button click listener
        binding.imgAdd.setOnClickListener(v -> {
            binding.imgAdd.setVisibility(View.GONE);
            binding.rlCounter.setVisibility(View.VISIBLE);
            binding.txtCount.setText("1");
        });

        //plus button click listener
        binding.imgPlus.setOnClickListener(v -> binding.txtCount.setText(String.valueOf(++count)));

        //minus button click listener
        binding.imgMinus.setOnClickListener(v -> {
            if (count > 1) {
                binding.txtCount.setText(String.valueOf(--count));
            } else {
                binding.imgAdd.setVisibility(View.VISIBLE);
                binding.rlCounter.setVisibility(View.GONE);
            }
        });
    }

    private void initViews(String title, int image) {
        //set name of product
        binding.txtTitle.setText(title);
        //set image of product
        binding.imgItem.setImageResource(image);

        //back button click listener
        binding.imgBack.setOnClickListener(v -> onBackPressed());
        //add to basket click listener
        binding.btnAddToBasket.setOnClickListener(v -> Toast.makeText(this, "Add to Basket", Toast.LENGTH_SHORT).show());
        //upload btn click listener
        binding.imgUpload.setOnClickListener(v -> Toast.makeText(this, "Image Upload", Toast.LENGTH_SHORT).show());
        //favourite button click listener
        binding.imgFavourite.setOnClickListener(v -> Toast.makeText(this, "Favourit", Toast.LENGTH_SHORT).show());

        //set click clistener of counter and set text of count
        initCounter();
    }
}