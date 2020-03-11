package com.example.solitariodefichas;

import android.view.View;
import android.widget.Button;

public class Game {
    //Dimensiones del tablero
    static final int NFILAS = 4;
    static final int NCOLUMNAS = 3;

    //Valores del tablero
    static final int VACIO = 0;
    static final int FICHA = 1;

    public char estado;
    public Coordenada coorMedio;
    public int jugada;

    public int tablero[][];

    public Game(){
        tablero = new int[NFILAS][NCOLUMNAS];
        for(int i = 0; i< NFILAS; i++)
            for(int j=0;j<NCOLUMNAS;j++)
                if(i==0 && j==2 || i==2 && j==2)
                    tablero[i][j] = VACIO;
                else
                    tablero[i][j] = FICHA;

        this.estado = 'J';
        this.coorMedio = new Coordenada(0,0);
        this.jugada = 0;
    }

    public String fila(Coordenada coordenada){
        String cadena = "";
        int f = coordenada.getFila();
        int c;
        for(c=0; c < NCOLUMNAS; c++){
            cadena+= Integer.toString(tablero[f][c]);
        }
        return cadena;
    }

    public String columna(Coordenada coordenada){
        String cadena = "";
        int c = coordenada.getColumna();
        int f;
        for(f=0;f<NFILAS;f++){
            cadena+= Integer.toString(tablero[f][c]);
        }
        return cadena;
    }

    public boolean actualizarTablero(Coordenada coordenadaOriginal, Coordenada coordenadaMovimiento){
        boolean lactualizado = false;

        int filaOriginal = coordenadaOriginal.getFila();
        int columnaOriginal = coordenadaOriginal.getColumna();

        int filaMovimiento = coordenadaMovimiento.getFila();
        int columnaMovimiento = coordenadaMovimiento.getColumna();


        if(filaMovimiento==filaOriginal){
            if(columnaMovimiento>columnaOriginal){
                if(columnaMovimiento-1==columnaOriginal){
                    lactualizado = false;
                }
                else if(tablero[filaMovimiento][columnaMovimiento]==VACIO && tablero[filaMovimiento][columnaMovimiento-1]==FICHA){
                    tablero[filaMovimiento][columnaMovimiento]=FICHA;
                    tablero[filaMovimiento][columnaMovimiento-1]=VACIO;
                    tablero[filaOriginal][columnaOriginal]=VACIO;
                    this.coorMedio = new Coordenada(filaMovimiento,columnaMovimiento-1);
                    lactualizado = true;
                }
            }
            else{
                if(columnaMovimiento+1==columnaOriginal){
                    lactualizado = false;
                }
                else if(tablero[filaMovimiento][columnaMovimiento]==VACIO && tablero[filaMovimiento][columnaMovimiento+1]==FICHA){
                    tablero[filaMovimiento][columnaMovimiento]=FICHA;
                    tablero[filaMovimiento][columnaMovimiento+1]=VACIO;
                    tablero[filaOriginal][columnaOriginal]=VACIO;
                    this.coorMedio = new Coordenada(filaMovimiento,columnaMovimiento+1);
                    lactualizado = true;
                }
            }
        }

        else {
            if(filaMovimiento>filaOriginal){
                if(filaMovimiento-1==filaOriginal){
                    lactualizado = false;
                }

                else if(tablero[filaMovimiento][columnaOriginal] == VACIO && tablero[filaMovimiento-1][columnaMovimiento] == FICHA){
                    tablero[filaMovimiento][columnaMovimiento]=FICHA;
                    tablero[filaMovimiento-1][columnaMovimiento]=VACIO;
                    tablero[filaOriginal][columnaOriginal]=VACIO;
                    this.coorMedio = new Coordenada(filaMovimiento-1,columnaMovimiento);
                    lactualizado = true;
                }
            }
            else {
                if(filaMovimiento+1==filaOriginal){
                    lactualizado = false;
                }

                else if(tablero[filaMovimiento][columnaMovimiento]==VACIO && tablero[filaMovimiento+1][columnaMovimiento]==FICHA) {
                    tablero[filaMovimiento][columnaMovimiento]=FICHA;
                    tablero[filaMovimiento+1][columnaMovimiento]=VACIO;
                    tablero[filaOriginal][columnaOriginal]=VACIO;
                    this.coorMedio = new Coordenada(filaMovimiento+1,columnaMovimiento);
                    lactualizado = true;
                }
            }
        }

        if(lactualizado){
            this.jugada++;
        }

        if(comprobarJugadaGanadora()){
            this.estado='G';
        }

        return lactualizado;
    }

    public boolean comprobarJugadaGanadora(){
        int numeroFichas=0;
        for(int i = 0; i< NFILAS; i++)
            for(int j=0;j<NCOLUMNAS;j++)
                if(tablero[i][j]==FICHA)
                    numeroFichas++;

        if(numeroFichas==1){
            return true;
        }
        return false;
    }


    public int jugada(Coordenada coordenada){
        int resultado = -1;
        if(this.estado=='G') return -1;
        String patron1 = Integer.toString(FICHA) + Integer.toString(VACIO);
        String patron2 = Integer.toString(FICHA) + Integer.toString(VACIO);


        return resultado;
    }
}