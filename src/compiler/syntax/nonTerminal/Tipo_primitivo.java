//Clase que almacena los tipos de datos primitivos
package compiler.syntax.nonTerminal;

import es.uned.lsi.compiler.semantic.type.TypeIF;

public class Tipo_primitivo extends NonTerminal {
	TypeIF tipo;

	public Tipo_primitivo () {
		this.tipo = null;
	}
	
	public Tipo_primitivo (TypeIF tipoP) {
		this.tipo = tipoP;
	}
	
	public void setType(TypeIF tipoP) {
		//Estable un tipo de dato primitivo
		this.tipo = tipoP;
		
	}
	public TypeIF getType() {
		//devuelve el tipo de dato primitivo
			return this.tipo;
	}
	
	public String getNombre() {
		//devuelve el nombre del tipo de dato primitivo
		return this.tipo.getName();
	}
}