package com.example.sqllite.controller;

public class Utility {
    // Constantes para los nombres de las columnas de la tabla Usuario
    public static final String TABLA_USUARIO = "usuario";
    public static final String CAMPO_ID = "id";
    public static final String CAMPO_NOMBRE = "nombre";
    public static final String CAMPO_CORREO = "correo";

    // Consulta SQL para crear la tabla Usuario
    public static final String CREAR_TABLA_USUARIO = "CREATE TABLE " + TABLA_USUARIO + " ("
            + CAMPO_ID + " INTEGER PRIMARY KEY, "
            + CAMPO_NOMBRE + " TEXT, "
            + CAMPO_CORREO + " TEXT)";
}