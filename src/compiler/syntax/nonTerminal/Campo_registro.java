//Clase que implementa un campo de registro
package compiler.syntax.nonTerminal;

public class Campo_registro extends NonTerminal {
	String identificador; //identificador del campo de registro
	Tipo_primitivo tipo; //tipo del campo de registro
	int linea; //linea donde se encuentra el campo de registro
	int columna; //columna donde se encuentra el campo de registro

	public Campo_registro () {
		this.identificador = null; 
		this.tipo = new Tipo_primitivo();
		this.linea = 0;
		this.columna = 0;
	}
	
	public void setValor(String id, Tipo_primitivo tp, int l, int c) {
		//establece los valores del identificador, tipo, linea y columna del campo de registro
		this.identificador=id;
		this.tipo=tp;
		this.linea=l;
		this.columna=c;
		
	}
	
	public void setID(String id) {
		//establece el identificador del campo de registro
		this.identificador=id;
	}
	
	public void setTipo(Tipo_primitivo tp) {
		//establece el tipo de datos del campo de registro
		this.tipo=tp;
	}
	
	public void setLinea(int l) {
		//establece la linea donde se encuentra el campo de registro
		this.linea=l;
	}
	
	public void setColumna(int c) {
		//establece la columna donde se encuentra el campo de registro
		this.columna=c;
	}
	
	public String getID() {
		//devuelve el iddentificador del campo de registro
		return this.identificador;
	}

	public Tipo_primitivo getTipo() {
		//devuelve el tipo de datos del campo de registro
		return this.tipo;
	}
	
	public int getLinea() {
		//devuelve la linea donde se encuentra el campo de registro
		return this.linea;
	}
	
	public int getColumna() {
		//devuelve la columna donde se encuentra el campo de registro
		return this.columna;
	}
}