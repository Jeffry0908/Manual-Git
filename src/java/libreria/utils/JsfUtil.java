/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Cinthya Martinez
 */
public class JsfUtil {
    
    public static void mensajeError(String msg){
        FacesMessage facesmsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg,msg);
        FacesContext.getCurrentInstance().addMessage(null, facesmsg);
    }
        
    public static void exitoMensaje(String msg){
       FacesMessage facesmsg = new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg);
       FacesContext.getCurrentInstance().addMessage(null, facesmsg);
   }
    
   public static HttpServletRequest getRequest(){
     return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
   }    
   
    public static void setFlashMessage(String name, String msg) {
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put(name, msg);
    }  
    
    public static void setErrorMessage(String idClient, String msg) {
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
        FacesContext.getCurrentInstance().addMessage(idClient, mensaje);
    }
    
    //                                          //                                                  //
    
    public static HttpSession getSession() {
        return (HttpSession)
          FacesContext.
          getCurrentInstance().
          getExternalContext().
          getSession(false);
      }
       
      public static String getUserName()
      {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return  session.getAttribute("usuario").toString();
      }
       
      public static String getUserId()
      {
        HttpSession session = getSession();
        if ( session != null )
            return (String) session.getAttribute("id");
        else
            return null;
      }
}
