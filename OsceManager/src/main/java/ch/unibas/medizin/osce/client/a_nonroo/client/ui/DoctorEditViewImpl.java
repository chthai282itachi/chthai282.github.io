

package ch.unibas.medizin.osce.client.a_nonroo.client.ui;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import ch.unibas.medizin.osce.client.i18n.Messages;
import ch.unibas.medizin.osce.client.managed.request.ClinicProxy;
import ch.unibas.medizin.osce.client.managed.request.DoctorProxy;
import ch.unibas.medizin.osce.client.style.widgets.TabPanelHelper;
import ch.unibas.medizin.osce.shared.Gender;
//import ch.unibas.medizin.osce.client.shared.Gender;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.requestfactory.client.RequestFactoryEditorDriver;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.text.shared.AbstractRenderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ValueListBox;
import com.google.gwt.user.client.ui.Widget;

public class DoctorEditViewImpl extends Composite implements DoctorEditView, Editor<DoctorProxy> {

	private static final Binder BINDER = GWT.create(Binder.class);

	private static DoctorEditView instance;
	
	@UiField
	TabPanel doctorPanel;

	@UiField
	Button cancel;
	@UiField
	Button save;
	@UiField
	DivElement errors;

	@UiField
	SpanElement header;
	
	@UiField(provided = true)
    ValueListBox<Gender> gender = new ValueListBox<Gender>(new AbstractRenderer<ch.unibas.medizin.osce.shared.Gender>() {

        public String render(ch.unibas.medizin.osce.shared.Gender obj) {
            return obj == null ? "" : String.valueOf(obj);
        }
    });

	@UiField
	TextBox title;
	@UiField
	TextBox name;
	@UiField
	TextBox preName;
	@UiField
	TextBox email;
	@UiField
	TextBox telephone;
	@UiField(provided = true)
	ValueListBox<ClinicProxy> clinic = new ValueListBox<ClinicProxy>(ch.unibas.medizin.osce.client.managed.ui.ClinicProxyRenderer.instance(), new com.google.gwt.requestfactory.ui.client.EntityProxyKeyProvider<ch.unibas.medizin.osce.client.managed.request.ClinicProxy>());
	@UiField
	SimplePanel officePanel;
	
	@UiField
    SpanElement labelGender;
    @UiField
    SpanElement labelTitle;
    @UiField
    SpanElement labelName;
    @UiField
    SpanElement labelPreName;
    @UiField
    SpanElement labelEmail;
    @UiField
    SpanElement labelTelephone;
    @UiField
    SpanElement labelClinic;
	
	private Delegate delegate;

	private Presenter presenter;

	
	public DoctorEditViewImpl() {
		initWidget(BINDER.createAndBindUi(this));
		gender.setValue(Gender.values()[0]);
		gender.setAcceptableValues(Arrays.asList(Gender.values()));
		
		doctorPanel.selectTab(0);

		doctorPanel.getTabBar().setTabText(0, Messages.GENERAL_INFO);
		doctorPanel.getTabBar().setTabText(1, Messages.OFFICE_DETAILS);
		
		TabPanelHelper.moveTabBarToBottom(doctorPanel);
		
		save.setText(Messages.SAVE);
		cancel.setText(Messages.CANCEL);
		
		labelGender.setInnerText(Messages.GENDER + ":");
		labelTitle.setInnerText(Messages.TITLE + ":");
		labelName.setInnerText(Messages.NAME + ":");
		labelPreName.setInnerText(Messages.PRENAME + ":");
		labelEmail.setInnerText(Messages.EMAIL + ":");
		labelTelephone.setInnerText(Messages.TELEPHONE + ":");
		labelClinic.setInnerText(Messages.CLINIC + ":");
	}


	@Override
	public RequestFactoryEditorDriver<DoctorProxy, DoctorEditViewImpl> createEditorDriver() {
		RequestFactoryEditorDriver<DoctorProxy, DoctorEditViewImpl> driver = GWT.create(Driver.class);
		driver.initialize(this);
		return driver;
	}

	public void setCreating(boolean creating) {
		if (creating) {
			header.setInnerText(Messages.DOCTOR_CREATE);
		} else {
			header.setInnerText(Messages.DOCTOR_EDIT);
		}
	}

	public void setDelegate(Delegate delegate) {
		this.delegate = delegate;
	}

	public void setEnabled(boolean enabled) {
		save.setEnabled(enabled);
	}

	public void showErrors(List<EditorError> errors) {
		SafeHtmlBuilder b = new SafeHtmlBuilder();
		for (EditorError error : errors) {
			b.appendEscaped(error.getPath()).appendEscaped(": ");
			b.appendEscaped(error.getMessage()).appendHtmlConstant("<br>");
		}
		this.errors.setInnerHTML(b.toSafeHtml().asString());
	}

	@UiHandler("cancel")
	void onCancel(ClickEvent event) {
		delegate.cancelClicked();
	}

	@UiHandler("save")
	void onSave(ClickEvent event) {
		delegate.saveClicked();
	}

	interface Binder extends UiBinder<Widget, DoctorEditViewImpl> {
	}

	interface Driver extends RequestFactoryEditorDriver<DoctorProxy, DoctorEditViewImpl> {
	}

	@Override
	public void setEditTitle(boolean edit) {
		
		if (edit) {
			header.setInnerText(Messages.DOCTOR_EDIT);
        } else {
			header.setInnerText(Messages.DOCTOR_CREATE);
        }

	}

	@Override
	public void setPresenter(Presenter doctorEditActivity) {
		this.presenter=doctorEditActivity;
	}

	@Override
	public void setClinicPickerValues(Collection<ClinicProxy> clinicList) {
		clinic.setAcceptableValues(clinicList);
	}
	
	@Override
	public SimplePanel getOfficePanel(){
		return officePanel;
	}
}
