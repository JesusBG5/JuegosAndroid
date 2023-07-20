package com.example.proyecto;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import android.widget.Toast;

public class TableroSudoku extends View {
    Context contexto;
    Paint pintar = new Paint();
    public TableroSudoku(Context context) {
        super(context);
        contexto = context;
    }

    @Override
    public void onDraw(Canvas canvas){
        try{
            int ancho = canvas.getWidth()/9;
            pintar.setColor(Color.CYAN);
            for (int i=0;i<9;i++){
                for (int j=0;j<9;j++){
                    canvas.drawRect(i*ancho,j*ancho,
                            ((i+1)*ancho)-8,((j+1)*ancho)-8,pintar);
                }
            }
            Thread.sleep(100);
            invalidate();
        }catch(Exception e){
            Toast.makeText(contexto,e.toString(),Toast.LENGTH_LONG).show();
        }
    }
}
