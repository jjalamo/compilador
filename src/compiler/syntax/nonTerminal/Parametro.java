//Clase que almacena informacion relativa a los parametros
package compiler.syntax.nonTerminal;

public class Parametro extends NonTerminal{
	Secuencia_IDs identificadores;
	Tipo_primitivo tipo;
	
	public Parametro () {
		this.identificadores = new Secuencia_IDs();
		this.tipo = new Tipo_primitivo();
	}
	
	public Parametro(Secuencia_IDs secIds, Tipo_primitivo tip) {
		this.identificadores=secIds;
		this.tipo=tip;
	}
	
	public void setIdentificadores (Secuencia_IDs secIds) {
		//establece una secuencia de identificadores de parametro
		this.identificadores=secIds;
	}
	
	public void setTipo (Tipo_primitivo tip) {
		//establece el tipo de parametro
		this.tipo=tip;
	}
	
	public Secuencia_IDs getIdentificadores() {
		//devuelve las secuencia de identifcadores de parametro
		return this.identificadores;
	}
	
	public Tipo_primitivo getTipo() {
		//devuelve el tipo de parametro
		return this.tipo;
	}

}
