// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ch.unibas.medizin.osce.domain;

import ch.unibas.medizin.osce.domain.EliminationCriterion;
import java.lang.Integer;
import java.lang.Long;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Version;
import org.springframework.transaction.annotation.Transactional;

privileged aspect EliminationCriterion_Roo_Entity {
    
    declare @type: EliminationCriterion: @Entity;
    
    @PersistenceContext
    transient EntityManager EliminationCriterion.entityManager;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long EliminationCriterion.id;
    
    @Version
    @Column(name = "version")
    private Integer EliminationCriterion.version;
    
    public Long EliminationCriterion.getId() {
        return this.id;
    }
    
    public void EliminationCriterion.setId(Long id) {
        this.id = id;
    }
    
    public Integer EliminationCriterion.getVersion() {
        return this.version;
    }
    
    public void EliminationCriterion.setVersion(Integer version) {
        this.version = version;
    }
    
    @Transactional
    public void EliminationCriterion.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void EliminationCriterion.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            EliminationCriterion attached = EliminationCriterion.findEliminationCriterion(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void EliminationCriterion.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void EliminationCriterion.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public EliminationCriterion EliminationCriterion.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        EliminationCriterion merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
    public static final EntityManager EliminationCriterion.entityManager() {
        EntityManager em = new EliminationCriterion().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long EliminationCriterion.countEliminationCriterions() {
        return entityManager().createQuery("SELECT COUNT(o) FROM EliminationCriterion o", Long.class).getSingleResult();
    }
    
    public static List<EliminationCriterion> EliminationCriterion.findAllEliminationCriterions() {
        return entityManager().createQuery("SELECT o FROM EliminationCriterion o", EliminationCriterion.class).getResultList();
    }
    
    public static EliminationCriterion EliminationCriterion.findEliminationCriterion(Long id) {
        if (id == null) return null;
        return entityManager().find(EliminationCriterion.class, id);
    }
    
    public static List<EliminationCriterion> EliminationCriterion.findEliminationCriterionEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM EliminationCriterion o", EliminationCriterion.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}