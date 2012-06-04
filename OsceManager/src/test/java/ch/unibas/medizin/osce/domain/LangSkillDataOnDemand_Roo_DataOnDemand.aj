// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ch.unibas.medizin.osce.domain;

import ch.unibas.medizin.osce.domain.LangSkill;
import ch.unibas.medizin.osce.domain.SpokenLanguageDataOnDemand;
import ch.unibas.medizin.osce.domain.StandardizedPatientDataOnDemand;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

privileged aspect LangSkillDataOnDemand_Roo_DataOnDemand {
    
    declare @type: LangSkillDataOnDemand: @Component;
    
    private Random LangSkillDataOnDemand.rnd = new java.security.SecureRandom();
    
    private List<LangSkill> LangSkillDataOnDemand.data;
    
    @Autowired
    private StandardizedPatientDataOnDemand LangSkillDataOnDemand.standardizedPatientDataOnDemand;
    
    @Autowired
    private SpokenLanguageDataOnDemand LangSkillDataOnDemand.spokenLanguageDataOnDemand;
    
    public LangSkill LangSkillDataOnDemand.getNewTransientLangSkill(int index) {
        ch.unibas.medizin.osce.domain.LangSkill obj = new ch.unibas.medizin.osce.domain.LangSkill();
        setSkill(obj, index);
        setStandardizedpatient(obj, index);
        setSpokenlanguage(obj, index);
        return obj;
    }
    
    private void LangSkillDataOnDemand.setSkill(LangSkill obj, int index) {
        ch.unibas.medizin.osce.shared.LangSkillLevel skill = ch.unibas.medizin.osce.shared.LangSkillLevel.class.getEnumConstants()[0];
        obj.setSkill(skill);
    }
    
    private void LangSkillDataOnDemand.setStandardizedpatient(LangSkill obj, int index) {
        ch.unibas.medizin.osce.domain.StandardizedPatient standardizedpatient = standardizedPatientDataOnDemand.getRandomStandardizedPatient();
        obj.setStandardizedpatient(standardizedpatient);
    }
    
    private void LangSkillDataOnDemand.setSpokenlanguage(LangSkill obj, int index) {
        ch.unibas.medizin.osce.domain.SpokenLanguage spokenlanguage = spokenLanguageDataOnDemand.getRandomSpokenLanguage();
        obj.setSpokenlanguage(spokenlanguage);
    }
    
    public LangSkill LangSkillDataOnDemand.getSpecificLangSkill(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size() - 1)) index = data.size() - 1;
        LangSkill obj = data.get(index);
        return LangSkill.findLangSkill(obj.getId());
    }
    
    public LangSkill LangSkillDataOnDemand.getRandomLangSkill() {
        init();
        LangSkill obj = data.get(rnd.nextInt(data.size()));
        return LangSkill.findLangSkill(obj.getId());
    }
    
    public boolean LangSkillDataOnDemand.modifyLangSkill(LangSkill obj) {
        return false;
    }
    
    public void LangSkillDataOnDemand.init() {
        data = ch.unibas.medizin.osce.domain.LangSkill.findLangSkillEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'LangSkill' illegally returned null");
        if (!data.isEmpty()) {
            return;
        }
        
        data = new java.util.ArrayList<ch.unibas.medizin.osce.domain.LangSkill>();
        for (int i = 0; i < 10; i++) {
            ch.unibas.medizin.osce.domain.LangSkill obj = getNewTransientLangSkill(i);
            obj.persist();
            obj.flush();
            data.add(obj);
        }
    }
    
}
