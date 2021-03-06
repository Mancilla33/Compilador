
package automatas.analizadorLS;

import java.util.HashMap;
import java.util.Map;

public class Tablas {
    private Map<String, Map<String, String>> tabla = new HashMap<String, Map<String, String>>();
    private Map<String, String> errores = new HashMap();

    public String getError(String s) {
        return errores.get(s);
    }

    public Map<String, Map<String, String>> TablaDart() {
        errores.clear();
        tabla.clear();

        errores.put("~001", "se esperaba una asignación");
        errores.put("~002", "se esperaba una operando");
        errores.put("~003", "se esperaba un operador");
        errores.put("~004", "se esperaba una declaración");
        errores.put("~005", "se esperaba un ;");
        errores.put("~006", "se esperaba una condición");
        errores.put("~007", "los id se deben de separar con coma");
        errores.put("~008", "no puede seguir código después de un brake");
        errores.put("~009", "sentencia no válida");

        Map<String, String> finP = new HashMap();
        finP.put("programa", "ç");
        finP.put("sentencias", "ç");
        finP.put("sentencia", "");
        finP.put("tipoDato", "");
        finP.put("declara", "~004");
        finP.put("masID", "");
        finP.put("asD", "~005");
        finP.put("asigna", "");
        finP.put("A", "~001"); 
        finP.put("cueSwi", "~008");
        finP.put("BRE", "");
        finP.put("cueWhi", "");
        finP.put("L", "~002");
        finP.put("L'", "~003");
        finP.put("R", "~002");
        finP.put("R'", "~003");
        finP.put("E", "~002");
        finP.put("E'", "~003");
        finP.put("T", "~002");
        finP.put("T'", "~003");
        finP.put("F", "~002");
        finP.put("D", "~002");
        tabla.put("finP", finP);

        Map<String, String> puntoYComa = new HashMap();
        puntoYComa.put("programa", "");
        puntoYComa.put("sentencias", "");
        puntoYComa.put("sentencia", "");
        puntoYComa.put("tipoDato", "");
        puntoYComa.put("declara", "~004");
        puntoYComa.put("masID", "ç");
        puntoYComa.put("asD", "ç");
        puntoYComa.put("asigna", "");
        puntoYComa.put("A", "~001");
        puntoYComa.put("cueSwi", "~008");
        puntoYComa.put("BRE", "");
        puntoYComa.put("cueWhi", "");
        puntoYComa.put("L", "~002");
        puntoYComa.put("L'", "ç");
        puntoYComa.put("R", "~002");
        puntoYComa.put("R'", "ç");
        puntoYComa.put("E", "~002");
        puntoYComa.put("E'", "ç");
        puntoYComa.put("T", "~002");
        puntoYComa.put("T'", "ç");
        puntoYComa.put("F", "~002");
        puntoYComa.put("D", "~002");
        tabla.put(";", puntoYComa);

        Map<String, String> pSwitch = new HashMap();
        pSwitch.put("programa", "sentencias finP");
        pSwitch.put("sentencias", "sentencia sentencias");
        pSwitch.put("sentencia", "switch L { cueSwi }");
        pSwitch.put("tipoDato", "");
        pSwitch.put("declara", "~004");
        pSwitch.put("masID", "");
        pSwitch.put("asD", "");
        pSwitch.put("asigna", "");
        pSwitch.put("A", "~001");
        pSwitch.put("cueSwi", "~008");
        pSwitch.put("BRE", "");
        pSwitch.put("cueWhi", "sentencia");
        pSwitch.put("L", "~002");
        pSwitch.put("L'", "ç");
        pSwitch.put("R", "~002");
        pSwitch.put("R'", "ç");
        pSwitch.put("E", "~002");
        pSwitch.put("E'", "ç");
        pSwitch.put("T", "~002");
        pSwitch.put("T'", "ç");
        pSwitch.put("F", "~002");
        pSwitch.put("D", "~002");
        tabla.put("switch", pSwitch);

        Map<String, String> pWhile = new HashMap();
        pWhile.put("programa", "sentencias finP");
        pWhile.put("sentencias", "sentencia sentencias");
        pWhile.put("sentencia", "while L cueWhi");
        pWhile.put("tipoDato", "");
        pWhile.put("declara", "~004");
        pWhile.put("masID", "");
        pWhile.put("asD", "");
        pWhile.put("asigna", "");
        pWhile.put("A", "~001");
        pWhile.put("cueSwi", "~008");
        pWhile.put("BRE", "");
        pWhile.put("cueWhi", "sentencia");
        pWhile.put("L", "~002");
        pWhile.put("L'", "ç");
        pWhile.put("R", "~002");
        pWhile.put("R'", "ç");
        pWhile.put("E", "~002");
        pWhile.put("E'", "ç");
        pWhile.put("T", "~002");
        pWhile.put("T'", "ç");
        pWhile.put("F", "~002");
        pWhile.put("D", "~002");
        tabla.put("while", pWhile);

        Map<String, String> llaveAbre = new HashMap();
        llaveAbre.put("programa", "");
        llaveAbre.put("sentencias", "~100");
        llaveAbre.put("sentencia", "");
        llaveAbre.put("tipoDato", "");
        llaveAbre.put("declara", "~004");
        llaveAbre.put("masID", "");
        llaveAbre.put("asD", "");
        llaveAbre.put("asigna", "");
        llaveAbre.put("A", "~001");
        llaveAbre.put("cueSwi", "~008");
        llaveAbre.put("BRE", "");
        llaveAbre.put("cueWhi", "{ sentencias }");
        llaveAbre.put("L", "~002");
        llaveAbre.put("L'", "ç");
        llaveAbre.put("R", "~002");
        llaveAbre.put("R'", "ç");
        llaveAbre.put("E", "~002");
        llaveAbre.put("E'", "ç");
        llaveAbre.put("T", "~002");
        llaveAbre.put("T'", "ç");
        llaveAbre.put("F", "~002");
        llaveAbre.put("D", "~002");
        tabla.put("{", llaveAbre);

        Map<String, String> llaveCierra = new HashMap();
        llaveCierra.put("programa", "");
        llaveCierra.put("sentencias", "ç");
        llaveCierra.put("sentencia", "");
        llaveCierra.put("tipoDato", "");
        llaveCierra.put("declara", "~004");
        llaveCierra.put("masID", "");
        llaveCierra.put("asD", "");
        llaveCierra.put("asigna", "");
        llaveCierra.put("A", "~001");
        llaveCierra.put("cueSwi", "ç");
        llaveCierra.put("BRE", "ç");
        llaveCierra.put("cueWhi", "");
        llaveCierra.put("L", "~002");
        llaveCierra.put("L'", "~003");
        llaveCierra.put("R", "~002");
        llaveCierra.put("R'", "~003");
        llaveCierra.put("E", "~002");
        llaveCierra.put("E'", "~003");
        llaveCierra.put("T", "~002");
        llaveCierra.put("T'", "~003");
        llaveCierra.put("F", "~002");
        llaveCierra.put("D", "~002");
        tabla.put("}", llaveCierra);

        Map<String, String> pPrint = new HashMap();
        pPrint.put("programa", "sentencias finP");
        pPrint.put("sentencias", "sentencia sentencias");
        pPrint.put("sentencia", "print L ;");
        pPrint.put("tipoDato", "");
        pPrint.put("declara", "~004");
        pPrint.put("masID", "");
        pPrint.put("asD", "");
        pPrint.put("asigna", "");
        pPrint.put("A", "~001");
        pPrint.put("cueSwi", "~008");
        pPrint.put("BRE", "");
        pPrint.put("cueWhi", "sentencia");
        pPrint.put("L", "~002");
        pPrint.put("L'", "ç");
        pPrint.put("R", "~002");
        pPrint.put("R'", "ç");
        pPrint.put("E", "~002");
        pPrint.put("E'", "ç");
        pPrint.put("T", "~002");
        pPrint.put("T'", "ç");
        pPrint.put("F", "~002");
        pPrint.put("D", "~002");
        tabla.put("print", pPrint);

        Map<String, String> readLine = new HashMap();
        readLine.put("programa", "sentencias finP");
        readLine.put("sentencias", "sentencia sentencias");
        readLine.put("sentencia", "readLine ( id ) ;");
        readLine.put("tipoDato", "");
        readLine.put("declara", "~004");
        readLine.put("masID", "");
        readLine.put("asD", "");
        readLine.put("asigna", "");
        readLine.put("A", "~001");
        readLine.put("cueSwi", "~008");
        readLine.put("BRE", "");
        readLine.put("cueWhi", "sentencia");
        readLine.put("L", "~002");
        readLine.put("L'", "ç");
        readLine.put("R", "~002");
        readLine.put("R'", "ç");
        readLine.put("E", "~002");
        readLine.put("E'", "ç");
        readLine.put("T", "~002");
        readLine.put("T'", "ç");
        readLine.put("F", "~002");
        readLine.put("D", "~002");
        tabla.put("readLine", readLine);

        Map<String, String> pareAbre = new HashMap();
        pareAbre.put("programa", "");
        pareAbre.put("sentencias", "");
        pareAbre.put("sentencia", "");
        pareAbre.put("tipoDato", "");
        pareAbre.put("declara", "~004");
        pareAbre.put("masID", "");
        pareAbre.put("asD", "");
        pareAbre.put("asigna", "");
        pareAbre.put("A", "~001");
        pareAbre.put("cueSwi", "~008");
        pareAbre.put("BRE", "");
        pareAbre.put("cueWhi", "");
        pareAbre.put("L", "R L'");
        pareAbre.put("L'", "~003");
        pareAbre.put("R", "E R'");
        pareAbre.put("R'", "~003");
        pareAbre.put("E", "T E'");
        pareAbre.put("E'", "~003");
        pareAbre.put("T", "F T'");
        pareAbre.put("T'", "~003");
        pareAbre.put("F", "( L )");
        pareAbre.put("D", "");
        tabla.put("(", pareAbre);

        Map<String, String> pareCierra = new HashMap();
        pareCierra.put("programa", "");
        pareCierra.put("sentencias", "");
        pareCierra.put("sentencia", "");
        pareCierra.put("tipoDato", "");
        pareCierra.put("declara", "~004");
        pareCierra.put("masID", "");
        pareCierra.put("asD", "");
        pareCierra.put("asigna", "");
        pareCierra.put("A", "~001");
        pareCierra.put("cueSwi", "~008");
        pareCierra.put("BRE", "");
        pareCierra.put("cueWhi", "");
        pareCierra.put("L", "~002");
        pareCierra.put("L'", "ç");
        pareCierra.put("R", "~002");
        pareCierra.put("R'", "ç");
        pareCierra.put("E", "~002");
        pareCierra.put("E'", "ç");
        pareCierra.put("T", "~002");
        pareCierra.put("T'", "ç");
        pareCierra.put("F", "~002");
        pareCierra.put("D", "~002");
        tabla.put(")", pareCierra);

        Map<String, String> bool = new HashMap();
        bool.put("programa", "sentencias finP");
        bool.put("sentencias", "sentencia sentencias");
        bool.put("sentencia", "tipoDato declara ;");
        bool.put("tipoDato", "bool");
        bool.put("declara", "~004");
        bool.put("masID", "");
        bool.put("asD", "");
        bool.put("asigna", "");
        bool.put("A", "~001");
        bool.put("cueSwi", "~008");
        bool.put("BRE", "");
        bool.put("cueWhi", "sentencia");
        bool.put("L", "~002");
        bool.put("L'", "ç");
        bool.put("R", "~002");
        bool.put("R'", "ç");
        bool.put("E", "~002");
        bool.put("E'", "ç");
        bool.put("T", "~002");
        bool.put("T'", "ç");
        bool.put("F", "~002");
        bool.put("D", "~002");
        tabla.put("bool", bool);

        Map<String, String> pString = new HashMap();
        pString.put("programa", "sentencias finP");
        pString.put("sentencias", "sentencia sentencias");
        pString.put("sentencia", "tipoDato declara ;");
        pString.put("tipoDato", "String");
        pString.put("declara", "~004");
        pString.put("masID", "");
        pString.put("asD", "");
        pString.put("asigna", "");
        pString.put("A", "~001");
        pString.put("cueSwi", "~008");
        pString.put("BRE", "");
        pString.put("cueWhi", "sentencia");
        pString.put("L", "~002");
        pString.put("L'", "ç");
        pString.put("R", "~002");
        pString.put("R'", "ç");
        pString.put("E", "~002");
        pString.put("E'", "ç");
        pString.put("T", "~002");
        pString.put("T'", "ç");
        pString.put("F", "~002");
        pString.put("D", "~002");
        tabla.put("String", pString);

        Map<String, String> pInt = new HashMap();
        pInt.put("programa", "sentencias finP");
        pInt.put("sentencias", "sentencia sentencias");
        pInt.put("sentencia", "tipoDato declara ;");
        pInt.put("tipoDato", "int");
        pInt.put("declara", "~004");
        pInt.put("masID", "");
        pInt.put("asD", "");
        pInt.put("asigna", "");
        pInt.put("A", "~001");
        pInt.put("cueSwi", "~008");
        pInt.put("BRE", "");
        pInt.put("cueWhi", "sentencia");
        pInt.put("L", "~002");
        pInt.put("L'", "ç");
        pInt.put("R", "~002");
        pInt.put("R'", "ç");
        pInt.put("E", "~002");
        pInt.put("E'", "ç");
        pInt.put("T", "~002");
        pInt.put("T'", "ç");
        pInt.put("F", "~002");
        pInt.put("D", "~002");
        tabla.put("int", pInt);

        Map<String, String> pDouble = new HashMap();
        pDouble.put("programa", "sentencias finP");
        pDouble.put("sentencias", "sentencia sentencias");
        pDouble.put("sentencia", "tipoDato declara ;");
        pDouble.put("tipoDato", "double");
        pDouble.put("declara", "~004");
        pDouble.put("masID", "");
        pDouble.put("asD", "");
        pDouble.put("asigna", "");
        pDouble.put("A", "~001");
        pDouble.put("cueSwi", "~008");
        pDouble.put("BRE", "");
        pDouble.put("cueWhi", "sentencia");
        pDouble.put("L", "~002");
        pDouble.put("L'", "ç");
        pDouble.put("R", "~002");
        pDouble.put("R'", "ç");
        pDouble.put("E", "~002");
        pDouble.put("E'", "ç");
        pDouble.put("T", "~002");
        pDouble.put("T'", "ç");
        pDouble.put("F", "~002");
        pDouble.put("D", "~002");
        tabla.put("double", pDouble);

        Map<String, String> coma = new HashMap();
        coma.put("programa", "");
        coma.put("sentencias", "");
        coma.put("sentencia", "");
        coma.put("tipoDato", "");
        coma.put("declara", "~004");
        coma.put("masID", ", id asD masID");
        coma.put("asD", "ç");
        coma.put("asigna", "");
        coma.put("A", "~001");
        coma.put("cueSwi", "~008");
        coma.put("BRE", "");
        coma.put("cueWhi", "");
        coma.put("L", "~002");
        coma.put("L'", "ç");
        coma.put("R", "~002");
        coma.put("R'", "ç");
        coma.put("E", "~002");
        coma.put("E'", "ç");
        coma.put("T", "~002");
        coma.put("T'", "ç");
        coma.put("F", "~002");
        coma.put("D", "~002");
        tabla.put(",", coma);

        Map<String, String> masmas = new HashMap();
        masmas.put("programa", "");
        masmas.put("sentencias", "");
        masmas.put("sentencia", "");
        masmas.put("tipoDato", "");
        masmas.put("declara", "~004");
        masmas.put("masID", "");
        masmas.put("asD", "");
        masmas.put("asigna", "");
        masmas.put("A", "++");
        masmas.put("cueSwi", "~008");
        masmas.put("BRE", "");
        masmas.put("cueWhi", "");
        masmas.put("L", "~002");
        masmas.put("L'", "~003");
        masmas.put("R", "~002");
        masmas.put("R'", "~003");
        masmas.put("E", "~002");
        masmas.put("E'", "~003");
        masmas.put("T", "~002");
        masmas.put("T'", "~003");
        masmas.put("F", "~002");
        masmas.put("D", "~002");
        tabla.put("++", masmas);

        Map<String, String> menmen = new HashMap();
        menmen.put("programa", "");
        menmen.put("sentencias", "");
        menmen.put("sentencia", "");
        menmen.put("tipoDato", "");
        menmen.put("declara", "~004");
        menmen.put("masID", "");
        menmen.put("asD", "");
        menmen.put("asigna", "");
        menmen.put("A", "--");
        menmen.put("cueSwi", "~008");
        menmen.put("BRE", "");
        menmen.put("cueWhi", "");
        menmen.put("L", "~002");
        menmen.put("L'", "~003");
        menmen.put("R", "~002");
        menmen.put("R'", "~003");
        menmen.put("E", "~002");
        menmen.put("E'", "~003");
        menmen.put("T", "~002");
        menmen.put("T'", "~003");
        menmen.put("F", "~002");
        menmen.put("D", "~002");
        tabla.put("--", menmen);

        Map<String, String> pCase = new HashMap();
        pCase.put("programa", "");
        pCase.put("sentencias", "ç");
        pCase.put("sentencia", "");
        pCase.put("tipoDato", "");
        pCase.put("declara", "~004");
        pCase.put("masID", "");
        pCase.put("asD", "");
        pCase.put("asigna", "");
        pCase.put("A", "~001");
        pCase.put("cueSwi", "case D : sentencias BRE cueSwi");
        pCase.put("BRE", "ç");
        pCase.put("cueWhi", "");
        pCase.put("L", "~002");
        pCase.put("L'", "~003");
        pCase.put("R", "~002");
        pCase.put("R'", "~003");
        pCase.put("E", "~002");
        pCase.put("E'", "~003");
        pCase.put("T", "~002");
        pCase.put("T'", "~003");
        pCase.put("F", "~002");
        pCase.put("D", "~002");
        tabla.put("case", pCase);

        Map<String, String> dosPuntos = new HashMap();
        dosPuntos.put("programa", "");
        dosPuntos.put("sentencias", "");
        dosPuntos.put("sentencia", "");
        dosPuntos.put("tipoDato", "");
        dosPuntos.put("declara", "~004");
        dosPuntos.put("masID", "");
        dosPuntos.put("asD", "");
        dosPuntos.put("asigna", "");
        dosPuntos.put("A", "~001");
        dosPuntos.put("cueSwi", "~008");
        dosPuntos.put("BRE", "");
        dosPuntos.put("cueWhi", "");
        dosPuntos.put("L", "~002");
        dosPuntos.put("L'", "");
        dosPuntos.put("R", "~002");
        dosPuntos.put("R'", "");
        dosPuntos.put("E", "~002");
        dosPuntos.put("E'", "");
        dosPuntos.put("T", "~002");
        dosPuntos.put("T'", "");
        dosPuntos.put("F", "~002");
        dosPuntos.put("D", "");
        tabla.put(":", dosPuntos);

        Map<String, String> pDefault = new HashMap();
        pDefault.put("programa", "");
        pDefault.put("sentencias", "ç");
        pDefault.put("sentencia", "");
        pDefault.put("tipoDato", "");
        pDefault.put("declara", "~004");
        pDefault.put("masID", "");
        pDefault.put("asD", "");
        pDefault.put("asigna", "");
        pDefault.put("A", "~001");
        pDefault.put("cueSwi", "default : sentencias BRE");
        pDefault.put("BRE", "ç");
        pDefault.put("cueWhi", "");
        pDefault.put("L", "~002");
        pDefault.put("L'", "~003");
        pDefault.put("R", "~002");
        pDefault.put("R'", "~003");
        pDefault.put("E", "~002");
        pDefault.put("E'", "~003");
        pDefault.put("T", "~002");
        pDefault.put("T'", "~003");
        pDefault.put("F", "~002");
        pDefault.put("D", "~002");
        tabla.put("default", pDefault);

        Map<String, String> pBreak = new HashMap();
        pBreak.put("programa", "");
        pBreak.put("sentencias", "ç");
        pBreak.put("sentencia", "");
        pBreak.put("tipoDato", "");
        pBreak.put("declara", "~004");
        pBreak.put("masID", "");
        pBreak.put("asD", "");
        pBreak.put("asigna", "");
        pBreak.put("A", "~001");
        pBreak.put("cueSwi", "~008");
        pBreak.put("BRE", "break ;");
        pBreak.put("cueWhi", "");
        pBreak.put("L", "~002");
        pBreak.put("L'", "~003");
        pBreak.put("R", "~002");
        pBreak.put("R'", "~003");
        pBreak.put("E", "~002");
        pBreak.put("E'", "~003");
        pBreak.put("T", "~002");
        pBreak.put("T'", "~003");
        pBreak.put("F", "~002");
        pBreak.put("D", "~002");
        tabla.put("break", pBreak);

        Map<String, String> admira = new HashMap();
        admira.put("programa", "");
        admira.put("sentencias", "~009");
        admira.put("sentencia", "");
        admira.put("tipoDato", "");
        admira.put("declara", "~004");
        admira.put("masID", "");
        admira.put("asD", "");
        admira.put("asigna", "");
        admira.put("A", "~001");
        admira.put("cueSwi", "~008");
        admira.put("BRE", "");
        admira.put("cueWhi", "");
        admira.put("L", "! L");
        admira.put("L'", "~003");
        admira.put("R", "~002");
        admira.put("R'", "~003");
        admira.put("E", "~002");
        admira.put("E'", "~003");
        admira.put("T", "~002");
        admira.put("T'", "~003");
        admira.put("F", "~002");
        admira.put("D", "~002");
        tabla.put("!", admira);

        Map<String, String> pNull = new HashMap();
        pNull.put("programa", "");
        pNull.put("sentencias", "");
        pNull.put("sentencia", "");
        pNull.put("tipoDato", "");
        pNull.put("declara", "~004");
        pNull.put("masID", "");
        pNull.put("asD", "");
        pNull.put("asigna", "");
        pNull.put("A", "~001");
        pNull.put("cueSwi", "~008");
        pNull.put("BRE", "");
        pNull.put("cueWhi", "");
        pNull.put("L", "null");
        pNull.put("L'", "~003");
        pNull.put("R", "~002");
        pNull.put("R'", "~003");
        pNull.put("E", "~002");
        pNull.put("E'", "~003");
        pNull.put("T", "~002");
        pNull.put("T'", "~003");
        pNull.put("F", "~002");
        pNull.put("D", "~002");
        tabla.put("null", pNull);

        Map<String, String> or = new HashMap();
        or.put("programa", "");
        or.put("sentencias", "");
        or.put("sentencia", "");
        or.put("tipoDato", "");
        or.put("declara", "~004");
        or.put("masID", "");
        or.put("asD", "");
        or.put("asigna", "");
        or.put("A", "~001");
        or.put("cueSwi", "~008");
        or.put("BRE", "");
        or.put("cueWhi", "");
        or.put("L", "~002");
        or.put("L'", "|| R");
        or.put("R", "~002");
        or.put("R'", "ç");
        or.put("E", "~002");
        or.put("E'", "ç");
        or.put("T", "~002");
        or.put("T'", "ç");
        or.put("F", "~002");
        or.put("D", "~002");
        tabla.put("||", or);

        Map<String, String> and = new HashMap();
        and.put("programa", "");
        and.put("sentencias", "");
        and.put("sentencia", "");
        and.put("tipoDato", "");
        and.put("declara", "~004");
        and.put("masID", "");
        and.put("asD", "");
        and.put("asigna", "");
        and.put("A", "~001");
        and.put("cueSwi", "~008");
        and.put("BRE", "");
        and.put("cueWhi", "");
        and.put("L", "~002");
        and.put("L'", "&& R");
        and.put("R", "~002");
        and.put("R'", "ç");
        and.put("E", "~002");
        and.put("E'", "ç");
        and.put("T", "~002");
        and.put("T'", "ç");
        and.put("F", "~002");
        and.put("D", "~002");
        tabla.put("&&", and);

        Map<String, String> menorQue = new HashMap();
        menorQue.put("programa", "");
        menorQue.put("sentencias", "");
        menorQue.put("sentencia", "");
        menorQue.put("tipoDato", "");
        menorQue.put("declara", "~004");
        menorQue.put("masID", "");
        menorQue.put("asD", "");
        menorQue.put("asigna", "");
        menorQue.put("A", "~001");
        menorQue.put("cueSwi", "~008");
        menorQue.put("BRE", "");
        menorQue.put("cueWhi", "");
        menorQue.put("L", "~002");
        menorQue.put("L'", "~003");
        menorQue.put("R", "~002");
        menorQue.put("R'", "< E");
        menorQue.put("E", "~002");
        menorQue.put("E'", "ç");
        menorQue.put("T", "~002");
        menorQue.put("T'", "ç");
        menorQue.put("F", "~002");
        menorQue.put("D", "~002");
        tabla.put("<", menorQue);

        Map<String, String> mayorQue = new HashMap();
        mayorQue.put("programa", "");
        mayorQue.put("sentencias", "");
        mayorQue.put("sentencia", "");
        mayorQue.put("tipoDato", "");
        mayorQue.put("declara", "~004");
        mayorQue.put("masID", "");
        mayorQue.put("asD", "");
        mayorQue.put("asigna", "");
        mayorQue.put("A", "~001");
        mayorQue.put("cueSwi", "~008");
        mayorQue.put("BRE", "");
        mayorQue.put("cueWhi", "");
        mayorQue.put("L", "~002");
        mayorQue.put("L'", "~003");
        mayorQue.put("R", "~002");
        mayorQue.put("R'", "> E");
        mayorQue.put("E", "~002");
        mayorQue.put("E'", "ç");
        mayorQue.put("T", "~002");
        mayorQue.put("T'", "ç");
        mayorQue.put("F", "~002");
        mayorQue.put("D", "~002");
        tabla.put(">", mayorQue);

        Map<String, String> menIgQue = new HashMap();
        menIgQue.put("programa", "");
        menIgQue.put("sentencias", "");
        menIgQue.put("sentencia", "");
        menIgQue.put("tipoDato", "");
        menIgQue.put("declara", "~004");
        menIgQue.put("masID", "");
        menIgQue.put("asD", "");
        menIgQue.put("asigna", "");
        menIgQue.put("A", "~001");
        menIgQue.put("cueSwi", "~008");
        menIgQue.put("BRE", "");
        menIgQue.put("cueWhi", "");
        menIgQue.put("L", "~002");
        menIgQue.put("L'", "~003");
        menIgQue.put("R", "~002");
        menIgQue.put("R'", "<= E");
        menIgQue.put("E", "~002");
        menIgQue.put("E'", "ç");
        menIgQue.put("T", "~002");
        menIgQue.put("T'", "ç");
        menIgQue.put("F", "~002");
        menIgQue.put("D", "~002");
        tabla.put("<=", menIgQue);

        Map<String, String> mayIgQue = new HashMap();
        mayIgQue.put("programa", "");
        mayIgQue.put("sentencias", "");
        mayIgQue.put("sentencia", "");
        mayIgQue.put("tipoDato", "");
        mayIgQue.put("declara", "~004");
        mayIgQue.put("masID", "");
        mayIgQue.put("asD", "");
        mayIgQue.put("asigna", "");
        mayIgQue.put("A", "~001");
        mayIgQue.put("cueSwi", "~008");
        mayIgQue.put("BRE", "");
        mayIgQue.put("cueWhi", "");
        mayIgQue.put("L", "~002");
        mayIgQue.put("L'", "~003");
        mayIgQue.put("R", "~002");
        mayIgQue.put("R'", ">= E");
        mayIgQue.put("E", "~002");
        mayIgQue.put("E'", "ç");
        mayIgQue.put("T", "~002");
        mayIgQue.put("T'", "ç");
        mayIgQue.put("F", "~002");
        mayIgQue.put("D", "~002");
        tabla.put(">=", mayIgQue);

        Map<String, String> diferente = new HashMap();
        diferente.put("programa", "");
        diferente.put("sentencias", "");
        diferente.put("sentencia", "");
        diferente.put("tipoDato", "");
        diferente.put("declara", "~004");
        diferente.put("masID", "");
        diferente.put("asD", "");
        diferente.put("asigna", "");
        diferente.put("A", "~001");
        diferente.put("cueSwi", "~008");
        diferente.put("BRE", "");
        diferente.put("cueWhi", "");
        diferente.put("L", "~002");
        diferente.put("L'", "~003");
        diferente.put("R", "~002");
        diferente.put("R'", "!= E");
        diferente.put("E", "~002");
        diferente.put("E'", "ç");
        diferente.put("T", "~002");
        diferente.put("T'", "ç");
        diferente.put("F", "~002");
        diferente.put("D", "~002");
        tabla.put("!=", diferente);

        Map<String, String> igualI = new HashMap();
        igualI.put("programa", "");
        igualI.put("sentencias", "");
        igualI.put("sentencia", "");
        igualI.put("tipoDato", "");
        igualI.put("declara", "~004");
        igualI.put("masID", "");
        igualI.put("asD", "");
        igualI.put("asigna", "");
        igualI.put("A", "~001");
        igualI.put("cueSwi", "~008");
        igualI.put("BRE", "");
        igualI.put("cueWhi", "");
        igualI.put("L", "~002");
        igualI.put("L'", "~003");
        igualI.put("R", "~002");
        igualI.put("R'", "== E");
        igualI.put("E", "~002");
        igualI.put("E'", "ç");
        igualI.put("T", "~002");
        igualI.put("T'", "ç");
        igualI.put("F", "~002");
        igualI.put("D", "~002");
        tabla.put("==", igualI);

        Map<String, String> mas = new HashMap();
        mas.put("programa", "");
        mas.put("sentencias", "");
        mas.put("sentencia", "");
        mas.put("tipoDato", "");
        mas.put("declara", "~004");
        mas.put("masID", "");
        mas.put("asD", "");
        mas.put("asigna", "");
        mas.put("A", "~001");
        mas.put("cueSwi", "~008");
        mas.put("BRE", "");
        mas.put("cueWhi", "");
        mas.put("L", "~002");
        mas.put("L'", "~003");
        mas.put("R", "~002");
        mas.put("R'", "~003");
        mas.put("E", "~002");
        mas.put("E'", "+ T E'");
        mas.put("T", "~002");
        mas.put("T'", "ç");
        mas.put("F", "~002");
        mas.put("D", "~002");
        tabla.put("+", mas);

        Map<String, String> menos = new HashMap();
        menos.put("programa", "");
        menos.put("sentencias", "");
        menos.put("sentencia", "");
        menos.put("tipoDato", "");
        menos.put("declara", "~004");
        menos.put("masID", "");
        menos.put("asD", "");
        menos.put("asigna", "");
        menos.put("A", "~001");
        menos.put("cueSwi", "~008");
        menos.put("BRE", "");
        menos.put("cueWhi", "");
        menos.put("L", "");
        menos.put("L'", "~003");
        menos.put("R", "");
        menos.put("R'", "~003");
        menos.put("E", "");
        menos.put("E'", "- T E'");
        menos.put("T", "");
        menos.put("T'", "ç");
        menos.put("F", "");
        menos.put("D", "");
        tabla.put("-", menos);

        Map<String, String> por = new HashMap();
        por.put("programa", "");
        por.put("sentencias", "");
        por.put("sentencia", "");
        por.put("tipoDato", "");
        por.put("declara", "~004");
        por.put("masID", "");
        por.put("asD", "");
        por.put("asigna", "");
        por.put("A", "~001");
        por.put("cueSwi", "~008");
        por.put("BRE", "");
        por.put("cueWhi", "");
        por.put("L", "~002");
        por.put("L'", "~003");
        por.put("R", "~002");
        por.put("R'", "~003");
        por.put("E", "~002");
        por.put("E'", "~003");
        por.put("T", "~002");
        por.put("T'", "* F T'");
        por.put("F", "~002");
        por.put("D", "~002");
        tabla.put("*", por);

        Map<String, String> entre = new HashMap();
        entre.put("programa", "");
        entre.put("sentencias", "");
        entre.put("sentencia", "");
        entre.put("tipoDato", "");
        entre.put("declara", "~004");
        entre.put("masID", "");
        entre.put("asD", "");
        entre.put("asigna", "");
        entre.put("A", "~001");
        entre.put("cueSwi", "~008");
        entre.put("BRE", "");
        entre.put("cueWhi", "");
        entre.put("L", "~002");
        entre.put("L'", "~003");
        entre.put("R", "~002");
        entre.put("R'", "~003");
        entre.put("E", "~002");
        entre.put("E'", "~003");
        entre.put("T", "~002");
        entre.put("T'", "/ F T'");
        entre.put("F", "~002");
        entre.put("D", "~002");
        tabla.put("/", entre);

        Map<String, String> igual = new HashMap();
        igual.put("programa", "");
        igual.put("sentencias", "");
        igual.put("sentencia", "");
        igual.put("tipoDato", "");
        igual.put("declara", "~004");
        igual.put("masID", "");
        igual.put("asD", "= L");
        igual.put("asigna", "");
        igual.put("A", "= L");
        igual.put("cueSwi", "~008");
        igual.put("BRE", "");
        igual.put("cueWhi", "");
        igual.put("L", "~002");
        igual.put("L'", "~003");
        igual.put("R", "~002");
        igual.put("R'", "~003");
        igual.put("E", "~002");
        igual.put("E'", "~003");
        igual.put("T", "~002");
        igual.put("T'", "~003");
        igual.put("F", "~002");
        igual.put("D", "~002");
        tabla.put("=", igual);

        Map<String, String> id = new HashMap();
        id.put("programa", "sentencias finP");
        id.put("sentencias", "sentencia sentencias");
        id.put("sentencia", "asigna ;");
        id.put("tipoDato", "");
        id.put("declara", "id asD masID");
        id.put("masID", "");
        id.put("asD", "~007");
        id.put("asigna", "id A");
        id.put("A", "~001");
        id.put("cueSwi", "~008");
        id.put("BRE", "");
        id.put("cueWhi", "sentencia");
        id.put("L", "R L'");
        id.put("L'", "ç");
        id.put("R", "E R'");
        id.put("R'", "ç");
        id.put("E", "T E'");
        id.put("E'", "ç");
        id.put("T", "F T'");
        id.put("T'", "ç");
        id.put("F", "id");
        id.put("D", "id");
        tabla.put("id", id);

        Map<String, String> num = new HashMap();
        num.put("programa", "");
        num.put("sentencias", "");
        num.put("sentencia", "");
        num.put("tipoDato", "");
        num.put("declara", "~004");
        num.put("masID", "");
        num.put("asD", "");
        num.put("asigna", "");
        num.put("A", "~001");
        num.put("cueSwi", "~008");
        num.put("BRE", "");
        num.put("cueWhi", "");
        num.put("L", "R L'");
        num.put("L'", "~003");
        num.put("R", "E R'");
        num.put("R'", "~003");
        num.put("E", "T E'");
        num.put("E'", "~003");
        num.put("T", "F T'");
        num.put("T'", "~003");
        num.put("F", "num");
        num.put("D", "num");
        tabla.put("num", num);

        Map<String, String> cad = new HashMap();
        cad.put("programa", "");
        cad.put("sentencias", "");
        cad.put("sentencia", "");
        cad.put("tipoDato", "");
        cad.put("declara", "~004");
        cad.put("masID", "");
        cad.put("asD", "");
        cad.put("asigna", "");
        cad.put("A", "~001");
        cad.put("cueSwi", "~008");
        cad.put("BRE", "");
        cad.put("cueWhi", "");
        cad.put("L", "R L'");
        cad.put("L'", "~003");
        cad.put("R", "E R'");
        cad.put("R'", "~003");
        cad.put("E", "T E'");
        cad.put("E'", "~003");
        cad.put("T", "F T'");
        cad.put("T'", "~003");
        cad.put("F", "cad");
        cad.put("D", "cad");
        tabla.put("cad", cad);

        Map<String, String> pTrue = new HashMap();
        pTrue.put("programa", "");
        pTrue.put("sentencias", "");
        pTrue.put("sentencia", "");
        pTrue.put("tipoDato", "");
        pTrue.put("declara", "~004");
        pTrue.put("masID", "");
        pTrue.put("asD", "");
        pTrue.put("asigna", "");
        pTrue.put("A", "~001");
        pTrue.put("cueSwi", "~008");
        pTrue.put("BRE", "");
        pTrue.put("cueWhi", "");
        pTrue.put("L", "R L'");
        pTrue.put("L'", "~003");
        pTrue.put("R", "E R'");
        pTrue.put("R'", "~003");
        pTrue.put("E", "T E'");
        pTrue.put("E'", "~003");
        pTrue.put("T", "F T'");
        pTrue.put("T'", "~003");
        pTrue.put("F", "true");
        pTrue.put("D", "true");
        tabla.put("true", pTrue);

        Map<String, String> pFalse = new HashMap();
        pFalse.put("programa", "");
        pFalse.put("sentencias", "");
        pFalse.put("sentencia", "");
        pFalse.put("tipoDato", "");
        pFalse.put("declara", "~004");
        pFalse.put("masID", "");
        pFalse.put("asD", "");
        pFalse.put("asigna", "");
        pFalse.put("A", "~001");
        pFalse.put("cueSwi", "~008");
        pFalse.put("BRE", "");
        pFalse.put("cueWhi", "");
        pFalse.put("L", "R L'");
        pFalse.put("L'", "~003");
        pFalse.put("R", "E R'");
        pFalse.put("R'", "~003");
        pFalse.put("E", "T E'");
        pFalse.put("E'", "~003");
        pFalse.put("T", "F T'");
        pFalse.put("T'", "~003");
        pFalse.put("F", "false");
        pFalse.put("D", "false");
        tabla.put("false", pFalse);

        Map<String, String> inicioPila = new HashMap<String, String>();
        inicioPila.put("inicio", "programa");
        tabla.put("inicio", inicioPila);

        return tabla;
    }
}

/*
 * 
 * 
 * Map<String, String> idedjd = new HashMap(); id.put("modulos" , "~ x4");
 * id.put("modulo" , "~ x5"); id.put("clas" , "~ x6"); id.put("iniObj" ,
 * "~ x7"); id.put("siguidObj" , "~ x8"); id.put("pNew" , "~ x9");
 * id.put("cuerpof" , "~ x10"); id.put("metysen" , "~ x11"); id.put("tipoMet" ,
 * "~ x12"); id.put("tipoMetD" , "~ x13"); id.put("sentencias" , "~ x14");
 * id.put("sentencia" , "~ x15"); id.put("declara" , "~ x16"); id.put("vald" ,
 * "~ x17"); id.put("siguid" , "~ x18"); id.put("tipoRetorno", "~ x19");
 * id.put("args" , "~ x20"); id.put("sigar" , "~ x21"); id.put("namearg" ,
 * "~ x22"); id.put("signame" , "~ x23"); id.put("optarg" , "~ x24");
 * id.put("tipoarg" , "~ x25"); id.put("params" , "~ x26"); id.put("sigpar" ,
 * "~ x27"); id.put("newL" , "~ x28"); id.put("asigna" , "~ x29");
 * id.put("tipoasig" , "~ x30"); id.put("senelse" , "~ x31"); id.put("senten" ,
 * "~ x32"); id.put("declfor" , "~ x33"); id.put("L" , "~ x34"); id.put("L'" ,
 * "~ x35"); id.put("R" , "~ x36"); id.put("R'" , "~ x37"); id.put("E" ,
 * "~ x38"); id.put("E'" , "~ x39"); id.put("T" , "~ x40"); id.put("T'" ,
 * "~ x41"); id.put("F" , "~ x42"); id.put("newF" , "~ x43"); tabla.put("", );
 * 
 */
