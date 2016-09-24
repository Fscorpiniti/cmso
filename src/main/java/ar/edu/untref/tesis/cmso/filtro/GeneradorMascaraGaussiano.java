package ar.edu.untref.tesis.cmso.filtro;

import ar.edu.untref.tesis.cmso.domain.FilterMask;

public class GeneradorMascaraGaussiano implements GeneradorMascara {

	private int[] dimensiones = { 3, 5, 9, 13, 15, 19 };

	@Override
	public FilterMask generar(int sigma) {
		int dimension = dimensiones[sigma - 1];
		float[][] mascara = inicializarMascara(dimension);
		this.completarMascara(sigma, dimension, mascara);
		return new FilterMask(mascara);
	}

	private void completarMascara(int sigma, int dimension, float[][] mascara) {
		int centro = dimension / 2;
		for (int j = 0; j < dimension; j++) {
			for (int i = 0; i < dimension; i++) {
				int x = i - centro;
				int y = j - centro;
				mascara[i][j] = calcularValorGaussiano(sigma, x, y);
			}
		}
	}

	private float[][] inicializarMascara(int dimension) {
		return new float[dimension][dimension];
	}

	private float calcularValorGaussiano(int sigma, int x, int y) {
		int sigmaCuadrado = sigma * sigma;
		return (float) ((1 / (2 * Math.PI * sigmaCuadrado)) * Math.pow(Math.E,
				-(x * x + y * y) / (2 * sigmaCuadrado)));
	}

}