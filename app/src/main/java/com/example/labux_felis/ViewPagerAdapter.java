package com.example.labux_felis;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.labux_felis.fragments.ConditionFragment;
import com.example.labux_felis.fragments.TermsFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new TermsFragment();
            case 1:
                return  new ConditionFragment();
            default:
                return new TermsFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
