/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.bean;

import java.net.Authenticator;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import libreria.entities.CategoriaEntity;
import libreria.entities.ProductoEntity;
import libreria.model.CategoriaModel;
import libreria.model.ProductoModel;
import libreria.utils.Codigo;
import libreria.utils.JsfUtil;

@ManagedBean
@RequestScoped
public class ProductoBean {

    ProductoModel modelo = new ProductoModel();
    CategoriaModel cat = new CategoriaModel();
    Codigo cod = new Codigo();
    private ProductoEntity producto;
    private CategoriaEntity categoria;
    private List<ProductoEntity> listaProductos;
    private List<CategoriaEntity> listaCate;

    public ProductoBean() {
        categoria = new CategoriaEntity();
        producto = new ProductoEntity();
    }

    public ProductoEntity getProducto() {
        return producto;
    }

    public void setProducto(ProductoEntity producto) {
        this.producto = producto;
    }

    public CategoriaEntity getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEntity categoria) {
        this.categoria = categoria;
    }

    public List<CategoriaEntity> getListaCate() {
        return cat.listarCategorias();

    }

    public void setListaCate(List<CategoriaEntity> listaCate) {
        this.listaCate = listaCate;
    }

    public List<ProductoEntity> getListaProductos() {
        /* Notese que se llama al método listarEstudiantes
para obtener la lista de objetos a partir de la bd */
        return modelo.listarProducto();
    }

    public String guardarProducto() {
        
        if(producto.getCodProducto() == null || producto.getCodProducto().isEmpty()){
            // VALOR DE NULL
            // Si no posee un cod se le genera segun el orden de productos
            int total = modelo.total();
            cod.generarcod(total, "PROD");
            String codig = cod.serie();
            producto.setCodProducto(codig);
        }else{
            //No se le genera un codigo por lo tanto entra en el if de modificar
        }
        
        producto.setCodCategoria(categoria);

        if (modelo.insertarProducto(producto) != 1) {
            modelo.modificarProducto(producto);
            JsfUtil.setFlashMessage("exito", "El Producto fue actualizado exitosamente");
            return "Productos?faces-redirect=true";
        } else {
            JsfUtil.setFlashMessage("exito", "Producto registrado exitosamente");
//Forzando la redirección en el cliente
            return "Productos?faces-redirect=true";
        }
    }

    public String eliminarProducto() {
// Leyendo el parametro enviado desde la vista
        String codigo = JsfUtil.getRequest().getParameter("codigo");
        if (modelo.eliminarProducto(codigo) > 0) {
            JsfUtil.setFlashMessage("exito", "Producto eliminado exitosamente");
        } else {
            JsfUtil.setErrorMessage(null, "No se pudo borrar a este Producto");
        }
        return "Productos?faces-redirect=true";
    }

    public void returnProductoId() {
        ProductoModel modeloo = new ProductoModel();
        String codigoo = JsfUtil.getRequest().getParameter("codigoo");
        ProductoEntity productoo = modeloo.obtenerProducto(codigoo);
        if (producto != null) {
            producto.setCodProducto(codigoo);
            producto.setNombreProduct(productoo.getNombreProduct());
            producto.setPrecioCompra(productoo.getPrecioCompra());
            producto.setGanancia(productoo.getGanancia());
            producto.setPrecioVenta(productoo.getPrecioVenta());
            producto.setCantidad(productoo.getCantidad());
            producto.setDescripcion(productoo.getDescripcion());
            producto.setCodCategoria(productoo.getCodCategoria());
        } else {
            producto.setCodProducto("");
            producto.setNombreProduct("");
            producto.setCantidad(0);
            producto.setDescripcion("");
            producto.setCodCategoria(0);

        }

    }

    public void obtenerNombre(String nombre) {
        if (modelo.nombre(nombre) == 1) {
            FacesContext.getCurrentInstance().addMessage("errorMessage", new FacesMessage(FacesMessage.SEVERITY_INFO, "Esta producto ya existe", "Categoria"));
        }
    }
}
