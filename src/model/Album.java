package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
	
	public List<Song> getSongs() {
		 return new ArrayList<>(songs);
		
	}
	
	public void addSong(Song song) {
		songs.add(new Song(song));
	}
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true;
	    if (obj == null || getClass() != obj.getClass()) return false;
	    Album album = (Album) obj;
	    return Objects.equals(title, album.title) && 
	           Objects.equals(artist, album.artist);
	}

	@Override
	public int hashCode() {
	    return Objects.hash(title, artist);
	}
	
	
}
	


