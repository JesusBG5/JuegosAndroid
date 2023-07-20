package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void opcionPinball(View view){
        Intent obj = new Intent(this,Pinball.class  );
        startActivity(obj);
    }

    public void opcionSudoku(View view){
        Intent obj = new Intent(this,Sudoku.class  );
        startActivity(obj);
    }
}