/**
 * 
 */
package ch.unibas.medizin.osce.client.a_nonroo.client.ui.role;

import ch.unibas.medizin.osce.client.managed.request.CheckListProxy;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.requestfactory.client.RequestFactoryEditorDriver;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author dk
 *
 */
//file by spec
//Editor<StandardizedRoleProxy>
public class RoleEditCheckListSubViewImpl extends Composite implements RoleEditCheckListSubView ,Editor<CheckListProxy>
{
	private static final Binder BINDER = GWT.create(Binder.class);
		private Delegate delegate;

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
	
	@UiField
	public TextBox title;
	
		
	/*private static RoleEditCheckListSubViewImplUiBinder uiBinder = GWT.create(RoleEditCheckListSubViewImplUiBinder.class);

	interface RoleEditCheckListSubViewImplUiBinder extends UiBinder<Widget, RoleEditCheckListSubViewImpl> {
	}*/

	interface Binder extends UiBinder<Widget, RoleEditCheckListSubViewImpl> {}
	interface Driver extends RequestFactoryEditorDriver<CheckListProxy, RoleEditCheckListSubViewImpl> {}

	
	public RoleEditCheckListSubViewImpl() {		
		initWidget(BINDER.createAndBindUi(this));
		init();
	}
	
	public void init() {
		// TODO implement this!
	}

	private Presenter presenter;

	@Override
	public void setDelegate(Delegate delegate) {
		this.delegate = delegate;
	}

	

	
//spec start
	
	
	@Override
	public RequestFactoryEditorDriver<CheckListProxy, RoleEditCheckListSubViewImpl> createCheckListEditorDriver() {
		RequestFactoryEditorDriver<CheckListProxy, RoleEditCheckListSubViewImpl> driver = GWT.create(Driver.class);
		driver.initialize(this);
		return driver;
	}
		
	//spec end
	
}