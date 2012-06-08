// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ch.unibas.medizin.osce.domain;

import ch.unibas.medizin.osce.domain.Clinic;
import ch.unibas.medizin.osce.domain.ClinicDataOnDemand;
import ch.unibas.medizin.osce.domain.Doctor;
import ch.unibas.medizin.osce.domain.Office;
import ch.unibas.medizin.osce.domain.OfficeDataOnDemand;
import ch.unibas.medizin.osce.domain.Specialisation;
import ch.unibas.medizin.osce.domain.SpecialisationDataOnDemand;
import ch.unibas.medizin.osce.shared.Gender;
import java.lang.Boolean;
import java.lang.String;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

privileged aspect DoctorDataOnDemand_Roo_DataOnDemand {
    
    declare @type: DoctorDataOnDemand: @Component;
    
    private Random DoctorDataOnDemand.rnd = new SecureRandom();
    
    private List<Doctor> DoctorDataOnDemand.data;
    
    @Autowired
    private ClinicDataOnDemand DoctorDataOnDemand.clinicDataOnDemand;
    
    @Autowired
    private OfficeDataOnDemand DoctorDataOnDemand.officeDataOnDemand;
    
    @Autowired
    private SpecialisationDataOnDemand DoctorDataOnDemand.specialisationDataOnDemand;
    
    public Doctor DoctorDataOnDemand.getNewTransientDoctor(int index) {
        Doctor obj = new Doctor();
        setClinic(obj, index);
        setEmail(obj, index);
        setGender(obj, index);
        setIsActive(obj, index);
        setName(obj, index);
        setOffice(obj, index);
        setPreName(obj, index);
        setSpecialisation(obj, index);
        setTelephone(obj, index);
        setTitle(obj, index);
        return obj;
    }
    
    public void DoctorDataOnDemand.setClinic(Doctor obj, int index) {
        Clinic clinic = clinicDataOnDemand.getRandomClinic();
        obj.setClinic(clinic);
    }
    
    public void DoctorDataOnDemand.setEmail(Doctor obj, int index) {
        String email = "email_" + index;
        if (email.length() > 40) {
            email = email.substring(0, 40);
        }
        obj.setEmail(email);
    }
    
    public void DoctorDataOnDemand.setGender(Doctor obj, int index) {
        Gender gender = Gender.class.getEnumConstants()[0];
        obj.setGender(gender);
    }
    
    public void DoctorDataOnDemand.setIsActive(Doctor obj, int index) {
        Boolean isActive = Boolean.TRUE;
        obj.setIsActive(isActive);
    }
    
    public void DoctorDataOnDemand.setName(Doctor obj, int index) {
        String name = "name_" + index;
        if (name.length() > 40) {
            name = name.substring(0, 40);
        }
        obj.setName(name);
    }
    
    public void DoctorDataOnDemand.setOffice(Doctor obj, int index) {
        Office office = officeDataOnDemand.getSpecificOffice(index);
        obj.setOffice(office);
    }
    
    public void DoctorDataOnDemand.setPreName(Doctor obj, int index) {
        String preName = "preName_" + index;
        if (preName.length() > 40) {
            preName = preName.substring(0, 40);
        }
        obj.setPreName(preName);
    }
    
    public void DoctorDataOnDemand.setSpecialisation(Doctor obj, int index) {
        Specialisation specialisation = specialisationDataOnDemand.getRandomSpecialisation();
        obj.setSpecialisation(specialisation);
    }
    
    public void DoctorDataOnDemand.setTelephone(Doctor obj, int index) {
        String telephone = "telephone_" + index;
        if (telephone.length() > 30) {
            telephone = telephone.substring(0, 30);
        }
        obj.setTelephone(telephone);
    }
    
    public void DoctorDataOnDemand.setTitle(Doctor obj, int index) {
        String title = "title_" + index;
        if (title.length() > 40) {
            title = title.substring(0, 40);
        }
        obj.setTitle(title);
    }
    
    public Doctor DoctorDataOnDemand.getSpecificDoctor(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size() - 1)) index = data.size() - 1;
        Doctor obj = data.get(index);
        return Doctor.findDoctor(obj.getId());
    }
    
    public Doctor DoctorDataOnDemand.getRandomDoctor() {
        init();
        Doctor obj = data.get(rnd.nextInt(data.size()));
        return Doctor.findDoctor(obj.getId());
    }
    
    public boolean DoctorDataOnDemand.modifyDoctor(Doctor obj) {
        return false;
    }
    
    public void DoctorDataOnDemand.init() {
        data = Doctor.findDoctorEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'Doctor' illegally returned null");
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<ch.unibas.medizin.osce.domain.Doctor>();
        for (int i = 0; i < 10; i++) {
            Doctor obj = getNewTransientDoctor(i);
            try {
                obj.persist();
            } catch (ConstraintViolationException e) {
                StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> it = e.getConstraintViolations().iterator(); it.hasNext();) {
                    ConstraintViolation<?> cv = it.next();
                    msg.append("[").append(cv.getConstraintDescriptor()).append(":").append(cv.getMessage()).append("=").append(cv.getInvalidValue()).append("]");
                }
                throw new RuntimeException(msg.toString(), e);
            }
            obj.flush();
            data.add(obj);
        }
    }
    
}
