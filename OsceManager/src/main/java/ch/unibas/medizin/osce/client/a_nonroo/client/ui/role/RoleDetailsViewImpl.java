/**
 * 
 */
package ch.unibas.medizin.osce.client.a_nonroo.client.ui.role;

import java.awt.Label;

import org.apache.bcel.generic.GOTO;

import ch.unibas.medizin.osce.client.a_nonroo.client.place.StandardizedPatientDetailsPlace;
import ch.unibas.medizin.osce.client.a_nonroo.client.ui.renderer.EnumRenderer;
import ch.unibas.medizin.osce.client.a_nonroo.client.ui.sp.StandardizedPatientScarSubViewImpl;
import ch.unibas.medizin.osce.client.a_nonroo.client.ui.sp.StandardizedPatientDetailsView.Presenter;
import ch.unibas.medizin.osce.client.i18n.OsceConstants;
import ch.unibas.medizin.osce.client.managed.request.StandardizedPatientProxy;
import ch.unibas.medizin.osce.client.managed.request.StandardizedRoleProxy;
import ch.unibas.medizin.osce.client.style.widgets.IconButton;
import ch.unibas.medizin.osce.client.style.widgets.TabPanelHelper;
import ch.unibas.medizin.osce.shared.Gender;
import ch.unibas.medizin.osce.shared.Operation;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.TabBar;
import com.google.gwt.user.client.ui.TabBar.Tab;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.Widget;
/**
 * @author dk
 *
 */
public class RoleDetailsViewImpl extends Composite implements RoleDetailsView 
{
	private static RoleDetailsViewImplUiBinder uiBinder = GWT.create(RoleDetailsViewImplUiBinder.class);

	interface RoleDetailsViewImplUiBinder extends UiBinder<Widget, RoleDetailsViewImpl> {
	}

	private OsceConstants constants = GWT.create(OsceConstants.class);
	private Presenter presenter;
	StandardizedRoleProxy proxy;
	
	@UiField
	TabPanel roleDetailPanel;
	
	private Delegate delegate;
	
	//@UiField
	StandardizedRoleDetailsViewImpl standardizedRoleDetailsViewImpl[];
	
	
	/*@UiField
		DisclosurePanel roleDisclosurePanel;
		@UiField
		Image arrow;*/
		//SPEC End
		
		// Panels
		
		//@UiField
		//TabPanel roleDetailPanel;
		
	/*	@UiField
		TabPanel rolePanel;
		
		@UiField
		TabPanel subViewPanel;
		
			
		// Buttons
		@UiField
		IconButton print;
		@UiField
		IconButton edit;
		@UiField
		IconButton delete;
		
		// Labels (Fieldnames)
		@UiField
		SpanElement labelShortName;
		@UiField
		SpanElement labellongName;
		@UiField
		SpanElement labelroletype;
		@UiField
		SpanElement labelstudyYear;
		
		// Temp Fields
		
		@UiField
		SpanElement labelShortNameValue;
		@UiField
		SpanElement labellongValue;
		@UiField
		SpanElement labelroletypeValue;
		@UiField
		SpanElement labelstudyYearValue;
		@UiField
		com.google.gwt.user.client.ui.Label labelLongNameHeader;*/
		
		
		/*// Fields
		@UiField
		SpanElement shortName;
		@UiField
		SpanElement longName;
		@UiField
		SpanElement roleType;
		@UiField
		SpanElement studyYear;*/
			
		
	
	
	public void setStandardizedRoleDetailsViewImpl(
			StandardizedRoleDetailsViewImpl standardizedRoleDetailsViewImpl[]) {
		this.standardizedRoleDetailsViewImpl = standardizedRoleDetailsViewImpl;
	}

	/**
	 * Because this class has a default constructor, it can
	 * be used as a binder template. In other words, it can be used in other
	 * *.ui.xml files as follows:
	 * <ui:UiBinder xmlns:ui="urn:ui:com.google.gwt.uibinder"
	 *   xmlns:g="urn:import:**user's package**">
	 *  <g:**UserClassName**>Hello!</g:**UserClassName>
	 * </ui:UiBinder>
	 * Note that depending on the widget that is used, it may be necessary to
	 * implement HasHTML instead of HasText.
	 */
	
	public RoleDetailsViewImpl() 
	{
		
		initWidget(uiBinder.createAndBindUi(this));	
		/*rolePanel.selectTab(0);*/
		//roleDetailPanel.selectTab(0);
		/*subViewPanel.selectTab(0);
		
		TabPanelHelper.moveTabBarToBottom(rolePanel);
		
		print.setText(constants.print());
		edit.setText(constants.edit());
		delete.setText(constants.delete());
		*/
		
		setTabTexts();
		setLabelTexts();
		
		init();
		/*roleDisclosurePanel.setContent(rolePanel);
		roleDisclosurePanel.setStyleName("");*/
	}
	
	public void init()
	{
		
	}
	private void setTabTexts() {
	/*	rolePanel.getTabBar().setTabText(0, constants.roleDetail());
		rolePanel.getTabBar().setTabText(1,  constants.roleParticipants());
		rolePanel.getTabBar().setTabText(2, constants.keywords());
		rolePanel.getTabBar().setTabText(3, constants.learningObjectives());	*/
		
		/*subViewPanel.getTabBar().setTabText(0, constants.subView1());
		subViewPanel.getTabBar().setTabText(1, constants.subView2());*/		
	}
	
	private void setLabelTexts() {
		/*labelShortName.setInnerText(constants.shortName() + ":");
		labellongName.setInnerText(constants.name() + ":");
		labelroletype.setInnerText(constants.roleType() + ":");		
		labelstudyYear.setInnerText(constants.studyYear() + ":");
		
		labelShortNameValue.setInnerText("Kohler");
		labellongValue.setInnerText("Daniel");
		labelroletypeValue.setInnerText("Simpat");
		labelstudyYearValue.setInnerText("SJ1");
		labelLongNameHeader.setText("Daniel");*/
	}
	
	@Override
	public void setValue(StandardizedRoleProxy proxy) {
		this.proxy = proxy;		
		/*shortName.setInnerText(proxy.getShortName() == null ? "" : String.valueOf(proxy.getShortName()));
		longName.setInnerText(proxy.getLongName() == null ? "" : String.valueOf(proxy.getLongName()));
		roleType.setInnerText(proxy.getRoleType() == null ? "" : String.valueOf(proxy.getRoleType()));
		studyYear.setInnerText(proxy.getStudyYear() == null ? "" : String.valueOf(proxy.getStudyYear()));*/
		
	}
	
	

	@Override
	public void setDelegate(Delegate delegate) {
		this.delegate = delegate;
	}
	
	
	@Override
	public void setPresenter(Presenter RoleActivity) {
		this.presenter =  RoleActivity;
	}
	
	
	public Widget asWidget() {
		return this;
	}
	
	@UiHandler("roleDetailPanel")
	public void roleDetailPanelClicked(SelectionEvent<Integer> click)
	{		
		if(roleDetailPanel.getTabBar().getSelectedTab()==(roleDetailPanel.getWidgetCount()-1))
		{
			Log.info("roleDetailPanel plus clicked");
			delegate.createRole();			
		}
		else
		{
			Log.info("roleDetailPanel clicked");			
		}
		
	}
	
	public StandardizedRoleProxy getValue() {
		return proxy;
	}

	@Override
	public StandardizedRoleDetailsViewImpl[] getStandardizedRoleDetailsViewImpl() 
	{
		return standardizedRoleDetailsViewImpl;
	}
	
	@Override
	public TabPanel getRoleDetailTabPanel()
	{
		return roleDetailPanel;
	}	
}
