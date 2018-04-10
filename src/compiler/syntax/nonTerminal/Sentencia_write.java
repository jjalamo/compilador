package compiler.syntax.nonTerminal;

import compiler.CompilerContext;
import compiler.code.TextManager;
import compiler.intermediate.Value;
import es.uned.lsi.compiler.intermediate.IntermediateCodeBuilder;
import es.uned.lsi.compiler.intermediate.TemporalIF;
import es.uned.lsi.compiler.semantic.ScopeIF;

public class Sentencia_write extends Sentencia {
	
	private Exp exp = null;
	private String literalString = "";
	private String keyText;
	private boolean sinParametros = false;
	
	
	
	public Sentencia_write() {
		super();
		this.exp = null;
		this.literalString = "";
	}
	
	public Sentencia_write(Exp e) {
		super();
		this.exp=e;
		this.literalString = "";
	}
	
	public Sentencia_write(String ls) {
		super();
		this.exp = null;
		this.literalString = ls;
		this.keyText = TextManager.getKeyText();
		TextManager.addText(keyText, literalString);
	}
	
	public Sentencia_write(boolean parametros) {
		super();
		this.sinParametros = true;
	}
	
	public void generateIntermediateCode() {
		ScopeIF scope = CompilerContext.getScopeManager().getCurrentScope();
		IntermediateCodeBuilder iCodeB = new IntermediateCodeBuilder(scope);
		
		if(this.sinParametros) {
			//WRITELN ( )
			iCodeB.addQuadruple("WRTLN", null);
		} else {
			if(this.exp != null) {
				// WRITE ( expresion )
				Exp expr = this.exp;
				iCodeB.addQuadruples(expr.getIntermediateCode());
				temporalNull(expr, iCodeB);
				TemporalIF eTemp = expr.getTemporal();
				iCodeB.addQuadruple("WRITE", eTemp);
			} else {
				if(!this.literalString.equals("")) {
					// WRITE ( cadena )
					iCodeB.addQuadruple("WRITE", new Value(this.keyText));
				} 
			}
		}
		
		this.setIntermediateCode(iCodeB.create());
	}
	
}
