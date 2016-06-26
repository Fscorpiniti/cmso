package ar.edu.untref.tesis.cmso.filtro;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.Kernel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;

import ar.edu.untref.tesis.cmso.domain.FabricaKernel;
import ar.edu.untref.tesis.cmso.domain.Imagen;
import ar.edu.untref.tesis.cmso.domain.MascaraFiltro;

public class FiltroGaussiano implements Filtro {

	public static final int PIXELES_BORDE_EN_CERO = 0;
	private Kernel kernel;
	private MascaraFiltro mascaraFiltro;

	public FiltroGaussiano(GeneradorMascara generadorMascara, int sigma,
			FabricaKernel fabricaKernel) {
		mascaraFiltro = generadorMascara.generar(sigma);
		kernel = fabricaKernel.construir(mascaraFiltro);
	}

	@Override
	public Imagen aplicar(Imagen imagen) {
		BufferedImage clonada = imagen.clonarEsqueleto();
		filtrar(imagen.getImagenOriginal(), clonada);
		return new Imagen(clonada);
	}

	private final BufferedImage filtrar(BufferedImage imagenOriginal,
			BufferedImage imagenDestino) {
		BufferedImage copiaOriginal = imagenOriginal;
		BufferedImage copiaDestino = imagenDestino;
		copiaDestino = crearImagenDestinoCompatible(imagenOriginal,
				imagenDestino, copiaOriginal, copiaDestino);

		filtrar(copiaOriginal.getRaster(), copiaDestino.getRaster());

		return imagenDestino;
	}

	private BufferedImage crearImagenDestinoCompatible(
			BufferedImage imagenOriginal, BufferedImage imagenDestino,
			BufferedImage copiaOriginal, BufferedImage copiaDestino) {
		if (copiaOriginal.getColorModel().getColorSpace().getType() != imagenDestino
				.getColorModel().getColorSpace().getType()) {
			copiaDestino = crearImagenCompatible(imagenOriginal,
					imagenOriginal.getColorModel());
		}
		return copiaDestino;
	}

	private BufferedImage crearImagenCompatible(BufferedImage imagen,
			ColorModel modeloColor) {
		if (modeloColor != null) {
			return new BufferedImage(modeloColor, imagen.getRaster()
					.createCompatibleWritableRaster(),
					imagen.isAlphaPremultiplied(), null);
		}
		return new BufferedImage(imagen.getWidth(), imagen.getHeight(),
				imagen.getType());
	}

	private final WritableRaster filtrar(Raster imagenInicial,
			WritableRaster imagenDestino) {

		int izquierda = kernel.getXOrigin();
		int derecha = Math.max(mascaraFiltro.getAncho() - izquierda - 1, 0);
		int arriba = kernel.getYOrigin();
		int abajo = Math.max(mascaraFiltro.getAlto() - arriba - 1, 0);

		int[] valorMaximo = imagenInicial.getSampleModel().getSampleSize();
		for (int i = 0; i < valorMaximo.length; i++) {
			valorMaximo[i] = (int) Math.pow(2, valorMaximo[i]) - 1;
		}

		int anchoDeLaRegionAlcanzable = imagenInicial.getWidth() - izquierda
				- derecha;
		int altoDeLaRegionAlcanzable = imagenInicial.getHeight() - arriba
				- abajo;
		float[] valoresDeLaMascara = kernel.getKernelData(null);
		float[] matrizTemporal = new float[mascaraFiltro.getAncho()
				* mascaraFiltro.getAlto()];

		float[] maximosYMinimos = calcularMaximosYMinimos(imagenInicial,
				imagenDestino);
		float minimo = maximosYMinimos[0];
		float maximo = maximosYMinimos[1];

		for (int x = 0; x < anchoDeLaRegionAlcanzable; x++) {
			for (int y = 0; y < altoDeLaRegionAlcanzable; y++) {

				for (int banda = 0; banda < imagenInicial.getNumBands(); banda++) {
					float v = 0;
					imagenInicial.getSamples(x, y, mascaraFiltro.getAncho(),
							mascaraFiltro.getAlto(), banda, matrizTemporal);
					for (int i = 0; i < matrizTemporal.length; i++)
						v += matrizTemporal[matrizTemporal.length - i - 1]
								* valoresDeLaMascara[i];

					float vTransformado = ((((float) valorMaximo[banda]) / (maximo - minimo)) * v)
							- ((minimo * (float) valorMaximo[banda]) / (maximo - minimo));

					imagenDestino.setSample(x + kernel.getXOrigin(),
							y + kernel.getYOrigin(), banda, vTransformado);

				}
			}
		}

		return imagenDestino;
	}

	public WritableRaster createCompatibleDestRaster(Raster src) {
		return src.createCompatibleWritableRaster();
	}

	public final Rectangle2D getBounds2D(BufferedImage src) {
		return src.getRaster().getBounds();
	}

	public final Rectangle2D getBounds2D(Raster src) {
		return src.getBounds();
	}

	public final Point2D getPoint2D(Point2D src, Point2D dst) {
		if (dst == null)
			return (Point2D) src.clone();
		dst.setLocation(src);
		return dst;
	}

	private float[] calcularMaximosYMinimos(Raster imagenInicial,
			WritableRaster imagenDestino) {

		int anchoMascara = kernel.getWidth();
		int alturaMascara = kernel.getHeight();
		int izquierda = kernel.getXOrigin();
		int derecha = Math.max(anchoMascara - izquierda - 1, 0);
		int arriba = kernel.getYOrigin();
		int abajo = Math.max(alturaMascara - arriba - 1, 0);

		float maximo = 0;
		float minimo = 0;

		int anchoDeLaRegionAlcanzable = imagenInicial.getWidth() - izquierda
				- derecha;
		int altoDeLaRegionAlcanzable = imagenInicial.getHeight() - arriba
				- abajo;
		float[] valoresDeLaMascara = kernel.getKernelData(null);
		float[] matrizTemporal = new float[anchoMascara * alturaMascara];

		for (int x = 0; x < anchoDeLaRegionAlcanzable; x++) {
			for (int y = 0; y < altoDeLaRegionAlcanzable; y++) {

				for (int banda = 0; banda < imagenInicial.getNumBands(); banda++) {
					float v = 0;
					imagenInicial.getSamples(x, y, anchoMascara, alturaMascara,
							banda, matrizTemporal);
					for (int i = 0; i < matrizTemporal.length; i++)
						v += matrizTemporal[matrizTemporal.length - i - 1]
								* valoresDeLaMascara[i];

					if (maximo < v) {
						maximo = v;
					}

					if (minimo > v) {
						minimo = v;
					}
				}
			}
		}
		float[] maximosYMinimos = new float[2];
		maximosYMinimos[0] = minimo;
		maximosYMinimos[1] = maximo;
		return maximosYMinimos;
	}

}