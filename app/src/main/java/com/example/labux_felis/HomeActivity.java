package com.example.labux_felis;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private ImageAdapter adapter;
    private ArrayList<Integer> imageList;
    private Handler handler = new Handler(Looper.getMainLooper());
    private Runnable slideRunnable;
    private TabLayout tabLayout;
    private ViewPager2 viewPager2Tab;
    private ViewPagerAdapter viewPagerAdapter;
    private TextView welcomeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        welcomeTextView = findViewById(R.id.welcome);

        String username = GlobalState.getInstance().getUsername();
        if (username != null) {
            welcomeTextView.setText("Welcome, " + username);
        }

        tabLayout = findViewById(R.id.tablayout);
        viewPager2Tab = findViewById(R.id.viewPagerTab);
        viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager2Tab.setAdapter(viewPagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2Tab.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // No-op
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // No-op
            }
        });

        viewPager2Tab.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });

        viewPager = findViewById(R.id.viewPager);
        imageList = new ArrayList<>();
        imageList.add(R.drawable.carousel_1);
        imageList.add(R.drawable.carousel_2);
        imageList.add(R.drawable.carousel_3);

        adapter = new ImageAdapter(this, imageList);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(1, false);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    viewPager.setCurrentItem(adapter.getItemCount() - 2, false);
                } else if (position == adapter.getItemCount() - 1) {
                    viewPager.setCurrentItem(1, false);
                }
            }
        });

        slideRunnable = new Runnable() {
            @Override
            public void run() {
                int currentItem = viewPager.getCurrentItem();
                int nextItem = currentItem + 1;
                viewPager.setCurrentItem(nextItem);
                handler.postDelayed(this, 2000);
            }
        };

        ImageView menuImageView = findViewById(R.id.menu_image);
        menuImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(HomeActivity.this, view);
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
        inflater.inflate(R.menu.drawer_menu, popupMenu.getMenu());

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
                    case R.id.item2:
                        activity.startActivity(new Intent(context, ProductListActivity.class));
                        return true;
                    case R.id.item3:
                        Intent profileIntent = new Intent(context, ProfileActivity.class);
                        profileIntent.putExtra("USERNAME", GlobalState.getInstance().getUsername());
                        activity.startActivity(profileIntent);
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
    protected void onResume() {
        super.onResume();
        handler.postDelayed(slideRunnable, 2000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(slideRunnable);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.drawer_menu, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(slideRunnable);
        // Cleanup any resources related to the PopupMenu or other UI elements if needed
    }
}
