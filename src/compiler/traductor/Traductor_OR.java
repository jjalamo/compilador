package compiler.traductor;

import compiler.intermediate.Temporal;
import es.uned.lsi.compiler.intermediate.QuadrupleIF;

public class Traductor_OR extends Traductor {
	protected void traducir(QuadrupleIF quadruple) {
		// CI - OR resultado, operando1, operando2  

		Temporal operando1 = (Temporal)quadruple.getFirstOperand();
		Temporal operando2 = (Temporal)quadruple.getSecondOperand();
		setInstruccion(String.format("%s #-%s[.IX], #-%s[.IX]", "OR", operando1.getAddress(),operando2.getAddress()));
		
		Temporal resultado = (Temporal)quadruple.getResult();
		setInstruccion(String.format("%s .A, #-%s[.IX]", "MOVE", resultado.getAddress()));
		
		
	}
}
