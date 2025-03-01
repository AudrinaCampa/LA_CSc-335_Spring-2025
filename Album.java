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
	
	public String getArtists() {
		return this.artist.getName(); 
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public List<String> getSongs() {
		List<String> songTitles = new ArrayList<>();
		for (Song song  : songs) {
			songTitles.add(song.getTitle());

		}
		return songTitles;
		
	}
	
	public void addSong(Song song) {
		songs.add(song);
	}
	
	
}
	


