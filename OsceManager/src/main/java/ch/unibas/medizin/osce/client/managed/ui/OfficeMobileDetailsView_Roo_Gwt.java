// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.

package ch.unibas.medizin.osce.client.managed.ui;

import ch.unibas.medizin.osce.client.managed.request.OfficeProxy;
import ch.unibas.medizin.osce.client.scaffold.place.ProxyDetailsView;
import ch.unibas.medizin.osce.shared.Gender;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
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

public abstract class OfficeMobileDetailsView_Roo_Gwt extends Composite implements ProxyDetailsView<OfficeProxy> {

    @UiField
    Element gender;

    @UiField
    Element title;

    @UiField
    Element name;

    @UiField
    Element preName;

    @UiField
    Element email;

    @UiField
    Element telephone;

    @UiField
    Element id;

    @UiField
    Element version;

    OfficeProxy proxy;

    public void setValue(OfficeProxy proxy) {
        this.proxy = proxy;
        gender.setInnerText(proxy.getGender() == null ? "" : String.valueOf(proxy.getGender()));
        title.setInnerText(proxy.getTitle() == null ? "" : String.valueOf(proxy.getTitle()));
        name.setInnerText(proxy.getName() == null ? "" : String.valueOf(proxy.getName()));
        preName.setInnerText(proxy.getPreName() == null ? "" : String.valueOf(proxy.getPreName()));
        email.setInnerText(proxy.getEmail() == null ? "" : String.valueOf(proxy.getEmail()));
        telephone.setInnerText(proxy.getTelephone() == null ? "" : String.valueOf(proxy.getTelephone()));
        id.setInnerText(proxy.getId() == null ? "" : String.valueOf(proxy.getId()));
        version.setInnerText(proxy.getVersion() == null ? "" : String.valueOf(proxy.getVersion()));
    }
}
