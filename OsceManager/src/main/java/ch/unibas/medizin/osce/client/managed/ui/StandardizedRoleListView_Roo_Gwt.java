// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.

package ch.unibas.medizin.osce.client.managed.ui;

import ch.unibas.medizin.osce.client.managed.request.AdvancedSearchCriteriaProxy;
import ch.unibas.medizin.osce.client.managed.request.CheckListProxy;
import ch.unibas.medizin.osce.client.managed.request.KeywordProxy;
import ch.unibas.medizin.osce.client.managed.request.RoleParticipantProxy;
import ch.unibas.medizin.osce.client.managed.request.RoleTopicProxy;
import ch.unibas.medizin.osce.client.managed.request.SimpleSearchCriteriaProxy;
import ch.unibas.medizin.osce.client.managed.request.StandardizedRoleProxy;
import ch.unibas.medizin.osce.client.scaffold.place.AbstractProxyListView;
import ch.unibas.medizin.osce.shared.RoleTypes;
import ch.unibas.medizin.osce.shared.StudyYears;
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

public abstract class StandardizedRoleListView_Roo_Gwt extends AbstractProxyListView<StandardizedRoleProxy> {

    @UiField
    CellTable<StandardizedRoleProxy> table;

    protected Set<String> paths = new HashSet<String>();

    public void init() {
        paths.add("id");
        table.addColumn(new TextColumn<StandardizedRoleProxy>() {

            Renderer<java.lang.Long> renderer = new AbstractRenderer<java.lang.Long>() {

                public String render(java.lang.Long obj) {
                    return obj == null ? "" : String.valueOf(obj);
                }
            };

            @Override
            public String getValue(StandardizedRoleProxy object) {
                return renderer.render(object.getId());
            }
        }, "Id");
        paths.add("version");
        table.addColumn(new TextColumn<StandardizedRoleProxy>() {

            Renderer<java.lang.Integer> renderer = new AbstractRenderer<java.lang.Integer>() {

                public String render(java.lang.Integer obj) {
                    return obj == null ? "" : String.valueOf(obj);
                }
            };

            @Override
            public String getValue(StandardizedRoleProxy object) {
                return renderer.render(object.getVersion());
            }
        }, "Version");
        paths.add("shortName");
        table.addColumn(new TextColumn<StandardizedRoleProxy>() {

            Renderer<java.lang.String> renderer = new AbstractRenderer<java.lang.String>() {

                public String render(java.lang.String obj) {
                    return obj == null ? "" : String.valueOf(obj);
                }
            };

            @Override
            public String getValue(StandardizedRoleProxy object) {
                return renderer.render(object.getShortName());
            }
        }, "Short Name");
        paths.add("longName");
        table.addColumn(new TextColumn<StandardizedRoleProxy>() {

            Renderer<java.lang.String> renderer = new AbstractRenderer<java.lang.String>() {

                public String render(java.lang.String obj) {
                    return obj == null ? "" : String.valueOf(obj);
                }
            };

            @Override
            public String getValue(StandardizedRoleProxy object) {
                return renderer.render(object.getLongName());
            }
        }, "Long Name");
        paths.add("caseDescription");
        table.addColumn(new TextColumn<StandardizedRoleProxy>() {

            Renderer<java.lang.String> renderer = new AbstractRenderer<java.lang.String>() {

                public String render(java.lang.String obj) {
                    return obj == null ? "" : String.valueOf(obj);
                }
            };

            @Override
            public String getValue(StandardizedRoleProxy object) {
                return renderer.render(object.getCaseDescription());
            }
        }, "Case Description");
        paths.add("roleScript");
        table.addColumn(new TextColumn<StandardizedRoleProxy>() {

            Renderer<java.lang.String> renderer = new AbstractRenderer<java.lang.String>() {

                public String render(java.lang.String obj) {
                    return obj == null ? "" : String.valueOf(obj);
                }
            };

            @Override
            public String getValue(StandardizedRoleProxy object) {
                return renderer.render(object.getRoleScript());
            }
        }, "Role Script");
        paths.add("roleType");
        table.addColumn(new TextColumn<StandardizedRoleProxy>() {

            Renderer<ch.unibas.medizin.osce.shared.RoleTypes> renderer = new AbstractRenderer<ch.unibas.medizin.osce.shared.RoleTypes>() {

                public String render(ch.unibas.medizin.osce.shared.RoleTypes obj) {
                    return obj == null ? "" : String.valueOf(obj);
                }
            };

            @Override
            public String getValue(StandardizedRoleProxy object) {
                return renderer.render(object.getRoleType());
            }
        }, "Role Type");
        paths.add("active");
        table.addColumn(new TextColumn<StandardizedRoleProxy>() {

            Renderer<java.lang.Boolean> renderer = new AbstractRenderer<java.lang.Boolean>() {

                public String render(java.lang.Boolean obj) {
                    return obj == null ? "" : String.valueOf(obj);
                }
            };

            @Override
            public String getValue(StandardizedRoleProxy object) {
                return renderer.render(object.getActive());
            }
        }, "Active");
        paths.add("roleTopic");
        table.addColumn(new TextColumn<StandardizedRoleProxy>() {

            Renderer<ch.unibas.medizin.osce.client.managed.request.RoleTopicProxy> renderer = ch.unibas.medizin.osce.client.managed.ui.RoleTopicProxyRenderer.instance();

            @Override
            public String getValue(StandardizedRoleProxy object) {
                return renderer.render(object.getRoleTopic());
            }
        }, "Role Topic");
        paths.add("roleParticipants");
        table.addColumn(new TextColumn<StandardizedRoleProxy>() {

            Renderer<java.util.Set> renderer = ch.unibas.medizin.osce.client.scaffold.place.CollectionRenderer.of(ch.unibas.medizin.osce.client.managed.ui.RoleParticipantProxyRenderer.instance());

            @Override
            public String getValue(StandardizedRoleProxy object) {
                return renderer.render(object.getRoleParticipants());
            }
        }, "Role Participants");
        paths.add("previousVersion");
        table.addColumn(new TextColumn<StandardizedRoleProxy>() {

            Renderer<ch.unibas.medizin.osce.client.managed.request.StandardizedRoleProxy> renderer = ch.unibas.medizin.osce.client.managed.ui.StandardizedRoleProxyRenderer.instance();

            @Override
            public String getValue(StandardizedRoleProxy object) {
                return renderer.render(object.getPreviousVersion());
            }
        }, "Previous Version");
        paths.add("studyYear");
        table.addColumn(new TextColumn<StandardizedRoleProxy>() {

            Renderer<ch.unibas.medizin.osce.shared.StudyYears> renderer = new AbstractRenderer<ch.unibas.medizin.osce.shared.StudyYears>() {

                public String render(ch.unibas.medizin.osce.shared.StudyYears obj) {
                    return obj == null ? "" : String.valueOf(obj);
                }
            };

            @Override
            public String getValue(StandardizedRoleProxy object) {
                return renderer.render(object.getStudyYear());
            }
        }, "Study Year");
        paths.add("mainVersion");
        table.addColumn(new TextColumn<StandardizedRoleProxy>() {

            Renderer<java.lang.Integer> renderer = new AbstractRenderer<java.lang.Integer>() {

                public String render(java.lang.Integer obj) {
                    return obj == null ? "" : String.valueOf(obj);
                }
            };

            @Override
            public String getValue(StandardizedRoleProxy object) {
                return renderer.render(object.getMainVersion());
            }
        }, "Main Version");
        paths.add("subVersion");
        table.addColumn(new TextColumn<StandardizedRoleProxy>() {

            Renderer<java.lang.Integer> renderer = new AbstractRenderer<java.lang.Integer>() {

                public String render(java.lang.Integer obj) {
                    return obj == null ? "" : String.valueOf(obj);
                }
            };

            @Override
            public String getValue(StandardizedRoleProxy object) {
                return renderer.render(object.getSubVersion());
            }
        }, "Sub Version");
        paths.add("keywords");
        table.addColumn(new TextColumn<StandardizedRoleProxy>() {

            Renderer<java.util.Set> renderer = ch.unibas.medizin.osce.client.scaffold.place.CollectionRenderer.of(ch.unibas.medizin.osce.client.managed.ui.KeywordProxyRenderer.instance());

            @Override
            public String getValue(StandardizedRoleProxy object) {
                return renderer.render(object.getKeywords());
            }
        }, "Keywords");
        paths.add("advancedSearchCriteria");
        table.addColumn(new TextColumn<StandardizedRoleProxy>() {

            Renderer<java.util.Set> renderer = ch.unibas.medizin.osce.client.scaffold.place.CollectionRenderer.of(ch.unibas.medizin.osce.client.managed.ui.AdvancedSearchCriteriaProxyRenderer.instance());

            @Override
            public String getValue(StandardizedRoleProxy object) {
                return renderer.render(object.getAdvancedSearchCriteria());
            }
        }, "Advanced Search Criteria");
        paths.add("simpleSearchCriteria");
        table.addColumn(new TextColumn<StandardizedRoleProxy>() {

            Renderer<java.util.Set> renderer = ch.unibas.medizin.osce.client.scaffold.place.CollectionRenderer.of(ch.unibas.medizin.osce.client.managed.ui.SimpleSearchCriteriaProxyRenderer.instance());

            @Override
            public String getValue(StandardizedRoleProxy object) {
                return renderer.render(object.getSimpleSearchCriteria());
            }
        }, "Simple Search Criteria");
        paths.add("checkList");
        table.addColumn(new TextColumn<StandardizedRoleProxy>() {

            Renderer<ch.unibas.medizin.osce.client.managed.request.CheckListProxy> renderer = ch.unibas.medizin.osce.client.managed.ui.CheckListProxyRenderer.instance();

            @Override
            public String getValue(StandardizedRoleProxy object) {
                return renderer.render(object.getCheckList());
            }
        }, "Check List");
    }
}