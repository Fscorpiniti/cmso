package ar.edu.untref.tesis.cmso.domain;

import java.awt.image.Kernel;

public interface KernelFactory {

	Kernel build(FilterMask filterMask);
	
}
