package com.example.pruebanativa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.pruebanativa.R;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;


import java.io.IOException;

public class IntroView extends AppCompatActivity {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_view);

        final  Intent intent = new Intent(getApplicationContext(), Login.class);
        final Toast toast = Toast.makeText(this, "heyyy", Toast.LENGTH_SHORT);


        final SharedPreferences sharedPref = getApplicationContext()
                .getSharedPreferences(getString(R.string.data_preferences),Context.MODE_PRIVATE);

        String sesion =  sharedPref.getString("dataSesion",null);

        if(sesion != null ){

            Intent intentHome = new Intent(this,MainActivity.class);
            startActivity(intentHome);
            return;

        }

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(getString(R.string.url_api)+"/Getroles")
                .method("GET", null)
                .build();

         client.newCall(request).enqueue(new Callback() {
             @Override
             public void onFailure(Request request, IOException e) {
                 e.printStackTrace();
                 Log.e("ERROR 1::","FALLAS TOTALES"+e.getMessage());
                 toast.setText("Error de conexion a internet");
                 toast.setDuration(Toast.LENGTH_LONG);
                 toast.show();


             }

             @Override
             public void onResponse(Response response) throws IOException {

                 if (!response.isSuccessful()) {
                     Log.e("ERROR 2::","FALLAS TOTALES 2"+response.body().string());

                     throw new IOException("Unexpected code " + response);
                 } else {


                     intent.putExtra("RolesString",(String) response.body().string());

                     startActivity(intent);
                     // do something wih the result
                 }

             }
         });

    }









}
