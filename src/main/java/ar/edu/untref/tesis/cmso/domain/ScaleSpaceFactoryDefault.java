package ar.edu.untref.tesis.cmso.domain;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import ar.edu.untref.tesis.cmso.filtro.Filter;

public class ScaleSpaceFactoryDefault implements ScaleSpaceFactory {

	@Override
	public ScaleSpace construir(Filter filtro, Image imagen, Double sigma,
			int cantidadDiferenciasGaussianas) {
		OctaveFactory fabricaOctava = new OctaveFactoryDefault();
		List<Octave> octavas = new ArrayList<>();
		BufferedImage imagenIteracion = imagen.getImagenOriginal();

		while (imagenIteracion.getWidth() > 0
				&& imagenIteracion.getHeight() > 0) {
			Octave octava = fabricaOctava.construir(imagen, sigma,
					cantidadDiferenciasGaussianas, filtro);
			octavas.add(octava);

			imagenIteracion = this.subMuestrear(octava.getImagenesOctava()
					.get(cantidadDiferenciasGaussianas).getImagen());
		}

		return new ScaleSpace(octavas);
	}

	private BufferedImage subMuestrear(Image imagen) {
		int widthSubMuestreado = (imagen.getWidth() + 1) / 2;
		int heightSubMuestreado = (imagen.getHeight() + 1) / 2;

		Image subMuestreada = new Image(new BufferedImage(widthSubMuestreado,
				heightSubMuestreado, imagen.getType()));

		for (int fila = 0; fila < widthSubMuestreado; fila++) {
			for (int columna = 0; columna < heightSubMuestreado; columna++) {
				int filaSubMuestreada = fila * 2;
				int columnaSubMuestreada = columna * 2;
				subMuestreada.setearPunto(fila, columna, imagen.obtenerPunto(filaSubMuestreada, columnaSubMuestreada));
			}
		}

		return subMuestreada.getImagenOriginal();
	}

}
