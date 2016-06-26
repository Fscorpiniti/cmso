package ar.edu.untref.tesis.cmso.domain;

public class MascaraFiltro {

	private float[][] mascara;

	public MascaraFiltro(float[][] mascara) {
		this.mascara = mascara;
	}

	public float[][] getMascara() {
		return mascara;
	}
	
	public int getAncho() {
		return mascara.length;
	}

	public int getAlto() {
		return mascara[0].length;
	}
	
}