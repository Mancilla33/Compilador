
package automatas.analizadorLS;

import java.util.*;

public class Lexico {

    private List tokens = new ArrayList<Token>();
    private String prog = "";

    private int ind;

    private List<String> reservadas = new ArrayList();
    // private final
    Analizador analizad;

    public Lexico(String programa, Analizador ana) {
        prog = programa;
        ind = 0;
        Listas();
        analizad = ana;
    }

    private String entra(char car) {
        String entra = "";
        if (car == 48)// es 0
            entra = "a";
        else if (car == 46) // punto
            entra = "b";
        else if (car > 47 && car < 58) // 1 al 9
            entra = "c";
        else if (car == 45)// signo menos
            entra = "d";
        else if (car == 95)// signo menos
            entra = "e";
        else if (car > 96 && car < 123) // a z
            entra = "f";
        else if (car > 64 && car < 91) // A Z
            entra = "g";
        else if (car == 34)// "
            entra = "h";
        else if (car == 39)// '
            entra = "i";
        else if (car == 32 || car == 9)// espacio
            entra = "j";
        else if (car == 10) // salto
            entra = "k";
        else if (car == 44) // ,
            entra = "l";
        else if (car == 59) // ;
            entra = "m";
        else if (car == 43) // +
            entra = "n";
        else if (car == 62) // >
            entra = "o";
        else if (car == 60) // <
            entra = "p";
        else if (car == 61) // =
            entra = "q";
        else if (car == 38) // &
            entra = "r";
        else if (car == 124) // |
            entra = "s";
        else if (car == 40) // (
            entra = "t";
        else if (car == 41) // )
            entra = "u";
        else if (car == 123) // {
            entra = "v";
        else if (car == 125) // }
            entra = "w";
        else if (car == 42) // *
            entra = "x";
        else if (car == 47) // /
            entra = "y";
        else if (car == 33) // !
            entra = "z";
        else if (car == 58) // :
            entra = "a1";
        else if (car == 36) // $
            entra = "b1";
        else if (car > 31 && car < 255) // Otro válido (Strings)
            entra = "c1";

        return entra;
    }

    public Token getToken() {
        int ini = ind;
        int fin = ind;
        int estado = 0;
        String token = "";
        Automata auto = new Automata();
        for (int i = ini; true; i++) {
            fin++;
            char car;
            String entra = "";
            if (i < prog.length()) {
                car = prog.charAt(i);
                entra = entra(car);
            } else {
                entra = "b1";
                car = ' ';
            }
            //// System.out.println("Caracter: "+car);
            // System.out.println(estado+ " "+ entra);

            estado = auto.tablaT.get(entra)[estado];
            if (estado == -1) {
                // System.out.println("Error léxico carácter no válido: "+car);
                analizad.AgregaError(lineas(fin) + "", "Error léxico carácter no válido: " + car);
                ind += 2;
                return null;
            }
            if (estado == 0)
                ini++;
            token = auto.acepta.get(estado);
            if (token != null) {
                String lex = "";
                switch (token) {
                    case "completo":
                        ind = fin;
                        lex = prog.substring(ini, fin);
                        return new Token(lex, lex, lineas(fin));
                    case "cad":
                        ind = fin;
                        lex = prog.substring(ini, fin);
                        return new Token("cad", lex, lineas(fin),"String");
                    case "operador":
                        ind = fin - 1;
                        lex = prog.substring(ini, fin - 1);
                        return new Token(lex, lex, lineas(fin - 1));
                    case "comentario":
                        ind = fin;
                        return getToken();
                    case "may":
                        ind = fin;
                        analizad.AgregaError(lineas(fin) + "",
                                "Error léxico los id deben comenzar con minúscula o guión bajo");
                        return getToken();
                    case "num":
                        ind = fin - 1;
                        lex = prog.substring(ini, fin - 1);
                        return new Token(token, lex, lineas(fin - 1),"int");
                    case "numD":
                        ind = fin - 1;
                        lex = prog.substring(ini, fin - 1);
                        return new Token("num", lex, lineas(fin - 1),"double");
                    default:
                        ind = fin - 1;
                        lex = prog.substring(ini, fin - 1);
                        if (token.equals("id")) {

                            if (reservadas.contains(lex)){
                                if(lex.equalsIgnoreCase("true")||lex.equalsIgnoreCase("false"))
                                return new Token(lex, lex, lineas(fin - 1),"bool");
                                return new Token(lex, lex, lineas(fin - 1));
                            }
                                

                        }

                        return new Token(token, lex, lineas(fin - 1));
                }

            }

            // acepta.put(99, "completo");//operadores y puntuación
            // acepta.put(50, "cad");
            // acepta.put(81,"operador");//idMetoto
            // acepta.put(82,"id");//id atributos
            // acepta.put(83,"idClas");//idClase
            // acepta.put(84,"num");//enteroy cero
            // acepta.put(85,"num");//double
            // acepta.put(86,"$");//fin
        }

    }

    private void Listas() {

        reservadas.add("int");
        reservadas.add("bool");
        reservadas.add("String");
        reservadas.add("double");
        reservadas.add("null");

        reservadas.add("while");
        reservadas.add("switch");
        reservadas.add("case");
        reservadas.add("default");
        reservadas.add("break");

        reservadas.add("print");
        reservadas.add("readLine");

        reservadas.add("true");
        reservadas.add("false");

    }

    private int lineas(int fin) {
        int count = 1;
        for (int i = 0; i < fin - 1 && i < prog.length(); i++) {
            if (prog.charAt(i) == 10) {
                count++;

            }
        }
        return count;
    }

    public List<Token> getListaTokens() {
        return tokens;
    }

}

class Token {
    String token;
    String lexema;
    int linea;
    String tipoDato;

    public Token(String token, String lexema, int linea) {
        this.token = token;
        this.lexema = lexema;
        this.linea = linea;

    }

    public Token(String token, String lexema, int linea, String tipoDato) {
        this.token = token;
        this.lexema = lexema;
        this.linea = linea;
        this.tipoDato= tipoDato; 

    }

    public String toString() {
        return token;
    }
}

class Automata {
    Map<String, int[]> tablaT = new HashMap();
    Map<Integer, String> acepta = new HashMap();
    Map<Integer, String> error = new HashMap();

    public Automata() {
        // 0 1 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18
        tablaT.put("a", new int[] { 7, 1, -2, -1, -1, 5, 81, -1, -1, 10, 10, -1, 81, 13, 14, 81, 81, 81, 18 });
        tablaT.put("b", new int[] { -1, 82, -2, -1, -1, 8, 81, 8, -1, 85, -1, -1, 81, 13, 14, 81, 81, 81, 18 });
        tablaT.put("c", new int[] { 5, 1, -2, -1, -1, 5, 81, -1, 9, 9, 9, -1, 81, 13, 14, 81, 81, 81, 18 });
        tablaT.put("d", new int[] { 6, 82, -2, -1, -1, 84, 99, 84, -1, 85, -1, -1, 81, 13, 14, 81, 81, 81, 18 });
        tablaT.put("e", new int[] { 3, 1, -2, -1, -1, 84, 81, 84, -1, 85, -1, -1, 81, 13, 14, 81, 81, 81, 18 });
        tablaT.put("f", new int[] { 1, 1, -2, 1, -1, 84, 81, 84, -1, 85, -1, -1, 81, 13, 14, 81, 81, 81, 18 });
        tablaT.put("g", new int[] { 1, 1, -2, 2, -1, 84, 81, 84, -1, 85, -1, -1, 81, 13, 14, 81, 81, 81, 18 });
        tablaT.put("h", new int[] { 13, 82, -2, -1, -1, 84, 81, 84, -1, 85, -1, -1, 81, 50, 14, 81, 81, 81, 18 });
        tablaT.put("i", new int[] { 14, 82, -2, -1, -1, 84, 81, 84, -1, 85, -1, -1, 81, 13, 50, 81, 81, 81, 18 });
        tablaT.put("j", new int[] { 0, 82, -2, -1, -1, 84, 81, 84, -1, 85, -1, -1, 81, 13, 14, 81, 81, 81, 18 });
        tablaT.put("k", new int[] { 0, 82, -2, -1, -1, 84, 81, 84, -1, 85, -1, -1, 81, 99, 99, 81, 81, 81, 0 });
        tablaT.put("l", new int[] { 99, 82, -2, -1, -1, 84, 81, 84, -1, 85, -1, -1, 81, 13, 14, 81, 81, 81, 18 });
        tablaT.put("m", new int[] { 99, 82, -2, -1, -1, 84, 81, 84, -1, 85, -1, -1, 81, 13, 14, 81, 81, 81, 18 });
        tablaT.put("n", new int[] { 15, 82, -2, -1, -1, 84, 81, 84, -1, 85, -1, -1, 81, 13, 14, 99, 81, 81, 18 });
        tablaT.put("o", new int[] { 12, 82, -2, -1, -1, 84, 81, 84, -1, 85, -1, -1, 81, 13, 14, 81, 81, 81, 18 });
        tablaT.put("p", new int[] { 12, 82, -2, -1, -1, 84, 81, 84, -1, 85, -1, -1, 81, 13, 14, 81, 81, 81, 18 });
        tablaT.put("q", new int[] { 16, 82, -2, -1, -1, 84, 81, 84, -1, 85, -1, -1, 99, 13, 14, 81, 99, 81, 18 });
        tablaT.put("r", new int[] { 11, 82, -2, -1, -1, 84, 81, 84, -1, 85, -1, 99, 81, 13, 14, 81, 81, 81, 18 });
        tablaT.put("s", new int[] { 4, 82, -2, -1, 99, 84, 81, 84, -1, 85, -1, -1, 81, 13, 14, 81, 81, 81, 18 });
        tablaT.put("t", new int[] { 99, 82, -2, -1, -1, 84, 81, 84, -1, 85, -1, -1, 81, 13, 14, 81, 81, 81, 18 });
        tablaT.put("u", new int[] { 99, 82, -2, -1, -1, 84, 81, 84, -1, 85, -1, -1, 81, 13, 14, 81, 81, 81, 18 });
        tablaT.put("v", new int[] { 99, 82, -2, -1, -1, 84, 81, 84, -1, 85, -1, -1, 81, 13, 14, 81, 81, 81, 18 });
        tablaT.put("w", new int[] { 99, 82, -2, -1, -1, 84, 81, 84, -1, 85, -1, -1, 81, 13, 14, 81, 81, 81, 18 });
        tablaT.put("x", new int[] { 99, 82, -2, -1, -1, 84, 81, 84, -1, 85, -1, -1, 81, 13, 14, 81, 81, 81, 18 });
        tablaT.put("y", new int[] { 17, 82, -2, -1, -1, 84, 81, 84, -1, 85, -1, -1, 81, 13, 14, 81, 81, 18, 18 });
        tablaT.put("z", new int[] { 12, 82, -2, -1, -1, 84, 81, 84, -1, 85, -1, -1, 81, 13, 14, 81, 81, 81, 18 });
        tablaT.put("a1", new int[] { 99, 82, -2, -1, -1, 84, 81, 84, -1, 85, -1, -1, 81, 13, 14, 81, 81, 81, 18 });
        tablaT.put("b1", new int[] { 86, 82, -2, -1, -1, 84, 81, 84, -1, 85, -1, -1, 81, 13, 14, 81, 81, 81, 18 });
        tablaT.put("c1", new int[] { -1, 82, -2, -1, -1, 84, 81, 84, -1, 85, -1, -1, 81, 13, 14, 81, 81, 81, 18 });

        acepta.put(99, "completo");// operadores y puntuación
        acepta.put(50, "cad");
        acepta.put(81, "operador");// idMetoto
        acepta.put(82, "id");// id atributos
        acepta.put(84, "num");// entero y cero
        acepta.put(85, "numD");// double
        acepta.put(86, "finP");// fin

        acepta.put(87, "comentario");// double
    }

}
