package com.stefanini.escola.arquitetos.dojo.objectpool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Reader {

	private Logger logger = Logger.getAnonymousLogger();
    private String identificador;
    private BufferedReader leitor;

    public Reader(){
    }

    public Reader(String identificador, String nomeArquivo){
        this.identificador = identificador;
        try {
        	nomeArquivo = System.getProperty("user.dir").concat("/src/main/resources/").concat(nomeArquivo);
            this.leitor = new BufferedReader(new FileReader(new File(nomeArquivo)));
        } catch (Exception e) {
            logger.log(Level.SEVERE,"ERRO: ",e);
        }
    }

    public BufferedReader getLeitor(){
        return this.leitor;
    }

    public String getIdentificador(){
        return this.identificador;
    }
    
    public void lerArquivo() throws IOException{
        logger.info("Iniciando leitura de arquivo com o " + this.identificador);
        while(leitor.ready()){
        	logger.info(leitor.readLine());
        }
    }

    @Override
    public String toString(){
        return "READER: " + this.identificador;
    }
}
