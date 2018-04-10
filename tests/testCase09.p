// subprogramas (paso por valor) funciones

program doce;



var
        z,v: integer;


function decrementa (x:integer):integer;

var
        y:integer;

        begin
            y:= x-1;
            decrementa:=y;

        end;

begin

        write ("SUBPROGRAMAS FUNCIONES");
        writeln();

        z:=3;
        v:= decrementa (z);

        write("v(2):");
        write(v);
end.
