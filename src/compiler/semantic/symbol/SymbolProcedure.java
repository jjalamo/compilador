package compiler.semantic.symbol;

import compiler.syntax.nonTerminal.Secuencia_parametros;
import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.symbol.SymbolBase;
import es.uned.lsi.compiler.semantic.type.TypeIF;

/**
 * Class for SymbolProcedure.
 */

// TODO: Student work
//       Include properties to characterize procedure calls

public class SymbolProcedure
    extends SymbolBase
{
	   Secuencia_parametros parametros; // almacena la secuencia de parametros
	   private Integer sizeParametros=0; //Tamaño en memoria de todos los parametros
	   
    /**
     * Constructor for SymbolProcedure.
     * @param scope The declaration scope.
     * @param name The symbol name.
     * @param type The symbol type.
     */
    public SymbolProcedure (ScopeIF scope, 
                            String name,
                            TypeIF type)
    {
    	super (scope, name, type);
    	this.parametros=null;
    	
      
    }
    
    public void setParametros (Secuencia_parametros p) {
    	this.parametros=p;
    }
    
    public Secuencia_parametros getParametros() {
    	return this.parametros;
    }
    
    public void setSizeParametros (Integer p) {
    	this.sizeParametros=p;
    }
    
    public Integer getsizeParametros() {
    	return this.sizeParametros;
    }
    
}
