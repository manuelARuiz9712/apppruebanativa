package com.example.pruebanativa.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.pruebanativa.R;

public class PrestadoresFragment extends Fragment {

    public ListView list_view ;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_prestadores, container, false);
        list_view = (ListView)  root.findViewById(R.id.list_view);


        return root;
    }
}
