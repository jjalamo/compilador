package compiler.intermediate;

import compiler.semantic.symbol.SymbolParameter;
import compiler.semantic.symbol.SymbolVariable;
import es.uned.lsi.compiler.intermediate.VariableIF;
import es.uned.lsi.compiler.semantic.ScopeIF;

/**
 * Class for variables in intermediate code.
 */

public class Variable
    implements VariableIF 
{
    private String  name     = null;
    private ScopeIF scope    = null;
    private SymbolVariable sVariable;
    private SymbolParameter sParametro;
    private boolean isParameter;
        
    /**
     * Constructor for Variable.
     * @param name The name.
     * @param scope The scope index.
     */
    public Variable (String name, ScopeIF scope)
    {
        super ();
        this.name = name;
        this.scope = scope;
    }
    
    public Variable (SymbolVariable symbol) {
    	this(symbol.getName(), symbol.getScope());
    	this.isParameter = false;
    	this.sVariable = symbol;
    }
    
    public Variable(SymbolParameter symbol) {
    	this(symbol.getName(), symbol.getScope());
    	this.isParameter = false;
    	this.sParametro = symbol;
    	
    }

    /**
     * Returns the name.
     * @return Returns the name.
     */
    @Override
    public final String getName ()
    {
        return name;
    }

    /**
     * Returns the scope.
     * @return Returns the scope.
     */
    @Override
    public final ScopeIF getScope ()
    {
        return scope;
    }

    /**
     * Returns the address.
     * @return Returns the address.
     */
    @Override
    public final int getAddress ()
    {
        if(isParameter) {
        	return this.sParametro.getAddress();
        } else {
        	return this.sVariable.getAddress();
        }
    }

    /**
     * Indicates whether the address is a global address.
     * @return true if the address is a global address.
     */
    @Override
    public final boolean isGlobal ()
    {
        // TODO : Student Work
        return (scope.getLevel() == 0);
    }

    public final boolean isNotLocal(int level) {
    	return (level != scope.getLevel());
    }
    
    public SymbolVariable getSymbolVariable() {
    	return this.sVariable;
    }
    
    public SymbolParameter getSymbolparameter() {
    	return this.sParametro;
    }
    
    public boolean isParameter() {
    	return this.isParameter;
    }
    
    /**
     * Compares this object with another one.
     * @param other the other object.
     * @return true if both objects has the same properties.
     */
    @Override
    public final boolean equals (Object other)
    {
        if (other == null) 
        {
            return false;
        }
        if (this == other)
        {
            return true;
        }
        if (!(other instanceof Variable))
        {
            return false;
        }
        
        final Variable aVariable = (Variable) other;
        return ((scope   == null) ? (aVariable.scope == null) : (aVariable.scope.equals (scope))) && 
               ((name    == null) ? (aVariable.name  == null) : (aVariable.name.equals (name)));
    }

    /**
     * Returns a hash code for the object.
     */
    @Override
    public final int hashCode ()
    {
        return 31 * scope.hashCode() +
                  ((name == null)? 0 : name.hashCode ());
    } 

    /**
     * Return a string representing the object.
     * @return a string representing the object.
     */
    @Override
    public final String toString ()
    {    
        return name;
    }
}
