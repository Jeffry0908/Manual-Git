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
import libreria.entities.CategoriaEntity;
import libreria.entities.ProductoEntity;

public class ProductoModel {

    public List<ProductoEntity> listarProducto() {
//Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query consulta = em.createNamedQuery("ProductoEntity.findAll");
            //El método getResultList() de la clase Query permite obtener
            // la lista de resultados de una consulta de selección
            List<ProductoEntity> lista = consulta.getResultList();
            em.close();// Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    public ProductoEntity obtenerProducto(String codigo) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
//Recupero el objeto desde la BD a través del método find
            ProductoEntity producto = em.find(ProductoEntity.class, codigo);
            em.close();
            return producto;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    public int insertarProducto(ProductoEntity producto) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();//Iniciando transacción
            em.persist(producto); //Guardando el objeto en la BD
            tran.commit();//Confirmando la transacción
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }

    public int modificarProducto(ProductoEntity producto) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();//Iniciando transacción
            em.merge(producto); //Actualizando el objeto en la BD
            tran.commit();//Confirmando la transacción
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }

    public int eliminarProducto(String codigo) {
        EntityManager em = JpaUtil.getEntityManager();
        int filasBorradas = 0;
        try {
//Recuperando el objeto a eliminar
            ProductoEntity pro = em.find(ProductoEntity.class, codigo);
            if (pro != null) {
                EntityTransaction tran = em.getTransaction();
                tran.begin();//Iniciando transacción
                em.remove(pro);//Borrando la instancia
                tran.commit();//Confirmando la transacción
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
        return ((Number) em.createNamedQuery("ProductoEntity.totalProduc").getSingleResult()).intValue();
    }

    public int nombre(String nombre) {
        EntityManager em = JpaUtil.getEntityManager();
        return ((Number) em.createNamedQuery("ProductoEntity.OptenerNombre").setParameter("nombreProduct", nombre).getSingleResult()).intValue();
    }
}
