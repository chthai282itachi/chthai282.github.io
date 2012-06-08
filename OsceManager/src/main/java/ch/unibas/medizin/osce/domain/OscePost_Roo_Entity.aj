// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ch.unibas.medizin.osce.domain;

import ch.unibas.medizin.osce.domain.OscePost;
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

privileged aspect OscePost_Roo_Entity {
    
    declare @type: OscePost: @Entity;
    
    @PersistenceContext
    transient EntityManager OscePost.entityManager;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long OscePost.id;
    
    @Version
    @Column(name = "version")
    private Integer OscePost.version;
    
    public Long OscePost.getId() {
        return this.id;
    }
    
    public void OscePost.setId(Long id) {
        this.id = id;
    }
    
    public Integer OscePost.getVersion() {
        return this.version;
    }
    
    public void OscePost.setVersion(Integer version) {
        this.version = version;
    }
    
    @Transactional
    public void OscePost.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void OscePost.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            OscePost attached = OscePost.findOscePost(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void OscePost.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void OscePost.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public OscePost OscePost.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        OscePost merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
    public static final EntityManager OscePost.entityManager() {
        EntityManager em = new OscePost().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long OscePost.countOscePosts() {
        return entityManager().createQuery("SELECT COUNT(o) FROM OscePost o", Long.class).getSingleResult();
    }
    
    public static List<OscePost> OscePost.findAllOscePosts() {
        return entityManager().createQuery("SELECT o FROM OscePost o", OscePost.class).getResultList();
    }
    
    public static OscePost OscePost.findOscePost(Long id) {
        if (id == null) return null;
        return entityManager().find(OscePost.class, id);
    }
    
    public static List<OscePost> OscePost.findOscePostEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM OscePost o", OscePost.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
