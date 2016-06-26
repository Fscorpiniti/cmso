package ar.edu.untref.tesis.cmso.domain;

import java.awt.image.Kernel;

public class FabricaKernel {

	public Kernel construir(MascaraFiltro mascaraFiltro) {
		int ancho = mascaraFiltro.getAncho();
		int alto = mascaraFiltro.getAlto();
		float filtroKernel[] = new float[ancho * alto];

		for (int i = 0; i < ancho; i++) {
			for (int j = 0; j < alto; j++) {
				filtroKernel[i * ancho + j] = mascaraFiltro.getMascara()[i][j];
			}
		}

		return new Kernel(ancho, alto, filtroKernel);
	}
	
}
