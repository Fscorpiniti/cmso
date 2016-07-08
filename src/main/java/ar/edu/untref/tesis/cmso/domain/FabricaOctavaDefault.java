package ar.edu.untref.tesis.cmso.domain;

import java.util.Arrays;
import java.util.List;

import ar.edu.untref.tesis.cmso.filtro.Filtro;

public class FabricaOctavaDefault implements FabricaOctava {

	@Override
	public Octava construir(Imagen imagen, Double sigma,
			int cantidadDiferenciasGaussianas, Filtro filtro) {

		Integer cantidadPisos = cantidadDiferenciasGaussianas + 2;
		List<ImagenOctava> imagenesOctava = Arrays.asList(new ImagenOctava(
				imagen));

		double sigmaIteracion = sigma;
		for (int piso = 1; piso < cantidadPisos; piso++) {
			Imagen imagenPisoAnterior = imagenesOctava.get(piso - 1)
					.getImagen();

			Imagen imagenFiltrada = filtro.aplicar(imagenPisoAnterior,
					(int) sigmaIteracion);
			
			ImagenOctava imagenOctava = new ImagenOctava(imagenFiltrada);
			imagenOctava.setSigma(sigma);
			
			imagenesOctava.add(imagenOctava);

			sigmaIteracion = Math.pow(2, piso / cantidadDiferenciasGaussianas);
		}

		return new Octava(imagenesOctava);
	}

}