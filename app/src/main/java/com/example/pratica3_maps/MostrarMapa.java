package com.example.pratica3_maps;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
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
    public LatLng VICOSA, coordenada;
    private FusedLocationProviderClient minhaLocalizacao;
    public Marker meuMarcador, marcadorLocal, marcadorItaocara, marcadorVicosa, marcadorDPI;
    public String descricao;
    public Cursor local;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_mapa);
        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapa)).getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Intent it = getIntent();
        String id = it.getStringExtra("id");
        assert id != null;
        if(id.equals("1")){
            local = BancoDadosSingleton.getInstance().buscar("Location", new String[]{"id", "descricao", "latitude", "longitude"}, "id == 1", null);
        } else if(id.equals("2")) {
            local = BancoDadosSingleton.getInstance().buscar("Location", new String[]{"id", "descricao", "latitude", "longitude"}, "id == 2", null);
        } else {
            local = BancoDadosSingleton.getInstance().buscar("Location", new String[]{"id", "descricao", "latitude", "longitude"}, "id == 3", null);
        }
        if (local.moveToFirst()) {
            double latitude = local.getDouble(local.getColumnIndexOrThrow("latitude"));
            double longitude = local.getDouble(local.getColumnIndexOrThrow("longitude"));
            descricao = local.getString(local.getColumnIndexOrThrow("descricao"));
            coordenada = new LatLng(latitude, longitude);
        }
        if(marcadorLocal != null) {
            marcadorLocal.remove();
        }
        map = googleMap;
        marcadorLocal = map.addMarker(new MarkerOptions().position(coordenada).title(descricao));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(coordenada, 16));
    }

    public void Itaocara(View v) {
        Cursor itaocara = BancoDadosSingleton.getInstance().buscar("Location", new String[]{"id", "descricao", "latitude", "longitude"}, "id == 1", null);
        if (itaocara.moveToFirst()) {
            double latitude = itaocara.getDouble(itaocara.getColumnIndexOrThrow("latitude"));
            double longitude = itaocara.getDouble(itaocara.getColumnIndexOrThrow("longitude"));
            descricao = itaocara.getString(itaocara.getColumnIndexOrThrow("descricao"));
            coordenada = new LatLng(latitude, longitude);
        }
        if(marcadorItaocara != null) {
            marcadorItaocara.remove();
        }
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        marcadorItaocara = map.addMarker(new MarkerOptions().position(coordenada).title(descricao));
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(coordenada, 16);
        map.animateCamera(update);
    }

    public void Vicosa(View v) {
        Cursor vicosa = BancoDadosSingleton.getInstance().buscar("Location", new String[]{"id", "descricao", "latitude", "longitude"}, "id == 2", null);
        if (vicosa.moveToFirst()) {
            double latitude = vicosa.getDouble(vicosa.getColumnIndexOrThrow("latitude"));
            double longitude = vicosa.getDouble(vicosa.getColumnIndexOrThrow("longitude"));
            descricao = vicosa.getString(vicosa.getColumnIndexOrThrow("descricao"));
            coordenada = new LatLng(latitude, longitude);
        }
        if(marcadorVicosa != null) {
            marcadorVicosa.remove();
        }
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        marcadorVicosa = map.addMarker(new MarkerOptions().position(coordenada).title(descricao));
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(coordenada, 16);
        map.animateCamera(update);
    }

    public void DPI(View v) {
        Cursor dpi = BancoDadosSingleton.getInstance().buscar("Location", new String[]{"id", "descricao", "latitude", "longitude"}, "id == 3", null);
        if (dpi.moveToFirst()) {
            double latitude = dpi.getDouble(dpi.getColumnIndexOrThrow("latitude"));
            double longitude = dpi.getDouble(dpi.getColumnIndexOrThrow("longitude"));
            descricao = dpi.getString(dpi.getColumnIndexOrThrow("descricao"));
            coordenada = new LatLng(latitude, longitude);
        }
        if(marcadorDPI != null) {
            marcadorDPI.remove();
        }
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        marcadorDPI = map.addMarker(new MarkerOptions().position(coordenada).title(descricao));
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(coordenada, 16);
        map.animateCamera(update);
    }

    public void minhaLocalizacao(View v) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }

        Cursor vicosa = BancoDadosSingleton.getInstance().buscar("Location", new String[]{"id", "descricao", "latitude", "longitude"}, "id == 2", null);
        if (vicosa.moveToFirst()) {
            double latitude = vicosa.getDouble(vicosa.getColumnIndexOrThrow("latitude"));
            double longitude = vicosa.getDouble(vicosa.getColumnIndexOrThrow("longitude"));
            coordenada = new LatLng(latitude, longitude);
        }

        minhaLocalizacao = LocationServices.getFusedLocationProviderClient(this);
        minhaLocalizacao.getLastLocation().addOnSuccessListener(location -> {
            if (location != null) {
                LatLng ATUAL = new LatLng(location.getLatitude(), location.getLongitude());

                Location posicaoAtual = new Location("");
                posicaoAtual.setLatitude(location.getLatitude());
                posicaoAtual.setLongitude(location.getLongitude());

                Location casaVicosa = new Location("");
                casaVicosa.setLatitude(coordenada.latitude);
                casaVicosa.setLongitude(coordenada.longitude);

                double distancia = posicaoAtual.distanceTo(casaVicosa);
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