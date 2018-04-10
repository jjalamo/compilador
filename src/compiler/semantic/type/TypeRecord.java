package compiler.semantic.type;

import java.util.ArrayList;

import compiler.syntax.nonTerminal.Campo_registro;
import compiler.syntax.nonTerminal.Tipo_primitivo;
import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.type.TypeBase;
import es.uned.lsi.compiler.semantic.type.TypeIF;





/**
 * Class for TypeRecord.
 */

// TODO: Student work
//       Include properties to characterize records

public class TypeRecord
    extends TypeBase
{   
	ArrayList<Campo_registro> valor;
	
    /**
     * Constructor for TypeRecord.
     * @param scope The declaration scope.
     */
    public TypeRecord (ScopeIF scope)
    {
        super (scope);
    }

    /**
     * Constructor for TypeRecord.
     * @param scope The declaration scope.
     * @param name The name of the type.
     */
    public TypeRecord (ScopeIF scope, String name)
    {   
        super (scope, name);
    }
   
    /**
     * Constructor for TypeRecord.
     * @param record The record to copy.
     */
    public TypeRecord (TypeRecord record)
    {
        super (record.getScope (), record.getName ());
    } 
    
    /**
     * Returns the size of the type.
     * @return the size of the type.
     */
    @Override
    public int getSize ()  {
    	//devuelve el tamaño
        // TODO: Student work
    	ArrayList<Campo_registro> listaCR;
		listaCR = this.valor;
		
		return listaCR.size();
    }
    
    

    public void setValor(ArrayList<Campo_registro> v) {
    	//añade la lista de campos
    	this.valor=v;
    }
    
    public ArrayList<Campo_registro> getValor() {
    	//devuelve la lista de campos
    	return this.valor;
    }
    
    public boolean contieneCampoRegistro(String nombreCampo) {
    	//comprueba si el campo nombreCampo esta en la lista de campos del registro
    	boolean res=false;
    	ArrayList<Campo_registro> listaCR;
		listaCR = this.valor;
		int i=0;
		Campo_registro campoR=null;
		String nombreCR=null;
		
		for(i=0;i<listaCR.size();i++) {
			campoR=listaCR.get(i);
			nombreCR=campoR.getID();
			if(nombreCR.equals(nombreCampo)) {
				res=true;
			}
		}
		return res;
    }
    
    public TypeIF tipoCampoRegistro(String nombreCampo) {
    	//devuelve el tipo del campo del resgistro "nombreCampo"
    	TypeIF res = null;
    	Tipo_primitivo tipoP;
    	ArrayList<Campo_registro> listaCR;
    	int i=0;
    	Campo_registro campoR=null;
    	String nombreCR=null;
    	    	
    	if(contieneCampoRegistro(nombreCampo)) {
    		listaCR=this.valor;
    		for(i=0;i<listaCR.size();i++) {
    			campoR=listaCR.get(i);
    			nombreCR=campoR.getID();
    			if(nombreCR.equals(nombreCampo)) {
    				tipoP=campoR.getTipo();
    				res=tipoP.getType();
    			}
    		}
    	}
    	return res;
    }
    
    public int  desplazamientoCampoRegistro(String nombreCampo) {
    	//devuelve del offset, el desplazamiento de del campo de resgistro "nombreCampo"
    	int res=0;
    	ArrayList<Campo_registro> listaCR;
		listaCR = this.valor;
		int i=0;
		Campo_registro campoR=null;
		String nombreCR=null;
		
		for(i=0;i<listaCR.size();i++) {
			campoR=listaCR.get(i);
			nombreCR=campoR.getID();
			if(nombreCR.equals(nombreCampo)) {
				res=i;
			}
		}
		return res;
    }
    
    public int  numCamposRegistro() {
    	//devuelve el numero de campos del registro
    	ArrayList<Campo_registro> listaCR;
		listaCR = this.valor;
		
		return listaCR.size();
    }
    
}
