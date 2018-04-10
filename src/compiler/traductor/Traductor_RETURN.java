package compiler.traductor;

import compiler.intermediate.Temporal;
import es.uned.lsi.compiler.intermediate.QuadrupleIF;

public class Traductor_RETURN extends Traductor{

	protected void traducir(QuadrupleIF quadruple) {
		// Devuelve el valor de retorno de una funcion
		
		Temporal operando1 = (Temporal)quadruple.getResult();
		int op1Address = operando1.getAddress();
		
		// copia el resultado de la funcion en la direccion de retorno de valor
		setInstruccion(String.format("%s .R7, #-%s[.IX]", "MOVE", op1Address));
	}

}
