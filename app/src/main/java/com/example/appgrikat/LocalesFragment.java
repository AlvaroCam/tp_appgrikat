package com.example.appgrikat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class LocalesFragment extends Fragment {
    private RecyclerView recyclerlocal;
    private LinearLayoutManager layoutManager;
    private AdaptadorLocales adaptador;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_locales,container,false);

        recyclerlocal = view.findViewById(R.id.idreciclador_locales);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerlocal.setLayoutManager(layoutManager);

        adaptador = new AdaptadorLocales();
        recyclerlocal.setAdapter(adaptador);
        return view;

    }

}
