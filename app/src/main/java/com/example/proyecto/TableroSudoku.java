package com.example.proyecto;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class TableroSudoku extends View {
    Context contexto;
    Paint pintar = new Paint();
    Rect [][] tablero = new Rect[9][9];
    Cuadro [][] tablero2 = new Cuadro[9][9];

    public TableroSudoku(Context context) {
        super(context);
        contexto = context;
        for (int i=0;i<9;i++){
            for (int j=0;j<9;j++){
                tablero2 [i][j] = new Cuadro();
            }
        }
        int contador = 0;
        while(contador<36){
            int x = (int) (Math.random()*9);
            int y = (int) (Math.random()*9);
            int num = (int) (Math.random()*9);
            if(tablero2[x][y].numero==0 && revisarColumna(x,num)==true && revisarFila(y,num)==true){
                tablero2[x][y].numero = num;
                contador++;
            }
        }
    }
    public boolean revisarColumna(int x,int num){
        boolean bandera = true;
        for (int i=0;i<9;i++){
            if(tablero2[x][i].numero==num){ bandera=false; }
        }
        return bandera;
    }

    public boolean revisarFila(int y,int num){
        boolean bandera = true;
        for (int i=0;i<9;i++){
            if(tablero2[i][y].numero==num){ bandera=false; }
        }
        return bandera;
    }

    @Override
    public void onDraw(Canvas canvas){
        try{
            int ancho = canvas.getWidth()/9;
            for (int i=0;i<9;i++){
                for (int j=0;j<9;j++){
                    if(tablero2[i][j].seleccionado==true){
                        pintar.setColor(Color.MAGENTA);
                    }else{
                        pintar.setColor(Color.CYAN);
                    }
                    canvas.drawRect(i*ancho,j*ancho,
                            ((i+1)*ancho)-8,((j+1)*ancho)-8,pintar);
                    tablero[i][j] = new Rect(i*ancho,j*ancho,
                            ((i+1)*ancho)-8,((j+1)*ancho)-8);



                    pintar.setColor(Color.BLACK);
                    pintar.setTextSize(70);
                    if(tablero2[i][j].numero!=0) {
                        canvas.drawText(tablero2[i][j].numero + "",
                                (i * ancho) + ancho / 2, (j * ancho) + ancho / 2, pintar);
                    }
                }
            }

            pintar.setStrokeWidth(15f);
            canvas.drawLine((int)ancho*3,0,
                    (int)ancho*3,(int)ancho*9,pintar);
            canvas.drawLine((int)ancho*6,0,
                    (int)ancho*6,(int)ancho*9,pintar);
            canvas.drawLine(0,(int)ancho*3,
                    (int)ancho*9,(int)ancho*3,pintar);
            canvas.drawLine(0,(int)ancho*6,
                    (int)ancho*9,(int)ancho*6,pintar);


            Thread.sleep(100);
            invalidate();
        }catch(Exception e){
            Toast.makeText(contexto,e.toString(),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        float x = e.getX();
        float y = e.getY();
        for (int i=0;i<9;i++){
            for (int j=0;j<9;j++) {
                if(tablero[i][j].contains((int)x,(int)y)) {
                    tablero2[i][j].seleccionado = true;
                }else{
                    tablero2[i][j].seleccionado = false;
                }
            }
        }
        return true;
    }
}
