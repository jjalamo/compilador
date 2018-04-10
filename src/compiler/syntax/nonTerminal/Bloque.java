//Clase que implementa un bloque 
package compiler.syntax.nonTerminal;

public class Bloque extends NonTerminal {
	Secuencia_sentencias secuenciaSentencias; //secuencia de sentencias del bloque
	
	public Bloque() {
		super();
		this.secuenciaSentencias= new Secuencia_sentencias();
	}
	
	public void setSecuenciaSentencias(Secuencia_sentencias secsen) {
		//establece la secuencia de sentencias del bloque
		this.secuenciaSentencias=secsen;
	}

	public Secuencia_sentencias getSecuenciaSentencias() {
		//devuelve la secuencia de sentencias del bloque
		return this.secuenciaSentencias;
	}
}
