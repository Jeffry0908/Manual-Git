/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
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
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioEntity.findAll", query = "SELECT u FROM UsuarioEntity u")
    ,@NamedQuery(name="OptenerCod", query="SELECT COUNT(u) FROM UsuarioEntity u WHERE u.codUsuario = :codUsuario") 
    ,@NamedQuery(name="UsuarioEntity.iniciarSesionD", query="SELECT COUNT(u) FROM UsuarioEntity u WHERE u.correoElectonico = :correoElectonico AND u.contrase\u00f1a = :contrase\u00f1a") 
    ,@NamedQuery(name="UsuarioEntity.iniciarSesion", query="SELECT u FROM UsuarioEntity u WHERE u.correoElectonico = :correoElectonico AND u.contrase\u00f1a = :contrase\u00f1a") 
    ,@NamedQuery(name="UsuarioEntity.buscarCorreo", query="SELECT COUNT(u) FROM UsuarioEntity u WHERE u.correoElectonico = :correoElectonico")   
    ,@NamedQuery(name="UsuarioEntity.totalUsuario", query="SELECT COUNT(u) FROM UsuarioEntity u")  
    ,@NamedQuery(name="UsuarioEntity.token", query="SELECT COUNT(u) FROM UsuarioEntity u WHERE u.token = :token") 
    , @NamedQuery(name = "UsuarioEntity.findByCodUsuario", query = "SELECT u FROM UsuarioEntity u WHERE u.codUsuario = :codUsuario")
    , @NamedQuery(name = "UsuarioEntity.findByToken", query = "SELECT u FROM UsuarioEntity u WHERE u.token = :token")
    , @NamedQuery(name = "UsuarioEntity.findByNombre", query = "SELECT u FROM UsuarioEntity u WHERE u.nombre = :nombre")
    , @NamedQuery(name = "UsuarioEntity.findByApellido", query = "SELECT u FROM UsuarioEntity u WHERE u.apellido = :apellido")
    , @NamedQuery(name = "UsuarioEntity.findByTelefono", query = "SELECT u FROM UsuarioEntity u WHERE u.telefono = :telefono")
    , @NamedQuery(name = "UsuarioEntity.findByCorreoElectonico", query = "SELECT u FROM UsuarioEntity u WHERE u.correoElectonico = :correoElectonico")
    , @NamedQuery(name = "UsuarioEntity.findByContrase\u00f1a", query = "SELECT u FROM UsuarioEntity u WHERE u.contrase\u00f1a = :contrase\u00f1a")
    , @NamedQuery(name = "UsuarioEntity.findByDireccion", query = "SELECT u FROM UsuarioEntity u WHERE u.direccion = :direccion")
    , @NamedQuery(name = "UsuarioEntity.findByEstado", query = "SELECT u FROM UsuarioEntity u WHERE u.estado = :estado")})
public class UsuarioEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    private String codUsuario;
    private String nombre;
    private String apellido;
    private String telefono;
    private String correoElectonico;
    private String contraseña;
    private String direccion;
    private Boolean estado;
    private String token;
    @JoinColumn(name = "CodTipoUsua", referencedColumnName = "CodTipoUsua")
    @ManyToOne
    private TipousuarioEntity codTipoUsua;

    public UsuarioEntity() {
    }

    public UsuarioEntity(String codUsuario) {
        this.codUsuario = codUsuario;
    }

    public String getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(String codUsuario) {
        this.codUsuario = codUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectonico() {
        return correoElectonico;
    }

    public void setCorreoElectonico(String correoElectonico) {
        this.correoElectonico = correoElectonico;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public TipousuarioEntity getCodTipoUsua() {
        return codTipoUsua;
    }

    public void setCodTipoUsua(TipousuarioEntity codTipoUsua) {
        this.codTipoUsua = codTipoUsua;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codUsuario != null ? codUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioEntity)) {
            return false;
        }
        UsuarioEntity other = (UsuarioEntity) object;
        if ((this.codUsuario == null && other.codUsuario != null) || (this.codUsuario != null && !this.codUsuario.equals(other.codUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "libreria.entities.UsuarioEntity[ codUsuario=" + codUsuario + " ]";
    }
    
}
