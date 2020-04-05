package com.e.poissyflore;

import android.content.Context;
import android.os.Bundle;
import android.util.EventLogTags;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class Plantsfragment extends Fragment implements View.OnClickListener {

    private ArrayList<ImageView> fish;
    private ImageView img;
    private TextView Title, d, d1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.plant,container,false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fish = new ArrayList<>();
        fish.add(0,(ImageView) getActivity().findViewById(R.id.p1));
        fish.add(1,(ImageView) getActivity().findViewById(R.id.p2));
        fish.add(2,(ImageView) getActivity().findViewById(R.id.p3));
        fish.add(3,(ImageView) getActivity().findViewById(R.id.p4));
        fish.add(4,(ImageView) getActivity().findViewById(R.id.p5));
        fish.add(5,(ImageView) getActivity().findViewById(R.id.p6));
        fish.add(6,(ImageView) getActivity().findViewById(R.id.p7));
        fish.add(7,(ImageView) getActivity().findViewById(R.id.p8));
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
        img = popupView.findViewById(R.id.ImageDetail);
        Title = popupView.findViewById(R.id.Title);
        d = popupView.findViewById(R.id.Description1);
        d1 = popupView.findViewById(R.id.Description2);
        switch (view.getId()) {
            case R.id.p1:
                Title.setText("Epinard");
                img.setBackgroundResource(R.drawable.epinard);
                d1.setText("\u2022Type : Annuelle\n" +
                        "\u2022Récolte : janvier à décembre - 6 semaines après semis\n" +
                        "\u2022Semis : entre le début printemps et fin d’été\n");
                break;
            case R.id.p2:
                Title.setText("Salade");
                img.setBackgroundResource(R.drawable.salade);
                d1.setText("\u2022Type : Annuelle\n" +
                        "\u2022Récolte : Environ 6 semaines après semis\n" +
                        "\u2022Semis : février à octobre\n");
                break;
            case R.id.p3:
                Title.setText("Poireau");
                img.setBackgroundResource(R.drawable.poireau);
                d1.setText("\u2022semi : mai juin\n" +
                        "\u2022recolte : décembre \n");
                break;
            case R.id.p4:
                Title.setText("Persil");
                img.setBackgroundResource(R.drawable.persil);
                d1.setText("\u2022semis : avril mars\n" +
                        "\u2022recolte : septembre octobre\n");
                break;
            case R.id.p5:
                Title.setText("Haricot Vert");
                img.setBackgroundResource(R.drawable.haricot_vert);
                d1.setText("\u2022semis : mars mai\n" +
                        "\u2022recolte :  aout\n");
                break;
            case R.id.p6:
                Title.setText("Ciboulette");
                img.setBackgroundResource(R.drawable.ciboulette);
                d1.setText("\u2022semis : fevrier avril\n" +
                        "\u2022récolte : septembre\n");
                break;
            case R.id.p7:
                Title.setText("Basilic");
                img.setBackgroundResource(R.drawable.basilic);
                d1.setText("\u2022semi : fevrier mars\n" +
                        "\u2022recolte : juillet aout\n");
                break;
            case R.id.p8:
                Title.setText("Aubergine");
                img.setBackgroundResource(R.drawable.aubergine);
                d1.setText("\u2022semis : mars  \n" +
                        "\u2022recolte : juillet \n");
                break;
        }
    }
}
