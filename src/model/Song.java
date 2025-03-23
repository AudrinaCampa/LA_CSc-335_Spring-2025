package model;

public class Song {
	private final String title;
	private Artist artist;
	private Album album;
	private Integer rating;
	

	public Song (String title, Artist artist, Album album) {
		this.title = title;
		this.artist = artist;
		this.album = album;
		this.rating = null;
	}
	
	// copy constructor (for adding song to album, avoiding escaping reference)
	public Song (Song other) {
		this.title = other.title;
		this.artist = other.artist;
		this.album = other.album;
		this.rating = other.rating;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getArtist() {
		return this.artist.getName();
	}
	
	public String getAlbum() {
		return this.album.getTitle();
	}
	
	public void setRating(int value) {
		if (value < 1 || value > 5) {
			throw new IllegalArgumentException("Rating must be in range of 1 and 5");
		}
		this.rating = value;
	}
	
	public int getRating() {
		return this.rating;
	}
	
	
	@Override
	public String toString() {
		return title;
		
	}

}
