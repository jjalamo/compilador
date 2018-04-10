//Clase que implementa una expresion aritmetica MENOS
package compiler.syntax.nonTerminal;

import compiler.CompilerContext;
import compiler.semantic.type.TypeInteger;
import compiler.semantic.type.TypePointer;
import es.uned.lsi.compiler.intermediate.IntermediateCodeBuilder;
import es.uned.lsi.compiler.intermediate.TemporalFactory;
import es.uned.lsi.compiler.intermediate.TemporalIF;
import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.type.TypeIF;

public class ExpresionMenos extends Exp{

	public ExpresionMenos(TypeIF tipo) {
		super(tipo);
	}
	
	public boolean tiposCompatibles(TypeIF tipo1, TypeIF tipo2) {
		//indica si 2 tipos de datos son compatibles en una expresion aritmetica MENOS
		boolean res = false;
		
		if( (tipo1 instanceof TypeInteger) || (tipo1 instanceof TypePointer) ) {
			if ( (tipo2 instanceof TypeInteger) || (tipo2 instanceof TypePointer) ) {
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
		
		iCodeB.addQuadruple("SUB",temp,temp1,temp2);
		this.setTemporal(temp);
		this.setIntermediateCode(iCodeB.create());
	}
}
