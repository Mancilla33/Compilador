package automatas.analizadorLS;

import java.util.*;

class Simbolo {
    String id;
    String tipo;
    String valor;
    int linea;

    public Simbolo(String id, String tipo, String valor, int linea) {
        this.id    = id;
        this.tipo  = tipo;
        this.valor = valor;
        this.linea = linea;
    }

    public String toString() {
        return "ID="+id+" TIPO="+tipo;
    }
}

public class TablaDeSim {
    private Map<String, Simbolo> tab = new HashMap<String, Simbolo>();

    public boolean addSimbolo(String id, String tipo, String valor, int linea) {
        if (containsSimbolo(id))
            return false;
        Simbolo simb = new Simbolo(id, tipo, valor, linea);
        tab.put(id, simb);
        System.out.println("\nVariables declaradas: "+tab);
        return true;
    }

    public Simbolo getSimbolo(String id){
        if(containsSimbolo(id))
            return tab.get(id);
        return null;
    }

    public String getTipo(String id) {
        if (!containsSimbolo(id))
            return "";
        return tab.get(id).tipo;
    }

    public String getValor(String id) {
        if (!containsSimbolo(id))
            return "";
        return tab.get(id).valor;
    }

    public int getLinea(String id) {
        if (!containsSimbolo(id))
            return -1;
        return tab.get(id).linea;
    }

    public boolean setValor(String id, String valor) {
        if (!containsSimbolo(id))
            return false;
        Simbolo sim = tab.get(id);
        sim.valor=valor;
        tab.put(id, sim);
        return true;
    }

    public boolean containsSimbolo(String id) {
        if (tab.containsKey(id))
            return true;
        return false;
    }
}