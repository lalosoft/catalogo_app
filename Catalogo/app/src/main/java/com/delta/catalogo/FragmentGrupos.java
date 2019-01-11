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

public class FragmentGrupos extends Fragment
{
    ListView lst;
    ArrayList<String> lista;
    ArrayAdapter adapter;
    Conexion cn;

    public FragmentGrupos() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment_grupos, container, false);
        getActivity().setTitle("Grupos");
        cn = new Conexion(getActivity());

        lst = (ListView) v.findViewById(R.id.listv_grupos);
        lista = cn.getGrupos();
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, lista);
        lst.setAdapter(adapter);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String gpo = adapter.getItem(position).toString();
                Bundle args = new Bundle();
                args.putString("grupo", gpo);

                InfoGrupo infoGrupo_fragment = new InfoGrupo();
                infoGrupo_fragment.setArguments(args);
                /*FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, infoGrupo_fragment, "info_grupoFrag");
                fragmentTransaction.commit();*/
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frame, infoGrupo_fragment, "info_grupoFrag").addToBackStack(null)
                        .commit();
            }
        });
        return v;
    }
}
