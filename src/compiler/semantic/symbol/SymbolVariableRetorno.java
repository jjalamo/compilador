package compiler.semantic.symbol;

import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.symbol.SymbolBase;
import es.uned.lsi.compiler.semantic.type.TypeIF;

public class SymbolVariableRetorno extends SymbolBase{
	private Integer address=0; //Almacena la direccion de memoria de retorno
	private Integer size=0; // Almacena el tamaño en memoria de la variable de retorno
	

    public SymbolVariableRetorno (ScopeIF scope, String name, TypeIF type) {
    	super (scope, name, type);
    } 
    public Integer getAddress() {
    	//Devuelve la direccion de memoria
    	return address;
    }
    
    public void setAddress(Integer a) {
    	//Establece la direccion de memoria
    	this.address=a;
    }
    

    public Integer getSize() {
    	//Devuelve el tamaño de la variable
    	return this.size;
    }
    
    public void setSize(Integer s) {
    	//Establece el tamaño de la variable
    	this.size = s;
    }
    
	
}
