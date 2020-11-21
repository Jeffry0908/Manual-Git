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
import libreria.entities.DetalleventaEntity;
import libreria.entities.ProductoEntity;
import libreria.entities.UsuarioEntity;
import libreria.entities.VentaEntity;
import libreria.utils.JpaUtil;


/**
 *
 * @author Cinthya Martinez
 */
public class VentaModel {
    public int insertarDetalleVenta(DetalleventaEntity detalle) {
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
    
    public int total() {
        EntityManager em = JpaUtil.getEntityManager();
        return ((Number)em.createNamedQuery("VentaEntity.totaldato").getSingleResult()).intValue();
    } 
    
    public int insertarVenta(VentaEntity venta) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();//Iniciando transacción
            em.persist(venta); //Guardando el objeto en la BD
            tran.commit();//Confirmando la transacción
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
        return 0;
        }
    } 
public List<DetalleventaEntity> listarporCod(String cod) {
    //Obtengo una instancia de EntityManager
    EntityManager em = JpaUtil.getEntityManager();
    try {
        Query consulta = em.createNamedQuery("DetalleventaEntity.buscarVen").setParameter("codVenta", cod);
        //El método getResultList() de la clase Query permite obtener
        // la lista de resultados de una consulta de selección
        
        List<DetalleventaEntity> lista = consulta.getResultList();

        em.close();// Cerrando el EntityManager
        return lista;
    } catch (Exception e) {
        em.close();
    return null;
    }
}  
    public VentaEntity monto(String codigo) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            //Recupero el objeto desde la BD a través del método find
            VentaEntity venta = em.find(VentaEntity.class, codigo);
            em.close();
            return venta;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }
     public int codigo(String cod) {
        EntityManager em = JpaUtil.getEntityManager();
        return ((Number)em.createNamedQuery("VentaEntity.optenerporcod").setParameter("codVenta", cod).getSingleResult()).intValue();
    }
    public int modificarVenta(VentaEntity venta) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();//Iniciando transacción
            em.merge(venta); //Actualizando el objeto en la BD
            tran.commit();//Confirmando la transacción
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }
public List<VentaEntity> ventaUsuario(UsuarioEntity cod) {
    //Obtengo una instancia de EntityManager
    EntityManager em = JpaUtil.getEntityManager();
    try {
        Query consulta = em.createNamedQuery("VentaEntity.obtenerVenta").setParameter("codUsuario", cod);
        //El método getResultList() de la clase Query permite obtener
        // la lista de resultados de una consulta de selección
        
        List<VentaEntity> lista = consulta.getResultList();

        em.close();// Cerrando el EntityManager
        return lista;
    } catch (Exception e) {
        em.close();
    return null;
    }
}  
        public List<VentaEntity> listarVentas() {
            EntityManager em = JpaUtil.getEntityManager();
            try {
                Query consulta = em.createNamedQuery("VentaEntity.findAll");
                List<VentaEntity> lista = consulta.getResultList();
                em.close();
                return lista;
            }catch (Exception e){
                em.close();
                return null;
            }
        }
        
}
