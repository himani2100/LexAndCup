import java.io.*;
import java_cup.runtime.*;
%%

%implements Scanner
%type Symbol
%function next_token
%class A3Scanner
%eofval{ return null;
%eofval}

NONE = (\t|\r|\n|" ")
%state COMMENT

%%
<YYINITIAL>"/**" {yybegin(COMMENT);}
<COMMENT>"**/" {yybegin(YYINITIAL);}
<COMMENT>.|\r|\n { }

<YYINITIAL>\"[^\"]*\" {return new Symbol(A3Symbol.QUOTATIONS);}

<YYINITIAL>INT|REAL|STRING {return new Symbol(A3Symbol.TYPE);}
<YYINITIAL>WRITE {return new Symbol(A3Symbol.WRITE);}
<YYINITIAL>RETURN {return new Symbol(A3Symbol.RETURN);}
<YYINITIAL>READ {return new Symbol(A3Symbol.READ);}
<YYINITIAL>MAIN {return new Symbol(A3Symbol.MAIN);}
<YYINITIAL>IF {return new Symbol(A3Symbol.IF);}
<YYINITIAL>ELSE {return new Symbol(A3Symbol.ELSE);}
<YYINITIAL>END {return new Symbol(A3Symbol.END);}
<YYINITIAL>BEGIN {return new Symbol(A3Symbol.BEGIN);}

<YYINITIAL>[a-zA-Z][a-zA-Z0-9]* {return new Symbol(A3Symbol.ID);}
<YYINITIAL>[0-9]+(\.[0-9]+)? {return new Symbol(A3Symbol.NUMBER);}


<YYINITIAL>"(" {return new Symbol(A3Symbol.LEBRAC);}
<YYINITIAL>")" {return new Symbol(A3Symbol.RIGBRAC);}
<YYINITIAL>";" {return new Symbol(A3Symbol.SCOLON);}
<YYINITIAL>"," {return new Symbol(A3Symbol.COMMA);}
<YYINITIAL>"+" {return new Symbol(A3Symbol.PLUS);}
<YYINITIAL>"-" {return new Symbol(A3Symbol.MINUS);}
<YYINITIAL>"/" {return new Symbol(A3Symbol.DIVIDE);}
<YYINITIAL>"*" {return new Symbol(A3Symbol.MULT);}
<YYINITIAL>"!=" {return new Symbol(A3Symbol.NOTEQUAL);}
<YYINITIAL>"==" {return new Symbol(A3Symbol.EQUAL);}
<YYINITIAL>":=" {return new Symbol(A3Symbol.COLEQUAL);}

{NONE} {}
