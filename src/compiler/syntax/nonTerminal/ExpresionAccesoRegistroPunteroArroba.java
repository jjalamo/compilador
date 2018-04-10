//Clase que implemeta una expresion de acceso a registro con campo puntero @
package compiler.syntax.nonTerminal;

import java.util.ArrayList;

import compiler.CompilerContext;
import compiler.intermediate.Variable;
import compiler.semantic.type.TypeRecord;
import es.uned.lsi.compiler.intermediate.IntermediateCodeBuilder;
import es.uned.lsi.compiler.intermediate.TemporalFactory;
import es.uned.lsi.compiler.intermediate.TemporalIF;
import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.symbol.SymbolIF;
import es.uned.lsi.compiler.semantic.type.TypeIF;

public class ExpresionAccesoRegistroPunteroArroba extends Exp{

	public ExpresionAccesoRegistroPunteroArroba(TypeIF tipo) {
		super(tipo);
	}

	public int numCampo(TypeIF simbolo, String nombre) {
		int res = 0;
		int i=0;
		String identificador=null;
		Campo_registro campo;
		ArrayList<Campo_registro> listaCampos = null;
		
		listaCampos = ((TypeRecord) simbolo).getValor();
		
		for(i=0; i<listaCampos.size();i++) {
			campo=listaCampos.get(i);
			identificador=campo.getID();
			if(identificador.equals(nombre)) {
				res=i+1;
			}
		}
		return res;
	}
	
	public void generateIntermediateCode(SymbolIF identificador,String nombreRegistro, int desplazamiento) {
		ScopeIF scope = CompilerContext.getScopeManager().getCurrentScope();
		TemporalFactory tFact = new TemporalFactory(scope);
		TemporalIF temp = tFact.create();
		TemporalIF temp1 = tFact.create();
		TemporalIF dirInicio = tFact.create();
		TemporalIF dirCampo = tFact.create();
		IntermediateCodeBuilder iCodeB = new IntermediateCodeBuilder(scope);

		Variable varRegistro = new Variable(nombreRegistro,identificador.getScope());
		iCodeB.addQuadruple("MVA",dirInicio,varRegistro);
		iCodeB.addQuadruple("MV",temp1,desplazamiento);
		iCodeB.addQuadruple("ADD",dirCampo,dirInicio,desplazamiento);
		iCodeB.addQuadruple("MV",temp,dirCampo);
		this.setTemporal(temp);
		this.setIntermediateCode(iCodeB.create());
	}
	
	
}