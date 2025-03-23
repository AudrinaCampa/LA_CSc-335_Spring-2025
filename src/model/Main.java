package model;

import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        // Create playlist
        PlayList myPlaylist = new PlayList("My Favorite Songs");
    
        String absolutePath = Paths.get("albums.txt").toAbsolutePath().toString();
        System.out.println("Absolute Path: " + absolutePath);


        // Create some artists
        Artist artist1 = new Artist("Beabadoobee");
        Artist artist2 = new Artist("Taylor Swift");

        // Create some albums
        Album album1 = new Album("This is How Tomorrow Moves", artist1);
        Album album2 = new Album("1989", artist2);
        
   

        // Create some songs
        Song song1 = new Song("Take A Bite", artist1, album1);
        Song song2 = new Song("Cruel Summer", artist2, album2);
        Song song3 = new Song("Take A Bite", artist1, album1); // Duplicate song

        // Add songs to playlist
        myPlaylist.addSong(song1);
        myPlaylist.addSong(song2);
        myPlaylist.addSong(song3); // This should not be added since it's a duplicate

        // Print out songs in the playlist
        System.out.println("Playlist: " + myPlaylist.getSongs());
        
        }
    
}
