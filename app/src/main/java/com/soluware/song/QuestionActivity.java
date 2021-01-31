package com.soluware.song;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.soluware.song.DAO.Recorde;
import com.soluware.song.DAO.RecordeDAO;
import com.soluware.song.importDAO.DatabaseAccess;

import java.util.List;
import java.util.Objects;

/**
 * Created by Luciano on 29/05/2016.
 */
public class QuestionActivity extends Activity {

    List<String> quest;
    Button opOne;
    Button opTwo;
    Button opThree;
    Button opFour;
    private Animation animation;
    final Handler handler = new Handler();

    protected void onCreate(Bundle savedInstanceState) {

        overridePendingTransition(R.anim.entrada_pergunta, R.anim.saida_pergunta);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);



        TextView questionBg = (TextView) findViewById(R.id.question_txt);
        this.opOne = (Button) findViewById(R.id.opOne_txt);
        this.opTwo = (Button) findViewById(R.id.opTwo_txt);
        this.opThree = (Button) findViewById(R.id.opThree_txt);
        this.opFour = (Button) findViewById(R.id.opFour_txt);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        this.quest = databaseAccess.getQuest();
        databaseAccess.close();

        animation = new AlphaAnimation(1, 0); // Altera alpha de visível a invisível
        animation.setDuration(800); // duração - meio segundo
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatCount(Animation.INFINITE); // Repetir infinitamente
        animation.setRepeatMode(Animation.REVERSE); //Inverte a animação no final para que o botão vá desaparecendo

        if(Objects.equals(quest.get(0), "-1")){
            //zera o recorde e mata a activity
            opIncorrect();
            //limpa lista de perguntas que já saíram
            Questoes.questSorteadas.clear();
            Intent intent = new Intent(this,FimQuestaoActivity.class);
            startActivity(intent);
        }else{
            questionBg.setText(quest.get(1));
            opOne.setText(quest.get(2));
            opTwo.setText(quest.get(3));
            opThree.setText(quest.get(4));
            opFour.setText(quest.get(5));
        }
    }

    public void onBackPressed()
    {
        //reseta lista de perguntas que já sairam
        if(!Questoes.questSorteadas.isEmpty()){
            Questoes.questSorteadas.clear();
        }
        UserRec.setPoints(0);
        super.onBackPressed();
    }

    public void checkAnswer(View v){

        String opClicked = null;
        String opRight = null;

        switch (v.getId()) {
            case R.id.opOne_txt:
                opClicked = "1";
                if(opClicked.equals(quest.get(6))){
                    opOne.setBackgroundResource(R.drawable.correct_answer);
                    opOne.startAnimation(animation);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            opCorrect();
                        }
                    }, 3000);
                }
                else{
                    opOne.setBackgroundResource(R.drawable.incorrect_answer);
                    opOne.startAnimation(animation);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            opIncorrect();
                        }
                    }, 3000);
                }
                break;
            case R.id.opTwo_txt:
                opClicked = "2";
                if(opClicked.equals(quest.get(6))){
                    opTwo.setBackgroundResource(R.drawable.correct_answer);
                    opTwo.startAnimation(animation);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            opCorrect();
                        }
                    }, 3000);
                }
                else{
                    opTwo.setBackgroundResource(R.drawable.incorrect_answer);
                    opTwo.startAnimation(animation);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            opIncorrect();
                        }
                    }, 3000);
                }
                break;
            case R.id.opThree_txt:
                opClicked = "3";
                if(opClicked.equals(quest.get(6))){
                    opThree.setBackgroundResource(R.drawable.correct_answer);
                    opThree.startAnimation(animation);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            opCorrect();
                        }
                    }, 3000);
                }
                else{
                    opThree.setBackgroundResource(R.drawable.incorrect_answer);
                    opThree.startAnimation(animation);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            opIncorrect();
                        }
                    }, 3000);
                }
                break;
            case R.id.opFour_txt:
                opClicked = "4";
                if(opClicked.equals(quest.get(6))){
                    opFour.setBackgroundResource(R.drawable.correct_answer);
                    opFour.startAnimation(animation);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            opCorrect();
                        }
                    }, 3000);
                }
                else{
                    opFour.setBackgroundResource(R.drawable.incorrect_answer);
                    opFour.startAnimation(animation);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            opIncorrect();
                        }
                    }, 3000);
                }
                break;
        }

    }

    public void opCorrect(){

        finish();
        Intent intent = new Intent(this,QuestionActivity.class);
        startActivity(intent);

        UserRec.setPoints(UserRec.getPoints() + 1);

        Context contexto = getApplicationContext();
        int texto = UserRec.getPoints();
        int duracao = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(contexto,"Pontos: " + Integer.toString(texto) ,duracao);
        toast.show();

    }

    public void opIncorrect(){
        if(!Questoes.questSorteadas.isEmpty()){
            Questoes.questSorteadas.clear();
        }

        //verifica se a pontuação deve ser gravada no bd ou não

        RecordeDAO rDao = RecordeDAO.getInstance(getApplicationContext());
        if(rDao.recuperar() != null){
            Recorde r = rDao.recuperar();
            if(r.getPontos() < UserRec.getPoints()){
                Recorde rAtual = new Recorde(1,UserRec.getPoints());
                rDao.alterar(rAtual);
            }
        }
        else{
            Recorde ra = new Recorde(1,UserRec.getPoints());
            long l = rDao.gravar(ra);
        }
        //RecordeDAO rDao = RecordeDAO.getInstance(getApplicationContext());
        //Recorde ra = new Recorde(1,1);
        //long l = rDao.gravar(ra);

        //reinicia a pontuação
        UserRec.setPoints(0);
        finish();

    }



}
