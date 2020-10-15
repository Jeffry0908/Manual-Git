/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Cinthya Martinez
 */
@Entity
@Table(name = "venta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VentaEntity.findAll", query = "SELECT v FROM VentaEntity v")
    , @NamedQuery(name = "VentaEntity.findByCodVenta", query = "SELECT v FROM VentaEntity v WHERE v.codVenta = :codVenta")
    , @NamedQuery(name = "VentaEntity.findByFechaVenta", query = "SELECT v FROM VentaEntity v WHERE v.fechaVenta = :fechaVenta")
    , @NamedQuery(name = "VentaEntity.findByMonto", query = "SELECT v FROM VentaEntity v WHERE v.monto = :monto")
    , @NamedQuery(name = "VentaEntity.findByEstado", query = "SELECT v FROM VentaEntity v WHERE v.estado = :estado")})
public class VentaEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    private String codVenta;
    @Temporal(TemporalType.DATE)
    private Date fechaVenta;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double monto;
    private String estado;
    @JoinColumn(name = "CodUsuario", referencedColumnName = "CodUsuario")
    @ManyToOne
    private UsuarioEntity codUsuario;
    @OneToMany(mappedBy = "codVenta")
    private List<DetalleventaEntity> detalleventaEntityList;

    public VentaEntity() {
    }

    public VentaEntity(String codVenta) {
        this.codVenta = codVenta;
    }

    public String getCodVenta() {
        return codVenta;
    }

    public void setCodVenta(String codVenta) {
        this.codVenta = codVenta;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public UsuarioEntity getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(UsuarioEntity codUsuario) {
        this.codUsuario = codUsuario;
    }

    @XmlTransient
    public List<DetalleventaEntity> getDetalleventaEntityList() {
        return detalleventaEntityList;
    }

    public void setDetalleventaEntityList(List<DetalleventaEntity> detalleventaEntityList) {
        this.detalleventaEntityList = detalleventaEntityList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codVenta != null ? codVenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VentaEntity)) {
            return false;
        }
        VentaEntity other = (VentaEntity) object;
        if ((this.codVenta == null && other.codVenta != null) || (this.codVenta != null && !this.codVenta.equals(other.codVenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "libreria.entidades.VentaEntity[ codVenta=" + codVenta + " ]";
    }
    
}
