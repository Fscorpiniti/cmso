package ar.edu.untref.tesis.cmso.domain;

import java.awt.Point;

public class ScaleSpacePoint {

	private Point point;
	private OctaveImage octaveImage;
	
	public ScaleSpacePoint(Point punto, OctaveImage octaveImage) {
		this.point = punto;
		this.octaveImage = octaveImage;
	}

	public Point getPoint() {
		return point;
	}

	public OctaveImage getOctaveImage() {
		return octaveImage;
	}

}
