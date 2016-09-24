package ar.edu.untref.tesis.cmso.domain;

import java.util.Arrays;
import java.util.List;

import ar.edu.untref.tesis.cmso.filtro.Filter;

public class OctaveFactoryDefault implements OctaveFactory {

	@Override
	public Octave construir(Image imagen, Double sigma,
			int cantidadDiferenciasGaussianas, Filter filtro) {

		Integer cantidadPisos = cantidadDiferenciasGaussianas + 2;
		Image originalFiltrada = filtro.apply(imagen, sigma);
		List<OctaveImage> imagenesOctava = Arrays.asList(new OctaveImage(
				originalFiltrada, sigma));

		for (int piso = 1; piso < cantidadPisos; piso++) {
			Double sigmaIteracion = sigma
					* Math.pow(2, piso / cantidadDiferenciasGaussianas);

			int pisoAnterior = piso - 1;
			Image imagenPisoAnterior = imagenesOctava.get(pisoAnterior)
					.getImagen();

			Image imagenIteracionFiltrada = filtro.apply(imagenPisoAnterior,
					sigmaIteracion);

			imagenesOctava.add(new OctaveImage(imagenIteracionFiltrada,
					sigmaIteracion));
		}

		return new Octave(imagenesOctava);
	}

}