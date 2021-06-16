package com.example.itm_proyectofinal;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.itm_proyectofinal.OpenHelper.SQLite_OpenHelper;
import com.google.android.material.navigation.NavigationView;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin,regresar;
    EditText eCorreo,ePassword;
    TextView registrarse;
    public static final String clave="gdsawr";

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        eCorreo=(EditText) findViewById(R.id.et_userMail_cla);
        ePassword=(EditText) findViewById(R.id.et_userPassword_cla);
        btnLogin=(Button) findViewById(R.id.btn_crear_cla);
        registrarse=(TextView) findViewById(R.id.et_register_login);
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
                            i.putExtra("SesionClasica",cursorClasico.getColumnIndex("id_us_cla"));
                            startActivity(i);

                        }else{
                            if (cursorAgricultor.getCount()>0){
                                Intent i= new Intent(getApplicationContext(),HomeAgricultorActivity.class);
                                i.putExtra("SesionAgricultor",cursorAgricultor.getColumnIndex("id_us_agri"));

                                startActivity(i);

                               /* DrawerLayout drawer = findViewById(R.id.drawer_layout);
                                NavigationView navigationView = findViewById(R.id.nav_view);
                                // Passing each menu ID as a set of Ids because each
                                // menu should be considered as top level destinations.
                                mAppBarConfiguration = new AppBarConfiguration.Builder(
                                        R.id.nav_home, R.id.nav_pedido, R.id.nav_producto, R.id.nav_my_account)
                                        .setOpenableLayout(drawer)
                                        .build();
                                NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
                                NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
                                NavigationUI.setupWithNavController(navigationView, navController);*/

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
                Intent i = new Intent(getApplicationContext(),HomeAgricultorActivity.class);
                startActivity(i);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}
