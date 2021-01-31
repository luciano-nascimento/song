package com.soluware.song;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.soluware.song.DAO.Recorde;
import com.soluware.song.DAO.RecordeDAO;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //altera a cor da action bar
        android.support.v7.app.ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#33b5e5")));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }



    public void options_menu(View view){

        Intent intent = new Intent(this,QuestionActivity.class);
        startActivity(intent);

    }

    public void mostrarRecorde(View view){

        Intent intent = new Intent(this,RecordeActivity.class);
        startActivity(intent);
    }

    public void mostrarInstrucoes(View view){
        Intent intent = new Intent(this,InstrucoesActivity.class);
        startActivity(intent);
    }

    public void mostrarSobre(View view){
        Intent intent = new Intent(this,SobreActivity.class);
        startActivity(intent);
    }
}
