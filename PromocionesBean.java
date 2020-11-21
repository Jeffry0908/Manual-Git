/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.bean;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import libreria.entities.ProductoEntity;
import libreria.entities.PromocionesEntity;
import libreria.model.ProductoModel;
import libreria.model.PromocionModel;
import libreria.utils.Codigo;
import libreria.utils.JsfUtil;
import libreria.utils.Log;

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
    Log lo=new Log();
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
        return listaPromocion=modelo.ListarPromociones();
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
    
    
     public String guardarPromocion(String codigo) throws IOException{
         ProductoEntity prod=pro.obtenerProducto(producto.getCodProducto());
        if(promocion.getFechaFinal().before(promocion.getFechaInicio())){
          JsfUtil.mensajeError("Fecha de fin no puede ser menor a la de inicio");
        }else if(promocion.getPrecioPromocion().compareTo(new BigDecimal (prod.getPrecioVenta()))==1){
         JsfUtil.mensajeError("El precio de la promocion no puede ser mayor al de venta");
        }
        else{ 
        if(promocion.getCodPromocion() == null || promocion.getCodPromocion().isEmpty()){
            int total = modelo.total();
            cod.generarcod(total, "PRO");
            String codig = cod.serie();
            promocion.setCodPromocion(codig);
        }else{
            
        }      
            promocion.setCodProducto(producto);
            if (modelo.insertarPromocion(promocion) != 1) {
            return null;//Regreso a la misma página
            } else {
            //Forzando la redirección en el cliente
            JsfUtil.setFlashMessage("exito","promocion registrada exitosamente");
            lo.escribirlog("Se agrego la promocion");
            return "promocion?faces-redirect=true";
            }}
        return null;
    }
     public String modificarPromocion(String codigo) throws IOException{ 
           
            if (modelo.modificarPromocion(promocion) != 1) {
            return null;//Regreso a la misma página
            } else {
                 JsfUtil.setFlashMessage("exito","promocion modificada exitosamente");
                 lo.escribirlog("Se modifico la promocion");
            //Forzando la redirección en el cliente
            return "promocion?faces-redirect=true";
            }
    }
    public String eliminarPromocion(String codigo){
        if (modelo.eliminarPromocion(codigo) > 0) {
            JsfUtil.setFlashMessage("exito","promocion eliminada exitosamente");           
        }else{
            JsfUtil.setErrorMessage(null, "No se pudo borrar a esta promocion");
        }
         return "promocion";
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
