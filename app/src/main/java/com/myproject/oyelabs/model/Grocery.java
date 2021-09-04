package com.myproject.oyelabs.model;

public class Grocery {
    private final int image;
    private final int background;
    private final String title;

    public Grocery(int image, int background, String title) {
        this.image = image;
        this.title = title;
        this.background = background;
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public int getBackground() {
        return background;
    }
}
