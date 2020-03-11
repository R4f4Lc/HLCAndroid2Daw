package com.example.solitariodefichas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Game game;
    int fichaPrimeraid;
    Coordenada fichaPrimeraCoor;
    boolean primerapulsada = true;

    private final int ids[][] = {
            {R.id.f0c0Button, R.id.f0c1Button, R.id.f0c2Button},
            {R.id.f1c0Button, R.id.f1c1Button, R.id.f1c2Button},
            {R.id.f2c0Button, R.id.f2c1Button, R.id.f2c2Button},
            {R.id.f3c0Button, R.id.f3c1Button, R.id.f3c2Button},
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        game = new Game();
    }

    public void pulsado(View v){
        int fila, columna, id = v.getId();
        ImageButton imageButton = (ImageButton) v;
        Coordenada coordenada = coorJuego(id);

        if(primerapulsada){
            primerapulsada = false;
            fichaPrimeraid = id;
            fichaPrimeraCoor = coordenada;
        }
        else {
            primerapulsada = true;
            if(game.actualizarTablero(fichaPrimeraCoor,coordenada)){
                dibujaFicha(fichaPrimeraCoor,coordenada);
            }
        }

        if(game.estado == 'G'){
            Button buttonJugadas = (Button) findViewById(R.id.buttonMostrar);
            buttonJugadas.setEnabled(true);
        }

    }

    public void njugadas(View v){
        Toast.makeText(getApplicationContext(),
                "Se han realizado un total de " + Integer.toString(game.jugada) + " jugadas", Toast.LENGTH_SHORT).show();
    }

    public void mostrarJugadas(View v){
        Intent intent = new Intent(this, Jugadas.class);

        startActivity(intent);
    }

    public void reiniciar(View v){
        Intent i = new Intent(MainActivity.this, MainActivity.class);
        finish();
        overridePendingTransition(0, 0);
        startActivity(i);
        overridePendingTransition(0, 0);
    }

    private void dibujaFicha(Coordenada original, Coordenada destino){
        ImageButton imageButtonOriginal = (ImageButton) findViewById(ids[original.getFila()][original.getColumna()]);
        ImageButton imageButtonMedio = (ImageButton) findViewById(ids[game.coorMedio.getFila()][game.coorMedio.getColumna()]);
        ImageButton imageButtonDestino = (ImageButton) findViewById(ids[destino.getFila()][destino.getColumna()]);
        imageButtonOriginal.setImageResource(R.drawable.button);
        imageButtonMedio.setImageResource(R.drawable.button);
        imageButtonDestino.setImageResource(R.drawable.button_p);
    }

    private Coordenada coorJuego(int id){
        int fila = 0;
        int columna = 0;

        for(int i = 0; i< Game.NFILAS; i++)
            for(int j = 0; j < Game.NCOLUMNAS; j++)
                if (ids[i][j] == id){
                    fila = i;
                    columna=j;
                }

        Coordenada coordenada = new Coordenada(fila, columna);
        return coordenada;
    }

}
