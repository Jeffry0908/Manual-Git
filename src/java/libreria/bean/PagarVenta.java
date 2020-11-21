/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.bean;

import java.io.IOException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import libreria.entities.DetalleventaEntity;
import libreria.entities.VentaEntity;
import libreria.model.VentaModel;
import libreria.utils.JsfUtil;
import libreria.utils.Log;

/**
 *
 * @author Cinthya Martinez
 */
@ManagedBean
@RequestScoped
public class PagarVenta {
    VentaModel modelo = new VentaModel();
    private VentaEntity venta;
    private DetalleventaEntity detalle= new DetalleventaEntity();
    Log lo=new Log();
    private List<DetalleventaEntity> listaVenta;
    public List<DetalleventaEntity> getListaVenta() {
        return listaVenta;
    }

    public void setListaVenta(List<DetalleventaEntity> listaVenta) {
        this.listaVenta = listaVenta;
    }
    /**
     * Creates a new instance of PagarVenta
     */
    public PagarVenta() {
        venta=new VentaEntity();
        detalle=new DetalleventaEntity();
    }

    public DetalleventaEntity getDetalle() {
        return detalle;
    }

    public void setDetalle(DetalleventaEntity detalle) {
        this.detalle = detalle;
    }

    public VentaModel getModelo() {
        return modelo;
    }

    public void setModelo(VentaModel modelo) {
        this.modelo = modelo;
    }

    public VentaEntity getVenta() {
        return venta;
    }

    public void setVenta(VentaEntity venta) {
        this.venta = venta;
    }
    
    public List<DetalleventaEntity>datosVenta(String cod) throws IOException{
        if(modelo.codigo(cod)==1){
            VentaEntity mon=modelo.monto(cod);
            venta.setMonto(mon.getMonto());
            venta.setEstado(mon.getEstado());
            venta.setCodVenta(mon.getCodVenta());
            venta.setCodUsuario(mon.getCodUsuario());
            listaVenta=modelo.listarporCod(cod);
            lo.escribirlog("LLena la vista con los datos de venta  "+cod);
            return listaVenta;            
        }else{
            JsfUtil.mensajeError("No existe compra con codigo "+cod);
            lo.escribirlog("no se encontro registro con codigo "+cod);
        }
        return null;

    }
    public String pagar(String cod) throws IOException{
         VentaEntity datos=modelo.monto(cod);
         datos.setEstado("Cancelada");
         modelo.modificarVenta(datos);
        FacesContext context = FacesContext.getCurrentInstance();
        lo.escribirlog("Se modifico el estado de venta a cancelada "+cod);
        context.addMessage(null, new FacesMessage("Exito","Venta Cancelada") );         
        return "PagarCompra";
        
    }    
}
