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

public class FragmentSustancias extends Fragment {

    ListView list;
    ArrayList<String> lista;
    ArrayAdapter adapter;
    Conexion conexion;

    public FragmentSustancias() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment_sustancias, container, false);
        conexion = new Conexion(getActivity());
        getActivity().setTitle("Sustancias");

        list = (ListView) v.findViewById(R.id.list_sust);
        lista = conexion.getSustancias();

        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, lista);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getActivity(), lista.get(position), Toast.LENGTH_SHORT).show();
                Bundle args = new Bundle();
                args.putString("sust", lista.get(position));

                InfoSust infoSust = new InfoSust();
                infoSust.setArguments(args);
                /*FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, infoSust, "frag_info_sust");
                fragmentTransaction.commit();*/
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frame, infoSust, "frag_info_sust").addToBackStack(null)
                        .commit();
            }
        });
        return v;
    }
}
