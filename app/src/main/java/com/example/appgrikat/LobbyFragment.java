package com.example.appgrikat;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SearchView;


public class LobbyFragment extends Fragment {
    private RecyclerView recyclerBebidas;
    private LinearLayoutManager layoutManager;
    private AdaptadorBebida adaptador;
    private AdaptadorComentarios adaptador2;
    private SearchView a;
    private EditText busca;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lobby,container,false);
        a=(SearchView) view.findViewById(R.id.sVBuscar);
        busca= (EditText) view.findViewById(R.id.eTBuscar);
        recyclerBebidas = view.findViewById(R.id.idRecycler);
        layoutManager =  new LinearLayoutManager(getActivity());
        /*new GridLayoutManager(getActivity(),1);*/
        recyclerBebidas.setLayoutManager(layoutManager);
        adaptador = new AdaptadorBebida();
        adaptador2 = new AdaptadorComentarios();
        adaptador.llenarbebidas(getContext(),busca.getText().toString());
        recyclerBebidas.setAdapter(adaptador);
        a.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adaptador.llenarbebidas(getContext(),busca.getText().toString());
                 adaptador2.getBebida();
                 adaptador2.listarValoraciones(getContext(),1);

            }
        });

        return view;
    }
}
