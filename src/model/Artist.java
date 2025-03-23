package model;

import java.util.Objects;

public class Artist {
	private final String name;
	
	public Artist (String name) {
		this.name = name;
	}
	
	
	public String getName() {
		return this.name;
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true;
	    if (obj == null || getClass() != obj.getClass()) return false;
	    Artist artist = (Artist) obj;
	    return Objects.equals(name, artist.name);
	}

	@Override
	public int hashCode() {
	    return Objects.hash(name);
	}
}