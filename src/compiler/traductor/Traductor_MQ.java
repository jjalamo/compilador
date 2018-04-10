package compiler.traductor;

import compiler.intermediate.Temporal;
import es.uned.lsi.compiler.intermediate.LabelFactory;
import es.uned.lsi.compiler.intermediate.LabelIF;
import es.uned.lsi.compiler.intermediate.QuadrupleIF;

public class Traductor_MQ extends Traductor {

	protected void traducir(QuadrupleIF quadruple) {
		// CI - MQ destino, operando1, operando2
		//			CF -	CMP #[.IX], #[.IX]
		//					BZ /label2
		//				 	BN /label2
		//					MOVE #1, #[.IX]
		//					BR /label1
		//					label2:
		//					MOVE #0, #[.IX]
		//					label1:

		// compara el operando1 y el operando2, almacena en destino 1, si operando1 es mayor
		//que operando2 y 0 en caso contrario
		
		LabelFactory lF = new LabelFactory();
		LabelIF l1 = lF.create();
		LabelIF l2 = lF.create();
		
		Temporal operando1 = (Temporal)quadruple.getFirstOperand();
		Temporal operando2 = (Temporal)quadruple.getSecondOperand();
		
		setInstruccion(String.format("%s #-%s[.IX], #-%s[.IX]", "CMP", operando1.getAddress(), operando2.getAddress()));
		setInstruccion(String.format("%s%s", "BZ /", l2));
		setInstruccion(String.format("%s%s", "BN /", l2));
		
		operando1 = (Temporal)quadruple.getResult();
		setInstruccion(String.format("%s #1, #-%s[.IX]", "MOVE", operando1.getAddress()));
		setInstruccion(String.format("%s%s", "BR /", l1));
		setInstruccion(l2 + ": ");
		setInstruccion(String.format("%s #0, #-%s[.IX]", "MOVE", operando1.getAddress()));
		setInstruccion(l1 + ": ");
	}
}
