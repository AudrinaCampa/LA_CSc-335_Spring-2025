package model.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Album;
import model.Artist;
import model.PlayList;
import model.Song;

class PlaylistTest {
	PlayList playlist = new PlayList("playlist 1");
	String title = "This is How Tomorrow Moves";
	Artist artist = new Artist("Beabadoobee");
	Album album = new Album(title,artist);
	Song songOne = new Song("Take A Bite", artist, album);
	Song songTwo = new Song("Ever Seen", artist, album);
	


	@Test
	public void testGetName() {
		assertEquals("playlist 1", playlist.getName());
	}
	
	@Test
	public void testGetSongs() {	
		playlist.addSong(songTwo);
		playlist.addSong(songOne);
		
		assertEquals("Ever Seen", playlist.getSongs().get(0));
		assertEquals("Take A Bite", playlist.getSongs().get(1));
		
	}
	
	@Test
	public void testRemoveSong() {
		playlist.addSong(songTwo);
		playlist.addSong(songOne);
		
		playlist.removeSong(songTwo);
		assertEquals("Take A Bite", playlist.getSongs().get(0));
	}

}
