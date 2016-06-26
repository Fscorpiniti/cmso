package ar.edu.untref.tesis.cmso.test;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ar.edu.untref.tesis.cmso.domain.FabricaKernelDefault;

public class FabricaKernelDefaultTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void crearFabricaKernelDefaultConMascaraFiltroNullDeberiaLanzarExcepcion() {
		exception.expect(IllegalArgumentException.class);
		new FabricaKernelDefault().construir(null);
	}
	
}
