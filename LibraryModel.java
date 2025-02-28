package model;

import java.util.ArrayList;
import java.util.List;

public class LibraryModel {
	private List<Song> favorite;
	private List<Song> userLib;
	private List<PlayList> playlists;
	
	public LibraryModel() {
		this.favorite = new ArrayList<>();
		this.userLib = new ArrayList<>();
		this.playlists = new ArrayList<>();
	}
	
	public void addFavorite(Song song) {
		
	}
	
	public void removeFavorite(Song song) {
		
	}
	
	public List<Song> getFavorite() {
		return this.favorite;
	}
	
	public void rateSong(Song song, int rating) {
		
	}
	
	public void addSongToLib (Song song) {
		
	}
	
	public void makePlaylist(String name) {
		
	}
	
	public List<PlayList> getPlaylistByName(String name) {
		return playlists;
		
	}
	
	public void addSongToPlaylist (String name, Song song) {
		
	}
	
	public void removeSongFromPlaylist (String name, Song song) {
		
	}
	
	public List<PlayList> getPlaylists() {
		return playlists;
		
	}
	
	
	
	

}
