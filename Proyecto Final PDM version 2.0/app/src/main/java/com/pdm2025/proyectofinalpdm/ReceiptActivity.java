package com.pdm2025.proyectofinalpdm;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class ReceiptActivity extends AppCompatActivity {

    private TextView tvUserDetails, tvProductDetails, tvGuideNumber;
    private Button btnContinueShopping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);

        // Referencias a los elementos de la UI
        tvUserDetails = findViewById(R.id.tv_user_details);
        tvProductDetails = findViewById(R.id.tv_product_details);
        tvGuideNumber = findViewById(R.id.tv_guide_number);
        btnContinueShopping = findViewById(R.id.btn_continue_shopping);

        // Obtener los datos del Intent
        Intent intent = getIntent();
        String userName = intent.getStringExtra("userName");
        String userEmail = intent.getStringExtra("userEmail");
        String userAddress = intent.getStringExtra("userAddress");
        String productName = intent.getStringExtra("productName");
        double productPrice = intent.getDoubleExtra("productPrice", 0.0);

        // Generar un número de guía aleatorio
        String guideNumber = generateGuideNumber();

        // Mostrar los datos en los TextViews
        tvUserDetails.setText("Nombre: " + userName + "\nCorreo: " + userEmail + "\nDirección: " + userAddress);
        tvProductDetails.setText("Producto: " + productName + "\nPrecio: $" + productPrice);
        tvGuideNumber.setText("Guía de envío: " + guideNumber);

        // Acción del botón "Seguir Comprando"
        btnContinueShopping.setOnClickListener(view -> {
            // Redirigir al usuario al Catálogo de Productos
            Intent catalogIntent = new Intent(ReceiptActivity.this, CatalogActivity.class);
            startActivity(catalogIntent);
            finish(); // Finalizar esta actividad para evitar que el usuario vuelva atrás
        });
    }

    /**
     * Método para generar un número de guía aleatorio.
     * @return Un número de guía aleatorio.
     */
    private String generateGuideNumber() {
        Random random = new Random();
        return "MX-" + (100000 + random.nextInt(900000)); // Genera un número aleatorio en el rango 100000 a 999999
    }
}
