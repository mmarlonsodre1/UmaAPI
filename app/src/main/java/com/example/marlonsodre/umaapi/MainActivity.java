package com.example.marlonsodre.umaapi;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {
    private FragmentManager mFragManager;
    private SupportMapFragment mMapFragment;
    private GoogleMap mMap;
    private LatLng mMeuLocal;
    private Spinner mSpinner,mSpinner1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMeuLocal = new LatLng(-22.905744, -43.176886);
        mSpinner = findViewById(R.id.spinner);
        mSpinner1 = findViewById(R.id.spinner1);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.locais, android.R.layout.simple_spinner_item);
        mSpinner.setAdapter(adapter);
        mSpinner1.setAdapter(adapter);

        mFragManager = getSupportFragmentManager();
        mMapFragment = (SupportMapFragment) mFragManager.findFragmentById(R.id.mapLocal);
        mMapFragment.getMapAsync(mapa);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                switch (position){
                    case 0:
                        mMeuLocal = new LatLng(-22.905744, -43.176886);
                        break;
                    case 1:
                        mMeuLocal = new LatLng(-22.9705212, -43.182531);
                        break;
                    case 2:
                        mMeuLocal = new LatLng(-22.912770, -43.229478);
                        break;
                }
                atualizarMapa();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                mMeuLocal = new LatLng(-22.905744, -43.176886);
                atualizarMapa();
            }
        });

        mSpinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                switch (position){
                    case 0:
                        mMeuLocal = new LatLng(-22.905744, -43.176886);
                        break;
                    case 1:
                        mMeuLocal = new LatLng(-22.9705212, -43.182531);
                        break;
                    case 2:
                        mMeuLocal = new LatLng(-22.912770, -43.229478);
                        break;
                }
                atualizarMapa1();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private OnMapReadyCallback mapa = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;
            atualizarMapa();
        }
    };

    private void atualizarMapa (){
        CameraPosition posicao = new CameraPosition.Builder().target(mMeuLocal).zoom(15).build();
        CameraUpdate atualizacao = CameraUpdateFactory.newCameraPosition(posicao);
        MarkerOptions marcador = new MarkerOptions().position(mMeuLocal).title("INFNET");
        marcador.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        mMap.clear();
        mMap.addMarker(marcador);
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mMap.moveCamera(atualizacao);
    }

    private void atualizarMapa1 (){
        CameraPosition posicao = new CameraPosition.Builder().target(mMeuLocal).zoom(10).build();
        CameraUpdate atualizacao = CameraUpdateFactory.newCameraPosition(posicao);
        MarkerOptions marcador1 = new MarkerOptions().position(mMeuLocal).title("Marcador 2");
        marcador1.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        mMap.addMarker(marcador1);
        mMap.moveCamera(atualizacao);
    }


}
