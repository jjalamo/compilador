// Control de flujo for (sin cuerpo)

program trece;



var
        x,y,z,w: integer;


begin

        write ("CRONTROL FLUJO FOR");
        writeln();


       //simple
       write("x(01234):");
       for (x:=0 to 4) do
          write(x);


       // con cuerpo
       write("y(345):");
       for (y:=3 to 5) do
       begin
            write(y);

       end;

       //iteraccion con variable
       write("z(0123):");
       w:=3;
       for (z:=0 to w) do
             write(z);

end.
