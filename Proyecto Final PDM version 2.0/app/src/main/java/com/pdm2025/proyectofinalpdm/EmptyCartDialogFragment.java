package com.pdm2025.proyectofinalpdm;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class EmptyCartDialogFragment extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_empty_cart, container, false);

        TextView closeText = view.findViewById(R.id.close_text);
        closeText.setOnClickListener(v -> dismiss());

        // Cierra automáticamente después de 5 segundos
        new Handler().postDelayed(this::dismiss, 5000);

        return view;
    }
}
