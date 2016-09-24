package ar.edu.untref.tesis.cmso.domain;

import java.util.ArrayList;
import java.util.List;

public class ScaleSpace {

	private List<Octave> octaves = new ArrayList<>();
	
	public ScaleSpace (List<Octave>octaves) {
		this.octaves = octaves;
	}

	public List<Octave> getOctaves() {
		return octaves;
	}

}
