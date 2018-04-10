//Clase que almacena un tipo de dato
package compiler.syntax.nonTerminal;

public class Tipo_datos extends NonTerminal {
	String tipo;

	public Tipo_datos () {
		this.tipo = null;
	}
	
	public void setValor(String v) {
		//establece el tipo de dato
		this.tipo = v;
		
	}
	public String getValor() {
		//devuelve el tipo de dato
		return this.tipo;
	}
}