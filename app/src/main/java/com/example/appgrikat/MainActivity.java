package com.example.appgrikat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONObject;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    OfertaFragment ofertaFragment;
    InicioFragment.detalles_Ofertas detalles_ofertas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_container,new InicioFragment());
        fragmentTransaction.commit();

*/


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//AQUI HAGO LA RELACION FRAGMENT CON EL MENU Y EL MAIN
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar
                , R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //para q no haya error al iniciar la app debe aparecer algo del menu
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new LobbyFragment()).commit();
        navigationView.setCheckedItem(R.id.nav_lobby);


    }

    private void parseJSON() {
        String url = "????";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
//aqui va el json array?
                 /* try {
                        JSONArray jsonArray = response.getJSONArray("??");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }*/
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //seleccionar entre las opciones lo hice con switch porq me parecio mas entendible q con if y else por default
        switch (item.getItemId()) {
            case R.id.nav_lobby:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new LobbyFragment()).commit();
                break;
            case R.id.nav_iniciar_sesion:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new InicioFragment()).commit();
                break;
            case R.id.nav_oferta:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new OfertaFragment()).commit();
                break;
            case R.id.nav_sugerencias:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SugerenciasFragment()).commit();
                break;
            case R.id.nav_locales:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new LocalesFragment()).commit();
                break;
            case R.id.nav_categorias:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new CategoriaFragment()).commit();
                break;
            case R.id.nav_calificar:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new CalificarFragment()).commit();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
//dinm. del menu

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else{
            super.onBackPressed();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.drawer_menu,menu);
        return true;
    }

}
