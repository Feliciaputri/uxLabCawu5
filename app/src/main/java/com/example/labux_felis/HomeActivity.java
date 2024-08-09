package com.example.labux_felis;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;


import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private ImageAdapter adapter;
    private ArrayList<Integer> imageList;
    private Handler handler = new Handler(Looper.getMainLooper());
    private Runnable slideRunnable;
    TabLayout tabLayout;
    ViewPager2 viewPager2Tab;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
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

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });
        viewPager2Tab.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position); // This line is not required, you can safely remove it
                tabLayout.getTabAt(position).select(); // Use select() to update the selected tab
            }
        });

        viewPager = findViewById(R.id.viewPager);
        imageList = new ArrayList<>();
        imageList.add(R.drawable.carousel_1); // Add your image resources
        imageList.add(R.drawable.carousel_2);
        imageList.add(R.drawable.carousel_3);

        adapter = new ImageAdapter(this, imageList);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(1, false); // Start at the first real item

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position == 0) {
                    viewPager.setCurrentItem(adapter.getItemCount() - 2, false); // Jump to the last real item
                } else if (position == adapter.getItemCount() - 1) {
                    viewPager.setCurrentItem(1, false); // Jump to the first real item
                }
            }
        });

        slideRunnable = new Runnable() {
            @Override
            public void run() {
                int currentItem = viewPager.getCurrentItem();
                int nextItem = currentItem + 1;
                viewPager.setCurrentItem(nextItem);
                handler.postDelayed(this, 2000); // Schedule the runnable again after 2 seconds
            }
        };

        ImageView menuImageView = findViewById(R.id.menu_image);
        menuImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(view);
            }
        });

    }

    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.drawer_menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item1:

                        return true;
                    case R.id.item2:

                        return true;
                    case R.id.item3:

                        return true;
                    case R.id.item4:

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
        handler.postDelayed(slideRunnable, 2000); // Start the runnable when the activity resumes
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(slideRunnable); // Stop the runnable when the activity pauses
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.drawer_menu,menu);
        return true;
    }
}