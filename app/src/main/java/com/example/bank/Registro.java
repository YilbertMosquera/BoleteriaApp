package com.example.bank;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registro extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference().child("USUARIO");

    Button btn_volver;
    Button btn_registrar;
    EditText _Id;
    EditText _nombre;
    EditText _apellido;
    EditText _telefono;
    EditText _correo;
    EditText _clave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro);
         btn_volver = findViewById(R.id.volver);
         btn_registrar = findViewById(R.id.btn_Guardar);
         _Id = findViewById(R.id.Id);
         _nombre = findViewById(R.id.nombre);
         _apellido = findViewById(R.id.apellido);
         _telefono = findViewById(R.id.telefono);
         _correo = findViewById(R.id.correo);



        btn_volver.setOnClickListener(v -> navegarAInicio());


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {

                registrarUsuario();

            }
        });


    }

    private void navegarAInicio() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    public void registrarUsuario(){

        String ID = _Id.getText().toString();

        DatabaseReference nuevoUsuario = reference.child(String.valueOf(ID));

        int id = Integer.parseInt(_Id.getText().toString());
        nuevoUsuario.child("Id").setValue(id);
        String nombre = _nombre.getText().toString();
        nuevoUsuario.child("Nombre").setValue(nombre);
        String apellido = _apellido.getText().toString();
        nuevoUsuario.child("Apellido").setValue(apellido);
        String telefono = _telefono.getText().toString();
        nuevoUsuario.child("Telefono").setValue(telefono);
        String correo = _correo.getText().toString();
        nuevoUsuario.child("Correo").setValue(correo);
        String clave = _clave.getText().toString();
        nuevoUsuario.child("Clave").setValue(clave);


        Toast.makeText(getApplicationContext(),"El usuario se ha creado correctamente", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }


}