package com.example.bbdd_2.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

        ;

        btnmostrarEmSalario.setOnClickListener(e->{
            setVisibleRVSalario();
            listaAllEmpleadosSalario=bd.findAll();
            recyclerViewSalario.setLayoutManager(new LinearLayoutManager(this));
            adapterSalario = new RVSalario(this,listaAllEmpleadosSalario);
            adapterSalario.setClickListener(this);
            recyclerViewSalario.setAdapter(adapterSalario);
        });

        btnMostrarEmDep.setOnClickListener(e->{
            Intent intent = new Intent(getApplicationContext(), DepartamentosActivity.class);
            startActivity(intent);
        });

        btnSalarioPlus.setOnClickListener(f->{
            setInvisbleRVSalario();
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("¿Estas seguro que quieres incrementar 10% todos los salarios?")
                    .setPositiveButton("si", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            bd.increase10();
                            dialogInterface.dismiss();
                        }
                    })
                    .setNegativeButton("no", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
            dialog.create().show();
        });

        btnEliminar.setOnClickListener(e->{
            Intent intent = new Intent(getApplicationContext(), EliminarActivity.class);
            startActivity(intent);
        });
    }

    private void setVisibleRVSalario() {
        recyclerViewSalario.setVisibility(View.VISIBLE);
    }

    private void setInvisbleRVSalario() {
        recyclerViewSalario.setVisibility(View.INVISIBLE);
    }


    private void loadElements() {
        bd = new BDAdaptador(getApplicationContext());
        listaEmpleadosByDepartamentos = new ArrayList<>();
        listaAllEmpleadosSalario = bd.findAll();

        recyclerViewSalario = findViewById(R.id.recyclerSalarios);

        btnEliminar = findViewById(R.id.buttonEliminar);
        btnMostrarEmDep = findViewById(R.id.buttonDepart);
        btnmostrarEmSalario = findViewById(R.id.buttonSalario);
        btnSalarioPlus = findViewById(R.id.button10);
    }



    @Override
    public void onItemClick(View activista, int posicion) {



    }
}