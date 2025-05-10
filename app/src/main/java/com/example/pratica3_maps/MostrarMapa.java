package com.example.pratica3_maps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MostrarMapa extends FragmentActivity implements OnMapReadyCallback {
    public LatLng ITAOCARA = new LatLng(-21.667090042336575, -42.081350019614895);
    public LatLng VICOSA = new LatLng(-20.756845864540256, -42.87517877659752);
    public LatLng DPI = new LatLng(-20.764807038105072, -42.86837497388309);
    public LatLng coordenada;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_mapa);

        Intent it = getIntent();
        double lat = it.getDoubleExtra("lat", 0);
        double lng = it.getDoubleExtra("lng", 0);
        coordenada = new LatLng(lat, lng);
        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapa)).getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.addMarker(new MarkerOptions().position(ITAOCARA).title("Cidade natal"));
        map.addMarker(new MarkerOptions().position(VICOSA).title("Minha casa em Viçosa"));
        map.addMarker(new MarkerOptions().position(DPI).title("DPI"));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(coordenada, 16));
    }

    public void onClickItaocara(View v) {
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ITAOCARA, 16);
        map.animateCamera(update);
    }

    public void onClickVicosa(View v) {
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(VICOSA, 16);
        map.animateCamera(update);
    }

    public void onClickDPI(View v) {
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(DPI, 16);
        map.animateCamera(update);
    }
}