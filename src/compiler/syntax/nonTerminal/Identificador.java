//Clase que implementa un identificador
package compiler.syntax.nonTerminal;

public class Identificador extends NonTerminal {
	private String nombre; //nombre del identificador
	private int linea; //linea del identificador
	private int columna; //columna del identificador

	public Identificador() {
		this.nombre=null;
		this.linea=0;
		this.columna=0;
	}
	
	public Identificador(String n, int l, int c) {
		this.nombre=n;
		this.linea=l;
		this.columna=c;
	}
	
	public void setNombre(String n) {
		//establece el nombre del identificador
		this.nombre=n;
	}
	
	public void setLinea(int l) {
		//establece la linea donde se encuentra el identificador
		this.linea=l;
	}
	
	public void setColumna(int c) {
		//establece la columna donde se encuentra el identificador
		this.columna=c;
	}
	
	public String getNombre() {
		//devuelve el nombre del identificador
		return this.nombre;
	}
	
	public int getLinea() {
		//devuelve la linea donde se encuetra el identificador
		return this.linea;
	}
	
	public int getColumna() {
		//devuelve la columna donde se encuentra el identificador
		return this.columna;
	}
}
