package compiler.semantic.symbol;

import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.symbol.SymbolBase;
import es.uned.lsi.compiler.semantic.type.TypeIF;

/**
 * Class for SymbolVariable.
 */

// TODO: Student work
//       Include properties to characterize parameters

public class SymbolParameter
    extends SymbolBase
{  
	private Integer address=0; //direccion de memoria del parametro
	private Integer size=0; //Tamaño en memoria del parametro
	
    /**
     * Constructor for SymbolParameter.
     * @param scope The declaration scope.
     * @param name The symbol name.
     * @param type The symbol type.
     */
    public SymbolParameter (ScopeIF scope, 
                           String name,
                           TypeIF type)
    {
        super (scope, name, type);
    } 
    
    public void setAddress(Integer add) {
    	this.address = add;
    }
    
    public Integer getAddress() {
    	return this.address;
    }

    public void setSize(Integer s) {
    	this.size=s;
    }
    
    public Integer getSize() {
    	return this.size;
    }

}
