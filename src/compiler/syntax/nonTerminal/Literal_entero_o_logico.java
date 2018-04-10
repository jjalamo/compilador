//clase que almacen un literal entero o logico
package compiler.syntax.nonTerminal;

public class Literal_entero_o_logico extends NonTerminal{
	String valor=null;

	public Literal_entero_o_logico () {
		this.valor = new String();
	}
	
	public void setValor(String v) {
		//establece el valor del literal entero o logico
		this.valor = v;
		
	}
	
	public String getValor() {
		//devuelve el valor del literal entero o logico
		return this.valor;
	}
}