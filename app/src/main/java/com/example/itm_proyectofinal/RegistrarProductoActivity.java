package com.example.itm_proyectofinal;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.itm_proyectofinal.OpenHelper.SQLite_OpenHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class RegistrarProductoActivity extends AppCompatActivity {

    Button regresar,foto,agregar;
    EditText nombre,precio,descip,stock;
    Spinner medida;
    int codAgri;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_add);
        regresar=(Button) findViewById(R.id.btn_regresar_prod_home);
        nombre=(EditText) findViewById(R.id.nombre_prodc);
        precio=(EditText) findViewById(R.id.precio_prod);
        descip=(EditText) findViewById(R.id.descripcion_prod);
        stock=(EditText) findViewById(R.id.stock_prod);
        medida=(Spinner) findViewById(R.id.unidaMedida_prod);
        foto=(Button) findViewById(R.id.btn_addPhotos);
        agregar=(Button) findViewById(R.id.btn_crear_prod);

        final SQLite_OpenHelper helper=new SQLite_OpenHelper(this);
        /*Bundle bundle=getIntent().getExtras();
        if (bundle.containsKey("idAgricultor")){
            codAgri= bundle.getInt("idAgricultor");
            //bundle.remove("Mensaje");
        }*/
        //nombre.setText(""+codAgri);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),HomeAgricultorActivity.class);
                i.putExtra("idAgricultor",codAgri);
                startActivity(i);
            }
        });


        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!nombre.getText().toString().isEmpty() && !precio.getText().toString().isEmpty() && !stock.getText().toString().isEmpty() &&
                !descip.getText().toString().isEmpty() && medida.getSelectedItemPosition()!=0){
                    Cursor c= helper.consultarProductoxNombre(nombre.getText().toString().toUpperCase(),codAgri);
                    String pr= descip.getText().toString().substring(0,1).toUpperCase();
                    String pr2=descip.getText().toString().substring(1).toLowerCase();
                    String total= pr+pr2;
                    if(c==null){
                        //agregar
                        SQLiteDatabase db=helper.getWritableDatabase();
                        try{
                            ContentValues values=new ContentValues();
                            values.put("nombre_prod",nombre.getText().toString().toUpperCase());
                            values.put("precio_prod",precio.getText().toString());
                            values.put("criterio_medida_pro",medida.getSelectedItem().toString());
                            values.put("stock_prod",stock.getText().toString());
                            values.put("foto_prod","xzczx");//por el momento foto es STRING
                            values.put("descrip_prod",total);
                            //values.put("fecha_registro_agri",obtenerFechaConFormato("YYYY-MM-dd HH:mm:ss","America/Lima"));

                            db.insert("PRODUCTO",null,values);
                            //Intent i= new Intent(getApplicationContext(),LoginActivity.class);
                            //startActivity(i);

                            //SE PUEDE UTILIZAR EL CURSOR O SINO MODIFICAR EL CURSOR TRAYENDO A TODOS Y IR HASTA EL ULTIMO REGISTRO Y CAPTURAR EL ID
                            Cursor c2= helper.obtenerIdProducto();


                            while (c2.moveToNext()){
                                //nombre.setText(""+c2.getInt(0));
                                SQLiteDatabase cd=helper.getWritableDatabase();
                                try{
                                    ContentValues valuess=new ContentValues();
                                    valuess.put("id_us_agric",codAgri);//falta
                                    valuess.put("id_produc",c2.getInt(0));//ver si esta bien
                                    valuess.put("fecha_reg",obtenerFechaConFormato("YYYY-MM-dd HH:mm:ss","America/Lima"));

                                    cd.insert("REGISTRO",null,valuess);
                                } catch (Exception e){
                                    e.printStackTrace();
                                }

                            }
                            Toast.makeText(getApplicationContext(), "Se registro correctamente el producto",Toast.LENGTH_LONG).show();
                            nombre.setText("");
                            precio.setText("");
                            descip.setText("");
                            stock.setText("");
                            medida.setSelection(0);//ver si esta bien
                            //faltaria la opcion de dejar en blanco la foto
                            nombre.requestFocus();



                            //nombre.setText("");
                            //precio.setText("");
                            //descip.setText(""+c2.getInt(0));
                            //stock.setText("");
                            //medida.setSelection(0);
                            /*if (c2!=null){
                                SQLiteDatabase cd=helper.getWritableDatabase();
                                try{
                                    ContentValues valuess=new ContentValues();
                                    valuess.put("id_us_agric",codAgri);//falta
                                    valuess.put("id_produc",c2.getInt(0));//ver si esta bien
                                    valuess.put("fecha_reg",obtenerFechaConFormato("YYYY-MM-dd HH:mm:ss","America/Lima"));

                                    cd.insert("REGISTRO",null,valuess);
                                } catch (Exception e){
                                    e.printStackTrace();
                                }
                            }*/
                        } catch (Exception e){
                            e.printStackTrace();
                        }

                    }else{
                        Toast.makeText(getApplicationContext(), "Ya existe el producto con ese nombre",Toast.LENGTH_LONG).show();
                        nombre.setText("");
                        nombre.requestFocus();
                    }

                }else{
                    Toast.makeText(getApplicationContext(),"Debe completar todos los campos",Toast.LENGTH_SHORT).show();

                    nombre.requestFocus();
                }
            }
        });




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
}
