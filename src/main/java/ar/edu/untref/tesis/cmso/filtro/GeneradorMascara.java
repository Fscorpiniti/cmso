package ar.edu.untref.tesis.cmso.filtro;

import ar.edu.untref.tesis.cmso.domain.MascaraFiltro;

public interface GeneradorMascara {

	MascaraFiltro generar(int sigma);
	
}