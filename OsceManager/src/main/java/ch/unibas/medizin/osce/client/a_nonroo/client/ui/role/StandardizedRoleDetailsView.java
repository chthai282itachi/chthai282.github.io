package ch.unibas.medizin.osce.client.a_nonroo.client.ui.role;

import java.util.List;

import ch.unibas.medizin.osce.client.a_nonroo.client.ui.sp.criteria.StandartizedPatientAdvancedSearchSubView;
import ch.unibas.medizin.osce.client.managed.request.RoleTemplateProxy;
import ch.unibas.medizin.osce.client.managed.request.StandardizedRoleProxy;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public interface StandardizedRoleDetailsView extends IsWidget {

	/**
	 * Implemented by the owner of the view.
	 */
	interface Delegate {
		void editClicked();

		void editRoleClicked(StandardizedRoleProxy standardizedRoleProxy);

		// Edit Clicked from StandardizedRoleDetailViewImpl implementation in
		// RoleDetailActivity
		void deleteRoleClicked(StandardizedRoleProxy standardizedRoleProxy);
		// void createRole(StandardizedRoleProxy standardizedRoleProxy);
	void previousRoleClicked(StandardizedRoleProxy standardizedRoleProxy);//spec

	//Assignment E[
			void saveCheckListTopic(String checkListTopic,String description);
			
			void setRoleListBoxValue(ImportTopicPopupView popupView);
			//Assignment E]
	
			//Assignment I
			
			void roleTemplateValueButtonClicked(RoleTemplateProxy value);
	}

	/*
	 * StandardizedPatientAnamnesisSubViewImpl
	 * getStandardizedPatientAnamnesisSubViewImpl();
	 * StandardizedPatientLangSkillSubViewImpl
	 * getStandardizedPatientLangSkillSubViewImpl();
	 * StandardizedPatientMediaSubViewImpl
	 * getStandardizedPatientMediaSubViewImpl();
	 */

/*	public void setValue(StandardizedRoleProxy proxy);

	public void setDelegate(Delegate delegate);*/

	// Assignment H[
	public RoleFileSubViewImpl getRoleFileSubViewImpl();

	// Assignment G[
	public RoomMaterialsDetailsSubViewImpl getRoomMaterialsDetailsSubViewImpl();

	// ]End

    public void setValue(StandardizedRoleProxy proxy); 
    public void setDelegate(Delegate delegate);

	public StandartizedPatientAdvancedSearchSubView getStandartizedPatientAdvancedSearchSubViewImpl();
	public TabPanel getRoleSubPanel();
	// ]Assignment F
	
	// SPEC START =
    public RoleRoleParticipantSubViewImpl getRoleRoleParticipantSubViewImpl();
    public RoleKeywordSubViewImpl getRoleKeywordSubViewImpl();
	// SPEC END =
    void setRoleTemplateListBox(List<RoleTemplateProxy> roleTemplateproxy);
	VerticalPanel getRoleBaseItemVerticalPanel();
	

}
