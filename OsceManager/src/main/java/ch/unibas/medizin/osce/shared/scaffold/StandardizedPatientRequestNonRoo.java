package ch.unibas.medizin.osce.shared.scaffold;

import java.util.List;

import ch.unibas.medizin.osce.client.managed.request.AdvancedSearchCriteriaProxy;
import ch.unibas.medizin.osce.client.managed.request.StandardizedPatientProxy;
import ch.unibas.medizin.osce.domain.StandardizedPatient;
import ch.unibas.medizin.osce.shared.Sorting;


import com.google.gwt.requestfactory.shared.InstanceRequest;
import com.google.gwt.requestfactory.shared.Request;
import com.google.gwt.requestfactory.shared.RequestContext;
import com.google.gwt.requestfactory.shared.Service;

import com.google.gwt.view.client.Range;

@SuppressWarnings("deprecation")
@Service(StandardizedPatient.class)
public interface StandardizedPatientRequestNonRoo extends RequestContext {
	abstract Request<Long> countPatientsByAdvancedSearchAndSort(String searchWord, List<String> searchThrough, List<AdvancedSearchCriteriaProxy> searchCriteria);
	
	abstract Request<List<StandardizedPatientProxy>> findPatientsByAdvancedSearchAndSort(
    		String sortColumn,
    		Sorting order,
    		String searchWord, 
    		List<String> searchThrough,
    		List<AdvancedSearchCriteriaProxy> searchCriteria,
    		Integer firstResult, 
    		Integer maxResults
    );
        
         //By Spec[start
         abstract Request<String> getCSVMapperFindPatientsByAdvancedSearchAndSort(
			String sortColumn, Sorting order, String searchWord,
			List<String> searchThrough,
			List<AdvancedSearchCriteriaProxy> searchCriteria
                         , int firstResult, int maxResults
	);
         abstract Request<String> getPdfPatientsBySearch(
    			 StandardizedPatientProxy standardizedPatientProxy);
         //By Spec]End
}
