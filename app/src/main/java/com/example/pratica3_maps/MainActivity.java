package com.example.pratica3_maps;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
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
                break;
            case 1:
                Intent vicosa = new Intent(getBaseContext(), MostrarMapa.class);
                vicosa.putExtra("id", "2");
                Toast.makeText(getBaseContext(), aux, Toast.LENGTH_SHORT).show();
                startActivity(vicosa);
                break;
            case 2:
                Intent dpi = new Intent(getBaseContext(), MostrarMapa.class);
                dpi.putExtra("id", "3");
                Toast.makeText(getBaseContext(), aux, Toast.LENGTH_SHORT).show();
                startActivity(dpi);
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