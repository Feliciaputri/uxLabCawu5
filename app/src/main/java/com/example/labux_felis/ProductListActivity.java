package com.example.labux_felis;

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
        setContentView(R.layout.activity_product_list);

        try {
            recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            productList = new ArrayList<>();
            productList.add(new Product("Title 1", "Game Assets: Knight Character", R.drawable.detail1));
            productList.add(new Product("Title 2", "Game Assets: 3D Arena", R.drawable.detail2));
            productList.add(new Product("Title 3", "Game Assets: Music", R.drawable.detail3));
            productList.add(new Product("Title 4", "Game Assets: GUI", R.drawable.detail4));
            productList.add(new Product("Title 5", "Game Assets: GUI", R.drawable.detail5));

            productAdapter = new ProductAdapter(productList);
            recyclerView.setAdapter(productAdapter);
        } catch (Exception e) {
            Log.e(TAG, "Error initializing RecyclerView", e);
        }
    }
}
