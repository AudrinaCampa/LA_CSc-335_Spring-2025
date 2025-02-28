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
