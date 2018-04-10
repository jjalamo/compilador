package compiler.traductor;

import compiler.intermediate.Temporal;
import es.uned.lsi.compiler.intermediate.QuadrupleIF;

public class Traductor_PARAM extends Traductor {

	protected void traducir(QuadrupleIF quadruple) {
		// Introduce un parametro de un subprograma en la pila, durante la llamada al subprograma
		// Realiza el paso de parametros.
		
		Temporal operando1 = (Temporal)quadruple.getResult();
		setInstruccion(String.format("%s #-%s[.IX]", "PUSH", operando1.getAddress()));
	}

}
