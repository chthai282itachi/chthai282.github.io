// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ch.unibas.medizin.osce.domain;

import ch.unibas.medizin.osce.domain.Nationality;
import java.lang.String;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.stereotype.Component;

privileged aspect NationalityDataOnDemand_Roo_DataOnDemand {
    
    declare @type: NationalityDataOnDemand: @Component;
    
    private Random NationalityDataOnDemand.rnd = new SecureRandom();
    
    private List<Nationality> NationalityDataOnDemand.data;
    
    public Nationality NationalityDataOnDemand.getNewTransientNationality(int index) {
        Nationality obj = new Nationality();
        setNationality(obj, index);
        return obj;
    }
    
    public void NationalityDataOnDemand.setNationality(Nationality obj, int index) {
        String nationality = "nationality_" + index;
        if (nationality.length() > 40) {
            nationality = nationality.substring(0, 40);
        }
        obj.setNationality(nationality);
    }
    
    public Nationality NationalityDataOnDemand.getSpecificNationality(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size() - 1)) index = data.size() - 1;
        Nationality obj = data.get(index);
        return Nationality.findNationality(obj.getId());
    }
    
    public Nationality NationalityDataOnDemand.getRandomNationality() {
        init();
        Nationality obj = data.get(rnd.nextInt(data.size()));
        return Nationality.findNationality(obj.getId());
    }
    
    public boolean NationalityDataOnDemand.modifyNationality(Nationality obj) {
        return false;
    }
    
    public void NationalityDataOnDemand.init() {
        data = Nationality.findNationalityEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'Nationality' illegally returned null");
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<ch.unibas.medizin.osce.domain.Nationality>();
        for (int i = 0; i < 10; i++) {
            Nationality obj = getNewTransientNationality(i);
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