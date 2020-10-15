/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.bean;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import libreria.entities.CategoriaEntity;
import libreria.model.CategoriaModel;

/**
 *
 * @author Egbert
 */
@ManagedBean
@RequestScoped
public class CategoriaBean {

    /**
     * Creates a new instance of CategoriaBean
     */
    
    CategoriaModel modelo = new CategoriaModel();
    private CategoriaEntity categoria;
    private List<CategoriaEntity> listaCategoria;
    
    public CategoriaBean() {
         categoria = new CategoriaEntity();
    }

    public CategoriaModel getModelo() {
        return modelo;
    }

    public void setModelo(CategoriaModel modelo) {
        this.modelo = modelo;
    }

    public CategoriaEntity getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEntity categoria) {
        this.categoria = categoria;
    }

    public List<CategoriaEntity> getListaCategoria() {
        return modelo.listarCategorias();
    }

    public void setListaCategoria(List<CategoriaEntity> listaCategoria) {
        this.listaCategoria = listaCategoria;
    }
    public String guardarCategoria(int codigo){ 
        if (modelo.dato(codigo)==1) {
            if(modelo.modificarCategoria(categoria)!=1){

                return null;
            }else {
                FacesContext.getCurrentInstance().addMessage("successMessage", new
                FacesMessage(FacesMessage.SEVERITY_INFO, "Agregado Exitosamente", "Agregado")); 
                return "categoria?faces-redirect=true";
            }
        }else{
            if(modelo.insertarCategoria(categoria)!=1){ 
                FacesContext.getCurrentInstance().addMessage("errorMessage", new
                FacesMessage(FacesMessage.SEVERITY_INFO, "Esta categoria ya existe", "Categoria"));
                return null;
            }else{
                FacesContext.getCurrentInstance().addMessage("successMessage", new
                FacesMessage(FacesMessage.SEVERITY_INFO, "Agregado Exitosamente", "Agregado"));     
                return "categoria?faces-redirect=true";
            }
        }
    }
    public String eliminarCategoria(int codigo){
        if (modelo.eliminarCategoria(codigo) > 0) {
                FacesContext.getCurrentInstance().addMessage("successMessage", new
                FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado Exitosamente", "Eliminado"));            
        }else{
            
        }
         return "categoria?faces-redirect=true";
    }
    public void obtenerdatos(int codigo){
        if (modelo.dato(codigo)==1) {
            CategoriaEntity dato=modelo.obtenerCategoria(codigo);
            categoria.setCodCategoria(dato.getCodCategoria());
            categoria.setNombreCategoria(dato.getNombreCategoria());
        }else{
            
        }
    }
    public void obtenerNombre(String nombre){
        if (modelo.obtenernombre(nombre)==1) {
            FacesContext.getCurrentInstance().addMessage("errorMessage", new
            FacesMessage(FacesMessage.SEVERITY_INFO, "Esta categoria ya existe", "Categoria"));
        }
    }
}
