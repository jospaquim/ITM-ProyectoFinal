package com.example.itm_proyectofinal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnRegistrar,btnIniciar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        btnIniciar= findViewById(R.id.btn_Login_Intro);
        btnRegistrar= findViewById(R.id.btn_Register_Intro);

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
                mostrarDialogoPersonalizado();
            }
        });



    }


    public void mostrarDialogoPersonalizado(){
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater=getLayoutInflater();
        View view=inflater.inflate(R.layout.dialog_personalizado_intro,null);
        builder.setView(view);
        final AlertDialog dialog=builder.create();
        dialog.show();

        TextView txt=view.findViewById(R.id.text_dialog);
        txt.setText("¿Con que tipo de usuario te identificas?");

        Button btnUserAgri=view.findViewById(R.id.btnUserAgri);
        btnUserAgri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j= new Intent(getApplicationContext(), RegistroAgricultorActivity.class);
                startActivity(j);
            }
        });
        Button btnUserClasic= view.findViewById(R.id.btnUserClasic);
        btnUserClasic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k= new Intent(getApplicationContext(),RegistroClasicoActivity.class);
                startActivity(k);
            }
        });

        /*builder.setView(inflater.inflate(R.layout.dialog_personalizado_intro,null))
                .setPositiveButton("USUARIO AGRICULTOR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent j= new Intent(getApplicationContext(), RegistroAgricultorActivity.class);
                        startActivity(j);
                    }
                }).setNegativeButton("USUARIO CLASICO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent k= new Intent(getApplicationContext(),RegistroClasicoActivity.class);
                startActivity(k);
            }
        });*/
    }
}