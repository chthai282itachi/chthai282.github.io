// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.

package ch.unibas.medizin.osce.client.managed.activity;

import ch.unibas.medizin.osce.client.managed.activity.StudentEditActivityWrapper.View;
import ch.unibas.medizin.osce.client.managed.request.ApplicationRequestFactory;
import ch.unibas.medizin.osce.client.managed.request.StudentOscesProxy;
import ch.unibas.medizin.osce.client.managed.request.StudentProxy;
import ch.unibas.medizin.osce.client.managed.ui.StudentOscesSetEditor;
import ch.unibas.medizin.osce.client.scaffold.activity.IsScaffoldMobileActivity;
import ch.unibas.medizin.osce.client.scaffold.place.ProxyEditView;
import ch.unibas.medizin.osce.client.scaffold.place.ProxyListPlace;
import ch.unibas.medizin.osce.client.scaffold.place.ProxyPlace;
import ch.unibas.medizin.osce.shared.Gender;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.requestfactory.shared.EntityProxyId;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public abstract class StudentEditActivityWrapper_Roo_Gwt implements Activity, IsScaffoldMobileActivity {

    protected Activity wrapped;

    protected View<?> view;

    protected ApplicationRequestFactory requests;

    @Override
    public void start(AcceptsOneWidget display, EventBus eventBus) {
        view.setGenderPickerValues(Arrays.asList(Gender.values()));
        view.setStudentOscesPickerValues(Collections.<StudentOscesProxy>emptyList());
        requests.studentOscesRequest().findStudentOscesEntries(0, 50).with(ch.unibas.medizin.osce.client.managed.ui.StudentOscesProxyRenderer.instance().getPaths()).fire(new Receiver<List<StudentOscesProxy>>() {

            public void onSuccess(List<StudentOscesProxy> response) {
                List<StudentOscesProxy> values = new ArrayList<StudentOscesProxy>();
                values.add(null);
                values.addAll(response);
                view.setStudentOscesPickerValues(values);
            }
        });
        wrapped.start(display, eventBus);
    }

    public interface View_Roo_Gwt<V extends ch.unibas.medizin.osce.client.scaffold.place.ProxyEditView<ch.unibas.medizin.osce.client.managed.request.StudentProxy, V>> extends ProxyEditView<StudentProxy, V> {

        void setGenderPickerValues(Collection<Gender> values);

        void setStudentOscesPickerValues(Collection<StudentOscesProxy> values);
    }
}
