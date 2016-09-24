package ar.edu.untref.tesis.cmso.domain;

import java.awt.image.Kernel;
import java.awt.image.Raster;

public class FilterRegion {

	private int width;
	private int height;
	private Kernel kernel;
	private Raster image;

	public FilterRegion(Kernel kernel, Raster image) {
		int left = kernel.getXOrigin();
		int right = Math.max(kernel.getWidth() - left - 1, 0);
		int up = kernel.getYOrigin();
		int down = Math.max(kernel.getHeight() - up - 1, 0);
		this.kernel = kernel;
		this.image = image;
		this.width = image.getWidth() - left - right;
		this.height = image.getHeight() - up - down;
	}

	public RegionExtremes calculateExtremes() {
		float maximum = 0;
		float minimum = 0;
		float[] maskValues = kernel.getKernelData(null);
		float[] temporalMatrix = new float[kernel.getWidth()
				* kernel.getHeight()];

		for (int x = 0; x < getWidth(); x++) {
			for (int y = 0; y < getHeight(); y++) {
				for (int band = 0; band < image.getNumBands(); band++) {
					image.getSamples(x, y, kernel.getWidth(),
							kernel.getHeight(), band, temporalMatrix);
					float accumulated = accumulateTemporal(maskValues,
							temporalMatrix);

					if (isMaximum(maximum, accumulated)) {
						maximum = accumulated;
					}
					if (isMinimum(minimum, accumulated)) {
						minimum = accumulated;
					}
				}
			}
		}

		return new RegionExtremes(maximum, minimum);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public float accumulateTemporal(float[] maskValues, float[] temporalMatrix) {
		float accumulated = 0;
		for (int i = 0; i < temporalMatrix.length; i++) {
			accumulated += temporalMatrix[temporalMatrix.length - i - 1]
					* maskValues[i];
		}
		return accumulated;
	}

	private boolean isMinimum(float minimum, float accumulated) {
		return minimum > accumulated;
	}

	private boolean isMaximum(float maximum, float accumulated) {
		return maximum < accumulated;
	}

}