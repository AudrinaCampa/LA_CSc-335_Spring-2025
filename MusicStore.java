package model;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MusicStore {
	
	// Instance Variables
	private String file;
	private ArrayList<String> list;
	
	// Constructor
	public MusicStore(String filename) {
		this.file = filename;
		this.list = new ArrayList<String>();
	}

	public void readFile() {
		try {
			FileReader fn = new FileReader(this.file); // calls the filename
			BufferedReader br = new BufferedReader(fn);
			
			if(br.ready()) {
				String line = br.readLine();
				System.out.println(line);
			}
			
		}catch(IOException e) {
			System.out.println("e");
		}
	}
}
