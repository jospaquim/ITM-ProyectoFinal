package com.example.itm_proyectofinal.vendedor.pedidos;

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

public class PedidosFragment extends Fragment {

    private PedidosViewModel pedidosViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        pedidosViewModel =
                new ViewModelProvider(this).get(PedidosViewModel.class);
        View root = inflater.inflate(R.layout.fragment_pedidos, container, false);
        pedidosViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }
}