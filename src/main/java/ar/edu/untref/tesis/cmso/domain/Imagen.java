package ar.edu.untref.tesis.cmso.domain;

import java.awt.image.BufferedImage;

public class Imagen {

	private BufferedImage imagenOriginal;

	public Imagen(BufferedImage imagenOriginal) {
		validarImagenOriginal(imagenOriginal);
		this.imagenOriginal = imagenOriginal;
	}

	private void validarImagenOriginal(BufferedImage imagenOriginal) {
		if (imagenOriginal == null) {
			throw new IllegalArgumentException("La imagen es necesaria.");
		}
	}

	public BufferedImage clonarEsqueleto() {
		return new BufferedImage(getImagenOriginal().getWidth(),
				getImagenOriginal().getHeight(), getImagenOriginal().getType());
	}

	public BufferedImage getImagenOriginal() {
		return imagenOriginal;
	}

}