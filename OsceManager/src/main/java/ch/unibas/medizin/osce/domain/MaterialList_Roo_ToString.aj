// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ch.unibas.medizin.osce.domain;

import java.lang.String;

privileged aspect MaterialList_Roo_ToString {
    
    public String MaterialList.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(getName()).append(", ");
        sb.append("Price: ").append(getPrice()).append(", ");
        sb.append("PriceType: ").append(getPriceType()).append(", ");
        sb.append("Type: ").append(getType()).append(", ");
        sb.append("UsedMaterials: ").append(getUsedMaterials() == null ? "null" : getUsedMaterials().size());
        return sb.toString();
    }
    
}
