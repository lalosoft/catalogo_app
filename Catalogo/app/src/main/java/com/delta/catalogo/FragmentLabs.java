package com.delta.catalogo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class FragmentLabs extends Fragment {

    ListView list;
    ArrayList<String> lista;
    ArrayAdapter adapter;
    Conexion conexion;

    public FragmentLabs() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment_labs, container, false);

        conexion = new Conexion(getActivity());
        getActivity().setTitle("Laboratorios");

        list = (ListView) v.findViewById(R.id.list_labs);
        lista = conexion.getLabs();

        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, lista);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getActivity(), lista.get(position).toString(), Toast.LENGTH_SHORT).show();
                Bundle args = new Bundle();
                args.putString("lab", lista.get(position));

                InfoLab infoLab = new InfoLab();
                infoLab.setArguments(args);
                /*FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, infoLab, "frag_info_lab");
                fragmentTransaction.commit();*/
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frame, infoLab, "frag_info_lab").addToBackStack(null)
                        .commit();
            }
        });
        return v;
    }
}
