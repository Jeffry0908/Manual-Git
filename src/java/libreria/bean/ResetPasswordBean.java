/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.bean;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;
import java.util.Properties;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import libreria.entities.TipousuarioEntity;
import libreria.entities.UsuarioEntity;
import libreria.model.UsuarioModel;
import libreria.utils.JsfUtil;

/**
 *
 * @author anto
 */
@ManagedBean
@SessionScoped
public class ResetPasswordBean {
    
    private UsuarioModel modelo = new UsuarioModel();
    private UsuarioEntity usuario;
    private String correoElectonico;
    
    
    /**
     * Creates a new instance of ResetPasswordBean
     */
    public ResetPasswordBean() {
        usuario = new UsuarioEntity();
    }

    public String actualizaPassword(){
        correoElectonico = usuario.getCorreoElectonico();
     
        SecureRandom random = new SecureRandom();
        String text = new BigInteger(20, random).toString(1);
        
        List<UsuarioEntity> usu = modelo.obtenerPorCorreo(correoElectonico);

        if(!usu.isEmpty()){
               
            for(UsuarioEntity u: usu){
             
                  
        //MANDANDO CONTRASENIA************************************************************8
          try{ 
            // Propiedades de la conexión
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.user", "libreriasv2019@gmail.com");
            props.setProperty("mail.smtp.auth", "true");

            // Preparamos la sesion
            Session session = Session.getDefaultInstance(props);

            // Construimos el mensaje
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("libreriasv2019@gmail.com"));
            message.addRecipient(
                Message.RecipientType.TO,
                new InternetAddress(correoElectonico));
            message.setSubject("Su contraseña a sido cambiada");
            message.setText("Hola " + u.getNombre() + " su contraseña "
                        + "ha sido cambiada ha la siguiente: "
                        + text);

            // Lo enviamos.
            Transport t = session.getTransport("smtp");
            t.connect("libreriasv2019@gmail.com", "Libreria2019");
            t.sendMessage(message, message.getAllRecipients());

            // Cierre.
            t.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
          
          //FIN MANDANDO CONTRA**************************************************************8
         
          String encriptada = DigestUtils.md5Hex(text);
          u.setPassword(text);
          
                 modelo.modificarUsuario(u);
              }
              
           }
    
        JsfUtil.setFlashMessage("exito", "Su contraseña a sido restablecisa"
                    + " COMPRUEBE SU CORREO ELECTRONICO, SE LE ASIGNARA UNA CONTRASEÑA NUEVA");
        
        return"resetPassword?faces-redirect=true";
    }
    
    
    
    /**
     * @return the modelo
     */
    public UsuarioModel getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(UsuarioModel modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the usuario
     */
    public UsuarioEntity getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the correoElectonico
     */
    public String getCorreoElectonico() {
        return correoElectonico;
    }

    /**
     * @param correoElectonico the correoElectonico to set
     */
    public void setCorreo(String correoElectonico) {
        this.correoElectonico = correoElectonico;
    }

    private static class DigestUtils {

        private static String md5Hex(String text) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        public DigestUtils() {
        }
    }
    
}
