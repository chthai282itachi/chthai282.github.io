// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.

package ch.unibas.medizin.osce.client.managed.ui;

import ch.unibas.medizin.osce.client.managed.request.CourseProxy;
import ch.unibas.medizin.osce.client.managed.request.OscePostRoomProxy;
import ch.unibas.medizin.osce.client.managed.request.OsceProxy;
import ch.unibas.medizin.osce.client.scaffold.place.AbstractProxyListView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.text.client.DateTimeFormatRenderer;
import com.google.gwt.text.shared.AbstractRenderer;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import java.util.HashSet;
import java.util.Set;

public abstract class CourseListView_Roo_Gwt extends AbstractProxyListView<CourseProxy> {

    @UiField
    CellTable<CourseProxy> table;

    protected Set<String> paths = new HashSet<String>();

    public void init() {
        paths.add("color");
        table.addColumn(new TextColumn<CourseProxy>() {

            Renderer<java.lang.String> renderer = new AbstractRenderer<java.lang.String>() {

                public String render(java.lang.String obj) {
                    return obj == null ? "" : String.valueOf(obj);
                }
            };

            @Override
            public String getValue(CourseProxy object) {
                return renderer.render(object.getColor());
            }
        }, "Color");
        paths.add("osce");
        table.addColumn(new TextColumn<CourseProxy>() {

            Renderer<ch.unibas.medizin.osce.client.managed.request.OsceProxy> renderer = ch.unibas.medizin.osce.client.managed.ui.OsceProxyRenderer.instance();

            @Override
            public String getValue(CourseProxy object) {
                return renderer.render(object.getOsce());
            }
        }, "Osce");
        paths.add("oscePostRooms");
        table.addColumn(new TextColumn<CourseProxy>() {

            Renderer<java.util.Set> renderer = ch.unibas.medizin.osce.client.scaffold.place.CollectionRenderer.of(ch.unibas.medizin.osce.client.managed.ui.OscePostRoomProxyRenderer.instance());

            @Override
            public String getValue(CourseProxy object) {
                return renderer.render(object.getOscePostRooms());
            }
        }, "Osce Post Rooms");
        paths.add("id");
        table.addColumn(new TextColumn<CourseProxy>() {

            Renderer<java.lang.Long> renderer = new AbstractRenderer<java.lang.Long>() {

                public String render(java.lang.Long obj) {
                    return obj == null ? "" : String.valueOf(obj);
                }
            };

            @Override
            public String getValue(CourseProxy object) {
                return renderer.render(object.getId());
            }
        }, "Id");
        paths.add("version");
        table.addColumn(new TextColumn<CourseProxy>() {

            Renderer<java.lang.Integer> renderer = new AbstractRenderer<java.lang.Integer>() {

                public String render(java.lang.Integer obj) {
                    return obj == null ? "" : String.valueOf(obj);
                }
            };

            @Override
            public String getValue(CourseProxy object) {
                return renderer.render(object.getVersion());
            }
        }, "Version");
    }
}