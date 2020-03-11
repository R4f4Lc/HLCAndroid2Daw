package com.example.conecta4;

public class Game {
    //Dimensiones del tablero
    static final int NFILAS = 6;
    static final int NCOLUMNAS = 7;

    //Valores del tablero
    static final int VACIO = 0;
    static final int MAQUINA = 1;
    static final int JUGADOR = 2;


    static final String MAQGANADOR = "1111";
    static final String JUGGANADOR = "2222";

    private int turno = JUGADOR;
    private char estado; //J Jugado, G Ganado, T Empate

    public int tablero[][];

    public Game(int jugador) {
        tablero = new int[NFILAS][NCOLUMNAS];
        turno = jugador;
        for(int i = 0; i< NFILAS; i++)
            for(int j=0;j<NCOLUMNAS;j++)
                tablero[i][j] = VACIO;

        this.estado = 'J';
        this.turno = jugador;

    }

    public void setTurno(int jugador){
        this.turno = jugador;
    }

    public int getTurno(){
        return this.turno;
    }

    public boolean actualizarTablero(Coordenada coordenada){
        boolean lactualizado = false;
        int fila = coordenada.getFila();
        int columna = coordenada.getColumna();
        if(tablero[fila][columna]==VACIO) {
            tablero[fila][columna] = getTurno();
            lactualizado = true;
        }
        return lactualizado;
    }

    public void cambiarTurno(){
        this.setTurno(this.getTurno() == MAQUINA ? JUGADOR : MAQUINA);
        return;
    }

    public int seleccionarFila(int columna){
        int i = NFILAS-1;
        int fil = -1;
        boolean lsel = false;
        while(i>=0 && !lsel) {
            if(tablero[i][columna] == VACIO) {
                fil = i;
                lsel = true;
            }
            i--;
        }
        return fil;
    }

    public boolean colCompleta(int columna) {
        boolean lcompleta = false;

        if(tablero[0][columna] != VACIO) lcompleta = true;

        return lcompleta;
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

    public String diagonal1(Coordenada coordenada){
        String cadena = "";
        int c = coordenada.getColumna();
        int f = coordenada.getFila();
        int i = f - Math.min(f,c);
        int j = c - Math.min(f,c);
        while (i<NFILAS && j < NCOLUMNAS){
            cadena+= Integer.toString(tablero[i][j]);
            i++;
            j++;
        }
        return cadena;
    }

    public String diagonal2(Coordenada coordenada){
        String cadena = "";
        int c = 0;
        int f = 0;
        for(f=0;f<NFILAS;f++){
            for (c=0;c<NCOLUMNAS;c++){
                if(f+c == coordenada.getFila() + coordenada.getColumna()){
                    cadena+=Integer.toString(tablero[f][c]);
                }
            }
        }
        return cadena;
    }

    public int jugadaGanadora(Coordenada coordenada){
        int resultado = -1;
        if(this.estado == 'G') return -1;
        String patron = this.getTurno() == MAQUINA ? MAQGANADOR: JUGGANADOR;

        if (fila(coordenada).contains(patron)){
            this.estado = 'G';
            resultado=0;
        }

        if (columna(coordenada).contains(patron)){
            this.estado = 'G';
            resultado=1;
        }

        if(diagonal1(coordenada).contains(patron)){
            this.estado = 'G';
            resultado = 2;
        }
        if(diagonal2(coordenada).contains(patron)){
            this.estado = 'G';
            resultado = 3;
        }

        return resultado;
    }


}