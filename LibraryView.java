package view;

import java.util.Scanner;

import model.MusicStore;
import model.PlayList;
import model.Album;
import model.LibraryModel;
import model.Artist;
import model.Song;


public class LibraryView {
  public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner input = new Scanner(System.in);
		
		System.out.print("Create Playlist: ");
		String playlist = input.nextLine();
		PlayList pl = new PlayList(playlist);
		
		System.out.println("Playlist Name: " + playlist);
		
		
		Scanner search = new Scanner(System.in);
		System.out.print("Search for Song by Artist: ");
		String artistName = search.nextLine();
		

}
