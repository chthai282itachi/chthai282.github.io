// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ch.unibas.medizin.osce.domain;

import ch.unibas.medizin.osce.domain.Keyword;
import java.lang.String;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.stereotype.Component;

privileged aspect KeywordDataOnDemand_Roo_DataOnDemand {
    
    declare @type: KeywordDataOnDemand: @Component;
    
    private Random KeywordDataOnDemand.rnd = new SecureRandom();
    
    private List<Keyword> KeywordDataOnDemand.data;
    
    public Keyword KeywordDataOnDemand.getNewTransientKeyword(int index) {
        Keyword obj = new Keyword();
        setName(obj, index);
        return obj;
    }
    
    public void KeywordDataOnDemand.setName(Keyword obj, int index) {
        String name = "name_" + index;
        if (name.length() > 255) {
            name = name.substring(0, 255);
        }
        obj.setName(name);
    }
    
    public Keyword KeywordDataOnDemand.getSpecificKeyword(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size() - 1)) index = data.size() - 1;
        Keyword obj = data.get(index);
        return Keyword.findKeyword(obj.getId());
    }
    
    public Keyword KeywordDataOnDemand.getRandomKeyword() {
        init();
        Keyword obj = data.get(rnd.nextInt(data.size()));
        return Keyword.findKeyword(obj.getId());
    }
    
    public boolean KeywordDataOnDemand.modifyKeyword(Keyword obj) {
        return false;
    }
    
    public void KeywordDataOnDemand.init() {
        data = Keyword.findKeywordEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'Keyword' illegally returned null");
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<ch.unibas.medizin.osce.domain.Keyword>();
        for (int i = 0; i < 10; i++) {
            Keyword obj = getNewTransientKeyword(i);
            try {
                obj.persist();
            } catch (ConstraintViolationException e) {
                StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> it = e.getConstraintViolations().iterator(); it.hasNext();) {
                    ConstraintViolation<?> cv = it.next();
                    msg.append("[").append(cv.getConstraintDescriptor()).append(":").append(cv.getMessage()).append("=").append(cv.getInvalidValue()).append("]");
                }
                throw new RuntimeException(msg.toString(), e);
            }
            obj.flush();
            data.add(obj);
        }
    }
    
}
