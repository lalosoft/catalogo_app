package com.delta.catalogo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<Precios>
{
    private ArrayList<Precios> items;
    Context context;

    public ListAdapter(Context context, ArrayList<Precios> items) {
        super(context, R.layout.layout_precios, items);
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View item = inflater.inflate(R.layout.layout_precios, null);

        TextView precios = (TextView) item.findViewById(R.id.txt_precio);
        precios.setText(items.get(position).getCadena_precios());

        return item;
    }
}
