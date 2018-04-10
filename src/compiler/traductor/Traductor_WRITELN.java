package compiler.traductor;

import es.uned.lsi.compiler.intermediate.QuadrupleIF;

public class Traductor_WRITELN extends Traductor {

	protected void traducir(QuadrupleIF quadruple) {
		// CI - WRTLN     
		setInstruccion("WRCHAR" + " #10");
		setInstruccion("WRCHAR" + " #13");
		
	}
	
}
