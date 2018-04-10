package compiler.traductor;

import java.util.List;

import compiler.code.FrameManager;
import compiler.code.MemoryManager;
import compiler.intermediate.Procedure;
import compiler.semantic.symbol.SymbolFunction;
import compiler.semantic.symbol.SymbolProcedure;
import compiler.syntax.nonTerminal.ParametroFormal;
import compiler.syntax.nonTerminal.Secuencia_parametros;
import compiler.syntax.nonTerminal.Tipo_primitivo;
import es.uned.lsi.compiler.intermediate.QuadrupleIF;
import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.symbol.SymbolIF;


public class Traductor_CALL extends Traductor {

	@Override
	protected void traducir(QuadrupleIF quadruple) {
		// Hace una llamada a un subprograma
		
		// Crea el RA
		setInstruccion(String.format("PUSH #0"));		
		setInstruccion(String.format("PUSH .IX"));
		setInstruccion(String.format("%s %s, %s", "MOVE", ".SP", ".IX"));
		setInstruccion(String.format("%s %s", "INC", ".IX"));
		Procedure proc = (Procedure)quadruple.getResult();	
		int offset = MemoryManager.getSizeAllocatedMemoryScope(proc.getName());
		setInstruccion(String.format("%s %s, #%s", "SUB", ".SP", offset));
		setInstruccion(String.format("%s %s, %s", "MOVE", ".A", ".SP"));
		int nivelSubprogramallamado = proc.getScope().getLevel() + 1; 
		int nivelSubprogramallamador = FrameManager.getInstancia().getNivelActual(); 
		offset = ADDRESS_FRAME + nivelSubprogramallamado;
		setInstruccion(String.format("%s %s, /%s", "MOVE", ".IX", offset));
		setInstruccion(String.format("%s .R9", "PUSH"));
		setInstruccion(String.format("%s .IX, .R9", "MOVE"));

		// Una vez creado el RA, ejecuta la llamada CALL
		setInstruccion(String.format("%s /%s", "CALL", proc.getCodeLabel()));
		
		// Una vez ejecutado el subprograma llamado,
		// elimina su RA y restaura el RA anterior
		setInstruccion(String.format("%s .R9", "POP"));
		offset = ADDRESS_FRAME + nivelSubprogramallamado;
		setInstruccion(String.format("%s %s, /%s", "MOVE", "[.IX]", offset));
		FrameManager.getInstancia().setNivelActual(nivelSubprogramallamador);	
		String nombreProc = proc.getName();
		ScopeIF scopeProc = proc.getScope();
		List<SymbolIF> symbols = scopeProc.getSymbolTable().getSymbols();
		int size=0;
		
		for (SymbolIF sIF : symbols) {
			if ((sIF instanceof SymbolProcedure) || (sIF instanceof SymbolFunction)) {
				String nombreSym = sIF.getName();
				if(nombreProc.equals(nombreSym)) {
					SymbolProcedure sP = (SymbolProcedure) sIF;
					Secuencia_parametros parametrosF = sP.getParametros();
					int numPF = parametrosF.numParametros();
					int i=0;
					ParametroFormal paramF=null;
					
					for(i=0;i<numPF;i++) {
						paramF = parametrosF.getParametro(i);
						Tipo_primitivo tipoPF = paramF.getTipo();
						size = size +  tipoPF.getType().getSize();
					}

				}
			}
			
		}
		offset = OFFSET_RETORNO + size;
		setInstruccion(String.format("%s %s, %s", "MOVE", ".IX", ".SP"));
		setInstruccion(String.format("%s %s, #%s", "ADD", ".SP", offset));
		setInstruccion(String.format("%s %s, %s", "MOVE", ".A", ".SP"));
		setInstruccion(String.format("%s %s, %s", "MOVE", "[.IX]", ".IX"));
		setInstruccion(String.format("%s .R9, .IX", "MOVE"));
	}
}
