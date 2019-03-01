import java.io.*;
%%
%{

      static int com=0, id=0, quot=0, num=0, key=0;

      public static void main(String argv[]) throws Exception {

      A2 lexer = new A2(new FileReader("A2.input"));

      lexer.yylex();

      BufferedWriter writer = new BufferedWriter(new FileWriter("A2.output"));
      writer.write("Identifiers: "+id+"\n" + "Keywords: "+key+"\n" + "Numbers: "+num+"\n" +
      "Comments: "+com+"\n" + "quotedString: "+quot);

      writer.close();
      }
%}
%notunix
%type void
%class A2
%eofval{ return;
%eofval}

KEYWORD = WRITE|READ|IF|ELSE|RETURN|BEGIN|END|MAIN|STRING|INT|REAL
IDENTIFIER = [a-zA-Z][a-zA-Z0-9]*
QUOTATIONS = \"[^\"]*\"
NUMBER = [0-9]*(\.[0-9]+)?
COMMENTS = "/**"
NONE = (\r|\n|.)
%state COMMENT

%%
<YYINITIAL>{COMMENTS} {yybegin(COMMENT);}
<COMMENT>"**/" {yybegin(YYINITIAL); com++;}
<YYINITIAL>{QUOTATIONS} {quot++;}
{KEYWORD} {key++;}
{NUMBER} {num++;}
<YYINITIAL>{IDENTIFIER} {id++;}
{NONE} {}
