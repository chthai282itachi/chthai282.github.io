// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.

package ch.unibas.medizin.osce.client.managed.ui;

import ch.unibas.medizin.osce.client.managed.request.BankaccountProxy;
import ch.unibas.medizin.osce.client.managed.request.NationalityProxy;
import ch.unibas.medizin.osce.client.scaffold.place.ProxyDetailsView;
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

public abstract class BankaccountMobileDetailsView_Roo_Gwt extends Composite implements ProxyDetailsView<BankaccountProxy> {

    @UiField
    Element bankName;

    @UiField
    Element iBAN;

    @UiField
    Element bIC;

    @UiField
    Element ownerName;

    @UiField
    Element postalCode;

    @UiField
    Element city;

    @UiField
    Element country;

    @UiField
    Element id;

    @UiField
    Element version;

    BankaccountProxy proxy;

    public void setValue(BankaccountProxy proxy) {
        this.proxy = proxy;
        bankName.setInnerText(proxy.getBankName() == null ? "" : String.valueOf(proxy.getBankName()));
        iBAN.setInnerText(proxy.getIBAN() == null ? "" : String.valueOf(proxy.getIBAN()));
        bIC.setInnerText(proxy.getBIC() == null ? "" : String.valueOf(proxy.getBIC()));
        ownerName.setInnerText(proxy.getOwnerName() == null ? "" : String.valueOf(proxy.getOwnerName()));
        postalCode.setInnerText(proxy.getPostalCode() == null ? "" : String.valueOf(proxy.getPostalCode()));
        city.setInnerText(proxy.getCity() == null ? "" : String.valueOf(proxy.getCity()));
        country.setInnerText(proxy.getCountry() == null ? "" : ch.unibas.medizin.osce.client.managed.ui.NationalityProxyRenderer.instance().render(proxy.getCountry()));
        id.setInnerText(proxy.getId() == null ? "" : String.valueOf(proxy.getId()));
        version.setInnerText(proxy.getVersion() == null ? "" : String.valueOf(proxy.getVersion()));
    }
}