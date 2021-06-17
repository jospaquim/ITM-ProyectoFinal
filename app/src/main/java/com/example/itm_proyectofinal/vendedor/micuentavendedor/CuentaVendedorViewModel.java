package com.example.itm_proyectofinal.vendedor.micuentavendedor;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CuentaVendedorViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CuentaVendedorViewModel
            () {
        mText = new MutableLiveData<>();
        mText.setValue("fragmento de mi cuenta");
    }

    public LiveData<String> getText() {

        return mText;
    }
}