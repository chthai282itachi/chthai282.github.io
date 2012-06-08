package ch.unibas.medizin.osce.client.managed.activity;

import ch.unibas.medizin.osce.client.managed.request.ApplicationRequestFactory;
import ch.unibas.medizin.osce.client.managed.request.FileProxy;
import ch.unibas.medizin.osce.client.managed.request.StandardizedRoleProxy;
import ch.unibas.medizin.osce.client.scaffold.activity.IsScaffoldMobileActivity;
import ch.unibas.medizin.osce.client.scaffold.place.ProxyDetailsView;
import ch.unibas.medizin.osce.client.scaffold.place.ProxyListPlace;
import ch.unibas.medizin.osce.client.scaffold.place.ProxyPlace;
import ch.unibas.medizin.osce.client.scaffold.place.ProxyPlace.Operation;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.requestfactory.shared.EntityProxy;
import com.google.gwt.requestfactory.shared.EntityProxyId;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.requestfactory.shared.Request;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import java.util.Set;

public class FileDetailsActivity extends FileDetailsActivity_Roo_Gwt {

    private final PlaceController placeController;

    private final ProxyDetailsView<FileProxy> view;

    private AcceptsOneWidget display;

    public FileDetailsActivity(EntityProxyId<ch.unibas.medizin.osce.client.managed.request.FileProxy> proxyId, ApplicationRequestFactory requests, PlaceController placeController, ProxyDetailsView<ch.unibas.medizin.osce.client.managed.request.FileProxy> view) {
        this.placeController = placeController;
        this.proxyId = proxyId;
        this.requests = requests;
        view.setDelegate(this);
        this.view = view;
    }

    public void deleteClicked() {
        if (!view.confirm("Really delete this entry? You cannot undo this change.")) {
            return;
        }
        requests.fileRequest().remove().using(view.getValue()).fire(new Receiver<Void>() {

            public void onSuccess(Void ignore) {
                if (display == null) {
                    return;
                }
                placeController.goTo(getBackButtonPlace());
            }
        });
    }

    public void editClicked() {
        placeController.goTo(getEditButtonPlace());
    }

    public Place getBackButtonPlace() {
        return new ProxyListPlace(FileProxy.class);
    }

    public String getBackButtonText() {
        return "Back";
    }

    public Place getEditButtonPlace() {
        return new ProxyPlace(view.getValue().stableId(), Operation.EDIT);
    }

    public String getTitleText() {
        return "View File";
    }

    public boolean hasEditButton() {
        return true;
    }

    public void onCancel() {
        onStop();
    }

    public void onStop() {
        display = null;
    }

    public void start(AcceptsOneWidget displayIn, EventBus eventBus) {
        this.display = displayIn;
        Receiver<EntityProxy> callback = new Receiver<EntityProxy>() {

            public void onSuccess(EntityProxy proxy) {
                if (proxy == null) {
                    placeController.goTo(getBackButtonPlace());
                    return;
                }
                if (display == null) {
                    return;
                }
                view.setValue((FileProxy) proxy);
                display.setWidget(view);
            }
        };
        find(callback);
    }
}
