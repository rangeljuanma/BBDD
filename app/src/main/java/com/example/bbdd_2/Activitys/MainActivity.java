package com.example.bbdd_2.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bbdd_2.R;

public class MainActivity extends AppCompatActivity {

    private Button btnBuscar;
    private EditText textID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadElements();

        btnBuscar.setOnClickListener(e->{
            if (textID.getText().toString().equals("1")){


            }


        });

    }

    private void loadElements() {
        btnBuscar = findViewById(R.id.btnBuscar);
        textID= findViewById(R.id.textID);
    }


}