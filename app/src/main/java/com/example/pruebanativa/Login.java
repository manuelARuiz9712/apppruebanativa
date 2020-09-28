package com.example.pruebanativa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import modelos.Rol;
import modelos.Usuario;

public class Login extends AppCompatActivity {

    TextView labelRegistrarse;
    Button btnIngresar;
    EditText campoUsuario;
    EditText campoClave;
    static Context context;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

       labelRegistrarse = (TextView)  findViewById(R.id.textRegistrarse);
       campoUsuario = (EditText) findViewById(R.id.usuarioText);
       campoClave = (EditText) findViewById(R.id.claveText);
       btnIngresar = (Button) findViewById(R.id.btnIngresar);
       final Toast toast  = Toast.makeText(getApplicationContext(),"",Toast.LENGTH_LONG);
       final OkHttpClient client = new OkHttpClient();
       final Gson gson = new Gson();
       final SharedPreferences sharedPref = getApplicationContext()
               .getSharedPreferences(getString(R.string.data_preferences),Context.MODE_PRIVATE);







       context = getApplicationContext();
       Intent intentExtraData =getIntent();
       final String JsonRoles = intentExtraData.getStringExtra("RolesString");


       labelRegistrarse.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               Intent intent = new Intent(context,Registrarse.class);
               intent.putExtra("RolesString",JsonRoles);
               startActivity(intent);

           }
       });

       btnIngresar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

            String usuario = campoUsuario.getText().toString();
            String clave = campoClave.getText().toString();

            if (usuario.isEmpty() || clave.isEmpty()  ){
                toast.setText("Algunos campos no han sido diligenciados");
                toast.show();

                return;
            }

       /*     Thread thread = new Thread(){
              public void run(){
                  runOnUiThread(new Runnable() {

                      public void run() {


                      }
                  });


              }
            }; */
               HashMap<String,String> params = new HashMap<String, String>();
               params.put("usuario",usuario);
               params.put("clave",clave);


               RequestBody body = RequestBody.create(JSON,gson.toJson(params));


               Request request = new Request.Builder()
                       .url((String) getResources().getText(R.string.url_api)+"/Login")
                       .method("POST", body)
                       .build();

               client.newCall(request).enqueue(new Callback() {
                   @Override
                   public void onFailure(Request request, IOException e) {
                       toast.setText("No se pudo enviar la solicitd");

                   }

                   @Override
                   public void onResponse(Response response) throws IOException {

                       if( response.isSuccessful() ){
                           Log.e("DATA SESION", response.message());
                           SharedPreferences.Editor editor = sharedPref.edit();

                           editor.putString("dataSesion",response.body().string());
                           editor.commit();
                           Intent intent = new Intent(context,MainActivity.class);
                           startActivity(intent);







                       }else{
                           toast.setText(gson.fromJson(response.body().string(),String.class));
                           toast.show();
                       }

                   }
               });


           }
       });







    }

    @Override
    public void onBackPressed() {
// super.onBackPressed();
// Not calling **super**, disables back button in current screen.
    }
}
