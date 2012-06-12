package ch.unibas.medizin.osce.client.a_nonroo.client.ui.role;

import java.util.List;

import ch.unibas.medizin.osce.client.a_nonroo.client.SearchCriteria;
import ch.unibas.medizin.osce.client.managed.request.DoctorProxy;
import ch.unibas.medizin.osce.client.managed.request.KeywordProxy;
import ch.unibas.medizin.osce.client.managed.request.RoleParticipantProxy;
import ch.unibas.medizin.osce.client.managed.request.RoleTopicProxy;
import ch.unibas.medizin.osce.client.managed.request.ScarProxy;
import ch.unibas.medizin.osce.client.managed.request.SpecialisationProxy;
import ch.unibas.medizin.osce.shared.StudyYears;
import ch.unibas.medizin.osce.shared.TraitTypes;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.ValueListBox;

/**
 * @author dk
 *
 */
public interface RoleView extends IsWidget{
	
    public interface Presenter {
        void goTo(Place place);
    }
	/**
	 * Implemented by the owner of the view.
	 */
	interface Delegate {
		// TODO define methods to be delegated!
		void showSubviewClicked();
		//spec start
		public void performSearch(String q, List<String> list,List<String> tableList,List<String> whereList);
		
		void newClicked(String value, String value2, SpecialisationProxy specialisationProxy,
				StudyYears value4);

		//spec end
	}

    String[] getPaths();
    
    void setDelegate(Delegate delegate);
    
    SimplePanel getDetailsPanel();
    
    void setPresenter(Presenter systemStartActivity);
    //spec start
  CellTable<RoleTopicProxy> getTable();
  public void updateSearch();
  ListBox getListBox();
  public ValueListBox<SpecialisationProxy> getSpecialisationBoxValues();
  public void setSpecialisationBoxValues(List<SpecialisationProxy> values);
  public List<String> getSearchFilters();
  public SearchCriteria getCriteria();
  //spec end

  public RoleFilterViewTooltipImpl getFilter();
  
/*void setKeywordListBoxValues(List<KeywordProxy> values);

void setReviewerListBoxValues(List<DoctorProxy> values);
void setAuthorListBoxValues(List<DoctorProxy> values);*/

void setKeywordAutocompleteValue(List<KeywordProxy> values);
void setAuthorAutocompleteValue(List<DoctorProxy> values);
void setReviewerAutocompleteValue(List<DoctorProxy> values);
void setSpecialisationAutocompleteValue(List<SpecialisationProxy> values);


}
