package compiler.syntax.nonTerminal;

import compiler.CompilerContext;
import compiler.intermediate.Variable;
import es.uned.lsi.compiler.intermediate.IntermediateCodeBuilder;
import es.uned.lsi.compiler.intermediate.LabelFactory;
import es.uned.lsi.compiler.intermediate.LabelIF;
import es.uned.lsi.compiler.intermediate.TemporalFactory;
import es.uned.lsi.compiler.intermediate.TemporalIF;
import es.uned.lsi.compiler.semantic.ScopeIF;

public class Sentencia_for extends Sentencia {
	
	public Sentencia_for() {
		super();
	}
	
	public void generateIntermediateCode(Variable var, Exp expresion1, Exp expresion2, Sentencias_then_else_for sentencias) {
		ScopeIF scope = CompilerContext.getScopeManager().getCurrentScope();
		IntermediateCodeBuilder iCodeB = new IntermediateCodeBuilder(scope);
		TemporalFactory tFact = new TemporalFactory(scope);

		//sentencia de asignacion del bucle for
		iCodeB.addQuadruples(expresion1.getIntermediateCode());
		temporalNull(expresion1,iCodeB);
		TemporalIF temp1 = expresion1.getTemporal();
		iCodeB.addQuadruple("MV",var,temp1);
		
		//resto del bucle
		LabelFactory lFact = new LabelFactory();
		LabelIF l1 = lFact.create();
		LabelIF l2 = lFact.create();
		
		iCodeB.addQuadruple("INL",l1);
		iCodeB.addQuadruples(expresion2.getIntermediateCode());
		temporalNull(expresion2,iCodeB);
		TemporalIF temp2 = expresion2.getTemporal();
		
		//*****************
		TemporalIF temp4 = tFact.create();
		iCodeB.addQuadruple("ADD",temp4,temp2,1);
		iCodeB.addQuadruple("MV",temp2,temp4);
		//*********************
		
		iCodeB.addQuadruple("BRI",l2, var,temp2);
		
		
		iCodeB.addQuadruples(sentencias.getIntermediateCode());
		temporalNull(sentencias, iCodeB);
		
		TemporalIF temp3 = tFact.create();
		iCodeB.addQuadruple("ADD",temp3,var,1);
		iCodeB.addQuadruple("MV",var,temp3);
		iCodeB.addQuadruple("BR",l1);
		iCodeB.addQuadruple("INL",l2);
		
		this.setIntermediateCode(iCodeB.create());
	}
	

}
