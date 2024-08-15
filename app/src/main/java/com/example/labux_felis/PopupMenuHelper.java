package com.example.labux_felis;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

public class PopupMenuHelper {

    public static void showPopupMenu(Context context, View view) {
        if (context == null || ((AppCompatActivity) context).isFinishing() || ((AppCompatActivity) context).isDestroyed()) {
            return;
        }

        PopupMenu popupMenu = new PopupMenu(context, view, 0, 0, R.style.CustomPopupMenu);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.drawer_menu, popupMenu.getMenu());


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
                        Intent homeIntent = new Intent(context, HomeActivity.class);
                        context.startActivity(homeIntent);
                        return true;
                    case R.id.item2:
                        Intent gameIntent = new Intent(context, ProductListActivity.class);
                        context.startActivity(gameIntent);
                        return true;
                    case R.id.item3:
                        Intent profileIntent = new Intent(context, ProfileActivity.class);
                        profileIntent.putExtra("USERNAME", GlobalState.getInstance().getUsername());
                        context.startActivity(profileIntent);
                        return true;
                    case R.id.item4:
                        Intent logoutIntent = new Intent(context, MainActivity.class);
                        context.startActivity(logoutIntent);
                        return true;
                    default:
                        return false;
                }
            }
        });

        popupMenu.show();
    }
}
