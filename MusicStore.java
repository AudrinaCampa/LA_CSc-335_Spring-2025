package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MusicStore {
    private List<Song> songs;
    private HashMap<Album, List<Song>> albums;
    private HashMap<Artist, List<Song>> artists;
    private final String file;
    
    public MusicStore(String filename ) {
    	this.songs = new ArrayList<>();
    	this.albums = new HashMap<>();
    	this.artists = new HashMap<>();
    	this.file = filename;
    	readAlbumsList();
    	
    }



    private void readAlbumsList() {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length < 2) 
                	continue;

                String albumTitle = details[0].trim();
                String artistName = details[1].trim();
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

            String albumTitle = details[0].trim();        
            Artist artist = new Artist(artistName);
            addArtist(artist);

            Album album = new Album(albumTitle, artist);
            addAlbum(album);

            String songTitle;
            while ((songTitle = br.readLine()) != null) {
                if (!songTitle.trim().isEmpty()) {
                    Song song = new Song(songTitle.trim(), artist, album);
                    album.addSong(song);
                    addSong(song);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file " + fileName + ": " + e.getMessage());
        }

	
}
