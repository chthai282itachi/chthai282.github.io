// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ch.unibas.medizin.osce.domain;

import ch.unibas.medizin.osce.domain.OscePost;
import ch.unibas.medizin.osce.domain.RoleTopicDataOnDemand;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

privileged aspect OscePostDataOnDemand_Roo_DataOnDemand {
    
    declare @type: OscePostDataOnDemand: @Component;
    
    private Random OscePostDataOnDemand.rnd = new java.security.SecureRandom();
    
    private List<OscePost> OscePostDataOnDemand.data;
    
    @Autowired
    private RoleTopicDataOnDemand OscePostDataOnDemand.roleTopicDataOnDemand;
    
    public OscePost OscePostDataOnDemand.getNewTransientOscePost(int index) {
        ch.unibas.medizin.osce.domain.OscePost obj = new ch.unibas.medizin.osce.domain.OscePost();
        setIsPossibleStart(obj, index);
        setRoleTopic(obj, index);
        setNextPost(obj, index);
        return obj;
    }
    
    private void OscePostDataOnDemand.setIsPossibleStart(OscePost obj, int index) {
        java.lang.Boolean isPossibleStart = Boolean.TRUE;
        obj.setIsPossibleStart(isPossibleStart);
    }
    
    private void OscePostDataOnDemand.setRoleTopic(OscePost obj, int index) {
        ch.unibas.medizin.osce.domain.RoleTopic roleTopic = roleTopicDataOnDemand.getRandomRoleTopic();
        obj.setRoleTopic(roleTopic);
    }
    
    private void OscePostDataOnDemand.setNextPost(OscePost obj, int index) {
        ch.unibas.medizin.osce.domain.OscePost nextPost = obj;
        obj.setNextPost(nextPost);
    }
    
    public OscePost OscePostDataOnDemand.getSpecificOscePost(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size() - 1)) index = data.size() - 1;
        OscePost obj = data.get(index);
        return OscePost.findOscePost(obj.getId());
    }
    
    public OscePost OscePostDataOnDemand.getRandomOscePost() {
        init();
        OscePost obj = data.get(rnd.nextInt(data.size()));
        return OscePost.findOscePost(obj.getId());
    }
    
    public boolean OscePostDataOnDemand.modifyOscePost(OscePost obj) {
        return false;
    }
    
    public void OscePostDataOnDemand.init() {
        data = ch.unibas.medizin.osce.domain.OscePost.findOscePostEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'OscePost' illegally returned null");
        if (!data.isEmpty()) {
            return;
        }
        
        data = new java.util.ArrayList<ch.unibas.medizin.osce.domain.OscePost>();
        for (int i = 0; i < 10; i++) {
            ch.unibas.medizin.osce.domain.OscePost obj = getNewTransientOscePost(i);
            obj.persist();
            obj.flush();
            data.add(obj);
        }
    }
    
}