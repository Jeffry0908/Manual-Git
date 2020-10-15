/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.bean;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import libreria.entities.CompraEntity;
import libreria.model.ComprasModel;
import libreria.utils.JsfUtil;
@ManagedBean
@RequestScoped
public class CompraBean {
    
ComprasModel modelo = new ComprasModel();

private CompraEntity compra;

private List<CompraEntity> listacompras;

    


public CompraBean() {
compra = new CompraEntity();
}


public CompraEntity getCompra() {
return compra;
}
public void setCompra(CompraEntity compra) {
this.compra= compra;
}

public List<CompraEntity> getListaCompras() {

return modelo.listarCompra();
}


public String actualizarCompra() {
modelo.modificarCompra(compra);
JsfUtil.exitoMensaje("La compra fue actualizada exitosamente");
return "Compras?faces-redirect=true";

}



public String guardarCompra() {
if (modelo.insertarCompra(compra) != 1) {

JsfUtil.exitoMensaje("La compra ya existe");
return "Compras?faces-redirect=true";
} else {
    
    modelo.insertarCompra(compra);
JsfUtil.exitoMensaje("Compra registrado exitosamente");

return "Compras?faces-redirect=true";
}
}

public String eliminarCompra() {
// Leyendo el parametro enviado desde la vista
String codigo= JsfUtil.getRequest().getParameter("codigo");
if (modelo.eliminarCompra(codigo) > 0) {
JsfUtil.exitoMensaje( "Compra eliminada exitosamente");
}
else{
JsfUtil.mensajeError( "No se pudo borrar la comprar");
}
return "Compras?faces-redirect=true";
}


public void returnCompraCodigo(){
ComprasModel modeloo = new ComprasModel();
String codigoo= JsfUtil.getRequest().getParameter("codigoo");
CompraEntity compraa = modeloo.obtenerCompra(codigoo);

if(compraa != null){
compra.setCodCompra(codigoo);
compra.setFechaCompra(compraa.getFechaCompra());
compra.setMonto(compraa.getMonto());
}else{
compra.setCodCompra("");
compra.setMonto(0.0);


}
}



}
