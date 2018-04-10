//Clase que almacena una lista de parametros de llamada
package compiler.syntax.nonTerminal;

import java.util.ArrayList;
import es.uned.lsi.compiler.semantic.type.TypeIF;

public class Secuencia_parametros_llamada extends NonTerminal {
	private ArrayList<Exp> listaParametros;
	
	public Secuencia_parametros_llamada() {
		super();
		this.listaParametros = new ArrayList<Exp>();

	}
	
	public void addParametro(Exp parametro) {
		//añade un parametro a la lista de parametros de llamada
		this.listaParametros.add(parametro);
	}
	
	
	public ArrayList<Exp> getSecuenciaParametros() {
		//devuelve la lista de parametros de llamada
		return this.listaParametros;
	}

	
	public int numParametros () {
		//devuelve el numero de parametros que contiene la lista de parametros de llamada
		return this.listaParametros.size();
	}
	
	public Exp getParametro(int pos) {
		//devuelve el parametro de la posicion pos de la lista de parametros de llamada
		return this.listaParametros.get(pos);
	}
	
	public TypeIF getTipoParametro(int pos) {
		//devuelve el tipo de parametro de la posicion pos de la lista de parametros de llamada
		return this.listaParametros.get(pos).getType();
	}
	
	public TypeIF getTipoParametro (Exp parametro) {
		//devuelve el tipo de parametro de un parametro concreto
		return parametro.getType();
	}
	
	public boolean parametrosCorrectos(Secuencia_parametros parametrosF) {
		//indica si la secuencia de parametros indicada es identica a la lista de parametros de llamada
		boolean res = true;
		int i;
		TypeIF tipoPF = null;
		TypeIF tipoPA = null;
		
		if(this.listaParametros.size() == parametrosF.numParametros()) {
			for(i=0;i<this.listaParametros.size();i++) {
				tipoPF = parametrosF.getTipoParametro(i);
				tipoPA = this.listaParametros.get(i).getType();

				if(!tipoPA.getName().equals(tipoPF.getName())) {
					res = false;
				}
			}
		} else {
			res = false;
		}
		return res;
	}

}
