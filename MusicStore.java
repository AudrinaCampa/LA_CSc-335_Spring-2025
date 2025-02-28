package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MusicStore {
    private List<Song> songs;
    private List<Album> albums;
    private List<Artist> artists;
    private String file;
    
    public MusicStore(String filename ) {
    	this.songs = new ArrayList<>();
    	this.albums = new ArrayList<>();
    	this.artists = new ArrayList<>();
    	this.file = filename;
    	
    }
    
    public void addSong(Song song) {
    	songs.add(song);
    }
    
    
    public void addAlbum(Album album) {
    	albums.add(album);
    }
    
    public void addArtist(Artist artist) {
    	for (Artist dupliacateArtist : artists) {
    		if (dupliacateArtist.getName().equals(artist.getName())) {
    			return;
    		}
    	}
    	artists.add(artist);
    }
    
    public List<String> getArtist() {
    	List<String> artistNames = new ArrayList<>();
			for (Artist artist  : artists) {
				artistNames.add(artist.getName());
		}
		return artistNames;
		
	}
    
    public String searchSongByTitle(String title) {
    	List<Song> results = new ArrayList<>();
    	for (Song song : songs) {
    		if (song.getTitle().equals(title)) {
    			results.add(song);
    		}
    	}
    	if (results.isEmpty()) {
    		return "Song " + title + " not found";
    	}
    	
    	return results.toString();
    }
    
    public String searchSongByArtist(String artist) {
    	List<String> results = new ArrayList<>();
    	for (Song song : songs) {
    		if (song.getArtist().equals(artist)) {
    			results.add(song.getTitle());
    		}
    	}
    	if (results.isEmpty()) {
    		return "No songs found for " + artist;
    	}
    	return results.toString();    	
    }
    
    public String searchAlbumByTitle(String title) {
    	List<String> results = new ArrayList<>();
    	for (Album album : albums) {
    		if (album.getTitle().equals(title)) {
    			results.add(album.getTitle());
    		}
    	}
    	if (results.isEmpty()) {
    		return "Album " + title + " not found";
    	}
    	
    	return results.toString();
    }
    
    public void readFile() {
        try {
            FileReader fn = new FileReader(this.file);
            try (BufferedReader br = new BufferedReader(fn)) {
				if (br.ready()) {
				    String firstLine = br.readLine();
				    //System.out.println("Raw File Data: " + firstLine);
				    
				    if (firstLine != null) {
				        String[] details = firstLine.split(",");
				        if (details.length >= 2) {
				            String albumTitle = details[0];
				            String artistName = details[1];
				            //System.out.println("Album: " + albumTitle);
				            //System.out.println("Artist: " + artistName);

				            Artist artist = new Artist(artistName);
				            addArtist(artist);

				            Album album = new Album(albumTitle, artist);
				            addAlbum(album);

				            String songTitle;
				            while (br.ready() && (songTitle = br.readLine()) != null) {
				                if (!songTitle.isEmpty()) {
				                    Song song = new Song(songTitle, artist, album);
				                    album.addSong(song);
				                    addSong(song);
				                    //System.out.println("Loaded song: " + songTitle);
				                }
				            }
				        }
				    }
				}
			}
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
