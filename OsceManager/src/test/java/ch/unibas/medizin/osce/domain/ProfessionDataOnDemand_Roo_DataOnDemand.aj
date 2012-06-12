// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ch.unibas.medizin.osce.domain;

import ch.unibas.medizin.osce.domain.Profession;
import java.lang.String;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.stereotype.Component;

privileged aspect ProfessionDataOnDemand_Roo_DataOnDemand {
    
    declare @type: ProfessionDataOnDemand: @Component;
    
    private Random ProfessionDataOnDemand.rnd = new SecureRandom();
    
    private List<Profession> ProfessionDataOnDemand.data;
    
    public Profession ProfessionDataOnDemand.getNewTransientProfession(int index) {
        Profession obj = new Profession();
        setProfession(obj, index);
        return obj;
    }
    
    public void ProfessionDataOnDemand.setProfession(Profession obj, int index) {
        String profession = "profession_" + index;
        if (profession.length() > 60) {
            profession = profession.substring(0, 60);
        }
        obj.setProfession(profession);
    }
    
    public Profession ProfessionDataOnDemand.getSpecificProfession(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size() - 1)) index = data.size() - 1;
        Profession obj = data.get(index);
        return Profession.findProfession(obj.getId());
    }
    
    public Profession ProfessionDataOnDemand.getRandomProfession() {
        init();
        Profession obj = data.get(rnd.nextInt(data.size()));
        return Profession.findProfession(obj.getId());
    }
    
    public boolean ProfessionDataOnDemand.modifyProfession(Profession obj) {
        return false;
    }
    
    public void ProfessionDataOnDemand.init() {
        data = Profession.findProfessionEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'Profession' illegally returned null");
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<ch.unibas.medizin.osce.domain.Profession>();
        for (int i = 0; i < 10; i++) {
            Profession obj = getNewTransientProfession(i);
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