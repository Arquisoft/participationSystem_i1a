package asw.model.types;

import java.util.Set;

import asw.io.FileManagement;

public class NotAllowedWords {

	private static NotAllowedWords instance = null;
	String filename = "src/main/resources/words/Not-allowed-words.txt";
	Set<String> words;
	
	private NotAllowedWords(){
		initialize();
	}
	
	private void initialize() {
		FileManagement fm = new FileManagement();
		words = fm.fileProcesing(filename);		
	}
	
	public Set<String> getSet(){
		return words;
	}

	public static NotAllowedWords getInstance(){
		if(instance == null)
			instance = new NotAllowedWords();
		return instance;
	}
	
	
	
	
}
