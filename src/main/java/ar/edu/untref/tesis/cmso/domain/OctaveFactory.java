package ar.edu.untref.tesis.cmso.domain;

import ar.edu.untref.tesis.cmso.filtro.Filter;

public interface OctaveFactory {

	Octave construir(Image imagen, Double sigma,
			int cantidadDiferenciasGaussianas, Filter filtro);

}
