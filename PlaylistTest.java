package model.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
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
		playlist.addSong(songTwo);
		assertTrue(playlist.getSongs().contains("Title : " + songTwo.getTitle() + " Artist : " + songTwo.getArtist()));

	}
	
	@Test
	public void testRemoveSong() {
		playlist.addSong(songOne);
		playlist.addSong(songTwo);
		
		playlist.removeSong(songOne);
		assertTrue(playlist.getSongs().get(0).contains("Title : " + songTwo.getTitle() + " Artist : " + songTwo.getArtist()));
	}

}

