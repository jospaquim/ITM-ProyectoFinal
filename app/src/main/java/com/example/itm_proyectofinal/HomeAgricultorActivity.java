package com.example.itm_proyectofinal;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HomeAgricultorActivity extends AppCompatActivity {
    ViewFlipper v_flipper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_agricultor);

        int images[]= {R.drawable.avocado,R.drawable.lucuma,R.drawable.naranja,R.drawable.manzana};

        v_flipper=(ViewFlipper) findViewById(R.id.vFlipper);
        for (int image:images){
            flipperImages(image);
        }


    }

    public void flipperImages(int image){
        ImageView imageView=new ImageView(this);
        imageView.setBackgroundResource(image);

        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(5000);
        v_flipper.setAutoStart(true);

    }

}
