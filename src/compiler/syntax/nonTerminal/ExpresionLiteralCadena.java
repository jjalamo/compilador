package compiler.syntax.nonTerminal;

import es.uned.lsi.compiler.semantic.type.TypeIF;

public class ExpresionLiteralCadena extends Exp{
	private String cadena;
	private boolean tieneParametro = true;
	
	public ExpresionLiteralCadena(TypeIF tipo) {
		super(tipo);
		this.cadena=null;
	}
	
	public ExpresionLiteralCadena(TypeIF tipo, String cad) {
		super(tipo);
		this.cadena=cad;
	}
	
	public void setCadena(String cad) {
		this.cadena=cad;
	}
	
	public String getCadena() {
		return this.cadena;
	}
	
	public void setConParametro() {
		this.tieneParametro = true;
	}
	
	public void setSinParametro() {
		this.tieneParametro = false;
	}
	
	public boolean getTieneParametro() {
		return this.tieneParametro;
	}
	
}
