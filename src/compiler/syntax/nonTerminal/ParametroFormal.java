//clase que implementa un parametro formal
package compiler.syntax.nonTerminal;

public class ParametroFormal {
	private String nombre; 	// nombre del parametro
	Tipo_primitivo tipo; 	// tipo del parametro
	int linea;				// linea donde se encuentra el parametro
	int columna;			// columna donde se encuentra el parametro
	private Integer address;//Direccion de memoria del parametro
	
	public ParametroFormal () {
		this.nombre=null;
		this.tipo=null;
		this.linea=0;
		this.columna=0;
	}
	
	public ParametroFormal(String n, Tipo_primitivo t, int l, int c) {
		this.nombre=n;
		this.tipo=t;
		this.linea=l;
		this.columna=c;
	}

	public void setParametroFormal(String n, Tipo_primitivo t, int l, int c) {
		this.nombre=n;
		this.tipo=t;
		this.linea=l;
		this.columna=c;
	}
	
	public void setNombre(String n) {
		this.nombre=n;
	}
	
	public void setTipo(Tipo_primitivo t) {
		this.tipo=t;
	}
	
	public void setLinea(int l) {
		this.linea=l;
	}
	
	public void setColumna(int c) {
		this.columna=c;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public Tipo_primitivo getTipo() {
		return this.tipo;
	}
	
	public int getLinea() {
		return this.linea;
	}
	
	public int getColumna() {
		return this.columna;
	}
	
	public void setAddress(Integer a) {
		this.address=a;
	}
	
	public Integer getAddress() {
		return this.address;
	}
}
