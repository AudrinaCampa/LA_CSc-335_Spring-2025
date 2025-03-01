package view;

import java.util.Scanner;

import model.PlayList;
import model.Album;
import model.LibraryModel;
import model.MusicStore;
import model.Artist;
import model.Song;

public class LibraryView {
	public static void main(String[] args) {
		MusicStore ms = new MusicStore("src/model/albums.txt");
		LibraryModel lm = new LibraryModel();
		
		Scanner input = new Scanner(System.in);
		
		System.out.print("Create Playlist: ");
		String playlist = input.nextLine();
		PlayList pl = new PlayList(playlist);
		
		System.out.println("Playlist Name: " + playlist);
		
		
		Scanner search = new Scanner(System.in);
		System.out.print("Search for Song by Artist: ");
		String artistName = search.nextLine();
		
		Scanner searchAlbum = new Scanner(System.in);
		System.out.print("Search for Album by Title: ");
		String albumName = searchAlbum.nextLine();
		
		ms.searchSongByArtist(artistName);
		ms.searchAlbumByTitle(albumName);
		 
	}
		
}
