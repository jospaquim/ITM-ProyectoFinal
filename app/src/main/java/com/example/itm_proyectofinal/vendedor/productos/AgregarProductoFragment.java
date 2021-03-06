package com.example.itm_proyectofinal.vendedor.productos;

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

public class AgregarProductoFragment extends Fragment {
    private ProductosViewModel productAgregarViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        productAgregarViewModel =
                new ViewModelProvider(this).get(ProductosViewModel.class);
        View root = inflater.inflate(R.layout.activity_product_add, container, false);


        productAgregarViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }
}



