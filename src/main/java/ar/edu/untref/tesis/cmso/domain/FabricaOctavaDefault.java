package ar.edu.untref.tesis.cmso.domain;

import java.util.Arrays;
import java.util.List;

import ar.edu.untref.tesis.cmso.filtro.Filtro;

public class FabricaOctavaDefault implements FabricaOctava {

	@Override
	public Octava construir(Imagen imagen, Double sigma,
			int cantidadDiferenciasGaussianas, Filtro filtro) {

		Integer cantidadPisos = cantidadDiferenciasGaussianas + 2;
		Imagen originalFiltrada = filtro.aplicar(imagen, sigma);
		List<ImagenOctava> imagenesOctava = Arrays.asList(new ImagenOctava(
				originalFiltrada, sigma));

		for (int piso = 1; piso < cantidadPisos; piso++) {
			Double sigmaIteracion = sigma
					* Math.pow(2, piso / cantidadDiferenciasGaussianas);

			int pisoAnterior = piso - 1;
			Imagen imagenPisoAnterior = imagenesOctava.get(pisoAnterior)
					.getImagen();

			Imagen imagenIteracionFiltrada = filtro.aplicar(imagenPisoAnterior,
					sigmaIteracion);

			imagenesOctava.add(new ImagenOctava(imagenIteracionFiltrada,
					sigmaIteracion));
		}

		return new Octava(imagenesOctava);
	}

}