package model.tests;

import model.MusicStore;
import model.Song;
import model.Album;
import model.Artist;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class MusicStoreTest {

	MusicStore store = new MusicStore("src/model/albums.txt");
	String title = "This is How Tomorrow Moves";
	Artist artist = new Artist("Beabadoobee");
	Album album = new Album(title, artist);
	Song songOne = new Song("Take A Bite", artist, album);
	Song songTwo = new Song("Ever Seen", artist, album);

	@Test
	public void testAddSong() {
	
		
		 store.addSong(songOne);
	     store.addSong(songTwo);
	     store.addAlbum(album);
	     store.addArtistSongs();
	     System.out.println(store.getArtist());
	     
	     
	       
	        
	}

}
