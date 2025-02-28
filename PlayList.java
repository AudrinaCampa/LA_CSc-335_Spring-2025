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
	
	public List<String> getSongs() {
		List<String> songTitles = new ArrayList<>();
		for (Song song  : playlist) {
			songTitles.add(song.getTitle());

		}
		return songTitles;
		
	}
	
	public void addSong(Song song) {
		for (Song otherSong : playlist) {
			if (otherSong.getTitle().equals(song.getTitle()) && otherSong.getArtist().equals(song.getArtist()));
			return;
		}
		playlist.add(song);
		
	}
	
	public void removeSong(Song song) {
		playlist.remove(song);
	}
}
