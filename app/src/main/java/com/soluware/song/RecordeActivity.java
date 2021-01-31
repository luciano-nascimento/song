package com.soluware.song;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.soluware.song.DAO.Recorde;
import com.soluware.song.DAO.RecordeDAO;

/**
 * Created by Luciano on 02/06/2016.
 */
public class RecordeActivity extends Activity{

    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorde);

        //puxa pontuação do bd
        RecordeDAO rDao = RecordeDAO.getInstance(getApplicationContext());
        Recorde r = rDao.recuperar();
        int pontos;
        if(r != null){
            pontos = r.getPontos();
        }
        else{
            pontos = UserRec.getPointsRecord();
        }

        String pontosRecorde = "Recorde:" + "\n" + "\n       " + pontos;
        TextView textView = (TextView) findViewById(R.id.recorde_textview);

        textView.setText(pontosRecorde);
    }


}
