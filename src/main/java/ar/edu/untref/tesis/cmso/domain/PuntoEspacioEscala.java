package ar.edu.untref.tesis.cmso.domain;

import java.awt.Point;

public class PuntoEspacioEscala {

	private Point punto;
	private ImagenOctava imagenOctava;
	
	public PuntoEspacioEscala(Point punto, ImagenOctava imagenOctava) {
		this.punto = punto;
		this.imagenOctava = imagenOctava;
	}

	public Point getPunto() {
		return punto;
	}

	public ImagenOctava getImagenOctava() {
		return imagenOctava;
	}

}
