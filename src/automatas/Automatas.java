package automatas;

import javax.swing.JOptionPane;

public class Automatas {
    int numV;
    Interfaz i1;
    Interfaz i2;
    Interfaz i3;
    public static void main(String[] args) {
       Automatas a=new Automatas();
       a.i1=new Interfaz(a);
       a.i1.setTitle("NuevoArchivo.jjs");
    }
    public void newInterfaz(){
        if(i1==null){
            i1=new Interfaz(this);
            i1.setTitle("NuevoArchivo.jjs");
        }else{
            if(i2==null){
                i2=new Interfaz(this);
                i2.setTitle("NuevoArchivo.jjs");
            }else{
                if(i3==null){
                    i3=new Interfaz(this);
                    i3.setTitle("NuevoArchivo.jjs");
                }else{
                    JOptionPane.showMessageDialog(null, "Solo se pueden tener tres archivos abiertos, cierre uno para poder crearlo");
                }
            }
        }
    }
    
    public void cerrar(Interfaz i){
        if(i1==i){
            i1.dispose();
            i1=null;
        }else{
            if(i2==i){
                i2.dispose();
                i2=null;
            }else{
               if(i3==i){
                   i3.dispose();
                   i3=null;
               }else{
                   JOptionPane.showMessageDialog(null, "Error");
               }
            }
        }
        if(i1==null && i2==null && i3==null){
            System.exit(0);
        }
    }
    
}
