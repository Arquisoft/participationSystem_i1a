package asw.model.types;

import asw.io.FileManagement;

import java.util.Set;

public class MinSupport {

	private static MinSupport instance = null;
	String filename = "src/main/resources/minSupport/support.txt";
	private int support;

	private MinSupport(){
		initialize();
	}
	
	private void initialize() {
		FileManagement fm = new FileManagement();
		support = fm.getSupport(filename);
	}
	
	public int getSupport(){
		return support;
	}

	public static MinSupport getInstance(){
		if(instance == null)
			instance = new MinSupport();
		return instance;
	}
	
	
	
	
}
