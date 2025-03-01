package model.tests;

import static org.junit.Assert.assertEquals;


import org.junit.jupiter.api.Test;

import model.Album;
import model.Artist;
import model.Song;

class AlbumTest {
	
	String title = "This is How Tomorrow Moves";
	Artist artist = new Artist("Beabadoobee");
	Album album = new Album(title,artist);
	


	@Test
	public void testGetArtist() {
		assertEquals("Beabadoobee",album.getArtists());
		
	}
	
	@Test
	public void testGetTitle() {
		assertEquals("This is How Tomorrow Moves", album.getTitle());
	}
	
	@Test
	public void testAddSong() {
		Song songOne = new Song("Take A Bite", artist, album);
		Song songTwo = new Song("Ever Seen", artist, album);
		album.addSong(songOne);
		album.addSong(songTwo);
		assertEquals("Take A Bite",album.getSongs().get(0));
		assertEquals("Ever Seen",album.getSongs().get(1));
		
	}

}
