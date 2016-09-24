package ar.edu.untref.tesis.cmso.domain;

import java.awt.image.BufferedImage;

public class Image {

	private int[][] imagenOriginal;
	private int type;
	private int width;
	private int height;

	public Image(BufferedImage imagenOriginal) {
		validarImagenOriginal(imagenOriginal);
		this.type = imagenOriginal.getType();
		this.width = imagenOriginal.getWidth();
		this.height = imagenOriginal.getHeight();
		this.imagenOriginal = convertirImagenEnMatriz(imagenOriginal);
	}

	public int[][] convertirImagenEnMatriz(BufferedImage imagen) {
		int[][] matriz = new int[imagen.getWidth()][imagen.getHeight()];
		for (int i = 0; i < imagen.getWidth(); i++) {
			for (int j = 0; j < imagen.getHeight(); j++) {
				int rgb = imagen.getRGB(i, j);
				int red = 0xff & (rgb >> 16);
				matriz[i][j] = red;
			}
		}
		return matriz;
	}

	public BufferedImage convertirMatrizEnImagen(int[][] matriz, int ancho,
			int alto) {
		BufferedImage bufferedImage = new BufferedImage(ancho, alto,
				BufferedImage.TYPE_3BYTE_BGR);
		for (int i = 0; i < ancho; i++) {
			for (int j = 0; j < alto; j++) {
				int pixel = matriz[i][j];
				colorToRGB(0, pixel, pixel, pixel);
				bufferedImage.setRGB(i, j, colorToRGB(0, pixel, pixel, pixel));
			}
		}
		return bufferedImage;
	}

	public BufferedImage clonarEsqueleto() {
		return new BufferedImage(getImagenOriginal().getWidth(),
				getImagenOriginal().getHeight(), getImagenOriginal().getType());
	}

	public BufferedImage getImagenOriginal() {
		return convertirMatrizEnImagen(imagenOriginal, width, height);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getType() {
		return type;
	}

	public int obtenerPunto(int fila, int columna) {
		return imagenOriginal[fila][columna];
	}

	public void setearPunto(int fila, int columna, int valor) {
		imagenOriginal[fila][columna] = valor;
	}

	private int colorToRGB(int alpha, int red, int green, int blue) {
		int newPixel = 0;
		newPixel += alpha;
		newPixel = newPixel << 8;
		newPixel += red;
		newPixel = newPixel << 8;
		newPixel += green;
		newPixel = newPixel << 8;
		newPixel += blue;
		return newPixel;
	}
	
	private void validarImagenOriginal(BufferedImage imagenOriginal) {
		if (imagenOriginal == null) {
			throw new IllegalArgumentException("La imagen es necesaria.");
		}
	}

}