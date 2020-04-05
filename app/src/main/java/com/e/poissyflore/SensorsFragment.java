package com.e.poissyflore;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class SensorsFragment extends Fragment implements View.OnClickListener{
        private ConstraintLayout c1;
        private TextView title, details,details2;
        private JSONArray temp, humid, pH;
        private ImageView circle, graph;
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.sensors,container,false);
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            c1 = getView().findViewById(R.id.details);
            ImageView b1 = (ImageView) getView().findViewById(R.id.sensor1);
            ImageView b2 = (ImageView) getView().findViewById(R.id.sensor2);
            ImageView b3 = (ImageView) getView().findViewById(R.id.sensor3);
            graph = getView().findViewById(R.id.imageView2);
            title = getView().findViewById(R.id.sensor);
            details = getView().findViewById(R.id.Value);
            circle = getView().findViewById(R.id.state);
            details2 = getView().findViewById(R.id.alert);
            b1.setOnClickListener(this);
            b2.setOnClickListener(this);
            b3.setOnClickListener(this);
            ArrayList<String> a1 = new ArrayList<>(10);
            a1.add(0,"Infos");
            MyAsyncTasks myAsyncTasks = new MyAsyncTasks();
            myAsyncTasks.execute(a1);
            String bdd = null;
            try {
                bdd = myAsyncTasks.get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (bdd != null) {
                try {
                    temp = new JSONObject(bdd).getJSONArray("Temperature");
                    humid = new JSONObject(bdd).getJSONArray("Humidite");
                    pH = new JSONObject(bdd).getJSONArray("pH");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.sensor1:
                    title.setText("Capteur de Ph :");
                    graph.setVisibility(View.VISIBLE);
                    graph.setBackgroundResource(R.drawable.hum);
                    for(int i =0; i < pH.length(); i++){
                        JSONObject e = null;
                        try {
                            e = pH.getJSONObject(i);
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                        try {
                            if(e.getInt("Id") == 0){
                                details.setText("Valeur : "+ e.getDouble("Valeur") + "°C");
                                if(e.getDouble("Valeur") < 6.5 || e.getDouble("Valeur") > 7.5) circle.setBackgroundResource(R.drawable.circley);
                                if(e.getDouble("Valeur") < 6){
                                    circle.setBackgroundResource(R.drawable.circle);
                                    details2.setText("\u26A0 Attention pH très faible et potentiellement dangereux pour votre systéme. Nous vous conseillons d'ajouter une solution basique (eau de ville, bicarbonnate de potassium, coquille d'oeufs");
                                }
                                if(e.getDouble("Valeur") > 8){
                                    circle.setBackgroundResource(R.drawable.circle);
                                    details2.setText("\u26A0 Attention pH très élevé et potentiellement dangereux pour votre systéme. Nous vous conseillons de vérifie que la pompe n'est pas obstrué.");
                                }
                            }
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                    }
                    c1.setVisibility(View.VISIBLE);
                    break;
                case R.id.sensor2:
                    title.setText("Capteur de température :");
                    circle.setBackgroundResource(R.drawable.circleg);
                    details2.setText("");
                    graph.setVisibility(View.VISIBLE);
                    graph.setBackgroundResource(R.drawable.temp);
                    for(int i =0; i < temp.length(); i++){
                        JSONObject e = null;
                        try {
                            e = temp.getJSONObject(i);
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                        try {
                            if(e.getInt("Id") == 0){
                                details.setText("Valeur : "+ e.getDouble("Valeur") + "°C");
                            }
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                    }
                    c1.setVisibility(View.VISIBLE);
                    break;
                    case R.id.sensor3:
                        details2.setText("");
                        circle.setBackgroundResource(R.drawable.circleg);
                    title.setText("Capteur d'Humidite :");
                    graph.setVisibility(View.GONE);
                        for(int i =0; i < humid.length(); i++){
                            JSONObject e = null;
                            try {
                                e = humid.getJSONObject(i);
                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            }
                            try {
                                if(e.getInt("Id") == 0){
                                    details.setText("Valeur : "+ e.getDouble("Valeur") + "%");
                                }
                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            }
                        }
                    c1.setVisibility(View.VISIBLE);
                    break;
            }

        }
    }
