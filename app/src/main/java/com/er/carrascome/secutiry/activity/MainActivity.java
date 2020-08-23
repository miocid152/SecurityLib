package com.er.carrascome.secutiry.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.er.carrascome.libsecurity.expose.SecurityExpose;
import com.er.carrascome.libsecurity.tools.Encrypter;
import com.er.carrascome.secutiry.R;
import com.er.carrascome.secutiry.domain.Usuario;

public class MainActivity extends AppCompatActivity {
    private Usuario usuario;
    private int dato;
    private Bundle extras;
    private  EditText edtNombreObjeto;
    private  EditText edtVariableNormal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtNombreObjeto = findViewById(R.id.edtNombreObjeto);
        edtVariableNormal = findViewById(R.id.edtVariableNormal);
        Button boton = findViewById(R.id.btnSiguiente);
        getExtras();
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                String nombreObjeto = ""+edtNombreObjeto.getText();
                int variableNormal = Integer.parseInt( edtVariableNormal.getText()+"");
                Usuario erick = new Usuario();
                erick.setNombre(nombreObjeto);
                intent.putExtra(SecurityExpose.codifica("USUARIO"),SecurityExpose.codifica(erick));
                intent.putExtra(SecurityExpose.codifica("DATO"),SecurityExpose.codifica(variableNormal));
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
            usuario = (Usuario) SecurityExpose.decodificaExtras(extras.get(SecurityExpose.codifica("USUARIO")));
            dato = (Integer) SecurityExpose.decodificaExtras(extras.get(SecurityExpose.codifica("DATO")));
        }
    }
}