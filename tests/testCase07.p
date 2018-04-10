// tipos PUNTEROS

program diez;



var
        pun: ^integer;
        x: integer;

begin
        write ("TIPOS PUNTEROS");
	writeln();

        x:=2;
        pun:=@x;
        write("pun(2):");
        write(pun^);


end.
