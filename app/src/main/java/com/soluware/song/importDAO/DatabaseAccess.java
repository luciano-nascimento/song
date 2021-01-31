package com.soluware.song.importDAO;

/**
 * Created by lucia on 30/05/2016.
 */
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.soluware.song.Questoes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    //total de peruntas no banco
    private final int numberOfQuestions = 17;
    private Random random = new Random();

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    /**
     * Read all quotes from the database.
     *
     * @return a List of quotes
     */
    public List<String> getQuest(){

        int numberQuest = randomQuest();
        List<String> list = new ArrayList<>();

        //-1 é retornando quando já foram usadas todas as perguntas
        if(numberQuest == -1){
            list.add("-1");
            return list;
        }else{
            //caso a pergunta ainda não tenha sido usada proseguir normalmente
            Cursor cursor = database.rawQuery("SELECT * FROM perguntas WHERE _id=" + numberQuest, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                list.add(cursor.getString(0));
                list.add(cursor.getString(1));
                list.add(cursor.getString(2));
                list.add(cursor.getString(3));
                list.add(cursor.getString(4));
                list.add(cursor.getString(5));
                list.add(cursor.getString(6));
                cursor.moveToNext();
            }
            cursor.close();
            return list;
        }



    }

    protected int randomQuest(){

        // faz um random e pega um num para equivaler a id de uma pergunta
        int randomInt = random.nextInt((numberOfQuestions - 1)+ 1) + 1;
        //checa se a lista de numeros sorteados está vazio
        boolean listStatus = Questoes.questSorteadas.isEmpty();
        //checa se o num sorteado está na lista
        boolean checkNum = Questoes.questSorteadas.contains(randomInt);


        if(!listStatus){
            while(checkNum){
                randomInt = random.nextInt((numberOfQuestions - 1)+ 1) + 1;
                checkNum = Questoes.questSorteadas.contains(randomInt);
                if(Questoes.questSorteadas.size() == numberOfQuestions){
                    return -1;
                }
            }
        }

        Questoes.questSorteadas.add(randomInt);
        return randomInt;

    }
}
