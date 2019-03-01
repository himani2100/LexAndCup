import java.io.*;
import java_cup.runtime.*;
%%

%implements Scanner
%type Symbol
%function next_token
%class A3Scanner
%{
   Symbol _5000(int _5001) {
      return new Symbol(_5001);
   }
%}

%init{
   yybegin(_600);
%init}

NONE = (\011|\015|\012|" ")
%state _600, _601

%%
<_600>"/**" {yybegin(_601);}
<_601>"**/" {yybegin(_600);}
<_601>.|\r|\n { }

<_600>\"[^\"]*\" {return _5000(2);}

<_600>INT|REAL|STRING {return _5000(3);}
<_600>WRITE {return _5000(4);}
<_600>RETURN {return _5000(5);}
<_600>READ {return _5000(6);}
<_600>MAIN {return _5000(7);}
<_600>IF {return _5000(8);}
<_600>ELSE {return _5000(9);}
<_600>END {return _5000(10);}
<_600>BEGIN {return _5000(11);}

<_600>[a-zA-Z][a-zA-Z0-9]* {return _5000(12);}
<_600>[0-9]+(\.[0-9]+)? {return _5000(13);}


<_600>"(" {return _5000(14);}
<_600>")" {return _5000(15);}
<_600>";" {return _5000(16);}
<_600>"," {return _5000(17);}
<_600>"+" {return _5000(18);}
<_600>"-" {return _5000(19);}
<_600>"/" {return _5000(20);}
<_600>"*" {return _5000(21);}
<_600>"!=" {return _5000(22);}
<_600>"==" {return _5000(23);}
<_600>":=" {return _5000(24);}

{NONE} {}
