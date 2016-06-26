package ar.edu.untref.tesis.cmso.test;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ar.edu.untref.tesis.cmso.domain.FabricaKernelDefault;
import ar.edu.untref.tesis.cmso.domain.Imagen;
import ar.edu.untref.tesis.cmso.filtro.FiltroGaussiano;
import ar.edu.untref.tesis.cmso.filtro.GeneradorMascaraGaussiano;

public class FiltroGaussianoTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void crearFiltroGaussianoConGeneradorMascaraNullDeberiaLanzarExcepcion() {
		exception.expect(IllegalArgumentException.class);
		new FiltroGaussiano(null, 3, new FabricaKernelDefault());
	}
	
	@Test
	public void crearFiltroGaussianoConFabricaKernelNullDeberiaLanzarExcepcion() {
		exception.expect(IllegalArgumentException.class);
		new FiltroGaussiano(new GeneradorMascaraGaussiano(), 3, null);
	}
	
	@Test
	public void crearFiltroGaussianoConSigmaNegativoDeberiaLanzarExcepcion() {
		exception.expect(IllegalArgumentException.class);
		new FiltroGaussiano(new GeneradorMascaraGaussiano(), -1, new FabricaKernelDefault());
	}
	
	@Test
	public void aplicarFiltroGaussianoConImagenNullDeberiaLanzarExcepcion() {
		exception.expect(IllegalArgumentException.class);
		new FiltroGaussiano(new GeneradorMascaraGaussiano(), 3, new FabricaKernelDefault()).aplicar(null);
	}
	
	@Test
	public void aplicarFiltroGaussianoConBufferedImageNullDeberiaLanzarExcepcion() {
		exception.expect(IllegalArgumentException.class);
		new FiltroGaussiano(new GeneradorMascaraGaussiano(), 3, new FabricaKernelDefault()).aplicar(new Imagen(null));
	}

}