package com.example.itm_proyectofinal;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.itm_proyectofinal.OpenHelper.SQLite_OpenHelper;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin,regresar;
    EditText eCorreo,ePassword;
    TextView registrarse;
    public static final String clave="gdsawr";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        eCorreo=(EditText) findViewById(R.id.et_userMail_general);
        ePassword=(EditText) findViewById(R.id.et_userPassword_general);
        btnLogin=(Button) findViewById(R.id.btn_iniciar_sesion);
        registrarse=(TextView) findViewById(R.id.et_regresar_general);
        regresar=(Button) findViewById(R.id.btn_regresar_register);

        final SQLite_OpenHelper helper= new SQLite_OpenHelper(this);

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!eCorreo.getText().toString().isEmpty() && !ePassword.getText().toString().isEmpty()){

                    try{
                        String pass_encr= encriptar(ePassword.getText().toString(),clave);
                        Cursor cursorClasico= helper.consultarUsuariosClasico(eCorreo.getText().toString(),pass_encr);
                        Cursor cursorAgricultor=helper.consultarUsuariosAgricultor(eCorreo.getText().toString(),pass_encr);
                        if (cursorClasico.getCount()>0){
                            Intent i= new Intent(getApplicationContext(),HomeClasicoActivity.class);
                            //i.putExtra("idClasico",cursorClasico.getColumnIndex("id_us_cla"));
                            while (cursorClasico.moveToNext()){
                                int codCla= cursorClasico.getInt(0);
                                i.putExtra("idClasico",codCla);
                            }
                            startActivity(i);

                        }else{
                            if (cursorAgricultor.getCount()>0){
                                Intent i= new Intent(getApplicationContext(),HomeAgricultorActivity.class);
                                //i.putExtra("idAgricultor",cursorAgricultor.getColumnIndex("id_us_agri"));
                                while (cursorAgricultor.moveToNext()){
                                    int codAgri= cursorAgricultor.getInt(0);
                                    i.putExtra("idAgricultor",codAgri);
                                }

                                startActivity(i);
                            }else {
                                Toast.makeText(getApplicationContext(),"DATOS INVALIDOS, VERIFICAR!!!",Toast.LENGTH_LONG).show();
                            }
                        }
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Debe completar todos los campos",Toast.LENGTH_SHORT).show();

                }

            }
        });
        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogoPersonalizado();
                //Intent i= new Intent(getApplicationContext(),RegistroClasicoActivity.class);
                //startActivity(i);
            }
        });


    }
    private String encriptar(String datos, String password) throws Exception{
        SecretKeySpec secretKey = generateKey(password);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] datosEncriptadosBytes = cipher.doFinal(datos.getBytes());
        String datosEncriptadosString = Base64.encodeToString(datosEncriptadosBytes, Base64.DEFAULT);
        return datosEncriptadosString;
    }
    /*private String desencriptar(String datos, String password) throws Exception{
        SecretKeySpec secretKey = generateKey(password);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] datosDescoficados = Base64.decode(datos, Base64.DEFAULT);
        byte[] datosDesencriptadosByte = cipher.doFinal(datosDescoficados);
        String datosDesencriptadosString = new String(datosDesencriptadosByte);
        return datosDesencriptadosString;
    }*/
    private SecretKeySpec generateKey(String password) throws Exception{
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        byte[] key = password.getBytes("UTF-8");
        key = sha.digest(key);
        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        return secretKey;
    }




    public void mostrarDialogoPersonalizado(){
        AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);
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
