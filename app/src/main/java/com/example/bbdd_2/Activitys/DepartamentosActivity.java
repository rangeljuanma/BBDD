package com.example.bbdd_2.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.bbdd_2.BBDD.BDAdaptador;
import com.example.bbdd_2.R;
import com.example.bbdd_2.RecyclerView.RVDepartamento;
import com.example.bbdd_2.RecyclerView.RVSalario;
import com.example.bbdd_2.models.Empleado;

import java.util.List;

public class DepartamentosActivity extends AppCompatActivity implements RVSalario.ItemClickListener {

    RecyclerView empleadosXDepa;
    RVDepartamento adapter;
    private List<Empleado> listaEmpleadosByDepartamentos;
    private Button btnIdDepart, btnBack;
    private EditText idDepaEdit;
    private BDAdaptador bd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departamentos);

        btnIdDepart = findViewById(R.id.buttondepa);
        idDepaEdit = findViewById(R.id.idDepartEdit);
        btnBack = findViewById(R.id.btnBack);
        empleadosXDepa = findViewById(R.id.recyclerviewDepa);
        bd= new BDAdaptador(getApplicationContext());

        btnIdDepart.setOnClickListener(e->{
            listaEmpleadosByDepartamentos = bd.findAllByDepartamento(Integer.parseInt(idDepaEdit.getText().toString()));
            empleadosXDepa.setLayoutManager(new LinearLayoutManager(this));
            adapter = new RVDepartamento(this,listaEmpleadosByDepartamentos);
            adapter.setClickListener(this);
            empleadosXDepa.setAdapter(adapter);
        });
    btnBack.setOnClickListener(f->{
        finish();
    });
    }


    @Override
    public void onItemClick(View activista, int posicion) {

    }
}