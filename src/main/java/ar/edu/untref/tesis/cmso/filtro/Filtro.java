package ar.edu.untref.tesis.cmso.filtro;

import ar.edu.untref.tesis.cmso.domain.Imagen;

public interface Filtro {

	Imagen aplicar(Imagen imagen, Double sigma);

}
