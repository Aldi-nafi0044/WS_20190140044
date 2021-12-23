/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web.service.tugas_01;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import web.service.tugas_01.exceptions.NonexistentEntityException;
import web.service.tugas_01.exceptions.PreexistingEntityException;

/**
 *
 * @author Nafi'
 */
public class DataMahasiswaJpaController implements Serializable {

    public DataMahasiswaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("web.service_tugas_01_jar_0.0.1-SNAPSHOTPU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public DataMahasiswaJpaController() {
    }

    public void create(DataMahasiswa dataMahasiswa) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(dataMahasiswa);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDataMahasiswa(dataMahasiswa.getNim()) != null) {
                throw new PreexistingEntityException("DataMahasiswa " + dataMahasiswa + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DataMahasiswa dataMahasiswa) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            dataMahasiswa = em.merge(dataMahasiswa);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = dataMahasiswa.getNim();
                if (findDataMahasiswa(id) == null) {
                    throw new NonexistentEntityException("The dataMahasiswa with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DataMahasiswa dataMahasiswa;
            try {
                dataMahasiswa = em.getReference(DataMahasiswa.class, id);
                dataMahasiswa.getNim();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The dataMahasiswa with id " + id + " no longer exists.", enfe);
            }
            em.remove(dataMahasiswa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DataMahasiswa> findDataMahasiswaEntities() {
        return findDataMahasiswaEntities(true, -1, -1);
    }

    public List<DataMahasiswa> findDataMahasiswaEntities(int maxResults, int firstResult) {
        return findDataMahasiswaEntities(false, maxResults, firstResult);
    }

    private List<DataMahasiswa> findDataMahasiswaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DataMahasiswa.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public DataMahasiswa findDataMahasiswa(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DataMahasiswa.class, id);
        } finally {
            em.close();
        }
    }

    public int getDataMahasiswaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DataMahasiswa> rt = cq.from(DataMahasiswa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
