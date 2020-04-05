package com.e.poissyflore;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.ExecutionException;

public class Modify extends Fragment implements View.OnClickListener {

    private RadioGroup planted, recolted;
    private Button recolte,ajout;
    private RadioButton[] rb, rb2;
    private String[] namesR,namesN;
    private int[] date;
    private EditText quantite;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.modify,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recolted = getView().findViewById(R.id.radiogroup);
        planted = getView().findViewById(R.id.radiogroup2);
        recolte = getView().findViewById(R.id.recolte);
        ajout = getView().findViewById(R.id.ajout);
        quantite = getView().findViewById(R.id.editText2);
        recolte.setOnClickListener(this);
        ajout.setOnClickListener(this);
        rb = new RadioButton[10];
        rb2 = new RadioButton[10];
        date = new int[10];
        namesR = new String[10];
        namesN = new String[10];
        ArrayList<String> a1 = new ArrayList<>(10);
        a1.add(0,"Infos");
        MyAsyncTasks myAsyncTasks = new MyAsyncTasks();
        myAsyncTasks.execute(a1);
        try {
            String bdd = myAsyncTasks.get();
            if(bdd != null) {
                JSONArray arr = new JSONObject(bdd).getJSONArray("Parts");
                for(int i= 0; i< rb.length; i++)
                {
                    rb[i]  = new RadioButton(this.getActivity());
                    rb2[i]  = new RadioButton(this.getActivity());
                }
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject e = arr.getJSONObject(i);
                    if(e.getString("Recolte").equals("null")){
                        rb[i]  = new RadioButton(this.getActivity());
                        recolted.addView(rb[i]); //the RadioButtons are added to the radioGroup instead of the layout
                        rb[i].setText(e.getString("Nom"));
                        namesR[i] = e.getString("Nom");
                    }
                }
                    rb2[0]  = new RadioButton(this.getActivity());
                    rb2[0].setText("Tomate");
                    namesN[0] = "Tomate";
                    date[0] = 2;
                rb2[1]  = new RadioButton(this.getActivity());
                rb2[1].setText("Aubergine");
                namesN[1] = "Aubergine";
                date[1] = 4;
                rb2[2]  = new RadioButton(this.getActivity());
                rb2[2].setText("Poireaux");
                namesN[2] = "Poireaux";
                date[2] = 6;

                for (int i = 0; i < 3; i++) {
                    planted.addView(rb2[i]);
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
        switch (view.getId()){
            case R.id.recolte:
                for(int i=0;i<rb.length;i++) {
                    if (rb[i].isChecked()) {
                        ArrayList<String> a1 = new ArrayList<>(10);
                        a1.add(0, "recolte");
                        a1.add(1, namesR[i]);
                        MyAsyncTasks myAsyncTasks = new MyAsyncTasks();
                        myAsyncTasks.execute(a1);
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.activity_main_frame_layout,new CulturFragment())
                                .commit();
                    }
                }

                break;
            case R.id.ajout:
                for(int i=0;i<rb2.length;i++) {
                    if (rb2[i].isChecked()) {
                        ArrayList<String> a1 = new ArrayList<>(10);
                        Date now = new Date();
                        DateFormat dateformatter = DateFormat.getDateInstance(DateFormat.SHORT);
                        String formattedDate = dateformatter.format(now);
                        java.util.Calendar cal = GregorianCalendar.getInstance();
                        cal.setTime( now );
                        cal.add( GregorianCalendar.MONTH, date[i]);
                        String Planned = dateformatter.format(cal.getTime());
                        String q = quantite.getText().toString();
                        a1.add(0, "new");
                        a1.add(1, namesN[i]);
                        a1.add(2, formattedDate);
                        a1.add(3, q);
                        a1.add(4, Planned);
                        MyAsyncTasks myAsyncTasks = new MyAsyncTasks();
                        myAsyncTasks.execute(a1);
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.activity_main_frame_layout,new CulturFragment())
                                .commit();
                    }
                }

        }
    }
}
