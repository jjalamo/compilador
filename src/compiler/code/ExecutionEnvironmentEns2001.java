package compiler.code;

import java.util.Arrays;
import java.util.List;

import compiler.semantic.type.TypeSimple;

import compiler.traductor.Traductor;
import compiler.traductor.Traductor_ADD;
import compiler.traductor.Traductor_BR;
import compiler.traductor.Traductor_BRF;
import compiler.traductor.Traductor_BRI;
import compiler.traductor.Traductor_CALL;
import compiler.traductor.Traductor_EQ;
import compiler.traductor.Traductor_HALT;
import compiler.traductor.Traductor_INIT;
import compiler.traductor.Traductor_INL;
import compiler.traductor.Traductor_MQ;
import compiler.traductor.Traductor_MV;
import compiler.traductor.Traductor_MVA;
import compiler.traductor.Traductor_MVP;
import compiler.traductor.Traductor_OR;
import compiler.traductor.Traductor_PARAM;
import compiler.traductor.Traductor_RET;
import compiler.traductor.Traductor_RETURN;
import compiler.traductor.Traductor_STP;
import compiler.traductor.Traductor_SUB;
import compiler.traductor.Traductor_WRITE;
import compiler.traductor.Traductor_WRITELN;
import es.uned.lsi.compiler.code.ExecutionEnvironmentIF;
import es.uned.lsi.compiler.code.MemoryDescriptorIF;
import es.uned.lsi.compiler.code.RegisterDescriptorIF;
import es.uned.lsi.compiler.intermediate.QuadrupleIF;

/**
 * Class for the ENS2001 Execution environment.
 */

public class ExecutionEnvironmentEns2001 
    implements ExecutionEnvironmentIF
{    
    private final static int      MAX_ADDRESS = 65535; 
    private final static String[] REGISTERS   = {
       ".PC", ".SP", ".SR", ".IX", ".IY", ".A", 
       ".R0", ".R1", ".R2", ".R3", ".R4", 
       ".R5", ".R6", ".R7", ".R8", ".R9"
    };
    
    private RegisterDescriptorIF registerDescriptor;
    private MemoryDescriptorIF   memoryDescriptor;
    
    /**
     * Constructor for ENS2001Environment.
     */
    public ExecutionEnvironmentEns2001 ()
    {       
        super ();
    }
    
    /**
     * Returns the size of the type within the architecture.
     * @return the size of the type within the architecture.
     */
    @Override
    public final int getTypeSize (TypeSimple type)
    {      
        return 1;  
    }
    
    /**
     * Returns the registers.
     * @return the registers.
     */
    @Override
    public final List<String> getRegisters ()
    {
        return Arrays.asList (REGISTERS);
    }
    
    /**
     * Returns the memory size.
     * @return the memory size.
     */
    @Override
    public final int getMemorySize ()
    {
        return MAX_ADDRESS;
    }
           
    /**
     * Returns the registerDescriptor.
     * @return Returns the registerDescriptor.
     */
    @Override
    public final RegisterDescriptorIF getRegisterDescriptor ()
    {
        return registerDescriptor;
    }

    /**
     * Returns the memoryDescriptor.
     * @return Returns the memoryDescriptor.
     */
    @Override
    public final MemoryDescriptorIF getMemoryDescriptor ()
    {
        return memoryDescriptor;
    }

    /**
     * Translate a quadruple into a set of final code instructions. 
     * @param cuadruple The quadruple to be translated.
     * @return a quadruple into a set of final code instructions. 
     */
    @Override
    public final String translate (QuadrupleIF quadruple)
    {      
        //TODO: Student work
    	
    	Traductor traduccion = null;
    	String operador = quadruple.getOperation();
    	
    	switch (operador) {
    		case "INIT" :
    			traduccion = new Traductor_INIT();
    			break;
			
    		case "HALT" :
    			traduccion = new Traductor_HALT();
    			break;
    			
    		case "INL" :
    			traduccion = new Traductor_INL();
    			break;
    			
    		case "WRITE" :
    			traduccion = new Traductor_WRITE();
    			break;
    			
    		case "WRTLN" :
    			traduccion = new Traductor_WRITELN();
    			break;
    			
    		case "MV" :
    			traduccion = new Traductor_MV();
    			break;
    			
    		case "SUB" :
    			traduccion = new Traductor_SUB();
    			break;
    			
    		case "MVA" :
    			traduccion = new Traductor_MVA();
    			break;
    			
    		case "STP" :
    			traduccion = new Traductor_STP();
    			break;
    			
    		case "EQ" :
    			traduccion = new Traductor_EQ();
    			break;
    			
    		case "BRF" :
    			traduccion = new Traductor_BRF();
    			break;
    			
    		case "BR" :
    			traduccion = new Traductor_BR();
    			break;
    			
    		case "BRI" :
    			traduccion = new Traductor_BRI();
    			break;
    			
    		case "ADD" :
    			traduccion = new Traductor_ADD();
    			break;
    			
    		case "MVP" :
    			traduccion = new Traductor_MVP();
    			break;
    			
    		case "OR" :
    			traduccion = new Traductor_OR();
    			break;
    			
    		case "RET" :
    			traduccion = new Traductor_RET();
    			break;
    			
    		case "CALL" :
    			traduccion = new Traductor_CALL();
    			break;
    			
    		case "PARAM" :
    			traduccion = new Traductor_PARAM();
    			break;
    			
    		case "RETURN" :
    			traduccion = new Traductor_RETURN();
    			break;
    			
    		case "MQ" :
    			traduccion = new Traductor_MQ();
    			break;
    			
    		default : 
    			break;
    	}
    	
    	if(traduccion == null) {
    		return "";
    	} else {
    		return traduccion.getQuadrupleTrans(quadruple);
    	}
    	
    }
}
