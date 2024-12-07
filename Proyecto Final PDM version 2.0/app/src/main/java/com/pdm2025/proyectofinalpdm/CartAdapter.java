package com.pdm2025.proyectofinalpdm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private final List<Product> cartItems;
    private final OnRemoveListener onRemoveListener;

    // Constructor para inicializar la lista de productos en el carrito
    public CartAdapter(List<Product> cartItems, OnRemoveListener onRemoveListener) {
        this.cartItems = cartItems;
        this.onRemoveListener = onRemoveListener;
    }

    // Crear una nueva vista para un ítem del carrito (se utiliza el layout 'item_cart')
    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflar el layout del ítem del carrito
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    // Asignar los datos del producto al holder (a cada ítem en el carrito)
    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        // Obtener el producto correspondiente a la posición
        Product product = cartItems.get(position);

        // Establecer los valores en los elementos de la vista
        holder.productName.setText(product.getName());
        holder.productPrice.setText("$" + product.getPrice());

        // Cargar la imagen del producto en el ImageView de ser necesario
        if (product.getImageResId() != 0) {
            holder.productImage.setImageResource(product.getImageResId());
        } else {
            // Establecer una imagen predeterminada en caso de que no haya imagen
            holder.productImage.setImageResource(R.drawable.ic_default_product);
        }

        // Configuración del botón de eliminar
        holder.removeButton.setOnClickListener(v -> {
            if (onRemoveListener != null) {
                onRemoveListener.onRemove(product);
            }
        });
    }

    // Regresar la cantidad de elementos en el carrito
    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    // Clase interna para mantener las referencias de las vistas del ítem
    static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView productName, productPrice;
        ImageView productImage;
        Button removeButton;

        // Constructor para inicializar las vistas
        CartViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.product_name);
            productPrice = itemView.findViewById(R.id.product_price);
            productImage = itemView.findViewById(R.id.product_image);  // Imagen del producto
            removeButton = itemView.findViewById(R.id.remove_button);  // Botón de eliminar
        }
    }

    // Interfaz para manejar el evento de eliminación de un producto
    public interface OnRemoveListener {
        void onRemove(Product product);
    }
}
