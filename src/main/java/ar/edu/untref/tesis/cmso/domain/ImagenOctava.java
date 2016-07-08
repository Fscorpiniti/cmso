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

}
