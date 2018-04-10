package compiler.semantic.symbol;

import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.symbol.SymbolBase;
import es.uned.lsi.compiler.semantic.type.TypeIF;

/**
 * Class for SymbolVariable.
 */

// TODO: Student work
//       Include properties to characterize variables

public class SymbolVariable extends SymbolBase {  
	private Integer address=0; //Almacena la direccion de memoria de la variable
	private Integer size=0; // Almacena el tamaño en memoria de la variable
   
    /**
     * Constructor for SymbolVariable.
     * @param scope The declaration scope.
     * @param name The symbol name.
     * @param type The symbol type.
     */
    public SymbolVariable (ScopeIF scope, 
                           String name,
                           TypeIF type)
    {
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
    	//Devuelve la direccion de memoria
    	return this.size;
    }
    
    public void setSize(Integer s) {
    	//Establece la direccion de memoria
    	this.size = s;
    }
    
}
