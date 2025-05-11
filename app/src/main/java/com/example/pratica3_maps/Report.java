package com.example.pratica3_maps;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class Report extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("REPORT", "Tentando acessar o banco de dados");
        Log.i("REPORT", "Entrei no onCreate");
        ArrayList<String> logsList = new ArrayList<>();
        Cursor c = BancoDadosSingleton.getInstance().buscar("Logs", new String[] {"id", "msg", "timestamp", "id_location"}, null, null);

        if (c != null && c.moveToFirst()) {
            do {
                int id = c.getInt(c.getColumnIndexOrThrow("id"));
                String msg = c.getString(c.getColumnIndexOrThrow("msg"));
                String timestamp = c.getString(c.getColumnIndexOrThrow("timestamp"));
                int idLocation = c.getInt(c.getColumnIndexOrThrow("id_location"));

                logsList.add(msg + " - " + timestamp);
            } while (c.moveToNext());
        } else {
            logsList.add("Nenhum log encontrado.");
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, logsList);
        setListAdapter(arrayAdapter);
    }
}
