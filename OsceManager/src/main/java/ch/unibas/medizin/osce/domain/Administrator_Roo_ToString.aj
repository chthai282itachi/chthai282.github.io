// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ch.unibas.medizin.osce.domain;

import java.lang.String;

privileged aspect Administrator_Roo_ToString {
    
    public String Administrator.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Email: ").append(getEmail()).append(", ");
        sb.append("Name: ").append(getName()).append(", ");
        sb.append("PreName: ").append(getPreName()).append(", ");
        sb.append("Semesters: ").append(getSemesters() == null ? "null" : getSemesters().size()).append(", ");
        sb.append("Tasks: ").append(getTasks() == null ? "null" : getTasks().size());
        return sb.toString();
    }
    
}
