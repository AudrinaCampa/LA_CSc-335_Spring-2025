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
	
	public void printDebugInfo() {
	    // Print songTitles HashMap size
	    System.out.println("\n===== SONG TITLES HASHMAP DEBUG =====");
	    System.out.println("Total unique song titles: " + songTitles.size());
	    int totalSongs = 0;
	    for (List<Song> songs : songTitles.values()) {
	        totalSongs += songs.size();
	    }
	    System.out.println("Total songs (including different versions): " + totalSongs);
	    
	    // Print albums HashMap details
	    System.out.println("\n===== ALBUMS HASHMAP DEBUG =====");
	    System.out.println("Total albums: " + albums.size());
	    System.out.println("Albums with their keys and hashcodes:");
	    for (Album album : albums.keySet()) {
	        System.out.println("Album key: " + album.getTitle() + " by " + album.getArtists() + 
	                           " (hashCode: " + album.hashCode() + ")");
	        System.out.println("  Number of songs in this album: " + albums.get(album).size());
	        System.out.println("  Songs:");
	        for (Song song : albums.get(album)) {
	            System.out.println("    - " + song.getTitle());
	        }
	    }
	    
	    // Print artists HashMap details
	    System.out.println("\n===== ARTISTS HASHMAP DEBUG =====");
	    System.out.println("Total artists: " + artists.size());
	    System.out.println("Artists with their keys and hashcodes:");
	    for (Artist artist : artists.keySet()) {
	        System.out.println("Artist key: " + artist.getName() + 
	                           " (hashCode: " + artist.hashCode() + ")");
	        System.out.println("  Number of songs by this artist: " + artists.get(artist).size());
	        System.out.println("  Songs:");
	        for (Song song : artists.get(artist)) {
	            System.out.println("    - " + song.getTitle() + " (Album: " + song.getAlbum() + ")");
	        }
	    }
	}
	
	public static void main(String[] args) {
	    // Create a MusicStore instance with your albums file
	    MusicStore store = new MusicStore("src/model/albums.txt");
	    
	    // Call the debug method to print detailed information
	    store.printDebugInfo();
	}
}