package com.example.itm_proyectofinal;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

public class HomeAgricultorActivity extends AppCompatActivity {
    /*ViewFlipper v_flipper;
    TextView home,agregar,estadistica,producto,venta,cuenta,cerrar;
    int codAgri;*/
    private AppBarConfiguration mAppBarConfiguration;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_vendedor);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_pedido, R.id.nav_producto, R.id.nav_my_account)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        /*home=(TextView) findViewById(R.id.idHomeAgri);
        agregar=(TextView) findViewById(R.id.idAgregarProducto);
        estadistica=(TextView) findViewById(R.id.idMisEstadisticasAgri);
        producto=(TextView) findViewById(R.id.idMisProductos);
        venta=(TextView) findViewById(R.id.idMisVentas);
        cuenta=(TextView) findViewById(R.id.idMicuentaAgri);
        cerrar=(TextView) findViewById(R.id.idCerrarAgri);

        Bundle bundle=getIntent().getExtras();
        if (bundle.containsKey("idAgricultor")){
            codAgri= bundle.getInt("idAgricultor");
        }

        int images[]= {R.drawable.avocado,R.drawable.lucuma,R.drawable.naranja,R.drawable.manzana};

        v_flipper=(ViewFlipper) findViewById(R.id.vFlipper);
        for (int image:images){
            flipperImages(image);
        }

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),HomeAgricultorActivity.class);
                //i.putExtra("idAgricultor",codAgri);
                startActivity(i);
            }
        });

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),RegistrarProductoActivity.class);
                i.putExtra("idAgricultor",codAgri);
                startActivity(i);
            }
        });
        producto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),VerProductosAgricultorActivity.class);
                i.putExtra("idAgricultor",codAgri);
                startActivity(i);
            }
        });

        cuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),MiCuentaAgricultorActivity.class);
                i.putExtra("idAgricultor",codAgri);
                startActivity(i);

            }
        });

        venta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        estadistica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
                //finish();
            }
        });*/


    }

   /* public void flipperImages(int image){
        ImageView imageView=new ImageView(this);
        imageView.setBackgroundResource(image);

        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(5000);
        v_flipper.setAutoStart(true);

    }*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}
