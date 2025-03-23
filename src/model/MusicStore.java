package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MusicStore {
	private HashMap<String, List<Song>> songTitles;
	private HashMap<Album, List<Song>> albums;
	private HashMap<Artist, List<Song>> artists;
	private final String file;

	public MusicStore(String filename) {
		this.songTitles = new HashMap<>();
		this.albums = new HashMap<>();
		this.artists = new HashMap<>();
		this.file = filename;
		readAlbumsList();
	}

	public void addSong(Song song) {
		if (songTitles.containsKey(song.getTitle())) {
			// get list of songs with the title
			List<Song> existingSongs = songTitles.get(song.getTitle());

			// check if duplicate song with same artist
			boolean isDuplicate = false;
			for (Song existingSong : existingSongs) {
				// compare the artists name
				// if the song with the same artist exists dont add
				if (existingSong.getArtist().equals(song.getArtist())) {
					isDuplicate = true;
					break;
				}
			}

			// if theres no duplicate add song
			if (!isDuplicate) {
				existingSongs.add(new Song(song));
			}
		} else {
			// if the song doesnt exist already, create a new list and add song
			List<Song> newSongList = new ArrayList<>();
			newSongList.add(new Song(song)); // add copy
			songTitles.put(song.getTitle(), newSongList); // add list to map
		}
	}

	public void addAlbum(Album album) {
	    if (!albums.containsKey(album)) {
	        albums.put(album, new ArrayList<>());
	    }
	}
	
	public void addArtist(Artist artist) {
	    if (!artists.containsKey(artist)) {
	        artists.put(artist, new ArrayList<>());
	    }
	}

	public void addArtistSongs() {
	    for (List<Song> songList : songTitles.values()) {
	        for (Song song : songList) {
	            Artist artist = new Artist(song.getArtist());
	            
	            if (!artists.containsKey(artist)) {
	                artists.put(artist, new ArrayList<>());
	            }
	            artists.get(artist).add(song);  // add song to the artist song list
	        }
	    }
	}

	public List<String> getSongs() {
		List<String> songList = new ArrayList<>();
		for (String songTitle : songTitles.keySet()) {
			// add keys to list
			songList.add(songTitle);
		}
		return songList;
	}

	public List<String> getArtist() {
		List<String> artistList = new ArrayList<>();
		for (Artist artistName : artists.keySet()) {
			// add keys to list
			artistList.add(artistName.getName());
		}
		return artistList;
	}

	public List<String> getAlbums() {
		List<String> albumList = new ArrayList<>();
		for (Album albumName : albums.keySet()) {
			// add keys to list
			albumList.add(albumName.getTitle());
		}
		return albumList;
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
			// add artist songs to hash map
			addArtistSongs();
		
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
					
					// add albums songs to hash map
					if (albums.containsKey(album)) {
					    albums.get(album).add(song);
					}
				}
			}
		} catch (IOException e) {
			System.out.println("Error reading file " + fileName + ": " + e.getMessage());
		}
	}
	
	public String searchSongbyArtist(String artistName) {
		List<String> results = new ArrayList<>();
	    
	    for (Artist artist : artists.keySet()) {
	        if (artist.getName().equals(artistName)) {
	            // get songs for the matching artist
	            List<Song> songs = artists.get(artist);
	            for (Song song : songs) {
	                results.add(song.getTitle() + " by " + artist.getName() + " from album " + song.getAlbum());
	            }
	        }
	    }
	    
	    if (results.isEmpty()) {
	        return "No songs found for " + artistName;
	    }
	    
	    return results.toString();
	}
	
	public String searchSongByTitle(String title) {
		if (!songTitles.containsKey(title)) {
			return "Song " + title + " not found.";
		}
		
		List<Song> songNames = songTitles.get(title);
		List<String> results = new ArrayList<>();
		
		for (Song song : songNames) {
			results.add(song.getTitle() + " by " + song.getArtist() + " from album " + song.getAlbum());
			
		}
		
		return results.toString();
		
	
	}
	
	public String searchAlbumByArtist(String artist) {
		List<String> results = new ArrayList<>();
		for (Album album : albums.keySet()) {
			if (album.getArtists().equals(artist)) {
				results.add(album.getTitle() + ": " + album.getSongs());
			}
		
		}
		
		if (results.isEmpty()) {
			return "No albums found for " + artist;
		}
			
		return results.toString();
		
	}
	
	public String searchAlbumByTitle(String title) {
		List<String> results = new ArrayList<>();
	    for (Album album : albums.keySet()) {
	        if (album.getTitle().equals(title)) {
	            results.add(album.getTitle() + " " + album.getSongs());
	        }
	    }
	    
	    if (results.isEmpty()) {
	    	return "No albums found for " + title;
	    }
	    return results.toString();
	}

}