import java.io.*;
import java.util.ArrayList;

public class A5
{
   private static ArrayList<Symbol> tokens = new ArrayList<Symbol>();
   private static int ptr = 0;


   public static void main(String[] args) throws Exception
   {
   	//construct the token array
   	BufferedWriter bw=new BufferedWriter(new FileWriter("a5.output"));
   	A5Scanner scanner = new A5Scanner(new FileInputStream(new File("A5.tiny")));
   	// note that yylex() is the default method to get the next token in scanner that is generated by JLlex.
   	Symbol token;
   	while ((token=scanner.yylex()).sym!= A5Sym.EOF)
      {
         //System.out.println(token.value);
   		tokens.add(token);
   	}
      //System.out.println(token.value);
   	tokens.add(token);   // add EOF as the last token in the array
   	boolean legal= program() && nextToken().sym==A5Sym.EOF;
   	bw.write((legal)?"legal":"illegal");
   	bw.close();
   }

   private static Symbol nextToken()
   {
      //System.out.print(tokens.get(ptr).value);
      return tokens.get(ptr++);
   }


   /*
   *         PROGRAM STRUCTURES
   */
   private static boolean program() //Program -> MethodDecl MethodDecl*
   {
      int ptr_back = ptr;

      if(MethodDecl() && program())
      {
         return true;
      }

      ptr = ptr_back;

      if(MethodDecl())
      {
         return true;
      }

      ptr = ptr_back;
      return false;
   }

   private static boolean type()
   {
      int ptr_back = ptr;

      int symbol = nextToken().sym;

      if(symbol == A5Sym.INT || symbol == A5Sym.REAL || symbol == A5Sym.STRING)
      {
         return true;
      }

      ptr = ptr_back;
      return false;
   }

   private static boolean MethodDecl()
   {
      int ptr_back = ptr;

      if(type() && optional(A5Sym.MAIN) && isthing(A5Sym.ID) && isthing(A5Sym.LEBRAC) && formalparams() && isthing(A5Sym.RIGBRAC) && block())
      {
         return true;
      }

      ptr = ptr_back;
      return false;
   }

   private static boolean optional(int term)
   {
      int ptr_back = ptr;

      if(nextToken().sym != term)
      {
         ptr = ptr_back;
         return true;
      }
      return true;
   }

   private static boolean or(int x, int y)
   {
      int ptr_back = ptr;

      int sym = nextToken().sym;

      if(sym == x || sym == y)
      {
         return true;
      }
      ptr = ptr_back;
      return false;
   }

   private static boolean isthing(int term)
   {
      return nextToken().sym == term;
   }

   private static boolean formalparams()
   {
      int ptr_back = ptr;
      if (formalparam() && _formalparams())
      {

         return true;
      }
      ptr = ptr_back;
      return true;
   }

   private static boolean _formalparams()
   {
      int ptr_back = ptr;
      if(isthing(A5Sym.COMMA) && formalparams())
      {
         return true;
      }
      ptr = ptr_back;
      return true;
   }

   private static boolean formalparam()
   {
      int ptr_back = ptr;
      if(type() && isthing(A5Sym.ID))
      {
         return true;
      }

      ptr = ptr_back;
      return false;
   }

   /*
   *         STATEMENTS
   */
   private static boolean block()
   {
      int ptr_back = ptr;
      if(isthing(A5Sym.BEGIN) && statement() && _statement() && isthing(A5Sym.END))
      {
         return true;
      }
      ptr = ptr_back;
      return false;
   }

   private static boolean _statement()
   {
      int ptr_back = ptr;
      if(statement() && _statement())
      {
         return true;
      }
      ptr = ptr_back;
      return true;
   }

   private static boolean statement()
   {
      int ptr_back = ptr;

      if(block())
      {
         return true;
      }

      ptr = ptr_back;
      if(LocalVarDecl())
      {
         return true;
      }

      ptr = ptr_back;
      if(AssignStmt())
      {
         return true;
      }

      ptr = ptr_back;
      if(ReturnStmt())
      {
         return true;
      }

      ptr = ptr_back;
      if(IfStmt())
      {
         return true;
      }

      ptr = ptr_back;
	   if(WriteStmt())
      {
         return true;
      }

      ptr = ptr_back;
	   if(ReadStmt())
      {
         return true;
      }
      ptr = ptr_back;
      return false;
   }

   private static boolean LocalVarDecl()
   {
      int ptr_back = ptr;

      if(type() && isthing(A5Sym.ID) && isthing(A5Sym.SCOLON))
      {
         return true;
      }

      ptr = ptr_back;
      if(type() && AssignStmt())
      {
         return true;
      }
      ptr = ptr_back;
      return false;
   }

   private static boolean AssignStmt()
   {
      int ptr_back = ptr;

      if(isthing(A5Sym.ID) && isthing(A5Sym.COLEQUAL) && expr() &&isthing(A5Sym.SCOLON))
      {
         return true;
      }
      ptr = ptr_back;

      if(isthing(A5Sym.ID) && isthing(A5Sym.COLEQUAL) && isthing(A5Sym.QUOTATIONS) &&isthing(A5Sym.SCOLON))
      {
         return true;
      }

      ptr = ptr_back;
      return false;
   }

   private static boolean ReturnStmt()
   {
      int ptr_back = ptr;

      if(isthing(A5Sym.RETURN) && expr() && isthing(A5Sym.SCOLON))
      {
         return true;
      }

      ptr = ptr_back;
      return false;
   }

   private static boolean IfStmt()
   {
      int ptr_back = ptr;

      if(isthing(A5Sym.IF) && isthing(A5Sym.LEBRAC) && boolexp() && isthing(A5Sym.RIGBRAC) && statement() && isthing(A5Sym.ELSE) && statement())
      {
         return true;
      }
      ptr = ptr_back;
      if(isthing(A5Sym.IF) && isthing(A5Sym.LEBRAC) && boolexp() && isthing(A5Sym.RIGBRAC) && statement())
      {
         return true;
      }

      ptr = ptr_back;
      return false;
   }

   private static boolean WriteStmt()
   {
      int ptr_back = ptr;

      if(isthing(A5Sym.WRITE) && isthing(A5Sym.LEBRAC) && expr() && isthing(A5Sym.COMMA) && isthing(A5Sym.QUOTATIONS) && isthing(A5Sym.RIGBRAC) && isthing(A5Sym.SCOLON))
      {
         return true;
      }

      ptr = ptr_back;
      return false;
   }

   private static boolean ReadStmt()
   {
      int ptr_back = ptr;

      if(isthing(A5Sym.READ) && isthing(A5Sym.LEBRAC) && isthing(A5Sym.ID) && isthing(A5Sym.COMMA) && isthing(A5Sym.QUOTATIONS) && isthing(A5Sym.RIGBRAC) && isthing(A5Sym.SCOLON))
      {
         return true;
      }

      ptr = ptr_back;
      return false;
   }

   /*
   *         expressions
   */

   private static boolean priexp()
   {
      int ptr_back = ptr;

      if(isthing(A5Sym.NUMBER))
      {
         return true;
      }

      ptr = ptr_back;
      if(isthing(A5Sym.ID))
      {
         return true;
      }

      ptr = ptr_back;
      if(isthing(A5Sym.LEBRAC) && expr() && isthing(A5Sym.RIGBRAC))
      {
         return true;
      }

      ptr = ptr_back;
      if(isthing(A5Sym.ID) && isthing(A5Sym.LEBRAC) && actualparams() && isthing(A5Sym.RIGBRAC))
      {
         return true;
      }

      ptr = ptr_back;
      return false;
   }

   private static boolean boolexp()
   {
      int ptr_back = ptr;

      if(expr() && isthing(A5Sym.EQUAL) && expr())
      {
         return true;
      }

      ptr = ptr_back;
      if(expr() && isthing(A5Sym.NOTEQUAL) && expr())
      {
         return true;
      }

      ptr = ptr_back;
      return false;
   }

   private static boolean actualparams()
   {
      int ptr_back = ptr;

      if(expr() && _actualparams())
      {
         return true;
      }

      ptr = ptr_back;

      return true;
   }

   private static boolean _actualparams()
   {
      int ptr_back = ptr;

      if(isthing(A5Sym.COMMA) && actualparams())
      {
         return true;
      }

      ptr = ptr_back;

      return true;
   }

   private static boolean multexp()
   {
      int ptr_back = ptr;

      if(priexp() && _multexp())
      {
         return true;
      }

      ptr = ptr_back;

      return false;
   }

   private static boolean _multexp()
   {
      int ptr_back = ptr;

      if(or(A5Sym.MULT, A5Sym.DIVIDE) && multexp())
      {
         return true;
      }

      ptr = ptr_back;

      return true;
   }

   private static boolean expr()
   {
      int ptr_back = ptr;

      if(multexp() && _expr())
      {
         return true;
      }

      ptr = ptr_back;

      return false;
   }

   private static boolean _expr()
   {
      int ptr_back = ptr;

      if(or(A5Sym.PLUS, A5Sym.MINUS) && expr())
      {
         return true;
      }

      ptr = ptr_back;

      return true;
   }



}
