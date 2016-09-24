package ar.edu.untref.tesis.cmso.domain.factory;

import java.awt.image.Kernel;

import ar.edu.untref.tesis.cmso.domain.FilterMask;

public interface KernelFactory {

	Kernel build(FilterMask filterMask);
	
}
