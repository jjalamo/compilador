package compiler.traductor;
import compiler.intermediate.Temporal;

import es.uned.lsi.compiler.intermediate.QuadrupleIF;

public class Traductor_RET extends Traductor {	
	
	protected void traducir(QuadrupleIF quadruple) 	{
		// Retorna de la ejecucion de un subprograma
		Temporal operando1 = (Temporal)quadruple.getResult();
		
		if(operando1 != null) {
			setInstruccion(String.format("%s #-%s[.IX], .R7", "MOVE", operando1.getAddress()));
		}
		setInstruccion("RET");
	}
}
