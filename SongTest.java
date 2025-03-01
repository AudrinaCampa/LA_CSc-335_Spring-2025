package model.tests;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

import model.Album;
import model.Artist;
import model.Song;

class SongTest {
	
	String title = "Take A Bite";
	Artist artist = new Artist("Beabadoobee");
	Album album = new Album("This is How Tomorrow Moves", artist);
	Song song = new Song(title,artist,album);
	
	@Test
	public void testToString() {
		assertEquals("Title : Take A Bite, Artist : Beabadoobee, Album : This is How Tomorrow Moves", song.toString());
		
	}
	
	@Test
	public void testSetRating() {
		song.setRating(5);
		int songRating = song.getRating();
		assertEquals(5,songRating);
		
	}
	
	@Test
	public void testSetRatingThrowsException() {
		try {
			song.setRating(0); // Should throw an exception
			
		} catch (IllegalArgumentException e) {
			assertEquals("Rating must be in range of 1 and 5", e.getMessage());
	        
		}
		try {
			song.setRating(6); // Should throw an exception
	        
		} catch (IllegalArgumentException e) {
	            assertEquals("Rating must be in range of 1 and 5", e.getMessage());
	        }
	    }
	
	@Test
	public void testGetAlbum() {
		assertEquals("This is How Tomorrow Moves", song.getAlbum());
	}
	
	@Test
	public void testGetArtist() {
		assertEquals("Beabadoobee", song.getArtist());
	}
	
	@Test
	public void testGetTitle() {
		assertEquals("Take A Bite", song.getTitle());
	}
	
}


