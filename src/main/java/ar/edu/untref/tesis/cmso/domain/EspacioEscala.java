package ar.edu.untref.tesis.cmso.domain;

import java.util.ArrayList;
import java.util.List;

public class EspacioEscala {

	private List<Octave> octavas = new ArrayList<>();
	
	public EspacioEscala (List<Octave>octavas) {
		this.octavas = octavas;
	}

	public List<Octave> getOctavas() {
		return octavas;
	}

}
