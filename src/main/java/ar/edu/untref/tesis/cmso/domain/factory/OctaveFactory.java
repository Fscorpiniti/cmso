package ar.edu.untref.tesis.cmso.domain.factory;

import ar.edu.untref.tesis.cmso.domain.Image;
import ar.edu.untref.tesis.cmso.domain.Octave;
import ar.edu.untref.tesis.cmso.filtro.Filter;

public interface OctaveFactory {

	Octave build(Image image, Double sigma,
			int amountOfGaussianDifferences, Filter filter);

}