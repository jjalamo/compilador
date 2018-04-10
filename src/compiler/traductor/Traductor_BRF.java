package compiler.traductor;

import compiler.intermediate.Temporal;
import es.uned.lsi.compiler.intermediate.QuadrupleIF;

public class Traductor_BRF extends Traductor{

	protected void traducir(QuadrupleIF quadruple) {
		// CI - BRF temp, label			   -   CF - CMP 1, temp
		//                                     BNZ /label
		// si temp es distinto de 1 salta a label

		String operando1 = quadruple.getFirstOperand().toString();
		Temporal operando2 = (Temporal)quadruple.getResult();
		setInstruccion(String.format("%s #%s, #-%s[.IX]", "CMP", 1, operando2.getAddress()));
		setInstruccion(String.format("%s /%s", "BNZ", operando1));
	}

}
