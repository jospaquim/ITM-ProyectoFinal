package com.example.itm_proyectofinal;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.itm_proyectofinal.OpenHelper.SQLite_OpenHelper;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class RegistroAgricultorActivity extends AppCompatActivity {
    EditText date,etDni,etNombre,etApellido,celular,direccion,correo,pass1,pass2;
    Button btncrear,regresar;
    TextView login,textViewreg;
    Spinner sp_depa,sp_provi,sp_tip;
    public static final String clave="gdsawr";
    String[] arreglo_amazonas={"Elija una Provincia","Chachapoyas","Bagua","Bongara","Condorcanqui","Luya","Rodríguez de Mendoza","Utcubamba"};
    String[] arreglo_ancash={"Elija una Provincia","Huaraz","Aija","Antonio Raimondi","Asunción","Bolognesi","Carhuaz","Carlos Fermín Fitzcarrald","Casma","Corongo","Huari","Huarmey","Huaylas","Mariscal Luzuriaga","Ocros","Pallasca","Pomabamba","Recuay","Santa","Sihuas","Yungay"};
    String[] arreglo_apurimac={"Elija una Provincia","Abancay","Andahuaylas","Antabamba","Aymaraes","Cotabambas","Chincheros","Grau"};
    String[] arreglo_arequipa={"Elija una Provincia","Arequipa","Camaná","Caravelí","Castilla","Caylloma","Condesuyos","Islay","La Unión"};
    String[] arreglo_ayacucho={"Elija una Provincia","Huamanga","Cangallo","Huanca Sancos","Huanta","La Mar","Lucanas","Parinacochas","Páucar del Sara Sara","Sucre","Víctor Fajardo","Vilcashuamán"};
    String[] arreglo_cajamarca={"Elija una Provincia","Cajamarca","Cajabamba","Celendín","Chota","Contumazá","Cutervo","Hualgayoc","Jaén","San Ignacio","San Marcos","San Miguel","San Pablo","Santa Cruz"};
    String[] arreglo_callao={"Elija una Provincia","Prov. Const. del Callao"};
    String[] arreglo_cusco={"Elija una Provincia","Cuzco","Acomayo","Anta","Calca","Canas","Canchis","Chumbivilcas","Espinar","La Convención","Paruro","Paucartambo","Quispicanchi","Urubamba"};
    String[] arreglo_huancavelica={"Elija una Provincia","Huancavelica","Acobamba","Angaraes","Castrovirreyna","Churcampa","Huaytará","Tayacaja"};
    String[] arreglo_huanuco={"Elija una Provincia","Huánuco","Ambo","Dos de Mayo","Huacaybamba","Huamalíes","Leoncio Prado","Marañón","Pachitea","Puerto Inca","Lauricocha","Yarowilca"};
    String[] arreglo_ica={"Elija una Provincia","Ica","Chincha","Nazca","Palpa","Pisco"};
    String[] arreglo_junin={"Elija una Provincia","Huancayo","Chanchamayo","Chupaca","Concepción","Jauja","Junín","Satipo","Tarma","Yauli"};
    String[] arreglo_laLibertad={"Elija una Provincia","Trujillo","Ascope","Bolívar","Chepén","Gran Chimú","Julcán","Otuzco","Pacasmayo","Pataz","Sánchez Carrión","Santiago de Chuco","Virú"};
    String[] arreglo_lambayeque={"Elija una Provincia","Chiclayo","Ferreñafe","Lambayeque"};
    String[] arreglo_lima={"Elija una Provincia","Lima","Barranca","Cajatambo","Canta","Cañete","Huaral","Huarochirí","Huaura","Oyón","Yauyos"};
    String[] arreglo_loreto={"Elija una Provincia","Maynas","Alto Amazonas","Datem del Marañón","Loreto","Mariscal Ramón Castilla","Putumayo","Requena","Ucayali"};
    String[] arreglo_madreDios={"Elija una Provincia","Tambopata","Manu","Tahuamanu"};
    String[] arreglo_moquegua={"Elija una Provincia","Mariscal Nieto","General Sánchez Cerro","Ilo"};
    String[] arreglo_pasco={"Elija una Provincia","Pasco","Daniel Alcides Carrión","Oxapampa"};
    String[] arreglo_piura={"Elija una Provincia","Piura","Ayabaca","Huancabamba","Morropón","Paita","Sechura","Sullana","Talara"};
    String[] arreglo_puno={"Elija una Provincia","Puno","Azángaro","Carabaya","Chucuito","El Collao","Huancané","Lampa","Melgar","Moho","San Antonio de Putina","San Román","Sandia","Yunguyo"};
    String[] arreglo_sanMartin={"Elija una Provincia","Moyobamba","Bellavista","El Dorado","Huallaga","Lamas","Mariscal Cáceres","Picota","Rioja","San Martín","Tocache"};
    String[] arreglo_tacna={"Elija una Provincia","Tacna","Candarave","Jorge Basadre","Tarata"};
    String[] arreglo_tumbes={"Elija una Provincia","Tumbes","Contralmirante Villar","Zarumilla"};
    String[] arreglo_ucayali={"Elija una Provincia","Coronel Portillo","Atalaya","Padre Abad","Purús"};
    String[] arreglo_defecto={"Elija una Provincia"};

    String[] arreglo_tipo={"Elija un tipo de agricultor","Agricultor Tradicional","Agricultor Industrial"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_register);
        date=(EditText) findViewById(R.id.etUserDate);
        etDni=(EditText) findViewById(R.id.userDNI);
        etNombre=(EditText) findViewById(R.id.userName);
        etApellido=(EditText) findViewById(R.id.userLastName);
        celular=(EditText) findViewById(R.id.etUserPhone);
        correo=(EditText) findViewById(R.id.et_userMail_register);
        pass1=(EditText) findViewById(R.id.et_userPassword_register);
        pass2=(EditText) findViewById(R.id.et_Re_userPassword_register);
        direccion=(EditText) findViewById(R.id.etUserDireccion);
        sp_depa= (Spinner) findViewById(R.id.spinner_depa);
        sp_provi=(Spinner) findViewById(R.id.spinner_provincia);
        sp_tip=(Spinner) findViewById(R.id.spinner_tipo);
        btncrear=(Button) findViewById(R.id.btn_login);
        login=(TextView) findViewById(R.id.et_register_login);
        regresar=(Button) findViewById(R.id.btn_regresar_register);



        ArrayAdapter adapter= new ArrayAdapter(this, android.R.layout.simple_list_item_1,arreglo_tipo);
        sp_tip.setAdapter(adapter);

        ArrayAdapter adapters= new ArrayAdapter(this, android.R.layout.simple_list_item_1,arreglo_defecto);
        sp_provi.setAdapter(adapters);



        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });


        sp_depa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case (0):
                        ArrayAdapter adapters= new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,arreglo_defecto);
                        sp_provi.setAdapter(adapters);
                        break;
                    case(1):
                        ArrayAdapter adapter1= new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,arreglo_amazonas);
                        sp_provi.setAdapter(adapter1);
                        break;
                    case (2):
                        ArrayAdapter adapter2= new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,arreglo_ancash);
                        sp_provi.setAdapter(adapter2);
                        break;
                    case(3):
                        ArrayAdapter adapter3= new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,arreglo_arequipa);
                        sp_provi.setAdapter(adapter3);
                        break;
                    case (4):
                        ArrayAdapter adapter4= new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,arreglo_apurimac);
                        sp_provi.setAdapter(adapter4);
                        break;
                    case(5):
                        ArrayAdapter adapter5= new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,arreglo_ayacucho);
                        sp_provi.setAdapter(adapter5);
                        break;
                    case(6):
                        ArrayAdapter adapter6= new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,arreglo_cajamarca);
                        sp_provi.setAdapter(adapter6);
                        break;
                    case (7):
                        ArrayAdapter adapter7= new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,arreglo_callao);
                        sp_provi.setAdapter(adapter7);
                        break;
                    case(8):
                        ArrayAdapter adapter8= new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,arreglo_cusco);
                        sp_provi.setAdapter(adapter8);
                        break;
                    case(9):
                        ArrayAdapter adapter9= new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,arreglo_huancavelica);
                        sp_provi.setAdapter(adapter9);
                        break;

                    case (10):
                        ArrayAdapter adapter10= new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,arreglo_huanuco);
                        sp_provi.setAdapter(adapter10);
                        break;
                    case(11):
                        ArrayAdapter adapter11= new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,arreglo_ica);
                        sp_provi.setAdapter(adapter11);
                        break;
                    case(12):
                        ArrayAdapter adapter12= new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,arreglo_junin);
                        sp_provi.setAdapter(adapter12);
                        break;
                    case (13):
                        ArrayAdapter adapter13= new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,arreglo_laLibertad);
                        sp_provi.setAdapter(adapter13);
                        break;
                    case(14):
                        ArrayAdapter adapter14= new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,arreglo_lambayeque);
                        sp_provi.setAdapter(adapter14);
                        break;
                    case(15):
                        ArrayAdapter adapter15= new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,arreglo_lima);
                        sp_provi.setAdapter(adapter15);
                        break;
                    case (16):
                        ArrayAdapter adapter16= new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,arreglo_loreto);
                        sp_provi.setAdapter(adapter16);
                        break;
                    case(17):
                        ArrayAdapter adapter17= new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,arreglo_madreDios);
                        sp_provi.setAdapter(adapter17);
                        break;
                    case(18):
                        ArrayAdapter adapter18= new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,arreglo_moquegua);
                        sp_provi.setAdapter(adapter18);
                        break;
                    case (19):
                        ArrayAdapter adapter19= new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,arreglo_pasco);
                        sp_provi.setAdapter(adapter19);
                        break;
                    case(20):
                        ArrayAdapter adapter20= new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,arreglo_piura);
                        sp_provi.setAdapter(adapter20);
                        break;
                    case(21):
                        ArrayAdapter adapter21= new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,arreglo_puno);
                        sp_provi.setAdapter(adapter21);
                        break;
                    case (22):
                        ArrayAdapter adapter22= new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,arreglo_sanMartin);
                        sp_provi.setAdapter(adapter22);
                        break;
                    case(23):
                        ArrayAdapter adapter23= new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,arreglo_tacna);
                        sp_provi.setAdapter(adapter23);
                        break;
                    case (24):
                        ArrayAdapter adapter24= new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,arreglo_tumbes);
                        sp_provi.setAdapter(adapter24);
                        break;
                    case(25):
                        ArrayAdapter adapter25= new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,arreglo_ucayali);
                        sp_provi.setAdapter(adapter25);
                        break;


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
                //textViewreg.setMovementMethod(new ScrollingMovementMethod());
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);
            }
        });

        final SQLite_OpenHelper helper=new SQLite_OpenHelper(this);
        btncrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!date.getText().toString().isEmpty() && !etDni.getText().toString().isEmpty() && !etNombre.getText().toString().isEmpty() &&
                !etApellido.getText().toString().isEmpty() && !celular.getText().toString().isEmpty() && !correo.getText().toString().isEmpty() &&
                !pass1.getText().toString().isEmpty() && !pass2.getText().toString().isEmpty() && !direccion.getText().toString().isEmpty()){
                    if (etDni.getText().toString().length()!=8){
                        Toast.makeText(getApplicationContext(),"El DNI solo tiene 8 numeros, verifique su DNI",Toast.LENGTH_LONG).show();
                        etDni.setText("");
                        etDni.requestFocus();
                    }else {
                        if (celular.getText().toString().length() != 9) {
                            Toast.makeText(getApplicationContext(), "El numero de celular solo tiene 9 numeros, verificar sus datos", Toast.LENGTH_LONG).show();
                            celular.setText("");
                            celular.requestFocus();
                        }else{
                            if (sp_depa.getSelectedItemPosition()==0 || sp_provi.getSelectedItemPosition()==0 || sp_tip.getSelectedItemPosition()==0){
                                Toast.makeText(getApplicationContext(), "Debe elegir correctamente un Departamento, Distrito o Tipo", Toast.LENGTH_LONG).show();

                            }else{
                                if (pass1.getText().toString().equalsIgnoreCase(pass2.getText().toString())){
                                    Cursor c= helper.consultarAgricultorxCorreo(correo.getText().toString());
                                    if (c==null){
                                        SQLiteDatabase db=helper.getWritableDatabase();
                                        try{
                                            String pass_encr= encriptar(pass1.getText().toString(),clave);
                                            ContentValues values=new ContentValues();
                                            values.put("nombre_agri",etNombre.getText().toString());
                                            values.put("apellido_agri",etApellido.getText().toString());
                                            values.put("DNI_agri",etDni.getText().toString());
                                            values.put("fecha_naci_agri",date.getText().toString());
                                            values.put("celular_agri",celular.getText().toString());
                                            values.put("departamento_agri",sp_depa.getSelectedItem().toString());
                                            values.put("provincia_agri",sp_provi.getSelectedItem().toString());
                                            values.put("direccion_agri",direccion.getText().toString());
                                            values.put("tipo_agri",sp_tip.getSelectedItem().toString());
                                            values.put("correo_agri",correo.getText().toString());
                                            values.put("password_agri",pass_encr);
                                            values.put("fecha_registro_agri",obtenerFechaConFormato("YYYY-MM-dd HH:mm:ss","America/Lima"));

                                            db.insert("USUARIO_AGRICULTOR",null,values);
                                            Intent i= new Intent(getApplicationContext(),LoginActivity.class);
                                            startActivity(i);
                                        } catch (Exception e){
                                            e.printStackTrace();
                                        }




                                    }else{
                                        Toast.makeText(getApplicationContext(), "Ya existe el correo ingresado",Toast.LENGTH_LONG).show();
                                        correo.setText("");
                                        correo.requestFocus();
                                    }
                                }else{
                                    Toast.makeText(getApplicationContext(),"No coincide las contraseñas ingresadas",Toast.LENGTH_SHORT).show();
                                    pass2.setText("");
                                    pass2.requestFocus();
                                }
                            }
                        }
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Debe completar todos los campos",Toast.LENGTH_SHORT).show();

                    etDni.requestFocus();
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
