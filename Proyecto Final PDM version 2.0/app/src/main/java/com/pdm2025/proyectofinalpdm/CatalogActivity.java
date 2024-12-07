package com.pdm2025.proyectofinalpdm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CatalogActivity extends AppCompatActivity implements ProductAdapter.OnProductClickListener {

    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private List<Product> productList;
    private List<Product> cartList = new ArrayList<>();
    private TextView cartCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        // Inicializar vistas
        recyclerView = findViewById(R.id.recycler_view);
        cartCounter = findViewById(R.id.cart_counter);

        // Configurar RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inicializar lista de productos y adaptador
        productList = new ArrayList<>();
        loadProducts(); // Método para cargar la lista de productos

        adapter = new ProductAdapter(productList);
        adapter.setOnProductClickListener(this); // Configurar el listener de clics
        recyclerView.setAdapter(adapter);

        // Configurar contador del carrito inicialmente oculto
        updateCartCounter();
    }

    /**
     * Método para cargar la lista de productos con datos iniciales.
     */
    private void loadProducts() {
        // Añadir productos a la lista
        productList.add(new Product("Mafex No.241, MAFEX FRIENDLY NEIGHBORHOOD SPIDER-MAN", 1850, R.drawable.p1_1));
        productList.add(new Product("Mafex No.248, MAFEX THE AMAZING SPIDER-MAN", 1750, R.drawable.p2_1));
        productList.add(new Product("Mafex No.245, TRAJE INTEGRADO MAFEX SPIDER-MAN", 1800, R.drawable.p3_1));
        productList.add(new Product("Mafex No.222, MAFEX BATMAN (ZACK SNYDER'S JUSTICE LEAGUE Ver.)", 1700, R.drawable.p4_1));
        productList.add(new Product("Mafex No.174, MAFEX SUPERMAN (ZACK SNYDER'S JUSTICE LEAGUE Ver.)", 1500, R.drawable.p5_1));
        productList.add(new Product("Mafex No.243, MAFEX THE FLASH (ZACK SNYDER'S JUSTICE LEAGUE Ver.)", 1600, R.drawable.p6_1));
        productList.add(new Product("Mafex No.211, MAFEX DARTH VADER(TM) (Rogue One Ver.1.5)", 1500, R.drawable.p7_1));
        productList.add(new Product("Mafex No.210, MAFEX AHSOKA TANO (The Mandalorian Ver.)", 1700, R.drawable.p8_1));
        productList.add(new Product("Mafex No.200, MAFEX THE MANDALORIAN Ver.2.0", 2000, R.drawable.p9_1));
        productList.add(new Product("Mafex No.100, MAFEX Michael Jordan (Chicago Bulls)", 1850, R.drawable.p10_1));
        productList.add(new Product("Mafex No.085, MAFEX JOHN WICK (R) (CHAPTER2)", 1700, R.drawable.p11_1));
        productList.add(new Product("Mafex No.226, MAFEX ROBOCOP 2 RENEWAL Ver.", 1800, R.drawable.p12_1));
        productList.add(new Product("Mafex No.071, MAFEX GWENPOOL", 1600, R.drawable.p13_1));
        productList.add(new Product("Mafex No.083, MAFEX EVIL GWENPOOL", 1750, R.drawable.p14_1));
        productList.add(new Product("Mafex No.082, MAFEX DEADPOOL (GURIHIRU ART Ver.)", 1650, R.drawable.p15_1));
        productList.add(new Product("Mafex No.227, MAFEX LUKE SKYWALKER (TM) (THE MANDALORIAN Ver.)", 1600, R.drawable.p16_1));
        productList.add(new Product("Mafex No.259, MAFEX STORMTROOPER (TM) Ver. 2.0", 1800, R.drawable.p17_1));
        productList.add(new Product("Mafex No.151, MAFEX HOMELANDER", 1500, R.drawable.p18_1));
        productList.add(new Product("Mafex No.140, MAFEX IRON MAN MARK85 (Endgame Ver.)", 2000, R.drawable.p19_1));
        productList.add(new Product("Mafex No.130, MAFEX CAPITÁN AMÉRICA (ENDGAME Ver.)", 2000, R.drawable.p20_1));
    }

    /**
     * Maneja el evento de clic en un producto.
     * @param product Producto clicado.
     */
    @Override
    public void onProductClick(Product product) {
        cartList.add(product); // Añade producto al carrito
        updateCartCounter(); // Actualiza el contador del carrito
        Toast.makeText(this, product.getName() + " añadido al carrito.", Toast.LENGTH_SHORT).show();
    }

    /**
     * Actualiza el contador del carrito en la UI.
     */
    private void updateCartCounter() {
        int count = cartList.size(); // Número de artículos en el carrito
        if (count > 0) {
            cartCounter.setVisibility(View.VISIBLE); // Muestra el contador si el carrito tiene productos
            cartCounter.setText(String.valueOf(count)); // Actualiza el texto con el número de artículos
        } else {
            cartCounter.setVisibility(View.GONE); // Oculta el contador si el carrito está vacío
        }
    }

    /**
     * Método para mostrar el resumen del carrito.
     * @param view Vista asociada al clic (por ejemplo, botón del carrito).
     */
    public void showCartSummary(View view) {
        if (cartList.isEmpty()) {
            // Si el carrito está vacío, mostrar un mensaje
            Toast.makeText(this, "El carrito está vacío", Toast.LENGTH_SHORT).show();
        } else {
            // Si el carrito tiene productos, abrir la pantalla de resumen del carrito
            Intent intent = new Intent(this, CartSummaryActivity.class);
            intent.putExtra("cartList", new ArrayList<>(cartList)); // Pasar el carrito a la siguiente actividad
            startActivity(intent); // Iniciar actividad
        }
    }
}
