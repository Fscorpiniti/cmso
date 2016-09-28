package ar.edu.untref.tesis.cmso.filter;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.Kernel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;

import ar.edu.untref.tesis.cmso.domain.FilterMask;
import ar.edu.untref.tesis.cmso.domain.FilterRegion;
import ar.edu.untref.tesis.cmso.domain.Image;
import ar.edu.untref.tesis.cmso.domain.RegionExtremes;
import ar.edu.untref.tesis.cmso.domain.factory.KernelFactory;

public class GaussianFilter implements Filter {

	public static final int EDGE_PIXELS = 0;
	private Kernel kernel;
	private FilterMask filterMask;
	private MaskGenerator maskGenerator;
	private KernelFactory kernelFactory;

	public GaussianFilter(MaskGenerator maskGenerator,
			KernelFactory kernelFactory) {
		this.maskGenerator = maskGenerator;
		this.kernelFactory = kernelFactory;
		validate();
	}

	@Override
	public Image apply(Image image, Double sigma) {
		validateParams(sigma, image);
		filterMask = maskGenerator.generate(sigma.intValue());
		kernel = kernelFactory.build(filterMask);
		BufferedImage cloned = image.cloneSkeleton();
		filter(image.getOriginalImage(), cloned);
		return new Image(cloned);
	}

	private final BufferedImage filter(BufferedImage originalImage,
			BufferedImage destinationImage) {
		BufferedImage originalCopy = originalImage;
		BufferedImage destinationCopy = destinationImage;
		destinationCopy = buildDestinationImage(originalImage,
				destinationImage, originalCopy, destinationCopy);

		filter(originalCopy.getRaster(), destinationCopy.getRaster());

		return destinationImage;
	}

	private BufferedImage buildDestinationImage(BufferedImage originalImage,
			BufferedImage destinationImage, BufferedImage originalCopy,
			BufferedImage destinationCopy) {
		if (originalCopy.getColorModel().getColorSpace().getType() != destinationImage
				.getColorModel().getColorSpace().getType()) {
			destinationCopy = buildImage(originalImage,
					originalImage.getColorModel());
		}
		return destinationCopy;
	}

	private BufferedImage buildImage(BufferedImage image, ColorModel colorModel) {
		if (colorModel != null) {
			return new BufferedImage(colorModel, image.getRaster()
					.createCompatibleWritableRaster(),
					image.isAlphaPremultiplied(), null);
		}
		return new BufferedImage(image.getWidth(), image.getHeight(),
				image.getType());
	}

	private final WritableRaster filter(Raster initialImage,
			WritableRaster destinationImage) {
		int[] maxValue = initialImage.getSampleModel().getSampleSize();
		for (int i = 0; i < maxValue.length; i++) {
			maxValue[i] = (int) Math.pow(2, maxValue[i]) - 1;
		}
		float[] maskValues = kernel.getKernelData(null);
		float[] temporalMatrix = new float[filterMask.getWidth()
				* filterMask.getHeight()];

		FilterRegion filterRegion = new FilterRegion(kernel, initialImage);
		RegionExtremes extremes = filterRegion.calculateExtremes();

		for (int x = 0; x < filterRegion.getWidth(); x++) {
			for (int y = 0; y < filterRegion.getHeight(); y++) {
				for (int band = 0; band < initialImage.getNumBands(); band++) {
					initialImage.getSamples(x, y, filterMask.getWidth(),
							filterMask.getHeight(), band, temporalMatrix);
					float accumulated = filterRegion.accumulateTemporal(
							maskValues, temporalMatrix);

					float cumulativeUpdated = updateCumulativeValue(maxValue,
							extremes, band, accumulated);

					updateDestinationImage(destinationImage, x, y, band,
							cumulativeUpdated);
				}
			}
		}

		return destinationImage;
	}

	private float updateCumulativeValue(int[] maxValue,
			RegionExtremes extremes, int band, float accumulated) {
		return ((((float) maxValue[band]) / (extremes.getMaximum() - extremes
				.getMinimum())) * accumulated)
				- ((extremes.getMinimum() * (float) maxValue[band]) / (extremes
						.getMaximum() - extremes.getMinimum()));
	}

	private void updateDestinationImage(WritableRaster destinationImage, int x,
			int y, int band, float cumulativeUpdated) {
		destinationImage.setSample(x + kernel.getXOrigin(),
				y + kernel.getYOrigin(), band, cumulativeUpdated);
	}

	private void validateImage(Image image) {
		if (image == null || image.getOriginalImage() == null) {
			throw new IllegalArgumentException(
					"La imagen es necesaria para aplicarle el filtro gaussiano");
		}
	}

	private void validateParams(Double sigma, Image image) {
		if (sigma < 0) {
			throw new IllegalArgumentException("El sigma debe ser mayor que 0");
		}
		validateImage(image);
	}

	private void validate() {
		if (maskGenerator == null || kernelFactory == null) {
			throw new IllegalArgumentException(
					"Debe tener generador y fabrica kernel para poder aplicar el filtro gaussiano.");
		}
	}

}