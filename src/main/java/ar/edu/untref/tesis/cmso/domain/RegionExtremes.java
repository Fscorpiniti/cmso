package ar.edu.untref.tesis.cmso.domain;

public class RegionExtremes {

	private float maximum;
	private float minimum;
	
	public RegionExtremes(float maximum, float minimum) {
		this.maximum = maximum;
		this.minimum = minimum;
	}

	public float getMaximum() {
		return maximum;
	}

	public float getMinimum() {
		return minimum;
	}

}
