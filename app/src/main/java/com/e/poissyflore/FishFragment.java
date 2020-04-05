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

public class FishFragment extends Fragment implements View.OnClickListener {

    private ArrayList<ImageView> fish;
    private ImageView img;
    private TextView Title, d, d1;
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
        fish.add(4,(ImageView) getActivity().findViewById(R.id.f5));
        fish.add(5,(ImageView) getActivity().findViewById(R.id.f6));
        fish.add(6,(ImageView) getActivity().findViewById(R.id.f7));
        fish.add(7,(ImageView) getActivity().findViewById(R.id.f8));
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
            case R.id.f1:
                Title.setText("Truite");
                img.setBackgroundResource(R.drawable.truite);
                d1.setText("Consommable : Oui \n\u2022 Idéal pour les températures fraîches (10°C - 20°C)\n" +
                        "\u2022 Taux de croissance très rapide\n" +
                        "\u2022 D’excellents taux de conversion alimentaire\n");
                break;
            case R.id.f2:
                Title.setText("Carpe");
                img.setBackgroundResource(R.drawable.carpe);
                d1.setText("Consommable : Oui\n" +
                        "\n" +
                        "\u2022 S’adapte à la température de l’aquarium ( de 5 à 30°C )\n" +
                        "\u2022 Bonnes capacités de reproduction\n" +
                        "\u2022 Peut s’adapter facilement dans divers environnements\n");
                break;
            case R.id.f3:
                Title.setText("Tilapia");
                img.setBackgroundResource(R.drawable.tilapia);
                d1.setText("Consommable : Oui\n" +
                        "\u2022 Difficulté de gestion : \u2605 \n" +
                        "\u2022 Vitesse de croissance : Rapide\n" +
                        "\u2022 Résistance à une eau dégradée : +++\n" +
                        "\u2022 Température d’eau préferentielle : >15°C\n");
                break;
            case R.id.f4:
                Title.setText("Koi");
                img.setBackgroundResource(R.drawable.koi);
                d1.setText("Consommable : Non\n" +
                        "\u2022 Peut prospérer dans un système aquaponique\n" +
                        "\u2022 Longue durée de vie\n" +
                        "\u2022 Grande résistance contre les parasites communs\n");
                break;
            case R.id.f5:
                Title.setText("Poisson rouge");
                img.setBackgroundResource(R.drawable.poissonrouge);
                d1.setText("Consommable : Non\n" +
                        "\u2022 A généralement besoin d’un couvert végétal pour se reproduire dans le réservoir\n" +
                        "\u2022 Poisson le plus populaire de l’aquarium\n");
                break;
            case R.id.f6:
                Title.setText("Poisson Chat");
                img.setBackgroundResource(R.drawable.poissonchat);
                d1.setText("Consommable : Oui\n" +
                        "\u2022 Besoin d’être écorchés, car ils ne disposent pas d’écailles\n" +
                        "\u2022 Croissance rapide\n" +
                        "\u2022 Bonne nourriture et bon ratio de conversion\n");
                break;
            case R.id.f7:
                Title.setText("Achigan à grande bouche");
                img.setBackgroundResource(R.drawable.achiganagrandebouche);
                d1.setText("Consommable: Oui\n" +
                        "\u2022 Alimentation très variée\n" +
                        "\u2022 A l’aise dans l’eau à basse température\n");
                break;
            case R.id.f8:
                Title.setText("Perchaude");
                img.setBackgroundResource(R.drawable.perchaude);
                d1.setText("Consommable : non\n" +
                        "\u2022 Poisson résistant à l’eau impure et froide\n" +
                        "\u2022 Poisson élégant\n");
                break;
        }
    }
}
