package com.stefanini.escola.arquitetos.dojo.objectpool;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReaderPool {
	private Logger logger = Logger.getAnonymousLogger();
	private static ReaderPool instance = null;
	private Set<Reader> locked = new HashSet<>();
	private Set<Reader> unlocked = new HashSet<>();
	private int maxReaders = 5;
	
	private ReaderPool() {}
	
	public static ReaderPool getInstance() {
		if(instance == null) {
			instance = new ReaderPool();
		}
		return instance;
	}
	
	public Reader aquireReader() {
		Reader reader = null;
		if(unlocked.size() == 0) {
			for(int i = 1; i <= maxReaders; i++) {
				unlocked.add(new Reader("READER_"+i,""));
			}
		}
		if(unlocked.size() <= maxReaders) {
			reader = unlocked.iterator().next();
			unlocked.remove(reader);
			locked.add(reader);
			logger.info("O reader "+reader.getIdentificador()+" foi empresrado!");
		}else {
			logger.log(Level.SEVERE,
					"Erro na política de atribuição: " + (unlocked.size() >= maxReaders ? "Pool cheio" : "Pool vazio"));
		}
		return reader;
	}
	
	public void releaseReader(Reader reader) {
		locked.remove(reader);
		unlocked.add(reader);
		logger.info("O reader "+reader.getIdentificador()+" foi devolvido!");
	}
	
	public Reader createReader() {
		Reader reader = new Reader("READER_"+unlocked.size()+1, "");
		locked.add(reader);
		return reader;
	}
	
	public void listBlocked() {
		logger.info("READERS BLOQUEADOS: "+ locked);
	}
	
	public void listUnLocked() {
		logger.info("READERS DESBLOQUEADOS: "+ unlocked);
	}
}
