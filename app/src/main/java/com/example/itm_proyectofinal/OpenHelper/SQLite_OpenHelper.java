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
        //db.execSQL();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*db.execSQL(Estructura_BBDD.SQL_DELETE_ENTRIES);
        onCreate(db);*/
    }
    public  void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db,oldVersion,newVersion);
    }
    public void openBD(){
        this.getWritableDatabase();

    }
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
