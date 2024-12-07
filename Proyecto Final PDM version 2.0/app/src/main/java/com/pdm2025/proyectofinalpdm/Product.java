package com.pdm2025.proyectofinalpdm;

public class Product {

    private final String name;       // Nombre del producto
    private final double price;      // Precio del producto
    private final int imageResId;    // Referencia al recurso de imagen (ID)

    /**
     * Constructor para inicializar los valores del producto.
     *
     * @param name Nombre del producto.
     * @param price Precio del producto.
     * @param imageResId ID del recurso de imagen.
     */
    public Product(String name, double price, int imageResId) {
        this.name = name;
        this.price = price;
        this.imageResId = imageResId;
    }

    /**
     * Método para obtener el nombre del producto.
     *
     * @return Nombre del producto.
     */
    public String getName() {
        return name;
    }

    /**
     * Método para obtener el precio del producto.
     *
     * @return Precio del producto.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Método para obtener el ID del recurso de la imagen.
     *
     * @return ID del recurso de la imagen.
     */
    public int getImageResId() {
        return imageResId;
    }

    /**
     * Método toString para una representación más clara del objeto.
     *
     * @return Representación en formato de cadena del producto.
     */
    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", imageResId=" + imageResId +
                '}';
    }
}
