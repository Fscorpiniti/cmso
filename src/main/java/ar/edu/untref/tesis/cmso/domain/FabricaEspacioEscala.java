package ar.edu.untref.tesis.cmso.domain;

import ar.edu.untref.tesis.cmso.filtro.Filter;

public interface FabricaEspacioEscala {

	EspacioEscala construir(Filter filtro, Imagen imagen, Double sigma,
			int cantidadDiferenciasGaussianas);

}
