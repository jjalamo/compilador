package compiler.semantic.symbol;

import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.symbol.SymbolBase;
import es.uned.lsi.compiler.semantic.type.TypeIF;

/**
 * Class for SymbolConstant.
 */

// TODO: Student work
//       Include properties to characterize constants

public class SymbolConstant
    extends SymbolBase
{
	private String valor;
	private Integer address=0; //Almacena la direccion de memoria de la constante
	private Integer size=0; // Almacena el tamaño en memoria de la constante
    
    /**
     * Constructor for SymbolConstant.
     * @param scope The declaration scope.
     * @param name The symbol name.
     * @param type The symbol type.
     */
    public SymbolConstant (ScopeIF scope,
                           String name,
                           TypeIF type)
    {
    
        super (scope, name, type);
    }
    
    public void setValor(String v) {
    	this.valor=v;
    }
    
    public String getValor() {
    	return this.valor;
    }
    
    public Integer getAddress() {
    	//Devuelve la direccion de memoria
    	return this.address;
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
