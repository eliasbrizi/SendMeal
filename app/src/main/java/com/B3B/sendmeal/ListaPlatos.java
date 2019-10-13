package com.B3B.sendmeal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.B3B.sendmeal.domain.Plato;

import java.util.ArrayList;

public class ListaPlatos extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
/*
Lista de platos
 */
    public static ArrayList<Plato> _PLATOS = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listaplatos);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarBack);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerListaPlatos);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //TODO
        if (_PLATOS.isEmpty()){
             /*
              creo una lista de un plato para que no explotee
             */
             Plato p;
             for (int i=0; i<5 ; i++){
             p = new Plato(i+1, 100+10*i, 150.0+i, "matambre", "Un matambre comunacho y conrriente");
            _PLATOS.add(p);}}
             /*
             borrar anterior
            */
        mAdapter = new PlatoViewAdapter(getApplicationContext(),_PLATOS,this);
        mRecyclerView.setAdapter(mAdapter);


    }

}
