package ch.unibas.medizin.osce.domain;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.transaction.annotation.Transactional;

import com.allen_sauer.gwt.log.client.Log;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import ch.unibas.medizin.osce.domain.StandardizedPatient;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.ManyToOne;
import javax.persistence.TypedQuery;

import ch.unibas.medizin.osce.domain.MediaContentType;

@RooJavaBean
@RooToString
@RooEntity
public class MediaContent {

    @NotNull
    @Size(max = 512)
    private String link;

    @Size(max = 512)
    private String comment;

    @ManyToOne
    private StandardizedPatient standardizedPatient;

    @ManyToOne
    private MediaContentType contentType;
    /**
     * Upload function
     * @param patientId connection to the patient
     * @param link link to resource
     * @throws Exception something happened to it
     */
    @Transactional
    public static void createMedia(Long patientId, String link) throws Exception {
    	EntityManager em = entityManager();
    	Log.info("PS: SAVE MC MANAGER IS CREATED");
//    	EntityTransaction transaction =em.getTransaction(); 
//    	Log.info("PS: TRANSACTION "+transaction);
    	try{
    		//transaction.begin();
	    	MediaContent content = new MediaContent();
	    	StandardizedPatient patient = em.find(StandardizedPatient.class , patientId);
	    	Log.info("PS: PATIENT IS FOUND: "+patient.getName());
	    	content.setLink(link);
	    	content.setStandardizedPatient(patient);
	    	Log.info("PS: OBJECT");
	    	em.persist(content);
	    	em.flush();
	    	//transaction.commit();
    	} catch (Exception e) {
    		Log.error("CREATE is going to ROLLBACK because:"+e);
    		//if(transaction.isActive())
    			//transaction.rollback();
		}
    	Log.info("PS: OK");
    	
    }
}
