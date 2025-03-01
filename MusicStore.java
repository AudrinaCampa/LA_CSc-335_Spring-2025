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
    readAlbumsList();
    
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
    public String searchAlbumByArtist(String artist) {
    	List<Album> results = new ArrayList<>();
    	for (Album album : albums) {
    		if (album.getArtists().equals(artist)) {
    			results.add(album);
    		}
    	}
    	if (results.isEmpty()) {
    		return "No albums found for " + artist;
    	}
    	
    	return results.toString();
    }
    
    
    public void readAlbumsList() {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            while (line != null) {
                String[] details = line.split(",");
                if (details.length < 2) 
                	continue;

                String albumTitle = details[0];
                String artistName = details[1];
                String albumFileName = "src/model/" + albumTitle + "_" + artistName + ".txt";
                readAlbumFile(albumFileName, artistName);
            }
        } catch (IOException e) {
            System.out.println("Error reading albums.txt: " + e.getMessage());
        }
    }

    private void readAlbumFile(String fileName, String artistName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String firstLine = br.readLine();
            if (firstLine == null) 
            	return;

            String[] details = firstLine.split(",");
            if (details.length < 4) 
            	return;

            String albumTitle = details[0];
            Artist artist = new Artist(artistName);
            addArtist(artist);

            Album album = new Album(albumTitle, artist);
            addAlbum(album);

            String songTitle;
            while ((songTitle = br.readLine()) != null) {
                if (!songTitle.isEmpty()) {
                    Song song = new Song(songTitle, artist, album);
                    album.addSong(song);
                    addSong(song);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file " + fileName + ": " + e.getMessage());
        }
    }

	public List<String> getSongs() {
		List<String> songList = new ArrayList<>();
		for (Song song : songs) {
			songList.add(song.getTitle());
		}
		return songList;
	}

	public List<String> getAlbums() {
		List<String> albumList = new ArrayList<>();
		for (Album album : albums) {
			albumList.add(album.getTitle());
		}
		return albumList;
	}
	
}
