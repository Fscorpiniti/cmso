package ar.edu.untref.tesis.cmso.domain;

import ar.edu.untref.tesis.cmso.filtro.Filtro;

public interface FabricaOctava {

	Octava construir(Imagen imagen, Double sigma,
			int cantidadDiferenciasGaussianas, Filtro filtro);

}
