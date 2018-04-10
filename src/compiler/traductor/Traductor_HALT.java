package compiler.traductor;

import es.uned.lsi.compiler.intermediate.QuadrupleIF;

public class Traductor_HALT extends Traductor {
	//fin de programa
	protected void traducir(QuadrupleIF quadruple) {
		setInstruccion("HALT");
	}

}
