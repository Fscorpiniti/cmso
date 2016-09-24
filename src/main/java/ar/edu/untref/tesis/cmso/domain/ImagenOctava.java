package ar.edu.untref.tesis.cmso.domain;

public class ImagenOctava {

	private Imagen imagen;
	private Double sigma;

	public ImagenOctava(Imagen imagen, Double sigma) {
		this.imagen = imagen;
		this.sigma = sigma;
	}

	public Imagen getImagen() {
		return imagen;
	}

	public Double getSigma() {
		return sigma;
	}

	public Boolean esExtremo(int pixel, int fila, int columna, boolean esValido) {
		Boolean resultado = esValido;
		if (esValido) {
			resultado &= imagen.obtenerPunto(fila - 1, columna - 1) < pixel;
			resultado &= imagen.obtenerPunto(fila - 1, columna) < pixel;
			resultado &= imagen.obtenerPunto(fila - 1, columna + 1) < pixel;
			resultado &= imagen.obtenerPunto(fila, columna - 1) < pixel;
			resultado &= imagen.obtenerPunto(fila, columna) < pixel;
			resultado &= imagen.obtenerPunto(fila, columna + 1) < pixel;
			resultado &= imagen.obtenerPunto(fila + 1, columna - 1) < pixel;
			resultado &= imagen.obtenerPunto(fila + 1, columna) < pixel;
			resultado &= imagen.obtenerPunto(fila + 1, columna + 1) < pixel;
		}
		return resultado;
	}

}
