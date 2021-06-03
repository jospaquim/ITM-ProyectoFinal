package com.example.itm_proyectofinal.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itm_proyectofinal.R;

import java.util.ArrayList;

public class Adapter_register extends RecyclerView.Adapter<Adapter_register.ViewHolderDataRegister> {
    ArrayList<String> listInputs;

    public Adapter_register(ArrayList<String> listInputs) {
        this.listInputs = listInputs;
    }

    @NonNull
    @Override
    public ViewHolderDataRegister onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_input_register,null,false);
        return new ViewHolderDataRegister(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDataRegister holder, int position) {
        holder.AsignarDatos(listInputs.get(position));
    }

    @Override
    public int getItemCount() {
        return listInputs.size();

    }

    public class ViewHolderDataRegister extends RecyclerView.ViewHolder {
        Button btn_regresar_register, btn_login_fb,btn_login_g,btn_login;
        EditText enunsolopaso,  userDNI,userName,userLastName,etUserPhone,etUserDate,et_userMail_register,et_userPassword_register, et_ConfirmPassword_register;
        TextView et_register_login, contucorreo;
        public ViewHolderDataRegister(@NonNull View itemView) {
            super(itemView);
            btn_regresar_register=itemView.findViewById(R.id.btn_regresar_register);
            btn_login_fb=itemView.findViewById(R.id.btn_login_fb);
            btn_login_g=itemView.findViewById(R.id.btn_login_g);
            btn_login=itemView.findViewById(R.id.btn_login);
            enunsolopaso=itemView.findViewById(R.id.enunsolopaso);
            userDNI=itemView.findViewById(R.id.userDNI);
            userName=itemView.findViewById(R.id.userName);
            userLastName=itemView.findViewById(R.id.userLastName);
            etUserDate=itemView.findViewById(R.id.etUserDate);
            et_userMail_register=itemView.findViewById(R.id.et_userMail_register);
            et_userPassword_register=itemView.findViewById(R.id.et_userPassword_register);
            et_ConfirmPassword_register=itemView.findViewById(R.id.et_ConfirmPassword_register);
            et_register_login=itemView.findViewById(R.id.et_register_login);
            contucorreo=itemView.findViewById(R.id.contucorreo);
            etUserPhone=itemView.findViewById(R.id.etUserPhone);
        }


        public void AsignarDatos(String s) {
            btn_regresar_register.setText(s);
        }
    }
}
