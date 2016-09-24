package ar.edu.untref.tesis.cmso.domain;

import java.awt.Point;

public class ScaleSpacePoint {

	private Point point;
	private OctaveImage octaveImage;
	
	public ScaleSpacePoint(Point point, OctaveImage octaveImage) {
		this.point = point;
		this.octaveImage = octaveImage;
	}

	public Point getPoint() {
		return point;
	}

	public OctaveImage getOctaveImage() {
		return octaveImage;
	}

}
