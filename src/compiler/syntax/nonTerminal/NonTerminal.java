package compiler.syntax.nonTerminal;

import java.util.ArrayList;
import java.util.List;

import compiler.CompilerContext;
import es.uned.lsi.compiler.intermediate.IntermediateCodeBuilder;
import es.uned.lsi.compiler.intermediate.LabelIF;
import es.uned.lsi.compiler.intermediate.QuadrupleIF;
import es.uned.lsi.compiler.intermediate.TemporalFactory;
import es.uned.lsi.compiler.intermediate.TemporalIF;
import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.syntax.nonTerminal.NonTerminalIF;

/**
 * Abstract class for non terminals.
 */
public abstract class NonTerminal
    implements NonTerminalIF
{
    private List<QuadrupleIF> intermediateCode;
	private TemporalIF temporal;
	private LabelIF label;
    
    
    /**
     * Constructor for NonTerminal.
     */
    public NonTerminal ()
    {
        super ();
        this.intermediateCode = new ArrayList<QuadrupleIF> ();
        this.temporal = null;
    }

    /**
     * Returns the intermediateCode.
     * @return Returns the intermediateCode.
     */
    public List<QuadrupleIF> getIntermediateCode ()
    {
        return intermediateCode;
    }

    /**
     * Sets The intermediateCode.
     * @param intermediateCode The intermediateCode to set.
     */
    public void setIntermediateCode (List<QuadrupleIF> intermediateCode)
    {
        this.intermediateCode = intermediateCode;
    }
    
	public void setTemporal(TemporalIF t) {
		this.temporal=t;
	}

	public TemporalIF getTemporal() {
		return this.temporal;
	}
	
	public void setLabel(LabelIF l) {
		this.label = l;
	}
	
	public LabelIF getLabel() {
		return this.label;
	}
	
	protected void temporalNull(Exp exp, IntermediateCodeBuilder iCodeB) {
		if(exp.getTemporal() == null) {
			// se crea un nuevo temporal
			ScopeIF scope = CompilerContext.getScopeManager().getCurrentScope();
			TemporalFactory tF = new TemporalFactory(scope);
			TemporalIF temp = tF.create();
			
			//se asigna el nuevo temporal a la expresion
			exp.setTemporal(temp);
			
			//se genera el codigo intermedio
			iCodeB.addQuadruple("RETURN", temp);
		}
	}
	
	protected void temporalNull(Bloque exp, IntermediateCodeBuilder iCodeB) {
		if(exp.getTemporal() == null) {
			// se crea un nuevo temporal
			ScopeIF scope = CompilerContext.getScopeManager().getCurrentScope();
			TemporalFactory tF = new TemporalFactory(scope);
			TemporalIF temp = tF.create();
			
			//se asigna el nuevo temporal a la expresion
			exp.setTemporal(temp);
			
			//se genera el codigo intermedio
			iCodeB.addQuadruple("RETURN", temp);
		}
	}
	
	protected void temporalNull(Sentencia exp, IntermediateCodeBuilder iCodeB) {
		if(exp.getTemporal() == null) {
			// se crea un nuevo temporal
			ScopeIF scope = CompilerContext.getScopeManager().getCurrentScope();
			TemporalFactory tF = new TemporalFactory(scope);
			TemporalIF temp = tF.create();
			
			//se asigna el nuevo temporal a la expresion
			exp.setTemporal(temp);
			
			//se genera el codigo intermedio
			iCodeB.addQuadruple("RETURN", temp);
		}
	}
}