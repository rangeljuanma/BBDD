package com.example.bbdd_2.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bbdd_2.BBDD.BDAdaptador;
import com.example.bbdd_2.R;
import com.example.bbdd_2.models.Empleado;

public class EliminarActivity extends AppCompatActivity {

    BDAdaptador bd;
    EditText idEdit;
    Button btnElimi, btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);

        bd = new BDAdaptador(getApplicationContext());
        idEdit = findViewById(R.id.eliminarID);
        btnElimi = findViewById(R.id.buttoneliminar1);
        btnVolver = findViewById(R.id.buttonback2);


        btnElimi.setOnClickListener(e->{
            String nombre = bd.findByid(idEdit.getText().toString());
            bd.eliminarEmpleado(idEdit.getText().toString());
            Toast.makeText(this,"Borrando a: " + nombre, Toast.LENGTH_SHORT).show();
        });

        btnVolver.setOnClickListener(e->{
            finish();
        });

    }
}