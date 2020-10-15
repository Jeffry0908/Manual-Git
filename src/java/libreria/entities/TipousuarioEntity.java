/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
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
@Table(name = "tipousuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipousuarioEntity.findAll", query = "SELECT t FROM TipousuarioEntity t")
    , @NamedQuery(name = "TipousuarioEntity.findByCodTipoUsua", query = "SELECT t FROM TipousuarioEntity t WHERE t.codTipoUsua = :codTipoUsua")
    , @NamedQuery(name = "TipousuarioEntity.findByTipo", query = "SELECT t FROM TipousuarioEntity t WHERE t.tipo = :tipo")})
public class TipousuarioEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CodTipoUsua")
    private Integer codTipoUsua;
    @Column(name = "Tipo")
    private String tipo;
    @OneToMany(mappedBy = "codTipoUsua")
    private List<UsuarioEntity> usuarioEntityList;

    public TipousuarioEntity() {
    }

    public TipousuarioEntity(Integer codTipoUsua) {
        this.codTipoUsua = codTipoUsua;
    }

    public Integer getCodTipoUsua() {
        return codTipoUsua;
    }

    public void setCodTipoUsua(Integer codTipoUsua) {
        this.codTipoUsua = codTipoUsua;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @XmlTransient
    public List<UsuarioEntity> getUsuarioEntityList() {
        return usuarioEntityList;
    }

    public void setUsuarioEntityList(List<UsuarioEntity> usuarioEntityList) {
        this.usuarioEntityList = usuarioEntityList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codTipoUsua != null ? codTipoUsua.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipousuarioEntity)) {
            return false;
        }
        TipousuarioEntity other = (TipousuarioEntity) object;
        if ((this.codTipoUsua == null && other.codTipoUsua != null) || (this.codTipoUsua != null && !this.codTipoUsua.equals(other.codTipoUsua))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "libreria.entities.TipousuarioEntity[ codTipoUsua=" + codTipoUsua + " ]";
    }
    
}
