package ar.edu.untref.tesis.cmso.domain;

import ar.edu.untref.tesis.cmso.filtro.Filter;

public interface ScaleSpaceFactory {

	ScaleSpace construir(Filter filtro, Image imagen, Double sigma,
			int cantidadDiferenciasGaussianas);

}
