package ar.edu.untref.tesis.cmso.domain;

public class ImagenOctava {

	private Imagen imagen;
	private Double sigma;
	
	public ImagenOctava(Imagen imagen) {
		this.imagen = imagen;
	}
	
	public Imagen getImagen() {
		return imagen;
	}

	public Double getSigma() {
		return sigma;
	}

	public void setSigma(Double sigma) {
		this.sigma = sigma;
	}
	
}
