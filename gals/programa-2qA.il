//programa fonte:
//
// begin
//  int lado, area;		(1) 
//
//  read (lado);		(2)
//  area = lado * lado;		(3)
//  print (area);		(4)
// end


.assembly extern mscorlib {}
.assembly programa{}
.module programa.exe

.class public _unica{
  .method static public void _principal(){
     .entrypoint

// cabeçalho do programa

   // linha (1): int lado, area;
   .locals(int64 lado, int64 area)

   // linha (2): read (lado);
   call string [mscorlib]System.Console::ReadLine()
   call int64  [mscorlib]System.Int64::Parse(string)
   stloc lado

   // linha (3): area = lado * lado;	
   ldloc lado
   conv.r8
   ldloc lado
   conv.r8
   mul
   conv.i8
   stloc area

   // linha (4): print (area);
   ldloc area 
   conv.r8
   conv.i8
   call void [mscorlib]System.Console::Write(int64)

// fim do programa
     ret
  }
}
