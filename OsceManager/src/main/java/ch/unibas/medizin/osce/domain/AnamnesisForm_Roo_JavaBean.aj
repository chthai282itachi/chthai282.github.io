// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ch.unibas.medizin.osce.domain;

import ch.unibas.medizin.osce.domain.AnamnesisChecksValue;
import ch.unibas.medizin.osce.domain.Scar;
import java.util.Date;
import java.util.Set;

privileged aspect AnamnesisForm_Roo_JavaBean {
    
    public Date AnamnesisForm.getCreateDate() {
        return this.createDate;
    }
    
    public void AnamnesisForm.setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
    public Set<AnamnesisChecksValue> AnamnesisForm.getAnamnesischecksvalues() {
        return this.anamnesischecksvalues;
    }
    
    public void AnamnesisForm.setAnamnesischecksvalues(Set<AnamnesisChecksValue> anamnesischecksvalues) {
        this.anamnesischecksvalues = anamnesischecksvalues;
    }
    
    public java.util.Set<Scar> AnamnesisForm.getScars() {
        return this.scars;
    }
    
    public void AnamnesisForm.setScars(java.util.Set<Scar> scars) {
        this.scars = scars;
    }
    
}