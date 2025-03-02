
package model.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Artist;

class ArtistTest {
	Artist artist = new Artist("Beabadoobee");

	@Test
	void testGetName() {
		assertEquals("Beabadoobee", artist.getName());
	}

}

