package com.example.bbdd_2.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.bbdd_2.R;

public class MainActivity extends AppCompatActivity {

    private Button btnAnadir, btnConsulta, btnELiminar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadElements();

        btnAnadir.setOnClickListener(e->{

        });

    }

    private void loadElements() {
        btnAnadir= findViewById(R.id.btnAnadir);
        btnConsulta = findViewById(R.id.btnConsulta);
        btnELiminar = findViewById(R.id.btnEliminar);

    }


}