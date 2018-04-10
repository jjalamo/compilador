//Clase que almacena una lista de identificadores
package compiler.syntax.nonTerminal;

import java.util.ArrayList;

public class Secuencia_IDs extends NonTerminal {
	private ArrayList<Identificador> listaIDs;
	
	public Secuencia_IDs() {
		super();
		this.listaIDs = new ArrayList<Identificador>();
	}
	
	public void addId(Identificador id) {
		//añade un identificador a la lista de identificadores
		this.listaIDs.add(id);
	}
	
	public ArrayList<Identificador> getSecuenciaIds() {
		//devuelve la lista de identificadores
		return this.listaIDs;
	}
	
	public boolean buscarId(String id) {
		//indica si el identificador id esta o no en la lista de identificadores
		boolean encontrado=false;
		int i;
		String id_aux;
		Identificador identificador;
		
		for(i=0;i<this.listaIDs.size();i++) {
			identificador = this.listaIDs.get(i);
			id_aux = identificador.getNombre();
			if(id.equals(id_aux)){
				encontrado=true;
			}
		}
		return encontrado;
	}
	
	public int numIds () {
		//devuelve el numero de identificadores que contiene la lista de identificadores
		return this.listaIDs.size();
	}
	
	public Identificador getId(int pos) {
		//devuelve el identificador de la posicion pos de la lista de identificadores
				
		return this.listaIDs.get(pos);
	}
}
