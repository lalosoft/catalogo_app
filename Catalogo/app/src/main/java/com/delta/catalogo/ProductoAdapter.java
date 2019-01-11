package com.delta.catalogo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.File;
import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder>
{
    Bitmap bm;
    private List<Producto> items;
    private OnProductoListener onProductoListener;

    public void setOnProductoListener(OnProductoListener onProductoListener)
    {
        this.onProductoListener = onProductoListener;
    }

    public ProductoAdapter(List<Producto> items)
    {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public ProductoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_card, parent, false);
        return new ProductoViewHolder(v, onProductoListener);
    }

    @Override
    public void onBindViewHolder(ProductoViewHolder holder, int position) {
        BitmapFactory.Options localOptions = new BitmapFactory.Options();
        localOptions.inSampleSize = 6;

        File f = new File("/mnt/sdcard/Android/data/farmadelta/imagen/"+ items.get(position).getImagen() + ".jpg");
        if(f.exists())
        {
            bm = BitmapFactory.decodeFile(f.getAbsolutePath(), localOptions);
            holder.imagen.setImageBitmap(bm);
        }
        else holder.imagen.setImageResource(R.drawable.no_disponible);
        while (true)
        {
            String name = items.get(position).getNombre();
            if(name.contains("@"))
            {
                name = name.replace("@","");
            }

            float precio = Float.parseFloat(items.get(position).getPrecio());

            holder.clave.setText(items.get(position).getClave());
            holder.nombre.setText(name);
            //holder.precio.setText("$".concat(String.format("%.02f", precio).replace(',', '.')));
            holder.lab.setText(items.get(position).getLab());
            holder.present.setText(items.get(position).getPresent());
            return;
        }
    }

    public static class ProductoViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView imagen;
        public TextView clave;
        public TextView nombre;
        public TextView precio;
        public TextView lab;
        public TextView present;

        public ProductoViewHolder(View itemView, final OnProductoListener onProductoListener)
        {
            super(itemView);
            imagen = (ImageView) itemView.findViewById(R.id.imagen);
            clave = (TextView) itemView.findViewById(R.id.clave);
            nombre = (TextView) itemView.findViewById(R.id.nombre);
            //precio = (TextView) itemView.findViewById(R.id.precio);
            lab = (TextView) itemView.findViewById(R.id.lab);
            present = (TextView) itemView.findViewById(R.id.present);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION) {
                        onProductoListener.onFondoClicked(position);
                    }
                }
            });
        }
    }
}