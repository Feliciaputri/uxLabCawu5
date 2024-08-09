package com.example.labux_felis;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ProductListActivity extends AppCompatActivity {

    private static final String TAG = "ProductListActivity";
    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_product_list);


        try {
            recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            productList = new ArrayList<>();
            productList.add(new Product("Music Asset", "Game Assets: Music", R.drawable.detail1, "Music Channels each with a volume slider and an instrument slider"));
            productList.add(new Product("2D Character", "Game Assets: Knight Character", R.drawable.detail2, "A clean and flat color cartoon design. This game character asset is suitable for your game character"));
            productList.add(new Product("Font Asset", "Game Assets: Font", R.drawable.detail3, "Font scripts containing 99 glyphs of the Universe's most famous dead language"));
            productList.add(new Product("3D Image", "Game Assets: 3D Arena", R.drawable.detail4, "Contains 70+ stylized 3D assets design to build a fully-fledged game world with many features."));
            productList.add(new Product("GUI Asset", "Game Assets: GUI", R.drawable.detail5, "A simple 16-bit asset pack that has all the necessities for your game's GUI"));

            productAdapter = new ProductAdapter(productList);
            recyclerView.setAdapter(productAdapter);
        } catch (Exception e) {
            Log.e(TAG, "Error initializing RecyclerView", e);
        }
    }
}
