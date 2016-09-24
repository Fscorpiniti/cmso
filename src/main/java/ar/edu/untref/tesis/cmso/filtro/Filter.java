package ar.edu.untref.tesis.cmso.filtro;

import ar.edu.untref.tesis.cmso.domain.Imagen;

public interface Filter {

	Imagen apply(Imagen imagen, Double sigma);

}
