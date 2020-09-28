package com.example.pruebanativa;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelos.Rol;

public class Registrarse extends AppCompatActivity {

    private  EditText campoUsuario ;
    private  EditText campoRazonSocial;
    private EditText campoClaveAcceso;
    private Spinner campoSpinner ;
    private Context context;
    private Button btnRegistrarse;
    private TextView textRegistrarse;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private  String token = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        context = getApplicationContext();

        campoUsuario = (EditText) findViewById(R.id.TextUsuario);
        campoRazonSocial = (EditText) findViewById(R.id.textRazonSocial);
        campoClaveAcceso =(EditText) findViewById(R.id.clave_acceso);
        campoSpinner = (Spinner) findViewById(R.id.rol_usuario);
        btnRegistrarse = (Button) findViewById(R.id.btnRegistrarse);
        textRegistrarse = (TextView) findViewById(R.id.textLogin);
        final SharedPreferences sharedPref = getApplicationContext()
                .getSharedPreferences(getString(R.string.data_preferences),Context.MODE_PRIVATE);
        final Context context = getApplicationContext();
        Intent intentExtraData =getIntent();
        final String JsonRoles = intentExtraData.getStringExtra("RolesString");
        final Toast toast = Toast.makeText(getApplicationContext(),"",Toast.LENGTH_LONG);
        final Gson gson = new Gson();
        final Type tipoListaEmpleados = new TypeToken<List<Rol>>(){}.getType();
        final List<Rol> roles = gson.fromJson(JsonRoles, tipoListaEmpleados);
        final List<String> items = new ArrayList<String>();
        final  OkHttpClient client = new OkHttpClient();





        items.add("Seleccione un rol");
        for ( Rol rol:roles ) {
            items.add((rol.getDescripcion()));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, items);
        campoSpinner.setAdapter(adapter);






       // Adapter  adapter = new Ad;

        textRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });

        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usuario = campoUsuario.getText().toString();
                String razonSocial = campoRazonSocial.getText().toString();
                String claveAcceso = campoClaveAcceso.getText().toString();

                int rolSeleccionado =  0;

                if( usuario.isEmpty() ){
                     toast.setText("Algunos campos estan incompletos");
                     toast.show();
                    return;
                }else{
                    if( usuario.contains(" ") ){
                        toast.setText("EL usuario no debe contener espacios");
                        toast.show();
                        return;
                    }
                }

                if( claveAcceso.isEmpty() ){
                    toast.setText("Algunos campos estan incompletos");
                    toast.show();
                    return;
                }

                if( campoSpinner.getSelectedItemPosition() == 0 ){

                    toast.setText("Debe seleccionar un rol para continuar");
                    toast.show();
                    return;

                }else{

                    for ( Rol rol:roles ) {

                        if( items.get(campoSpinner.getSelectedItemPosition()).equals(rol.getDescripcion())){
                            rolSeleccionado = rol.getCod();
                            break;
                        }
                    }
                }

                HashMap<String,String> arrayData = new HashMap<String,String>();
                arrayData.put("usuario",usuario);
                arrayData.put("clave",claveAcceso);
                arrayData.put("razonSocial",razonSocial);
                arrayData.put("rol",String.valueOf( rolSeleccionado )  );
                //Log.e("Array Datos",gson.toJson(arrayData));
                RequestBody body = RequestBody.create(JSON,gson.toJson(arrayData));
                Request request = new Request.Builder()
                        .url((String) getResources().getText(R.string.url_api)+"/Registro")
                        .method("POST",body)
                        .build();
                 Log.e("MAKER","CALL");
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {
                        toast.setText("Fallo al tratar de enviar la peticion");
                        toast.show();
                    }

                    @Override
                    public void onResponse(Response response) throws IOException {

                        if( response.isSuccessful() ){

                            Thread thread = new Thread(){
                                public void run(){
                                    runOnUiThread(new Runnable() {
                                        public void run() {
                                            AlertDialog.Builder builder = new AlertDialog.Builder(Registrarse.this);
                                            builder.setMessage("Usuario registrado con exito"+"\n¿ Desea in al login?")
                                                    .setTitle("Proceso Exitoso");

                                            builder.setPositiveButton(context.getString(R.string.ok_btn), new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    finish();
                                                }
                                            });
                                            builder.setNegativeButton(R.string.cancel_btn, new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    // User cancelled the dialog
                                                }
                                            });

                                            builder.create();
                                            builder.show();

                                        }
                                    });
                                }
                            };


                            thread.start();








                        }else{
                            toast.setText(gson.fromJson(response.body().string(),String.class));
                            toast.show();

                        }

                    }
                });









            }
        });


    }
}
