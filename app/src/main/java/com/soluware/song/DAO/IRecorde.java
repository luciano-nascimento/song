package com.soluware.song.DAO;

/**
 * Created by Luciano on 06/06/2016.
 */
public interface IRecorde {
    long gravar(Recorde rec);
    void alterar(Recorde rec);
    Recorde recuperar();
}
