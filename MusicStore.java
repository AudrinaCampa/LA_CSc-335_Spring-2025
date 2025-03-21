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

		Artist artist = new Artist(song.getArtist());
		addArtist(artist);
		List<Song> artistSongs = artists.get(artist);
		artistSongs.add(new Song(song));
	}

	public void addAlbum(Album album) {
		albums.put(album, album.getSongs());
	}

	public void addArtist(Artist artist) {
		if (!artists.containsKey(artist)) {
			artists.put(artist, new ArrayList<>());
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
		List<String> songList = new ArrayList<>();
		for (String songTitle : songTitles.keySet()) {
			// add keys to list
			songList.add(songTitle);
		}
		return songList;
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

}
