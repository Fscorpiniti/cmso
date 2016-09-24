package ar.edu.untref.tesis.cmso.domain;

import java.awt.image.Kernel;

public interface FabricaKernel {

	Kernel construir(FilterMask mascaraFiltro);
	
}
