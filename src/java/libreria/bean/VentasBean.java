/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.bean;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import libreria.entities.DetalleventaEntity;
import libreria.entities.ProductoEntity;
import libreria.entities.PromocionesEntity;
import libreria.entities.UsuarioEntity;
import libreria.entities.VentaEntity;
import libreria.model.ProductoModel;
import libreria.model.UsuarioModel;
import libreria.model.VentaModel;
import libreria.utils.Codigo;
import libreria.utils.JsfUtil;
import libreria.utils.Log;
import libreria.utils.Reporte;



/**
 *
 * @author Cinthya Martinez
 */
@ManagedBean
@SessionScoped
public class VentasBean {

    ProductoModel mode = new ProductoModel();
    private VentaEntity venta;
    Codigo codi=new Codigo();
    UsuarioModel usua= new UsuarioModel();
    VentaModel modelo = new VentaModel();
    UsuarioEntity entU=new UsuarioEntity();
    private DetalleventaEntity detalle= new DetalleventaEntity();
    private ProductoEntity producto=new ProductoEntity();
    private PromocionesEntity promocion=new PromocionesEntity();
    private int cantidad;
    private double precio;
    private List<DetalleventaEntity>lista=new ArrayList();
    private List<DetalleventaEntity>lista2=new ArrayList();
    private List<ProductoEntity> listaProductos;
    private List<DetalleventaEntity> listaVenta;
    private List<VentaEntity> listaVentaUsuario;
    private List<VentaEntity> listaVent;
    private String time;
    Reporte re=new Reporte();
    Log lo=new Log();
    public VentasBean() {
        venta=new VentaEntity();
        detalle=new DetalleventaEntity();
    }

    public VentaEntity getVenta() {
        return venta;
    }

    public void setVenta(VentaEntity venta) {
        this.venta = venta;
    }

    public Codigo getCod() {
        return codi;
    }

    public void setCod(Codigo cod) {
        this.codi = cod;
    }

    public VentaModel getModelo() {
        return modelo;
    }

    public void setModelo(VentaModel modelo) {
        this.modelo = modelo;
    }

    public DetalleventaEntity getDetalle() {
        return detalle;
    }

    public void setDetalle(DetalleventaEntity detalle) {
        this.detalle = detalle;
    }

    public ProductoEntity getProducto() {
        return producto;
    }

    public void setProducto(ProductoEntity producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public List<DetalleventaEntity> getLista() {
        return lista;
    }

    public void setLista(List<DetalleventaEntity> lista) {
        this.lista = lista;
    }

    public List<ProductoEntity> getListaProductos() {
        return mode.listarProducto();
    }

    public void setListaProductos(List<ProductoEntity> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public UsuarioEntity getEntU() {
        return entU;
    }

    public void setEntU(UsuarioEntity entU) {
        this.entU = entU;
    }

    public List<DetalleventaEntity> getListaVenta() {
        return listaVenta;
    }

    public void setListaVenta(List<DetalleventaEntity> listaVenta) {
        this.listaVenta = listaVenta;
    }

    public List<VentaEntity> getListaVentaUsuario() {
        return listaVentaUsuario;
    }

    public void setListaVentaUsuario(List<VentaEntity> listaVentaUsuario) {
        this.listaVentaUsuario = listaVentaUsuario;
    }

    public List<VentaEntity> getListaVent() {
        return listaVent=modelo.listarVentas();
    }

    public void setListaVent(List<VentaEntity> listaVent) {
        this.listaVent = listaVent;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public PromocionesEntity getPromocion() {
        return promocion;
    }

    public void setPromocion(PromocionesEntity promocion) {
        this.promocion = promocion;
    }

    public List<DetalleventaEntity> getLista2() {
        return lista2;
    }

    public void setLista2(List<DetalleventaEntity> lista2) {
        this.lista2 = lista2;
    }

    public void agregar() throws IOException{
        DetalleventaEntity det=new DetalleventaEntity();
        det.setCantidad(cantidad);
        det.setCodProducto(producto);
        this.lista.add(det);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Exito","Producto Agregado al Carrito") );
        lo.escribirlog("Producto agregado al carrito");
    }
        public void agregarPromo() throws IOException{
        DetalleventaEntity det=new DetalleventaEntity();
        det.setCantidad(cantidad);
        det.setCodProducto(producto);
        det.setPrecio(promocion.getPrecioPromocion().doubleValue());       
        this.lista2.add(det);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Exito","Producto Agregado al Carrito") );
        lo.escribirlog("Producto con promocion agrgado al carrito ");
    }
public void registrar(String cod){
        BigDecimal totalpr  = BigDecimal.ZERO;
        BigDecimal monto  = BigDecimal.ZERO;
        BigDecimal da  = BigDecimal.ZERO;
        double mon=0;
        double mon2=0;
        double tota=0;
        int cantidadFinal;
        int total = modelo.total();
        codi.generarcod(total, "VEN");
        String codig = codi.serie();
        venta.setCodVenta(codig);
        

        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        time = sdfDate.format(now);
        
        for(DetalleventaEntity dat:lista){
            mon+=dat.getCodProducto().getPrecioVenta()*dat.getCantidad();
        }
        for(DetalleventaEntity d:lista2){
            mon2+=d.getPrecio()*d.getCantidad();
        }
        entU.setCodUsuario(cod);
        venta.setMonto(mon+mon2);
        venta.setCodUsuario(entU);
        venta.setFechaVenta(now);
        venta.setEstado("Pendiente de Pago");
        modelo.insertarVenta(venta);
        
        for(DetalleventaEntity datos:lista){
            datos.getCodProducto().getCodProducto();
            datos.getCantidad();
            datos.setPrecio(datos.getCodProducto().getPrecioVenta());
            ProductoEntity p= mode.obtenerProducto(datos.getCodProducto().getCodProducto());
            cantidadFinal=p.getCantidad()-datos.getCantidad();
            p.setCantidad(cantidadFinal);
            mode.modificarProducto(p);
            datos.setCodVenta(venta);
            datos.getTotal();
            tota=datos.getCodProducto().getPrecioVenta()*datos.getCantidad();
            datos.setTotal(tota);
            modelo.insertarDetalleVenta(datos);
           
        }
        for(DetalleventaEntity dat:lista2){
            dat.getCodProducto().getCodProducto();
            dat.getCantidad();
            dat.getPrecio();
            ProductoEntity p= mode.obtenerProducto(dat.getCodProducto().getCodProducto());
            cantidadFinal=p.getCantidad()-dat.getCantidad(); 
            p.setCantidad(cantidadFinal);
            mode.modificarProducto(p);
            dat.setCodVenta(venta);
            dat.getTotal();
            tota=dat.getPrecio()*dat.getCantidad();
            dat.setTotal(tota);
            modelo.insertarDetalleVenta(dat);
        }
        lista.clear();
        lista2.clear();
    }
        public void ver(String cod) throws ClassNotFoundException, IllegalAccessException, SQLException, InstantiationException{
        int total = modelo.total();
        codi.generarcod(total, "VEN");
        String codig = codi.serie();
        
        this.registrar(cod);
            FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext =(ServletContext) facesContext.getExternalContext().getContext();
        String ruta = servletContext.getRealPath("/reportes/Factura.jasper");    
        re.getReporte(ruta,cod,codig);
        FacesContext.getCurrentInstance().responseComplete();
        
    }
        
    public List<VentaEntity>ventaUsuario(String cod){
            entU.setCodUsuario(cod);
            listaVentaUsuario=modelo.ventaUsuario(entU);
            return listaVentaUsuario;            
    } 
        public void verD(String cod,String codig) throws ClassNotFoundException, IllegalAccessException, SQLException, InstantiationException{

            FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext =(ServletContext) facesContext.getExternalContext().getContext();
        String ruta = servletContext.getRealPath("/reportes/Factura.jasper");    
        re.getReporte(ruta,cod,codig);
        FacesContext.getCurrentInstance().responseComplete();
        
    } 
       public List<DetalleventaEntity>datosDetalleVenta(String cod){
         listaVenta=modelo.listarporCod(cod);
            return listaVenta;            

    }
}
