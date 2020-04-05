package com.e.poissyflore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;

public class InfosFragment extends Fragment implements TabLayout.BaseOnTabSelectedListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.infos,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TabLayout tabLayout = (TabLayout) getActivity().findViewById(R.id.tablayout);
        tabLayout.addOnTabSelectedListener(this);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.infoframe,new FishFragment())
                .commit();
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        switch (tab.getPosition()) {
            case 0:
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.infoframe,new FishFragment())
                        .commit();
                break;
            case 1:
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.infoframe,new Plantsfragment())
                        .commit();
                break;
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        switch (tab.getPosition()) {
            case 0:
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.infoframe,new FishFragment())
                        .commit();
                break;
            case 1:

                break;
        }

    }
}
