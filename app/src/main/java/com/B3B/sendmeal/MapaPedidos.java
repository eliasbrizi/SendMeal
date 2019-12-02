package com.B3B.sendmeal;

import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.B3B.sendmeal.dao.PedidoRepositoryServer;
import com.B3B.sendmeal.domain.EstadoPedido;
import com.B3B.sendmeal.domain.Pedido;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.List;

public class MapaPedidos extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Spinner spnEstadoPedido;
    private EstadoPedido[] adapter = {EstadoPedido.PENDIENTE, EstadoPedido.ACEPTADO,
                    EstadoPedido.EN_ENVIO, EstadoPedido.RECHAZADO,
                    EstadoPedido.EN_PREPARACION, EstadoPedido.ENTREGADO,
                    EstadoPedido.ENVIADO, EstadoPedido.CANCELADO};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_pedidos);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        spnEstadoPedido = (Spinner) findViewById(R.id.spnEstadoPedidoMapa);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,adapter);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spnEstadoPedido.setAdapter(aa);
        spnEstadoPedido.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                actualizarMarcadores(adapter[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                actualizarMarcadores();
            }
        });

                /*
            Cargo los pedidos en el repositorio
         */
        PedidoRepositoryServer.getInstance().listarPedidos();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            @Override
            public View getInfoWindow(Marker arg0) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {

                LinearLayout info = new LinearLayout(getApplicationContext());
                info.setOrientation(LinearLayout.VERTICAL);

                TextView title = new TextView(getApplicationContext());
                title.setTextColor(Color.BLACK);
                title.setGravity(Gravity.CENTER);
                title.setTypeface(null, Typeface.BOLD);
                title.setText(marker.getTitle());

                TextView snippet = new TextView(getApplicationContext());
                snippet.setTextColor(Color.GRAY);
                snippet.setText(marker.getSnippet());

                info.addView(title);
                info.addView(snippet);

                return info;
            }
        });
        actualizarMarcadores();
    }

    private void actualizarMarcadores(){
        List<Pedido> lista = PedidoRepositoryServer.getInstance().getListaPedidos();
        mMap.clear();
        for (Pedido p: lista)
        mMap.addMarker(new MarkerOptions().position(new LatLng(p.getLat(),p.getLng())));
    }

    private void actualizarMarcadores(EstadoPedido e){
        //TODO implementar
        List<Pedido> lista = PedidoRepositoryServer.getInstance().getListaPedidos();
        mMap.clear();
        PolylineOptions poly = new PolylineOptions();
        for (Pedido p: lista) if(p.getEstadoPedido() == e){
            Marker marker = mMap.addMarker(new MarkerOptions().position(new LatLng(p.getLat(),p.getLng()))
                    .snippet("Id pedido: "+p.getIdPedido()+"\nEstado: "+p.getEstadoPedido()+"\nPrecio: "+p.getPrecio()));
            switch (e){
                case EN_ENVIO:
                    marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE));
                    poly.add(new LatLng(p.getLat(),p.getLng())).color(Color.YELLOW);
                    break;
                case EN_PREPARACION:
                   marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                    break;
                case ENVIADO:
                    marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
                    break;
                case ENTREGADO:
                    marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
                    break;
                case ACEPTADO:
                    marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                    break;
                default:
                    marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                    break;
            }
        }
        mMap.addPolyline(poly);
    }
}
