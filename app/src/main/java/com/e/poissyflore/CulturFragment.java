package com.e.poissyflore;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class CulturFragment extends Fragment implements View.OnClickListener {

    private Button b1;
    private TextView T;
    private LinearLayout Lay,Lay2,LL;
    private RadioGroup rg;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.cultures,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        b1 = getView().findViewById(R.id.modifier);
        b1.setOnClickListener(this);
        ArrayList<String> a1 = new ArrayList<>(10);
        a1.add(0,"Infos");
        MyAsyncTasks myAsyncTasks = new MyAsyncTasks();
        myAsyncTasks.execute(a1);
        Lay = getActivity().findViewById(R.id.frameLayout2);
        Lay2 = getActivity().findViewById(R.id.frameLayout);
        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lparams.setMargins(50,10,50,5);
        LinearLayout.LayoutParams LLparams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LLparams.setMargins(0,30,0,0);
        try {
            String bdd = myAsyncTasks.get();
            JSONArray arr = new JSONObject(bdd).getJSONArray("Parts");
            if (arr != null) {
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject e = arr.getJSONObject(i);

                    if(e.getString("Recolte").equals("null")) {
                        T = new TextView(this.getActivity());
                        LL = new LinearLayout(this.getActivity());
                        LL.setOrientation(LinearLayout.HORIZONTAL);
                        LL.setLayoutParams(LLparams);
                        T.setTextSize(14);
                        T.setText("\u2022" + "Plant de " + e.getString("Nom"));
                        T.setTypeface(null, Typeface.BOLD);
                        T.setLayoutParams(lparams);
                        this.LL.addView(T);
                        T = new TextView(this.getActivity());
                        T.setTextSize(14);
                        T.setText("Quantité : " +e.getString("Quantite"));
                        T.setLayoutParams(lparams);
                        this.LL.addView(T);
                        this.Lay.addView(LL);
                        T = new TextView(this.getActivity());
                        LL = new LinearLayout(this.getActivity());
                        LL.setOrientation(LinearLayout.HORIZONTAL);
                        LL.setLayoutParams(LLparams);
                        T.setTextSize(14);
                        T.setText("Planté le : " + e.getString("Date_Plantation"));
                        T.setTypeface(null, Typeface.ITALIC);
                        T.setLayoutParams(lparams);
                        this.LL.addView(T);
                        T = new TextView(this.getActivity());
                        T.setTextSize(14);
                        T.setText("Récolte prévu le : " + e.getString("Date_Recolte"));
                        T.setTypeface(null, Typeface.ITALIC);
                        T.setLayoutParams(lparams);
                        this.Lay.addView(LL);
                        this.Lay.addView(T);


                    }
                    else
                    {
                        T = new TextView(this.getActivity());
                        LL = new LinearLayout(this.getActivity());
                        LL.setOrientation(LinearLayout.HORIZONTAL);
                        LL.setLayoutParams(LLparams);
                        T.setTextSize(14);
                        T.setText("\u2022" + " " + e.getString("Nom"));
                        T.setLayoutParams(lparams);
                        this.LL.addView(T);
                        T = new TextView(this.getActivity());
                        T.setTextSize(14);
                        T.setText("Quantité : " +e.getString("Quantite"));
                        T.setLayoutParams(lparams);
                        this.LL.addView(T);
                        this.Lay2.addView(LL);
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

        @Override
    public void onClick(View view) {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_main_frame_layout,new Modify())
                .addToBackStack(null)
                .commit();
    }
}
