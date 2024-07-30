package com.example.labux_felis;

public class Product {
    private String title;
    private String type;
    private int imageResId;
    private String description;

    public Product(String title, String type, int imageResId, String description) {
        this.title = title;
        this.type = type;
        this.imageResId = imageResId;
        this.description = description;
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

    public String getDescription() {
        return description;
    }
}