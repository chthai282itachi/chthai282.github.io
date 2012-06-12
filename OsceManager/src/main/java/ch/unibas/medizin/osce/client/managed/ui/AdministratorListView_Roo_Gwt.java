// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.

package ch.unibas.medizin.osce.client.managed.ui;

import ch.unibas.medizin.osce.client.managed.request.AdministratorProxy;
import ch.unibas.medizin.osce.client.managed.request.SemesterProxy;
import ch.unibas.medizin.osce.client.managed.request.TaskProxy;
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

public abstract class AdministratorListView_Roo_Gwt extends AbstractProxyListView<AdministratorProxy> {

    @UiField
    CellTable<AdministratorProxy> table;

    protected Set<String> paths = new HashSet<String>();

    public void init() {
        paths.add("email");
        table.addColumn(new TextColumn<AdministratorProxy>() {

            Renderer<java.lang.String> renderer = new AbstractRenderer<java.lang.String>() {

                public String render(java.lang.String obj) {
                    return obj == null ? "" : String.valueOf(obj);
                }
            };

            @Override
            public String getValue(AdministratorProxy object) {
                return renderer.render(object.getEmail());
            }
        }, "Email");
        paths.add("name");
        table.addColumn(new TextColumn<AdministratorProxy>() {

            Renderer<java.lang.String> renderer = new AbstractRenderer<java.lang.String>() {

                public String render(java.lang.String obj) {
                    return obj == null ? "" : String.valueOf(obj);
                }
            };

            @Override
            public String getValue(AdministratorProxy object) {
                return renderer.render(object.getName());
            }
        }, "Name");
        paths.add("preName");
        table.addColumn(new TextColumn<AdministratorProxy>() {

            Renderer<java.lang.String> renderer = new AbstractRenderer<java.lang.String>() {

                public String render(java.lang.String obj) {
                    return obj == null ? "" : String.valueOf(obj);
                }
            };

            @Override
            public String getValue(AdministratorProxy object) {
                return renderer.render(object.getPreName());
            }
        }, "Pre Name");
        paths.add("semesters");
        table.addColumn(new TextColumn<AdministratorProxy>() {

            Renderer<java.util.Set> renderer = ch.unibas.medizin.osce.client.scaffold.place.CollectionRenderer.of(ch.unibas.medizin.osce.client.managed.ui.SemesterProxyRenderer.instance());

            @Override
            public String getValue(AdministratorProxy object) {
                return renderer.render(object.getSemesters());
            }
        }, "Semesters");
        paths.add("tasks");
        table.addColumn(new TextColumn<AdministratorProxy>() {

            Renderer<java.util.Set> renderer = ch.unibas.medizin.osce.client.scaffold.place.CollectionRenderer.of(ch.unibas.medizin.osce.client.managed.ui.TaskProxyRenderer.instance());

            @Override
            public String getValue(AdministratorProxy object) {
                return renderer.render(object.getTasks());
            }
        }, "Tasks");
        paths.add("id");
        table.addColumn(new TextColumn<AdministratorProxy>() {

            Renderer<java.lang.Long> renderer = new AbstractRenderer<java.lang.Long>() {

                public String render(java.lang.Long obj) {
                    return obj == null ? "" : String.valueOf(obj);
                }
            };

            @Override
            public String getValue(AdministratorProxy object) {
                return renderer.render(object.getId());
            }
        }, "Id");
        paths.add("version");
        table.addColumn(new TextColumn<AdministratorProxy>() {

            Renderer<java.lang.Integer> renderer = new AbstractRenderer<java.lang.Integer>() {

                public String render(java.lang.Integer obj) {
                    return obj == null ? "" : String.valueOf(obj);
                }
            };

            @Override
            public String getValue(AdministratorProxy object) {
                return renderer.render(object.getVersion());
            }
        }, "Version");
    }
}