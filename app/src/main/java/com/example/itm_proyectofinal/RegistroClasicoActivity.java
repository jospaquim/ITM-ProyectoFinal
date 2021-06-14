package com.example.itm_proyectofinal;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.itm_proyectofinal.OpenHelper.SQLite_OpenHelper;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class RegistroClasicoActivity extends AppCompatActivity {
    Button btnLogin,regresar;
    EditText eDni,name,apellido,celular,date,email,pass1,pass2;
    TextView inicio,textViewreg;
    ListView lst;
    public static final String clave="gdsawr";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        eDni= findViewById(R.id.userDNI);
        name= findViewById(R.id.userName);
        apellido= findViewById(R.id.userLastName);
        celular= findViewById(R.id.etUserPhone);
        date= findViewById(R.id.etUserDate);
        email= findViewById(R.id.et_userMail_register);
        pass1=findViewById(R.id.et_userPassword_register);
        pass2=findViewById(R.id.et_ConfirmPassword_register);
        btnLogin= findViewById(R.id.btn_login);
        inicio= findViewById(R.id.et_register_login);
        regresar=findViewById(R.id.btn_regresar_register);
        //lst=(ListView) findViewById(R.id.list);
        //lst.setAdapter();



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
                //textViewreg.setMovementMethod(new ScrollingMovementMethod());

            }
        });

        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);
            }
        });
        final SQLite_OpenHelper helper=new SQLite_OpenHelper(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!eDni.getText().toString().isEmpty() && !name.getText().toString().isEmpty() && !apellido.getText().toString().isEmpty()
                && !celular.getText().toString().isEmpty() && !date.getText().toString().isEmpty() && !email.getText().toString().isEmpty()
                && !pass1.getText().toString().isEmpty() && !pass2.getText().toString().isEmpty()){
                    if (eDni.getText().toString().length()!=8){
                        Toast.makeText(getApplicationContext(),"El DNI solo tiene 8 numeros, verifique su DNI",Toast.LENGTH_LONG).show();
                        eDni.setText("");
                        eDni.requestFocus();
                    }else{
                        if (celular.getText().toString().length()!=9){
                            Toast.makeText(getApplicationContext(),"El numero de celular solo tiene 9 numeros, verificar sus datos",Toast.LENGTH_LONG).show();
                            celular.setText("");
                            celular.requestFocus();
                        }else{
                            if (pass1.getText().toString().equalsIgnoreCase(pass2.getText().toString())){
                                Cursor c= helper.consultarUsuarioxCorreo(email.getText().toString());
                                if (c==null){
                                    SQLiteDatabase db=helper.getWritableDatabase();
                                    try{
                                        String pass_encr= encriptar(pass1.getText().toString(),clave);
                                        ContentValues values=new ContentValues();
                                        values.put("nombre_cla",name.getText().toString());
                                        values.put("apellido_cla",apellido.getText().toString());
                                        values.put("DNI_cla",eDni.getText().toString());
                                        values.put("fecha_naci_cla",date.getText().toString());
                                        values.put("celular_cla",celular.getText().toString());
                                        values.put("coreo_cla",email.getText().toString());
                                        values.put("password_cla",pass_encr);
                                        values.put("fecha_registro_cla",obtenerFechaConFormato("YYYY-MM-dd HH:mm:ss","America/Lima"));

                                        db.insert("USUARIO_CLASICO",null,values);
                                        Intent i= new Intent(getApplicationContext(),LoginActivity.class);
                                        startActivity(i);
                                    } catch (Exception e){
                                        e.printStackTrace();
                                    }




                                }else{
                                    Toast.makeText(getApplicationContext(), "Ya existe el correo ingresado",Toast.LENGTH_LONG).show();
                                    email.setText("");
                                    email.requestFocus();
                                }
                            }else{
                                Toast.makeText(getApplicationContext(),"No coincide las contraseñas ingresadas",Toast.LENGTH_SHORT).show();
                                pass2.setText("");
                                pass2.requestFocus();
                            }


                        }
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Debe completar todos los campos",Toast.LENGTH_SHORT).show();

                    eDni.requestFocus();
                }
            }
        });









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
