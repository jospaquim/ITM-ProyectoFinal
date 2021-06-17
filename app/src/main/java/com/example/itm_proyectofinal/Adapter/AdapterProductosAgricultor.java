package com.example.itm_proyectofinal.Adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itm_proyectofinal.Beans.Producto;
import com.example.itm_proyectofinal.ModificarProductoActivity;
import com.example.itm_proyectofinal.OpenHelper.SQLite_OpenHelper;
import com.example.itm_proyectofinal.R;
import com.example.itm_proyectofinal.VerProductosAgricultorActivity;

import java.util.ArrayList;

public class AdapterProductosAgricultor extends RecyclerView.Adapter<AdapterProductosAgricultor.ViewHolderProductosAgri> implements View.OnClickListener {
    ArrayList<Producto> lstPro;
    private View.OnClickListener listener;
    Context context;


    public AdapterProductosAgricultor(ArrayList<Producto> lstPro, Context context) {
        this.lstPro = lstPro;
        this.context = context;

    }


    @NonNull
    @Override
    public ViewHolderProductosAgri onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_productos_agricultor,null,false);
        view.setOnClickListener(this);
        return new ViewHolderProductosAgri(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterProductosAgricultor.ViewHolderProductosAgri holder, int position) {
        holder.nom.setText(lstPro.get(position).getNombre());
        holder.desc.setText(lstPro.get(position).getDescripcion());
        holder.prec.setText("S/ "+lstPro.get(position).getPrecio());
        holder.medida.setText(""+lstPro.get(position).getCriterio_medida());
        holder.stock.setText(""+lstPro.get(position).getStock());
        //falta el SET DE LA IMAGEN - AGREGAR AQUI




        final SQLite_OpenHelper helper=new SQLite_OpenHelper(context);

        holder.modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(context, ModificarProductoActivity.class);
                i.putExtra("idAgricultor",lstPro.get(position).getCodAgri());
                i.putExtra("idProducto",lstPro.get(position).getCodigo());
                context.startActivity(i);


                //Toast.makeText(context,"THIS IS MODIFICAR!!!",Toast.LENGTH_LONG).show();
            }
        });
        holder.eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db= helper.getWritableDatabase();
                String selection="id_prod= ?";
                String[] selectionArgs={""+lstPro.get(position).getCodigo()};
                db.delete("PRODUCTO",selection,selectionArgs);
                lstPro.remove(position);
                notifyDataSetChanged();


                Toast.makeText(context, "Registro eliminado satisfactoriamente!!!!",Toast.LENGTH_LONG).show();


                //i.putExtra("idAgricultor",lstPro.get(position).getCodAgri());
            }
        });


    }

    @Override
    public int getItemCount() {
        return lstPro.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }


    @Override
    public void onClick(View v) {
        if (listener!=null){
            listener.onClick(v);
        }

    }

    public class ViewHolderProductosAgri extends RecyclerView.ViewHolder{
        TextView nom,desc,prec,medida,stock;
        ImageView img;
        Button modificar,eliminar;
        public ViewHolderProductosAgri(@NonNull View itemView) {
            super(itemView);
            nom=(TextView) itemView.findViewById(R.id.idNombreP);
            desc=(TextView) itemView.findViewById(R.id.descripcionP);
            prec=(TextView) itemView.findViewById(R.id.idPrecioP);
            medida=(TextView) itemView.findViewById(R.id.idMedidaP);
            img=(ImageView) itemView.findViewById(R.id.idImagenP);
            stock=(TextView) itemView.findViewById(R.id.idStockP);
            modificar=(Button) itemView.findViewById(R.id.idModificarP);
            eliminar=(Button) itemView.findViewById(R.id.idBorrarP);





        }
    }


    //metodo para filtrar datos
    /*public void filterListUsers(ArrayList<Producto> filterUsuarios){
        this.lstPro=filterUsuarios;
        notifyDataSetChanged();
    }*/

    public Producto obtenerUsu(int indice){
        if (lstPro.size()==0){
            return null;
        }else{
            return lstPro.get(indice);
        }

    }
}
