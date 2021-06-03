package com.example.itm_proyectofinal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnRegistrar,btnIniciar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        btnIniciar= (Button) findViewById(R.id.btn_Login_Intro);
        btnRegistrar=(Button) findViewById(R.id.btn_Register_Intro);

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);
            }
        });
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] opciones= {"SI","NO"};
                final AlertDialog.Builder alertOpciones= new AlertDialog.Builder(MainActivity.this);
                alertOpciones.setTitle("¿Desea buscar el registro por correo?");
                alertOpciones.setItems(opciones, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (opciones[which].equals("SI")){



                        }else {
                            if (opciones[which].equals("NO")){
                                dialog.dismiss(); //cerrar el dialogo
                            }
                        }
                    }
                });
                alertOpciones.show(); //Mostrar alerta
            }
        });

            }
        });


    }
}