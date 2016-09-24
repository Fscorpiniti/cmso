package ar.edu.untref.tesis.cmso.domain;

public class FilterMask {

	private float[][] mask;

	public FilterMask(float[][] mask) {
		this.mask = mask;
	}

	public int getWidth() {
		return getMask().length;
	}

	public int getHeight() {
		return getMask()[0].length;
	}

	public float[][] getMask() {
		return mask;
	}

}