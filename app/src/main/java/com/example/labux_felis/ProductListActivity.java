package com.example.labux_felis;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
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

            ImageView menuImageView = findViewById(R.id.menu_image); // Assuming you have a menu button
            menuImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showPopupMenu(ProductListActivity.this, view);
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Error initializing RecyclerView", e);
        }
    }

    public static void showPopupMenu(Context context, View view) {
        if (context == null) return;

        AppCompatActivity activity = (AppCompatActivity) context;
        if (activity.isFinishing() || activity.isDestroyed()) {
            return;
        }

        PopupMenu popupMenu = new PopupMenu(context, view, 0, 0, R.style.CustomPopupMenu);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.drawer_menu_item, popupMenu.getMenu());

        // Apply icon color filter
        for (int i = 0; i < popupMenu.getMenu().size(); i++) {
            MenuItem item = popupMenu.getMenu().getItem(i);
            Drawable icon = item.getIcon();
            if (icon != null) {
                icon.mutate();
                icon.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
            }
        }

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item1:
                        activity.startActivity(new Intent(context, HomeActivity.class));
                        return true;
                    case R.id.item3:
                        activity.startActivity(new Intent(context, ProfileActivity.class));
                        return true;
                    case R.id.item4:
                        activity.startActivity(new Intent(context, MainActivity.class));
                        return true;
                    default:
                        return false;
                }
            }
        });

        popupMenu.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
