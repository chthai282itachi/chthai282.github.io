// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ch.unibas.medizin.osce.domain;

import ch.unibas.medizin.osce.domain.AnamnesisCheck;
import ch.unibas.medizin.osce.domain.AnamnesisForm;
import java.lang.Boolean;
import java.lang.String;

privileged aspect AnamnesisChecksValue_Roo_JavaBean {
    
    public Boolean AnamnesisChecksValue.getTruth() {
        return this.truth;
    }
    
    public void AnamnesisChecksValue.setTruth(Boolean truth) {
        this.truth = truth;
    }
    
    public String AnamnesisChecksValue.getComment() {
        return this.comment;
    }
    
    public void AnamnesisChecksValue.setComment(String comment) {
        this.comment = comment;
    }
    
    public String AnamnesisChecksValue.getAnamnesisChecksValue() {
        return this.anamnesisChecksValue;
    }
    
    public void AnamnesisChecksValue.setAnamnesisChecksValue(String anamnesisChecksValue) {
        this.anamnesisChecksValue = anamnesisChecksValue;
    }
    
    public AnamnesisForm AnamnesisChecksValue.getAnamnesisform() {
        return this.anamnesisform;
    }
    
    public void AnamnesisChecksValue.setAnamnesisform(AnamnesisForm anamnesisform) {
        this.anamnesisform = anamnesisform;
    }
    
    public AnamnesisCheck AnamnesisChecksValue.getAnamnesischeck() {
        return this.anamnesischeck;
    }
    
    public void AnamnesisChecksValue.setAnamnesischeck(AnamnesisCheck anamnesischeck) {
        this.anamnesischeck = anamnesischeck;
    }
    
}
