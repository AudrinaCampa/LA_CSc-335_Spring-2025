package model;

import java.util.ArrayList;
import java.util.List;

public class PlayList {
	private List<Song> playlist;
	private final String name;
	
	public PlayList (String name) {
		this.name = name;
		this.playlist = new ArrayList<>();
		
	}
	
	public String getName() {
		return this.name;
	}
	
	public List<Song> getSongs() {
		return this.playlist;
	}
	
	public void addSong(Song song) {
		
	}
	
	public void removeSong(Song song) {
		
	}
}
