package ar.edu.untref.tesis.cmso.domain.factory;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import ar.edu.untref.tesis.cmso.domain.Image;
import ar.edu.untref.tesis.cmso.domain.Octave;
import ar.edu.untref.tesis.cmso.domain.ScaleSpace;
import ar.edu.untref.tesis.cmso.filtro.Filter;

public class ScaleSpaceFactoryDefault implements ScaleSpaceFactory {

	@Override
	public ScaleSpace build(Filter filter, Image image, Double sigma,
			int amountOfGaussianDifferences) {
		OctaveFactory octaveFactory = new OctaveFactoryDefault();
		List<Octave> octaves = new ArrayList<>();
		BufferedImage iterationImage = image.getOriginalImage();

		while (iterationImage.getWidth() > 0 && iterationImage.getHeight() > 0) {
			Octave octave = octaveFactory.build(image, sigma,
					amountOfGaussianDifferences, filter);
			octaves.add(octave);

			iterationImage = this.subSampling(octave.getOctaveImages()
					.get(amountOfGaussianDifferences).getImage());
		}

		return new ScaleSpace(octaves);
	}

	private BufferedImage subSampling(Image image) {
		int widthSubSampled = (image.getWidth() + 1) / 2;
		int heightSubSampled = (image.getHeight() + 1) / 2;

		Image subSampled = new Image(new BufferedImage(widthSubSampled,
				heightSubSampled, image.getType()));

		for (int row = 0; row < widthSubSampled; row++) {
			for (int column = 0; column < heightSubSampled; column++) {
				int rowSubSampled = row * 2;
				int columnSubSampled = column * 2;
				subSampled.setPoint(row, column,
						image.getPoint(rowSubSampled, columnSubSampled));
			}
		}

		return subSampled.getOriginalImage();
	}

}
