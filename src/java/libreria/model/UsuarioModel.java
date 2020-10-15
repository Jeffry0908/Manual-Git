/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import libreria.entities.UsuarioEntity;
import libreria.utils.JpaUtil;

/**
 *
 * @author Cinthya Martinez
 */
public class UsuarioModel {
        public List<UsuarioEntity> listarUsuarios() {
            EntityManager em = JpaUtil.getEntityManager();
            try {
                Query consulta = em.createNamedQuery("UsuarioEntity.findAll");
                List<UsuarioEntity> lista = consulta.getResultList();
                em.close();
                return lista;
            }catch (Exception e){
                em.close();
                return null;
            }
        }
        public UsuarioEntity obtenerUsurio(String codUsuario) {
            EntityManager em = JpaUtil.getEntityManager();
            try {
                UsuarioEntity usuario = em.find(UsuarioEntity.class,codUsuario);
                em.close();
                return usuario;
            }catch (Exception e) {
                em.close();
                return null;     
            }
        }
        public int insertarUsuario(UsuarioEntity usuario) {
            EntityManager em = JpaUtil.getEntityManager();
            EntityTransaction tran = em.getTransaction();
            try {
                tran.begin();
                em.persist(usuario);
                tran.commit();
                em.close();
                return 1;
            } catch (Exception e) {
                em.close();
                return 0;
            }
        }
        public int modificarUsuario(UsuarioEntity usuario) {
            EntityManager em = JpaUtil.getEntityManager();
            EntityTransaction tran = em.getTransaction();
            try {
                tran.begin();
                em.merge(usuario);
                tran.commit();
                em.close();
                return 1;
            }catch (Exception e) {
                em.close();
                return 0;
            }
        }
        public int eliminarUsuario(String codUsuario) {
            EntityManager em = JpaUtil.getEntityManager();
            int filasBorradas = 0;
            try {
                UsuarioEntity cate = em.find(UsuarioEntity.class, codUsuario);
                if (cate != null) {
                    EntityTransaction tran = em.getTransaction();
                    tran.begin();
                    em.remove(cate);
                    tran.commit();
                    filasBorradas = 1;
                }
                em.close();
                return filasBorradas;
            }catch (Exception e) {
                em.close();
                return 0;
            }
        }
  
      public int existeCorreo(String correo){
        EntityManager em = JpaUtil.getEntityManager();

           return ((Number)em.createNamedQuery("UsuarioEntity.buscarCorreo").setParameter("correoElectonico", correo).getSingleResult()).intValue();
    }   
    public int total() {
        EntityManager em = JpaUtil.getEntityManager();
        return ((Number)em.createNamedQuery("UsuarioEntity.totalUsuario").getSingleResult()).intValue();
    } 
    
    public int token(String token) {
        EntityManager em = JpaUtil.getEntityManager();
        return ((Number)em.createNamedQuery("UsuarioEntity.token").setParameter("token", token).getSingleResult()).intValue();
    }   
    
    public UsuarioEntity FindByToken(String token){
        try{
            EntityManager em =  JpaUtil.getEntityManager();
            Query query = em.createNamedQuery("UsuarioEntity.findByToken").setParameter("token",token); 
            try{
                UsuarioEntity u = (UsuarioEntity) query.getSingleResult();
                return u;
            }catch(Exception e){
                return new UsuarioEntity();
            }
         }catch(Exception e){
             return null;
         }
    }
    public UsuarioEntity IniciarSesion(String usuario,String contraseña){
        try{
            EntityManager em =  JpaUtil.getEntityManager();
            Query query = em.createNamedQuery("UsuarioEntity.iniciarSesion").setParameter("correoElectonico",usuario).
                                                                       setParameter("contrase\u00f1a",contraseña);           
            List<UsuarioEntity> list = query.getResultList();
            if(list.isEmpty()){
                return new UsuarioEntity();
            }else{
                return list.get(0);
            }
         }catch(Exception e){
             return null;
         }
    }
    public int IniciarSesionD(String usuario,String contraseña) {
        EntityManager em = JpaUtil.getEntityManager();
        return ((Number)em.createNamedQuery("UsuarioEntity.iniciarSesionD").setParameter("correoElectonico",usuario).
                setParameter("contrase\u00f1a",contraseña).getSingleResult()).intValue();
    }     
}
