package model;

import java.util.ArrayList;

public class Artist {
	
	private ArrayList<String> artist;
	
	public Artist() {
		this.artist = new ArrayList<String>();
	}
	
	public void addArtist(String name) {
		this.artist.add(name);
	}

}
