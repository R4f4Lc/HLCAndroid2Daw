package com.example.solitariodefichas;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Jugadas extends Activity {
    public void onCreate(Bundle saveInstaceState){
        super.onCreate(saveInstaceState);
        setContentView(R.layout.jugadas);
        ListView ListView = (ListView) findViewById(R.id.listJugadas);

        String[] items = new String[] {
                "7 al 9",
                "2 al 8",
                "1 al 7",
                "9 al 3",
                "7 al 9",
                "12 al 6",
                "3 al 9",
                "10 al 12",
                "12 al 6"
        };

        final List<String> items_list = new ArrayList<String>(Arrays.asList(items));

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, items_list);

        ListView.setAdapter(arrayAdapter);
    }
}