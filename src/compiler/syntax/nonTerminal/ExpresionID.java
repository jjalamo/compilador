//Clase que implementa la expresion identificador
package compiler.syntax.nonTerminal;

import compiler.CompilerContext;
import compiler.intermediate.Variable;
import compiler.semantic.symbol.SymbolConstant;
import compiler.semantic.symbol.SymbolParameter;
import compiler.semantic.symbol.SymbolVariable;
import es.uned.lsi.compiler.intermediate.IntermediateCodeBuilder;
import es.uned.lsi.compiler.intermediate.TemporalFactory;
import es.uned.lsi.compiler.intermediate.TemporalIF;
import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.symbol.SymbolIF;
import es.uned.lsi.compiler.semantic.type.TypeIF;

public class ExpresionID extends Exp {

	String nombre;
	
	public ExpresionID(TypeIF tipo) {
		super(tipo);
	}
	
	public ExpresionID(TypeIF tipo, String n) {
		super(tipo);
		this.nombre=n;
	}
	
	public void generateIntermediateCode(SymbolIF identificador, String nombre) {
		ScopeIF scope = CompilerContext.getScopeManager().getCurrentScope();
		TemporalFactory tFact = new TemporalFactory(scope);
		TemporalIF temp = tFact.create();
		IntermediateCodeBuilder iCodeB = new IntermediateCodeBuilder(scope);

		if(identificador instanceof SymbolConstant) {
			if(((SymbolConstant) identificador).getValor().equals("TRUE")) {
				iCodeB.addQuadruple("MV",temp,1);
			} else {
				if(((SymbolConstant) identificador).getValor().equals("FALSE")) {
					iCodeB.addQuadruple("MV",temp,0);
				} else {
					iCodeB.addQuadruple("MV",temp,Integer.parseInt(((SymbolConstant) identificador).getValor()));
				}
			}
		}
		if((identificador instanceof SymbolVariable) || (identificador instanceof SymbolParameter)) {
			Variable var = new Variable(nombre,identificador.getScope());
			iCodeB.addQuadruple("MV",temp,var);

			
			
		}

		
		this.setTemporal(temp);
		this.setIntermediateCode(iCodeB.create());
	}
	
	public void setNombre(String n) {
		this.nombre=n;
	}
	
	public String getNombre() {
		return this.nombre;
	}
}
