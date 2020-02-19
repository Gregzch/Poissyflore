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
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


import static android.content.ContentValues.TAG;

public class DashboardFragment extends Fragment implements View.OnClickListener {
    private ArrayList<TextView> plants;
    private TextView T;
    private Button b;
    private LinearLayout Lay;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.board,container,false);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button b1 = (Button) getView().findViewById(R.id.buttonfood);
        b1.setOnClickListener(this);
        Button b2 = (Button) getView().findViewById(R.id.buttonsensor);
        b2.setOnClickListener(this);
        Lay = getActivity().findViewById(R.id.dynamiclay);
        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lparams.setMargins(150,0,50,10);
        MyAsyncTasks myAsyncTasks = new MyAsyncTasks();
        myAsyncTasks.execute();
        try {
            JSONArray arr = myAsyncTasks.get();
                for (int i = 0; i < arr.length(); i++) {
                    T = new TextView(this.getActivity());
                    JSONObject e = arr.getJSONObject(i);
                    T.setTextSize(14);
                    T.setLayoutParams(lparams);
                    T.setText("\u2022"+"" + e.getString("Nom"));
                    this.Lay.addView(T);
                }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        b = new Button(this.getActivity());
        b.setText("Plus d'informations");
        b.setId(4);
        b.setLayoutParams(lparams);
        this.Lay.addView(b);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonfood:
                break;
            case R.id.buttonsensor:
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.activity_main_frame_layout,new SensorsFragment())
                        .commit();
                break;
            case 4:
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.activity_main_frame_layout,new CulturFragment())
                        .commit();
                break;

        }
    }

    public void onBackPressed()
    {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        fm.popBackStack();
    }
   /* public void Connection() throws JSONException {

        for (int i = 0; i < arr.length(); i++) {
            JSONObject e = arr.getJSONObject(i);
            T = new TextView(this.getActivity());
            T.setTextSize(14);
            T.setLayoutParams(lparams);
            T.setText(e.getString("Nom"));
            this.Lay.addView(T);

        }
    }*/
}

