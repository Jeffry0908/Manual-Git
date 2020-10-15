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
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import libreria.entities.ProductoEntity;
import libreria.entities.PromocionesEntity;
import libreria.model.ProductoModel;
import libreria.model.PromocionModel;
import libreria.utils.Codigo;

/**
 *
 * @author Egbert
 */
@ManagedBean
@RequestScoped
public class PromocionesBean {
    Codigo cod=new Codigo();
    PromocionModel modelo = new PromocionModel();
    ProductoModel pro = new ProductoModel();
    private PromocionesEntity promocion;
    private ProductoEntity producto;
    private List<PromocionesEntity> listaPromocion;
    private List<ProductoEntity> listaProduc;
    /**
     * Creates a new instance of PromocionesBean
     */
    public PromocionesBean() {
        promocion = new PromocionesEntity();
        producto=new ProductoEntity();
    }

    public PromocionModel getModelo() {
        return modelo;
    }

    public void setModelo(PromocionModel modelo) {
        this.modelo = modelo;
    }

    public PromocionesEntity getPromocion() {
        return promocion;
    }

    public void setPromocion(PromocionesEntity promocion) {
        this.promocion = promocion;
    }

    public List<PromocionesEntity> getListaPromocion() {
        return modelo.ListarPromociones();
    }

    public void setListaPromocion(List<PromocionesEntity> listaPromocion) {
        this.listaPromocion = listaPromocion;
    }

    public ProductoEntity getProducto() {
        return producto;
    }

    public void setProducto(ProductoEntity producto) {
        this.producto = producto;
    }

    public List<ProductoEntity> getListaProduc() {
       return pro.listarProducto();
    }

    public void setListaProduc(List<ProductoEntity> listaProduc) {
        this.listaProduc = listaProduc;
    }
    
    
    public String guardarPromocion(String codigo){ 
             int total= modelo.total();
            cod.generarcod(total, "PRO");
            String codig= cod.serie();
            promocion.setCodPromocion(codig);       
            promocion.setCodProducto(producto);
        if (modelo.dato(codigo)==1) {
            if(modelo.modificarPromocion(promocion)!=1){

                return null;
            }else {
                FacesContext.getCurrentInstance().addMessage("successMessage", new
                FacesMessage(FacesMessage.SEVERITY_INFO, "Agregado Exitosamente", "Agregado")); 
                return "promocion?faces-redirect=true";
            }
        }else{
            if(modelo.insertarPromocion(promocion)!=1){ 
                FacesContext.getCurrentInstance().addMessage("errorMessage", new
                FacesMessage(FacesMessage.SEVERITY_INFO, "Esta promocion ya existe", "Promocion"));
                return null;
            }else{
                FacesContext.getCurrentInstance().addMessage("successMessage", new
                FacesMessage(FacesMessage.SEVERITY_INFO, "Agregado Exitosamente", "Agregado"));     
                return "promocion?faces-redirect=true";
            }
        }
    }
    public String eliminarPromocion(String codigo){
        if (modelo.eliminarPromocion(codigo) > 0) {
                FacesContext.getCurrentInstance().addMessage("successMessage", new
                FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado Exitosamente", "Eliminado"));            
        }else{
            
        }
         return "promocion?faces-redirect=true";
    }
    public void obtenerdatos(String codigo){
        if (modelo.dato(codigo)==1) {
            PromocionesEntity dato=modelo.obtenerPromocion(codigo);
            ProductoEntity tipo= new ProductoEntity();
            promocion.setCodPromocion(dato.getCodPromocion());
            promocion.setCodProducto(dato.getCodProducto());
            promocion.setFechaInicio(dato.getFechaInicio());
            promocion.setFechaFinal(dato.getFechaFinal());
            promocion.setPrecioPromocion(dato.getPrecioPromocion());
        }else{
            
        }
    }    
}
