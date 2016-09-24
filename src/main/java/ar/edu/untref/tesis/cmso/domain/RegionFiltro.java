package ar.edu.untref.tesis.cmso.domain;

import java.awt.image.Kernel;
import java.awt.image.Raster;

public class RegionFiltro {

	private int ancho;
	private int alto;
	private Kernel kernel;
	private Raster imagen;

	public RegionFiltro(Kernel kernel, Raster imagen) {
		int izquierda = kernel.getXOrigin();
		int derecha = Math.max(kernel.getWidth() - izquierda - 1, 0);
		int arriba = kernel.getYOrigin();
		int abajo = Math.max(kernel.getHeight() - arriba - 1, 0);
		this.kernel = kernel;
		this.imagen = imagen;
		this.ancho = imagen.getWidth() - izquierda - derecha;
		this.alto = imagen.getHeight() - arriba - abajo;
	}

	public RegionExtremes calcularExtremos() {
		float maximo = 0;
		float minimo = 0;
		float[] valoresMascara = kernel.getKernelData(null);
		float[] matrizTemporal = new float[kernel.getWidth()
				* kernel.getHeight()];

		for (int x = 0; x < getAncho(); x++) {
			for (int y = 0; y < getAlto(); y++) {
				for (int banda = 0; banda < imagen.getNumBands(); banda++) {
					imagen.getSamples(x, y, kernel.getWidth(),
							kernel.getHeight(), banda, matrizTemporal);
					float acumulado = acumularTemporal(valoresMascara,
							matrizTemporal);

					if (esMaximo(maximo, acumulado)) {
						maximo = acumulado;
					}
					if (esMinimo(minimo, acumulado)) {
						minimo = acumulado;
					}
				}
			}
		}

		return new RegionExtremes(maximo, minimo);
	}

	public int getAncho() {
		return ancho;
	}

	public int getAlto() {
		return alto;
	}

	public float acumularTemporal(float[] valoresMascara, float[] matrizTemporal) {
		float acumulado = 0;
		for (int i = 0; i < matrizTemporal.length; i++) {
			acumulado += matrizTemporal[matrizTemporal.length - i - 1]
					* valoresMascara[i];
		}
		return acumulado;
	}

	private boolean esMinimo(float minimo, float acumulado) {
		return minimo > acumulado;
	}

	private boolean esMaximo(float maximo, float acumulado) {
		return maximo < acumulado;
	}

}