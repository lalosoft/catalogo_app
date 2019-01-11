package com.delta.catalogo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.File;
import java.util.List;

public class BuscadorAdapter extends RecyclerView.Adapter<BuscadorAdapter.BuscadorViewHolder>
{
    Bitmap bm;
    private List<Producto> items;
    private OnProductoListener onProductoListener;

    public BuscadorAdapter(List<Producto> items)
    {
        this.items = items;
    }

    public void setOnProductoListener(OnProductoListener onProductoListener)
    {
        this.onProductoListener = onProductoListener;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public BuscadorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_card, parent, false);
        return new BuscadorViewHolder(v, onProductoListener);
    }

    @Override
    public void onBindViewHolder(BuscadorViewHolder holder, int position) {
        BitmapFactory.Options localOptions = new BitmapFactory.Options();
        localOptions.inSampleSize = 6;

        File f = new File("/mnt/sdcard/Android/data/farmadelta/imagen/"+ items.get(position).getImagen() + ".jpg");
        if(f.exists())
        {
            bm = BitmapFactory.decodeFile(f.getAbsolutePath(), localOptions);
            holder.imagen_busq.setImageBitmap(bm);
        }
        else holder.imagen_busq.setImageResource(R.drawable.no_disponible);
        while (true)
        {
            String name = items.get(position).getNombre();
            if(name.contains("@"))
            {
                name = name.replace("@","");
            }
            float precio = Float.parseFloat(items.get(position).getPrecio());

            holder.nombre_busq.setText("Nombre: ".concat(items.get(position).getNombre()));
            holder.clave_busq.setText("Clave: ".concat(items.get(position).getClave()));
            //holder.precio_busq.setText("Precio: $".concat(String.format("%.02f", precio).replace(',', '.')));
            holder.lab_busq.setText("Laboratorio: ".concat(items.get(position).getLab()));
            holder.present_lab.setText("Presentacion: ".concat(items.get(position).getPresent()));
            return;
        }
    }

    public static class BuscadorViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imagen_busq;
        TextView nombre_busq;
        TextView clave_busq;
        TextView precio_busq;
        TextView lab_busq;
        TextView present_lab;

        public BuscadorViewHolder(View itemView, final OnProductoListener onProductoListener) {
            super(itemView);
            imagen_busq = (ImageView) itemView.findViewById(R.id.img_busq);
            nombre_busq = (TextView) itemView.findViewById(R.id.nombre_busq);
            clave_busq = (TextView) itemView.findViewById(R.id.clave_busq);
            //precio_busq = (TextView) itemView.findViewById(R.id.precio_busq);
            lab_busq = (TextView) itemView.findViewById(R.id.lab_busq);
            present_lab = (TextView) itemView.findViewById(R.id.present_busq);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        onProductoListener.onFondoClicked(position);
                    }
                }
            });
        }
    }
}
