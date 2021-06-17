package com.example.itm_proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.itm_proyectofinal.OpenHelper.SQLite_OpenHelper;

public class MiCuentaAgricultorActivity extends AppCompatActivity {
    int codAgri;
    TextView date,etDni,etNombre,etApellido,celular,direccion,correo,tipo,depar,prov,fech_reg;
    Button editar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_cuenta_agricultor);
        etNombre=(TextView) findViewById(R.id.idnombre_ver);
        etApellido=(TextView) findViewById(R.id.idapellido_ver);
        etDni=(TextView) findViewById(R.id.idDNI_ver);
        date=(TextView) findViewById(R.id.idFecha_Nac_ver);
        celular=(TextView) findViewById(R.id.idCelular_ver);
        depar=(TextView) findViewById(R.id.id_depart_ver);
        prov=(TextView) findViewById(R.id.idProvincia_ver);
        direccion=(TextView) findViewById(R.id.idDirec_ver);
        tipo=(TextView) findViewById(R.id.idTipo_ver);
        correo=(TextView) findViewById(R.id.idCorreo_ver);
        fech_reg=(TextView) findViewById(R.id.idfechaReg_ver);
        editar=(Button) findViewById(R.id.btn_editar_perfil_agri);

        Bundle bundle=getIntent().getExtras();
        if (bundle.containsKey("idAgricultor")){
            codAgri= bundle.getInt("idAgricultor");
        }

        final SQLite_OpenHelper helper=new SQLite_OpenHelper(this);
        Cursor c= helper.obtenerUsuarioAgri(codAgri);

        while (c.moveToNext()) {
            etNombre.setText(c.getString(1));
            etApellido.setText(c.getString(2));
            etDni.setText(c.getString(3));
            date.setText(c.getString(4));
            celular.setText(c.getString(5));
            depar.setText(c.getString(6));
            prov.setText(c.getString(7));
            direccion.setText(c.getString(8));
            tipo.setText(c.getString(9));
            correo.setText(c.getString(10));
            fech_reg.setText(c.getString(12));
        }

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent i= new Intent(getApplicationContext(),EditarMicuentaActivity.class);
                startActivity(i);*/
            }
        });
    }
}