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
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "compra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CompraEntity.findAll", query = "SELECT c FROM CompraEntity c")
    , @NamedQuery(name = "CompraEntity.findByCodCompra", query = "SELECT c FROM CompraEntity c WHERE c.codCompra = :codCompra")
    , @NamedQuery(name = "CompraEntity.findByFechaCompra", query = "SELECT c FROM CompraEntity c WHERE c.fechaCompra = :fechaCompra")
    , @NamedQuery(name = "CompraEntity.findByMonto", query = "SELECT c FROM CompraEntity c WHERE c.monto = :monto")})
public class CompraEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    private String codCompra;
    @Temporal(TemporalType.DATE)
    private Date fechaCompra;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double monto;
    @OneToMany(mappedBy = "codCompra")
    private List<DetallecompraEntity> detallecompraEntityList;

    public CompraEntity() {
    }

    public CompraEntity(String codCompra) {
        this.codCompra = codCompra;
    }

    public String getCodCompra() {
        return codCompra;
    }

    public void setCodCompra(String codCompra) {
        this.codCompra = codCompra;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
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
        hash += (codCompra != null ? codCompra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompraEntity)) {
            return false;
        }
        CompraEntity other = (CompraEntity) object;
        if ((this.codCompra == null && other.codCompra != null) || (this.codCompra != null && !this.codCompra.equals(other.codCompra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "libreria.entities.CompraEntity[ codCompra=" + codCompra + " ]";
    }
    
}
