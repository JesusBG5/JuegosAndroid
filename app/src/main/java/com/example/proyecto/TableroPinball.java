package com.example.proyecto;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class TableroPinball extends View {

    Paint pintar = new Paint();
    Context contexto;
    int barraX=100;
    float posicionX;
    float circuloX = 10, circuloY = 10;
    boolean dirCirculoX = true, dirCirculoY = true;
    int puntuacion = 0;
    public TableroPinball(Context context) {
        super(context);
        contexto = context;
        circuloX = (float) Math.random()*1000;
    }

    @Override
    public void onDraw(Canvas canvas){
        try{
            Rect barra = new Rect(barraX,canvas.getHeight()-70,
                    barraX+200,canvas.getHeight());
            Rect pelota = new Rect((int)circuloX,(int)circuloY,
                    (int)circuloX+80,(int)circuloY+80);

            pintar.setColor(Color.BLUE);

            pintar.setTextSize(80);
            canvas.drawText("Puntuación: " + puntuacion,20,50,pintar);
            canvas.drawCircle(circuloX,circuloY,80,pintar);
            if(circuloX>canvas.getWidth()-80){
                dirCirculoX = false;
            }
            if(circuloX<0){
                dirCirculoX = true;
            }
            //La barra choca con la pelota
            if(barra.intersect(pelota)){
                dirCirculoY = false;
                puntuacion+=10;
            }else {
                if (circuloY > canvas.getHeight() - 80) {
                    dirCirculoY = false;
                    puntuacion -= 30;
                }
                if (circuloY < 0) {
                    dirCirculoY = true;
                }
            }

            if(dirCirculoX==true){
                circuloX+=10;
            }else{
                circuloX-=10;
            }

            if(dirCirculoY==true){
                circuloY+=10;
            }else{
                circuloY-=10;
            }

            if(posicionX>canvas.getWidth()/2 && barraX<canvas.getWidth()-200){
                barraX=barraX + 10;
            }else{
                if(barraX>0) {
                    barraX = barraX - 10;
                }
            }
            canvas.drawRect(barraX,canvas.getHeight()-70,
                    barraX+200,canvas.getHeight(),pintar);
            Thread.sleep(10);
            invalidate();
        }catch(Exception e){
            Toast.makeText(contexto,e.toString(),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        posicionX = e.getX();
        return true;
    }

}
