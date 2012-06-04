// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.

package ch.unibas.medizin.osce.client.managed.ui;

import ch.unibas.medizin.osce.client.managed.request.RoleBaseItemProxy;
import ch.unibas.medizin.osce.client.managed.request.RoleTableItemProxy;
import ch.unibas.medizin.osce.client.managed.request.RoleTableItemValueProxy;
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
import java.util.Set;

public abstract class RoleTableItemMobileDetailsView_Roo_Gwt extends Composite implements ProxyDetailsView<RoleTableItemProxy> {

    @UiField
    Element itemName;

    @UiField
    Element roleBaseItem;

    @UiField
    Element sort_order;

    @UiField
    Element roleTableItemValue;

    @UiField
    Element id;

    @UiField
    Element version;

    RoleTableItemProxy proxy;

    public void setValue(RoleTableItemProxy proxy) {
        this.proxy = proxy;
        itemName.setInnerText(proxy.getItemName() == null ? "" : String.valueOf(proxy.getItemName()));
        roleBaseItem.setInnerText(proxy.getRoleBaseItem() == null ? "" : ch.unibas.medizin.osce.client.managed.ui.RoleBaseItemProxyRenderer.instance().render(proxy.getRoleBaseItem()));
        sort_order.setInnerText(proxy.getSort_order() == null ? "" : String.valueOf(proxy.getSort_order()));
        roleTableItemValue.setInnerText(proxy.getRoleTableItemValue() == null ? "" : ch.unibas.medizin.osce.client.scaffold.place.CollectionRenderer.of(ch.unibas.medizin.osce.client.managed.ui.RoleTableItemValueProxyRenderer.instance()).render(proxy.getRoleTableItemValue()));
        id.setInnerText(proxy.getId() == null ? "" : String.valueOf(proxy.getId()));
        version.setInnerText(proxy.getVersion() == null ? "" : String.valueOf(proxy.getVersion()));
    }
}
