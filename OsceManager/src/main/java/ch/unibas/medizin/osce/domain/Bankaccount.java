package ch.unibas.medizin.osce.domain;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

@RooJavaBean
@RooToString
@RooEntity
public class Bankaccount {

    @Size(max = 40)
    private String bankName;

    @Size(max = 40)
    private String IBAN;

    @Size(max = 40)
    private String BIC;
    
    @Size(max = 40)
    private String ownerName;
    
    private Integer postalCode;
    
    @Size(max = 30)
    private String city;
    
    @ManyToOne
    private Nationality country;
}
