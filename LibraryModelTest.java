package model.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

import model.Album;
import model.Artist;
import model.LibraryModel;
import model.MusicStore;
import model.Song;

class LibraryModelTest {

        LibraryModel library = new LibraryModel();
        MusicStore store = new MusicStore("src/model/albums.txt");
        String title = "This is How Tomorrow Moves";
    	Artist artist = new Artist("Beabadoobee");
    	Album album = new Album(title,artist);
    	Song songOne = new Song("Take A Bite", artist, album);
    	Song songTwo = new Song("Ever Seen", artist, album);

    

    @Test
    public void testAddFavorite() {
    	library.addSongToLib(songOne, store);
    	library.addFavorite(songOne);
    	assertTrue(library.getFavorite().contains(songOne));
    	
    }

    @Test
    public void testRemoveFavorite() {
    	library.addFavorite(songOne);
    	int initialSize = library.getFavorite().size();
        library.removeFavorite(songOne);
        int newSize = library.getFavorite().size();
        assertFalse(newSize == initialSize);
    }

    @Test
    public void testRateSongAddsToFavorites() {
    	library.rateSong(songOne, 5);
    	assertTrue(library.getFavorite().contains(songOne));
    }

    @Test
    public void testAddSongToLib() {
    	store.addSong(songOne);
        library.addSongToLib(songOne, store);
        assertTrue(library.getSongNames().contains(songOne.getTitle()));
    }

    @Test
    public void testAddAlbumToLib() {
    	store.addAlbum(album);
        library.addAlbumToLib(album, store);
        assertTrue(library.getAlbums().contains(album.getTitle()));
    }
    
    
    // doesnt work
    @Test
    public void testAddArtist() {
    	store.addArtist(artist);
    	//System.out.print(store.getArtist());
        library.addArtist(artist, store);
        //System.out.print(library.getAlbums());
        assertTrue(library.getArtists().contains(artist.getName()));
    }

    @Test
    public void testMakeAndGetPlaylist() {
        library.makePlaylist("My Playlist");
        assertTrue(library.getPlaylists().contains("My Playlist"));
    }

    @Test
    public void testAddSongToPlaylist() {
    	store.addSong(songOne);
        library.addSongToLib(songOne, store);
        library.addSongToPlaylist("My Playlist", songOne);
        assertTrue(library.getPlaylistSongs("My Playlist").contains(songOne.getTitle()));
    }

    @Test
    public void testRemoveSongFromPlaylist() {
    	store.addSong(songOne);
        library.addSongToLib(songOne, store);
        library.addSongToPlaylist("My Playlist", songOne);
        library.removeSongFromPlaylist("My Playlist", songOne);
        assertFalse(library.getPlaylistSongs("My Playlist").contains(songOne.getTitle()));
    }

    @Test
    public void testSearchSongByTitleFound() {
    	store.addSong(songOne);
        library.addSongToLib(songOne, store);
        String result = library.searchSongByTitle(songOne.getTitle());
        assertTrue(result.contains(songOne.getTitle()));
    }

    @Test
    public void testSearchSongByTitleNotFound() {
        String result = library.searchSongByTitle("Song");
        assertEquals("Song Song not found", result);
    }

    @Test
    public void testSearchSongByArtistFound() {
    	store.addSong(songOne);
        library.addSongToLib(songOne, store);
        String result = library.searchSongByArtist(songOne.getArtist());
        assertTrue(result.contains(songOne.getTitle()));
    }

    @Test
    void testSearchSongByArtistNotFound() {
        String result = library.searchSongByArtist("Unknown Artist");
        assertEquals("No songs found for Unknown Artist", result);
    }

    @Test
    void testSearchAlbumByTitleFound() {
        store.addAlbum(album);
        library.addAlbumToLib(album, store);
        System.out.print(library.getAlbums());
        String result = library.searchAlbumByTitle(album.getTitle());
        System.out.print(result);
        assertEquals(album.getTitle(),result);
    }

    @Test
    void testSearchAlbumByTitleNotFound() {
        String result = library.searchAlbumByTitle("Album");
        assertEquals("Album Album not found", result);
    }

    @Test
    void testSearchAlbumByArtistFound() {
    	store.addAlbum(album);
    	library.addAlbumToLib(album, store);
        String result = library.searchAlbumByArtist(album.getArtists());
        assertTrue(result.contains(album.getArtists()));
    }

	@Test
	void testSearchAlbumByArtistNotFound() {
		String result = library.searchAlbumByArtist("Unknown Artist");
		assertEquals("No albums found for Unknown Artist", result);
	}
}
