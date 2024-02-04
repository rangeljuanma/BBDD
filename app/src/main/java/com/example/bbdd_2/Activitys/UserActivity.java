package com.example.bbdd_2.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bbdd_2.BBDD.BDAdaptador;
import com.example.bbdd_2.R;
import com.example.bbdd_2.models.Empleado;

public class UserActivity extends AppCompatActivity {
    TextView nombreText, idText, departamentoText;
    BDAdaptador bd;
    Empleado empleado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        loadElements();
        Intent intent = getIntent();
        int id = intent.getIntExtra("id",0);
        empleado=bd.findAllById(id);

        putEmpleadoText(empleado);
    }

    private void putEmpleadoText(Empleado empleado) {
        idText.setText(String.valueOf(empleado.getId()));
        nombreText.setText(empleado.getNombre());
        departamentoText.setText(empleado.getDepartamento());
    }

    private void loadElements() {
        bd = new BDAdaptador(getApplicationContext());
        idText= findViewById(R.id.idText);
        nombreText= findViewById(R.id.nombreText);
        departamentoText= findViewById(R.id.departamentoText);

    }
}