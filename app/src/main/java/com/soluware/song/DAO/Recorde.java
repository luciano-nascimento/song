package com.soluware.song.DAO;

/**
 * Created by Luciano on 06/06/2016.
 */
public class Recorde {

    int id;
    int pontos;

    public Recorde(int id, int pontos){
        this.id = id;
        this.pontos = pontos;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
