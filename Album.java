/**
 * 
 */
package model;

import java.util.ArrayList;

/**
 * 
 *
 */
public class Album {
	
	private ArrayList<String> albums;
	
	
	public Album() {
		this.albums = new ArrayList<String>();
	}


	public void addAlbum(String title) {
		this.albums.add(title);
	}
}
