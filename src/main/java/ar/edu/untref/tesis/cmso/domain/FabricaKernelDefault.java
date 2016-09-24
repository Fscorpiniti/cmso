package ar.edu.untref.tesis.cmso.domain;

import java.awt.image.Kernel;

public class FabricaKernelDefault implements FabricaKernel {

	@Override
	public Kernel construir(FilterMask mascaraFiltro) {
		validarMascaraFiltro(mascaraFiltro);
		int ancho = mascaraFiltro.getWidth();
		int alto = mascaraFiltro.getHeight();
		float filtroKernel[] = new float[ancho * alto];

		for (int i = 0; i < ancho; i++) {
			for (int j = 0; j < alto; j++) {
				filtroKernel[i * ancho + j] = mascaraFiltro.getMask()[i][j];
			}
		}

		return new Kernel(ancho, alto, filtroKernel);
	}

	private void validarMascaraFiltro(FilterMask mascaraFiltro) {
		if (mascaraFiltro == null) {
			throw new IllegalArgumentException(
					"La mascara del filtro es un dato obligatorio para construir un kernel");
		}
	}

}
