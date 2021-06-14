package com.example.itm_proyectofinal.BD;

public class Estructura_BBDD {

    //public static  class FeedEntry implements BaseColumns{
    /*public static final String TABLE_NAME="USUARIOS";
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
                    Estructura.NOMBRE_COLUMN8 +  TEXT_TYPE + ")";*/

    /*public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Estructura_BBDD.TABLE_NAME;*/

    public static final String SQL_CREATE_USER_CLASICO= "CREATE TABLE USUARIO_CLASICO (" +
            "id_us_cla INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nombre_cla TEXT NOT NULL," +
            "apellido_cla TEXT NOT NULL,"+
            "DNI_cla VARCHAR(8) NOT NULL," +
            "fecha_naci_cla DATE NOT NULL ,"+
            "celular_cla VARCHAR(9) NOT NULL,"+
            "coreo_cla TEXT NOT NULL,"+
            "password_cla TEXT NOT NULL,"+
            "fecha_registro_cla DATETIME NOT NULL)";

    public static final String SQL_DELETE_USER_CLASICO ="DROP TABLE IF EXISTS USUARIO_CLASICO";

    public static final String SQL_CREATE_USER_AGRICULTOR="CREATE TABLE USUARIO_AGRICULTOR (" +
            "id_us_agri INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nombre_agri TEXT NOT NULL," +
            "apellido_agri TEXT NOT NULL," +
            "DNI_agri VARCHAR(8) NOT NULL," +
            "fecha_naci_agri DATE NOT NULL," +
            "celular_agri VARCHAR(9) NOT NULL," +
            "departamento_agri VARCHAR(30) NOT NULL,"+
            "provincia_agri VARCHAR(30) NOT NULL,"+
            "direccion_agri TEXT NOT NULL,"+
            "tipo_agri VARCHAR(20) NOT NULL," +
            "correo_agri TEXT NOT NULL,"+
            "password_agri TEXT NOT NULL,"+
            "fecha_registro_agri DATETIME NOT NULL)";

    public static final String SQL_DELETE_USER_AGRICULTOR ="DROP TABLE IF EXISTS USUARIO_AGRICULTOR";

    public static final String SQL_CREATE_PRODUCTO="CREATE TABLE PRODUCTO (" +
            "id_prod INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nombre_prod TEXT NOT NULL," +
            "precio_prod NUMERIC NOT NULL," +
            "criterio_medida_pro VARCHAR(15) NOT NULL," +
            "stock_prod INTEGER NOT NULL," +
            "foto_prod TEXT NOT NULL," +
            "descrip_prod TEXT NOT NULL)";

    public static final String SQL_DELETE_USER_PRODUCTO ="DROP TABLE IF EXISTS PRODUCTO";

    //analizar si el producto se considera como unico, si se da ese caso en el producto va una clave foranea de agricultor
    public static final String SQL_CREATE_REGISTRO="CREATE TABLE REGISTRO (" +
            "id_reg INTEGER PRIMARY KEY AUTOINCREMENT," +
            "id_us_agric INTEGER FOREIGN KEY REFERENCES USUARIO_AGRICULTOR(id_us_agri)," +
            "id_produc INTEGER FOREIGN KEY REFERENCES PRODUCTO(id_prod)," +
            "cantidad_reg INTEGER NOT NULL," +
            "fecha_reg DATETIME NOT NULL)";

    public static final String SQL_DELETE_REGISTRO ="DROP TABLE IF EXISTS REGISTRO";

    public static final String SQL_CREATE_COMENTARIO="CREATE TABLE COMENTARIO (" +
            "id_comen INTEGER PRIMARY KEY AUTOINCREMENT," +
            "descrip_comen TEXT NOT NULL," +
            "id_us_clasic INTEGER FOREIGN KEY REFERENCES USUARIO_CLASICO(id_us_cla)," +
            "id_producto INTEGER FOREIGN KEY REFERENCES PRODUCTO(id_prod)," +
            "fecha_comen DATETIME NOT NULL)";

    public static final String SQL_DELETE_COMENTARIO ="DROP TABLE IF EXISTS COMENTARIO";

    public static final String SQL_CREATE_VENTA="CREATE TABLE VENTA (" +
            "id_venta INTEGER PRIMARY KEY AUTOINCREMENT," +
            "id_agricultor INTEGER FOREIGN KEY REFERENCES USUARIO_AGRICULTOR(id_us_agri)," +
            "id_clasico INTEGER FOREIGN KEY REFERENCES USUARIO_CLASICO(id_us_cla)," +
            "fecha_venta DATE NOT NULL," +
            "costo_delivery NUMERIC NOT NULL," +
            "direccion_entrega TEXT NOT NULL)";

    public static final String SQL_DELETE_VENTA ="DROP TABLE IF EXISTS VENTA";

    public static final String SQL_CREATE_VENTA_DETALLE="CREATE TABLE VENTA_DETALLE (" +
            "id_v INTEGER FOREIGN KEY REFERENCES VENTA(id_venta)," +
            "id_prodc INTEGER FOREIGN KEY REFERENCES PRODUCTO(id_prod)," +
            "cantidad INTEGER NOT NULL," +
            "PRIMARY KEY(id_v,id_prodc))";

    public static final String SQL_DELETE_VENTA_DETALLE ="DROP TABLE IF EXISTS VENTA_DETALLE";
}
