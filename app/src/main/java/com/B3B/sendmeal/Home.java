package com.B3B.sendmeal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

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
                i1 = new Intent(this,AltaNuevoPlato.class);
                startActivity(i1);
                return true;
            /*case R.id.menuItemConsultar: Intent i1 =newIntent(this,Main3Activity.class);
                startActivity(i1);
                return true;*/
                default:
                Toast.makeText(this,". . . .",Toast.LENGTH_LONG).show();
            return super.onOptionsItemSelected(item);
        }
    }}