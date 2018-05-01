package com.gnstkd95.cafego;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by alfo6-7 on 2018-04-23.
 */

public class CommunityFragment extends Fragment {
    Context context;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = inflater.getContext();

        View view = inflater.inflate(R.layout.community,container,false);
        return view;
    }
}
