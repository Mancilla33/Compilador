package automatas.analizadorLS;

public class CodigoIntermedio {
    public String codigo="//Código generado por la computadora, no lo modifique \n#include<stdio.h> \nint main(){ \n  float VI1,VI2,VI3,VI4;\n  char *VS1,*VS2,*VS3,*VS4;\n  float VD1,VD2,VD3,VD4;\n  int VB1,VB2,VB3,VB4;\n";
    
    public void AgregarCodigo(String cad){
        codigo+=cad;
        //System.out.println(codigo);
    }

    public void impCodigo(){
        System.out.println(codigo);
    }
}
