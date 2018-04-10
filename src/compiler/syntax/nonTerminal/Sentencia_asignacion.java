//Clase que almacena la referencia de una sentencia de asignacion
package compiler.syntax.nonTerminal;

import compiler.CompilerContext;
import compiler.intermediate.Value;
import compiler.intermediate.Variable;
import compiler.semantic.symbol.SymbolVariableRetorno;
import compiler.semantic.type.TypeBoolean;
import compiler.semantic.type.TypeInteger;
import compiler.semantic.type.TypePointer;
import es.uned.lsi.compiler.intermediate.IntermediateCodeBuilder;
import es.uned.lsi.compiler.intermediate.TemporalFactory;
import es.uned.lsi.compiler.intermediate.TemporalIF;
import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.symbol.SymbolIF;
import es.uned.lsi.compiler.semantic.type.TypeIF;

public class Sentencia_asignacion extends Sentencia{
	String referencia; //referencia de la sentencia de asignacion
	

	public Sentencia_asignacion() {
		super();
		referencia=null;
	}

	public Sentencia_asignacion(String nombre) {
		super();
		referencia=nombre;
	}
	
	public void setReferencia(String nombre) {
		//Establece la referencia de una sentencia de asignacion
		this.referencia=nombre;
	}
	
	public String getReferencia() {
		//devuelve la referencia de una sentencia de asignacion
		return this.referencia;
	}
	
	public boolean tiposCompatibles(TypeIF tipo1, TypeIF tipo2) {
		//indica si 2 tipos de datos en una sentencia de asignacion son o no compatibles
		boolean res = false;
		
		if( (tipo1 instanceof TypeInteger) || (tipo1 instanceof TypePointer) ) {
			if ( (tipo2 instanceof TypeInteger) || (tipo2 instanceof TypePointer) ) {
				res = true;
			}
		}

		if(tipo1 instanceof TypeBoolean) {
			if(tipo2 instanceof TypeBoolean) {
				res = true;
			}
		}
		
		return res;
	}
	
	public void generateIntermediateCode(Sentencia_asignacion_izq referencia, Exp expresion, String nombre) {
		ScopeIF scope = CompilerContext.getScopeManager().getCurrentScope();
		TemporalFactory tFact = new TemporalFactory(scope);
		IntermediateCodeBuilder iCodeB = new IntermediateCodeBuilder(scope);
		SymbolIF simbolo = referencia.getSimbolo();
		String claseReferencia = referencia.getTipoReferencia();
		int desplazamiento=0;
		Variable var = null;

		TemporalIF temp0 = expresion.getTemporal();
		
		iCodeB.addQuadruples(expresion.getIntermediateCode());
		temporalNull(expresion, iCodeB);
		
		if(simbolo instanceof SymbolVariableRetorno) {
			// es una sentencia de retorno de funcion
		
			iCodeB.addQuadruple("RET", temp0, new Value(scope.getLevel()), referencia.getNombre());
		} else {
			TemporalIF temp1 = tFact.create();

			if(claseReferencia.equals("ID")) {
				var = new Variable(nombre,simbolo.getScope());
				iCodeB.addQuadruple("MVA",temp1,var);
				iCodeB.addQuadruple("STP",temp1,temp0);
			} else {
		
				if(claseReferencia.equals("ID^")) {
					var = new Variable(nombre,simbolo.getScope());
					iCodeB.addQuadruple("MV",temp1,var);
					iCodeB.addQuadruple("STP",temp1,temp0);
				
				} else {
					if(claseReferencia.equals("ID.ID")) {
						var = new Variable(nombre,simbolo.getScope());
						desplazamiento=referencia.getDesplazamiento();
					
						TemporalIF temp2 = tFact.create();
						TemporalIF dirInicio = tFact.create();
						TemporalIF dirCampo = tFact.create();

						iCodeB.addQuadruple("MVA",dirInicio,var);
						iCodeB.addQuadruple("MV",temp2,desplazamiento);
						iCodeB.addQuadruple("ADD",dirCampo,dirInicio,desplazamiento);
						iCodeB.addQuadruple("STP",dirCampo,temp0);
					} else {
						if(claseReferencia.equals("ID.ID^")) {
							var = new Variable(nombre,simbolo.getScope());
							desplazamiento=referencia.getDesplazamiento();
					
							TemporalIF temp2 = tFact.create();
							TemporalIF temp3 = tFact.create();
							TemporalIF dirInicio = tFact.create();
							TemporalIF dirCampo = tFact.create();

							iCodeB.addQuadruple("MVA",dirInicio,var);
							iCodeB.addQuadruple("MV",temp2,desplazamiento);
							iCodeB.addQuadruple("ADD",dirCampo,dirInicio,desplazamiento);
							iCodeB.addQuadruple("MVP",temp3,dirCampo);
							iCodeB.addQuadruple("STP",temp3,temp0);
						}
					
					}
				}
			}
		}
		this.setIntermediateCode(iCodeB.create());
		
	}
	
}
