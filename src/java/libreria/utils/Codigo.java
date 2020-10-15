/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.utils;

/**
 *
 * @author Cinthya Martinez
 */
public class Codigo {
    private int dato;
    private int cont=1;
    private String num="";
    
       public void generarcod(int dato, String nom){
        this.dato=dato;
        if((this.dato>=9)&&(this.dato<100)){
           int can=cont+this.dato;
           num=nom+"0"+can;            
        }
        if((this.dato>=1)&&(this.dato<9)){
           int can=cont+this.dato;
           num=nom+"00"+can;            
        }
        if(this.dato==0){
           int can=cont+this.dato;
           num=nom+"00"+can;  
        }
    }
    
    public String serie(){
        return this.num;
    }

}
