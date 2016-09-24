package ar.edu.untref.tesis.cmso.domain;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ExtremeDetector {

	private ScaleSpace scaleSpace;

	public ExtremeDetector(ScaleSpace scaleSpace) {
		this.scaleSpace = scaleSpace;
	}

	public List<ScaleSpacePoint> detect() {
		List<ScaleSpacePoint> points = new ArrayList<>();
		for (Octave octave : scaleSpace.getOctaves()) {
			List<OctaveImage> octaveImages = octave.getImagenesOctava();
			for (int i = 1; i < octaveImages.size() - 1; i++) {
				points.addAll(findExtreme(octaveImages, i));
			}
		}
		return points;
	}

	private Collection<? extends ScaleSpacePoint> findExtreme(
			List<OctaveImage> octaveImages, int centerOctavePosition) {

		Collection<ScaleSpacePoint> points = new ArrayList<>();
		OctaveImage top = octaveImages.get(centerOctavePosition + 1);
		OctaveImage center = octaveImages.get(centerOctavePosition);
		OctaveImage lower = octaveImages.get(centerOctavePosition - 1);

		for (int row = 1; row < center.getImagen().getHeight(); row++) {
			for (int column = 1; column < center.getImagen().getWidth(); column++) {
				int pixel = center.getImagen().obtenerPunto(row, column);
				Boolean isTopExtreme = top.esExtremo(pixel, row, column, true);
				Boolean isCenterExtreme = center.esExtremo(pixel, row, column,
						isTopExtreme);
				Boolean isLowerExtreme = lower.esExtremo(pixel, row, column,
						isCenterExtreme);

				if (isCenterExtreme && isLowerExtreme && isTopExtreme) {
					points.add(new ScaleSpacePoint(new Point(row, column),
							center));
				}
			}
		}

		return points;
	}

}