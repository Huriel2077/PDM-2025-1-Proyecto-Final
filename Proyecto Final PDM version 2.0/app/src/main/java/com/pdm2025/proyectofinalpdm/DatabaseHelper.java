package com.pdm2025.proyectofinalpdm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Nombre de la base de datos y versión
    private static final String DATABASE_NAME = "store.db";
    private static final int DATABASE_VERSION = 2;

    // Tabla y columnas para usuarios
    public static final String TABLE_USERS = "usuarios";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "nombre";
    public static final String COLUMN_ADDRESS = "direccion";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";

    // Tabla y columnas para productos
    public static final String TABLE_PRODUCTS = "productos";
    public static final String COLUMN_PRODUCT_ID = "id_producto";
    public static final String COLUMN_PRODUCT_NAME = "nombre_producto";
    public static final String COLUMN_PRODUCT_PRICE = "precio";
    public static final String COLUMN_PRODUCT_QUANTITY = "cantidad";  // Inventario

    // Sentencia SQL para crear la tabla de usuarios
    private static final String TABLE_CREATE_USERS =
            "CREATE TABLE " + TABLE_USERS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT NOT NULL, " +
                    COLUMN_ADDRESS + " TEXT NOT NULL, " +
                    COLUMN_EMAIL + " TEXT UNIQUE NOT NULL, " +
                    COLUMN_PASSWORD + " TEXT NOT NULL" +
                    ");";

    // Sentencia SQL para crear la tabla de productos
    private static final String TABLE_CREATE_PRODUCTS =
            "CREATE TABLE " + TABLE_PRODUCTS + " (" +
                    COLUMN_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_PRODUCT_NAME + " TEXT NOT NULL, " +
                    COLUMN_PRODUCT_PRICE + " REAL NOT NULL, " +
                    COLUMN_PRODUCT_QUANTITY + " INTEGER NOT NULL" +
                    ");";

    // Constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Crear la base de datos
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE_USERS);
        db.execSQL(TABLE_CREATE_PRODUCTS);

        // Insertar productos iniciales (inventario de 2000)
        db.execSQL("INSERT INTO " + TABLE_PRODUCTS + " (" + COLUMN_PRODUCT_NAME + ", " + COLUMN_PRODUCT_PRICE + ", " + COLUMN_PRODUCT_QUANTITY + ") VALUES" +
                "('Producto A', 100.0, 2000)," +
                "('Producto B', 150.0, 2000)," +
                "('Producto C', 200.0, 2000);");
    }

    // Actualización de la base de datos
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
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
            return db.insertOrThrow(TABLE_USERS, null, values);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    // Método para verificar si un usuario existe con el correo y contraseña dados
    public boolean checkUser(String email, String password) {
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            return false;
        }

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, new String[]{COLUMN_ID},
                COLUMN_EMAIL + "=? AND " + COLUMN_PASSWORD + "=?",
                new String[]{email, password}, null, null, null);

        boolean userExists = (cursor != null && cursor.getCount() > 0);

        if (cursor != null) {
            cursor.close();
        }

        return userExists;
    }

    // Método para agregar un producto
    public long addProduct(String productName, double price, int quantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_NAME, productName);
        values.put(COLUMN_PRODUCT_PRICE, price);
        values.put(COLUMN_PRODUCT_QUANTITY, quantity);

        try {
            return db.insertOrThrow(TABLE_PRODUCTS, null, values);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    // Método para obtener la cantidad disponible de un producto
    public int getProductQuantity(int productId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_PRODUCTS, new String[]{COLUMN_PRODUCT_QUANTITY},
                COLUMN_PRODUCT_ID + " = ?", new String[]{String.valueOf(productId)},
                null, null, null);

        int quantity = 0;

        if (cursor != null && cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex(COLUMN_PRODUCT_QUANTITY);
            if (columnIndex >= 0) {
                quantity = cursor.getInt(columnIndex);
            }
            cursor.close();
        }

        return quantity;
    }

    // Método para actualizar la cantidad de un producto
    public boolean updateProductQuantity(int productId, int quantitySold) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Obtiene la cantidad actual de productos
        int currentQuantity = getProductQuantity(productId);

        if (currentQuantity >= quantitySold) {
            // Si la cantidad actual es suficiente, se actualiza el inventario
            int newQuantity = currentQuantity - quantitySold;
            ContentValues values = new ContentValues();
            values.put(COLUMN_PRODUCT_QUANTITY, newQuantity);

            int rowsUpdated = db.update(TABLE_PRODUCTS, values, COLUMN_PRODUCT_ID + " = ?",
                    new String[]{String.valueOf(productId)});

            return rowsUpdated > 0;
        }

        return false;  // No hay suficiente inventario
    }
}
