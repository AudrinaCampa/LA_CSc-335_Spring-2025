package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MusicStoreTest {

	@Test
	void testReadFile() {
		
		String filename = "19_Adele.txt";
		
		MusicStore ms = new MusicStore(filename);
		ms.readFile();
		
		
	}

}
