//Clase que almacena informacion sobre una llamada a subprograma
package compiler.syntax.nonTerminal;

import es.uned.lsi.compiler.semantic.type.TypeIF;

public class LLamada_subprograma extends NonTerminal{
	String tipoSubprograma; //Tipo de subprograma PROCEDURE o FUNCTION
	String idSubprograma; //identificador del subprograma
	TypeIF tipoRetorno; //Tipo de retorno si es un subprograma FUNCTION
	int linea; //linea donde se encuentra el identificador del subprograma
	int columna; //columna donde se encuentra el identificador del subprograma
	Secuencia_parametros_llamada secParametrosLL; //Almacena los parametros de llamada
	
	public LLamada_subprograma() {
		super();
		tipoSubprograma=null;
		idSubprograma=null;
		tipoRetorno=null;
		linea=0;
		columna=0;
	}
	
	public void setTipoSubprograma(String tipo) {
		//establece el tipo de subprograma
		this.tipoSubprograma = tipo;
	}
	
	public void setIdSubprograma(String id) {
		//establece el identificador del subprograma
		this.idSubprograma = id;
	}
	
	public void setTipoRetorno(TypeIF tipoR) {
		//estable el tipo de dato de retorno si el subprograma es FUNCTION
		this.tipoRetorno=tipoR;
	}
	
	public void setLinea(int l) {
		//estable la linea donde se encuentra el identificador del subprograma
		this.linea=l;
	}
	
	public void setColumna(int c) {
		//establece la columna donde se encuentra el identificador del subprograma
		this.columna=c;
	}
	
	public void setParametrosLLamada(Secuencia_parametros_llamada pll) {
		this.secParametrosLL=pll;
	}
	
	public String getTipoSubprograma() {
		//devuelve el tipo de subprograma
		return this.tipoSubprograma;
	}
	
	public String getIdSubprograma() {
		//devuelve el identificador del subprograma
		return this.idSubprograma;
	}

	public TypeIF getTipoRetorno() {
		//devuelve el tipo de dato de retorno si el subprograma es FUNCTION
		return this.tipoRetorno;
	}
	
	public int getLinea() {
		//devuelve la linea donde se encuentra el identificador del subprograma
		return this.linea;
	}
	
	public int getColumna() {
		//devuelve la columna donde se encuentra el identificador del subprograma
		return this.columna;
	}
	
	public Secuencia_parametros_llamada getParametrosLLamada() {
		return this.secParametrosLL;
	}
	
	
}
