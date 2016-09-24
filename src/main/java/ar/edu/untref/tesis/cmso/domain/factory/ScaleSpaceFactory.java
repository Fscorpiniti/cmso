package ar.edu.untref.tesis.cmso.domain.factory;

import ar.edu.untref.tesis.cmso.domain.Image;
import ar.edu.untref.tesis.cmso.domain.ScaleSpace;
import ar.edu.untref.tesis.cmso.filtro.Filter;

public interface ScaleSpaceFactory {

	ScaleSpace build(Filter filter, Image image, Double sigma,
			int amountOfGaussianDifferences);

}
