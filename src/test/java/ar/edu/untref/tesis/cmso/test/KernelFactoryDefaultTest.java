package ar.edu.untref.tesis.cmso.test;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ar.edu.untref.tesis.cmso.domain.factory.KernelFactoryDefault;

public class KernelFactoryDefaultTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void kernelBuildWithNullParamShouldThrownException() {
		exception.expect(IllegalArgumentException.class);
		new KernelFactoryDefault().build(null);
	}
	
}
