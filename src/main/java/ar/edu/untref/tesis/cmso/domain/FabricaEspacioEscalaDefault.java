package ar.edu.untref.tesis.cmso.domain;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import ar.edu.untref.tesis.cmso.filtro.Filtro;

public class FabricaEspacioEscalaDefault implements FabricaEspacioEscala {

	@Override
	public EspacioEscala construir(Filtro filtro, Imagen imagen, Double sigma,
			int cantidadDiferenciasGaussianas) {
		FabricaOctava fabricaOctava = new FabricaOctavaDefault();
		List<Octava> octavas = new ArrayList<>();
		BufferedImage imagenIteracion = imagen.getImagenOriginal();

		while (imagenIteracion.getWidth() > 0
				&& imagenIteracion.getHeight() > 0) {
			Octava octava = fabricaOctava.construir(imagen, sigma,
					cantidadDiferenciasGaussianas, filtro);
			octavas.add(octava);

			imagenIteracion = this.subMuestrear(octava.getImagenesOctava()
					.get(cantidadDiferenciasGaussianas).getImagen());
		}

		return new EspacioEscala(octavas);
	}

	private BufferedImage subMuestrear(Imagen imagen) {
		int widthSubMuestreado = (imagen.getWidth() + 1) / 2;
		int heightSubMuestreado = (imagen.getHeight() + 1) / 2;

		Imagen subMuestreada = new Imagen(new BufferedImage(widthSubMuestreado,
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
