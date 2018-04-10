//Clase que almacena una lista de las referencias de sentencias de asignacion
package compiler.syntax.nonTerminal;

import java.util.ArrayList;

public class Secuencia_sentencias extends NonTerminal{
	private ArrayList<String> listaReferencias;
	private ArrayList<Sentencia> listaSentencias;
	
	public Secuencia_sentencias() {
		super();
		this.listaReferencias=new ArrayList<String>();
		this.listaSentencias=new ArrayList<Sentencia>();
	}
	
	public void addReferencia(String ref) {
		//añade una referencia a la lista
		this.listaReferencias.add(ref);
	}
	
	public ArrayList<String> getListaReferencias() {
		//devuelve la lista de referencias
		return this.listaReferencias;
	}
	
	public boolean contieneReferencia(String referencia) {
		//indica si la lista contiene una determinada referencia
		boolean res=false;
		
		if(this.listaReferencias.contains(referencia)) {
			res = true;
		}
		return res;
	}
	
	public void addSentencia(Sentencia s) {
		this.listaSentencias.add(s);
	}
	
	public ArrayList<Sentencia> getListaSentencias() {
		return this.listaSentencias;
	}

	public int numSentencias() {
		return this.listaSentencias.size();
	}
	
	public Sentencia getSentencia(int pos) {
		return this.listaSentencias.get(pos);
	}
}
