// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ch.unibas.medizin.osce.domain;

import java.lang.String;

privileged aspect RoleItemAccess_Roo_ToString {
    
    public String RoleItemAccess.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(getName()).append(", ");
        sb.append("RoleBaseItem: ").append(getRoleBaseItem() == null ? "null" : getRoleBaseItem().size());
        return sb.toString();
    }
    
}
