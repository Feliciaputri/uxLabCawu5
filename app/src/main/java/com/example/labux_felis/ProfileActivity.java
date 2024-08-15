package com.example.labux_felis;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

public class ProfileActivity extends AppCompatActivity {
    private TextView HiTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.profile);

        HiTextView = findViewById(R.id.hiUsernameText);

        // Get username from intent
        String username = GlobalState.getInstance().getUsername();

        if (username != null) {
            HiTextView.setText("Welcome, " + username);
        }
        ImageView menuImageView = findViewById(R.id.menu_image); // Assuming you have a menu button
        menuImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(ProfileActivity.this, view);
            }
        });
    }

    public static void showPopupMenu(Context context, View view) {
        if (context == null) return;

        AppCompatActivity activity = (AppCompatActivity) context;
        if (activity.isFinishing() || activity.isDestroyed()) {
            return;
        }

        PopupMenu popupMenu = new PopupMenu(context, view, 0, 0, R.style.CustomPopupMenu);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.drawer_menu_profile, popupMenu.getMenu());

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
                    case R.id.item2:
                        activity.startActivity(new Intent(context, ProductListActivity.class));
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
