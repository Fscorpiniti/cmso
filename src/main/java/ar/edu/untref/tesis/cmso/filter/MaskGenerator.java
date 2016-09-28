package ar.edu.untref.tesis.cmso.filter;

import ar.edu.untref.tesis.cmso.domain.FilterMask;

public interface MaskGenerator {

	FilterMask generate(int sigma);
	
}