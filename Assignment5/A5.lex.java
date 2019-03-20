

class A5Scanner {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 128;
	private final int YY_EOF = 129;
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private boolean yy_at_bol;
	private int yy_lexical_state;

	A5Scanner (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	A5Scanner (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private A5Scanner () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int COMMENT = 1;
	private final int yy_state_dtrans[] = {
		0,
		46
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NOT_ACCEPT,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NOT_ACCEPT,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NOT_ACCEPT,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NOT_ACCEPT,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NOT_ACCEPT,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NOT_ACCEPT,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NOT_ACCEPT,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NOT_ACCEPT,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NO_ANCHOR,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NO_ANCHOR,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NO_ANCHOR,
		/* 69 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"3:9,4:2,3:2,4,3:18,4,30,5,3:5,24,25,2,28,27,29,23,1,22:10,32,26,3,31,3:3,11" +
",20,21,17,10,19,14,21,6,21:2,12,18,7,21:3,9,13,8,16,21,15,21:3,3:6,21:26,3:" +
"5,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,70,
"0,1,2,1:2,3,4,1:8,5,1:4,5:10,1:2,6,5,7,8,9,10,11,12,13,14,15,16,7,17,18,19," +
"20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41")[0];

	private int yy_nxt[][] = unpackFromString(42,33,
"1,2,3,-1,4,32,5,33:2,60,52,33:2,69,33,66,33:2,61,33,67,33,6,7,8,9,10,11,12," +
"13,36,38,40,-1:35,42,-1:36,33,37,33:11,15,33:3,-1:32,6,44,-1:15,33:17,-1:11" +
",32:4,14,32:27,-1:22,34,-1:12,48,-1:61,16,-1:7,33:2,20,33:14,-1:41,17,-1:7," +
"33:11,21,33:5,-1:41,18,-1:7,33:6,22,33:4,23,33:5,-1:12,19,-1:36,33:4,24,33:" +
"12,-1:16,33,25,33:15,-1:10,1,30,35,30:30,-1:6,33:4,26,33:12,-1:11,31,-1:37," +
"33,27,33:15,-1:16,33,28,33:15,-1:16,33:8,29,33:8,-1:16,33,39,33:4,54,33:10," +
"-1:16,33:2,64,33:2,41,33:11,-1:16,33:7,43,33:9,-1:16,45,33:16,-1:16,33:2,47" +
",33:14,-1:16,49,33:16,-1:16,33:3,50,33:13,-1:16,33,51,33:15,-1:16,33:4,53,3" +
"3:12,-1:16,33:5,55,33:11,-1:16,56,33:16,-1:16,33:8,57,33:8,-1:16,33:10,58,3" +
"3:6,-1:16,59,33:16,-1:16,33:3,62,33:13,-1:16,33:4,63,33:12,-1:16,33:3,65,33" +
":13,-1:16,33:2,68,33:14,-1:10");

	public Symbol yylex ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {
 return new Symbol(A5Sym.EOF);
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{return new Symbol(A5Sym.DIVIDE, yytext());}
					case -3:
						break;
					case 3:
						{return new Symbol(A5Sym.MULT, yytext());}
					case -4:
						break;
					case 4:
						{}
					case -5:
						break;
					case 5:
						{return new Symbol(A5Sym.ID, yytext());}
					case -6:
						break;
					case 6:
						{return new Symbol(A5Sym.NUMBER, yytext());}
					case -7:
						break;
					case 7:
						{return new Symbol(A5Sym.ERROR, yytext());}
					case -8:
						break;
					case 8:
						{return new Symbol(A5Sym.LEBRAC, yytext());}
					case -9:
						break;
					case 9:
						{return new Symbol(A5Sym.RIGBRAC, yytext());}
					case -10:
						break;
					case 10:
						{return new Symbol(A5Sym.SCOLON, yytext());}
					case -11:
						break;
					case 11:
						{return new Symbol(A5Sym.COMMA, yytext());}
					case -12:
						break;
					case 12:
						{return new Symbol(A5Sym.PLUS, yytext());}
					case -13:
						break;
					case 13:
						{return new Symbol(A5Sym.MINUS, yytext());}
					case -14:
						break;
					case 14:
						{return new Symbol(A5Sym.QUOTATIONS, yytext());}
					case -15:
						break;
					case 15:
						{return new Symbol(A5Sym.IF, yytext());}
					case -16:
						break;
					case 16:
						{return new Symbol(A5Sym.NOTEQUAL, yytext());}
					case -17:
						break;
					case 17:
						{return new Symbol(A5Sym.EQUAL, yytext());}
					case -18:
						break;
					case 18:
						{return new Symbol(A5Sym.COLEQUAL, yytext());}
					case -19:
						break;
					case 19:
						{yybegin(COMMENT);}
					case -20:
						break;
					case 20:
						{return new Symbol(A5Sym.INT, yytext());}
					case -21:
						break;
					case 21:
						{return new Symbol(A5Sym.END, yytext());}
					case -22:
						break;
					case 22:
						{return new Symbol(A5Sym.REAL, yytext());}
					case -23:
						break;
					case 23:
						{return new Symbol(A5Sym.READ, yytext());}
					case -24:
						break;
					case 24:
						{return new Symbol(A5Sym.ELSE, yytext());}
					case -25:
						break;
					case 25:
						{return new Symbol(A5Sym.MAIN, yytext());}
					case -26:
						break;
					case 26:
						{return new Symbol(A5Sym.WRITE, yytext());}
					case -27:
						break;
					case 27:
						{return new Symbol(A5Sym.BEGIN, yytext());}
					case -28:
						break;
					case 28:
						{return new Symbol(A5Sym.RETURN, yytext());}
					case -29:
						break;
					case 29:
						{return new Symbol(A5Sym.STRING, yytext());}
					case -30:
						break;
					case 30:
						{ }
					case -31:
						break;
					case 31:
						{yybegin(YYINITIAL);}
					case -32:
						break;
					case 33:
						{return new Symbol(A5Sym.ID, yytext());}
					case -33:
						break;
					case 34:
						{return new Symbol(A5Sym.NUMBER, yytext());}
					case -34:
						break;
					case 35:
						{ }
					case -35:
						break;
					case 37:
						{return new Symbol(A5Sym.ID, yytext());}
					case -36:
						break;
					case 39:
						{return new Symbol(A5Sym.ID, yytext());}
					case -37:
						break;
					case 41:
						{return new Symbol(A5Sym.ID, yytext());}
					case -38:
						break;
					case 43:
						{return new Symbol(A5Sym.ID, yytext());}
					case -39:
						break;
					case 45:
						{return new Symbol(A5Sym.ID, yytext());}
					case -40:
						break;
					case 47:
						{return new Symbol(A5Sym.ID, yytext());}
					case -41:
						break;
					case 49:
						{return new Symbol(A5Sym.ID, yytext());}
					case -42:
						break;
					case 50:
						{return new Symbol(A5Sym.ID, yytext());}
					case -43:
						break;
					case 51:
						{return new Symbol(A5Sym.ID, yytext());}
					case -44:
						break;
					case 52:
						{return new Symbol(A5Sym.ID, yytext());}
					case -45:
						break;
					case 53:
						{return new Symbol(A5Sym.ID, yytext());}
					case -46:
						break;
					case 54:
						{return new Symbol(A5Sym.ID, yytext());}
					case -47:
						break;
					case 55:
						{return new Symbol(A5Sym.ID, yytext());}
					case -48:
						break;
					case 56:
						{return new Symbol(A5Sym.ID, yytext());}
					case -49:
						break;
					case 57:
						{return new Symbol(A5Sym.ID, yytext());}
					case -50:
						break;
					case 58:
						{return new Symbol(A5Sym.ID, yytext());}
					case -51:
						break;
					case 59:
						{return new Symbol(A5Sym.ID, yytext());}
					case -52:
						break;
					case 60:
						{return new Symbol(A5Sym.ID, yytext());}
					case -53:
						break;
					case 61:
						{return new Symbol(A5Sym.ID, yytext());}
					case -54:
						break;
					case 62:
						{return new Symbol(A5Sym.ID, yytext());}
					case -55:
						break;
					case 63:
						{return new Symbol(A5Sym.ID, yytext());}
					case -56:
						break;
					case 64:
						{return new Symbol(A5Sym.ID, yytext());}
					case -57:
						break;
					case 65:
						{return new Symbol(A5Sym.ID, yytext());}
					case -58:
						break;
					case 66:
						{return new Symbol(A5Sym.ID, yytext());}
					case -59:
						break;
					case 67:
						{return new Symbol(A5Sym.ID, yytext());}
					case -60:
						break;
					case 68:
						{return new Symbol(A5Sym.ID, yytext());}
					case -61:
						break;
					case 69:
						{return new Symbol(A5Sym.ID, yytext());}
					case -62:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
