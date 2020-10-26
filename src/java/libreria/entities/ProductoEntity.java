/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Cinthya Martinez
 */
@Entity
@Table(name = "producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductoEntity.findAll", query = "SELECT p FROM ProductoEntity p")
    ,@NamedQuery(name = "ProductoEntity.OptenerNombre", query = "SELECT COUNT(p) FROM ProductoEntity p WHERE p.nombreProduct = :nombreProduct")
    ,@NamedQuery(name = "ProductoEntity.totalProduc", query = "SELECT COUNT(p) FROM ProductoEntity p")
    , @NamedQuery(name = "ProductoEntity.findByCodProducto", query = "SELECT p FROM ProductoEntity p WHERE p.codProducto = :codProducto")
    , @NamedQuery(name = "ProductoEntity.findByNombreProduct", query = "SELECT p FROM ProductoEntity p WHERE p.nombreProduct = :nombreProduct")
    , @NamedQuery(name = "ProductoEntity.findByPrecioCompra", query = "SELECT p FROM ProductoEntity p WHERE p.precioCompra = :precioCompra")
    , @NamedQuery(name = "ProductoEntity.findByGanancia", query = "SELECT p FROM ProductoEntity p WHERE p.ganancia = :ganancia")
    , @NamedQuery(name = "ProductoEntity.findByPrecioVenta", query = "SELECT p FROM ProductoEntity p WHERE p.precioVenta = :precioVenta")
    , @NamedQuery(name = "ProductoEntity.findByCantidad", query = "SELECT p FROM ProductoEntity p WHERE p.cantidad = :cantidad")})
public class ProductoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    private String codProducto;
    private String nombreProduct;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private BigDecimal precioCompra;
    private BigDecimal ganancia;
    private BigDecimal precioVenta;
    private Integer cantidad;
    @Lob
    private byte[] imagen;
    @Lob
    private String descripcion;
    @OneToMany(mappedBy = "codProducto")
    private List<DetalleventaEntity> detalleventaEntityList;
    @JoinColumn(name = "codCategoria", referencedColumnName = "codCategoria")
    @ManyToOne
    private CategoriaEntity codCategoria;
    @OneToMany(mappedBy = "codProducto")
    private List<PromocionesEntity> promocionesEntityList;
    @OneToMany(mappedBy = "codProducto")
    private List<DetallecompraEntity> detallecompraEntityList;

    public ProductoEntity() {
    }

    public ProductoEntity(String codProducto) {
        this.codProducto = codProducto;
    }

    public String getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(String codProducto) {
        this.codProducto = codProducto;
    }

    public String getNombreProduct() {
        return nombreProduct;
    }

    public void setNombreProduct(String nombreProduct) {
        this.nombreProduct = nombreProduct;
    }

    public BigDecimal getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(BigDecimal precioCompra) {
        this.precioCompra = precioCompra;
    }

    public BigDecimal getGanancia() {
        return ganancia;
    }

    public void setGanancia(BigDecimal ganancia) {
        this.ganancia = ganancia;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<DetalleventaEntity> getDetalleventaEntityList() {
        return detalleventaEntityList;
    }

    public void setDetalleventaEntityList(List<DetalleventaEntity> detalleventaEntityList) {
        this.detalleventaEntityList = detalleventaEntityList;
    }

    public CategoriaEntity getCodCategoria() {
        return codCategoria;
    }

    public void setCodCategoria(CategoriaEntity codCategoria) {
        this.codCategoria = codCategoria;
    }

    @XmlTransient
    public List<PromocionesEntity> getPromocionesEntityList() {
        return promocionesEntityList;
    }

    public void setPromocionesEntityList(List<PromocionesEntity> promocionesEntityList) {
        this.promocionesEntityList = promocionesEntityList;
    }

    @XmlTransient
    public List<DetallecompraEntity> getDetallecompraEntityList() {
        return detallecompraEntityList;
    }

    public void setDetallecompraEntityList(List<DetallecompraEntity> detallecompraEntityList) {
        this.detallecompraEntityList = detallecompraEntityList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codProducto != null ? codProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductoEntity)) {
            return false;
        }
        ProductoEntity other = (ProductoEntity) object;
        if ((this.codProducto == null && other.codProducto != null) || (this.codProducto != null && !this.codProducto.equals(other.codProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "libreria.entities.ProductoEntity[ codProducto=" + codProducto + " ]";
    }

    public void setCodCategoria(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
