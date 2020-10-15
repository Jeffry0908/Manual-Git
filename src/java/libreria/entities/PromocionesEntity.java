/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Cinthya Martinez
 */
@Entity
@Table(name = "promociones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PromocionesEntity.findAll", query = "SELECT p FROM PromocionesEntity p")
    ,@NamedQuery(name="UsuarioEntity.totalPromocion", query="SELECT COUNT(p) FROM PromocionesEntity p")
    ,@NamedQuery(name="OptenerPr", query="SELECT COUNT(p) FROM PromocionesEntity p WHERE p.codPromocion = :codPromocion") 
    , @NamedQuery(name = "PromocionesEntity.findByCodPromocion", query = "SELECT p FROM PromocionesEntity p WHERE p.codPromocion = :codPromocion")
    , @NamedQuery(name = "PromocionesEntity.findByFechaInicio", query = "SELECT p FROM PromocionesEntity p WHERE p.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "PromocionesEntity.findByFechaFinal", query = "SELECT p FROM PromocionesEntity p WHERE p.fechaFinal = :fechaFinal")
    , @NamedQuery(name = "PromocionesEntity.findByPrecioPromocion", query = "SELECT p FROM PromocionesEntity p WHERE p.precioPromocion = :precioPromocion")})
public class PromocionesEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    private String codPromocion;
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Temporal(TemporalType.DATE)
    private Date fechaFinal;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private BigDecimal precioPromocion;
    @JoinColumn(name = "CodProducto", referencedColumnName = "CodProducto")
    @ManyToOne
    private ProductoEntity codProducto;

    public PromocionesEntity() {
    }

    public PromocionesEntity(String codPromocion) {
        this.codPromocion = codPromocion;
    }

    public String getCodPromocion() {
        return codPromocion;
    }

    public void setCodPromocion(String codPromocion) {
        this.codPromocion = codPromocion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public BigDecimal getPrecioPromocion() {
        return precioPromocion;
    }

    public void setPrecioPromocion(BigDecimal precioPromocion) {
        this.precioPromocion = precioPromocion;
    }

    public ProductoEntity getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(ProductoEntity codProducto) {
        this.codProducto = codProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codPromocion != null ? codPromocion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PromocionesEntity)) {
            return false;
        }
        PromocionesEntity other = (PromocionesEntity) object;
        if ((this.codPromocion == null && other.codPromocion != null) || (this.codPromocion != null && !this.codPromocion.equals(other.codPromocion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "libreria.entities.PromocionesEntity[ codPromocion=" + codPromocion + " ]";
    }
    
}
