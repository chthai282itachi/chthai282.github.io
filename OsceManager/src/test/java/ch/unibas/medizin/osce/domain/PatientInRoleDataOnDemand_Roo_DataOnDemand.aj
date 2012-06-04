// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ch.unibas.medizin.osce.domain;

import ch.unibas.medizin.osce.domain.PatientInRole;
import ch.unibas.medizin.osce.domain.PatientInSemesterDataOnDemand;
import ch.unibas.medizin.osce.domain.StandardizedRoleDataOnDemand;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

privileged aspect PatientInRoleDataOnDemand_Roo_DataOnDemand {
    
    declare @type: PatientInRoleDataOnDemand: @Component;
    
    private Random PatientInRoleDataOnDemand.rnd = new java.security.SecureRandom();
    
    private List<PatientInRole> PatientInRoleDataOnDemand.data;
    
    @Autowired
    private PatientInSemesterDataOnDemand PatientInRoleDataOnDemand.patientInSemesterDataOnDemand;
    
    @Autowired
    private StandardizedRoleDataOnDemand PatientInRoleDataOnDemand.standardizedRoleDataOnDemand;
    
    public PatientInRole PatientInRoleDataOnDemand.getNewTransientPatientInRole(int index) {
        ch.unibas.medizin.osce.domain.PatientInRole obj = new ch.unibas.medizin.osce.domain.PatientInRole();
        setPatientInSemester(obj, index);
        setStandardizedRole(obj, index);
        return obj;
    }
    
    private void PatientInRoleDataOnDemand.setPatientInSemester(PatientInRole obj, int index) {
        ch.unibas.medizin.osce.domain.PatientInSemester patientInSemester = patientInSemesterDataOnDemand.getRandomPatientInSemester();
        obj.setPatientInSemester(patientInSemester);
    }
    
    private void PatientInRoleDataOnDemand.setStandardizedRole(PatientInRole obj, int index) {
        ch.unibas.medizin.osce.domain.StandardizedRole standardizedRole = standardizedRoleDataOnDemand.getRandomStandardizedRole();
        obj.setStandardizedRole(standardizedRole);
    }
    
    public PatientInRole PatientInRoleDataOnDemand.getSpecificPatientInRole(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size() - 1)) index = data.size() - 1;
        PatientInRole obj = data.get(index);
        return PatientInRole.findPatientInRole(obj.getId());
    }
    
    public PatientInRole PatientInRoleDataOnDemand.getRandomPatientInRole() {
        init();
        PatientInRole obj = data.get(rnd.nextInt(data.size()));
        return PatientInRole.findPatientInRole(obj.getId());
    }
    
    public boolean PatientInRoleDataOnDemand.modifyPatientInRole(PatientInRole obj) {
        return false;
    }
    
    public void PatientInRoleDataOnDemand.init() {
        data = ch.unibas.medizin.osce.domain.PatientInRole.findPatientInRoleEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'PatientInRole' illegally returned null");
        if (!data.isEmpty()) {
            return;
        }
        
        data = new java.util.ArrayList<ch.unibas.medizin.osce.domain.PatientInRole>();
        for (int i = 0; i < 10; i++) {
            ch.unibas.medizin.osce.domain.PatientInRole obj = getNewTransientPatientInRole(i);
            obj.persist();
            obj.flush();
            data.add(obj);
        }
    }
    
}
