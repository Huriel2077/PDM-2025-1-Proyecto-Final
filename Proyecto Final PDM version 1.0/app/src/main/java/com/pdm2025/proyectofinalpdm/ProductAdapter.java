package com.pdm2025.proyectofinalpdm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> productList;

    // Constructor que recibe la lista de productos
    public ProductAdapter(List<Product> productList) {
        this.productList = productList;
    }

    // Crea el viewHolder a partir del layout item_product.xml
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Infla el layout item_product
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    // Asocia los datos del producto al viewHolder
    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);

        // Establece el nombre y precio del producto en los TextViews
        holder.productName.setText(product.getName());
        holder.productPrice.setText("$" + product.getPrice());

        // Establece la imagen del producto en el ImageView
        // Si las imágenes son recursos locales, deberías usar 'setImageResource()'.
        // Si las imágenes son URLs, deberías usar una librería como Picasso o Glide.
        holder.productImage.setImageResource(product.getImageResId());
    }

    // Devuelve la cantidad total de productos
    @Override
    public int getItemCount() {
        return productList.size();
    }

    // ViewHolder para mantener las vistas de cada ítem
    static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView productName, productPrice;
        ImageView productImage;

        // Constructor que vincula las vistas con los elementos del layout
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.product_name);
            productPrice = itemView.findViewById(R.id.product_price);
            productImage = itemView.findViewById(R.id.product_image);
        }
    }
}