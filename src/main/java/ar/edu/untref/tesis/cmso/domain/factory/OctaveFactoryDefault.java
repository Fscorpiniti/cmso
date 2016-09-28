package ar.edu.untref.tesis.cmso.domain.factory;

import java.util.Arrays;
import java.util.List;

import ar.edu.untref.tesis.cmso.domain.Image;
import ar.edu.untref.tesis.cmso.domain.Octave;
import ar.edu.untref.tesis.cmso.domain.OctaveImage;
import ar.edu.untref.tesis.cmso.filter.Filter;

public class OctaveFactoryDefault implements OctaveFactory {

	@Override
	public Octave build(Image image, Double sigma,
			int amountOfGaussianDifferences, Filter filter) {

		Integer numberOfFloors = amountOfGaussianDifferences + 2;
		Image originalFiltered = filter.apply(image, sigma);
		List<OctaveImage> octaveImages = Arrays.asList(new OctaveImage(
				originalFiltered, sigma));

		for (int floor = 1; floor < numberOfFloors; floor++) {
			Double sigmaIteration = sigma
					* Math.pow(2, floor / amountOfGaussianDifferences);

			int previousFloor = floor - 1;
			Image previousFloorImage = octaveImages.get(previousFloor)
					.getImage();

			Image filteredIterationImage = filter.apply(previousFloorImage,
					sigmaIteration);

			octaveImages.add(new OctaveImage(filteredIterationImage,
					sigmaIteration));
		}

		return new Octave(octaveImages);
	}

}