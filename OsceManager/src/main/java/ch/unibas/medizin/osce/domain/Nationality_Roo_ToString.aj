// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ch.unibas.medizin.osce.domain;

import java.lang.String;

privileged aspect Nationality_Roo_ToString {
    
    public String Nationality.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nationality: ").append(getNationality()).append(", ");
        sb.append("Standardizedpatients: ").append(getStandardizedpatients() == null ? "null" : getStandardizedpatients().size());
        return sb.toString();
    }
    
}
