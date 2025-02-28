package model;

import java.util.ArrayList;
import java.util.List;

public class LibraryModel {
	private List<Song> favorite;
	private List<Song> userLib;
	private List<PlayList> playlists;
	private List<Album> albums;
	private List<Artist> artists;

	public LibraryModel() {
		this.favorite = new ArrayList<>();
		this.userLib = new ArrayList<>();
		this.playlists = new ArrayList<>();
		this.albums = new ArrayList<>();
		this.artists = new ArrayList<>();
	}

	public void addFavorite(Song song) {
		if (!favorite.contains(song)) {
			favorite.add(song);
		}

	}

	public void removeFavorite(Song song) {
		favorite.remove(song);

	}

	public List<Song> getFavorite() {
		return new ArrayList<>(favorite);
	}

	public void rateSong(Song song, int rating) {
		song.setRating(rating);

		if (rating == 5 && !favorite.contains(song)) {
			favorite.add(song);
		}
	}

	public void addSongToLib(Song song, MusicStore store) {
		if (store.getSongs().contains(song.getTitle()) && !userLib.contains(song) && !userLib.contains(song.getArtist()) {
			userLib.add(song);
		}
	}

	public void addAlbumToLib(Album album, MusicStore store) {
		if (store.getAlbums().contains(album.getTitle()) && !albums.contains(album)) {
			albums.add(album);
		}
	}

	public void addArtist(Artist artist, MusicStore store) {
		if (store.getArtist().contains(artist.getName()) && !artists.contains(artist)) {
			for (Artist dupliacateArtist : artists) {
				if (dupliacateArtist.getName().equals(artist.getName())) {
					return;
				}
			}
			artists.add(artist);
		}
	}

	public void makePlaylist(String name) {
		playlists.add(new PlayList(name));
	}

	public PlayList getPlaylistByName(String name) {
		for (PlayList playlist : playlists) {
			if (playlist.getName().equals(name)) {
				return playlist;
			}
		}
		return null;

	}

	public void addSongToPlaylist(String name, Song song) {
		PlayList playlist = getPlaylistByName(name);
		if (playlist == null) {
			playlist = new PlayList(name);
			playlists.add(playlist);
		}

		if (userLib.contains(song) && !playlist.getSongs().contains(song.getTitle())) {
			playlist.addSong(song);
		}
	}

	public void removeSongFromPlaylist(String name, Song song) {
		PlayList playlist = getPlaylistByName(name);
		if (playlist != null) {
			playlist.removeSong(song);
		}

	}

	public List<String> getSongNames() {
		List<String> songNames = new ArrayList<>();
		for (Song song : userLib) {
			songNames.add(song.getTitle());
		}
		return songNames;
	}

	public List<String> getArtists() {
		List<String> artistNames = new ArrayList<>();
		for (Song song : userLib) {
			if (!artistNames.contains(song.getArtist())) {
				artistNames.add(song.getArtist());
			}
		}
		return artistNames;
	}

	public List<String> getAlbums() {
		List<String> albumList = new ArrayList<>();
		for (Album album : albums) {
			albumList.add(album.getTitle());
		}
		return albumList;
	}

	public List<String> getPlaylists() {
		List<String> playlistNames = new ArrayList<>();
		for (PlayList playlist : playlists) {
			playlistNames.add(playlist.getName());
		}
		return playlistNames;
	}

	public List<String> getFavoriteSongs() {
		List<String> favoriteSongs = new ArrayList<>();
		for (Song song : favorite) {
			favoriteSongs.add(song.getTitle());
		}
		return favoriteSongs;
	}

	public String getPlaylistSongs(String playlistName) {
		List<String> results = new ArrayList<>();
		for (PlayList playlist : playlists) {
			if (playlist.getName().equals(playlistName)) {
				for (String song : playlist.getSongs()) {
					results.add(song);
				}
			}
		}
		return results.toString();
	}

	public String searchSongByTitle(String title) {
		List<Song> results = new ArrayList<>();
		for (Song song : userLib) {
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
		for (Song song : userLib) {
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
		for (Song song : userLib) {
			if (song.getAlbum() != null && song.getAlbum().equals(title)) {
				results.add(song.getAlbum());
			}
		}
		if (results.isEmpty()) {
			return "Album " + title + " not found";
		}

		return results.toString();
	}

	public String searchAlbumByArtist(String artist) {
		List<String> results = new ArrayList<>();
		for (Song song : userLib) {
			if (song.getArtist() != null && song.getArtist().equals(artist)) {
				results.add(song.getArtist());
			}
		}
		if (results.isEmpty()) {
			return "No albums found for " + artist;
		}

		return results.toString();
	}
}
