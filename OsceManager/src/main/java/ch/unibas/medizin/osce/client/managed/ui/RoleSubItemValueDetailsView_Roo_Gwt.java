// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.

package ch.unibas.medizin.osce.client.managed.ui;

import ch.unibas.medizin.osce.client.managed.request.RoleBaseItemProxy;
import ch.unibas.medizin.osce.client.managed.request.RoleSubItemValueProxy;
import ch.unibas.medizin.osce.client.managed.request.StandardizedRoleProxy;
import ch.unibas.medizin.osce.client.scaffold.place.ProxyDetailsView;
import ch.unibas.medizin.osce.client.scaffold.place.ProxyListView;
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

public abstract class RoleSubItemValueDetailsView_Roo_Gwt extends Composite implements ProxyDetailsView<RoleSubItemValueProxy> {

    @UiField
    SpanElement id;

    @UiField
    SpanElement version;

    @UiField
    SpanElement itemText;

    @UiField
    SpanElement roleBaseItem;

    @UiField
    SpanElement standardizedRole;

    RoleSubItemValueProxy proxy;

    @UiField
    SpanElement displayRenderer;

    public void setValue(RoleSubItemValueProxy proxy) {
        this.proxy = proxy;
        id.setInnerText(proxy.getId() == null ? "" : String.valueOf(proxy.getId()));
        version.setInnerText(proxy.getVersion() == null ? "" : String.valueOf(proxy.getVersion()));
        itemText.setInnerText(proxy.getItemText() == null ? "" : String.valueOf(proxy.getItemText()));
        roleBaseItem.setInnerText(proxy.getRoleBaseItem() == null ? "" : ch.unibas.medizin.osce.client.managed.ui.RoleBaseItemProxyRenderer.instance().render(proxy.getRoleBaseItem()));
        standardizedRole.setInnerText(proxy.getStandardizedRole() == null ? "" : ch.unibas.medizin.osce.client.managed.ui.StandardizedRoleProxyRenderer.instance().render(proxy.getStandardizedRole()));
        displayRenderer.setInnerText(RoleSubItemValueProxyRenderer.instance().render(proxy));
    }
}
