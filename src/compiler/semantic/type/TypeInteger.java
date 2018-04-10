package compiler.semantic.type;

import es.uned.lsi.compiler.semantic.ScopeIF;

public class TypeInteger extends TypeSimple {

	public TypeInteger(ScopeIF scope) {
		super(scope,"INTEGER");
	}

	public TypeInteger (ScopeIF scope, String name)
    {
        super (scope, name);
    } 

}
