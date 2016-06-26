package ar.edu.untref.tesis.cmso.domain;

import java.awt.image.BufferedImage;

public class Imagen {

	private BufferedImage imagenOriginal;

	public Imagen(BufferedImage imagenOriginal) {
		this.imagenOriginal = imagenOriginal;
	}

	public BufferedImage clonarEsqueleto() {
		return new BufferedImage(getImagenOriginal().getWidth(),
				getImagenOriginal().getHeight(), getImagenOriginal().getType());
	}

	public BufferedImage getImagenOriginal() {
		return imagenOriginal;
	}

}