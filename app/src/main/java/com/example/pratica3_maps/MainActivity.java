package com.example.pratica3_maps;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends ListActivity {

//    public LatLng ITAOCARA = new LatLng(-21.674786599501207, -42.087803755987444);
//    public LatLng VICOSA = new LatLng(-20.756855651371684, -42.8751327472389);
//    public LatLng DPI = new LatLng(-20.764807038105072, -42.86837497388309);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] menu = new String [] {"Minha casa na cidade natal", "Minha casa em Viçosa", "Meu departamento", "Relatório", "Fechar aplicação"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, menu);
        setListAdapter(arrayAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        Intent it = new Intent(getBaseContext(), MostrarMapa.class);
//        it.putExtra("lat1", ITAOCARA.latitude);
//        it.putExtra("lng1", ITAOCARA.longitude);
//        it.putExtra("lat2", VICOSA.latitude);
//        it.putExtra("lng2", VICOSA.longitude);
//        it.putExtra("lat3", DPI.latitude);
//        it.putExtra("lng3", DPI.longitude);

        String aux = l.getItemAtPosition(position).toString();

        switch(position) {
            case 0:
                Cursor itaocara = BancoDadosSingleton.getInstance().buscar("Location", new String[]{"id, descricao, latitude, longitude"}, String.valueOf(id == 1), null);
                Toast.makeText(getBaseContext(), aux, Toast.LENGTH_SHORT).show();
                it.putExtra("local", "itaocara");
                startActivity(it);
                break;
            case 1:
                Cursor vicosa = BancoDadosSingleton.getInstance().buscar("Location", new String[]{"id, descricao, latitude, longitude"}, String.valueOf(id == 1), null);
                Toast.makeText(getBaseContext(), aux, Toast.LENGTH_SHORT).show();
                it.putExtra("local", "vicosa");
                startActivity(it);
                break;
            case 2:
                Cursor dpi = BancoDadosSingleton.getInstance().buscar("Location", new String[]{"id, descricao, latitude, longitude"}, String.valueOf(id == 1), null);
                Toast.makeText(getBaseContext(), aux, Toast.LENGTH_SHORT).show();
                it.putExtra("local", "dpi");
                startActivity(it);
                break;
            case 3:
                break;
            case 4:
                finish();
                break;
        }
    }
}