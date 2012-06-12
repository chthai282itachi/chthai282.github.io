// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.

package ch.unibas.medizin.osce.client.managed.ui;

import ch.unibas.medizin.osce.client.managed.request.ClinicProxy;
import ch.unibas.medizin.osce.client.managed.request.DoctorProxy;
import ch.unibas.medizin.osce.client.managed.request.OfficeProxy;
import ch.unibas.medizin.osce.client.managed.request.RoleParticipantProxy;
import ch.unibas.medizin.osce.client.managed.request.SpecialisationProxy;
import ch.unibas.medizin.osce.client.scaffold.place.ProxyDetailsView;
import ch.unibas.medizin.osce.client.scaffold.place.ProxyListView;
import ch.unibas.medizin.osce.shared.Gender;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import java.util.Set;

public abstract class DoctorDetailsView_Roo_Gwt extends Composite implements ProxyDetailsView<DoctorProxy> {

    @UiField
    SpanElement id;

    @UiField
    SpanElement version;

    @UiField
    SpanElement gender;

    @UiField
    SpanElement title;

    @UiField
    SpanElement name;

    @UiField
    SpanElement preName;

    @UiField
    SpanElement email;

    @UiField
    SpanElement telephone;

    @UiField
    SpanElement clinic;

    @UiField
    SpanElement office;

    @UiField
    SpanElement isActive;

    @UiField
    SpanElement specialisation;

    @UiField
    SpanElement roleParticipants;

    DoctorProxy proxy;

    @UiField
    SpanElement displayRenderer;

    public void setValue(DoctorProxy proxy) {
        this.proxy = proxy;
        id.setInnerText(proxy.getId() == null ? "" : String.valueOf(proxy.getId()));
        version.setInnerText(proxy.getVersion() == null ? "" : String.valueOf(proxy.getVersion()));
        gender.setInnerText(proxy.getGender() == null ? "" : String.valueOf(proxy.getGender()));
        title.setInnerText(proxy.getTitle() == null ? "" : String.valueOf(proxy.getTitle()));
        name.setInnerText(proxy.getName() == null ? "" : String.valueOf(proxy.getName()));
        preName.setInnerText(proxy.getPreName() == null ? "" : String.valueOf(proxy.getPreName()));
        email.setInnerText(proxy.getEmail() == null ? "" : String.valueOf(proxy.getEmail()));
        telephone.setInnerText(proxy.getTelephone() == null ? "" : String.valueOf(proxy.getTelephone()));
        clinic.setInnerText(proxy.getClinic() == null ? "" : ch.unibas.medizin.osce.client.managed.ui.ClinicProxyRenderer.instance().render(proxy.getClinic()));
        office.setInnerText(proxy.getOffice() == null ? "" : ch.unibas.medizin.osce.client.managed.ui.OfficeProxyRenderer.instance().render(proxy.getOffice()));
        isActive.setInnerText(proxy.getIsActive() == null ? "" : String.valueOf(proxy.getIsActive()));
        specialisation.setInnerText(proxy.getSpecialisation() == null ? "" : ch.unibas.medizin.osce.client.managed.ui.SpecialisationProxyRenderer.instance().render(proxy.getSpecialisation()));
        roleParticipants.setInnerText(proxy.getRoleParticipants() == null ? "" : ch.unibas.medizin.osce.client.scaffold.place.CollectionRenderer.of(ch.unibas.medizin.osce.client.managed.ui.RoleParticipantProxyRenderer.instance()).render(proxy.getRoleParticipants()));
        displayRenderer.setInnerText(DoctorProxyRenderer.instance().render(proxy));
    }
}