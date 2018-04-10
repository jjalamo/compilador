package compiler.syntax.nonTerminal;

import java.util.List;

import compiler.CompilerContext;
import compiler.semantic.symbol.SymbolParameter;
import compiler.semantic.symbol.SymbolProcedure;
import es.uned.lsi.compiler.intermediate.IntermediateCodeBuilder;
import es.uned.lsi.compiler.intermediate.TemporalFactory;
import es.uned.lsi.compiler.intermediate.TemporalIF;
import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.symbol.SymbolIF;
import es.uned.lsi.compiler.semantic.symbol.SymbolTableIF;

public class Sentencia_subprograma extends Sentencia {
	
	public Sentencia_subprograma() {
		super();
	}

	public void generateIntermediateCode(String nombreSubprograma,LLamada_subprograma sub) {
		ScopeIF scope = CompilerContext.getScopeManager().getCurrentScope();
		IntermediateCodeBuilder iCodeB = new IntermediateCodeBuilder(scope);
		
		iCodeB.addQuadruples(sub.getIntermediateCode());
		
		Secuencia_parametros_llamada secParam = sub.getParametrosLLamada();
		
		//buscamos el Ambito (scope) del procedimiento y obtenemos su tabla de simbolos
		List<ScopeIF> allScopes =  CompilerContext.getScopeManager().getAllScopes();
		ScopeIF scopeProcedure = null;
		for (ScopeIF scopeTemp : allScopes)	{
			String nombreScope = scopeTemp.getName();
			if(nombreScope.equals(nombreSubprograma)) {
				scopeProcedure = scopeTemp;
			}
		}
		SymbolTableIF tablaSimbolosProcedureScope = scopeProcedure.getSymbolTable();
		
		
		//buscamos en el la tabla de simbolos del ambito de llamada (current scope) 
		// el simbolo del procedimiento
		SymbolTableIF tablaSimbolosCurrentScope = scope.getSymbolTable();
		SymbolIF sIF = tablaSimbolosCurrentScope.getSymbol(nombreSubprograma);
		SymbolProcedure simboloProcedure = null;
		
		if(sIF instanceof SymbolProcedure) {
			simboloProcedure = (SymbolProcedure) sIF;
		}
		
		//Obtenermos la lista de parametros formales del procedimiento
		Secuencia_parametros secParametrosFormales = simboloProcedure.getParametros();
		int numParametrosFormales = secParametrosFormales.numParametros();
		if(numParametrosFormales > 0) {
		//Creamos para cada parametro su linea de CF
			int i=0;
			for(i=(numParametrosFormales - 1); i>=0; i--) {
				ParametroFormal parametroF = secParametrosFormales.getParametro(i);
				parametroF.setAddress(i);
				String nombreParametroF = parametroF.getNombre();
			
				SymbolIF simboloParametroF = tablaSimbolosProcedureScope.getSymbol(nombreParametroF);
				SymbolParameter sParam = null;
			
				if(simboloParametroF instanceof SymbolParameter) {
					sParam = (SymbolParameter) simboloParametroF;
					sParam.setAddress(i);
				}
				TemporalIF tTemp = secParam.getParametro(i).getTemporal();
				//	obtenemos el parametro actual
				Exp pActual = secParam.getParametro(i);
			
				iCodeB.addQuadruples(pActual.getIntermediateCode());
				iCodeB.addQuadruple("PARAM",tTemp,i );
			}
		}
		TemporalFactory tFact = new TemporalFactory(scope);
		TemporalIF temp = tFact.create();

		iCodeB.addQuadruple("CALL",nombreSubprograma);
		
		this.setTemporal(temp);
		this.setIntermediateCode(iCodeB.create());
	}
}
