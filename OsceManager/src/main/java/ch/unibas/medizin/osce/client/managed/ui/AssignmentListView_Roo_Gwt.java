// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.

package ch.unibas.medizin.osce.client.managed.ui;

import ch.unibas.medizin.osce.client.managed.request.AssignmentProxy;
import ch.unibas.medizin.osce.client.managed.request.DoctorProxy;
import ch.unibas.medizin.osce.client.managed.request.OsceDayProxy;
import ch.unibas.medizin.osce.client.managed.request.OscePostRoomProxy;
import ch.unibas.medizin.osce.client.managed.request.PatientInRoleProxy;
import ch.unibas.medizin.osce.client.managed.request.StudentProxy;
import ch.unibas.medizin.osce.client.scaffold.place.AbstractProxyListView;
import ch.unibas.medizin.osce.shared.AssignmentTypes;
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

public abstract class AssignmentListView_Roo_Gwt extends AbstractProxyListView<AssignmentProxy> {

    @UiField
    CellTable<AssignmentProxy> table;

    protected Set<String> paths = new HashSet<String>();

    public void init() {
        paths.add("id");
        table.addColumn(new TextColumn<AssignmentProxy>() {

            Renderer<java.lang.Long> renderer = new AbstractRenderer<java.lang.Long>() {

                public String render(java.lang.Long obj) {
                    return obj == null ? "" : String.valueOf(obj);
                }
            };

            @Override
            public String getValue(AssignmentProxy object) {
                return renderer.render(object.getId());
            }
        }, "Id");
        paths.add("version");
        table.addColumn(new TextColumn<AssignmentProxy>() {

            Renderer<java.lang.Integer> renderer = new AbstractRenderer<java.lang.Integer>() {

                public String render(java.lang.Integer obj) {
                    return obj == null ? "" : String.valueOf(obj);
                }
            };

            @Override
            public String getValue(AssignmentProxy object) {
                return renderer.render(object.getVersion());
            }
        }, "Version");
        paths.add("type");
        table.addColumn(new TextColumn<AssignmentProxy>() {

            Renderer<ch.unibas.medizin.osce.shared.AssignmentTypes> renderer = new AbstractRenderer<ch.unibas.medizin.osce.shared.AssignmentTypes>() {

                public String render(ch.unibas.medizin.osce.shared.AssignmentTypes obj) {
                    return obj == null ? "" : String.valueOf(obj);
                }
            };

            @Override
            public String getValue(AssignmentProxy object) {
                return renderer.render(object.getType());
            }
        }, "Type");
        paths.add("slotNumber");
        table.addColumn(new TextColumn<AssignmentProxy>() {

            Renderer<java.lang.Integer> renderer = new AbstractRenderer<java.lang.Integer>() {

                public String render(java.lang.Integer obj) {
                    return obj == null ? "" : String.valueOf(obj);
                }
            };

            @Override
            public String getValue(AssignmentProxy object) {
                return renderer.render(object.getSlotNumber());
            }
        }, "Slot Number");
        paths.add("timeStart");
        table.addColumn(new TextColumn<AssignmentProxy>() {

            Renderer<java.util.Date> renderer = new DateTimeFormatRenderer(DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_SHORT));

            @Override
            public String getValue(AssignmentProxy object) {
                return renderer.render(object.getTimeStart());
            }
        }, "Time Start");
        paths.add("timeEnd");
        table.addColumn(new TextColumn<AssignmentProxy>() {

            Renderer<java.util.Date> renderer = new DateTimeFormatRenderer(DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_SHORT));

            @Override
            public String getValue(AssignmentProxy object) {
                return renderer.render(object.getTimeEnd());
            }
        }, "Time End");
        paths.add("osceDay");
        table.addColumn(new TextColumn<AssignmentProxy>() {

            Renderer<ch.unibas.medizin.osce.client.managed.request.OsceDayProxy> renderer = ch.unibas.medizin.osce.client.managed.ui.OsceDayProxyRenderer.instance();

            @Override
            public String getValue(AssignmentProxy object) {
                return renderer.render(object.getOsceDay());
            }
        }, "Osce Day");
        paths.add("oscePostRoom");
        table.addColumn(new TextColumn<AssignmentProxy>() {

            Renderer<ch.unibas.medizin.osce.client.managed.request.OscePostRoomProxy> renderer = ch.unibas.medizin.osce.client.managed.ui.OscePostRoomProxyRenderer.instance();

            @Override
            public String getValue(AssignmentProxy object) {
                return renderer.render(object.getOscePostRoom());
            }
        }, "Osce Post Room");
        paths.add("student");
        table.addColumn(new TextColumn<AssignmentProxy>() {

            Renderer<ch.unibas.medizin.osce.client.managed.request.StudentProxy> renderer = ch.unibas.medizin.osce.client.managed.ui.StudentProxyRenderer.instance();

            @Override
            public String getValue(AssignmentProxy object) {
                return renderer.render(object.getStudent());
            }
        }, "Student");
        paths.add("patientInRole");
        table.addColumn(new TextColumn<AssignmentProxy>() {

            Renderer<ch.unibas.medizin.osce.client.managed.request.PatientInRoleProxy> renderer = ch.unibas.medizin.osce.client.managed.ui.PatientInRoleProxyRenderer.instance();

            @Override
            public String getValue(AssignmentProxy object) {
                return renderer.render(object.getPatientInRole());
            }
        }, "Patient In Role");
        paths.add("examiner");
        table.addColumn(new TextColumn<AssignmentProxy>() {

            Renderer<ch.unibas.medizin.osce.client.managed.request.DoctorProxy> renderer = ch.unibas.medizin.osce.client.managed.ui.DoctorProxyRenderer.instance();

            @Override
            public String getValue(AssignmentProxy object) {
                return renderer.render(object.getExaminer());
            }
        }, "Examiner");
    }
}
