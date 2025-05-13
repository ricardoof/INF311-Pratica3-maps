package com.example.pratica3_maps;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class Report extends ListActivity {

    ArrayList<String> logsList = new ArrayList<>();
    ArrayList<Integer> logIds = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        logsList.add(0, "Meus Logs");
        Cursor c = BancoDadosSingleton.getInstance().buscar("Logs", new String[] {"id", "msg", "timestamp", "id_location"}, null, null);
        while (c.moveToNext()) {
            int id = c.getInt(c.getColumnIndexOrThrow("id"));
            String msg = c.getString(c.getColumnIndexOrThrow("msg"));
            String timestamp = c.getString(c.getColumnIndexOrThrow("timestamp"));
            int idLocation = c.getInt(c.getColumnIndexOrThrow("id_location"));
            logsList.add(msg + " - " + timestamp);
            logIds.add(id);
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, logsList);
        setListAdapter(arrayAdapter);
    }

    @Override
    protected void onListItemClick(android.widget.ListView l, android.view.View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        if (position == 0) return;
        int idLog = logIds.get(position - 1);

        Cursor c = BancoDadosSingleton.getInstance().buscar("Logs lg, Location lc", new String[]{"lg.id_location idlog", "lc.id idloc", "lc.latitude lat", "lc.longitude lon"}, "lg.id = " + idLog + " AND lg.id_location = lc.id", null);

        if (c.moveToFirst()) {
            int idlog = c.getInt(c.getColumnIndexOrThrow("idlog"));
            int idloc = c.getInt(c.getColumnIndexOrThrow("idloc"));
            double latitude = c.getDouble(c.getColumnIndexOrThrow("lat"));
            double longitude = c.getDouble(c.getColumnIndexOrThrow("lon"));

            Toast.makeText(this, "Latitude: " + latitude + "\nLongitude: " + longitude, Toast.LENGTH_LONG).show();
        }
        c.close();
    }
}
