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

public class InfoPrecios extends Fragment {

    int precio_ini;
    int precio_top;
    private RecyclerView recyclerView;
    private ProductoAdapter adapter;
    private RecyclerView.LayoutManager lManager;
    List<Producto> items;
    Conexion conexion;

    public InfoPrecios() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_info_precios, container, false);
        conexion = new Conexion(getActivity());

        Bundle p = getArguments();
        precio_ini = p.getInt("precio_ini");
        precio_top = p.getInt("precio_top");
        getActivity().setTitle("$" + precio_ini + " - $" + precio_top);

        getProductXPreci();
        if(items.size() <=  0) {
            Toast.makeText(getActivity(), "No hay resultados por mostrar", Toast.LENGTH_SHORT).show();

            FragmentPrecios fragmentPrecios = new FragmentPrecios();
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragmentPrecios, "frag_precios");
            fragmentTransaction.commit();
        }
        else {
            Toast.makeText(getActivity(), items.size() + " resultados encontrados", Toast.LENGTH_SHORT).show();


            recyclerView = (RecyclerView) v.findViewById(R.id.reciclador_prec);
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
        }
        return v;
    }

    public List<Producto> getProductXPreci()
    {
        Cursor c = conexion.getProdsXPrecio(precio_ini, precio_top);
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
