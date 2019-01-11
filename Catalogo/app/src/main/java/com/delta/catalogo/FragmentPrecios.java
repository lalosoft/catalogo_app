package com.delta.catalogo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FragmentPrecios extends Fragment {

    ListView list;
    ListAdapter adapter;
    ArrayList<Precios> precios;

    public FragmentPrecios() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment_precios, container, false);
        getActivity().setTitle("Precios");

        list = (ListView) v.findViewById(R.id.listV_precios);
        getPrices();
        adapter = new ListAdapter(getActivity(),precios);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle precio = new Bundle();
                precio.putInt("precio_ini", precios.get(position).getPrecio_inicio());
                precio.putInt("precio_top", precios.get(position).getPrecio_tope());

                InfoPrecios infoPrecios = new InfoPrecios();
                infoPrecios.setArguments(precio);
                /*FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, infoPrecios, "frag_info_precios");
                fragmentTransaction.commit();*/
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frame, infoPrecios, "frag_info_precios").addToBackStack(null)
                        .commit();
            }
        });
        return v;
    }

    public void getPrices()
    {
        precios = new ArrayList<>();
        int i = 0;
        while (i<1801)
        {
            if(i > 1000)
            {
                i = i + 100;
                precios.add(new Precios(i, i - 100, "$" + (i - 100) + " - $" + i ));
            }
            else {
                i = i + 50;
                precios.add(new Precios(i, i - 50, "$" + (i - 50) + " - $" + i ));
            }
        }
        precios.add(new Precios(5000, 1850, "Mayor a $1850" ));
    }
}