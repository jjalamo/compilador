//Clase que almacena una lista de parametros
package compiler.syntax.nonTerminal;

import java.util.ArrayList;

import es.uned.lsi.compiler.semantic.type.TypeIF;

public class Secuencia_parametros extends NonTerminal {
	private ArrayList<ParametroFormal> listaParametros;
	
	public Secuencia_parametros() {
		super();
		this.listaParametros = new ArrayList<ParametroFormal>();
	}
	
	public void addParametro(ParametroFormal parametroF) {
		//añade un parametro a la lista de parametros
		this.listaParametros.add(parametroF);
	}
	
	public void addParametro(String n, Tipo_primitivo t, int l, int c) {
		ParametroFormal pf;
		pf = new ParametroFormal (n,t,l,c);
		this.listaParametros.add(pf);
	}
	
	public ArrayList<ParametroFormal> getSecuenciaParametros() {
		//devuelve la lista de parametros
		return this.listaParametros;
	}
	
	public boolean buscarParametro(String id) {
		//indica si la lista de parametros contiene un determinado parametro
		boolean encontrado=false;
		int i;
		ParametroFormal parametro_aux;
		String nombre;
		
		for(i=0;i<this.listaParametros.size();i++) {
			parametro_aux=this.listaParametros.get(i);
			nombre=parametro_aux.getNombre();
			if(nombre.equals(id)) {
				encontrado=true;
			}
		}
		return encontrado;
	}
	
	public int numParametros () {
		//devuelve el numero de parametros que contiene la lista de parametros
		return this.listaParametros.size();
	}
	
	public ParametroFormal getParametro(int pos) {
		//devuelve el parametro de la posicion pos en la lista de parametros
		return this.listaParametros.get(pos);
	}
	
	public TypeIF getTipoParametro(int pos) {
		//devuelve el tipo de parametro de la posicion pos de la lista de parametros
		ParametroFormal parametro;
		Tipo_primitivo tipoP;
		
		parametro = this.listaParametros.get(pos);
		tipoP = parametro.getTipo();
		return tipoP.getType();
	}

}
