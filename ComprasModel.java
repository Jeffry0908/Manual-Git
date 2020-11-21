/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.model;

import libreria.utils.JpaUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import libreria.entities.CompraEntity;
import libreria.entities.DetallecompraEntity;

public class ComprasModel {
    
    public List<CompraEntity> listarCompra() {
        //Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query consulta = em.createNamedQuery("CompraEntity.findAll");
            //El método getResultList() de la clase Query permite obtener
            // la lista de resultados de una consulta de selección
            List<CompraEntity> lista = consulta.getResultList();
            em.close();// Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    public CompraEntity obtenerCompra(String codigo) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            //Recupero el objeto desde la BD a través del método find
            CompraEntity compra = em.find(CompraEntity.class, codigo);
            em.close();
            return compra;
        } catch (Exception e) {
            em.close();
            return null;
        }
     }

    public int insertarCompra(CompraEntity compra) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();//Iniciando transacción
            em.persist(compra); //Guardando el objeto en la BD
            tran.commit();//Confirmando la transacción
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
        return 0;
        }
    }
    
    public int insertarDetalleCompra(DetallecompraEntity detalle) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();//Iniciando transacción
            em.persist(detalle); //Guardando el objeto en la BD
            tran.commit();//Confirmando la transacción
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
        return 0;
        }
    }    

    public int modificarCompra(CompraEntity compra) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();
            em.merge(compra);
            tran.commit();
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }

    public int eliminarCompra(String codigo) {
        EntityManager em = JpaUtil.getEntityManager();
        int filasBorradas = 0;
        try {
            //Recuperando el objeto a eliminar
            CompraEntity com = em.find(CompraEntity.class, codigo);
            if (com != null) {
                EntityTransaction tran = em.getTransaction();
                tran.begin();
                em.remove(com);
                tran.commit();
                filasBorradas = 1;
            }
            em.close();
            return filasBorradas;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }
    public int total() {
        EntityManager em = JpaUtil.getEntityManager();
        return ((Number)em.createNamedQuery("CompraEntity.totaldatos").getSingleResult()).intValue();
    }
public List<DetallecompraEntity> listarporCod(String cod) {
    //Obtengo una instancia de EntityManager
    EntityManager em = JpaUtil.getEntityManager();
    try {
        Query consulta = em.createNamedQuery("DetallecompraEntity.buscarCom").setParameter("codCompra", cod);
        //El método getResultList() de la clase Query permite obtener
        // la lista de resultados de una consulta de selección
        
        List<DetallecompraEntity> lista = consulta.getResultList();

        em.close();// Cerrando el EntityManager
        return lista;
    } catch (Exception e) {
        em.close();
    return null;
    }
}    
}