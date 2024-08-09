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
            productList.add(new Product("Music Asset", "Game Assets: Music", R.drawable.detail1, "Description of object"));
            productList.add(new Product("2D Character", "Game Assets: Knight Character", R.drawable.detail2, "Description of object"));
            productList.add(new Product("Font Asset", "Game Assets: Font", R.drawable.detail3, "Description of object"));
            productList.add(new Product("3D Image", "Game Assets: 3D Arena", R.drawable.detail4, "Description of object"));
            productList.add(new Product("GUI Asset", "Game Assets: GUI", R.drawable.detail5, "Description of object"));

            productAdapter = new ProductAdapter(productList);
            recyclerView.setAdapter(productAdapter);
        } catch (Exception e) {
            Log.e(TAG, "Error initializing RecyclerView", e);
        }
    }
}
