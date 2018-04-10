package compiler.traductor;
import compiler.intermediate.Temporal;
import compiler.intermediate.Variable;
import compiler.semantic.symbol.SymbolVariable;
import es.uned.lsi.compiler.intermediate.OperandIF;
import es.uned.lsi.compiler.intermediate.QuadrupleIF;
import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.symbol.SymbolIF;
import es.uned.lsi.compiler.semantic.symbol.SymbolTableIF;

public class Traductor_MVP extends Traductor{

	//  CI - MVP destino, operando1  --> mueve operando1 a la direccion de memoria
	// que almacena el destino.

	
	protected void traducir(QuadrupleIF quadruple) {


		int op1Address = 0;
		int destinoAddress = 0;
		
		OperandIF destino = quadruple.getResult();
		OperandIF operando1 = quadruple.getFirstOperand();

		// comprobamos el tipo del destino, Temporal o Variable y obtenemos su direccion
		if(destino instanceof Temporal) {
			Temporal des = (Temporal) destino;
			destinoAddress = des.getAddress();
		}
		
		if(destino instanceof Variable) {
			Variable des = (Variable) destino;
			String destinoNombre = des.getName();
			ScopeIF desScope = des.getScope();
			SymbolTableIF desTablaSimbolos = desScope.getSymbolTable();
			SymbolIF desSim = desTablaSimbolos.getSymbol(destinoNombre);
			SymbolVariable dess = (SymbolVariable)desSim;
			destinoAddress = dess.getAddress();
		}

		// Comprobamos el tipo del operando1, Variable Temporal o Value
		// y obtenemos su direccion (Variable Temporal) o su valor (Value)
		if (operando1 instanceof Variable) {
			Variable op1 = (Variable) operando1;
			String op1Nombre = op1.getName();
			ScopeIF op1Scope = op1.getScope();
			SymbolTableIF op1TablaSimbolos = op1Scope.getSymbolTable();
			SymbolIF op1Sim = op1TablaSimbolos.getSymbol(op1Nombre);
			SymbolVariable opp1 = (SymbolVariable)op1Sim;
			op1Address = opp1.getAddress();
		}
		
		if(operando1 instanceof Temporal) {
			Temporal op1 = (Temporal) operando1;
			op1Address = op1.getAddress();
		}

		// creamos la instruccion de CF MV segun el tipo
		// del operando1 y del destino
		if( (destino instanceof Temporal) && (operando1 instanceof Temporal)) {
			setInstruccion(String.format("%s #-%s[.IX], .R0", "MOVE", op1Address));
			setInstruccion(String.format("%s [.R0], #-%s[.IX]", "MOVE", destinoAddress));
		}
		
		if( (destino instanceof Temporal) && (operando1 instanceof Variable)) {
			setInstruccion(String.format("%s /%s, .R0", "MOVE", op1Address));
			setInstruccion(String.format("%s [.R0], #-%s[.IX]", "MOVE", destinoAddress));
		}
	}
}
