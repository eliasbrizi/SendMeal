package com.B3B.sendmeal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.B3B.sendmeal.dao.PedidoRepository;
import com.B3B.sendmeal.dao.PlatoRepository;
import com.B3B.sendmeal.domain.Pedido;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarSendMeal);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i1;
        switch(item.getItemId()) {
            case R.id.menuItemRegistrar:
                i1 = new Intent (this,MainActivity.class);
                startActivity(i1);
                return true;
            case R.id.menuItemCrear:
                i1 = new Intent(this, AltaNuevoPlato.class);
                startActivity(i1);
                return true;
            case R.id.menuItemConsultar:
                /*
                Pido una nueva lista de platos por si se realizo una busqueda
                y la lista quedo con basura
                 */
                PlatoRepository.getInstance().listarPlatos();
                i1 = new Intent(this,ListaPlatos.class);
                startActivity(i1);
                return true;
            case R.id.menuItemBuscar:
                i1 = new Intent(this,BuscarPlato.class);
                startActivity(i1);
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return true;
    }
}