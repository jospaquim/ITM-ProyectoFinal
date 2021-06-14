package com.example.itm_proyectofinal;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class RegistroAgricultorActivity extends AppCompatActivity {
    EditText date,etDni,etNombre,etApellido,celular,provincia,distrito,direccion,descripcion,tipo,correo,pass1,pass2;
    Button btnLogin,regresar;
    TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_register);
        date=(EditText) findViewById(R.id.etUserDate);
        etDni=(EditText) findViewById(R.id.et_nameProduct);
        etNombre=(EditText) findViewById(R.id.et_productPrice);
        etApellido=(EditText) findViewById(R.id.et_productStock);
        celular=(EditText) findViewById(R.id.etUserPhone);
        correo=(EditText) findViewById(R.id.et_userMail_register);
        pass1=(EditText) findViewById(R.id.et_userPassword_register);
        pass2=(EditText) findViewById(R.id.et_Re_userPassword_register);
        direccion=(EditText) findViewById(R.id.et_productDescription);
        btnLogin=(Button) findViewById(R.id.btn_addProduct);
        login=(TextView) findViewById(R.id.et_register_login);
        regresar=(Button) findViewById(R.id.btn_regresar_register);

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });




        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);
            }
        });

        /*btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/




    }


    public void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate = twoDigits(day) + "/" + twoDigits(month+1) + "/" + year;
                String d1= year+"-"+twoDigits(month+1)+"-"+twoDigits(day);
                String fecha_act= obtenerFechaConFormato("YYYY-MM-dd","America/Lima");
                //int anio_act=  Integer.valueOf(fecha_act.split("-")[0]);

                int diferencia= Integer.valueOf(fecha_act.split("-")[0])- Integer.valueOf(d1.split("-")[0]);


                if (fecha_act.compareTo(d1)<0){
                    Toast.makeText(getApplicationContext(),"La fecha seleccionada es una fecha futura",Toast.LENGTH_LONG).show();
                }else{
                    if (diferencia<18){
                        Toast.makeText(getApplicationContext(),"TIENE MENOS DE 18 AÑOS",Toast.LENGTH_LONG).show();
                    }else{
                        date.setText(selectedDate);
                    }
                }

            }
        });

        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    private String twoDigits(int n) {
        return (n<=9) ? ("0"+n) : String.valueOf(n);
    }

    @SuppressLint("SimpleDateFormat")
    public static String obtenerFechaConFormato(String formato, String zonaHoraria) {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat(formato);
        sdf.setTimeZone(TimeZone.getTimeZone(zonaHoraria));
        return sdf.format(date);
    }



    private String encriptar(String datos, String password) throws Exception{
        SecretKeySpec secretKey = generateKey(password);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] datosEncriptadosBytes = cipher.doFinal(datos.getBytes());
        String datosEncriptadosString = Base64.encodeToString(datosEncriptadosBytes, Base64.DEFAULT);
        return datosEncriptadosString;
    }
    private SecretKeySpec generateKey(String password) throws Exception{
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        byte[] key = password.getBytes("UTF-8");
        key = sha.digest(key);
        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        return secretKey;
    }
}
