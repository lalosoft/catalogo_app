package com.delta.catalogo;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.MenuInflater;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    //FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setTitle("Catálogo de Productos");
        FragmentInicio fragmentInicio = new FragmentInicio();
        //fragmentTransaction = getSupportFragmentManager().beginTransaction().addToBackStack(null);
        fragmentManager.beginTransaction()
                .replace(R.id.frame, fragmentInicio, "fragment_Ini").addToBackStack(null)
                .commit();
        //fragmentTransaction.replace(R.id.frame, fragmentInicio, "fragment_Ini");
        //fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() > 1) fragmentManager.popBackStack();
        else finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);

        MenuItem searchItem = menu.findItem(R.id.item_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Bundle args = new Bundle();
                args.putString("busca", query);
                FragmentBuscar fragmentBuscar = new FragmentBuscar();
                fragmentBuscar.setArguments(args);
                /*fragmentTransaction = getSupportFragmentManager().beginTransaction().addToBackStack(null);
                fragmentTransaction.replace(R.id.frame, fragmentBuscar, "frag_busca");
                fragmentTransaction.commit();*/

                fragmentManager.beginTransaction()
                        .replace(R.id.frame, fragmentBuscar, "frag_busca").addToBackStack(null)
                        .commit();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_Inicio)
        {
            setTitle("Catlogo de Productos");
            FragmentInicio fragmentInicio = new FragmentInicio();
            /*fragmentTransaction = getSupportFragmentManager().beginTransaction().addToBackStack(null);
            fragmentTransaction.replace(R.id.frame, fragmentInicio, "fragment_Ini");
            fragmentTransaction.commit();*/
            fragmentManager.beginTransaction()
                    .replace(R.id.frame, fragmentInicio, "fragment_Ini")
                    .commit();
        }

        if(id == R.id.nav_Todos)
        {
            setTitle("Todos los Productos");
            FragmentTodos fragmentTodos = new FragmentTodos();
            /*fragmentTransaction = getSupportFragmentManager().beginTransaction().addToBackStack(null);
            fragmentTransaction.replace(R.id.frame, fragmentTodos, "fragment_todos");
            fragmentTransaction.commit();*/
            fragmentManager.beginTransaction()
                    .replace(R.id.frame, fragmentTodos, "fragment_todos").addToBackStack(null)
                    .commit();

        }
        else if (id == R.id.nav_grupos)
        {
            setTitle("Grupos");
            FragmentGrupos fragmentGrupos = new FragmentGrupos();
            /*fragmentTransaction = getSupportFragmentManager().beginTransaction().addToBackStack(null);
            fragmentTransaction.replace(R.id.frame, fragmentGrupos, "fragment_grupos");
            fragmentTransaction.commit();*/
            fragmentManager.beginTransaction()
                    .replace(R.id.frame, fragmentGrupos, "fragment_grupos").addToBackStack(null)
                    .commit();
        }
        /*else if (id == R.id.nav_precios)
        {
            setTitle("Precios");
            FragmentPrecios fragmentPrecios = new FragmentPrecios();
            /*fragmentTransaction = getSupportFragmentManager().beginTransaction().addToBackStack(null);
            fragmentTransaction.replace(R.id.frame, fragmentPrecios, "frag_precios");
            fragmentTransaction.commit();*/
            /*fragmentManager.beginTransaction()
                    .replace(R.id.frame, fragmentPrecios, "frag_precios").addToBackStack(null)
                    .commit();
        }*/
        else if(id == R.id.nav_laboratorio)
        {
            setTitle("Laboratorios");
            FragmentLabs fragmentLabs = new FragmentLabs();
            /*fragmentTransaction = getSupportFragmentManager().beginTransaction().addToBackStack(null);
            fragmentTransaction.replace(R.id.frame, fragmentLabs, "frag_labs");
            fragmentTransaction.commit();*/
            fragmentManager.beginTransaction()
                    .replace(R.id.frame, fragmentLabs, "frag_labs").addToBackStack(null)
                    .commit();
        }
        else if(id == R.id.nav_sustancia)
        {
            setTitle("Sustancias");
            FragmentSustancias fragmentSustancias = new FragmentSustancias();
            /*fragmentTransaction = getSupportFragmentManager().beginTransaction().addToBackStack(null);
            fragmentTransaction.replace(R.id.frame, fragmentSustancias, "frag_sust");
            fragmentTransaction.commit();*/
            fragmentManager.beginTransaction()
                    .replace(R.id.frame, fragmentSustancias, "frag_sust").addToBackStack(null)
                    .commit();
        }
        else if(id == R.id.nav_nuevos)
        {
            setTitle("Productos Nuevos");
            FragmentNuevos fragmentNuevos = new FragmentNuevos();
            /*fragmentTransaction = getSupportFragmentManager().beginTransaction().addToBackStack(null);
            fragmentTransaction.replace(R.id.frame, fragmentNuevos, "frag_nuevos");
            fragmentTransaction.commit();*/
            fragmentManager.beginTransaction()
                    .replace(R.id.frame, fragmentNuevos, "frag_nuevos").addToBackStack(null)
                    .commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}