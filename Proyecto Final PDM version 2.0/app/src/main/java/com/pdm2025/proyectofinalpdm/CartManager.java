package com.pdm2025.proyectofinalpdm;

import java.util.ArrayList;
import java.util.List;

public class CartManager {

    private static CartManager instance;
    private List<Product> cartItems;

    // Constructor privado para el patrón Singleton
    private CartManager() {
        cartItems = new ArrayList<>();
    }

    // Obtener la instancia del CartManager (Singleton)
    public static CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    // Obtener los artículos en el carrito
    public List<Product> getCartItems() {
        return cartItems;
    }

    // Agregar un producto al carrito
    public void addProductToCart(Product product) {
        if (product != null) {
            cartItems.add(product);
        }
    }

    // Eliminar un producto del carrito
    public void removeProduct(Product product) {
        if (product != null && cartItems.contains(product)) {
            cartItems.remove(product);
        }
    }

    // Vaciar el carrito
    public void clearCart() {
        cartItems.clear();
    }

    // Obtener el total de los productos en el carrito
    public double getTotalPrice() {
        double total = 0.0;
        for (Product product : cartItems) {
            total += product.getPrice();
        }
        return total;
    }

    // Verificar si el carrito está vacío
    public boolean isCartEmpty() {
        return cartItems.isEmpty();
    }

    // Método para obtener el número de productos en el carrito
    public int getCartItemCount() {
        return cartItems.size();
    }
}
