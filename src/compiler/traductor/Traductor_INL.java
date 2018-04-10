package compiler.traductor;

import compiler.code.FrameManager;
import compiler.intermediate.Value;
import es.uned.lsi.compiler.intermediate.QuadrupleIF;

public class Traductor_INL extends Traductor {	
	
	protected void traducir(QuadrupleIF quadruple) 
	{
		// INL label, value   - procedimientos y funciones
		// INL label          - sentencias if Then, if The Else, for
		
		//Añade una etiqueta
		
		setInstruccion(quadruple.getResult() + ": ");
				
		if(quadruple.getFirstOperand() != null) 
		{
			//Si la etiqueta esta asociada a un procedimiento o una funcion
			//actualiza el nivel del Display activo, ya que la etiqueta indica
			// el inicio del subprograma
			Value value = (Value)quadruple.getFirstOperand();
			Integer iValue = (Integer)value.getValue();
			FrameManager.getInstancia().setNivelActual(iValue);
		}
	}
}
