/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "categoria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CategoriaEntity.findAll", query = "SELECT c FROM CategoriaEntity c")
    ,@NamedQuery(name="Optener", query="SELECT COUNT(c) FROM CategoriaEntity c WHERE c.codCategoria = :codCategoria")    
    ,@NamedQuery(name="OptenerNombre", query="SELECT COUNT(c) FROM CategoriaEntity c WHERE c.nombreCategoria = :nombreCategoria") 
    , @NamedQuery(name = "CategoriaEntity.findByCodCategoria", query = "SELECT c FROM CategoriaEntity c WHERE c.codCategoria = :codCategoria")
    , @NamedQuery(name = "CategoriaEntity.findByNombreCategoria", query = "SELECT c FROM CategoriaEntity c WHERE c.nombreCategoria = :nombreCategoria")})
public class CategoriaEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer codCategoria;
    private String nombreCategoria;
    @OneToMany(mappedBy = "codCategoria")
    private List<ProductoEntity> productoEntityList;

    public CategoriaEntity() {
    }

    public CategoriaEntity(Integer codCategoria) {
        this.codCategoria = codCategoria;
    }

    public Integer getCodCategoria() {
        return codCategoria;
    }

    public void setCodCategoria(Integer codCategoria) {
        this.codCategoria = codCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    @XmlTransient
    public List<ProductoEntity> getProductoEntityList() {
        return productoEntityList;
    }

    public void setProductoEntityList(List<ProductoEntity> productoEntityList) {
        this.productoEntityList = productoEntityList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codCategoria != null ? codCategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CategoriaEntity)) {
            return false;
        }
        CategoriaEntity other = (CategoriaEntity) object;
        if ((this.codCategoria == null && other.codCategoria != null) || (this.codCategoria != null && !this.codCategoria.equals(other.codCategoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "libreria.entities.CategoriaEntity[ codCategoria=" + codCategoria + " ]";
    }
    
}
