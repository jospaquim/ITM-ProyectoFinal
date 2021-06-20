package com.example.itm_proyectofinal.vendedor.micuentavendedor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.itm_proyectofinal.R;
import com.example.itm_proyectofinal.vendedor.productos.ProductosViewModel;

public class EditarCuentaAgriFragment extends Fragment {
    private CuentaVendedorViewModel producEditarViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        producEditarViewModel =
                new ViewModelProvider(this).get(CuentaVendedorViewModel.class);
        View root = inflater.inflate(R.layout.editar_perfil_agricultor, container, false);


        producEditarViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }


}
