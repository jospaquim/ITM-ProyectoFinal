package com.example.itm_proyectofinal.vendedor.micuentavendedor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.itm_proyectofinal.R;

public class CuentaVendedorFragment extends Fragment{

    private CuentaVendedorViewModel cuentavendedorViewModel;
    Button editar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        /*editar= getActivity().findViewById(R.id.btn_editar_perfil_agri);*/

        cuentavendedorViewModel =
                new ViewModelProvider(this).get(CuentaVendedorViewModel.class);
        View root = inflater.inflate(R.layout.fragment_micuenta_vendedor , container, false);



        cuentavendedorViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;


        /*editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(),"hereeeeeee",Toast.LENGTH_LONG).show();
            }
        });*/

    }



    /*@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_micuenta_vendedor);
        editar= findViewById();
    }*/

    /*private void setContentView(int fragment_micuenta_vendedor) {
    }*/

}