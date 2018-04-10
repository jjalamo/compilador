package compiler.traductor;

import compiler.intermediate.Temporal;
import compiler.intermediate.Value;
import es.uned.lsi.compiler.intermediate.QuadrupleIF;

public class Traductor_STP extends Traductor {

	protected void traducir(QuadrupleIF quadruple) {

		Temporal result = (Temporal)quadruple.getResult();
		
		//Almacena en .R0 la direccion de referencia
		setInstruccion(String.format("%s #-%s[.IX], .R0", "MOVE", result.getAddress()));
		
		if(quadruple.getFirstOperand() == null) {
			//Se trata del valor de retorno de una funcion. se almacena el valor de retorno
			//del RA activo en la direccion de referencia
			setInstruccion(String.format("%s #%s[.IX], [.R0]",  "MOVE", OFFSET_RETORNO));
		} else {
			if(quadruple.getFirstOperand() instanceof Value) {
				//Se pone el primer bloque de memoria a 0. Alamcena la direccion de la
				//referencia en R0
				setInstruccion(String.format("%s #0, [.R0]", "MOVE"));
			} else {
				Temporal operando1 = (Temporal)quadruple.getFirstOperand();
				
				if(quadruple.getSecondOperand() == null) {
					//Almacena el valor del temporal en la direccion de referencia
					setInstruccion(String.format("%s #-%s[.IX], [.R0]", "MOVE", operando1.getAddress()));
				}
			}
		}
	}
	

}
