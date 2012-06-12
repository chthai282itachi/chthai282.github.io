// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.

package ch.unibas.medizin.osce.client.managed.ui;

import ch.unibas.medizin.osce.client.managed.request.OscePostRoomProxy;
import ch.unibas.medizin.osce.client.managed.request.RoomProxy;
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

public abstract class RoomMobileDetailsView_Roo_Gwt extends Composite implements ProxyDetailsView<RoomProxy> {

    @UiField
    Element id;

    @UiField
    Element version;

    @UiField
    Element roomNumber;

    @UiField
    Element oscePostRooms;

    @UiField
    Element length;

    @UiField
    Element width;

    RoomProxy proxy;

    public void setValue(RoomProxy proxy) {
        this.proxy = proxy;
        id.setInnerText(proxy.getId() == null ? "" : String.valueOf(proxy.getId()));
        version.setInnerText(proxy.getVersion() == null ? "" : String.valueOf(proxy.getVersion()));
        roomNumber.setInnerText(proxy.getRoomNumber() == null ? "" : String.valueOf(proxy.getRoomNumber()));
        oscePostRooms.setInnerText(proxy.getOscePostRooms() == null ? "" : ch.unibas.medizin.osce.client.scaffold.place.CollectionRenderer.of(ch.unibas.medizin.osce.client.managed.ui.OscePostRoomProxyRenderer.instance()).render(proxy.getOscePostRooms()));
        length.setInnerText(proxy.getLength() == null ? "" : String.valueOf(proxy.getLength()));
        width.setInnerText(proxy.getWidth() == null ? "" : String.valueOf(proxy.getWidth()));
    }
}