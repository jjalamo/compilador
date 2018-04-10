package compiler.traductor;

import java.util.Iterator;

import compiler.code.MemoryManager;
import compiler.code.TextManager;
import es.uned.lsi.compiler.intermediate.QuadrupleIF;

public class Traductor_INIT extends Traductor {
			
	protected void traducir(QuadrupleIF quadruple) {
		//Prepara el inicio del programa

		setInstruccion(String.format("%s /%s", "BR",MemoryManager.getNextGlobalAddress()));
		
		//ensambla los textos de salida
		setInstruccion("ORG " + MemoryManager.getNextTextAddress());
		
		StringBuilder instruccion = new StringBuilder();
		Iterator<String> iter = TextManager.getText().keySet().iterator();
		
		while(iter.hasNext()) {
			String key = iter.next();
			instruccion.append(key + ": " + "DATA" + " " + "\"" + TextManager.getText().get(key) + "\"" + "\n");
		}
		instruccion.append("\n");
		getTraduccion().append(instruccion.toString());
		
		setInstruccion("ORG " + MemoryManager.getNextGlobalAddress());
		
		//Inicia SP segun el tamaño del RA del programa principal 
		String scopePrincipal = MemoryManager.getNombreScopePrincipal();
		int sizeLocalMemory = MemoryManager.getSizeAllocatedMemoryScope(scopePrincipal);
		int offset = ADDRESS_FP - (sizeLocalMemory + 1);
		
		setInstruccion(String.format("%s #%s, %s", "MOVE", offset, ".SP"));
		setInstruccion(String.format("%s #%s, %s", "MOVE", ADDRESS_FRAME, ".R0"));
		setInstruccion(String.format("%s #%s, %s", "MOVE", ADDRESS_FP, "[.R0]"));
		
		//Inicia IX con la direccion del FP
		setInstruccion(String.format("%s #%s, %s", "MOVE", ADDRESS_FP, ".IX"));
		setInstruccion(String.format("%s .SP, .R8", "MOVE"));
		setInstruccion(String.format("%s .IX, .R9", "MOVE"));

		// Salta al codigo del programa
		setInstruccion(String.format("%s /%s", "BR", quadruple.getFirstOperand()));
		
	}
	


}
