package com.e.poissyflore;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class DashboardFragment extends Fragment implements View.OnClickListener {
private TextView info1,info2;
    private TextView T;
    private Button b;
    private LinearLayout Lay;
    private LinearLayout.LayoutParams lparams;
    private ImageView sensor,food;
    ProgressBar pb;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.board,container,false);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().getSupportFragmentManager().beginTransaction().isEmpty();
        Button b2 = (Button) getView().findViewById(R.id.buttonsensor);
        b2.setOnClickListener(this);
        sensor = getView().findViewById(R.id.sensorstate);
        food = getView().findViewById(R.id.foodstate);
        info1 = getView().findViewById(R.id.textView2);
        info2 = getView().findViewById(R.id.textView4);
        Lay = getActivity().findViewById(R.id.dynamiclay);
        pb = getView().findViewById(R.id.progressBar);
        lparams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lparams.setMargins(150,0,50,15);
        ArrayList<String> a1 = new ArrayList<>(10);
        a1.add(0,"Infos");
        MyAsyncTasks myAsyncTasks = new MyAsyncTasks();
        myAsyncTasks.execute(a1);
        try {
            String bdd = myAsyncTasks.get();
            if(bdd != null) {
                JSONArray arr = new JSONObject(bdd).getJSONArray("Parts");
                    for (int i = 0; i < arr.length(); i++) {
                        T = new TextView(this.getActivity());
                        JSONObject e = arr.getJSONObject(i);
                        if(e.getString("Recolte").equals("null")) {
                            T.setTextSize(14);
                            T.setLayoutParams(lparams);
                            T.setText("\u2022" + "" + e.getString("Nom"));
                            this.Lay.addView(T);
                        }
                    }
                    JSONArray arr2 = new JSONObject(bdd).getJSONArray("pH");
                for(int i =0; i < arr2.length(); i++) {
                    JSONObject e = arr2.getJSONObject(i);
                    if (e.getInt("Id") == 0){//La valeur mesurée la plus récente
                        if (e.getDouble("Valeur") < 6.5 || e.getDouble("Valeur") > 7.5)
                            sensor.setBackgroundResource(R.drawable.circley);
                        if(e.getDouble("Valeur") < 6 || e.getDouble("Valeur") > 8)
                            sensor.setBackgroundResource(R.drawable.circle);
                }
                }
                    lparams.setMargins(150, 30, 50, 15);
                    b = new Button(this.getActivity());
                    b.setText("Plus d'informations");
                    b.setId(R.id.modif);
                    b.setLayoutParams(lparams);
                    b.setOnClickListener(this);
                    this.Lay.addView(b);
                } else {
                    b2.setVisibility(view.GONE);
                    sensor.setVisibility(view.GONE);
                    food.setVisibility(view.GONE);
                    info2.setVisibility(view.GONE);
                    info1.setText("Impossible de se connecter à votre système");
                    pb.setVisibility(view.VISIBLE);
                }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonsensor:
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.activity_main_frame_layout,new SensorsFragment())
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.modif:
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.activity_main_frame_layout,new CulturFragment())
                        .addToBackStack(null)
                        .commit();
                break;

        }
    }

}

