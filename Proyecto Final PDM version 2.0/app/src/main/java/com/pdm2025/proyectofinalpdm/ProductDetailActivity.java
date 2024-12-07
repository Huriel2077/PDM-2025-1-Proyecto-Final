package com.pdm2025.proyectofinalpdm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailActivity extends AppCompatActivity {

    private TextView quantityText;  // TextView para mostrar la cantidad
    private int quantity = 1;       // Cantidad inicial
    private int productId;          // ID del producto seleccionado
    private double productPrice;    // Precio del producto

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        // Obtener datos del Intent
        Intent intent = getIntent();
        productId = intent.getIntExtra("PRODUCT_ID", -1);
        productPrice = intent.getDoubleExtra("PRODUCT_PRICE", 0.0);
        String productName = intent.getStringExtra("PRODUCT_NAME");

        // Inicializar vistas
        quantityText = findViewById(R.id.quantity_text);
        Button btnIncrease = findViewById(R.id.btn_increase_quantity);
        Button btnDecrease = findViewById(R.id.btn_decrease_quantity);
        Button btnBuyNow = findViewById(R.id.btn_buy_now);
        Button btnAddToCart = findViewById(R.id.btn_add_to_cart);
        Button btnBackToCatalog = findViewById(R.id.btn_back_to_catalog);
        ViewPager viewPager = findViewById(R.id.product_image_carousel);

        // Configurar cantidad inicial
        quantityText.setText(String.valueOf(quantity));

        // Cargar imágenes dinámicamente en el ViewPager
        List<Integer> imageList = getProductImages(productId);
        ImageAdapter imageAdapter = new ImageAdapter(this, imageList);
        viewPager.setAdapter(imageAdapter);

        // Configuración de botones
        btnIncrease.setOnClickListener(v -> {
            quantity++;
            quantityText.setText(String.valueOf(quantity));
        });

        btnDecrease.setOnClickListener(v -> {
            if (quantity > 1) {
                quantity--;
                quantityText.setText(String.valueOf(quantity));
            }
        });

        btnBuyNow.setOnClickListener(v -> {
            double totalPrice = quantity * productPrice;
            Toast.makeText(ProductDetailActivity.this,
                    "Comprando ahora: " + productName + "\nCantidad: " + quantity + "\nTotal: $" + totalPrice,
                    Toast.LENGTH_SHORT).show();
            // pendiente la lógica para realizar la compra
        });

        btnAddToCart.setOnClickListener(v -> {
            Toast.makeText(ProductDetailActivity.this,
                    "Agregado al carrito: " + productName + "\nCantidad: " + quantity,
                    Toast.LENGTH_SHORT).show();
            // pendiente la lógica para guardar en el carrito de compras
        });

        btnBackToCatalog.setOnClickListener(v -> finish());
    }

    /**
     * Retorna la lista de IDs de imágenes según el ID del producto.
     */
    private List<Integer> getProductImages(int productId) {
        List<Integer> imageList = new ArrayList<>();
        int imageCount;

        // Se define la cantidad de imágenes por producto
        switch (productId) {
            case 1: imageCount = 16; break;
            case 2: imageCount = 15; break;
            case 3: imageCount = 11; break;
            case 4: imageCount = 13; break;
            case 5: imageCount = 8; break;
            case 6: imageCount = 12; break;
            case 7: imageCount = 7; break;
            case 8: imageCount = 10; break;
            case 9: imageCount = 16; break;
            case 10: imageCount = 13; break;
            case 11: imageCount = 9; break;
            case 12: imageCount = 12; break;
            case 13: imageCount = 9; break;
            case 14: imageCount = 8; break;
            case 15: imageCount = 10; break;
            case 16: imageCount = 13; break;
            case 17: imageCount = 11; break;
            case 18: imageCount = 14; break;
            case 19: imageCount = 18; break;
            case 20: imageCount = 12; break;
            default: imageCount = 0; // Producto desconocido
        }

        // Agregar las imágenes al carrusel dinámicamente
        for (int i = 1; i <= imageCount; i++) {
            int resId = getResources().getIdentifier(
                    "p" + productId + "_" + i, "drawable", getPackageName());
            if (resId != 0) { // Verificar que el recurso exista
                imageList.add(resId);
            }
        }
        return imageList;
    }
}
