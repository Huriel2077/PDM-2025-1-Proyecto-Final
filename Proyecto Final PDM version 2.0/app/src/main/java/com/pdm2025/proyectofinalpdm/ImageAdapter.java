package com.pdm2025.proyectofinalpdm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class ImageAdapter extends PagerAdapter {

    private Context context;
    private List<Integer> imageList;  // Lista de imágenes locales

    // Constructor del adaptador
    public ImageAdapter(Context context, List<Integer> imageList) {
        this.context = context;
        this.imageList = imageList;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // Infla el layout de cada imagen
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(imageList.get(position));  // Asigna la imagen de la lista
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);  // Ajusta la imagen para que ocupe el espacio adecuadamente

        container.addView(imageView);  // Añade la imagen al contenedor (ViewPager)

        return imageView;  // Devuelve el ImageView que representa la imagen
    }

    @Override
    public int getCount() {
        return imageList.size();  // Devuelve el número de imágenes que se mostrarán
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;  // Verifica si la vista es la misma que el objeto
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ImageView) object);  // Elimina la vista de la imagen cuando ya no es necesaria
    }
}