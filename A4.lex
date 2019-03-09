import java.io.*;
import java_cup.runtime.*;
%%

%implements Scanner
%type Symbol
%function next_token
%class A4Scanner
%eofval{ return null;
%eofval}

NONE = (\t|\r|\n|" ")
%state COMMENT

%%
<YYINITIAL>"/**" {yybegin(COMMENT);}
<COMMENT>"**/" {yybegin(YYINITIAL);}
<COMMENT>.|\r|\n { }

<YYINITIAL>\"[^\"]*\" {return new Symbol(A4Symbol.QUOTATIONS);}

<YYINITIAL>INT|REAL|STRING {return new Symbol(A4Symbol.TYPE);}
<YYINITIAL>WRITE {return new Symbol(A4Symbol.WRITE);}
<YYINITIAL>RETURN {return new Symbol(A4Symbol.RETURN);}
<YYINITIAL>READ {return new Symbol(A4Symbol.READ);}
<YYINITIAL>MAIN {return new Symbol(A4Symbol.MAIN);}
<YYINITIAL>IF {return new Symbol(A4Symbol.IF);}
<YYINITIAL>ELSE {return new Symbol(A4Symbol.ELSE);}
<YYINITIAL>END {return new Symbol(A4Symbol.END);}
<YYINITIAL>BEGIN {return new Symbol(A4Symbol.BEGIN);}

<YYINITIAL>[a-zA-Z][a-zA-Z0-9]* {return new Symbol(A4Symbol.ID);}
<YYINITIAL>[0-9]+(\.[0-9]+)? {return new Symbol(A4Symbol.NUMBER);}


<YYINITIAL>"(" {return new Symbol(A4Symbol.LEBRAC);}
<YYINITIAL>")" {return new Symbol(A4Symbol.RIGBRAC);}
<YYINITIAL>";" {return new Symbol(A4Symbol.SCOLON);}
<YYINITIAL>"," {return new Symbol(A4Symbol.COMMA);}
<YYINITIAL>"+" {return new Symbol(A4Symbol.PLUS);}
<YYINITIAL>"-" {return new Symbol(A4Symbol.MINUS);}
<YYINITIAL>"/" {return new Symbol(A4Symbol.DIVIDE);}
<YYINITIAL>"*" {return new Symbol(A4Symbol.MULT);}
<YYINITIAL>"!=" {return new Symbol(A4Symbol.NOTEQUAL);}
<YYINITIAL>"==" {return new Symbol(A4Symbol.EQUAL);}
<YYINITIAL>":=" {return new Symbol(A4Symbol.COLEQUAL);}

{NONE} {}
