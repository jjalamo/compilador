package compiler.traductor;

import java.util.List;

import compiler.intermediate.Temporal;
import compiler.intermediate.Value;
import compiler.intermediate.Variable;
import compiler.semantic.symbol.SymbolParameter;
import compiler.semantic.symbol.SymbolVariable;
import es.uned.lsi.compiler.intermediate.OperandIF;
import es.uned.lsi.compiler.intermediate.QuadrupleIF;
import es.uned.lsi.compiler.intermediate.TemporalIF;
import es.uned.lsi.compiler.semantic.ScopeIF;
	// + Direccionamiento:
	// 	 - Inmediato:                            #
	//   - Directo a registro:                 .Rx
	//   - Directo a memoria:                    /
	//   - Indirecto:                        [.Rx]
	//   - Relativo a registro índice:      #[.Ix]
	//   - Relativo a contador de programa:      $
import es.uned.lsi.compiler.semantic.symbol.SymbolIF;
import es.uned.lsi.compiler.semantic.symbol.SymbolTableIF;

public abstract class Traductor {
	protected static final int ADDRESS_STACK = 65000; //Direccion de inicio de STACK
	protected static final int OFFSET_RETORNO = 1; //Offset del retorno
	protected static final int ADDRESS_FP = ADDRESS_STACK - OFFSET_RETORNO; //Direccion  de FP
	protected static final int ADDRESS_FRAME = ADDRESS_STACK + 1; //Direccion del Frame
	
	private StringBuilder traduccionCF;
	
	public Traductor() {
		this.traduccionCF = new StringBuilder();
	}
	
	protected abstract void traducir(QuadrupleIF quadruple);
	
	protected void setInstruccion(String i) {
		//añade una instruccion de codigo final
		this.traduccionCF.append(i + "\n");
	}
	
	public StringBuilder getTraduccion() {
		//devuelve la traduccion
		return this.traduccionCF;
	}
	
	public void setTraduccion(StringBuilder t) {
		//establece la traduccion
		this.traduccionCF = t;
	}

	public String getQuadrupleTrans(QuadrupleIF quadruple) {
		//pasa la traduccion a cadena
		this.traduccionCF.append("; " + quadruple.toString() + "\n");
		this.traducir(quadruple);
		
		return this.traduccionCF.toString();
	}
	
//------------------------------------------------------------------	
	@SuppressWarnings("unused")
	public int getVariableMemoryOffset(ScopeIF scope) {
		// devuelve el desplazamiento de la zona de variables locales
		int offset = 1;

		List<TemporalIF> temporals = scope.getTemporalTable().getTemporals();
		for(TemporalIF tIF : temporals) {
			offset = offset + 1;
		}
		return offset;
	}
	
//------------------------------------------------------------------	
	public ScopeIF getOperandoScope(OperandIF op) {
		//devuelve el scope de un operando
		ScopeIF scope = null;
		
		if(op instanceof Variable) {
			Variable opp = (Variable) op;
			scope = opp.getScope();
		}
		
		if(op instanceof Temporal) {
			Temporal opp =(Temporal) op;
			scope = opp.getScope();
		}
		return scope;
	}
//------------------------------------------------------------------	
	public boolean operandoEsVariable(OperandIF op) {
		//devuelve true si el operando es de tipo VARIABLE
		//devuelve false en caso contrario
		boolean res = false;
		
		if(op instanceof Variable) {
			Variable opp = (Variable) op;
			String opNombre = opp.getName();
			ScopeIF opScope = opp.getScope();
			SymbolTableIF opTablaSimbolos = opScope.getSymbolTable();
			SymbolIF opSim = opTablaSimbolos.getSymbol(opNombre);
			if(opSim instanceof SymbolVariable) {
				res = true;
			}
		}
		
		return res;
	}
//--------------------------------------------------------------------		
	public boolean operandoEsParametro(OperandIF op) {
			//devuelve true si el operando es de tipo PARAMETRO
			//devuelve false en caso contrario
		boolean res = false;
			
		if(op instanceof Variable) {
			Variable opp = (Variable) op;
			String opNombre = opp.getName();
			ScopeIF opScope = opp.getScope();
			SymbolTableIF opTablaSimbolos = opScope.getSymbolTable();
			SymbolIF opSim = opTablaSimbolos.getSymbol(opNombre);
			if(opSim instanceof SymbolParameter) {
				res = true;
			}
		}
		
		return res;
	}
//----------------------------------------------------------
	public boolean operandoEsTemporal(OperandIF op) {
		//devuelve true si op es TEMPORAL y false en caso contrario
		return(op instanceof Temporal);
	}
//-------------------------------------------------------------
	public boolean operandoEsValue(OperandIF op) {
		//devuelve true si op es VALUE y false en caso contrario
		return (op instanceof Value);
	}
//--------------------------------------------------------------
	public boolean operandoEsVariableRetorno(OperandIF op) {
		//devuelve true si op es VARIABLE DE RETORNO y false en caso contrario
		
		return (!operandoEsVariable(op) && !operandoEsParametro(op) && !operandoEsTemporal(op) && !operandoEsValue(op));
	}
//----------------------------------------------------------------
	public int getOperandoAddress(OperandIF op) {
		//devuelve la direccion de memoria del operando
		int res = 1;
		
		if(operandoEsVariable(op)) {
			Variable opp = (Variable) op;
			String nombre = opp.getName();
			ScopeIF scope = opp.getScope();
			SymbolTableIF tablaSimbolos = scope.getSymbolTable();
			SymbolIF sIF = tablaSimbolos.getSymbol(nombre);
			if(sIF instanceof SymbolVariable) {
				SymbolVariable opSim = (SymbolVariable) sIF;
				res = opSim.getAddress();
			}
		}
		
		if(operandoEsParametro(op)) {
			Variable opp = (Variable) op;
			String nombre = opp.getName();
			ScopeIF scope = opp.getScope();
			SymbolTableIF tablaSimbolos = scope.getSymbolTable();
			SymbolIF sIF = tablaSimbolos.getSymbol(nombre);
			if(sIF instanceof SymbolParameter) {
				SymbolParameter opSim = (SymbolParameter) sIF;
				res = opSim.getAddress();
			}
		} 
		
		if (operandoEsTemporal(op)) {
			Temporal opp = (Temporal) op;
			res = opp.getAddress();
		}
	
		return res;
	}
//-------------------------------------------------------------------------	
	public String getOperandoValue(OperandIF op) {
		//devuelve el VALOR del operando

		Value opp = (Value) op;
		
		return opp.getValue().toString();
	}
//-------------------------------------------------------------------------
	public boolean operandoEsGlobal (OperandIF op) {
		//devuelve true si el operando es GLOBAL
		//devuelve false en caso contrario
		boolean res = false;
		ScopeIF opScope = null;
		
		if(!operandoEsVariableRetorno(op)) {
			if(op instanceof Value) {
				res = true;
			} else {
				if(op instanceof Temporal) {
					Temporal opp = (Temporal) op;
					opScope = opp.getScope();
				} else {
					Variable opp = (Variable) op;
					opScope = opp.getScope();
				}
			
				if(opScope.getLevel() == 0) {
					res = true;
				}
			}
		}
		
		return res;
	}
//--------------------------------------------------------------------------
	public boolean operandoEsGlobal (OperandIF op, OperandIF des) {
		//devuelve true si el operando es GLOBAL
		//devuelve false en caso contrario
		boolean res = false;
		ScopeIF opScope = null;
		
		if(operandoEsValue(op)) {
			res = operandoEsGlobal(des);
		} else {
			if(op instanceof Temporal) {
				Temporal opp = (Temporal) op;
				opScope = opp.getScope();
			} else {
				Variable opp = (Variable) op;
				opScope = opp.getScope();
			}
			
			if(opScope.getLevel() == 0) {
				res = true;
			}
			
		}
		return res;
	}
//----------------------------------------------------------------------
	public boolean operandoEsLocal(OperandIF op) {
		//devuelve true si op es Global y false en caso contrario
		return (!operandoEsGlobal(op));
	}
//----------------------------------------------------------------------	
	public boolean operandoEsLocal(OperandIF op, OperandIF des) {
		//devuelve true si op es Local y false en caso contrario
		return (!operandoEsGlobal(op,des));
	}
//----------------------------------------------------------------------	
	
}
