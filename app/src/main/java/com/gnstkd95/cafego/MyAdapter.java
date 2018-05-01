package com.gnstkd95.cafego;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by alfo6-7 on 2018-04-23.
 */

public class MyAdapter extends FragmentPagerAdapter {

    Fragment[] fragments = new Fragment[4];
//    String[] titles = {"카페 검색","커뮤니티","랭킹","나만의카페" };

    public MyAdapter(FragmentManager fm) {
        super(fm);

        fragments[0] = new CafeSearchFragment();
        fragments[1] = new CommunityFragment();
        fragments[2] = new RankingFragment();
        fragments[3] = new MyCafeFragment();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

//    @Override
//    public CharSequence getPageTitle(int position) {
//
//        return titles[position];
//    }
}
