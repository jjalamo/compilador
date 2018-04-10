package compiler.semantic.symbol;

import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.type.TypeIF;

/**
 * Class for SymbolFunction.
 */

// TODO: Student work
//       Include properties to characterize function calls

public class SymbolFunction
    extends SymbolProcedure
{
//	   Secuencia_parametros parametros; //alamcena la secuencia de parametros
	   TypeIF tipoRetorno; // almacena el tipo de retorno
      
    /**
     * Constructor for SymbolFunction.
     * @param scope The declaration scope.
     * @param name The symbol name.
     * @param type The symbol type.
     */
    public SymbolFunction (ScopeIF scope, 
                           String name,
                           TypeIF type)
    {
        super (scope, name, type);
        this.parametros=null;
        this.tipoRetorno=null;
    } 
    
    public void setTipoRetorno (TypeIF tr) {
    	this.tipoRetorno=tr;
    }
    
    public TypeIF getTipoRetorno() {
    	return this.tipoRetorno;
    }
    
}
