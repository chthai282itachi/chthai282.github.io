// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ch.unibas.medizin.osce.domain;

import ch.unibas.medizin.osce.domain.StandardizedPatient;
import java.lang.String;
import java.util.Set;

privileged aspect Profession_Roo_JavaBean {
    
    public String Profession.getProfession() {
        return this.profession;
    }
    
    public void Profession.setProfession(String profession) {
        this.profession = profession;
    }
    
    public Set<StandardizedPatient> Profession.getStandardizedpatients() {
        return this.standardizedpatients;
    }
    
    public void Profession.setStandardizedpatients(Set<StandardizedPatient> standardizedpatients) {
        this.standardizedpatients = standardizedpatients;
    }
    
}