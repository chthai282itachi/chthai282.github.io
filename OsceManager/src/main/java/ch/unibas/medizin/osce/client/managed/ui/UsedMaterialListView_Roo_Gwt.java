// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.

package ch.unibas.medizin.osce.client.managed.ui;

import ch.unibas.medizin.osce.client.managed.request.MaterialListProxy;
import ch.unibas.medizin.osce.client.managed.request.StandardizedRoleProxy;
import ch.unibas.medizin.osce.client.managed.request.UsedMaterialProxy;
import ch.unibas.medizin.osce.client.scaffold.place.AbstractProxyListView;
import ch.unibas.medizin.osce.shared.MaterialUsedFromTypes;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
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

public abstract class UsedMaterialListView_Roo_Gwt extends AbstractProxyListView<UsedMaterialProxy> {

    @UiField
    CellTable<UsedMaterialProxy> table;

    protected Set<String> paths = new HashSet<String>();

    public void init() {
        paths.add("id");
        table.addColumn(new TextColumn<UsedMaterialProxy>() {

            Renderer<java.lang.Long> renderer = new AbstractRenderer<java.lang.Long>() {

                public String render(java.lang.Long obj) {
                    return obj == null ? "" : String.valueOf(obj);
                }
            };

            @Override
            public String getValue(UsedMaterialProxy object) {
                return renderer.render(object.getId());
            }
        }, "Id");
        paths.add("version");
        table.addColumn(new TextColumn<UsedMaterialProxy>() {

            Renderer<java.lang.Integer> renderer = new AbstractRenderer<java.lang.Integer>() {

                public String render(java.lang.Integer obj) {
                    return obj == null ? "" : String.valueOf(obj);
                }
            };

            @Override
            public String getValue(UsedMaterialProxy object) {
                return renderer.render(object.getVersion());
            }
        }, "Version");
        paths.add("materialCount");
        table.addColumn(new TextColumn<UsedMaterialProxy>() {

            Renderer<java.lang.Integer> renderer = new AbstractRenderer<java.lang.Integer>() {

                public String render(java.lang.Integer obj) {
                    return obj == null ? "" : String.valueOf(obj);
                }
            };

            @Override
            public String getValue(UsedMaterialProxy object) {
                return renderer.render(object.getMaterialCount());
            }
        }, "Material Count");
        paths.add("used_from");
        table.addColumn(new TextColumn<UsedMaterialProxy>() {

            Renderer<ch.unibas.medizin.osce.shared.MaterialUsedFromTypes> renderer = new AbstractRenderer<ch.unibas.medizin.osce.shared.MaterialUsedFromTypes>() {

                public String render(ch.unibas.medizin.osce.shared.MaterialUsedFromTypes obj) {
                    return obj == null ? "" : String.valueOf(obj);
                }
            };

            @Override
            public String getValue(UsedMaterialProxy object) {
                return renderer.render(object.getUsed_from());
            }
        }, "Used_from");
        paths.add("standardizedRole");
        table.addColumn(new TextColumn<UsedMaterialProxy>() {

            Renderer<ch.unibas.medizin.osce.client.managed.request.StandardizedRoleProxy> renderer = ch.unibas.medizin.osce.client.managed.ui.StandardizedRoleProxyRenderer.instance();

            @Override
            public String getValue(UsedMaterialProxy object) {
                return renderer.render(object.getStandardizedRole());
            }
        }, "Standardized Role");
        paths.add("materialList");
        table.addColumn(new TextColumn<UsedMaterialProxy>() {

            Renderer<ch.unibas.medizin.osce.client.managed.request.MaterialListProxy> renderer = ch.unibas.medizin.osce.client.managed.ui.MaterialListProxyRenderer.instance();

            @Override
            public String getValue(UsedMaterialProxy object) {
                return renderer.render(object.getMaterialList());
            }
        }, "Material List");
        paths.add("sort_order");
        table.addColumn(new TextColumn<UsedMaterialProxy>() {

            Renderer<java.lang.Integer> renderer = new AbstractRenderer<java.lang.Integer>() {

                public String render(java.lang.Integer obj) {
                    return obj == null ? "" : String.valueOf(obj);
                }
            };

            @Override
            public String getValue(UsedMaterialProxy object) {
                return renderer.render(object.getSort_order());
            }
        }, "Sort_order");
    }
}