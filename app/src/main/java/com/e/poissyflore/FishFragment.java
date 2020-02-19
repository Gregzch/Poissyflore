package com.e.poissyflore;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class FishFragment extends Fragment implements View.OnClickListener {

    private ArrayList<ImageView> fish;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fish,container,false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fish = new ArrayList<>();
        fish.add(0,(ImageView) getActivity().findViewById(R.id.f1));
        fish.add(1,(ImageView) getActivity().findViewById(R.id.f2));
        fish.add(2,(ImageView) getActivity().findViewById(R.id.f3));
        fish.add(3,(ImageView) getActivity().findViewById(R.id.f4));
       /* fish.add(4,(ImageView) getActivity().findViewById(R.id.f5));
        fish.add(5,(ImageView) getActivity().findViewById(R.id.f6));
        fish.add(6,(ImageView) getActivity().findViewById(R.id.f7));
        fish.add(7,(ImageView) getActivity().findViewById(R.id.f8));
        fish.add(8,(ImageView) getActivity().findViewById(R.id.f9));*/
        for(int i=0; i< fish.size();i++){
            fish.get(i).setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View view) {
        Log.d("Click","Clique sur image");
        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = layoutInflater.inflate(R.layout.popup, null);
        int width = 800;
        int height = 1000;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        popupWindow.showAtLocation(view,Gravity.CENTER_VERTICAL, 0, 0);
        switch (view.getId()) {
            case R.id.f1:
                break;
            case R.id.f2:
                break;
            case R.id.f3:
                break;
            case R.id.f4:
                break;
           /* case R.id.f5:
                break;
            case R.id.f6:
                break;
            case R.id.f7:
                break;
            case R.id.f8:
                break;
            case R.id.f9:
                break;    */
        }
    }
}
