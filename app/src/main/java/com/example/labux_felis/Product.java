package com.example.labux_felis;

public class Product {
    private String title;
    private String type;
    private int imageResId;

    public Product(String title, String type, int imageResId) {
        this.title = title;
        this.type = type;
        this.imageResId = imageResId;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public int getImageResId() {
        return imageResId;
    }
}