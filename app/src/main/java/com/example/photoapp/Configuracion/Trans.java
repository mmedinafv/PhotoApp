package com.example.photoapp.Configuracion;

public class Trans {

    public static final int Version = 4;

    public static final String DBname = "photoApp.db";

    public static final String TablaFotografias = "fotos";

    //PROPIEDADES
    public static final String id = "id";
    public static final String descripcion = "descripcion";
    public static final String foto = "foto";

    public static final String createTableFotos = "CREATE TABLE " + TablaFotografias + " ( " +
            id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            descripcion + " TEXT, " +
            foto + " BLOB)";

    public static final String SelectAllFotos = "SELECT * FROM " + TablaFotografias;

    public static final String DropTableFotos = "DROP TABLE IF EXISTS " + TablaFotografias;

}
