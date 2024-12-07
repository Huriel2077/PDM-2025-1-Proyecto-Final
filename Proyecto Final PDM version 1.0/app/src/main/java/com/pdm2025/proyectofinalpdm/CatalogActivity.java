package com.pdm2025.proyectofinalpdm;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class CatalogActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        // Inicializar RecyclerView
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inicializar lista de productos
        productList = new ArrayList<>();
        loadProducts();

        // Configurar el adaptador
        adapter = new ProductAdapter(productList);
        recyclerView.setAdapter(adapter);
    }

    /**
     * Método para cargar la lista de productos con datos reales.
     * Aquí puedes agregar más productos o modificarlos.
     */
    private void loadProducts() {
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
}