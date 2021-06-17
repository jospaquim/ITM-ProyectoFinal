package com.example.itm_proyectofinal;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.itm_proyectofinal.OpenHelper.SQLite_OpenHelper;

public class ModificarProductoActivity extends AppCompatActivity {
    Button regresar,modificar,foto;
    EditText nombre,precio,descip,stock;
    Spinner medida;
    int codAgri,codProd;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifcar_producto);

        regresar=(Button) findViewById(R.id.btn_regresar_list_prod);
        nombre=(EditText) findViewById(R.id.nombre_prodc_modificar);
        precio=(EditText) findViewById(R.id.precio_prod_modificar);
        descip=(EditText) findViewById(R.id.descripcion_prod_modificar);
        stock=(EditText) findViewById(R.id.stock_prod_modificar);
        medida=(Spinner) findViewById(R.id.unidaMedida_prod_modificar);
        foto=(Button) findViewById(R.id.btn_addPhotos_modificar);
        modificar=(Button) findViewById(R.id.btn_crear_prod_modificar);

        final SQLite_OpenHelper helper=new SQLite_OpenHelper(this);
        Bundle bundle=getIntent().getExtras();
        if (bundle.containsKey("idAgricultor")){
            codAgri= bundle.getInt("idAgricultor");
            //bundle.remove("Mensaje");
        }
        if (bundle.containsKey("idProducto")){
            codProd= bundle.getInt("idProducto");
            //bundle.remove("Mensaje");
        }
        nombre.setText(""+codProd);
        descip.setText(""+codAgri);

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),VerProductosAgricultorActivity.class);
                i.putExtra("idAgricultor",codAgri);
                startActivity(i);
            }
        });

        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!nombre.getText().toString().isEmpty() && !precio.getText().toString().isEmpty() && !stock.getText().toString().isEmpty() &&
                        !descip.getText().toString().isEmpty() && medida.getSelectedItemPosition()!=0){
                    Cursor c= helper.consultarProductoxNombre(nombre.getText().toString().toUpperCase(),codAgri);
                    String pr= descip.getText().toString().substring(0,1).toUpperCase();
                    String pr2=descip.getText().toString().substring(1).toLowerCase();
                    String total= pr+pr2;
                    if(c==null){
                        SQLiteDatabase db= helper.getReadableDatabase();
                        /*String[] projection={
                                "id_prod",
                                "nombre_prod",
                                "precio_prod",
                                "criterio_medida_pro",
                                "stock_prod",
                                "foto_prod",
                                "descrip_prod"
                        };*/
                        //new value for column
                        ContentValues values= new ContentValues();
                        values.put("nombre_prod",nombre.getText().toString().toUpperCase());
                        values.put("precio_prod",precio.getText().toString());
                        values.put("criterio_medida_pro",medida.getSelectedItem().toString());
                        values.put("stock_prod",stock.getText().toString());
                        values.put("foto_prod","ysyss");//por el momento foto es STRING
                        values.put("descrip_prod",total);

                        //which row to update based on the title
                        String selection="id_prod= ?";
                        String[] selectionArgs={""+codProd};
                        //Cursor c= db.query("PRODUCTO",projection,selection,selectionArgs,null,null,null);

                            db.update(
                                    "PRODUCTO",
                                    values,
                                    selection,
                                    selectionArgs
                            );
                            //Toast.makeText(getApplicationContext(),"Registro modificado satisfactoriamente!!!!",Toast.LENGTH_LONG).show();
                        //nombre.setText("");
                        //precio.setText("");
                        //descip.setText("");
                        //stock.setText("");
                        //medida.setSelection(0);//ver si esta bien
                        //faltaria la opcion de dejar en blanco la foto
                        //nombre.requestFocus();

                        Intent i= new Intent(getApplicationContext(),VerProductosAgricultorActivity.class);
                        i.putExtra("idAgricultor",codAgri);
                        startActivity(i);



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
}
