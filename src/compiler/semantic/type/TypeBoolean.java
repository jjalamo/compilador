package compiler.semantic.type;

import es.uned.lsi.compiler.semantic.ScopeIF;

public class TypeBoolean extends TypeSimple {

	public TypeBoolean(ScopeIF scope) {
		super(scope,"BOOLEAN");
	}

	public TypeBoolean (ScopeIF scope, String name)
    {
        super (scope, name);
    } 

}
