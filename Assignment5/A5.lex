%%

%type Symbol
%class A5Scanner
%eofval{ return new Symbol(A5Sym.EOF);
%eofval}


NONE = (\t|\r|\n|" ")
%state COMMENT

%%
<YYINITIAL>"/**" {yybegin(COMMENT);}
<COMMENT>"**/" {yybegin(YYINITIAL);}
<COMMENT>.|\r|\n { }

<YYINITIAL>\"[^\"]*\" {return new Symbol(A5Sym.QUOTATIONS, yytext());}

<YYINITIAL>INT {return new Symbol(A5Sym.INT, yytext());}
<YYINITIAL>REAL {return new Symbol(A5Sym.REAL, yytext());}
<YYINITIAL>STRING {return new Symbol(A5Sym.STRING, yytext());}

<YYINITIAL>WRITE {return new Symbol(A5Sym.WRITE, yytext());}
<YYINITIAL>RETURN {return new Symbol(A5Sym.RETURN, yytext());}
<YYINITIAL>READ {return new Symbol(A5Sym.READ, yytext());}
<YYINITIAL>MAIN {return new Symbol(A5Sym.MAIN, yytext());}
<YYINITIAL>IF {return new Symbol(A5Sym.IF, yytext());}
<YYINITIAL>ELSE {return new Symbol(A5Sym.ELSE, yytext());}
<YYINITIAL>END {return new Symbol(A5Sym.END, yytext());}
<YYINITIAL>BEGIN {return new Symbol(A5Sym.BEGIN, yytext());}

<YYINITIAL>[a-zA-Z][a-zA-Z0-9]* {return new Symbol(A5Sym.ID, yytext());}
<YYINITIAL>[0-9]+(\.[0-9]+)? {return new Symbol(A5Sym.NUMBER, yytext());}


<YYINITIAL>"(" {return new Symbol(A5Sym.LEBRAC, yytext());}
<YYINITIAL>")" {return new Symbol(A5Sym.RIGBRAC, yytext());}
<YYINITIAL>";" {return new Symbol(A5Sym.SCOLON, yytext());}
<YYINITIAL>"," {return new Symbol(A5Sym.COMMA, yytext());}
<YYINITIAL>"+" {return new Symbol(A5Sym.PLUS, yytext());}
<YYINITIAL>"-" {return new Symbol(A5Sym.MINUS, yytext());}
<YYINITIAL>"/" {return new Symbol(A5Sym.DIVIDE, yytext());}
<YYINITIAL>"*" {return new Symbol(A5Sym.MULT, yytext());}
<YYINITIAL>"!=" {return new Symbol(A5Sym.NOTEQUAL, yytext());}
<YYINITIAL>"==" {return new Symbol(A5Sym.EQUAL, yytext());}
<YYINITIAL>":=" {return new Symbol(A5Sym.COLEQUAL, yytext());}

{NONE} {}
<YYINITIAL>"." {return new Symbol(A5Sym.ERROR, yytext());}
