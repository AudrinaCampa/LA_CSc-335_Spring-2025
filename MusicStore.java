/**
 * The model package is where our data is stored and managed. 
 */
package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class stores the music from provided text files that include album
 * information. With this class, we are storing the album information within an
 * ArrayList.
 *
 */
public class MusicStore {

	// Instance variables
	private Artist artist;
	private Song songs;
	private Album albums;
	
	private String aFile, fName;
	
	public MusicStore(String albumFile, String filename) {
		this.aFile = albumFile;
		this.fName = filename;
		artist = new Artist();
		albums = new Album();
		songs = new Song();
	}
	
	public void readAlbumFile() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(this.aFile));

			String content = br.readLine();
			while (content != null) {
				String[] line = content.split(",");
				albums.addAlbum(line[0]);
				artist.addArtist(line[1]);
				content = br.readLine();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public void readFileName() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(this.fName));
			br.readLine();
			String content;
			while ((content = br.readLine()) != null) {
				songs.addSong(content);
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
