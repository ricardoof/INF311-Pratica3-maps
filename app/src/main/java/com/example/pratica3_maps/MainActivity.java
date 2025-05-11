package com.example.pratica3_maps;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] menu = new String [] {"Minha casa na cidade natal", "Minha casa em Viçosa", "Meu departamento", "Relatório", "Fechar aplicação"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, menu);
        setListAdapter(arrayAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        String aux = l.getItemAtPosition(position).toString();

        switch(position) {
            case 0:
                Intent itaocara = new Intent(getBaseContext(), MostrarMapa.class);
                itaocara.putExtra("id", "1");
                Toast.makeText(getBaseContext(), aux, Toast.LENGTH_SHORT).show();
                startActivity(itaocara);
//                Cursor itaocara = BancoDadosSingleton.getInstance().buscar("Location", new String[]{"id", "descricao", "latitude", "longitude"}, "id == 1", null);
//                Log.i("MENU", "Cliquei em itaocara");
//                if (itaocara.moveToFirst()) {
//                    double latitude = itaocara.getDouble(itaocara.getColumnIndexOrThrow("latitude"));
//                    double longitude = itaocara.getDouble(itaocara.getColumnIndexOrThrow("longitude"));
//                    String descricao = itaocara.getString(itaocara.getColumnIndexOrThrow("descricao"));
//
//                    it.putExtra("local", "itaocara");
//                    it.putExtra("lat", latitude);
//                    it.putExtra("lng", longitude);
//                    it.putExtra("descricao", descricao);
//                    Log.i("INTENT", "Cliquei em itaocara");
//                    Toast.makeText(getBaseContext(), aux, Toast.LENGTH_SHORT).show();
//                    startActivity(it);
//                }
                break;
            case 1:
                Intent vicosa = new Intent(getBaseContext(), MostrarMapa.class);
                vicosa.putExtra("id", "2");
                Toast.makeText(getBaseContext(), aux, Toast.LENGTH_SHORT).show();
                startActivity(vicosa);
//                Cursor vicosa = BancoDadosSingleton.getInstance().buscar("Location", new String[]{"id", "descricao", "latitude", "longitude"}, "id = 2", null);
//                Log.i("MENU", "Cliquei em vicosa");
//                if (vicosa.moveToFirst()) {
//                    double latitude = vicosa.getDouble(vicosa.getColumnIndexOrThrow("latitude"));
//                    double longitude = vicosa.getDouble(vicosa.getColumnIndexOrThrow("longitude"));
//                    String descricao = vicosa.getString(vicosa.getColumnIndexOrThrow("descricao"));
//
//                    it.putExtra("local", "vicosa");
//                    it.putExtra("lat", latitude);
//                    it.putExtra("lng", longitude);
//                    it.putExtra("descricao", descricao);
//                    Log.i("INTENT", "Cliquei em vicosa");
//                    Toast.makeText(getBaseContext(), aux, Toast.LENGTH_SHORT).show();
//                    startActivity(it);
//                }
                break;
            case 2:
                Intent dpi = new Intent(getBaseContext(), MostrarMapa.class);
                dpi.putExtra("id", "3");
                Toast.makeText(getBaseContext(), aux, Toast.LENGTH_SHORT).show();
                startActivity(dpi);
//                Cursor dpi = BancoDadosSingleton.getInstance().buscar("Location", new String[]{"id", "descricao", "latitude", "longitude"}, "id = 3", null);
//                Log.i("MENU", "Cliquei em dpi");
//                if (dpi.moveToFirst()) {
//                    double latitude = dpi.getDouble(dpi.getColumnIndexOrThrow("latitude"));
//                    double longitude = dpi.getDouble(dpi.getColumnIndexOrThrow("longitude"));
//                    String descricao = dpi.getString(dpi.getColumnIndexOrThrow("descricao"));
//
//                    it.putExtra("local", "dpi");
//                    it.putExtra("lat", latitude);
//                    it.putExtra("lng", longitude);
//                    it.putExtra("descricao", descricao);
//                    Log.i("INTENT", "Cliquei em dpi");
//                    Toast.makeText(getBaseContext(), aux, Toast.LENGTH_SHORT).show();
//                    startActivity(it);
//                }
                break;
            case 3:
                break;
            case 4:
                BancoDadosSingleton.getInstance().fechar();
                finish();
                break;
        }
    }
}