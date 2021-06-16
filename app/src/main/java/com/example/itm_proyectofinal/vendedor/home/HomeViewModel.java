package com.example.itm_proyectofinal.vendedor.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Este es la interfaz principal");
    }

    public LiveData<String> getText() {
        return mText;
    }
}