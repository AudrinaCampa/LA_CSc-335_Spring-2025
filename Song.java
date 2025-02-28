/**
 * 
 */
package model;

import java.util.ArrayList;

/**
 *
 */
public class Song {
	
	//Instance variables
	
	private ArrayList<String> songs;
	
	public Song() {
		this.songs = new ArrayList<String>();
	}
	
	public void addSong(String title) {
		songs.add(title);
	}
}
