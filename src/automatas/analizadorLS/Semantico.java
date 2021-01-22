package automatas.analizadorLS;

import java.util.*;

public class Semantico {
    private List<String> pila = new ArrayList<String>();
    public List<String> pos = new ArrayList<String>();
    public List<String> valSwi = new ArrayList<String>();
    public Map<String, List<String>> switchP = new HashMap();
    // private List<Simbolo> pilaSim = new ArrayList<Simbolo>();
    private List<String> pilaOp = new ArrayList<String>();
    private int numWhile = 0;
    private int numSwitch = 0;
    private int swAnidado = 0;
    private int etiqSwi = 1;
    TablaDeSim tabla = new TablaDeSim();
    private String tipoSwitch = "";

    private CodigoIntermedio codigo;

    public Semantico(CodigoIntermedio codigo) {
        LlenaReglas();

        this.codigo = codigo;
    }

    private Map<String, Map<String, String>> reglas = new HashMap<String, Map<String, String>>();

    public String addSwitch(int linea, boolean def) {
        if (etiqSwi > 1) {
            codigo.AgregarCodigo("\n             goto sigc" + numSwitch + "" + swAnidado + "" + (etiqSwi) + ";");
            codigo.AgregarCodigo("\n             etsw" + numSwitch + "" + swAnidado + "" + (etiqSwi - 1) + ":");
        }

        if (!def) {
            String resE = reduceTodo(linea);
            if (!resE.isEmpty()) {
                return resE;
            }
            //System.out.println(pos);

            String act = pos.get(0);
            String comp[] = act.split(" ");

            List<String> lista = switchP.get(numSwitch + "" + swAnidado + "");
            String tipo = lista.get(1);
            if (!comp[1].equalsIgnoreCase(tipo)) {
                return "Error semántico, el switch es " + tipo + ", fíjese compa";
            }
            if (valSwi.contains(comp[0])) {
                return "Error semántico, el switch ya tiene " + comp[0] + ", fíjese compa";
            } else {
                valSwi.add(comp[0]);
                String v = lista.get(0);

               /* if(valSwi.size()!=1){
                    codigo.AgregarCodigo("\n      "+v+"="+comp[0]+";");
               }*/
                codigo.AgregarCodigo("\n      //CASE "+comp[0]);
                codigo.AgregarCodigo("\n      if(!(" + v + "==" + comp[0] + "))");
                codigo.AgregarCodigo("\n         goto etsw" + numSwitch + "" + swAnidado + "" + etiqSwi + ";");
                codigo.AgregarCodigo("\n             sigc" + numSwitch + "" + swAnidado + "" + (etiqSwi) + ":");
                codigo.AgregarCodigo("\n             "+v+"="+v+";");
                etiqSwi++;

            }
        }else{
            codigo.AgregarCodigo("\n             sigc" + numSwitch + "" + swAnidado + "" + (etiqSwi) + ":");
        }

        vaciaPilas();
        return "";
    }

    public void breakSwitch() {
        codigo.AgregarCodigo("\n        goto finsw" + numSwitch + "" + swAnidado + ";");
    }

    public void finSwitch() {
        codigo.AgregarCodigo("\n        finsw" + numSwitch + "" + swAnidado + ":");
        swAnidado--;
        etiqSwi = 1;
        valSwi.clear();
        if (swAnidado == 0)
            numSwitch++;

    }

    public String generaInicioSwitch(int linea) {
        String resE = reduceTodo(linea);
        if (!resE.isEmpty()) {
            vaciaPilas();
            return resE;
        }
        tipoSwitch = getTipo();
        swAnidado++;
        String var = codOperaciones(0).get(0).split("ç")[0];
        // codigo.AgregarCodigo("\n var");
        List<String> lista = new ArrayList<String>();
        lista.add(var);
        lista.add(tipoSwitch);
        String tc = "";
        switch (tipoSwitch.trim()) {
            case "String":
                tc = "char *";
                break;
            case "bool":
                tc = "int ";
                break;
            default:
                tc = "float ";

        }
        lista.add(tc);
        switchP.put(numSwitch + "" + swAnidado + "", lista);
        vaciaPilas();
        return "";
    }

    public boolean generaCodigo() {
        System.out.println("\nReduce operaciones en posfijo: "+pos);
        codOperaciones(1);
        vaciaPilas();
        return false;
    }

    private List<String> codOperaciones(int com) {
        List<String> p = new ArrayList<String>();
        for (int i = com; i < pos.size(); i++) {
            String act = pos.get(i);
            if (act.equals("++") || act.equals("--") || act.equals("!") || act.equals("=") || act.equals("==")
                    || act.equals("<") || act.equals(">") || act.equals("<=") || act.equals(">=") || act.equals("!=")
                    || act.equals("&&") || act.equals("||") || act.equals("+") || act.equals("-") || act.equals("*")
                    || act.equals("/")) { // Es un operador
                if (act.equals("++") || act.equals("--")) {
                    String comp[] = pos.get(0).split(" ");
                    String nomVar = "V" + comp[1].toUpperCase().charAt(0) + (p.size() + 1);
                    p.add(nomVar + "ç" + comp[0]);
                    String val = comp[0];

                    String var1 = nomVar;
                    codigo.AgregarCodigo("\n  " + var1 + " = " + val + ";");
                    codigo.AgregarCodigo("\n  " + var1 + act + ";");
                    codigo.AgregarCodigo("\n  " + val + " = " + var1 + ";");
                } else if (act.equals("!")) {
                    String comp[] = p.get(p.size() - 1).split("ç");
                    String var1 = comp[0];
                    codigo.AgregarCodigo("\n  " + var1 + " = !" + var1 + ";");
                    // codigo.AgregarCodigo("\n "+comp[1]+" = "+var1+";");
                } else if (act.equals("=")) {
                    String comp1[] = p.get(p.size() - 1).split("ç");
                    String var1 = comp1[0];
                    /*
                     * String comp2[]=p.get(p.size()-2).split(" "); String var2 = comp2[0];
                     */
                    // codigo.AgregarCodigo("\n "+var2+" = "+var1+";");
                    codigo.AgregarCodigo("\n  " + pos.get(0).split(" ")[0] + " = " + var1 + ";");
                    tabla.setValor(pos.get(0).split(" ")[0], "");
                    p.remove(p.size() - 1);
                } else {
                    String comp1[] = p.get(p.size() - 1).split("ç");
                    String var1 = comp1[0];
                    String comp2[] = p.get(p.size() - 2).split("ç");
                    String var2 = comp2[0];
                    codigo.AgregarCodigo("\n  " + var2 + " = " + var2 + act + var1 + ";");
                    // codigo.AgregarCodigo("\n "+comp2[1]+" = "+var2+";");
                    p.remove(p.size() - 1);
                }
            } else { // No es un operador
                String comp[] = act.split(" ");
                String nomVar = "V" + comp[comp.length - 1].toUpperCase().charAt(0) + (p.size() + 1);
                String val = "";
                for (int j = 0; j < comp.length - 1; j++) {
                    val = val + " " + comp[j];
                }

                if (val.trim().equals("true"))
                    val = "1";
                else if (val.trim().equals("false"))
                    val = "0";
                codigo.AgregarCodigo("\n  " + nomVar + " = " + val + ";");
                p.add(nomVar + "ç" + val);
            }
        }
        codigo.AgregarCodigo("\n");
        return p;
    }

    public String generaWhile(int n) {

        codigo.AgregarCodigo("\n  ciclo" + numWhile + "" + n + ":");
        String var = codOperaciones(0).get(0).split("ç")[0];
        codigo.AgregarCodigo("\n    if(!" + var + ")");
        codigo.AgregarCodigo("\n       goto fciclo" + numWhile + "" + n + ";");
        vaciaPilas();

        return "";
    }

    public void generaFinWhile(int n) {
        codigo.AgregarCodigo("\n     goto ciclo" + numWhile + "" + n + ";");
        codigo.AgregarCodigo("\n  fciclo" + numWhile + "" + n + ":");
        if (n == 1) {
            numWhile++;
        }
    }

    public String leeLinea() {
        String vec[] = pos.get(0).split(" ");
        String tip = vec[1];
        if (tip.equals("String")) {
            codigo.AgregarCodigo("\"%s\"," + vec[0] + ");");
        } else if (tip.equals("int")) {
            codigo.AgregarCodigo("\"%f\",&" + vec[0] + ");");
        } else if (tip.equals("double")) {
            codigo.AgregarCodigo("\"%f\",&" + vec[0] + ");");
        } else {
            vaciaPilas();
            return "Error semántico, aquí no hacemos eso joven!!! (bool)  ";
        }
        tabla.setValor(vec[0], "");
        vaciaPilas();
        return "";
    }

    public String imprime() {
        String var = codOperaciones(0).get(0).split("ç")[0];
        //System.out.println("VARIABLE: " + var);
        codigo.AgregarCodigo("\n  printf(");
        String tipo = getTipo();
        switch (tipo.trim()) {
            case "String":
                codigo.AgregarCodigo("\"%s\"");
                break;
            case "double":
                codigo.AgregarCodigo("\"%f\"");
                break;
            default:
                codigo.AgregarCodigo("\"%f\"");
        }
        codigo.AgregarCodigo(", " + var + ");");

        // codigo.AgregarCodigo("\n print");
        return "";
    }

    public boolean vaciaPilas() {
        pila.clear();
        // pilaSim.clear();
        pilaOp.clear();
        pos.clear();
        return true;
    }

    public String getTipo() {
        if (pila.size() == 1)
            return pila.get(pila.size() - 1);
        else
            return "";
    }

    public String addTipo(String tipo, String val) {
        if (tipo.equalsIgnoreCase("bool") || tipo.equalsIgnoreCase("String") || tipo.equalsIgnoreCase("int")
                || tipo.equalsIgnoreCase("double")) {
            pila.add(tipo);
            pos.add(val + " " + tipo);
            return ("");
        }
        return "Error semántico 333";
    }

    public String reduceTodo(int linea) {

        while (true) {
            if (!pilaOp.isEmpty()) {
                String sima = pilaOp.get(pilaOp.size() - 1);
                String resR = reduce(sima, linea);

                if (resR.isEmpty()) {
                    pilaOp.remove(pilaOp.size() - 1);
                } else {
                    return resR;
                }

            } else {
                return "";
            }

        }
    }

    public String reduceTodoPar(int linea) {
        while (true) {
            if (!pilaOp.isEmpty()) {
                String sima = pilaOp.get(pilaOp.size() - 1);
                if (sima.equalsIgnoreCase("(")) {
                    pilaOp.remove(pilaOp.size() - 1);
                    return "";
                } else {
                    String resR = reduce(sima, linea);
                    if (resR.isEmpty()) {
                        pilaOp.remove(pilaOp.size() - 1);
                    } else {
                        vaciaPilas();
                        return resR;
                    }
                }

            } else {
                return "";
            }

        }
    }

    public String agregaOp(String oper, int linea) {

        if (!pilaOp.isEmpty()) {
            String sima = pilaOp.get(pilaOp.size() - 1);

            if (oper.equalsIgnoreCase(")")) {
                String resE = reduceTodoPar(linea);
                return resE;
            } else {

                if (!sima.equalsIgnoreCase("(")) {
                    int p1 = Integer.parseInt(reglas.get("prioridad").get(oper)); // 5
                    int p2 = Integer.parseInt(reglas.get("prioridad").get(sima)); // 4
                    if (p2 >= p1) {
                        String resR = reduce(sima, linea);

                        if (resR.isEmpty()) {
                            pilaOp.remove(pilaOp.size() - 1);
                        } else {
                            return resR;
                        }
                    }
                }

            }

        }
        pilaOp.add(oper);
        return "";
    }

    private String reduce(String oper, int linea) {
        //System.out.println("PILA SEMANTICA: " + pila);

        Map<String, String> regOp = reglas.get(oper);
        if (regOp != null) {
            // Penúltimo oper Último

            if ((oper.equalsIgnoreCase("++") || oper.equalsIgnoreCase("--") || oper.equalsIgnoreCase("!"))
                    && !pila.isEmpty()) {
                String ult = pila.get(pila.size() - 1);
                String res = regOp.get(ult);
                if (!res.isEmpty()) {

                    pila.remove(pila.size() - 1);
                    pila.add(res);

                    pos.add(oper);
                    return "";
                } else {
                    return "Error semántico (" + linea + "), tipo de dato no compatible ";
                }

            }
            if (pila.size() >= 2) {
                String ult = pila.get(pila.size() - 1);
                String pen = pila.get(pila.size() - 2);

                String res = regOp.get(pen + " " + ult);

                if (!res.isEmpty()) {

                    pila.remove(pila.size() - 1);
                    pila.remove(pila.size() - 1);
                    pila.add(res);

                    /*
                     * if (oper.equalsIgnoreCase("=")) { intAsigna(); } else { intOper(oper); }
                     */
                    pos.add(oper);
                    return "";
                }
                return "Error semántico (" + linea + "), tipos de datos no compatibles (" + pen + oper + ult + ")";
            }
            return "Error semántico (" + linea + "), operación incorrecta " + "(" + oper + ")";
        }
        return "";

    }

    public String declara(String id, String tipo, int linea) {
        boolean add = tabla.addSimbolo(id, tipo, null, linea);
        if (add) {
            String tc = "";
        switch (tipoSwitch.trim()) {
            case "String":
                tc = "char *";
                break;
            case "bool":
                tc = "int ";
                break;
            default:
                tc = "float ";

        }
            codigo.AgregarCodigo("\n  " + tc + id + ";");

            return "";
        }

        //System.out.println(tabla);
        return "Error semántico (" + linea + "), ya hay una variable declarada como \"" + id + "\"";
    }

    public String addVar(Token token) {

        if (tabla.getValor(token.lexema) == null)
            return "Error semántico (" + token.linea + "), la variable \"" + token.lexema
                    + "\" no ha sido inicializada";
        return addVar(token, true);
    }

    public String addVar(Token token, boolean b) {
        String tipo = tabla.getTipo(token.lexema);
        if (!tipo.isEmpty()) {
            pila.add(tipo);
            pos.add(token.lexema + " " + tipo);
            System.out.println("Se añade \""+token.lexema+"\" a la pila semántica");
            return "";
        }
        return "Error semántico (" + token.linea + "), la variable \"" + token.lexema + "\" no fue declarada";
    }

    private void LlenaReglas() {

        // PRIORIDAD DE OPERACIONES
        // i<=0+3 || i>0
        Map<String, String> prioridad = new HashMap<String, String>();
        prioridad.put("(", "5");
        prioridad.put("*", "4");
        prioridad.put("/", "4");
        prioridad.put("+", "3");
        prioridad.put("++", "3");
        prioridad.put("-", "3");
        prioridad.put("--", "3");
        prioridad.put("<", "2");
        prioridad.put(">", "2");
        prioridad.put("<=", "2");
        prioridad.put("<=", "2");
        prioridad.put("!=", "2");
        prioridad.put("==", "2");
        prioridad.put("||", "1");
        prioridad.put("&&", "1");
        prioridad.put("!", "1");
        prioridad.put("=", "0");
        reglas.put("prioridad", prioridad);

        // LÓGICOS
        Map<String, String> or = new HashMap<String, String>();
        or.put("int int", "");
        or.put("int double", "");
        or.put("int String", "");
        or.put("int bool", "");
        or.put("double int", "");
        or.put("double double", "");
        or.put("double String", "");
        or.put("double bool", "");
        or.put("String int", "");
        or.put("String double", "");
        or.put("String String", "");
        or.put("String bool", "");
        or.put("bool int", "");
        or.put("bool double", "");
        or.put("bool String", "");
        or.put("bool bool", "bool");
        reglas.put("||", or);

        Map<String, String> and = new HashMap<String, String>();
        and.put("int int", "");
        and.put("int double", "");
        and.put("int String", "");
        and.put("int bool", "");
        and.put("double int", "");
        and.put("double double", "");
        and.put("double String", "");
        and.put("double bool", "");
        and.put("String int", "");
        and.put("String double", "");
        and.put("String String", "");
        and.put("String bool", "");
        and.put("bool int", "");
        and.put("bool double", "");
        and.put("bool String", "");
        and.put("bool bool", "bool");
        reglas.put("&&", and);

        Map<String, String> NOT = new HashMap<String, String>();
        NOT.put("int", "");
        NOT.put("double", "");
        NOT.put("String", "");
        NOT.put("bool", "bool");
        reglas.put("!", NOT);

        // RELACIONALES
        Map<String, String> asigna = new HashMap<String, String>();
        asigna.put("int int", "int");
        asigna.put("int double", "");
        asigna.put("int String", "");
        asigna.put("int bool", "int"); // Jsdjeuifhiduf
        asigna.put("double int", "double");
        asigna.put("double double", "double");
        asigna.put("double String", "");
        asigna.put("double bool", "");
        asigna.put("String int", "");
        asigna.put("String double", "");
        asigna.put("String String", "String");
        asigna.put("String bool", "");
        asigna.put("bool int", "");
        asigna.put("bool double", "");
        asigna.put("bool String", "");
        asigna.put("bool bool", "bool");
        reglas.put("=", asigna);

        Map<String, String> menorQue = new HashMap<String, String>();
        menorQue.put("int int", "bool");
        menorQue.put("int double", "bool");
        menorQue.put("int String", "");
        menorQue.put("int bool", "");
        menorQue.put("double int", "bool");
        menorQue.put("double double", "bool");
        menorQue.put("double String", "");
        menorQue.put("double bool", "");
        menorQue.put("String int", "");
        menorQue.put("String double", "");
        menorQue.put("String String", "");
        menorQue.put("String bool", "");
        menorQue.put("bool int", "");
        menorQue.put("bool double", "");
        menorQue.put("bool String", "");
        menorQue.put("bool bool", "");
        reglas.put("<", menorQue);
        reglas.put(">", menorQue);
        reglas.put("<=", menorQue);
        reglas.put(">=", menorQue);

        Map<String, String> diferente = new HashMap<String, String>();
        diferente.put("int int", "bool");
        diferente.put("int double", "bool");
        diferente.put("int String", "");
        diferente.put("int bool", "");
        diferente.put("double int", "bool");
        diferente.put("double double", "bool");
        diferente.put("double String", "");
        diferente.put("double bool", "");
        diferente.put("String int", "");
        diferente.put("String double", "");
        diferente.put("String String", "");
        diferente.put("String bool", "");
        diferente.put("bool int", "");
        diferente.put("bool double", "");
        diferente.put("bool String", "");
        diferente.put("bool bool", "");
        reglas.put("!=", diferente);
        reglas.put("==", diferente);

        // Aritméticos
        Map<String, String> suma = new HashMap<String, String>();
        suma.put("int int", "int");
        suma.put("int double", "double");
        suma.put("int String", "");
        suma.put("int bool", "");
        suma.put("double int", "double");
        suma.put("double double", "double");
        suma.put("double String", "");
        suma.put("double bool", "");
        suma.put("String int", "");
        suma.put("String double", "");
        suma.put("String String", "");
        suma.put("String bool", "");
        suma.put("bool int", "");
        suma.put("bool double", "");
        suma.put("bool String", "");
        suma.put("bool bool", "");
        reglas.put("+", suma);

        Map<String, String> resta = new HashMap<String, String>();
        resta.put("int int", "int");
        resta.put("int double", "double");
        resta.put("int String", "");
        resta.put("int bool", "");
        resta.put("double int", "double");
        resta.put("double double", "double");
        resta.put("double String", "");
        resta.put("double bool", "");
        resta.put("String int", "");
        resta.put("String double", "");
        resta.put("String String", "");
        resta.put("String bool", "");
        resta.put("bool int", "");
        resta.put("bool double", "");
        resta.put("bool String", "");
        resta.put("bool bool", "");
        reglas.put("-", resta);

        Map<String, String> mult = new HashMap<String, String>();
        mult.put("int int", "int");
        mult.put("int double", "double");
        mult.put("int String", "");
        mult.put("int bool", "");
        mult.put("double int", "double");
        mult.put("double double", "double");
        mult.put("double String", "");
        mult.put("double bool", "");
        mult.put("String int", "");
        mult.put("String double", "");
        mult.put("String String", "");
        mult.put("String bool", "");
        mult.put("bool int", "");
        mult.put("bool double", "");
        mult.put("bool String", "");
        mult.put("bool bool", "");
        reglas.put("*", mult);
        reglas.put("/", mult);

        Map<String, String> masmas = new HashMap<String, String>();
        masmas.put("int", "int");
        masmas.put("double", "double");
        masmas.put("String", "");
        masmas.put("bool", "");
        reglas.put("++", masmas);
        reglas.put("--", masmas);

    }
}