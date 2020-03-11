package com.example.mnemos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.util.Arrays;
import java.util.stream.IntStream;

public class MainActivity extends AppCompatActivity {
    Game game;
    int fichaPrimeraid, fichaPrimeraimg;
    MediaPlayer mediaPlayer;


    private final int[] idsFichas = {
            R.id.ficha1,R.id.ficha2,R.id.ficha3,R.id.ficha4,R.id.ficha5,R.id.ficha6,
            R.id.ficha7,R.id.ficha8,R.id.ficha9,R.id.ficha10,R.id.ficha11,R.id.ficha12,
            R.id.ficha13,R.id.ficha14,R.id.ficha15,R.id.ficha16,R.id.ficha17,R.id.ficha18,
            R.id.ficha19,R.id.ficha20,R.id.ficha21,R.id.ficha22,R.id.ficha23,R.id.ficha24,
            R.id.ficha25,R.id.ficha26,R.id.ficha27,R.id.ficha28,R.id.ficha29,R.id.ficha30,
            R.id.ficha31,R.id.ficha32
    };

    private int[] idsFichasImagenes = {
            R.drawable.ficha1, R.drawable.ficha2, R.drawable.ficha3, R.drawable.ficha4, R.drawable.ficha5,
            R.drawable.ficha6, R.drawable.ficha7, R.drawable.ficha8, R.drawable.ficha9, R.drawable.ficha10,
            R.drawable.ficha11, R.drawable.ficha12, R.drawable.ficha13, R.drawable.ficha14, R.drawable.ficha15,
            R.drawable.ficha16
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        game = new Game(idsFichasImagenes);
    }

    public void pulsado(View v){
        int id = v.getId();
        ImageButton imagebutton = (ImageButton) findViewById(id);
        imagebutton.setBackgroundResource(R.drawable.fondo);
        int indice = 0;
        for(int i = 0; i<idsFichas.length;i++){
            if(idsFichas[i]==id){
                indice = i;
            }
        }
        int[] arrayRevelar = game.revelarCarta(indice);

        if(arrayRevelar[0] == 0){
            imagebutton.setBackgroundResource(arrayRevelar[1]);
            imagebutton.setBackgroundResource(R.drawable.fondo);
            ImageButton imagebutton2 = (ImageButton) findViewById(fichaPrimeraid);
            imagebutton2.setBackgroundResource(R.drawable.fondo);
            mediaPlayer = MediaPlayer.create(this,R.raw.error);
            mediaPlayer.start();
        }
        else if(arrayRevelar[0]==1){
            imagebutton.setBackgroundResource(arrayRevelar[1]);
            fichaPrimeraid = id;
            fichaPrimeraimg =arrayRevelar[1];
        }
        else if(arrayRevelar[0]==2){
            imagebutton.setBackgroundResource(arrayRevelar[1]);
            mediaPlayer = MediaPlayer.create(this,R.raw.acierto);
            mediaPlayer.start();
            ImageButton imagebutton1 = (ImageButton) findViewById(fichaPrimeraid);
            imagebutton.setBackgroundResource(0);
            imagebutton1.setBackgroundResource(0);
            imagebutton.setEnabled(false);
            imagebutton1.setEnabled(false);
        }
    }
}
