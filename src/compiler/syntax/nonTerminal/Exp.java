//Clase que implementa una expresion general
package compiler.syntax.nonTerminal;

import es.uned.lsi.compiler.semantic.type.TypeIF;

public class Exp extends NonTerminal {
	private TypeIF tipo;

	public Exp(TypeIF tipoExp) {
		super();
		this.tipo=tipoExp;
	}
	
	public void setType(TypeIF tipoExp) {
		//establece el tipo de la expresion
		this.tipo=tipoExp;
	}
	
	public TypeIF getType() {
		//devuelve el tipo de la expresion
		return this.tipo;
	}
	
}
