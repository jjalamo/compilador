package compiler.traductor;

import es.uned.lsi.compiler.intermediate.OperandIF;
import es.uned.lsi.compiler.intermediate.QuadrupleIF;
import es.uned.lsi.compiler.semantic.ScopeIF;

public class Traductor_MV extends Traductor{

	//  CI - MV destino, operando1  --> mueve operando1 al destino.
	//          destino = operando1
	protected void traducir(QuadrupleIF quadruple) {
	
	
		OperandIF operando = quadruple.getFirstOperand();
		OperandIF destino = quadruple.getResult();
		
		String operandoValor = null;
		int operandoAddress = 0;
		int destinoAddress = getOperandoAddress(destino);
		
		int operando_VARIABLE_MEMORY_OFFSET = 0;
		int destino_VARIABLE_MEMORY_OFFSET = this.getVariableMemoryOffset(this.getOperandoScope(destino));

		if(operandoEsValue(operando)) {
			operandoValor = getOperandoValue(operando);
		} else {
			operandoAddress = getOperandoAddress(operando);
			ScopeIF opScope = this.getOperandoScope(operando);
			operando_VARIABLE_MEMORY_OFFSET = this.getVariableMemoryOffset(opScope);
		}
		
		//Construimos el CF
		if( (operandoEsGlobal(operando, destino)) && (operandoEsVariable(operando)) && (operandoEsTemporal(destino)) ) {
			setInstruccion(String.format("MOVE /%s, #-%s[.IX]", operandoAddress, destinoAddress));
		}
		
		if( (operandoEsValue(operando)) && operandoEsTemporal(destino) ) {
			setInstruccion(String.format("MOVE #%s, #-%s[.IX]", operandoValor, destinoAddress));
		}
		
		if( (operandoEsGlobal(destino)) && (operandoEsTemporal(operando)) && (operandoEsVariable(destino)) ) {
			setInstruccion(String.format("MOVE #-%s[.IX], /%s", operandoAddress, destinoAddress));
		}
		
		if( (operandoEsTemporal(operando)) && (operandoEsTemporal(destino)) ) {
			setInstruccion(String.format("MOVE #-%s[.IX], #-%s[.IX]", operandoAddress, destinoAddress));
		}
		
		if( (operandoEsLocal(operando, destino)) && operandoEsLocal(destino) ) {
			
			if( (operandoEsVariable(operando)) && operandoEsTemporal(destino) ) {
				setInstruccion(String.format("SUB .IX, #%s", operandoAddress + operando_VARIABLE_MEMORY_OFFSET));
				setInstruccion(String.format("MOVE .A, .R0"));
				setInstruccion(String.format("MOVE [.R0], #-%s[.IX]", destinoAddress));
			}
			
			if( (operandoEsParametro(operando)) && (operandoEsTemporal(destino)) ) {
				setInstruccion(String.format("MOVE #%s[.IX], #-%s[.IX]", operandoAddress + OFFSET_RETORNO + 1, destinoAddress));
			}
			
			if( (operandoEsTemporal(operando)) && operandoEsVariable(destino) ) {
				setInstruccion(String.format("SUB .IX, #%s", destinoAddress + destino_VARIABLE_MEMORY_OFFSET));
				setInstruccion(String.format("MOVE .A, .R0"));
				setInstruccion(String.format("MOVE #-%s[.IX], [.R0]", operandoAddress));
			}
			
			if( (operandoEsTemporal(operando)) && operandoEsParametro(destino) ) {
				setInstruccion(String.format("MOVE #-%s[.IX], #%s[.IX]", operandoAddress, destinoAddress + OFFSET_RETORNO + 1));
			}
		}
	}

}
