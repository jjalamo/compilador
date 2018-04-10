//Clase que implemeta una expresion literal entero
package compiler.syntax.nonTerminal;


import compiler.CompilerContext;
import es.uned.lsi.compiler.intermediate.IntermediateCodeBuilder;
import es.uned.lsi.compiler.intermediate.TemporalFactory;
import es.uned.lsi.compiler.intermediate.TemporalIF;
import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.type.TypeIF;

public class ExpresionLiteralEntero extends Exp {
	private int valor;
	
	
	public ExpresionLiteralEntero(TypeIF tipo, Integer valorEntero) {
		super(tipo);
		this.valor=valorEntero;
	}
	
	public void setValor(int valorEntero) {
		//establece el valor de literal entero
		this.valor=valorEntero;
	}
	
	
	public int getValor() {
		//devuelve el valor del literal entero
		return this.valor;
	}
	
	public void generateIntermediateCode() {
		ScopeIF scope = CompilerContext.getScopeManager().getCurrentScope();
		TemporalFactory tFact = new TemporalFactory(scope);
		TemporalIF temp = tFact.create();
		IntermediateCodeBuilder iCodeB = new IntermediateCodeBuilder(scope);
		iCodeB.addQuadruple("MV",temp, this.valor);
		this.setTemporal(temp);
		this.setIntermediateCode(iCodeB.create());
	}
	
}


