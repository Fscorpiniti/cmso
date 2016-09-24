package ar.edu.untref.tesis.cmso.domain;

import java.util.List;

public class Octave {

	private List<OctaveImage> octaveImages;
	
	public Octave(List<OctaveImage> octaveImages) {
		this.octaveImages = octaveImages;
	}
	
	public List<OctaveImage> getOctaveImages() {
		return octaveImages;
	}

}
