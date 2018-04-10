package compiler.semantic.type;

import compiler.syntax.nonTerminal.Secuencia_parametros;
import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.type.TypeIF;

/**
 * Class for TypeFunction.
 */

// TODO: Student work
//       Include properties to characterize function declarations

public class TypeFunction
    extends TypeProcedure
{   
	   Secuencia_parametros parametros;
	   TypeIF tipoRetorno;
	
    
    /**
     * Constructor for TypeFunction.
     * @param scope The declaration scope.
     */
    public TypeFunction (ScopeIF scope)
    {
        super (scope);
        this.parametros=null;
        this.tipoRetorno=null;
    }

    /**
     * Constructor for TypeFunction.
     * @param scope The declaration scope
     * @param name The name of the function.
     */
    public TypeFunction (ScopeIF scope, String name)
    {
        super (scope, name);
        this.parametros=null;
        this.tipoRetorno=null;
    }
    
    /**
     * Returns the size of the type.
     * @return the size of the type.
     */
    @Override
    public int getSize ()
    {
        // TODO: Student work
        return 1;
    }

    public void setParametros (Secuencia_parametros p) {
    	this.parametros=p;
    }
    
    public Secuencia_parametros getParametros() {
    	return this.parametros;
    }

    public void setTipoRetorno (TypeIF tr) {
    	this.tipoRetorno=tr;
    }
    
    public TypeIF getTipoRetorno() {
    	return this.tipoRetorno;
    }
    
}
