package ar.edu.untref.tesis.cmso.domain;

import java.util.ArrayList;
import java.util.List;

public class EspacioEscala {

	private List<Octava> octavas = new ArrayList<>();
	
	public EspacioEscala (List<Octava>octavas) {
		this.octavas = octavas;
	}

	public List<Octava> getOctavas() {
		return octavas;
	}

}
