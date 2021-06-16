package com.example.itm_proyectofinal.vendedor.productos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.itm_proyectofinal.R;

public class ProductosFragment extends Fragment {

    private ProductosViewModel productosViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        productosViewModel =
                new ViewModelProvider(this).get(ProductosViewModel.class);
        View root = inflater.inflate(R.layout.fragment_productos, container, false);

        return root;
    }
}