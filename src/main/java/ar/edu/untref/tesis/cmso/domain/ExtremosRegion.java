package ar.edu.untref.tesis.cmso.domain;

public class ExtremosRegion {

	private float maximo;
	private float minimo;
	
	public ExtremosRegion(float maximo, float minimo) {
		this.maximo = maximo;
		this.minimo = minimo;
	}
	
	public float getMaximo() {
		return maximo;
	}

	public float getMinimo() {
		return minimo;
	}
	
}
