
package automatas.analizadorLS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sintactico {
    private List<String> pila = new ArrayList();
    private Map<String, Map<String, String>> tabla = new HashMap();
    private Tablas obT;
    private int contP = 0;
    private int contL = 0;
    private boolean banInt, banDouble, banString, banBool, banAsig, banLeer, banImp, banWhi, banCase, banSwi, banDef;
    private Semantico semantico;
    private List<String> pilaWhile = new ArrayList();
    private List<String> pilaEstruct = new ArrayList();
    private String tipoSwitch, tipoWhile;
    private CodigoIntermedio codigo;
    public Analizador ana;

    public Sintactico(Analizador ana,CodigoIntermedio cod) {
        obT = new Tablas();
        tabla = obT.TablaDart();
        //// System.out.println(tabla+"");
        // pila.add("finP");
        pila.add(tabla.get("inicio").get("inicio"));
        //// System.out.println(pila);
        banInt = banDouble = banString = banBool = banAsig = banLeer = banImp= banWhi = banCase= banSwi = banDef= false;
    
        codigo=cod;
        semantico = new Semantico(codigo);
        tipoWhile = tipoSwitch = "";
        this.ana = ana;
    }

    public boolean estaVacia() {
        if (pila.size() == 0)
            return true;
        return false;
    }

    public String analizaS(Token token) {

        
        Map<String, String> tab = tabla.get(token.token.trim());
        if (tab == null) {
            if (token.token.trim().startsWith("\"") || token.token.trim().startsWith("'")) {
                return "Error sintáctico: cadena de texto no cerrada";
            }
            return "Error sintáctico";
        } else {
            while (true) {
                System.out.println("Pila: " + pila);
                //System.out.println(token.token.trim());
                String sima = pila.get(pila.size() - 1);
                if (sima.equalsIgnoreCase("cueSwi")) { // Vacia las pilas semanticas
                                                       // cuando inicia el cuerpo de
                                                       // un While o Switch
                    String errorS = semantico.reduceTodo(token.linea);
                    tipoSwitch = semantico.getTipo();
                    if (!errorS.isEmpty()) { // Si hubo un error semántico lo agrega
                        semantico.vaciaPilas();
                        ana.AgregaError(token.linea - 1 + "", errorS);
                    }
                    semantico.vaciaPilas();
                    banAsig=false;
                } else if (sima.equalsIgnoreCase("cueWhi")) {
                    String errorS = semantico.reduceTodo(token.linea);
                    tipoWhile = semantico.getTipo();
                    
                    String resE= semantico.generaWhile(pilaWhile.size()+1);
                    if (!tipoWhile.equalsIgnoreCase("bool"))
                        ana.AgregaError(token.linea + "", "Error semántico, un while necesita un booleano");
                    if (!errorS.isEmpty() || !resE.equals("")) { // Si hubo un error semántico lo agrega
                        semantico.vaciaPilas();
                        ana.AgregaError(token.linea - 1 + "", errorS);
                        ana.AgregaError(token.linea - 1 + "", resE);
                    }
                    banAsig=false;
                    
                }else if(sima.equalsIgnoreCase("sentencia") && banWhi){
                    pilaWhile.add("0");
                    banWhi=false;
                }else if(sima.equalsIgnoreCase("sentencias")){
                    boolean ban=true;
                    while(pilaWhile.size()>0 && ban){
                        String res=pilaWhile.get(pilaWhile.size()-1);
                       if (res.equals("0")){
                            semantico.generaFinWhile(pilaWhile.size());
                            pilaWhile.remove(pilaWhile.size()-1);
                        }else{
                            ban=false;
                        }
                    }
                }
                if (sima.equals(token.token.trim())) {
                    return concuerda(token);
                } else {
                    String nt = tab.get(sima);
                    if (nt == null) {
                        pila.remove(pila.size() - 1);
                        return "Error sintáctico falta: " + sima + " Ø";
                    } else if (nt.equals("ç")) {
                        if (token.token.trim().equals(")") && contP == 0)
                            return "Error sintáctico paréntesis derecho no equilibrado \")\"";
                        if (token.token.trim().equals("}") && contL == 0)
                            return "Error sintáctico llave derecha no equilibrada \"}\"";
                        pila.remove(pila.size() - 1);
                    } else if (nt.startsWith("~")) {
                        String er = obT.getError(nt);
                        if (er == null)
                            return "Error sintáctico código " + nt;
                        else
                            return "Error sintáctico " + nt + ",  " + obT.getError(nt);

                    } else {
                        String vec[] = nt.split(" ");
                        Collection<String> cole = new ArrayList();
                        for (int i = vec.length - 1; i >= 0; i--) {
                            cole.add(vec[i]);
                        }
                        pila.remove(pila.size() - 1);
                        pila.addAll(cole);
                    }
                }
            }
        }

    }

    private String concuerda(Token token) {
        pila.remove(pila.size() - 1);

        if (token.token.trim().equals("("))
            contP++;
        else if (token.token.trim().equals(")"))
            contP--;
        else if (token.token.trim().equals("{")){
            contL++;
            if(banWhi){
                pilaWhile.add("1");
                pilaEstruct.add("while");
                banWhi=false;
            }else if(banSwi){
                semantico.generaInicioSwitch(token.linea);
                pilaEstruct.add("switch");
            }
        }else if (token.token.trim().equals("}")){
            contL--;
            String est="";
            if(pilaEstruct.size()>0)
                est=pilaEstruct.get(pilaEstruct.size()-1);
            
            if(est.equalsIgnoreCase("switch")){
                banSwi=false;
                if(!banDef){
                    String resE=semantico.addSwitch(token.linea, true);
                    if(!resE.isEmpty()){
                        semantico.vaciaPilas();
                        return resE;
                    }
                }
                banDef=false;
                semantico.finSwitch();
                pilaEstruct.remove(pilaEstruct.size()-1);
            }else if(est.equalsIgnoreCase("while")){
                String res=pilaWhile.get(pilaWhile.size()-1);
                if (res.equals("1")){
                    pilaWhile.remove(pilaWhile.size()-1);
                    pilaWhile.add("0");
                }
                pilaEstruct.remove(pilaEstruct.size()-1);
            }    
            
        }
        // SEMANTICO
        if (token.token.equalsIgnoreCase("int")) {
            banInt = true;
        } else if (token.token.equalsIgnoreCase("String")) {
            banString = true;
        } else if (token.token.equalsIgnoreCase("double")) {
            banDouble = true;
        } else if (token.token.equalsIgnoreCase("bool")) {
            banBool = true;
        } else if (token.token.equalsIgnoreCase(",")) {
            String resE = semantico.reduceTodo(token.linea);
            banAsig = false;
            if (!resE.isEmpty()) {
                semantico.vaciaPilas();
                return resE;
            } else {
                semantico.generaCodigo();
            }
        } else if (token.token.equalsIgnoreCase("print")) {
            banImp=true;
            banAsig=true;
        } else if (token.token.equalsIgnoreCase("readLine")) {
            codigo.AgregarCodigo("\n  scanf(");
            banLeer=true;
        } else if (token.token.equalsIgnoreCase("while")) {
            banAsig = true;
            banWhi=true;
        } else if(token.token.equalsIgnoreCase("case")){
            banCase=true;
            banAsig=true;
        } else if(token.token.equalsIgnoreCase("switch")){
            banSwi=true;
        } else if(token.token.equalsIgnoreCase("Default")){
            banDef=true;
        }else if (token.token.equalsIgnoreCase("break")){
            semantico.breakSwitch();
        }else if(token.token.equalsIgnoreCase(":")){
            banCase=false;
            banAsig=false;
            String resE=semantico.addSwitch(token.linea, banDef);
            if(!resE.isEmpty()){
                semantico.vaciaPilas();
                return resE;
            }
        }else if (token.token.equalsIgnoreCase("switch")) {
            banAsig = false;
        } else if ((token.token.equalsIgnoreCase("num") || token.token.equalsIgnoreCase("cad")
                || token.token.equalsIgnoreCase("true") || token.token.equalsIgnoreCase("false")) && banAsig) {
            String resE = semantico.addTipo(token.tipoDato, token.lexema);
            if (!resE.isEmpty()) {
                semantico.vaciaPilas();
                return resE;
            }
        } else if (token.token.equalsIgnoreCase("id")) {
            String tipo = "";

            if (banInt) { // Declarando int
                tipo = "int";
            } else if (banString) { // Declarando String
                tipo = "String";
            } else if (banDouble) {// Declarando Double
                tipo = "double";
            } else if (banBool) {// Declarando bool
                tipo = "bool";
            } else { // Asignaciones
                String resE;
                //System.out.println(banImp+"        "+banAsig);
                if (!banAsig && !banImp) {
                    banAsig = true;
                    resE = semantico.addVar(token, true);
                } else {
                    banAsig=true;
                    resE = semantico.addVar(token);
                }
                if (!resE.isEmpty()) {
                    semantico.vaciaPilas();
                    return resE;
                }
            }
            if (!tipo.isEmpty()) {// Declara variable
                if (!banAsig) {
                    String res = semantico.declara(token.lexema, tipo, token.linea);
                    if (!res.isEmpty()) {
                        semantico.vaciaPilas();
                        return res;
                    }
                    String resE = semantico.addVar(token, true);
                    if (!resE.isEmpty()) {
                        semantico.vaciaPilas();
                        return resE;
                    }
                    banAsig = true;
                } else {
                    String resE = semantico.addVar(token, true);
                    if (!resE.isEmpty()) {
                        semantico.vaciaPilas();
                        return resE;
                    }
                }

            }
        } else if (token.token.equalsIgnoreCase(";")) {
            if(banImp){
                banImp=false;
                String resE= semantico.imprime();
                if (!resE.isEmpty()) {
                    semantico.vaciaPilas();
                    return resE;
                }
            }
            if (banAsig) {
                if (banLeer) {
                    banLeer=false;
                    String resE= semantico.leeLinea();
                    if (!resE.isEmpty()) {
                        semantico.vaciaPilas();
                        return resE;
                    }
                }else {
                    String resE = semantico.reduceTodo(token.linea);
                    if (!resE.isEmpty()) {
                        semantico.vaciaPilas();
                        return resE;
                    } else {
                        semantico.generaCodigo();
                    }
                }

            }
            semantico.vaciaPilas();
            banInt = banDouble = banString = banBool = banAsig = false;
        } else if ((token.token.equalsIgnoreCase("*") || token.token.equalsIgnoreCase("/")
                || token.token.equalsIgnoreCase("+") || token.token.equalsIgnoreCase("-")
                || token.token.equalsIgnoreCase("<") || token.token.equalsIgnoreCase(">")
                || token.token.equalsIgnoreCase("<=") || token.token.equalsIgnoreCase(">=")
                || token.token.equalsIgnoreCase("==") || token.token.equalsIgnoreCase("!=")
                || token.token.equalsIgnoreCase("||") || token.token.equalsIgnoreCase("&&")
                || token.token.equalsIgnoreCase("++") || token.token.equalsIgnoreCase("--")
                || token.token.equalsIgnoreCase("!") || token.token.equalsIgnoreCase("(")
                || token.token.equalsIgnoreCase(")") || token.token.equalsIgnoreCase("=")) && banAsig) {
            String resE = semantico.agregaOp(token.lexema, token.linea);
            if (!resE.isEmpty()) {
                return resE;
            }
        }

        return "Concuerda";
    }

    public void impCodigo(){
        codigo.impCodigo();
    }

    public void agregaFin(){
        codigo.AgregarCodigo("\n   return(0);\n}");
    }
}
