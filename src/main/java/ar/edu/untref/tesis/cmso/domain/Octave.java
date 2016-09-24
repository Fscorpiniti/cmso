package ar.edu.untref.tesis.cmso.domain;

import java.util.List;

public class Octave {

	private List<OctaveImage> imagenesOctava;
	
	public Octave(List<OctaveImage> imagenesOctava) {
		this.imagenesOctava = imagenesOctava;
	}
	
	public List<OctaveImage> getImagenesOctava() {
		return imagenesOctava;
	}

}
