package compiler.traductor;

import compiler.intermediate.Temporal;
import compiler.intermediate.Value;
import es.uned.lsi.compiler.intermediate.OperandIF;
import es.uned.lsi.compiler.intermediate.QuadrupleIF;

public class Traductor_WRITE extends Traductor {

		protected void traducir(QuadrupleIF quadruple) {
			OperandIF result = quadruple.getResult();
			if(result instanceof Value) {
				// CI - WRITE text  
				Value v = (Value) result;
				
				if(v.getValue() instanceof String) {
					String label = v.getValue().toString();
					setInstruccion("WRSTR /" + label);
				}
			} else {
				// CI - WRITE temporal   
				Temporal operando1 = (Temporal)quadruple.getResult();
				setInstruccion(String.format("%s #-%s[.IX]", "WRINT", operando1.getAddress()));	
			}
		}

}
