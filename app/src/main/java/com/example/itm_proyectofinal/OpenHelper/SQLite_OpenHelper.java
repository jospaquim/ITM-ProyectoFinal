package com.example.itm_proyectofinal.OpenHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.itm_proyectofinal.BD.Estructura_BBDD;

public class SQLite_OpenHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "freshmarket.dbd";

    public SQLite_OpenHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Estructura_BBDD.SQL_CREATE_USER_CLASICO);
        db.execSQL(Estructura_BBDD.SQL_CREATE_USER_AGRICULTOR);
        //db.execSQL();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Estructura_BBDD.SQL_DELETE_USER_CLASICO);
        db.execSQL(Estructura_BBDD.SQL_DELETE_USER_AGRICULTOR);
        onCreate(db);
    }


    public Cursor consultarUsuarioxCorreo(String correo){
        SQLiteDatabase bd= this.getReadableDatabase();
        String[] projection={
                "nombre_cla",
                "apellido_cla",
                "DNI_cla",
                "fecha_naci_cla",
                "celular_cla",
                "coreo_cla",
                "password_cla",
                "fecha_registro_cla"

        };
        String selection="coreo_cla"+" like ?";
        String[] selectionArgs={correo};
        Cursor c= bd.query("USUARIO_CLASICO",
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null);
        if(c.getCount()>0){
            return c;
        }else{
            return null;
        }

    }



    public Cursor consultarUsuariosClasico(String usu, String cla){
        SQLiteDatabase bd= this.getReadableDatabase();
        String[] projection={
                "nombre_cla",
                "apellido_cla",
                "DNI_cla",
                "fecha_naci_cla",
                "celular_cla",
                "coreo_cla",
                "password_cla",
                "fecha_registro_cla"
        };
        String selection="coreo_cla"+" like ? and "+"password_cla"+" like ?";
        String[] selectionArgs={usu,cla};
        Cursor c= bd.query("USUARIO_CLASICO",
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null);
        return c;

    }
    public Cursor consultarUsuariosAgricultor(String usu, String cla){
        SQLiteDatabase bd= this.getReadableDatabase();
        String[] projection={
                "nombre_agri",
                "apellido_agri",
                "DNI_agri",
                "fecha_naci_agri",
                "celular_agri",
                "provincia_agri",
                "distrito_agri",
                "direccion_agri",
                "descrip_cultivo_agri",
                "tipo_agri",
                "correo_agri",
                "password_agri",
                "fecha_registro_agri"
        };
        String selection="correo_agri"+" like ? and "+"password_agri"+" like ?";
        String[] selectionArgs={usu,cla};
        Cursor c= bd.query("USUARIO_AGRICULTOR",
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null);
        return c;

    }


    /*public  void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db,oldVersion,newVersion);
    }
    public void openBD(){
        this.getWritableDatabase();

    }*/
    /*public Cursor consultarUsuarios(String usu, String cla){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {
                Estructura_BBDD.NOMBRE_COLUMN2,
                Estructura_BBDD.NOMBRE_COLUMN3,
                Estructura_BBDD.NOMBRE_COLUMN4,
                Estructura_BBDD.NOMBRE_COLUMN5,
                Estructura_BBDD.NOMBRE_COLUMN6,
                Estructura_BBDD.NOMBRE_COLUMN7,
                Estructura_BBDD.NOMBRE_COLUMN8

        };
        String selection = Estructura_BBDD.NOMBRE_COLUMN6 + " like ? and " + Estructura_BBDD.NOMBRE_COLUMN8 + " like ?";
        String[] selectionArgs = {usu,cla};
        Cursor c = db.query(Estructura_BBDD.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null);
        return c;
    }*/
}
