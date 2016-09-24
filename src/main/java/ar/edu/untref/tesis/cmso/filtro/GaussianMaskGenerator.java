package ar.edu.untref.tesis.cmso.filtro;

import ar.edu.untref.tesis.cmso.domain.FilterMask;

public class GaussianMaskGenerator implements MaskGenerator {

	private int[] dimensions = { 3, 5, 9, 13, 15, 19 };

	@Override
	public FilterMask generate(int sigma) {
		int dimension = dimensions[sigma - 1];
		float[][] mask = initMask(dimension);
		this.completeMask(sigma, dimension, mask);
		return new FilterMask(mask);
	}

	private void completeMask(int sigma, int dimension, float[][] mask) {
		int center = dimension / 2;
		for (int j = 0; j < dimension; j++) {
			for (int i = 0; i < dimension; i++) {
				int x = i - center;
				int y = j - center;
				mask[i][j] = calculatedGaussianValue(sigma, x, y);
			}
		}
	}

	private float[][] initMask(int dimension) {
		return new float[dimension][dimension];
	}

	private float calculatedGaussianValue(int sigma, int x, int y) {
		int sigmaSquare = sigma * sigma;
		return (float) ((1 / (2 * Math.PI * sigmaSquare)) * Math.pow(Math.E,
				-(x * x + y * y) / (2 * sigmaSquare)));
	}

}