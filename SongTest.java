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
	
	
	void testToString() {
		assertEquals("Title : Take A Bite, Artist : Beabadoobee, Album : This is How Tomorrow Moves", song.toString());
		
	}

}
