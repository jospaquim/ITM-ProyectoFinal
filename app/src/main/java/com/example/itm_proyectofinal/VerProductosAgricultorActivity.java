package com.example.itm_proyectofinal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itm_proyectofinal.Adapter.AdapterProductosAgricultor;
import com.example.itm_proyectofinal.Beans.Producto;
import com.example.itm_proyectofinal.OpenHelper.SQLite_OpenHelper;

import java.util.ArrayList;

public class VerProductosAgricultorActivity extends AppCompatActivity {

    ArrayList<Producto> lstProducto;
    SQLite_OpenHelper helper;
    AdapterProductosAgricultor adaptador;
    RecyclerView ryc;
    Button modificar;
    int codAgri;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mis_productos_agricultor);


        Bundle bundle=getIntent().getExtras();
        if (bundle.containsKey("idAgricultor")){
            codAgri= bundle.getInt("idAgricultor");
        }

        helper= new SQLite_OpenHelper(this);
        lstProducto= new ArrayList<>();
        ryc=(RecyclerView) findViewById(R.id.recyclerProdAgri);


        listar();

    }


    public void listar(){
        SQLiteDatabase db= helper.getReadableDatabase();
        Producto pro=null;
        Cursor cursor= db.rawQuery("SELECT P.id_prod,P.nombre_prod,P.precio_prod,P.criterio_medida_pro,P.stock_prod,P.foto_prod,P.descrip_prod FROM REGISTRO R INNER JOIN PRODUCTO P ON R.id_produc=P.id_prod WHERE R.id_us_agric="+codAgri,null);
        //Cursor cursor= db.rawQuery("SELECT * FROM "+ Estructura_BBDD.TABLE_NAME,null);
        while (cursor.moveToNext()){
            pro= new Producto();
            pro.setCodigo(cursor.getInt(0));
            pro.setNombre(cursor.getString(1));
            pro.setPrecio(cursor.getDouble(2));
            pro.setCriterio_medida(cursor.getString(3));
            pro.setStock(cursor.getInt(4));
            pro.setImagen(cursor.getString(5));
            pro.setDescripcion(cursor.getString(6));
            pro.setCodAgri(codAgri);
            lstProducto.add(pro);
        }

        ryc.setLayoutManager(new LinearLayoutManager(VerProductosAgricultorActivity.this));

        adaptador= new AdapterProductosAgricultor(lstProducto,this);
        ryc.setAdapter(adaptador);

    }

}
