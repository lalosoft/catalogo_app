package com.delta.catalogo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class Conexion extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "FarmaDeltaDB";
    private static final int DATABASE_VERSION = 1;
    private static final String RUTA_DB = "/mnt/sdcard/Android/data/farmadelta/Base/FarmadeltaDB.db";
    //private static final String RUTA_DB = "/mnt/sdcard/farmadelta/Base/FarmadeltaDB.db" ;

    public Conexion(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    ArrayList<String> getGrupos() //Obtiene todos los grupos de productos
    {
        ArrayList<String> lista = new ArrayList<>();
        String q = "Select Grupo from Catalogo where Grupo != 'NULL' and Grupo != 'ACTIVO' group by Grupo order by Grupo asc";
        Cursor registro = SQLiteDatabase.openDatabase(RUTA_DB, null, 1).rawQuery(q, null);
        if(registro.moveToFirst())
        {
            do {
                lista.add(registro.getString(0));
            }while (registro.moveToNext());
        }
        registro.close();
        return lista;
    }

    ArrayList<String> getLabs()
    {
        ArrayList<String> lista = new ArrayList<>();
        String q = "Select Laboratorio from Catalogo where Laboratorio != 'SG.COM COM' group by Laboratorio order by Laboratorio asc";
        Cursor r = SQLiteDatabase.openDatabase(RUTA_DB, null, 1).rawQuery(q, null);
        if(r.moveToFirst())
        {
            do {
                lista.add(r.getString(0));
            }while (r.moveToNext());
        }
        r.close();
        return lista;
    }

    ArrayList<String> getSustancias()
    {
        ArrayList<String> lista = new ArrayList<>();
        String q = "Select Sustancia from Catalogo group by Sustancia order by Sustancia asc";
        Cursor s = SQLiteDatabase.openDatabase(RUTA_DB, null, 1).rawQuery(q, null);
        if(s.moveToFirst())
        {
            do {
                lista.add(s.getString(0));
            }while (s.moveToNext());
        }
        s.close();
        return lista;
    }

    Cursor getProductos(String grupo) //Otiene los datos los productos que pertenecen a un grupo
    {
        String q = "Select Clave, Nombre, Precio, Laboratorio, Presentacion from Catalogo where Grupo = '" + grupo + "'";
        return SQLiteDatabase.openDatabase(RUTA_DB, null, 1).rawQuery(q, null);
    }

    Cursor getInfoProd(String clave) //Obtiene los datos de un producto
    {
        String q = "Select Clave, Nombre, Laboratorio, Presentacion, Sustancia, Grupo, Precio from Catalogo where Clave = '" + clave + "'";
        return SQLiteDatabase.openDatabase(RUTA_DB, null, 1).rawQuery(q, null);
    }

    Cursor buscarProductos(String parametro)
    {
        String q ="Select Clave, Nombre, Precio, Laboratorio, Presentacion from Catalogo where Clave like '%" + parametro + "%' or Nombre like '%" + parametro + "%' or Laboratorio like '%" + parametro + "%' or Sustancia like '%" + parametro + "%'  or Grupo like '%" + parametro + "%' order by Precio asc";
        return SQLiteDatabase.openDatabase(RUTA_DB, null, 1).rawQuery(q, null);
    }

    Cursor getProdsXPrecio(int precio_min, int precio_max)
    {
        String q ="Select Clave, Nombre, Precio, Laboratorio, Presentacion from Catalogo where Precio > " + precio_min + " and Precio <= " + precio_max + " order by Precio asc";
        return SQLiteDatabase.openDatabase(RUTA_DB, null, 1).rawQuery(q, null);
    }

    Cursor getProdXLab(String lab)
    {
        String q = "Select Clave, Nombre, Precio, Laboratorio, Presentacion from Catalogo where Laboratorio = '" + lab + "'";
        return SQLiteDatabase.openDatabase(RUTA_DB, null, 1).rawQuery(q, null);
    }

    Cursor getProdXSust(String sust)
    {
        String q = "Select Clave, Nombre, Precio, Laboratorio, Presentacion from Catalogo where Sustancia = '" + sust + "'";
        return  SQLiteDatabase.openDatabase(RUTA_DB, null, 1).rawQuery(q, null);
    }

    Cursor getNuevos()
    {
        String q = "Select Clave, Nombre, Precio, Laboratorio, Presentacion from Catalogo where Nombre like '%(NUE%' order by Clave asc";
        return SQLiteDatabase.openDatabase(RUTA_DB, null, 1).rawQuery(q, null);
    }

    Cursor getTodos()
    {
        String q = "Select Clave, Nombre, Precio, Laboratorio, Presentacion from Catalogo order by Clave asc";
        return SQLiteDatabase.openDatabase(RUTA_DB, null, 1).rawQuery(q, null);
    }
}