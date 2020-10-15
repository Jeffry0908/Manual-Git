/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Cinthya Martinez
 */
@Entity
@Table(name = "detallecompra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetallecompraEntity.findAll", query = "SELECT d FROM DetallecompraEntity d")
    , @NamedQuery(name = "DetallecompraEntity.findByIddetalleCom", query = "SELECT d FROM DetallecompraEntity d WHERE d.iddetalleCom = :iddetalleCom")
    , @NamedQuery(name = "DetallecompraEntity.findByCantidad", query = "SELECT d FROM DetallecompraEntity d WHERE d.cantidad = :cantidad")
    , @NamedQuery(name = "DetallecompraEntity.findByTotal", query = "SELECT d FROM DetallecompraEntity d WHERE d.total = :total")})
public class DetallecompraEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer iddetalleCom;
    private Integer cantidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double total;
    @JoinColumn(name = "CodProducto", referencedColumnName = "CodProducto")
    @ManyToOne
    private ProductoEntity codProducto;
    @JoinColumn(name = "CodCompra", referencedColumnName = "CodCompra")
    @ManyToOne
    private CompraEntity codCompra;

    public DetallecompraEntity() {
    }

    public DetallecompraEntity(Integer iddetalleCom) {
        this.iddetalleCom = iddetalleCom;
    }

    public Integer getIddetalleCom() {
        return iddetalleCom;
    }

    public void setIddetalleCom(Integer iddetalleCom) {
        this.iddetalleCom = iddetalleCom;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public ProductoEntity getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(ProductoEntity codProducto) {
        this.codProducto = codProducto;
    }

    public CompraEntity getCodCompra() {
        return codCompra;
    }

    public void setCodCompra(CompraEntity codCompra) {
        this.codCompra = codCompra;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddetalleCom != null ? iddetalleCom.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetallecompraEntity)) {
            return false;
        }
        DetallecompraEntity other = (DetallecompraEntity) object;
        if ((this.iddetalleCom == null && other.iddetalleCom != null) || (this.iddetalleCom != null && !this.iddetalleCom.equals(other.iddetalleCom))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "libreria.entities.DetallecompraEntity[ iddetalleCom=" + iddetalleCom + " ]";
    }
    
}
