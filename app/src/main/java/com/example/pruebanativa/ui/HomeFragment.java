package com.example.pruebanativa.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.pruebanativa.R;

import java.util.ArrayList;
import java.util.List;

import adapters.GridAdapter;
import modelos.Citas;

public class HomeFragment extends Fragment implements AdapterView.OnItemClickListener {


    private GridView grilla_citas;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        grilla_citas = (GridView) root.findViewById(R.id.gridview);
       // final TextView textView = root.findViewById(R.id.text_home);
       //textView.setText("Hola Mundo home");
        Log.e("Create View","On create vie");

        List<Citas> citas = new ArrayList<>();

        for (int i = 0;i<10;i++){

            Citas prueba  = new Citas();
            prueba.setUsuario("Usuarrio numero "+i);
            prueba.setCod_usuario_prestador(i);
            prueba.setUsuario("Fecha Numero"+i);
            citas.add(prueba);

        }
        GridAdapter adapter = new GridAdapter(getContext(),citas);
        grilla_citas.setAdapter(adapter);
        grilla_citas.setOnItemClickListener(this);


        return root;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
