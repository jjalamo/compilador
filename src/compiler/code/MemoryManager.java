package compiler.code;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import compiler.CompilerContext;
import compiler.intermediate.Temporal;
import compiler.semantic.symbol.SymbolFunction;
import compiler.semantic.symbol.SymbolParameter;
import compiler.semantic.symbol.SymbolProcedure;
import compiler.semantic.symbol.SymbolVariable;
import compiler.semantic.symbol.SymbolVariableRetorno;
import compiler.semantic.type.TypeRecord;
import compiler.syntax.nonTerminal.ParametroFormal;
import compiler.syntax.nonTerminal.Secuencia_parametros;
import es.uned.lsi.compiler.intermediate.TemporalIF;
import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.symbol.SymbolIF;
import es.uned.lsi.compiler.semantic.type.TypeIF;
import es.uned.lsi.compiler.semantic.type.TypeTableIF;

public class MemoryManager {
	// nextGlobalAddress representa a la primera dirección global sin asignar.
	// Esta comienza por 2, debido a que las dos primeras direcciónes están
	// asignadas a la intruccón BR /offset, donde offset es la primera 
	// instrucción del código del programa. 
	private static int nextGlobalAddress = 2;

	// nextTextAddress representa siguiente direccion sin asignar para textos de a mostrar.
	private static int nextTextAddress = 0;

	// Nombre identificativo para el ámbito principal
	private static String nombreScopePrincipal;

	//memoria local reservada
	private static LinkedHashMap<String, Integer> sizeAllocatedMemoryScope = new LinkedHashMap<String, Integer>();
	
	public static void mapAddresses() {
		// Realiza el mapa de memoria
		
		int nextLocalVariableAddress  = 0;
		int nextLocalParameterAddress = 0;

		// obtenemos todos los ambitos
		List<ScopeIF> allScopes = CompilerContext.getScopeManager().getAllScopes();

		for (ScopeIF scope : allScopes)	{
			//Obtenermos el nombre del ambito principal
			if (scope.getLevel() == 0) {
				nombreScopePrincipal = scope.getName();
			}

			// Para cada ambito, obtenermos todos sus simbolos
			List<SymbolIF> symbols = scope.getSymbolTable().getSymbols();
			TypeTableIF tablaTipos = scope.getTypeTable();
		
			// Obtenemos uno a uno todos los simbolos del ambito
			for (SymbolIF sIF : symbols) {
				
				//comprobamos si el simbolo es una variable
				if(sIF instanceof SymbolVariable) {
					SymbolVariable symbol = (SymbolVariable) sIF;
					
					//comprobamos si pertenece al ambito principal
					if(scope.getLevel() == 0) {
						//si pertenece al ambito principal, se trata de una variable global
						// se mapea en una direccion de memoria global
						
						//Comprobamos si la variable es de tipo registro o no
						if(symbol.getType() instanceof TypeRecord) {
							//obtenemos el nombre del tipo registro
							String nombreRecord = symbol.getType().getName();
							//obtenemos el tipo registro
							TypeIF tipoRegistro = tablaTipos.getType(nombreRecord);
							if(tipoRegistro instanceof TypeRecord) {
								TypeRecord tReg = (TypeRecord) tipoRegistro;
								//Calcula el tamaño del registro en funcion del numero de campos que tiene
								int numCampos = tReg.numCamposRegistro();
								//añade la direccion del registro y actualiza la proxima direccion me memoria libra
								symbol.setAddress(nextGlobalAddress);
								symbol.setSize(numCampos);
								nextGlobalAddress = nextGlobalAddress + numCampos;
							}
						} else {
							int size = symbol.getType().getSize();
							symbol.setAddress(nextGlobalAddress);
							symbol.setSize(size);
							//	se actualiza la proxima direccion global libre
							nextGlobalAddress = nextGlobalAddress + size;
						}
					} else {
						// Si no pertenece al ambito principal, se trata de una variable local
						// se mapea la variable en el marco de variables locales del RA
						// relativo al ambito en el que se encuentra la variable local
						// la direccion que se le asigna, sera el desplazamiento relativo 
						// al RA del ambito en el que se encuentra
						
						int size = symbol.getType().getSize();
						symbol.setAddress(nextLocalVariableAddress);
						symbol.setSize(size);
						// se actualiza la proxima direccion local libre
						nextLocalVariableAddress = nextLocalVariableAddress + size;
					}
					
				} else {
					// si el simbolo no es una variable, comprueba si es un procedimiento
					if(sIF instanceof SymbolProcedure) {
						//obtenemos la secuencia de parametros del procedimiento
						SymbolProcedure sP = (SymbolProcedure) sIF;
						Secuencia_parametros parametrosF = sP.getParametros();
						int numPF = parametrosF.numParametros();
						int i=0;
						int tama=0;
						ParametroFormal paramF=null;
						
						//Calculamos el tamaño de todos los parametros formales
						for(i=0;i<numPF;i++) {
							paramF = parametrosF.getParametro(i);
							int size = paramF.getTipo().getType().getSize();
							tama=tama + size;
						}
						sP.setSizeParametros(tama);
						nextLocalParameterAddress = 0;
					} else {
						// si el simbolo no es un procedimiento comprueba si es una funcion
						if(sIF instanceof SymbolFunction) {
							//obtenemos la secuencia de parametros de la funcion
							SymbolFunction sF = (SymbolFunction) sIF;
							Secuencia_parametros parametrosF = sF.getParametros();
							int numPF = parametrosF.numParametros();
							int i=0;
							int tama=0;
							ParametroFormal paramF=null;
							
							//Calculamos el tamaño de todos los parametros formales
							for(i=0;i<numPF;i++) {
								paramF = parametrosF.getParametro(i);
								int size = paramF.getTipo().getType().getSize();
								tama=tama + size;
							}
							sF.setSizeParametros(tama);
							nextLocalParameterAddress = 0;
						} else {
							// si el simbolo no es una funcion, comprueba si es un parametro
							if(sIF instanceof SymbolParameter) {
								SymbolParameter sParam = (SymbolParameter) sIF;
								int size = sParam.getType().getSize();
								sParam.setAddress(nextLocalParameterAddress);
								sParam.setSize(size);
								nextLocalParameterAddress = nextLocalParameterAddress + size;
							} else {
								//si el simbolo no es un parametro, comprueba si es una variable de retorno de funcion
								if(sIF instanceof SymbolVariableRetorno) {
									SymbolVariableRetorno sVR = (SymbolVariableRetorno) sIF;
									int size = sVR.getType().getSize();
									sVR.setAddress(0);
									sVR.setSize(size);
								}
							}
						}
					}
				}
			}

			// Gestionamos la lista de temporales
			List<TemporalIF> temporals = scope.getTemporalTable().getTemporals();
			
			for(TemporalIF tIF : temporals) {
				//se obtienen una a una todas las variables temporales, y se mapean como
				//una direccion local, como un desplazamiento relativo al ambito en el que
				// se declaran
				Temporal temporal = (Temporal) tIF;
				int size = temporal.getSize();
				temporal.setAddress(nextLocalVariableAddress);
				nextLocalVariableAddress = nextLocalVariableAddress + size;
			}
			
			// Se almacena el total de memoria mapeada en el ambito
			sizeAllocatedMemoryScope.put(scope.getName(), nextLocalVariableAddress);
			
			//se reinician los contadores de direccions locales
			nextLocalVariableAddress = 0;
			nextLocalParameterAddress = 0;
		}
		//Se reserva memoria para los textos de Write
		nextTextAddress = nextGlobalAddress;
		memoryAllocationTextAddresses();
	}

	private static void memoryAllocationTextAddresses() {
		//realiza el mapa de memoria para los textos a mostrar
		LinkedHashMap<String, String> texts = TextManager.getText();

		Iterator<String> it = texts.values().iterator();
		while (it.hasNext()) {
			nextGlobalAddress += it.next().length();
		}
	}

	public static int getNextGlobalAddress() {
		//devuelve la siguiente direccion global libre
		return nextGlobalAddress;
	}

	public static void setNextGlobalAddress(int nextGlobalAddress) 	{
		//establece la siguiente direccion global libre
		MemoryManager.nextGlobalAddress = nextGlobalAddress;
	}

	public static int getNextTextAddress() {
		//devuelve la siguiente direccion libre para textos
		return nextTextAddress;
	}

	public static void setNextTextAddress(int nextTextAddress) {
		//establece la siguiente direccion libre para textos
		MemoryManager.nextTextAddress = nextTextAddress;
	}

	public static int getSizeAllocatedMemoryScope(String name)	{
		//devuelve el tamaño de memoria reservada por un scope
		return sizeAllocatedMemoryScope.get(name);
	}

	public static String getNombreScopePrincipal() {
		//devuelve el nombre del scope principal
		return nombreScopePrincipal;
	}
}
