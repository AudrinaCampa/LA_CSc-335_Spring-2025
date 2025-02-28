package model;

import java.util.ArrayList;
import java.util.List;

public class Album { 
	private List<Song> songs;
	private final String title;
	private Artist artist;
	
	public Album (String title, Artist artist) {
		this.title = title;
		this.artist = artist;
		this.songs = new ArrayList<>();
	
	}
	
	public Artist getArtists() {
		return this.artist; 
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public List<Song> getSongs() {
		return this.songs;
		
	}
	
	public void addSong(Song song) {
		songs.add(song);
	}
	
	

}
