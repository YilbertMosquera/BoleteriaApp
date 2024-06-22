package com.example.bank;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuthException;

public class MainActivity2 extends AppCompatActivity {

    Button btnRegistrar;
    Button btnAcceder;
    EditText Correo;
    EditText Contraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnAcceder = findViewById(R.id.btnAcceder);
        Correo = findViewById(R.id.editTextEmail);
        Contraseña = findViewById(R.id.editTextContraseña);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Setup
        setup()
    }

    private fun setup() {

        title = "Autenticacion"
        btnRegistrar.setOnClickListener{
            if(Correo.getText().isNotEmpty() && Contraseña.getText().isNotEmpty() ){
                FirebaseAuthException.getInstance().createUserWithEmailAndPassword(Correo.getText().toString(),
                        Contraseña.getText().toString()).addOnCompleteListener {

                    if(it.isSuccessful){

                    }else{
                        showAlert();
                    }
                }

            }
        }

    }

    private  fun showAlert(){

        val builder = AlertDialog.Builder(this);
        builder.setTitle("Error");
        builder.setMessage("Se ha producido un error autenticando al usuario");
        builder.setPositiveButton("Aceptar", null);
        val dialog: AlertDialog = builder.create();
        dialog.show()

    }
}