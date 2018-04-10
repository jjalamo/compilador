// tipos estructurados RESGISTROS

program nueve;

type
        tipoPersona = record
                dni: integer;
                casado: boolean;
        end;

var
        persona1: tipoPersona;


begin
        write ("TIPOS ESTRUCTURADOS REGISTROS");
	writeln();

        persona1.dni:=12345;
        persona1.casado:=true;

        write ("persona1.dni(12345):");
        write (persona1.dni);

        write ("persona1.casado(true):");
        if (persona1.casado or false) then
                write("true");
        else
                write("fail");

end.
