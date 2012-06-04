// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ch.unibas.medizin.osce.domain;

import ch.unibas.medizin.osce.domain.Scar;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Component;

privileged aspect ScarDataOnDemand_Roo_DataOnDemand {
    
    declare @type: ScarDataOnDemand: @Component;
    
    private Random ScarDataOnDemand.rnd = new java.security.SecureRandom();
    
    private List<Scar> ScarDataOnDemand.data;
    
    public Scar ScarDataOnDemand.getNewTransientScar(int index) {
        ch.unibas.medizin.osce.domain.Scar obj = new ch.unibas.medizin.osce.domain.Scar();
        setBodypart(obj, index);
        setTraitType(obj, index);
        return obj;
    }
    
    private void ScarDataOnDemand.setBodypart(Scar obj, int index) {
        java.lang.String bodypart = "bodypart_" + index;
        if (bodypart.length() > 60) {
            bodypart = bodypart.substring(0, 60);
        }
        obj.setBodypart(bodypart);
    }
    
    private void ScarDataOnDemand.setTraitType(Scar obj, int index) {
        ch.unibas.medizin.osce.shared.TraitTypes traitType = ch.unibas.medizin.osce.shared.TraitTypes.class.getEnumConstants()[0];
        obj.setTraitType(traitType);
    }
    
    public Scar ScarDataOnDemand.getSpecificScar(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size() - 1)) index = data.size() - 1;
        Scar obj = data.get(index);
        return Scar.findScar(obj.getId());
    }
    
    public Scar ScarDataOnDemand.getRandomScar() {
        init();
        Scar obj = data.get(rnd.nextInt(data.size()));
        return Scar.findScar(obj.getId());
    }
    
    public boolean ScarDataOnDemand.modifyScar(Scar obj) {
        return false;
    }
    
    public void ScarDataOnDemand.init() {
        data = ch.unibas.medizin.osce.domain.Scar.findScarEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'Scar' illegally returned null");
        if (!data.isEmpty()) {
            return;
        }
        
        data = new java.util.ArrayList<ch.unibas.medizin.osce.domain.Scar>();
        for (int i = 0; i < 10; i++) {
            ch.unibas.medizin.osce.domain.Scar obj = getNewTransientScar(i);
            obj.persist();
            obj.flush();
            data.add(obj);
        }
    }
    
}
