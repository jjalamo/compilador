package compiler.syntax.nonTerminal;

import compiler.CompilerContext;
import es.uned.lsi.compiler.intermediate.IntermediateCodeBuilder;
import es.uned.lsi.compiler.intermediate.LabelFactory;
import es.uned.lsi.compiler.intermediate.LabelIF;
import es.uned.lsi.compiler.intermediate.TemporalIF;
import es.uned.lsi.compiler.semantic.ScopeIF;

public class Sentencia_if extends Sentencia {
	
	public Sentencia_if() {
		super();
	}

	public void generateIntermediateCode(Exp expresion, Sentencia sentencias1, Sentencia sentencias2) {
		ScopeIF scope = CompilerContext.getScopeManager().getCurrentScope();
		
		LabelFactory lFact = new LabelFactory();
		LabelIF l1 = lFact.create();
		LabelIF l2 = lFact.create();
		
		IntermediateCodeBuilder iCodeB = new IntermediateCodeBuilder(scope);
		
		iCodeB.addQuadruples(expresion.getIntermediateCode());
		temporalNull(expresion, iCodeB);
		TemporalIF eTemp = expresion.getTemporal();
		
		iCodeB.addQuadruple("BRF",eTemp,l1);
		iCodeB.addQuadruples(sentencias1.getIntermediateCode());
		iCodeB.addQuadruple("BR",l2);
		iCodeB.addQuadruple("INL",l1);
		iCodeB.addQuadruples(((Sentencia) sentencias2).getIntermediateCode());
		iCodeB.addQuadruple("INL",l2);
		
		this.setIntermediateCode(iCodeB.create());
		
	}
	
}
