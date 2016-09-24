package ar.edu.untref.tesis.cmso.domain;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DetectorExtremo {

	private ScaleSpace espacioEscala;

	public DetectorExtremo(ScaleSpace espacioEscala) {
		this.espacioEscala = espacioEscala;
	}

	public List<ScaleSpacePoint> detectar() {
		List<ScaleSpacePoint> puntos = new ArrayList<>();
		for (Octave octava : espacioEscala.getOctaves()) {
			List<OctaveImage> imagenesOctava = octava.getImagenesOctava();
			for (int i = 1; i < imagenesOctava.size() - 1; i++) {
				puntos.addAll(buscarExtremos(imagenesOctava, i));
			}
		}

		return puntos;
	}

	private Collection<? extends ScaleSpacePoint> buscarExtremos(
			List<OctaveImage> imagenesOctava, int posicionOctavaCentral) {

		Collection<ScaleSpacePoint> puntos = new ArrayList<>();
		OctaveImage superior = imagenesOctava.get(posicionOctavaCentral + 1);
		OctaveImage central = imagenesOctava.get(posicionOctavaCentral);
		OctaveImage inferior = imagenesOctava.get(posicionOctavaCentral - 1);

		for (int fila = 1; fila < central.getImagen().getHeight(); fila++) {
			for (int columna = 1; columna < central.getImagen().getWidth(); columna++) {
				int pixel = central.getImagen().obtenerPunto(fila, columna);
				Boolean esExtremoSuperior = superior.esExtremo(pixel, fila,
						columna, true);
				Boolean esExtremoCentral = central.esExtremo(pixel, fila,
						columna, esExtremoSuperior);
				Boolean esExtremoInferior = inferior.esExtremo(pixel, fila,
						columna, esExtremoCentral);

				if (esExtremoCentral && esExtremoInferior && esExtremoSuperior) {
					puntos.add(new ScaleSpacePoint(new Point(fila, columna),
							central));
				}
			}
		}

		return puntos;
	}

}