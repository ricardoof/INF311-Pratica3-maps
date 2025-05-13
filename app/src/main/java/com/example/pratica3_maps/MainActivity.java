package com.example.pratica3_maps;

import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.time.Instant;

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
        String mensagem, timestamp;
        ContentValues valores;

        switch(position) {
            case 0:
                mensagem = "Cidade natal";
                timestamp = "";

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    timestamp = Instant.now().toString();
                }
                valores = new ContentValues();
                valores.put("msg", mensagem);
                valores.put("timestamp", timestamp);
                valores.put("id_location", 1);
                BancoDadosSingleton.getInstance().inserir("Logs", valores);

                Intent itaocara = new Intent(getBaseContext(), MostrarMapa.class);
                itaocara.putExtra("id", "1");
                Toast.makeText(getBaseContext(), aux, Toast.LENGTH_SHORT).show();
                startActivity(itaocara);
                break;
            case 1:
                mensagem = "Viçosa";
                timestamp = "";

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    timestamp = Instant.now().toString();
                }
                valores = new ContentValues();
                valores.put("msg", mensagem);
                valores.put("timestamp", timestamp);
                valores.put("id_location", 2);
                BancoDadosSingleton.getInstance().inserir("Logs", valores);

                Intent vicosa = new Intent(getBaseContext(), MostrarMapa.class);
                vicosa.putExtra("id", "2");
                Toast.makeText(getBaseContext(), aux, Toast.LENGTH_SHORT).show();
                startActivity(vicosa);
                break;
            case 2:
                mensagem = "DPI";
                timestamp = "";

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    timestamp = Instant.now().toString();
                }
                valores = new ContentValues();
                valores.put("msg", mensagem);
                valores.put("timestamp", timestamp);
                valores.put("id_location", 3);
                BancoDadosSingleton.getInstance().inserir("Logs", valores);

                Intent dpi = new Intent(getBaseContext(), MostrarMapa.class);
                dpi.putExtra("id", "3");
                Toast.makeText(getBaseContext(), aux, Toast.LENGTH_SHORT).show();
                startActivity(dpi);
                break;
            case 3:
                Intent it = new Intent(getBaseContext(), Report.class);
                startActivity(it);
                break;
            case 4:
                BancoDadosSingleton.getInstance().fechar();
                finish();
                break;
        }
    }
}