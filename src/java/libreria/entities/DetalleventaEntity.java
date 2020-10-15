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
@Table(name = "detalleventa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleventaEntity.findAll", query = "SELECT d FROM DetalleventaEntity d")
    , @NamedQuery(name = "DetalleventaEntity.findByIddetalle", query = "SELECT d FROM DetalleventaEntity d WHERE d.iddetalle = :iddetalle")
    , @NamedQuery(name = "DetalleventaEntity.findByCantidad", query = "SELECT d FROM DetalleventaEntity d WHERE d.cantidad = :cantidad")
    , @NamedQuery(name = "DetalleventaEntity.findByTotal", query = "SELECT d FROM DetalleventaEntity d WHERE d.total = :total")})
public class DetalleventaEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer iddetalle;
    private Integer cantidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double total;
    @JoinColumn(name = "CodProducto", referencedColumnName = "CodProducto")
    @ManyToOne
    private ProductoEntity codProducto;
    @JoinColumn(name = "CodVenta", referencedColumnName = "CodVenta")
    @ManyToOne
    private VentaEntity codVenta;

    public DetalleventaEntity() {
    }

    public DetalleventaEntity(Integer iddetalle) {
        this.iddetalle = iddetalle;
    }

    public Integer getIddetalle() {
        return iddetalle;
    }

    public void setIddetalle(Integer iddetalle) {
        this.iddetalle = iddetalle;
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

    public VentaEntity getCodVenta() {
        return codVenta;
    }

    public void setCodVenta(VentaEntity codVenta) {
        this.codVenta = codVenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddetalle != null ? iddetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleventaEntity)) {
            return false;
        }
        DetalleventaEntity other = (DetalleventaEntity) object;
        if ((this.iddetalle == null && other.iddetalle != null) || (this.iddetalle != null && !this.iddetalle.equals(other.iddetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "libreria.entities.DetalleventaEntity[ iddetalle=" + iddetalle + " ]";
    }
    
}
