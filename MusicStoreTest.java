package model.tests;

import model.MusicStore;
import model.Song;
import model.Album;
import model.Artist;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

class MusicStoreTest {
	
	MusicStore store = new MusicStore("src/model/albums.txt");
	String title = "This is How Tomorrow Moves";
	Artist artist = new Artist("Beabadoobee");
	Album album = new Album(title,artist);
	Song songOne = new Song("Take A Bite", artist, album);
	Song songTwo = new Song("Ever Seen", artist, album);

    @Test
    public void testAddArtist() {
        store.addArtist(artist);
        assertTrue(store.getArtist().contains(artist.getName()));
    }
    
    @Test
    public void testAddDuplicate() {
    	int initialSize = store.getArtist().size();
    	Artist dupArtist = new Artist("Adele");
    	store.addArtist(dupArtist);
    	int newSize = store.getArtist().size();
    	assertEquals(newSize,initialSize);
    }

    @Test
    public void testAddSong() {
        store.addSong(songOne);
        assertTrue(store.getSongs().contains("Take A Bite"));
    }

    @Test
    public void testAddAlbum() {
        store.addAlbum(album);
        assertTrue(store.getAlbums().contains("This is How Tomorrow Moves"));
    }

    @Test
    void testSearchSongByTitleFound() {
        store.addSong(songOne);

        String result = store.searchSongByTitle("Take A Bite");
        String expected = "[Take A Bite Beabadoobee This is How Tomorrow Moves]";
        
        assertEquals(expected, result);
    }

    @Test
    void testSearchSongByTitleNotFound() {
        String result = store.searchSongByTitle("Wrong Song");
        assertEquals("Song Wrong Song not found", result);
    }

    @Test
    void testSearchSongByArtistFound() {
        store.addSong(songOne);
        store.addSong(songTwo);

        String result = store.searchSongByArtist("Beabadoobee");
        String expected = "[Take A Bite Beabadoobee This is How Tomorrow Moves, Ever Seen Beabadoobee This is How Tomorrow Moves]";
        assertEquals(expected,result);
    }

    @Test
    void testSearchSongByArtistNotFound() {
        String result = store.searchSongByArtist("Unknown Artist");
        assertEquals("No songs found for Unknown Artist", result);
    }

    @Test
    void testSearchAlbumByTitleFound() {
    	album.addSong(songOne);
        store.addAlbum(album);

        String result = store.searchAlbumByTitle("This is How Tomorrow Moves");
        String expected = "[This is How Tomorrow Moves [Take A Bite]]";
        assertEquals(expected, result);
    }

    @Test
    void testSearchAlbumByTitleNotFound() {
        String result = store.searchAlbumByTitle("Nonexistent Album");
        assertEquals("Album Nonexistent Album not found", result);
    }

    @Test
    void testSearchAlbumByArtist_Found() {
        store.addAlbum(album);
        String result = store.searchAlbumByArtist("Beabadoobee");
        String expected = "[This is How Tomorrow Moves []]";
        assertEquals(expected, result);
    }

    @Test
    void testSearchAlbumByArtistNotFound() {
        String result = store.searchAlbumByArtist("Unknown Artist");
        assertEquals("No albums found for Unknown Artist", result, "Search should return 'not found' message.");
    }
}
