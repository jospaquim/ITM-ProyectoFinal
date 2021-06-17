package com.example.itm_proyectofinal.vendedor.micuentavendedor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.itm_proyectofinal.R;

public class CuentaVendedorFragment extends Fragment {

    private CuentaVendedorViewModel cuentavendedorViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        cuentavendedorViewModel =
                new ViewModelProvider(this).get(CuentaVendedorViewModel.class);
        View root = inflater.inflate(R.layout.fragment_micuenta_vendedor , container, false);
        cuentavendedorViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }
}