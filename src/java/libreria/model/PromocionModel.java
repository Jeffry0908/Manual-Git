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
import libreria.entities.PromocionesEntity;
import libreria.utils.JpaUtil;

/**
 *
 * @author Egbert
 */
public class PromocionModel {
        public List<PromocionesEntity> ListarPromociones() {
            EntityManager em = JpaUtil.getEntityManager();
            try {
                Query consulta = em.createNamedQuery("PromocionesEntity.findAll");
                List<PromocionesEntity> lista = consulta.getResultList();
                em.close();
                return lista;
            }catch (Exception e){
                em.close();
                return null;
            }
        }  
        
        public PromocionesEntity obtenerPromocion(String codCategoria) {
            EntityManager em = JpaUtil.getEntityManager();
            try {
                PromocionesEntity promocion = em.find(PromocionesEntity.class,codCategoria);
                em.close();
                return promocion;
            }catch (Exception e) {
                em.close();
                return null;     
            }
        }
        public int insertarPromocion(PromocionesEntity promocion) {
            EntityManager em = JpaUtil.getEntityManager();
            EntityTransaction tran = em.getTransaction();
            try {
                tran.begin();
                em.persist(promocion);
                tran.commit();
                em.close();
                return 1;
            } catch (Exception e) {
                em.close();
                return 0;
            }
        }
        public int modificarPromocion(PromocionesEntity promociones) {
            EntityManager em = JpaUtil.getEntityManager();
            EntityTransaction tran = em.getTransaction();
            try {
                tran.begin();
                em.merge(promociones);
                tran.commit();
                em.close();
                return 1;
            }catch (Exception e) {
                em.close();
                return 0;
            }
        }
        public int eliminarPromocion(String codpromo) {
            EntityManager em = JpaUtil.getEntityManager();
            int filasBorradas = 0;
            try{
                PromocionesEntity cate = em.find(PromocionesEntity.class, codpromo);
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
        
        
    public int dato(String codigo){
        EntityManager em = JpaUtil.getEntityManager();
        try{
           Query consulta = em.createNamedQuery("OptenerPr"); 
           consulta.setParameter("codPromocion", codigo);
           em.close();
           return 1;
        }catch (Exception e){
            em.close();
            return 0;
        }
    }
      public int obtenernombre(String nombre){
        EntityManager em = JpaUtil.getEntityManager();
        try{
           Query consulta = em.createNamedQuery("OptenerNombre"); 
           consulta.setParameter("nombreCategoria", nombre);
           em.close();
           return 1;
        }catch (Exception e){
            em.close();
            return 0;
        }
    }  
    public int total() {
        EntityManager em = JpaUtil.getEntityManager();
        return ((Number)em.createNamedQuery("UsuarioEntity.totalPromocion").getSingleResult()).intValue();
    }
}

