package com.example.itm_proyectofinal.OpenHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.itm_proyectofinal.BD.Estructura_BBDD;

import org.w3c.dom.Text;

import java.util.EmptyStackException;

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
        db.execSQL(Estructura_BBDD.SQL_CREATE_PRODUCTO);
        db.execSQL(Estructura_BBDD.SQL_CREATE_REGISTRO);
        //db.execSQL();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Estructura_BBDD.SQL_DELETE_USER_CLASICO);
        db.execSQL(Estructura_BBDD.SQL_DELETE_USER_AGRICULTOR);
        db.execSQL(Estructura_BBDD.SQL_DELETE_USER_PRODUCTO);
        db.execSQL(Estructura_BBDD.SQL_DELETE_REGISTRO);
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
                "id_us_cla",
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
                "id_us_agri",
                "nombre_agri",
                "apellido_agri",
                "DNI_agri",
                "fecha_naci_agri",
                "celular_agri",
                "departamento_agri",
                "provincia_agri",
                "direccion_agri",
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


    public Cursor consultarAgricultorxCorreo(String correo){
        SQLiteDatabase bd= this.getReadableDatabase();
        String[] projection={
                "nombre_agri",
                "apellido_agri",
                "DNI_agri",
                "fecha_naci_agri",
                "celular_agri",
                "departamento_agri",
                "provincia_agri",
                "direccion_agri",
                "tipo_agri",
                "correo_agri",
                "password_agri",
                "fecha_registro_agri"

        };
        String selection="correo_agri"+" like ?";
        String[] selectionArgs={correo};
        Cursor c= bd.query("USUARIO_AGRICULTOR",
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

    public Cursor consultarProductoxNombre(String nombre, int id_Agri){
        SQLiteDatabase bd= this.getReadableDatabase();
        Cursor cursor= bd.rawQuery("SELECT R.id_us_agric,P.id_prod,P.nombre_prod FROM REGISTRO R INNER JOIN PRODUCTO P ON R.id_produc=P.id_prod WHERE (P.nombre_prod like '"+nombre+"') and (R.id_us_agric="+id_Agri+")",null);
        //Cursor cursor2 = bd.rawQuery("SELECT * FROM PRODUCTO", null);

            if(cursor.getCount()>0){
                return cursor;
            }else{
                return null;
            }

    }

    public Cursor consultarProductoxIDxNombre(String nombre,int id_Agri,int id_Prod){
        SQLiteDatabase bd= this.getReadableDatabase();
        Cursor cursor= bd.rawQuery("SELECT R.id_us_agric,P.id_prod FROM REGISTRO R INNER JOIN PRODUCTO P ON R.id_produc=P.id_prod WHERE R.id_produc="+id_Agri+" AND P.nombre_prod LIKE "+nombre+" AND P.id_produc="+id_Prod,null);
        Cursor cursor2 = bd.rawQuery("SELECT * FROM PRODUCTO", null);
        if (cursor2.getCount()>0){
            if(cursor.getCount()>0){
                return cursor;
            }else{
                return null;
            }
        }else{
            return null;
        }


    }


    public Cursor obtenerIdProducto() {
        SQLiteDatabase bd = this.getReadableDatabase();
        Cursor cursor = bd.rawQuery("SELECT MAX(id_prod) FROM PRODUCTO ORDER BY id_prod DESC", null);//DUDA SI EXISTE TOP(1) Y SI EL ORDENAMIENTO ESTA BIEN

        //Cursor cursor = bd.rawQuery("SELECT TOP(1) id_prod FROM REGISTRO R INNER JOIN PRODUCTO P ON R.id_produc=P.id_prod ORDER BY id_prod DESC", null);//DUDA SI EXISTE TOP(1) Y SI EL ORDENAMIENTO ESTA BIEN
        if(cursor.getCount()>0){
            return cursor;
        }else{
            return null;
        }
        /*while (cursor.moveToNext()) {
            usu = new Usuario();
            usu.setId(cursor.getInt(0));
            usu.setNombre(cursor.getString(1));
            usu.setDistrito(cursor.getString(2));
            usu.setCorreo(cursor.getString(3));
            usu.setPassword(cursor.getString(4));
            lstUsuario.add(usu);
        }*/
    }

    public Cursor obtenerUsuarioAgri(int id) {
        SQLiteDatabase bd = this.getReadableDatabase();
        Cursor cursor = bd.rawQuery("SELECT * FROM USUARIO_AGRICULTOR WHERE id_us_agri="+id, null);
        if(cursor.getCount()>0){
            return cursor;
        }else{
            return null;
        }
    }

    /*public void consultaListarUsuarios(){
        SQLiteDatabase db= helper.getReadableDatabase();
        Usuario usu=null;
        Cursor cursor= db.rawQuery("SELECT * FROM "+ Estructura_BBDD.TABLE_NAME,null);
        while (cursor.moveToNext()){
            usu= new Usuario();
            usu.setId(cursor.getInt(0));
            usu.setNombre(cursor.getString(1));
            usu.setDistrito(cursor.getString(2));
            usu.setCorreo(cursor.getString(3));
            usu.setPassword(cursor.getString(4));
            lstUsuario.add(usu);
        }
    }*/


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
