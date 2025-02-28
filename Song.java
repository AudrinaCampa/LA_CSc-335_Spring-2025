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
	
	public String getTitle() {
		return this.title;
	}
	
	public Artist getArtist() {
		return this.artist;
	}
	
	public Album getAlbum() {
		return this.album;
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

}
