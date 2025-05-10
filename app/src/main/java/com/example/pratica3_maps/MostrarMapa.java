package com.example.pratica3_maps;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.Manifest;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DecimalFormat;

public class MostrarMapa extends FragmentActivity implements OnMapReadyCallback {
    public LatLng ITAOCARA, VICOSA, DPI, coordenada;
    private FusedLocationProviderClient minhaLocalizacao;
    public Marker meuMarcador;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_mapa);

        Intent it = getIntent();
        ITAOCARA = new LatLng(it.getDoubleExtra("lat1", 0), it.getDoubleExtra("lng1", 0));
        VICOSA = new LatLng(it.getDoubleExtra("lat2", 0), it.getDoubleExtra("lng2", 0));
        DPI = new LatLng(it.getDoubleExtra("lat3", 0), it.getDoubleExtra("lng3", 0));
        String local = it.getStringExtra("local");

        if("itaocara".equals(local)){
            coordenada = ITAOCARA;
        } else if ("vicosa".equals(local)) {
            coordenada = VICOSA;
        } else {
            coordenada = DPI;
        }

        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapa)).getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.addMarker(new MarkerOptions().position(ITAOCARA).title("Minha casa em Itaocara"));
        map.addMarker(new MarkerOptions().position(VICOSA).title("Minha casa em Viçosa"));
        map.addMarker(new MarkerOptions().position(DPI).title("DPI/UFV"));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(coordenada, 16));
    }

    public void Itaocara(View v) {
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ITAOCARA, 16);
        map.animateCamera(update);
    }

    public void Vicosa(View v) {
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(VICOSA, 16);
        map.animateCamera(update);
    }

    public void DPI(View v) {
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(DPI, 16);
        map.animateCamera(update);
    }

    public void minhaLocalizacao(View v) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }

        minhaLocalizacao = LocationServices.getFusedLocationProviderClient(this);
        minhaLocalizacao.getLastLocation().addOnSuccessListener(location -> {
            if (location != null) {
                LatLng ATUAL = new LatLng(location.getLatitude(), location.getLongitude());

                Location posicaoAtual = new Location("");
                posicaoAtual.setLatitude(location.getLatitude());
                posicaoAtual.setLongitude(location.getLongitude());

                Location vicosa = new Location("");
                vicosa.setLatitude(VICOSA.latitude);
                vicosa.setLongitude(VICOSA.longitude);

                double distancia = posicaoAtual.distanceTo(vicosa);
                DecimalFormat df = new DecimalFormat("0.##");
                String distanciaString = df.format(distancia);
                Toast.makeText(getBaseContext(), distanciaString +" m", Toast.LENGTH_SHORT).show();

                if(meuMarcador != null) {
                    meuMarcador.remove();
                }

                map.animateCamera(CameraUpdateFactory.newLatLngZoom(ATUAL, 16));
                meuMarcador = map.addMarker(new MarkerOptions().position(ATUAL).title("Minha localização atual").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            }
        });
    }
}