// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ch.unibas.medizin.osce.domain;

import ch.unibas.medizin.osce.domain.SpokenLanguage;
import ch.unibas.medizin.osce.domain.StandardizedPatient;
import ch.unibas.medizin.osce.shared.LangSkillLevel;

privileged aspect LangSkill_Roo_JavaBean {
    
    public LangSkillLevel LangSkill.getSkill() {
        return this.skill;
    }
    
    public void LangSkill.setSkill(LangSkillLevel skill) {
        this.skill = skill;
    }
    
    public StandardizedPatient LangSkill.getStandardizedpatient() {
        return this.standardizedpatient;
    }
    
    public void LangSkill.setStandardizedpatient(StandardizedPatient standardizedpatient) {
        this.standardizedpatient = standardizedpatient;
    }
    
    public SpokenLanguage LangSkill.getSpokenlanguage() {
        return this.spokenlanguage;
    }
    
    public void LangSkill.setSpokenlanguage(SpokenLanguage spokenlanguage) {
        this.spokenlanguage = spokenlanguage;
    }
    
}