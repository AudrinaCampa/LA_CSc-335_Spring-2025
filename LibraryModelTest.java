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
    void testRemoveFavorite() {
    	library.addFavorite(songOne);
    	int initialSize = library.getFavorite().size();
        library.removeFavorite(songOne);
        int newSize = library.getFavorite().size();
        assertFalse(newSize == initialSize);
    }
}
