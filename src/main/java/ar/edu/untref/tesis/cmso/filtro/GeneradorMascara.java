package ar.edu.untref.tesis.cmso.filtro;

import ar.edu.untref.tesis.cmso.domain.FilterMask;

public interface GeneradorMascara {

	FilterMask generar(int sigma);
	
}