package com.stefanini.escola.arquitetos.dojo.objectpool;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

	private static Logger logger = Logger.getAnonymousLogger();
	
	public static void main(String[] args) {
        try {
            ReaderPool pool = ReaderPool.getInstance();

            Reader readerX = pool.aquireReader();
            readerX.lerArquivo();

            Reader readerY = pool.aquireReader();
            System.out.println(readerY.toString());

            pool.releaseReader(readerY);
            pool.releaseReader(readerX);

            Reader readerZ = pool.aquireReader();
            readerZ.lerArquivo();

            readerX = pool.aquireReader();
            readerX.lerArquivo();

            pool.releaseReader(readerX);

            pool.listBlocked();
            pool.listUnLocked();

            pool.releaseReader(readerZ);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "ERRO: ", e);
        }

	}

}
