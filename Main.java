/**
 * 
 */
package view;

import model.MusicStore;



/**
 * 
 *
 */
public class Main {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String aFile = "C:\\Users\\audri\\OneDrive\\Desktop\\Spring 2025\\CSC 335\\LA1 Project\\src\\albums\\albums.txt";
		String filename = "C:\\Users\\audri\\OneDrive\\Desktop\\Spring 2025\\CSC 335\\LA1 Project\\src\\albums\\19_Adele.txt";
		
		MusicStore ms = new MusicStore(aFile,filename);
		
		ms.readFileName();
	}

}
