/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.bean;

import java.io.IOException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import libreria.entities.TipousuarioEntity;

import libreria.entities.UsuarioEntity;
import libreria.model.CorreoModel;
import libreria.model.UsuarioModel;
import libreria.utils.Codigo;
import libreria.utils.JsfUtil;
import libreria.utils.Token;

/**
 *
 * @author Cinthya Martinez
 */
@ManagedBean
@RequestScoped
public class UsuarioBean {

    Token token = new Token();
    String tok;
    CorreoModel correo = new CorreoModel();
    UsuarioModel modelo = new UsuarioModel();
    private UsuarioEntity usuario;
    Codigo cod = new Codigo();
    private List<UsuarioEntity> listaCategoria;

    /**
     * Creates a new instance of UsuarioBean
     */
    public UsuarioBean() {
        usuario = new UsuarioEntity();
    }

    public UsuarioModel getModelo() {
        return modelo;
    }

    public void setModelo(UsuarioModel modelo) {
        this.modelo = modelo;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public List<UsuarioEntity> getListaCategoria() {
        return modelo.listarUsuarios();
    }

    public void setListaCategoria(List<UsuarioEntity> listaCategoria) {
        this.listaCategoria = listaCategoria;
    }

    public String getTok() {
        return tok;
    }

    public void setTok(String tok) {
        this.tok = tok;
    }

    public String validarDatos() {
        try {
            int ExitsEmail = modelo.existeCorreo(usuario.getCorreoElectonico());
            System.out.println(ExitsEmail);
            if (ExitsEmail == 1) {
                JsfUtil.mensajeError("¡Error! Correo Electronico ya resgistrado");
                return null;
            }
            int total = modelo.total();
            cod.generarcod(total, "USU");
            String codigo = cod.serie();
            usuario.setCodUsuario(codigo);
            TipousuarioEntity tipo = new TipousuarioEntity();
            tipo.setCodTipoUsua(2);
            usuario.setCodTipoUsua(tipo);
            usuario.setEstado(Boolean.FALSE);
            String toke = String.valueOf(token.generarToken());
            usuario.setToken(toke);
            if (modelo.insertarUsuario(usuario) != 1) {
                JsfUtil.mensajeError("¡Error! Correo Electronico ya resgistrado");
                return null;
            } else {
                JsfUtil.exitoMensaje("Usuario ingresado, hemos enviado un correo para validar su cuenta");
                correo.enviarCorreo(usuario.getCorreoElectonico(), "Ya ha sido registrado", "Su token para validar su cuenta es:" + toke);
                return "verificarCuenta";
            }
        } catch (Exception e) {
            JsfUtil.mensajeError("¡Error! No pudo ser insertado");
            return "";
        }
    }

    public void obtenerNombre(String correo) {
        if (modelo.existeCorreo(correo) > 0) {
            JsfUtil.mensajeError("¡Error! Correo Electronico ya resgistrado");
        }
    }

    public String validarToken(String token) {
        System.out.println(token);
        if (modelo.token(token) == 1) {
            UsuarioEntity u = modelo.FindByToken(token);
            u.setEstado(true);
            if (modelo.modificarUsuario(u) == 1) {
                JsfUtil.exitoMensaje("Usuario Validado");
                return "login";
            }
        }
        return null;
    }

    public String iniciar(String usuario, String contraseña) throws IOException {
        if (modelo.IniciarSesionD(usuario, contraseña) == 1) {
            UsuarioEntity us = modelo.IniciarSesion(usuario, contraseña);
            if (us.getEstado() == true) {
               FacesContext context = FacesContext.getCurrentInstance();
               context.getExternalContext().getSessionMap().put("usuario", us.getNombre());
                switch (us.getCodTipoUsua().getCodTipoUsua()) {
                    case 1:
                        FacesContext.getCurrentInstance().getExternalContext().redirect("indexAdmin.xhtml");
                        break;
                    case 2:
                        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
                        break;
                }
            } else {
                JsfUtil.mensajeError("Su cuenta no esta validada");
                return null;
            }
        } else {
            JsfUtil.mensajeError("Usuario o contraseña no son correctos");
            return null;
        }
        return null;
    }
    public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "Producto?faces-redirect=true";
    }
}
