package com.pdm2025.proyectofinalpdm;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText fullNameEditText, addressEditText, emailEditText, passwordEditText;
    private Button registerButton;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Inicializar vistas
        fullNameEditText = findViewById(R.id.full_name);
        addressEditText = findViewById(R.id.address);
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        registerButton = findViewById(R.id.register_button);

        // Inicializar la base de datos
        dbHelper = new DatabaseHelper(this);

        // Configurar el botón de registro
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        // Obtener los valores de los campos
        String fullName = fullNameEditText.getText().toString().trim();
        String address = addressEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // Validar campos
        if (TextUtils.isEmpty(fullName) || TextUtils.isEmpty(address) ||
                TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Correo electrónico no válido", Toast.LENGTH_SHORT).show();
            return;
        }

        // Insertar los datos en la base de datos
        long result = dbHelper.addUser(fullName, address, email, password);

        if (result != -1) {
            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
            // Regresar al login después del registro
            finish();
        } else {
            Toast.makeText(this, "Error al registrar usuario. Verifica los datos.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }
}
