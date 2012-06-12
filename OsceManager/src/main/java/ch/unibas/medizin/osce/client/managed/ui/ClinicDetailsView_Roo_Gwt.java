// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.

package ch.unibas.medizin.osce.client.managed.ui;

import ch.unibas.medizin.osce.client.managed.request.ClinicProxy;
import ch.unibas.medizin.osce.client.managed.request.DoctorProxy;
import ch.unibas.medizin.osce.client.scaffold.place.ProxyDetailsView;
import ch.unibas.medizin.osce.client.scaffold.place.ProxyListView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import java.util.Set;

public abstract class ClinicDetailsView_Roo_Gwt extends Composite implements ProxyDetailsView<ClinicProxy> {

    @UiField
    SpanElement name;

    @UiField
    SpanElement street;

    @UiField
    SpanElement city;

    @UiField
    SpanElement postalCode;

    @UiField
    SpanElement doctors;

    @UiField
    SpanElement id;

    @UiField
    SpanElement version;

    ClinicProxy proxy;

    @UiField
    SpanElement displayRenderer;

    public void setValue(ClinicProxy proxy) {
        this.proxy = proxy;
        name.setInnerText(proxy.getName() == null ? "" : String.valueOf(proxy.getName()));
        street.setInnerText(proxy.getStreet() == null ? "" : String.valueOf(proxy.getStreet()));
        city.setInnerText(proxy.getCity() == null ? "" : String.valueOf(proxy.getCity()));
        postalCode.setInnerText(proxy.getPostalCode() == null ? "" : String.valueOf(proxy.getPostalCode()));
        doctors.setInnerText(proxy.getDoctors() == null ? "" : ch.unibas.medizin.osce.client.scaffold.place.CollectionRenderer.of(ch.unibas.medizin.osce.client.managed.ui.DoctorProxyRenderer.instance()).render(proxy.getDoctors()));
        id.setInnerText(proxy.getId() == null ? "" : String.valueOf(proxy.getId()));
        version.setInnerText(proxy.getVersion() == null ? "" : String.valueOf(proxy.getVersion()));
        displayRenderer.setInnerText(ch.unibas.medizin.osce.client.managed.ui.ClinicProxyRenderer.instance().render(proxy));
    }
}