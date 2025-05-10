package com.example.pratica3_maps;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends ListActivity {

    public LatLng ITAOCARA = new LatLng(-21.667090042336575, -42.081350019614895);
    public LatLng VICOSA = new LatLng(-20.756845864540256, -42.87517877659752);
    public LatLng DPI = new LatLng(-20.764807038105072, -42.86837497388309);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] menu = new String [] {"Minha casa na cidade natal", "Minha casa em Viçosa", "Meu departamento", "Fechar aplicação"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu);
        setListAdapter(arrayAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        Intent it = new Intent(getBaseContext(), MostrarMapa.class);

        switch(position) {
            case 0:
                it.putExtra("lat", ITAOCARA.latitude);
                it.putExtra("lng", ITAOCARA.longitude);
                Log.d("Debug", "Opção itaocara");
                startActivity(it);
                break;
            case 1:
                it.putExtra("lat", VICOSA.latitude);
                it.putExtra("lng", VICOSA.longitude);
                Log.d("Debug", "Opção viçosa");
                startActivity(it);
                break;
            case 2:
                it.putExtra("lat", DPI.latitude);
                it.putExtra("lng", DPI.longitude);
                Log.d("Debug", "Opção dpi");
                startActivity(it);
                break;
            case 3:
                finish();
                break;
        }
    }
}