package com.myproject.oyelabs.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.myproject.oyelabs.R;

import java.util.List;

public class SliderAdapter extends PagerAdapter {

    private final List<Integer> list;

    public SliderAdapter(List<Integer> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        try {
            return list.size();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.slider_layout, container, false);
        ImageView imageView = view.findViewById(R.id.imgSlider);
        imageView.setImageResource(list.get(position));
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
