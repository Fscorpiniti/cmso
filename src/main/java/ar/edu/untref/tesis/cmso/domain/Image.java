package ar.edu.untref.tesis.cmso.domain;

import java.awt.image.BufferedImage;

public class Image {

	private int[][] originalImage;
	private int type;
	private int width;
	private int height;

	public Image(BufferedImage originalImage) {
		validateOriginalImage(originalImage);
		this.type = originalImage.getType();
		this.width = originalImage.getWidth();
		this.height = originalImage.getHeight();
		this.originalImage = convertImageToMatrix(originalImage);
	}

	public int[][] convertImageToMatrix(BufferedImage image) {
		int[][] matrix = new int[image.getWidth()][image.getHeight()];
		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				int rgb = image.getRGB(i, j);
				int red = 0xff & (rgb >> 16);
				matrix[i][j] = red;
			}
		}
		return matrix;
	}

	public BufferedImage convertMatrixToImage(int[][] matrix, int width,
			int height) {
		BufferedImage bufferedImage = new BufferedImage(width, height,
				BufferedImage.TYPE_3BYTE_BGR);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int pixel = matrix[i][j];
				colorToRGB(0, pixel, pixel, pixel);
				bufferedImage.setRGB(i, j, colorToRGB(0, pixel, pixel, pixel));
			}
		}
		return bufferedImage;
	}

	public BufferedImage cloneSkeleton() {
		return new BufferedImage(getOriginalImage().getWidth(),
				getOriginalImage().getHeight(), getOriginalImage().getType());
	}

	public BufferedImage getOriginalImage() {
		return convertMatrixToImage(originalImage, width, height);
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

	public int getPoint(int row, int column) {
		return originalImage[row][column];
	}

	public void setPoint(int fila, int column, int value) {
		originalImage[fila][column] = value;
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

	private void validateOriginalImage(BufferedImage imagenOriginal) {
		if (imagenOriginal == null) {
			throw new IllegalArgumentException("La imagen es necesaria.");
		}
	}

}