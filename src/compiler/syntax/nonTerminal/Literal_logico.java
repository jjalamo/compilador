//Clase que almacena un literal logico
package compiler.syntax.nonTerminal;

public class Literal_logico extends NonTerminal {
	String valor;

	public Literal_logico () {
		this.valor = null;
	}
	
	public void setValor(String v) {
		//establece el valor del literal logico
		this.valor = v;
		
	}
	public String getValor() {
		//devuelve el valor del literal logico
		return this.valor;
	}
}