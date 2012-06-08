// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ch.unibas.medizin.osce.domain;

import ch.unibas.medizin.osce.domain.AnamnesisForm;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.stereotype.Component;

privileged aspect AnamnesisFormDataOnDemand_Roo_DataOnDemand {
    
    declare @type: AnamnesisFormDataOnDemand: @Component;
    
    private Random AnamnesisFormDataOnDemand.rnd = new SecureRandom();
    
    private List<AnamnesisForm> AnamnesisFormDataOnDemand.data;
    
    public AnamnesisForm AnamnesisFormDataOnDemand.getNewTransientAnamnesisForm(int index) {
        AnamnesisForm obj = new AnamnesisForm();
        setCreateDate(obj, index);
        return obj;
    }
    
    public void AnamnesisFormDataOnDemand.setCreateDate(AnamnesisForm obj, int index) {
        Date createDate = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setCreateDate(createDate);
    }
    
    public AnamnesisForm AnamnesisFormDataOnDemand.getSpecificAnamnesisForm(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size() - 1)) index = data.size() - 1;
        AnamnesisForm obj = data.get(index);
        return AnamnesisForm.findAnamnesisForm(obj.getId());
    }
    
    public AnamnesisForm AnamnesisFormDataOnDemand.getRandomAnamnesisForm() {
        init();
        AnamnesisForm obj = data.get(rnd.nextInt(data.size()));
        return AnamnesisForm.findAnamnesisForm(obj.getId());
    }
    
    public boolean AnamnesisFormDataOnDemand.modifyAnamnesisForm(AnamnesisForm obj) {
        return false;
    }
    
    public void AnamnesisFormDataOnDemand.init() {
        data = AnamnesisForm.findAnamnesisFormEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'AnamnesisForm' illegally returned null");
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<ch.unibas.medizin.osce.domain.AnamnesisForm>();
        for (int i = 0; i < 10; i++) {
            AnamnesisForm obj = getNewTransientAnamnesisForm(i);
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
