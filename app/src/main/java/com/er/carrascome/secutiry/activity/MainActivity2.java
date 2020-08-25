package com.er.carrascome.secutiry.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.er.carrascome.libsecurity.expose.SecurityExpose;
import com.er.carrascome.libsecurity.tools.Encrypter;
import com.er.carrascome.secutiry.BundleArguments;
import com.er.carrascome.secutiry.R;
import com.er.carrascome.secutiry.domain.Usuario;

public class MainActivity2 extends AppCompatActivity {
    private Usuario usuario;
    private int dato;
    private Bundle extras;
    private  EditText edtNombreObjeto;
    private  EditText edtVariableNormal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        edtNombreObjeto = findViewById(R.id.edtNombreObjeto);
        edtVariableNormal = findViewById(R.id.edtVariableNormal);
        Button boton = findViewById(R.id.btnSiguiente);
        getExtras();
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this,MainActivity.class);
                String nombreObjeto = ""+edtNombreObjeto.getText();
                int variableNormal = (""+edtVariableNormal.getText()).matches("[0-9]*")?Integer.parseInt(edtVariableNormal.getText()+""):0;
                Usuario erick = new Usuario();
                erick.setNombre(nombreObjeto);
                intent.putExtra(SecurityExpose.codifica(BundleArguments.USUARIO),SecurityExpose.codifica(erick));
                intent.putExtra(SecurityExpose.codifica(BundleArguments.DATO),SecurityExpose.codifica(variableNormal));
                startActivity(intent);

            }
        });
        if(extras!=null){
            edtNombreObjeto.setText(usuario.getNombre());
            edtVariableNormal.setText(""+dato);
        }

    }

    private void getExtras(){
        extras = getIntent().getExtras();
        if(extras!=null ) {
            usuario = SecurityExpose.decodificaExtras(BundleArguments.USUARIO,extras,Usuario.class,null);
            dato = SecurityExpose.decodificaExtras(BundleArguments.DATO,extras,Integer.class,0);

        }
    }
}