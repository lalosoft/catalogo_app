package com.delta.catalogo;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FragmentNuevos extends Fragment {

    private RecyclerView recyclerView;
    private ProductoAdapter adapter;
    private RecyclerView.LayoutManager lManager;
    List<Producto> items;
    Conexion conexion;

    public FragmentNuevos() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment_nuevos, container, false);
        getActivity().setTitle("Productos Nuevos");
        conexion = new Conexion(getActivity());
        nuevos();

        if(items.size() <= 0) Toast.makeText(getActivity(), "No se encontraron nuevos productos", Toast.LENGTH_SHORT).show();
        else Toast.makeText(getActivity(), items.size() + " Productos Encontrados", Toast.LENGTH_SHORT).show();

        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_nuevos);
        recyclerView.setHasFixedSize(true);

        lManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(lManager);

        adapter = new ProductoAdapter(items);
        adapter.setOnProductoListener(new OnProductoListener() {
            @Override
            public void onFondoClicked(int position) {
                Bundle args = new Bundle();
                args.putString("clave", items.get(position).getClave());

                ProductoFragment productoFragment = new ProductoFragment();
                productoFragment.setArguments(args);
                /*FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, productoFragment, "productoFragment");
                fragmentTransaction.commit();*/
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frame, productoFragment, "productoFragment").addToBackStack(null)
                        .commit();
            }
        });
        recyclerView.setAdapter(adapter);
        return v;
    }

    public List<Producto> nuevos()
    {
        Cursor c = conexion.getNuevos();
        items = new ArrayList<>();
        if(c.moveToFirst())
        {
            do {
                items.add(new Producto(c.getString(0), c.getString(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4)));
            }while (c.moveToNext());
        }
        return items;
    }
}