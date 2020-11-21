/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.bean;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean; 
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import libreria.entities.CompraEntity;
import libreria.entities.DetallecompraEntity;
import libreria.entities.ProductoEntity;
import libreria.model.ComprasModel;
import libreria.model.ProductoModel;
import libreria.utils.Codigo;
import libreria.utils.Log;

 
@ManagedBean
@ViewScoped
public class CompraBean {

    private CompraEntity compra;
    Codigo cod=new Codigo();
    ComprasModel modelo = new ComprasModel();
    ProductoModel modelP = new ProductoModel();
    private DetallecompraEntity detalle= new DetallecompraEntity();
    private ProductoEntity producto=new ProductoEntity();
    private int cantidad;
    private List<DetallecompraEntity>lista=new ArrayList();
    private List<CompraEntity> listaCompra;
   private List<DetallecompraEntity> listaDetalleC;
   Log lo=new Log();
    public CompraBean() {
        compra=new CompraEntity();
    }
    
    public List<DetallecompraEntity> getLista() {
        return lista;
    }

    public void setLista(List<DetallecompraEntity> lista) {
        this.lista = lista;
    }
    
    
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public ProductoEntity getProducto() {
        return producto;
    }

    public void setProducto(ProductoEntity producto) {
        this.producto = producto;
    }
    
    
    public DetallecompraEntity getDetalle() {
        return detalle;
    }

    public void setDetalle(DetallecompraEntity detalle) {
        this.detalle = detalle;
    }

    public CompraEntity getCompra() {
        return compra;
    }

    public void setCompra(CompraEntity compra) {
        this.compra = compra;
    }

    public List<CompraEntity> getListaCompra() {
        return listaCompra=modelo.listarCompra();
    }

    public void setListaCompra(List<CompraEntity> listaCompra) {
        this.listaCompra = listaCompra;
    }

    public List<DetallecompraEntity> getListaDetalleC() {
        return listaDetalleC;
    }

    public void setListaDetalleC(List<DetallecompraEntity> listaDetalleC) {
        this.listaDetalleC = listaDetalleC;
    }
    
    public void Agregar() throws IOException{
        DetallecompraEntity det=new DetallecompraEntity();
        det.setCantidad(cantidad);
        det.setCodProducto(producto);
        this.lista.add(det);
        lo.escribirlog("Agregado a la lista de compras");
        
    } 
    public String registrar(){
        BigDecimal totalpr  = BigDecimal.ZERO;
        BigDecimal monto  = BigDecimal.ZERO;
        BigDecimal da  = BigDecimal.ZERO;
        double mon=0;
        double tota=0;
        int cantidadFinal;
        int total = modelo.total();
        cod.generarcod(total, "COM");
        String codig = cod.serie();
        compra.setCodCompra(codig);
        for(DetallecompraEntity dat:lista){
            mon+=dat.getCodProducto().getPrecioCompra()*dat.getCantidad();  
        }
        compra.setMonto(mon);
        modelo.insertarCompra(compra);
        
        for(DetallecompraEntity datos:lista){
            datos.getCodProducto().getCodProducto();
            datos.getCantidad();
            ProductoEntity p= modelP.obtenerProducto(datos.getCodProducto().getCodProducto());
            cantidadFinal=p.getCantidad()+datos.getCantidad();
            p.setCantidad(cantidadFinal);
            modelP.modificarProducto(p);
            datos.setCodCompra(compra);
            datos.getTotal();
            tota=datos.getCodProducto().getPrecioCompra()*datos.getCantidad();
            datos.setTotal(tota);
            modelo.insertarDetalleCompra(datos);
        }
        lista.clear();
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Exito","Compra Ingresada") );    
        return "compra";
    }
       public List<DetallecompraEntity>datosDetalleVenta(String cod){
         listaDetalleC=modelo.listarporCod(cod);
            return listaDetalleC;            

    }
}
