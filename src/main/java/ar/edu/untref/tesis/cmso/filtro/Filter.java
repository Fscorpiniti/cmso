package ar.edu.untref.tesis.cmso.filtro;

import ar.edu.untref.tesis.cmso.domain.Image;

public interface Filter {

	Image apply(Image imagen, Double sigma);

}
