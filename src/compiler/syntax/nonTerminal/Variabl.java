package compiler.syntax.nonTerminal;

public class Variabl extends NonTerminal {
	private Secuencia_IDs listaIDs;
	private Tipo_datos tipo;
	
	public Variabl() {
		super();
		this.listaIDs=null;
		this.tipo=null;
	}
	
	public Variabl(Secuencia_IDs si, Tipo_datos td) {
		super();
		this.listaIDs=si;
		this.tipo=td;
	}
	
	public void setSecuenciaIDs(Secuencia_IDs si) {
		this.listaIDs=si;
	}
	
	public void setTipo(Tipo_datos td) {
		this.tipo=td;
	}
	
	public Secuencia_IDs getSecuenciaIDs() {
		return this.listaIDs;
	}
	
	public Tipo_datos getTipo(){
		return this.tipo;
	}

}
