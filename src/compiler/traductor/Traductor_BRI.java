package compiler.traductor;

import compiler.intermediate.Variable;
import es.uned.lsi.compiler.intermediate.OperandIF;
import es.uned.lsi.compiler.intermediate.QuadrupleIF;

public class Traductor_BRI extends Traductor {
	// Salto Si igual
	// CI - BRI label op1 op2  --> si op1==op2 salta a label

	protected void traducir(QuadrupleIF quadruple) {
		
		
		int op1Address = 0;
		int op2Address = 0;
		String op1Valor = "";
		String op2Valor = "";
		int op1VarOffset = 0;
		int op2VarOffset = 0;
		int parOffset = OFFSET_RETORNO + 1;
		
		
		
		String label = quadruple.getResult().toString();
		OperandIF operando1 = quadruple.getFirstOperand();
		OperandIF operando2 = quadruple.getSecondOperand();

		
		if( (operandoEsTemporal(operando1)) && (operandoEsTemporal(operando2))) {
			op1Address = getOperandoAddress(operando1);
			op2Address = getOperandoAddress(operando2);
			setInstruccion(String.format("%s #-%s[.IX], #-%s[.IX]", "CMP", op1Address, op2Address));
		}
		
		if( (operandoEsTemporal(operando1)) && (operandoEsValue(operando2))) {
			op1Address = getOperandoAddress(operando1);
			op2Valor = getOperandoValue(operando2);
			setInstruccion(String.format("%s #-%s[.IX], #%s", "CMP", op1Address, op2Valor));
		}
		
		if( (operandoEsTemporal(operando1)) && (operando2 instanceof Variable)) {
			if(operandoEsGlobal(operando2)) {
				op1Address = getOperandoAddress(operando1);
				op2Address = getOperandoAddress(operando2);
				setInstruccion(String.format("%s #-%s[.IX], /%s", "CMP", op1Address, op2Address));
			} else {
				if(operandoEsVariable(operando2)) {
					op2VarOffset = getVariableMemoryOffset(getOperandoScope(operando2));
					setInstruccion(String.format("CMP #-%s[.IX], #-%s[.IX]", op1Address, op2Address + op2VarOffset));
				}
				if(operandoEsParametro(operando2)) {
					op1Address = getOperandoAddress(operando1);
					op2Address = getOperandoAddress(operando2);
					setInstruccion(String.format("CMP #-%s[.IX], #%s[.IX]", op1Address, op2Address + parOffset));
					
				}
			}
		}
		
		if( (operandoEsValue(operando1)) && (operandoEsTemporal(operando2))) {
			op1Valor = getOperandoValue(operando1);
			op2Address = getOperandoAddress(operando2);
			setInstruccion(String.format("%s #%s, #-%s[.IX]", "CMP", op1Valor, op2Address));
		}
		
		
		if( (operandoEsValue(operando1)) && (operandoEsValue(operando2))) {
			op1Valor = getOperandoValue(operando1);
			op2Valor = getOperandoValue(operando2);
			setInstruccion(String.format("%s #%s, #%s", "CMP", op1Valor, op2Valor));
		}
		
		if( (operandoEsValue(operando1)) && (operando2 instanceof Variable)) {
			if(operandoEsGlobal(operando2)) {
				op1Valor = getOperandoValue(operando1);
				op2Address = getOperandoAddress(operando2);
				setInstruccion(String.format("CMP #%s, /%s",op1Valor, op2Address));
			} else {
				if(operandoEsVariable(operando2)) {
					op2VarOffset = getVariableMemoryOffset(getOperandoScope(operando2));
					op1Valor = getOperandoValue(operando1);
					op2Address = getOperandoAddress(operando2);
					setInstruccion(String.format("CMP #%s, #-%s[.IX]", op1Valor, op2Address + op2VarOffset));
				}
				if(operandoEsParametro(operando2)) {
					op1Valor = getOperandoValue(operando1);
					op2Address = getOperandoAddress(operando2);
					setInstruccion(String.format("CMP #%s, #%s[.IX]", op1Valor, op2Address + parOffset));
					
				}
			}
		}

		if( (operando1 instanceof Variable) && (operandoEsTemporal(operando2))) {
			if(operandoEsGlobal(operando1)) {
				op1Address = getOperandoAddress(operando1);
				op2Address = getOperandoAddress(operando2);
				setInstruccion(String.format("CMP /%s, #-%s[.IX]", op1Address, op2Address));
			} else {
				if(operandoEsVariable(operando1)) {
					op1VarOffset = getVariableMemoryOffset(getOperandoScope(operando1));
					op1Address = getOperandoAddress(operando1);
					op2Address = getOperandoAddress(operando2);
					setInstruccion(String.format("CMP #-%s[.IX], #-%s[.IX]", op1Address + op1VarOffset, op2Address));
				}
				if(operandoEsParametro(operando1)) {
					op1Address = getOperandoAddress(operando1);
					op2Address = getOperandoAddress(operando2);
					setInstruccion(String.format("CMP #%s[.IX], #-%s[.IX]", op1Address + parOffset, op2Address));
					
				}
			}
		}

		if( (operando1 instanceof Variable) && (operandoEsValue(operando2))) {
			if(operandoEsGlobal(operando1)) {
				op1Address = getOperandoAddress(operando1);
				op2Valor = getOperandoValue(operando2);
				setInstruccion(String.format("CMP /%s, #%s", op1Address, op2Valor));
			} else {
				if(operandoEsVariable(operando1)) {
					op1VarOffset = getVariableMemoryOffset(getOperandoScope(operando1));
					op1Address = getOperandoAddress(operando1);
					op2Valor = getOperandoValue(operando2);
					setInstruccion(String.format("CMP #-%s[.IX], #%s", op1Address + op1VarOffset, op2Valor));
				}
				if(operandoEsParametro(operando1)) {
					op1Address = getOperandoAddress(operando1);
					op2Valor = getOperandoValue(operando2);
					setInstruccion(String.format("CMP #%s[.IX], #%s", op1Address + parOffset + 1, op2Valor));
					
				}
			}
		}
		
		if( (operando1 instanceof Variable) && (operando2 instanceof Variable)) {
			if( (operandoEsGlobal(operando1)) && (operandoEsGlobal(operando2)) ) {
				op1Address = getOperandoAddress(operando1);
				op2Address = getOperandoAddress(operando2);
				setInstruccion(String.format("CMP /%s, /%s", op1Address, op2Address));
			}
			if( (operandoEsGlobal(operando1)) && (operandoEsLocal(operando2)) ) {
				if(operandoEsVariable(operando2)) {
					op2VarOffset = getVariableMemoryOffset(getOperandoScope(operando2));
					op1Address = getOperandoAddress(operando1);
					op2Address = getOperandoAddress(operando2);
					setInstruccion(String.format("CMP /%s, #-%s[.IX]", op1Address, op2Address + op2VarOffset));
				} else {
					op1Address = getOperandoAddress(operando1);
					op2Address = getOperandoAddress(operando2);
					setInstruccion(String.format("CMP /%s, #%s[.IX]", op1Address, op2Address + parOffset));
				}
			}
			if( (operandoEsLocal(operando1)) && (operandoEsGlobal(operando2)) ) {
				if(operandoEsVariable(operando1)) {
					op1VarOffset = getVariableMemoryOffset(getOperandoScope(operando1));
					op1Address = getOperandoAddress(operando1);
					op2Address = getOperandoAddress(operando2);
					setInstruccion(String.format("CMP #-%s[.IX], /%s", op1Address + op1VarOffset, op2Address));
				} else {
					op1Address = getOperandoAddress(operando1);
					op2Address = getOperandoAddress(operando2);
					setInstruccion(String.format("CMP #%s[.IX], /%s", op1Address + parOffset, op2Address));
				}
			}
			
			if( (operandoEsLocal(operando1)) && (operandoEsLocal(operando2)) ) {
				if(operandoEsVariable(operando1) && operandoEsVariable(operando2)) {
					op1VarOffset = getVariableMemoryOffset(getOperandoScope(operando1));
					op2VarOffset = getVariableMemoryOffset(getOperandoScope(operando2));
					op1Address = getOperandoAddress(operando1);
					op2Address = getOperandoAddress(operando2);
					setInstruccion(String.format("CMP #-%s[.IX], #-%s[.IX]", op1Address + op1VarOffset, op2Address + op2VarOffset));
				}

				if(operandoEsVariable(operando1) && operandoEsParametro(operando2)) {
					op1VarOffset = getVariableMemoryOffset(getOperandoScope(operando1));
					op1Address = getOperandoAddress(operando1);
					op2Address = getOperandoAddress(operando2);
					setInstruccion(String.format("CMP #-%s[.IX], #%s[.IX]", op1Address + op1VarOffset, op2Address + parOffset));
				}

				if(operandoEsParametro(operando1) && operandoEsVariable(operando2)) {
					op2VarOffset = getVariableMemoryOffset(getOperandoScope(operando2));
					op1Address = getOperandoAddress(operando1);
					op2Address = getOperandoAddress(operando2);
					setInstruccion(String.format("CMP #%s[.IX], #-%s[.IX]", op1Address + parOffset, op2Address + op2VarOffset));
				}

				if(operandoEsParametro(operando1) && operandoEsParametro(operando2)) {
					op1Address = getOperandoAddress(operando1);
					op2Address = getOperandoAddress(operando2);
					setInstruccion(String.format("CMP #%s[.IX], #%s[.IX]", op1Address + parOffset, op2Address + parOffset));
				}
			}
		}
		setInstruccion(String.format("%s /%s", "BZ", label));
		
	}
}
