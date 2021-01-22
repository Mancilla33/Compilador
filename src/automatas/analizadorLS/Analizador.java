
package automatas.analizadorLS;

import java.util.*;

import automatas.Interfaz;


public class Analizador {
    private Map<String, List> errores= new HashMap();
    private Interfaz interfaz;
    private CodigoIntermedio codigo;
    
    public Map<String, List> Analiza(String cad, CodigoIntermedio cod){
        
        errores.clear();
        codigo=cod;
        for (int i=0; i < 100; i++)
     {
      System.out.println();
     }
       Lexico lx=new Lexico(cad,this);
       Sintactico st=new Sintactico(this,codigo);
       
       boolean ban=true;
       while(ban){
           Token token=lx.getToken();
           if(token!=null){
            System.out.println("\n\n\n▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄ "+token+" ▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄");
               String res;
               do{

                res=st.analizaS(token);
                System.out.println(res);
                String linea=token.linea+"";
                if(res.startsWith("Error")){
                    AgregaError(linea, res);
                }
               }while (res.endsWith("Ø"));
                    
                
                    
                if(token.token.equals("finP"))
                    ban=false;
                if(st.estaVacia() && res.equals("Concuerda"))
                    ban=false;
           }
           
       }
       
       st.agregaFin();
       //st.impCodigo();
       if(errores.size()==0){
           interfaz.bdebug.setVisible(true);
       }else{
           interfaz.bdebug.setVisible(false);
       }
       return errores;
    }

    public Analizador(Interfaz i){
        interfaz=i;
        
        
    }
    
    
    
    public void AgregaError(String linea, String error){
        List l=new ArrayList();
                    if(errores.get(linea)!=null){
                        l=errores.get(linea);
                    }
                    l.add(error);
                    errores.put(linea+"", l);
                    ////System.out.println(errores);
    }
    
    
    
    
    
   
    
}
