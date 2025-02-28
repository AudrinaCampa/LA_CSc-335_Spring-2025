package model;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MusicStore {
	
	// Instance Variables
	private String file;
	private List<Song> songs;
	private List<Album> albums;
	
	// Constructor
	public MusicStore(String filename) {
		this.file = filename;
		this.songs = new ArrayList<>();
		this.albums = new ArrayList<>();
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
	public void addSong(Song song) {
		songs.add(song);
	}
	
	public void addAlbum(Album album) {
		albums.add(album);
	}
	
	public Song getSongByTitle (String title) {
		return null;
		
	}
	
	public Artist getSongByArtist (Artist artist) {
		return artist;
		
	}
}

