package com.example.bbdd_2.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bbdd_2.BBDD.BDAdaptador;
import com.example.bbdd_2.R;
import com.example.bbdd_2.models.Empleado;

public class UserActivity extends AppCompatActivity {
    TextView nombreText, idText, departamentoText, phoneText, emailText;
    BDAdaptador bd;
    Empleado empleado;
    Button modifyEmail, modifyPhone, saveEmail, savePhone;
    EditText emailEditText, editTextPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        loadElements();
        Intent intent = getIntent();
        int id = intent.getIntExtra("id",0);
        empleado=bd.findAllById(id);
        putEmpleadoText(empleado);


        modifyPhone.setOnClickListener(e->{
            setPhoneUpdateVisible();
        });
        modifyEmail.setOnClickListener(e->{
            setEmailUpdateVisible();
        });

        savePhone.setOnClickListener(e->{
            bd.modifyPhoneEmployee(id, editTextPhone.getText().toString());
            setPhoneUpdateInvisble();
        });

        saveEmail.setOnClickListener(e->{
            bd.modifyEmail(id,emailEditText.getText().toString());
            setEmailUpdateInvisible();
        });


    }

    private void setEmailUpdateVisible() {
        emailEditText.setVisibility(View.VISIBLE);
        saveEmail.setVisibility(View.VISIBLE);
    }

    private void setPhoneUpdateVisible() {
        editTextPhone.setVisibility(View.VISIBLE);
        savePhone.setVisibility(View.VISIBLE);
    }

    private void setPhoneUpdateInvisble() {
        editTextPhone.setVisibility(View.INVISIBLE);
        savePhone.setVisibility(View.INVISIBLE);
    }

    private void setEmailUpdateInvisible() {
        emailEditText.setVisibility(View.INVISIBLE);
        saveEmail.setVisibility(View.INVISIBLE);
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
        modifyEmail= findViewById(R.id.modifyEmail);
        modifyPhone= findViewById(R.id.modifyPhone);
        savePhone= findViewById(R.id.savePhone);
        saveEmail= findViewById(R.id.saveEmail);
        editTextPhone= findViewById(R.id.editTextPhone);
        emailEditText= findViewById(R.id.emailEditText);
        phoneText= findViewById(R.id.phoneText);
        emailText= findViewById(R.id.emailText);








    }
}