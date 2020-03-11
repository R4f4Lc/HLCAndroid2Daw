package com.example.conecta4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Game game;
    private final int ids[][] = {
            {R.id.f0c0Button, R.id.f0c1Button, R.id.f0c2Button, R.id.f0c3Button, R.id.f0c4Button, R.id.f0c5Button, R.id.f0c6Button},
            {R.id.f1c0Button, R.id.f1c1Button, R.id.f1c2Button, R.id.f1c3Button, R.id.f1c4Button, R.id.f1c5Button, R.id.f1c6Button},
            {R.id.f2c0Button, R.id.f2c1Button, R.id.f2c2Button, R.id.f2c3Button, R.id.f2c4Button, R.id.f2c5Button, R.id.f2c6Button},
            {R.id.f3c0Button, R.id.f3c1Button, R.id.f3c2Button, R.id.f3c3Button, R.id.f3c4Button, R.id.f3c5Button, R.id.f3c6Button},
            {R.id.f4c0Button, R.id.f4c1Button, R.id.f4c2Button, R.id.f4c3Button, R.id.f4c4Button, R.id.f4c5Button, R.id.f4c6Button},
            {R.id.f5c0Button, R.id.f5c1Button, R.id.f5c2Button, R.id.f5c3Button, R.id.f5c4Button, R.id.f5c5Button, R.id.f5c6Button}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        game = new Game(1);
    }

    public void pulsado(View v){
        int fila, columna, id = v.getId();
        Coordenada coordenada = new Coordenada(0,0);
        ImageButton imageButton = (ImageButton) v;
        coordenada = coorJuego(id);

        if(!game.colCompleta(coordenada.getColumna())) {
            game.actualizarTablero(coordenada);
            dibujaFicha(coordenada, game.getTurno());
            if(game.jugadaGanadora(coordenada) != -1){
                Toast.makeText(getApplicationContext(),
                        getString(R.string.ganadora) + " " + game.getTurno(), Toast.LENGTH_SHORT).show();
            }
            game.cambiarTurno();
        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Columna completa", Toast.LENGTH_SHORT);

            toast.show();

        }
    }

    private void dibujaFicha(Coordenada coordenada, int jugador){
        int columna = coordenada.getColumna();
        int fila = coordenada.getFila();


        int id = ids[fila][columna];

        ImageButton imageButton = (ImageButton) findViewById(id);

        if(jugador == game.JUGADOR){
            imageButton.setImageResource(R.drawable.c4_button_player);
            //Resources resources = getResources();
            //imageButton.setImageDrawable(resources.getDrawable(R.drawable.c4_button_p1));
            }
        else {
            imageButton.setImageResource(R.drawable.c4_button_p2);
        }
    }


    private Coordenada coorJuego(int id){
        //int fila = 0;
        int columna = 0;

        for(int i = 0; i< Game.NFILAS; i++)
            for(int j = 0; j < Game.NCOLUMNAS; j++)
                if (ids[i][j] == id){
                    //fila = i;
                    columna=j;

                }

        Coordenada coordenada = new Coordenada(game.seleccionarFila(columna), columna);
                return coordenada;
    }

    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case R.id.menuAbout:
                startActivity(new Intent(this, About.class));
                return true;
            case R.id.sendMessage:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT,"CONECTA 4");
                intent.putExtra(Intent.EXTRA_TEXT,"Nueva aplicación Android");
                startActivity(intent);
                return true;
            case R.id.preferences:
                startActivity(new Intent(this, Preferences.class));
                return true;
        }
        return true;
    }
}