package com.myproject.oyelabs.model;

public class Product {
    private final int image;
    private final String title;
    private final String subTitle;
    private final String price;

    public Product(int image, String title, String subTitle, String price) {
        this.image = image;
        this.title = title;
        this.subTitle = subTitle;
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public String getPrice() {
        return price;
    }
}
