package ar.edu.untref.tesis.cmso.domain;

import java.awt.Point;

public class ScaleSpacePoint {

	private Point punto;
	private OctaveImage imagenOctava;
	
	public ScaleSpacePoint(Point punto, OctaveImage imagenOctava) {
		this.punto = punto;
		this.imagenOctava = imagenOctava;
	}

	public Point getPunto() {
		return punto;
	}

	public OctaveImage getImagenOctava() {
		return imagenOctava;
	}

}
