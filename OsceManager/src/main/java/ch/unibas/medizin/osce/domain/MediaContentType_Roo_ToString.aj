// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ch.unibas.medizin.osce.domain;

import java.lang.String;

privileged aspect MediaContentType_Roo_ToString {
    
    public String MediaContentType.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Comment: ").append(getComment()).append(", ");
        sb.append("ContentType: ").append(getContentType());
        return sb.toString();
    }
    
}
