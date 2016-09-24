package ar.edu.untref.tesis.cmso.domain;

import java.util.List;

public class Octave {

	private List<ImagenOctava> imagenesOctava;
	
	public Octave(List<ImagenOctava> imagenesOctava) {
		this.imagenesOctava = imagenesOctava;
	}
	
	public List<ImagenOctava> getImagenesOctava() {
		return imagenesOctava;
	}

}
