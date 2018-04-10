package compiler.traductor;

import es.uned.lsi.compiler.intermediate.OperandIF;
import es.uned.lsi.compiler.intermediate.QuadrupleIF;

public class Traductor_MVA extends Traductor {

	//MVA operando, destino
	// almacena en destino la direccion de memoria del operando
	
	protected void traducir(QuadrupleIF quadruple) {

		OperandIF operando = quadruple.getFirstOperand();
		OperandIF destino = quadruple.getResult();
		
		int operandoAddress = getOperandoAddress(operando);
		int destinoAddress = getOperandoAddress(destino);
		
		int operando_VARIABLE_MEMORY_OFFSET = this.getVariableMemoryOffset(this.getOperandoScope(operando));
		
		if( operandoEsGlobal(operando) ) {
			setInstruccion(String.format("MOVE #%s, #-%s[.IX]", operandoAddress, destinoAddress));
		}
		
		if(operandoEsLocal(operando)) {
			if(operandoEsVariable(operando)) {
				setInstruccion(String.format("SUB .IX, #%s", operandoAddress + operando_VARIABLE_MEMORY_OFFSET));
				setInstruccion(String.format("MOVE .A, .R0"));
				setInstruccion(String.format("MOVE .R0, #-%s[.IX]", destinoAddress));
			}
			
			if(operandoEsParametro(operando)) {
				setInstruccion(String.format("MOVE #%s[.IX], #-%s[.IX]", operandoAddress + OFFSET_RETORNO +1, destinoAddress));
			}
			
			if(operandoEsVariableRetorno(operando)) {
				setInstruccion(String.format("MOVE #%s[.IX], #-%s[.IX]", operandoAddress + OFFSET_RETORNO, destinoAddress));
			}
		}
	}

}