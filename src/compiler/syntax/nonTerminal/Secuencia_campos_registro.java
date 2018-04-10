//Clase que almacena una lista de campos de registro
package compiler.syntax.nonTerminal;

import java.util.ArrayList;

public class Secuencia_campos_registro extends NonTerminal {
	
	private ArrayList<Campo_registro> listadecampos;
	
	public Secuencia_campos_registro() {
		super ();
		this.listadecampos = new ArrayList<Campo_registro>();
	}
	
	public void addCampoR(Campo_registro cr) {
		//añade un campo a la lista de campos de registro
		this.listadecampos.add(cr);
	}
	
	public ArrayList<Campo_registro> getSecuenciaCamposR() {
		//devuelve la lista de campos de registro
		return this.listadecampos;
	}
	
	public boolean buscarCampoRegistro(Campo_registro cr) {
		//indica si un campo de registro concreto esta en la lista de campos de registro
		boolean encontrado=false;
		int i;
		Campo_registro cr_aux;
		
		for(i=0;i<this.listadecampos.size();i++) {
			cr_aux=this.listadecampos.get(i);
			if(cr.getID().equals(cr_aux.getID())) {
				encontrado=true;
			}
		}
		
		return encontrado;
	}
	
	public int numCamposRegistro () {
		//devuelve el numero de campos de la lista de campos de registro
		return this.listadecampos.size();
	}
}

