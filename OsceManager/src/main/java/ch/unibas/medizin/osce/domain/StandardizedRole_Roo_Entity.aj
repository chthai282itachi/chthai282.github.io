// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ch.unibas.medizin.osce.domain;

import ch.unibas.medizin.osce.domain.StandardizedRole;
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

privileged aspect StandardizedRole_Roo_Entity {
    
    declare @type: StandardizedRole: @Entity;
    
    @PersistenceContext
    transient EntityManager StandardizedRole.entityManager;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long StandardizedRole.id;
    
    @Version
    @Column(name = "version")
    private Integer StandardizedRole.version;
    
    public Long StandardizedRole.getId() {
        return this.id;
    }
    
    public void StandardizedRole.setId(Long id) {
        this.id = id;
    }
    
    public Integer StandardizedRole.getVersion() {
        return this.version;
    }
    
    public void StandardizedRole.setVersion(Integer version) {
        this.version = version;
    }
    
    @Transactional
    public void StandardizedRole.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void StandardizedRole.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            StandardizedRole attached = StandardizedRole.findStandardizedRole(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void StandardizedRole.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void StandardizedRole.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public StandardizedRole StandardizedRole.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        StandardizedRole merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
    public static final EntityManager StandardizedRole.entityManager() {
        EntityManager em = new StandardizedRole().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long StandardizedRole.countStandardizedRoles() {
        return entityManager().createQuery("SELECT COUNT(o) FROM StandardizedRole o", Long.class).getSingleResult();
    }
    
    public static List<StandardizedRole> StandardizedRole.findAllStandardizedRoles() {
        return entityManager().createQuery("SELECT o FROM StandardizedRole o", StandardizedRole.class).getResultList();
    }
    
    public static StandardizedRole StandardizedRole.findStandardizedRole(Long id) {
        if (id == null) return null;
        return entityManager().find(StandardizedRole.class, id);
    }
    
    public static List<StandardizedRole> StandardizedRole.findStandardizedRoleEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM StandardizedRole o", StandardizedRole.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}