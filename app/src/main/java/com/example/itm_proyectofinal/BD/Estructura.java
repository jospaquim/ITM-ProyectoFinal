package com.example.itm_proyectofinal.BD;

public class Estructura {
    private Estructura(){}
    //public static  class FeedEntry implements BaseColumns{
    public static final String TABLE_NAME="USUARIOS";
    public static final String NOMBRE_COLUMN1="_Id";
    public static final String NOMBRE_COLUMN2="DNI";
    public static final String NOMBRE_COLUMN3="Nombres";
    public static final String NOMBRE_COLUMN4="Apellidos";
    public static final String NOMBRE_COLUMN5="Celular";
    public static final String NOMBRE_COLUMN6="Correo";
    public static final String NOMBRE_COLUMN7="Direccion";
    public static final String NOMBRE_COLUMN8="Password";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Estructura.TABLE_NAME + " (" +
                    Estructura.NOMBRE_COLUMN1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Estructura.NOMBRE_COLUMN2 +  TEXT_TYPE + COMMA_SEP +
                    Estructura.NOMBRE_COLUMN3 +  TEXT_TYPE + COMMA_SEP +
                    Estructura.NOMBRE_COLUMN4 +  TEXT_TYPE + COMMA_SEP +
                    Estructura.NOMBRE_COLUMN5 +  TEXT_TYPE + COMMA_SEP +
                    Estructura.NOMBRE_COLUMN6 +  TEXT_TYPE + COMMA_SEP +
                    Estructura.NOMBRE_COLUMN7 +  TEXT_TYPE + COMMA_SEP +
                    Estructura.NOMBRE_COLUMN8 +  TEXT_TYPE + ")";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Estructura.TABLE_NAME;
}
