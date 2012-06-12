// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ch.unibas.medizin.osce.domain;

import ch.unibas.medizin.osce.domain.OscePostRoom;
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

privileged aspect OscePostRoom_Roo_Entity {
    
    declare @type: OscePostRoom: @Entity;
    
    @PersistenceContext
    transient EntityManager OscePostRoom.entityManager;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long OscePostRoom.id;
    
    @Version
    @Column(name = "version")
    private Integer OscePostRoom.version;
    
    public Long OscePostRoom.getId() {
        return this.id;
    }
    
    public void OscePostRoom.setId(Long id) {
        this.id = id;
    }
    
    public Integer OscePostRoom.getVersion() {
        return this.version;
    }
    
    public void OscePostRoom.setVersion(Integer version) {
        this.version = version;
    }
    
    @Transactional
    public void OscePostRoom.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void OscePostRoom.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            OscePostRoom attached = OscePostRoom.findOscePostRoom(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void OscePostRoom.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void OscePostRoom.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public OscePostRoom OscePostRoom.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        OscePostRoom merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
    public static final EntityManager OscePostRoom.entityManager() {
        EntityManager em = new OscePostRoom().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long OscePostRoom.countOscePostRooms() {
        return entityManager().createQuery("SELECT COUNT(o) FROM OscePostRoom o", Long.class).getSingleResult();
    }
    
    public static List<OscePostRoom> OscePostRoom.findAllOscePostRooms() {
        return entityManager().createQuery("SELECT o FROM OscePostRoom o", OscePostRoom.class).getResultList();
    }
    
    public static OscePostRoom OscePostRoom.findOscePostRoom(Long id) {
        if (id == null) return null;
        return entityManager().find(OscePostRoom.class, id);
    }
    
    public static List<OscePostRoom> OscePostRoom.findOscePostRoomEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM OscePostRoom o", OscePostRoom.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}