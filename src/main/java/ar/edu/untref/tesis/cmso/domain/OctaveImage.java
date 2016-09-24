package ar.edu.untref.tesis.cmso.domain;

public class OctaveImage {

	private Image image;
	private Double sigma;

	public OctaveImage(Image image, Double sigma) {
		this.image = image;
		this.sigma = sigma;
	}

	public Image getImage() {
		return image;
	}

	public Double getSigma() {
		return sigma;
	}

	public Boolean isExtreme(int pixel, int row, int column, boolean isValid) {
		Boolean result = isValid;
		if (isValid) {
			result &= image.getPoint(row - 1, column - 1) < pixel;
			result &= image.getPoint(row - 1, column) < pixel;
			result &= image.getPoint(row - 1, column + 1) < pixel;
			result &= image.getPoint(row, column - 1) < pixel;
			result &= image.getPoint(row, column) < pixel;
			result &= image.getPoint(row, column + 1) < pixel;
			result &= image.getPoint(row + 1, column - 1) < pixel;
			result &= image.getPoint(row + 1, column) < pixel;
			result &= image.getPoint(row + 1, column + 1) < pixel;
		}
		return result;
	}

}
