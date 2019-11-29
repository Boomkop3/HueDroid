package com.example.huedroid.ui.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.huedroid.R;

import java.util.ArrayList;
import java.util.function.Consumer;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    ArrayList<LightsFragment> lightsFragments;

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        for (LightsFragment lightsFragment : lightsFragments){
            lightsFragment.notifyDataSetChanged();
        }
    }

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
        this.lightsFragments = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            LightsFragment lightsFragment = LightsFragment.newInstance();
            lightsFragments.add(lightsFragment);
            return lightsFragment;
        }
        else {
            return LightsFragment.newInstance();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 2;
    }
}