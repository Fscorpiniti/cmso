package ar.edu.untref.tesis.cmso.filtro;

import java.awt.image.BufferedImage;
import java.awt.image.Kernel;

import ar.edu.untref.tesis.cmso.domain.FabricaKernel;
import ar.edu.untref.tesis.cmso.domain.Imagen;
import ar.edu.untref.tesis.cmso.domain.MascaraFiltro;

public class FiltroGaussiano {

	private GeneradorMascara generadorMascara;
	
	public FiltroGaussiano(GeneradorMascara generadorMascara) {
		this.generadorMascara = generadorMascara;
	}
	
	public BufferedImage aplicar(Imagen imagen, int sigma) {
		BufferedImage clonada = imagen.clonarEsqueleto();
		MascaraFiltro mascaraFiltro = generadorMascara.generar(sigma);
		Kernel kernel = new FabricaKernel().construir(mascaraFiltro);
		new Filtro(kernel).filter(imagen.getImagenOriginal(), clonada);
		return clonada;
	}

}