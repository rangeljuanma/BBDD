package com.example.bbdd_2.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.bbdd_2.BBDD.BDAdaptador;
import com.example.bbdd_2.R;
import com.example.bbdd_2.RecyclerView.RVDepartamento;
import com.example.bbdd_2.RecyclerView.RVSalario;
import com.example.bbdd_2.models.Empleado;

import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends AppCompatActivity implements RVSalario.ItemClickListener {
    private RecyclerView recyclerViewSalario, recyclerViewDepa;
    private RVSalario adapterSalario;
    private RVDepartamento adapterDepa;
    private List<Empleado> listaEmpleadosByDepartamentos, listaAllEmpleadosSalario;
    BDAdaptador bd;

    Button btnSalarioPlus, btnEliminar, btnmostrarEmSalario, btnMostrarEmDep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        loadElements();

        recyclerViewSalario.setLayoutManager(new LinearLayoutManager(this));
        adapterSalario = new RVSalario(this,listaAllEmpleadosSalario);
        adapterSalario.setClickListener(this);
        recyclerViewSalario.setAdapter(adapterSalario);

        btnmostrarEmSalario.setOnClickListener(e->{
            setVisibleRVSalario();
        });

        btnMostrarEmDep.setOnClickListener(e->{
            setVisibleRVDepa();
            recyclerViewDepa.setLayoutManager(new LinearLayoutManager(this));
            adapterDepa = new RVDepartamento(this,listaEmpleadosByDepartamentos);
            adapterDepa.setClickListener(this);
            recyclerViewDepa.setAdapter(adapterDepa);
        });
    }

    private void setVisibleRVSalario() {
        recyclerViewSalario.setVisibility(View.VISIBLE);
        recyclerViewDepa.setVisibility(View.INVISIBLE);
    }
    private void setVisibleRVDepa() {
        recyclerViewSalario.setVisibility(View.INVISIBLE);
        recyclerViewDepa.setVisibility(View.VISIBLE);
    }

    private void loadElements() {
        bd = new BDAdaptador(getApplicationContext());
        listaEmpleadosByDepartamentos = new ArrayList<>();
        listaAllEmpleadosSalario = bd.findAll();


        recyclerViewSalario = findViewById(R.id.recyclerDepartmentos);
        recyclerViewDepa = findViewById(R.id.recyclerviewDepa);
        btnEliminar = findViewById(R.id.buttonEliminar);
        btnMostrarEmDep = findViewById(R.id.buttonDepart);
        btnmostrarEmSalario = findViewById(R.id.buttonSalario);
        btnSalarioPlus = findViewById(R.id.button10);
    }

    @Override
    public void onItemClick(View activista, int posicion) {



    }
}