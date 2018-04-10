//Clase que almacena informacion sobre la parte izq de una sentencia de asignacion
package compiler.syntax.nonTerminal;

import es.uned.lsi.compiler.semantic.symbol.SymbolIF;
import es.uned.lsi.compiler.semantic.type.TypeIF;

public class Sentencia_asignacion_izq extends NonTerminal {
	String nombre; //nombre de la referencia izq
	SymbolIF simbolo; //simbolo de la referencia izq
	TypeIF tipo; //tipo de la referencia izq
	int linea; //linea donde se ubica la referencia izq
	String nombreCampoRegistro; //nombre del campo de registro si la referencia izq es un registro
	TypeIF tipoCampoRegistro; // tipo del campo de registro si la referencia izq es un registro
	int desplazamiento; //desplazamiento del campo de registro
	String tipoReferencia; //indica el tipo de referencia (ID, ID^, ID.ID, ID.ID^)
	
	public Sentencia_asignacion_izq (String n, SymbolIF s, TypeIF t, int l,String tr) {
		super();
		this.nombre=n;
		this.simbolo=s;
		this.tipo=t;
		this.linea=l;
		this.nombreCampoRegistro=null;
		this.tipoCampoRegistro=null;
		this.tipoReferencia=tr;
		this.desplazamiento=0;
	}
	
	public void setNombre(String n) {
		//establece el nombre de la referencia izq 
		this.nombre=n;
	}
	
	public void setSimbolo(SymbolIF s) {
		//establece el simbolo de la referencia izq
		this.simbolo=s;
	}
	
	public void setTipo(TypeIF t) {
		//establece el tipo de la referencia izq
		this.tipo=t;
	}
	
	public void setLinea(int l) {
		//establece la linea de la referencia izq
		this.linea=l;
	}
	
	public void setNombreCampoRegistro(String n) {
		//establece el nombre del campo de registro si la referencia izq es un registro
		this.nombreCampoRegistro=n;
	}
	
	public void setTipoCampoRegistro(TypeIF t) {
		//establece el tipo del campo de registro si la referencia izq es un registro
		this.tipoCampoRegistro=t;
	}
	
	public void setTipoReferencia(String tr) {
		//establece que clase de referencia es
		this.tipoReferencia=tr;
	}
	
	public void setDesplazamineto(int d) {
		//establece el desplazamiento del campo de registro
		this.desplazamiento=d;
	}

	public String getNombre() {
		//devuelve el nombre de la referencia izq
		return this.nombre;
	}
	
	public SymbolIF getSimbolo() {
		//devuelve el simbolo de la referencia izq
		return this.simbolo;
	}
	
	public TypeIF getTipo() {
		//devuelve el tipo de la referencia izq
		return this.tipo;
	}
	
	public int getLinea() {
		//devuelve la linea de la referencia izq
		return this.linea;
	}
	
	public String getNombreCampoRegistro() {
		//devuelve el nombre del campo de registro si la referencia izq es un registro
		return this.nombreCampoRegistro;
	}
	
	public TypeIF getTipoCampoRegistro() {
		//devuelve el tipo del campo de registro si la referencia izq es un registtro
		return this.tipoCampoRegistro;
	}
	
	public String getTipoReferencia() {
		//devuelve la clase de referencia que es
		return this.tipoReferencia;
	}
	
	public int getDesplazamiento() {
		//devuelve el desplazamineto del campo de registro
		return this.desplazamiento;
	}
	
}
