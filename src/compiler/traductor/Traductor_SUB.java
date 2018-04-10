package compiler.traductor;

import es.uned.lsi.compiler.intermediate.OperandIF;
import es.uned.lsi.compiler.intermediate.QuadrupleIF;

public class Traductor_SUB extends Traductor{

	// CI - SUB resultado, operando1, operando2
	//    resultado = operando1 - operando2
	
	protected void traducir(QuadrupleIF quadruple) {
		
		int op1Address = 0;
		int op2Address = 0;
		int resAddress = 0;
		int op1VarOffset = 0;
		int op2VarOffset = 0;
		int resVarOffset = 0;
		int parOffset = OFFSET_RETORNO + 1;
		
		String op1Valor = "";
		String op2Valor = "";
		
		OperandIF resultado = quadruple.getResult();
		OperandIF operando1 = quadruple.getFirstOperand();
		OperandIF operando2 = quadruple.getSecondOperand();
		
		//------------------------------------------------------------------------------------------------
		//Construimos CF pora la instruccion ADD
		if(operandoEsValue(operando1)) {
			if(operandoEsTemporal(operando2)) {
				op2Address = getOperandoAddress(operando2);
				op1Valor = getOperandoValue(operando1);
				setInstruccion(String.format("SUB #%s, #-%s[.IX]", op1Valor, op2Address));
			}

			if(operandoEsValue(operando2)) {
				op2Valor = getOperandoValue(operando2);
				op1Valor = getOperandoValue(operando1);
				setInstruccion(String.format("SUB #%s, #%s", op1Valor, op2Valor));
			}

			if(operandoEsGlobal(operando2) && operandoEsVariable(operando2)) {
				op2Address = getOperandoAddress(operando2);
				op1Valor = getOperandoValue(operando1);
				setInstruccion(String.format("SUB #%s, /%s", op1Valor, op2Address));
			}
			if(operandoEsLocal(operando2) && operandoEsVariable(operando2)) {
				op1Valor = getOperandoValue(operando1);
				op2Address = getOperandoAddress(operando2);
				op2VarOffset = getVariableMemoryOffset(getOperandoScope(operando2));
				setInstruccion(String.format("SUB #%s, #-%s[.IX]", op1Valor, op2Address + op2VarOffset));
			}

			if(operandoEsLocal(operando2) && operandoEsParametro(operando2)) {
				op1Valor = getOperandoValue(operando1);
				op2Address = getOperandoAddress(operando2);
				setInstruccion(String.format("SUB #%s, #%s[.IX]", op1Valor, op2Address + parOffset));
			}
		}
		//------------------------------------------------------------------------------------------------

		//------------------------------------------------------------------------------------------------
		if(operandoEsValue(operando2)) {
			if( operandoEsTemporal(operando1)) {
				op1Address = getOperandoAddress(operando1);
				op2Valor = getOperandoValue(operando2);
				setInstruccion(String.format("SUB #-%s[.IX], #%s", op1Address, op2Valor));
			}
			
			if(operandoEsVariable(operando1) && operandoEsGlobal(operando1)) {
				op1Address = getOperandoAddress(operando1);
				op2Valor = getOperandoValue(operando2);
				setInstruccion(String.format("SUB /%s, #%s", op1Address, op2Valor));
			}
			if(operandoEsVariable(operando1) && operandoEsLocal(operando1)) {
				op1Address = getOperandoAddress(operando1);
				op2Valor = getOperandoValue(operando2);
				op1VarOffset = getVariableMemoryOffset(getOperandoScope(operando1));
				setInstruccion(String.format("SUB #-%s[.IX], #%s", op1Address + op1VarOffset, op2Valor));
			}
			if(operandoEsParametro(operando1) && operandoEsLocal(operando1)) {
				op1Address = getOperandoAddress(operando1);
				op2Valor = getOperandoValue(operando2);
				setInstruccion(String.format("SUB #%s[.IX], #%s", op1Address + parOffset, op2Valor));
			}
		}
		//------------------------------------------------------------------------------------------------
		
		//------------------------------------------------------------------------------------------------
		if( operandoEsGlobal(operando1) && operandoEsVariable(operando1) && operandoEsTemporal(operando2)) {
			op1Address = getOperandoAddress(operando1);
			op2Address = getOperandoAddress(operando2);
			setInstruccion(String.format("SUB /%s, #-%s[.IX]", op1Address, op2Address));
		}

		if( operandoEsGlobal(operando2) && operandoEsTemporal(operando1) && operandoEsVariable(operando2)) {
			op1Address = getOperandoAddress(operando1);
			op2Address = getOperandoAddress(operando2);
			setInstruccion(String.format("SUB #-%s[.IX], /%s", op1Address, op2Address));
		}

		if( operandoEsTemporal(operando1) && operandoEsTemporal(operando2)) {
			op1Address = getOperandoAddress(operando1);
			op2Address = getOperandoAddress(operando2);
			setInstruccion(String.format("SUB #-%s[.IX], #-%s[.IX]", op1Address, op2Address));
		}

		//------------------------------------------------------------------------------------------------
		
		
		//------------------------------------------------------------------------------------------------
		if( operandoEsGlobal(operando1) && operandoEsGlobal(operando2) ) {
			if(operandoEsVariable(operando1) && operandoEsVariable(operando2)) {
				op1Address = getOperandoAddress(operando1);
				op2Address = getOperandoAddress(operando2);
				setInstruccion(String.format("SUB /%s, /%s", op1Address, op2Address));
			}

		}
		//------------------------------------------------------------------------------------------------
		
		
		//------------------------------------------------------------------------------------------------
		if( operandoEsGlobal(operando1) && operandoEsLocal(operando2)) {
			if(operandoEsVariable(operando1) && operandoEsVariable(operando2)) {
				op1Address = getOperandoAddress(operando1);
				op2Address = getOperandoAddress(operando2);
				op2VarOffset = getVariableMemoryOffset(getOperandoScope(operando2)); 
				setInstruccion(String.format("SUB /%s, #-%s[.IX]", op1Address, op2Address + op2VarOffset));
			}

			if(operandoEsVariable(operando1) && operandoEsParametro(operando2)) {
				op1Address = getOperandoAddress(operando1);
				op2Address = getOperandoAddress(operando2);
				setInstruccion(String.format("SUB /%s, #%s[.IX]", op1Address, op2Address + parOffset));
			}
		}
		//------------------------------------------------------------------------------------------------

		//------------------------------------------------------------------------------------------------
		if( operandoEsLocal(operando1) && operandoEsGlobal(operando2)) {
			if(operandoEsVariable(operando1) && operandoEsVariable(operando2)) {
				op1Address = getOperandoAddress(operando1);
				op2Address = getOperandoAddress(operando2);
				op1VarOffset = getVariableMemoryOffset(getOperandoScope(operando1)); 
				setInstruccion(String.format("SUB #-%s[.IX], /%s", op1Address + op1VarOffset, op2Address));
			}

			if(operandoEsParametro(operando1) && operandoEsVariable(operando2)) {
				op1Address = getOperandoAddress(operando1);
				op2Address = getOperandoAddress(operando2);
				setInstruccion(String.format("SUB  #%s[.IX], /%s", op1Address + parOffset, op2Address));
			}
		}
		//------------------------------------------------------------------------------------------------

		//------------------------------------------------------------------------------------------------
		if( operandoEsLocal(operando1) && operandoEsLocal(operando2)) {
			if(operandoEsVariable(operando1) && operandoEsVariable(operando2)) {
				op1Address = getOperandoAddress(operando1);
				op2Address = getOperandoAddress(operando2);
				op1VarOffset = getVariableMemoryOffset(getOperandoScope(operando1));
				op2VarOffset = getVariableMemoryOffset(getOperandoScope(operando2));
				setInstruccion(String.format("SUB #-%s[.IX], #-%s[.IX]", op1Address + op1VarOffset, op2Address + op2VarOffset));
			}

			if(operandoEsVariable(operando1) && operandoEsParametro(operando2)) {
				op1Address = getOperandoAddress(operando1);
				op2Address = getOperandoAddress(operando2);
				op1VarOffset = getVariableMemoryOffset(getOperandoScope(operando1));
				setInstruccion(String.format("SUB #-%s[.IX], #%s[.IX]", op1Address + op1VarOffset, op2Address + parOffset));
			}

			if(operandoEsVariable(operando1) && operandoEsTemporal(operando2)) {
				op1Address = getOperandoAddress(operando1);
				op2Address = getOperandoAddress(operando2);
				op1VarOffset = getVariableMemoryOffset(getOperandoScope(operando1));
				setInstruccion(String.format("SUB #-%s[.IX], #-%s[.IX]", op1Address + op1VarOffset, op2Address));
			}


			if(operandoEsParametro(operando1) && operandoEsVariable(operando2)) {
				op1Address = getOperandoAddress(operando1);
				op2Address = getOperandoAddress(operando2);
				op2VarOffset = getVariableMemoryOffset(getOperandoScope(operando2));
				setInstruccion(String.format("SUB #%s[.IX], #-%s[.IX]", op1Address + parOffset, op2Address + op2VarOffset));
			}

			if(operandoEsParametro(operando1) && operandoEsParametro(operando2)) {
				op1Address = getOperandoAddress(operando1);
				op2Address = getOperandoAddress(operando2);
				setInstruccion(String.format("SUB #%s[.IX], #%s[.IX]", op1Address + parOffset, op2Address + parOffset));
			}

			if(operandoEsParametro(operando1) && operandoEsTemporal(operando2)) {
				op1Address = getOperandoAddress(operando1);
				op2Address = getOperandoAddress(operando2);
				setInstruccion(String.format("SUB #%s[.IX], #-%s[.IX]", op1Address + parOffset, op2Address));
			}


			if(operandoEsTemporal(operando1) && operandoEsVariable(operando2)) {
				op1Address = getOperandoAddress(operando1);
				op2Address = getOperandoAddress(operando2);
				op2VarOffset = getVariableMemoryOffset(getOperandoScope(operando2));
				setInstruccion(String.format("SUB #-%s[.IX], #-%s[.IX]", op1Address, op2Address + op2VarOffset));
			}

			if(operandoEsTemporal(operando1) && operandoEsParametro(operando2)) {
				op1Address = getOperandoAddress(operando1);
				op2Address = getOperandoAddress(operando2);
				setInstruccion(String.format("SUB #-%s[.IX], #%s[.IX]", op1Address, op2Address + parOffset));
			}

		}
		//------------------------------------------------------------------------------------------------
		
		
		// Creamos el CF para la instruccion que mueve el resultado (.A) al operando resultado
		if(operandoEsTemporal(resultado)) {
			resAddress = getOperandoAddress(resultado);
			setInstruccion(String.format("MOVE .A, #-%s[.IX]", resAddress));
		}
		
		if( operandoEsGlobal(resultado) && operandoEsVariable(resultado)) {
			resAddress = getOperandoAddress(resultado);
			setInstruccion(String.format("MOVE .A, /%s", resAddress));
		}
		
		if( operandoEsLocal(resultado) && operandoEsVariable(resultado)) {
			resAddress = getOperandoAddress(resultado);
			resVarOffset = getVariableMemoryOffset(getOperandoScope(resultado));
			setInstruccion(String.format("MOVE .A, #-%s[.IX]", resAddress + resVarOffset));
		}
		
		if( operandoEsLocal(resultado) && operandoEsParametro(resultado)) {
			resAddress = getOperandoAddress(resultado);
			setInstruccion(String.format("MOVE .A, #%s[.IX]", resAddress + parOffset));
		}
	
	
	}
}
