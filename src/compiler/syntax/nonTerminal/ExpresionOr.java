//Clase que implementa una expresion logica OR
package compiler.syntax.nonTerminal;

import compiler.CompilerContext;
import compiler.semantic.type.TypeBoolean;
import es.uned.lsi.compiler.intermediate.IntermediateCodeBuilder;
import es.uned.lsi.compiler.intermediate.TemporalFactory;
import es.uned.lsi.compiler.intermediate.TemporalIF;
import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.type.TypeIF;

public class ExpresionOr extends Exp{

	public ExpresionOr(TypeIF tipo) {
		super(tipo);
	}
	
	public boolean tiposCompatibles(TypeIF tipo1, TypeIF tipo2) {
		//indica si 2 tipos de datos son compatibles en una expresion logica OR
		boolean res = false;
		
		if(tipo1 instanceof TypeBoolean) {
			if(tipo2 instanceof TypeBoolean) {
				res = true;
			}
		}
		return res;
	}
	
	public void generateIntermediateCode(Exp exp1, Exp exp2) {
		ScopeIF scope = CompilerContext.getScopeManager().getCurrentScope();
		IntermediateCodeBuilder iCodeB = new IntermediateCodeBuilder(scope);
		TemporalFactory tFact = new TemporalFactory(scope);
		TemporalIF temp = tFact.create();
		
		iCodeB.addQuadruples(exp1.getIntermediateCode());
		temporalNull(exp1,iCodeB);
		TemporalIF temp1 = exp1.getTemporal();
		
		iCodeB.addQuadruples(exp2.getIntermediateCode());
		temporalNull(exp2,iCodeB);
		TemporalIF temp2 = exp2.getTemporal();
		
		iCodeB.addQuadruple("OR",temp,temp1,temp2);
		
		this.setTemporal(temp);
		this.setIntermediateCode(iCodeB.create());
	}
	

}
