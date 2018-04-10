//Clase que implementa una expresion logica de valores TRUE o FALSE
package compiler.syntax.nonTerminal;

import compiler.CompilerContext;
import es.uned.lsi.compiler.intermediate.IntermediateCodeBuilder;
import es.uned.lsi.compiler.intermediate.TemporalFactory;
import es.uned.lsi.compiler.intermediate.TemporalIF;
import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.type.TypeIF;

public class ExpresionLogicaTrueFalse extends Exp {
	private boolean valor;
	
	public ExpresionLogicaTrueFalse(TypeIF tipo, boolean valorLogico) {
		super(tipo);
		this.valor=valorLogico;
	}
	
	public void setValor(boolean valorLogico) {
		//estable un valor logico en la expresion logica
		this.valor=valorLogico;
	}
	
	public boolean getValor() {
		//devuelve el valor logico de la expresion 
		return this.valor;
	}
	
	public void generateIntermediateCode() {
		ScopeIF scope = CompilerContext.getScopeManager().getCurrentScope();
		TemporalFactory tFact = new TemporalFactory(scope);
		TemporalIF temp = tFact.create();
		IntermediateCodeBuilder iCodeB = new IntermediateCodeBuilder(scope);
		
		if(this.valor) {
			iCodeB.addQuadruple("MV",temp, 1);
		} else {
			iCodeB.addQuadruple("MV",temp, 0);
			
		}
		this.setTemporal(temp);
		this.setIntermediateCode(iCodeB.create());
	}
	
}
