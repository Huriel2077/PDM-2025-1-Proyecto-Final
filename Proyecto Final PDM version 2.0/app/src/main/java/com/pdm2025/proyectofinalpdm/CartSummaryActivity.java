package com.pdm2025.proyectofinalpdm;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartSummaryActivity extends AppCompatActivity {

    private RecyclerView cartRecyclerView;
    private TextView totalPriceTextView;
    private Button checkoutButton;
    private CartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_summary);

        // Inicializar vistas
        cartRecyclerView = findViewById(R.id.cart_recycler_view);
        totalPriceTextView = findViewById(R.id.total_price_text_view); // Verifica que el ID esté en el XML
        checkoutButton = findViewById(R.id.checkout_button);

        // Obtener el carrito de compras
        CartManager cartManager = CartManager.getInstance();
        List<Product> cartItems = cartManager.getCartItems();

        // Configurar RecyclerView
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Configurar el adaptador para el RecyclerView
        adapter = new CartAdapter(cartItems, new CartAdapter.OnRemoveListener() {
            @Override
            public void onRemove(Product product) {
                // eliminar el producto del carrito
                cartManager.removeProduct(product); // Eliminar producto de CartManager
                // Actualizar la lista de productos
                cartItems.remove(product);
                adapter.notifyDataSetChanged(); // Notificar al adaptador sobre el cambio
                // Actualizar el precio total
                double totalPrice = calculateTotalPrice(cartItems);
                totalPriceTextView.setText("Total: $ " + totalPrice);
            }
        });

        // Asignar el adaptador al RecyclerView
        cartRecyclerView.setAdapter(adapter);

        // Calcular y mostrar el precio total
        double totalPrice = calculateTotalPrice(cartItems);
        totalPriceTextView.setText("Total: $ " + totalPrice);

        // Configurar el botón de checkout
        checkoutButton.setOnClickListener(v -> {
            // pendiente la lógica para proceder al checkout
            Toast.makeText(this, "Compra realizada con éxito", Toast.LENGTH_SHORT).show();
            // Limpiar el carrito después del checkout
            cartManager.clearCart();
            finish(); // Regresar a la pantalla anterior
        });
    }

    /**
     * Método para calcular el precio total del carrito.
     * @param cartItems Lista de productos en el carrito.
     * @return El precio total.
     */
    private double calculateTotalPrice(List<Product> cartItems) {
        double total = 0.0;
        for (Product product : cartItems) {
            total += product.getPrice();
        }
        return total;
    }
}
