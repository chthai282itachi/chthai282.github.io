// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ch.unibas.medizin.osce.domain;

import ch.unibas.medizin.osce.domain.ChecklistTopic;
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

privileged aspect ChecklistTopic_Roo_Entity {
    
    declare @type: ChecklistTopic: @Entity;
    
    @PersistenceContext
    transient EntityManager ChecklistTopic.entityManager;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long ChecklistTopic.id;
    
    @Version
    @Column(name = "version")
    private Integer ChecklistTopic.version;
    
    public Long ChecklistTopic.getId() {
        return this.id;
    }
    
    public void ChecklistTopic.setId(Long id) {
        this.id = id;
    }
    
    public Integer ChecklistTopic.getVersion() {
        return this.version;
    }
    
    public void ChecklistTopic.setVersion(Integer version) {
        this.version = version;
    }
    
    @Transactional
    public void ChecklistTopic.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void ChecklistTopic.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            ChecklistTopic attached = ChecklistTopic.findChecklistTopic(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void ChecklistTopic.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void ChecklistTopic.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public ChecklistTopic ChecklistTopic.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        ChecklistTopic merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
    public static final EntityManager ChecklistTopic.entityManager() {
        EntityManager em = new ChecklistTopic().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long ChecklistTopic.countChecklistTopics() {
        return entityManager().createQuery("SELECT COUNT(o) FROM ChecklistTopic o", Long.class).getSingleResult();
    }
    
    public static List<ChecklistTopic> ChecklistTopic.findAllChecklistTopics() {
        return entityManager().createQuery("SELECT o FROM ChecklistTopic o", ChecklistTopic.class).getResultList();
    }
    
    public static ChecklistTopic ChecklistTopic.findChecklistTopic(Long id) {
        if (id == null) return null;
        return entityManager().find(ChecklistTopic.class, id);
    }
    
    public static List<ChecklistTopic> ChecklistTopic.findChecklistTopicEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM ChecklistTopic o", ChecklistTopic.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}