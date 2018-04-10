//Clase que implementa una expresion de tipo puntero @
package compiler.syntax.nonTerminal;

import compiler.CompilerContext;
import compiler.intermediate.Variable;
import compiler.semantic.symbol.SymbolParameter;
import compiler.semantic.symbol.SymbolVariable;
import es.uned.lsi.compiler.intermediate.IntermediateCodeBuilder;
import es.uned.lsi.compiler.intermediate.TemporalFactory;
import es.uned.lsi.compiler.intermediate.TemporalIF;
import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.symbol.SymbolIF;
import es.uned.lsi.compiler.semantic.type.TypeIF;

public class ExpresionPunteroArroba extends Exp {

	public ExpresionPunteroArroba(TypeIF tipo) {
		super(tipo);
	}

	public void generateIntermediateCode(SymbolIF identificador, String nombre) {
		ScopeIF scope = CompilerContext.getScopeManager().getCurrentScope();
		TemporalFactory tFact = new TemporalFactory(scope);
		TemporalIF temp = tFact.create();
		IntermediateCodeBuilder iCodeB = new IntermediateCodeBuilder(scope);

		if((identificador instanceof SymbolVariable) || (identificador instanceof SymbolParameter)) {
			Variable var = new Variable(nombre,identificador.getScope());
			iCodeB.addQuadruple("MVA",temp,var);
		}

		this.setTemporal(temp);
		this.setIntermediateCode(iCodeB.create());
		
	}
	
}