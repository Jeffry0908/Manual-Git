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
import libreria.entities.CategoriaEntity;
import libreria.utils.JpaUtil;

/**
 *
 * @author Egbert
 */
public class CategoriaModel {
        public List<CategoriaEntity> listarCategorias() {
            EntityManager em = JpaUtil.getEntityManager();
            try {
                Query consulta = em.createNamedQuery("CategoriaEntity.findAll");
                List<CategoriaEntity> lista = consulta.getResultList();
                em.close();
                return lista;
            }catch (Exception e){
                em.close();
                return null;
            }
        }
        public CategoriaEntity obtenerCategoria(int codCategoria) {
            EntityManager em = JpaUtil.getEntityManager();
            try {
                CategoriaEntity categoria = em.find(CategoriaEntity.class,codCategoria);
                em.close();
                return categoria;
            }catch (Exception e) {
                em.close();
                return null;     
            }
        }
        public int insertarCategoria(CategoriaEntity categoria) {
            EntityManager em = JpaUtil.getEntityManager();
            EntityTransaction tran = em.getTransaction();
            try {
                tran.begin();
                em.persist(categoria);
                tran.commit();
                em.close();
                return 1;
            } catch (Exception e) {
                em.close();
                return 0;
            }
        }
        public int modificarCategoria(CategoriaEntity categoria) {
            EntityManager em = JpaUtil.getEntityManager();
            EntityTransaction tran = em.getTransaction();
            try {
                tran.begin();
                em.merge(categoria);
                tran.commit();
                em.close();
                return 1;
            }catch (Exception e) {
                em.close();
                return 0;
            }
        }
        public int eliminarCategoria(int codCategoria) {
            EntityManager em = JpaUtil.getEntityManager();
            int filasBorradas = 0;
            try {
                CategoriaEntity cate = em.find(CategoriaEntity.class, codCategoria);
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
    public int dato(int codigo){
        EntityManager em = JpaUtil.getEntityManager();
        try{
           Query consulta = em.createNamedQuery("Optener"); 
           consulta.setParameter("codCategoria", codigo);
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
}

