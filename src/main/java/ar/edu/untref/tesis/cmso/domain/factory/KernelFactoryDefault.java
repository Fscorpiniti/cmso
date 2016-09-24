package ar.edu.untref.tesis.cmso.domain.factory;

import java.awt.image.Kernel;

import ar.edu.untref.tesis.cmso.domain.FilterMask;

public class KernelFactoryDefault implements KernelFactory {

	@Override
	public Kernel build(FilterMask filterMask) {
		validateFilterMask(filterMask);
		int width = filterMask.getWidth();
		int height = filterMask.getHeight();
		float kernelFilter[] = new float[width * height];

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				kernelFilter[i * width + j] = filterMask.getMask()[i][j];
			}
		}

		return new Kernel(width, height, kernelFilter);
	}

	private void validateFilterMask(FilterMask filterMask) {
		if (filterMask == null) {
			throw new IllegalArgumentException(
					"La mascara del filtro es un dato obligatorio para construir un kernel");
		}
	}

}
