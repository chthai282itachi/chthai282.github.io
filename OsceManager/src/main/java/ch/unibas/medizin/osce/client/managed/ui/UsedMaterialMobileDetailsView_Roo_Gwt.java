// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.

package ch.unibas.medizin.osce.client.managed.ui;

import ch.unibas.medizin.osce.client.managed.request.MaterialListProxy;
import ch.unibas.medizin.osce.client.managed.request.StandardizedRoleProxy;
import ch.unibas.medizin.osce.client.managed.request.UsedMaterialProxy;
import ch.unibas.medizin.osce.client.scaffold.place.ProxyDetailsView;
import ch.unibas.medizin.osce.shared.MaterialUsedFromTypes;
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

public abstract class UsedMaterialMobileDetailsView_Roo_Gwt extends Composite implements ProxyDetailsView<UsedMaterialProxy> {

    @UiField
    Element materialCount;

    @UiField
    Element used_from;

    @UiField
    Element standardizedRole;

    @UiField
    Element materialList;

    @UiField
    Element sort_order;

    @UiField
    Element id;

    @UiField
    Element version;

    UsedMaterialProxy proxy;

    public void setValue(UsedMaterialProxy proxy) {
        this.proxy = proxy;
        materialCount.setInnerText(proxy.getMaterialCount() == null ? "" : String.valueOf(proxy.getMaterialCount()));
        used_from.setInnerText(proxy.getUsed_from() == null ? "" : String.valueOf(proxy.getUsed_from()));
        standardizedRole.setInnerText(proxy.getStandardizedRole() == null ? "" : ch.unibas.medizin.osce.client.managed.ui.StandardizedRoleProxyRenderer.instance().render(proxy.getStandardizedRole()));
        materialList.setInnerText(proxy.getMaterialList() == null ? "" : ch.unibas.medizin.osce.client.managed.ui.MaterialListProxyRenderer.instance().render(proxy.getMaterialList()));
        sort_order.setInnerText(proxy.getSort_order() == null ? "" : String.valueOf(proxy.getSort_order()));
        id.setInnerText(proxy.getId() == null ? "" : String.valueOf(proxy.getId()));
        version.setInnerText(proxy.getVersion() == null ? "" : String.valueOf(proxy.getVersion()));
    }
}
