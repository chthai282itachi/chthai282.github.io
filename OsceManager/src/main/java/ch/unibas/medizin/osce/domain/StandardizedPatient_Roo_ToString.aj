// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ch.unibas.medizin.osce.domain;

import java.lang.String;

privileged aspect StandardizedPatient_Roo_ToString {
    
    public String StandardizedPatient.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AnamnesisForm: ").append(getAnamnesisForm()).append(", ");
        sb.append("BankAccount: ").append(getBankAccount()).append(", ");
        sb.append("Birthday: ").append(getBirthday()).append(", ");
        sb.append("City: ").append(getCity()).append(", ");
        sb.append("Descriptions: ").append(getDescriptions()).append(", ");
        sb.append("Email: ").append(getEmail()).append(", ");
        sb.append("Gender: ").append(getGender()).append(", ");
        sb.append("Height: ").append(getHeight()).append(", ");
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("ImmagePath: ").append(getImmagePath()).append(", ");
        sb.append("Langskills: ").append(getLangskills() == null ? "null" : getLangskills().size()).append(", ");
        sb.append("MaritalStatus: ").append(getMaritalStatus()).append(", ");
        sb.append("Mobile: ").append(getMobile()).append(", ");
        sb.append("Name: ").append(getName()).append(", ");
        sb.append("Nationality: ").append(getNationality()).append(", ");
        sb.append("PostalCode: ").append(getPostalCode()).append(", ");
        sb.append("PreName: ").append(getPreName()).append(", ");
        sb.append("Profession: ").append(getProfession()).append(", ");
        sb.append("SocialInsuranceNo: ").append(getSocialInsuranceNo()).append(", ");
        sb.append("Street: ").append(getStreet()).append(", ");
        sb.append("Telephone: ").append(getTelephone()).append(", ");
        sb.append("Telephone2: ").append(getTelephone2()).append(", ");
        sb.append("Version: ").append(getVersion()).append(", ");
        sb.append("VideoPath: ").append(getVideoPath()).append(", ");
        sb.append("Weight: ").append(getWeight()).append(", ");
        sb.append("WorkPermission: ").append(getWorkPermission());
        return sb.toString();
    }
    
}