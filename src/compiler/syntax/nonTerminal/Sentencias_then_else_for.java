package compiler.syntax.nonTerminal;

import compiler.CompilerContext;
import es.uned.lsi.compiler.intermediate.IntermediateCodeBuilder;
import es.uned.lsi.compiler.semantic.ScopeIF;

public class Sentencias_then_else_for extends Sentencia {
	
	public Sentencias_then_else_for() {
		super();
	}

	public void generateIntermediateCode(Bloque bloq) {
		ScopeIF scope = CompilerContext.getScopeManager().getCurrentScope();
		IntermediateCodeBuilder iCodeB = new IntermediateCodeBuilder(scope);
		iCodeB.addQuadruples(bloq.getIntermediateCode());
		temporalNull(bloq, iCodeB);
		
		this.setIntermediateCode(iCodeB.create());
		
	}

	public void generateIntermediateCode(Sentencia sent) {
		ScopeIF scope = CompilerContext.getScopeManager().getCurrentScope();
		IntermediateCodeBuilder iCodeB = new IntermediateCodeBuilder(scope);
		iCodeB.addQuadruples(sent.getIntermediateCode());
		
		temporalNull(sent, iCodeB);
		
		this.setIntermediateCode(iCodeB.create());
		
	}
	
}
