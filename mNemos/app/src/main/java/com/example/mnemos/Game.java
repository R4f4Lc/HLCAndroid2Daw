package com.example.mnemos;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    //Elementos del tablero
    static final int NELEMENTOS = 32;

    static final int PUNTUACION_GANADORA = 32;
    public int tablero[];
    List<Integer> fichas_acertadas = new ArrayList<>();
    public boolean pulsado = false;
    public int almacenada;
    public int indiceAlmacenado;



    public Game(int[] fichasImagenes){
        tablero = new int[NELEMENTOS];
        for(int i=0;i<NELEMENTOS;i++){
            List fichas_colocadas = new ArrayList<>();
            boolean colocada = true;
            do {
                int number_random = new Random().nextInt(fichasImagenes.length);
                if(!fichas_colocadas.contains(fichasImagenes[number_random])){
                    tablero[i] = fichasImagenes[number_random];
                    fichas_colocadas.add(fichasImagenes[number_random]);
                    colocada = false;
                }
            }while(colocada);
        }
    }

    public int[] revelarCarta(int indice){
        int ficha = tablero[indice];
        if(pulsado && indice != indiceAlmacenado){
            if(!fichas_acertadas.contains(ficha)){
                pulsado = false;
                if(ficha == almacenada){
                    fichas_acertadas.add(ficha);

                    return new int[] {2, ficha};
                }
                else {
                    indiceAlmacenado = -1;
                    return new int[] {0, ficha};
                }
            }
            else{
                return new int[] {3, ficha};
            }
        }
        else {
            if(!fichas_acertadas.contains(ficha)) {
                pulsado = true;
                almacenada = ficha;
                indiceAlmacenado = indice;
                return  new int[] {1, ficha};
            }
            else {
                return new int[]{3, ficha};
            }
        }
    }
}
