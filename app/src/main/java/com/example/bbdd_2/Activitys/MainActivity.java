package com.example.bbdd_2.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bbdd_2.BBDD.BDAdaptador;
import com.example.bbdd_2.R;



public class MainActivity extends AppCompatActivity {


    private Button btnBuscar;
    private EditText textID;
    BDAdaptador bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadElements();
        bd.showEmpleados();

        btnBuscar.setOnClickListener(e->{
            int id = Integer.parseInt(textID.getText().toString());
            if (bd.isIn(id)){
                Intent intent;
                if (id==1)
                    intent = new Intent(e.getContext(), AdminActivity.class);
                else {
                    intent = new Intent(e.getContext(), UserActivity.class);
                    intent.putExtra("id", id);
                }
                startActivity(intent);
            }
            else{
                Toast.makeText(this, "Pruebe con un id de empleado válido.", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void loadElements() {
        btnBuscar = findViewById(R.id.btnBuscar);
        textID= findViewById(R.id.textID);
        bd = new BDAdaptador(getApplicationContext());
    }


}