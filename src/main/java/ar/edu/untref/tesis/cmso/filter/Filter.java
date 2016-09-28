package ar.edu.untref.tesis.cmso.filter;

import ar.edu.untref.tesis.cmso.domain.Image;

public interface Filter {

	Image apply(Image imagen, Double sigma);

}
