package compiler.lexical;

import compiler.syntax.sym;
import compiler.lexical.Token;
import es.uned.lsi.compiler.lexical.ScannerIF;
import es.uned.lsi.compiler.lexical.LexicalError;
import es.uned.lsi.compiler.lexical.LexicalErrorManager;

// incluir aqui, si es necesario otras importaciones

%%
 
%public				
%class Scanner
%char		
%line		
%column
%cup	

%implements ScannerIF
%scanerror LexicalError

// incluir aqui, si es necesario otras directivas
%ignorecase
%unicode 

Caracter			= [a-zA-Z]
Digito				= [0-9]
LiteralEntero 		= 0|([1-9]{Digito}*)
FinLinea			= \r|\n|\r\n
Separador			= [ \t]			

Identificador		= {Caracter}({Caracter}|{Digito})*

%{
  // Variable para almacenar las cadenas de caracteres encerradas entre comillas
  StringBuffer cadenaCaracteres = new StringBuffer();
  
  LexicalErrorManager lexicalErrorManager = new LexicalErrorManager ();
  private int commentCount = 0;

  private Token token(int tipo) 
    {
	 Token token = new Token (tipo);
         token.setLine (yyline + 1);
         token.setColumn (yycolumn + 1);
         token.setLexema (yytext ());
         return token;
    }
%}  

// Definición de estados adicionales al estado YYINITIAL
%state COMENTARIO_ENTRE_LLAVES
%state COMENTARIO_UNA_LINEA
%state CADENA_CARACTERES
  
%%

<YYINITIAL> 
{
	// Comentarios
	
	"\{"				{
							// Cambio de estado
						    yybegin(COMENTARIO_ENTRE_LLAVES);
                        }

	"//"					{
							// Cambio de estado
						    yybegin(COMENTARIO_UNA_LINEA);
                        }
	
	// Constantes literales
	
    {LiteralEntero}    {
                           Token token = new Token (sym.LITERAL_ENTERO);
                           token.setLine (yyline + 1);
                           token.setColumn (yycolumn + 1);
                           token.setLexema (yytext ());
           			       return token;
                        }
	
	// Los literales lógicos (true y false) se incluyen en el grupo de las palabras reservadas.
	
	\"					{
							// Limpiamos el StringBuffer donde guardaremos la cadena de caracteres
							cadenaCaracteres.setLength(0);
							// Cambio de estado
						    yybegin(CADENA_CARACTERES);
                        }

    // Palabras reservadas (especificación B)
    
    begin				{
    						return token(sym.BEGIN);
					}

    boolean				{
    						return token(sym.BOOLEAN);
							
					}	        
    const				{
    						return token (sym.CONST);
					}		

    do					{
    						return token (sym.DO);
					}	
						        
    else				{
    						return token (sym.ELSE);
					}
						        
    end					{
    						return token (sym.END);
					}

    false				{
    						return token (sym.FALSE);
					}
						        
    for					{
    						return token (sym.FOR);
					}
						        
    function				{
    						return token (sym.FUNCTION);
					}
						        
    if					{
    						return token (sym.IF);
					}
						        
    integer				{
    						return token (sym.INTEGER);
					}
						        
    or					{
    						return token (sym.OR);
					}
						        
    procedure				{
    						return token (sym.PROCEDURE);
					}
						        
    program				{
    						return token (sym.PROGRAM);
					}
						        
    record				{
    						return token (sym.RECORD);
					}
						        
    then				{
    						return token (sym.THEN);
					}
						        
    to					{
    						return token (sym.TO);
					}
						        
    true				{
    						return token (sym.TRUE);
					}
						        
    type				{
    						return token (sym.TYPE);
					}
						        
    var					{
    						return token (sym.VAR);
					}
						        
    write				{
    						return token (sym.WRITE);
					}
						        
    writeln				{
    						return token (sym.WRITELN);
					}
						
	// Delimitadores de la especificación B

    "\("				{
    						return token (sym.PARENT_ABRIR);
					}

    "\)"				{
    						return token (sym.PARENT_CERRAR);
					}

	"\}"				{
						   // Error, hemos encontrado un cierre de comentario sin la apertura previa
                           LexicalError error = new LexicalError ("Cierre de comentario encontrado sin apertura previa");
                           error.setLine (yyline + 1);
                           error.setColumn (yycolumn + 1);
                           error.setLexema (yytext ());
                           lexicalErrorManager.lexicalFatalError (error);
					}
						
    ","					{
    						return token (sym.COMA);
					}
						
    ";"					{
    						return token (sym.PUNTO_Y_COMA);
					}
						
    ":"					{
    						return token (sym.DOS_PUNTOS);
					}
						
    "="					{
    						return token (sym.IGUAL);
					}

	// Operadores de la especificación B
						
    "-"               			{  
                           			return token (sym.MENOS);
		                   	}
    
    ">"               			{  
			                        return token (sym.MAYOR_QUE);
                           		}
                       
    ":="                		{  
				           	return token (sym.DOS_PUNTOS_IGUAL);				           
				        }

	

     "\."                		{  
				          	return token (sym.PUNTO);
				        }

    "\^"                		{  
                           			return token (sym.CIRCUNFLEJO);
                          		}

    "\@"                		{  
                           			return token (sym.ARROBA);
                           		}

	// Identificadores 
	           			       
    {Identificador}    			{
                           			return token(sym.ID);
                           		}

	// Otros
	
    {FinLinea}    			{
    						// Ignorar
                        		}
    
    {Separador}    			{
    						// Ignorar
                       	 		}

    // error en caso de no coincidir con los patrones anteriores
    
	[^]                 		{                                               
				           LexicalError error = new LexicalError ("Token no reconocido: " + yytext());
				           error.setLine (yyline + 1);
				           error.setColumn (yycolumn + 1);
				           error.setLexema (yytext ());
				           lexicalErrorManager.lexicalFatalError (error);
                        		}
}

<COMENTARIO_ENTRE_LLAVES>
{
	"\}"				{
					
							yybegin(YYINITIAL);		
					}
						
	"\{"				{
						   
			                           LexicalError error = new LexicalError ("Comentario anidado encontrado");
                        			   error.setLine (yyline + 1);
						   error.setColumn (yycolumn + 1);
						   error.setLexema (yytext ());
						   lexicalErrorManager.lexicalFatalError (error);
					}

	<<EOF>>				{
						   
						   LexicalError error = new LexicalError ("Comentario no cerrado antes de fin de fichero");
						   error.setLine (yyline + 1);
						   error.setColumn (yycolumn + 1);
						   error.setLexema (yytext ());
						   lexicalErrorManager.lexicalFatalError (error);
					}	

    [^]		    			{
    						// Ignorar cualquier otro caracter dentro del comentario
                        		}
}

<COMENTARIO_UNA_LINEA>
{
	{FinLinea}			{
					
							yybegin(YYINITIAL);		
					}
						
	\{|\}				{

						   LexicalError error = new LexicalError ("Caracter no permitido en comentario de línea");
						   error.setLine (yyline + 1);
						   error.setColumn (yycolumn + 1);
						   error.setLexema (yytext ());
						   lexicalErrorManager.lexicalFatalError (error);
					}
	
    [^]					{
    						// Ignorar cualquier otro caracter dentro del comentario
                    		        }
}	

<CADENA_CARACTERES>
{
	\"				{
					
						    yybegin(YYINITIAL);
							
						    Token token = new Token (sym.LITERAL_CADENA);
						    token.setLine (yyline + 1);
						    token.setColumn (yycolumn + 1);
						    token.setLexema (cadenaCaracteres.toString());
		            			    return token;							
					}
						
	<<EOF>>				{
						 
						   LexicalError error = new LexicalError ("Cadena de caracteres no cerrada antes de fin de fichero");
						   error.setLine (yyline + 1);
						   error.setColumn (yycolumn + 1);
						   error.setLexema (yytext ());
						   lexicalErrorManager.lexicalFatalError (error);
					}	
	
    [^]					{
    						// Añadir al buffer de la cadena de caracteres
    						cadenaCaracteres.append(yytext());
                        		}
}

