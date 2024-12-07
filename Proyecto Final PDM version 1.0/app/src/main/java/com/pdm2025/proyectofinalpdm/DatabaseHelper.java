package com.pdm2025.proyectofinalpdm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Nombre de la base de datos y versión
    private static final String DATABASE_NAME = "users.db";
    private static final int DATABASE_VERSION = 2; // Incrementar si hay cambios en la estructura

    // Tabla y columnas
    public static final String TABLE_USERS = "usuarios"; // Cambiado a "usuarios"
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "nombre";
    public static final String COLUMN_ADDRESS = "direccion";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";

    // Sentencia SQL para crear la tabla de usuarios
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_USERS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT NOT NULL, " +
                    COLUMN_ADDRESS + " TEXT NOT NULL, " +
                    COLUMN_EMAIL + " TEXT UNIQUE NOT NULL, " + // UNIQUE para evitar duplicados
                    COLUMN_PASSWORD + " TEXT NOT NULL" +
                    ");";

    // Constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Crear la base de datos
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE); // Crear tabla de usuarios
    }

    // Actualización de la base de datos
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS); // Eliminar tabla existente
        onCreate(db); // Crear tabla con nueva estructura
    }

    // Método para agregar un usuario
    public long addUser(String nombre, String direccion, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, nombre);
        values.put(COLUMN_ADDRESS, direccion);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, password);

        try {
            return db.insertOrThrow(TABLE_USERS, null, values); // Manejar errores explícitamente
        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // Indicar que ocurrió un error
        }
    }

    // Método para verificar si un usuario existe con el correo y contraseña dados
    public boolean checkUser(String email, String password) {
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            return false; // Validación inicial para evitar consultas innecesarias
        }

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, new String[]{COLUMN_ID},
                COLUMN_EMAIL + "=? AND " + COLUMN_PASSWORD + "=?",
                new String[]{email, password}, null, null, null);

        boolean userExists = (cursor != null && cursor.getCount() > 0);

        if (cursor != null) {
            cursor.close();
        }

        return userExists; // Devolver si el usuario existe
    }
}