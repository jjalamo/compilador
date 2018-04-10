package compiler.traductor;

import es.uned.lsi.compiler.intermediate.QuadrupleIF;

public class Traductor_BR extends Traductor{

	protected void traducir(QuadrupleIF quadruple) {
		// CI - BR label			  
		//Salto incondicional, salta a label

		String operando1 = quadruple.getResult().toString();
		setInstruccion(String.format("%s /%s", "BR", operando1));
	}

}
