package com.e.poissyflore;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

    public class SensorsFragment extends Fragment implements View.OnClickListener{
        private ConstraintLayout c1;
        private TextView title, details;
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.sensors,container,false);
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            c1 = getView().findViewById(R.id.details);
            Button b1 = (Button) getView().findViewById(R.id.sensor1);
            Button b2 = (Button) getView().findViewById(R.id.sensor2);
            Button b3 = (Button) getView().findViewById(R.id.sensor3);
            Button b4 = (Button) getView().findViewById(R.id.sensor4);
            title = getView().findViewById(R.id.sensor);
            b1.setOnClickListener(this);
            b2.setOnClickListener(this);
            b3.setOnClickListener(this);
            b4.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.sensor1:
                    title.setText("Capteur de Ph :");
                    c1.setVisibility(View.VISIBLE);
                    break;
                case R.id.sensor2:
                    c1.setVisibility(View.VISIBLE);
                    break;
                case R.id.sensor3:
                    title.setText("Capteur d'Ammoniaque :");
                    c1.setVisibility(View.VISIBLE);
                    break;
                case R.id.sensor4:
                    c1.setVisibility(View.VISIBLE);
                    break;
            }

        }
    }
