package ar.edu.untref.tesis.cmso.domain;

import ar.edu.untref.tesis.cmso.filtro.Filtro;

public interface FabricaEspacioEscala {

	EspacioEscala construir(Filtro filtro, Imagen imagen, Double sigma,
			int cantidadDiferenciasGaussianas);

}
