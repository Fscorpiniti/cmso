package ar.edu.untref.tesis.cmso.filtro;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.Kernel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;

import ar.edu.untref.tesis.cmso.domain.ExtremosRegion;
import ar.edu.untref.tesis.cmso.domain.FabricaKernel;
import ar.edu.untref.tesis.cmso.domain.Imagen;
import ar.edu.untref.tesis.cmso.domain.MascaraFiltro;
import ar.edu.untref.tesis.cmso.domain.RegionFiltro;

public class FiltroGaussiano implements Filtro {

	public static final int PIXELES_BORDE_EN_CERO = 0;
	private Kernel kernel;
	private MascaraFiltro mascaraFiltro;
	private GeneradorMascara generadorMascara;
	private FabricaKernel fabricaKernel;

	public FiltroGaussiano(GeneradorMascara generadorMascara, FabricaKernel fabricaKernel) {
		this.generadorMascara = generadorMascara;
		this.fabricaKernel = fabricaKernel;
		validarConstruccion();
	}

	@Override
	public Imagen aplicar(Imagen imagen, Double sigma) {
		validarParametrosObligatorios(sigma, imagen);
		mascaraFiltro = generadorMascara.generar(sigma.intValue());
		kernel = fabricaKernel.construir(mascaraFiltro);
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
		int[] valorMaximo = imagenInicial.getSampleModel().getSampleSize();
		for (int i = 0; i < valorMaximo.length; i++) {
			valorMaximo[i] = (int) Math.pow(2, valorMaximo[i]) - 1;
		}
		float[] valoresMascara = kernel.getKernelData(null);
		float[] matrizTemporal = new float[mascaraFiltro.getAncho()
				* mascaraFiltro.getAlto()];

		RegionFiltro regionFiltro = new RegionFiltro(kernel, imagenInicial);
		ExtremosRegion extremos = regionFiltro.calcularExtremos();

		for (int x = 0; x < regionFiltro.getAncho(); x++) {
			for (int y = 0; y < regionFiltro.getAlto(); y++) {
				for (int banda = 0; banda < imagenInicial.getNumBands(); banda++) {
					imagenInicial.getSamples(x, y, mascaraFiltro.getAncho(),
							mascaraFiltro.getAlto(), banda, matrizTemporal);
					float acumulado = regionFiltro.acumularTemporal(
							valoresMascara, matrizTemporal);

					float acumuladoActualizado = actualizarValorAcumulado(
							valorMaximo, extremos, banda, acumulado);

					actualizarImagenDestino(imagenDestino, x, y, banda,
							acumuladoActualizado);
				}
			}
		}

		return imagenDestino;
	}

	private float actualizarValorAcumulado(int[] valorMaximo,
			ExtremosRegion extremos, int banda, float acumulado) {
		return ((((float) valorMaximo[banda]) / (extremos.getMaximo() - extremos
				.getMinimo())) * acumulado)
				- ((extremos.getMinimo() * (float) valorMaximo[banda]) / (extremos
						.getMaximo() - extremos.getMinimo()));
	}

	private void actualizarImagenDestino(WritableRaster imagenDestino, int x,
			int y, int banda, float acumuladoActualizado) {
		imagenDestino.setSample(x + kernel.getXOrigin(),
				y + kernel.getYOrigin(), banda, acumuladoActualizado);
	}

	private void validarImagen(Imagen imagen) {
		if (imagen == null || imagen.getImagenOriginal() == null) {
			throw new IllegalArgumentException(
					"La imagen es necesaria para aplicarle el filtro gaussiano");
		}
	}

	private void validarParametrosObligatorios(Double sigma, Imagen imagen) {
		if (sigma < 0) {
			throw new IllegalArgumentException("El sigma debe ser mayor que 0");
		}
		validarImagen(imagen);
	}

	private void validarConstruccion() {
		if (generadorMascara == null || fabricaKernel == null) {
			throw new IllegalArgumentException(
					"Debe tener generador y fabrica kernel para poder aplicar el filtro gaussiano.");
		}
	}

}