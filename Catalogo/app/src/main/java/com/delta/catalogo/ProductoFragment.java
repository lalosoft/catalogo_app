package com.delta.catalogo;

import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.File;

public class ProductoFragment extends Fragment {

    ImageView img;
    TextView clave;
    TextView nombre;
    TextView laboratorio;
    TextView sustancia;
    TextView presentacion;
    TextView precio;
    TextView grupo;
    Conexion conexion;
    String cve;
    String name;

    public ProductoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_producto, container, false);
        conexion = new Conexion(getActivity());
        cve = getArguments().getString("clave");
        getActivity().setTitle("Producto");

        img = (ImageView) v.findViewById(R.id.img_prod);
        clave = (TextView) v.findViewById(R.id.text_clave);
        nombre = (TextView) v.findViewById(R.id.text_nombre);
        laboratorio = (TextView) v.findViewById(R.id.text_lab);
        sustancia = (TextView) v.findViewById(R.id.text_sustancia);
        presentacion = (TextView) v.findViewById(R.id.text_present);
        grupo = (TextView) v.findViewById(R.id.text_grupo);
        //precio = (TextView) v.findViewById(R.id.text_precio);

        Cursor c = conexion.getInfoProd(cve);
        if(c.moveToFirst())
        {
            do {
                File imagen = new File("/mnt/sdcard/Android/data/farmadelta/imagen/" + c.getString(0) + ".jpg");
                if(imagen.exists()) img.setImageBitmap(BitmapFactory.decodeFile(imagen.getAbsolutePath()));
                else  img.setImageResource(R.drawable.no_disponible);

                clave.setText("Clave: ".concat(c.getString(0)));

                name = c.getString(1);
                if(name.contains("@"))
                {
                    name = name.replace("@","");
                }

                nombre.setText(name);
                laboratorio.setText("Laboratorio: ".concat(c.getString(2)));
                sustancia.setText("Sustancia: ".concat(c.getString(4)));
                presentacion.setText("Presentacion: ".concat(c.getString(3)));
                grupo.setText("Grupo: ".concat(c.getString(5)));
                float f = Float.parseFloat((c.getString(6)));
                //precio.setText("Precio: $".concat(String.format("%.02f", f).replace(',', '.')));
            }while (c.moveToNext());
        }
        return v;
    }
}