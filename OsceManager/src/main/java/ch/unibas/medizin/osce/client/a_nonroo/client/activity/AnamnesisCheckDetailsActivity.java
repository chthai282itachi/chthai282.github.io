package ch.unibas.medizin.osce.client.a_nonroo.client.activity;

import ch.unibas.medizin.osce.client.a_nonroo.client.place.AnamnesisCheckDetailsPlace;
import ch.unibas.medizin.osce.client.a_nonroo.client.place.AnamnesisCheckPlace;
import ch.unibas.medizin.osce.client.a_nonroo.client.request.OsMaRequestFactory;
import ch.unibas.medizin.osce.client.a_nonroo.client.ui.AnamnesisCheckDetailsView;
import ch.unibas.medizin.osce.client.a_nonroo.client.ui.AnamnesisCheckDetailsViewImpl;
import ch.unibas.medizin.osce.client.managed.request.AnamnesisCheckProxy;
import ch.unibas.medizin.osce.shared.AnamnesisCheckTypes;
import ch.unibas.medizin.osce.shared.Operation;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.requestfactory.shared.ServerFailure;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.view.client.SingleSelectionModel;

public class AnamnesisCheckDetailsActivity extends AbstractActivity implements
AnamnesisCheckDetailsView.Presenter, AnamnesisCheckDetailsView.Delegate {

	private OsMaRequestFactory requests;
	private PlaceController placeController;
	private AcceptsOneWidget widget;
	private AnamnesisCheckDetailsView view;
	private CellTable<AnamnesisCheckProxy> table;
	private SingleSelectionModel<AnamnesisCheckProxy> selectionModel;
	private HandlerRegistration rangeChangeHandler;

	private AnamnesisCheckDetailsPlace place;
	private AnamnesisCheckProxy anamnesisCheckProxy;


	public AnamnesisCheckDetailsActivity(AnamnesisCheckDetailsPlace place, OsMaRequestFactory requests, PlaceController placeController) {
		this.place = place;
		this.requests = requests;
		this.placeController = placeController;
	}

	public void onStop(){

	}
	@SuppressWarnings("deprecation")
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		Log.info("AnamnesisCheckDetailsActivity.start()");
		AnamnesisCheckDetailsView anamnesisCheckDetailsView = new AnamnesisCheckDetailsViewImpl();
		anamnesisCheckDetailsView.setPresenter(this);
		this.widget = panel;
		this.view = anamnesisCheckDetailsView;
		widget.setWidget(anamnesisCheckDetailsView.asWidget());

		view.setDelegate(this);

		requests.find(place.getProxyId()).with("title").fire(new Receiver<Object>() {

			public void onFailure(ServerFailure error) {
				Log.error(error.getMessage());
			}

			@Override
			public void onSuccess(Object response) {
				if (response instanceof AnamnesisCheckProxy) {
					Log.info(((AnamnesisCheckProxy) response).getId().toString());
					final AnamnesisCheckProxy anamnesisCheckProxy = (AnamnesisCheckProxy) response;
					int previousSortOrder = -1;
					if (anamnesisCheckProxy.getSort_order() != null) {
						previousSortOrder = anamnesisCheckProxy.getSort_order() - 1;
					}
					if (anamnesisCheckProxy.getType() != AnamnesisCheckTypes.QUESTION_TITLE) {
						requests.anamnesisCheckRequestNonRoo().findAnamnesisChecksBySortOder(previousSortOrder).fire(new Receiver<AnamnesisCheckProxy>() {
							public void onFailure(ServerFailure error) {
								Log.error(error.getMessage());
							}

							@Override
							public void onSuccess(AnamnesisCheckProxy response) {
								String previousAnamnesisCheckText = "";
								if (response != null) {
									previousAnamnesisCheckText = response.getText();
								}
								init(anamnesisCheckProxy, previousAnamnesisCheckText);

							}
						});
					} else {
						if(anamnesisCheckProxy.getSort_order()!=null){
						requests.anamnesisCheckRequestNonRoo().findPreviousTitleBySortOder(anamnesisCheckProxy.getSort_order()).fire(new Receiver<AnamnesisCheckProxy>() {
							public void onFailure(ServerFailure error) {
								Log.error(error.getMessage());
							}

							@Override
							public void onSuccess(AnamnesisCheckProxy response) {
								String previousAnamnesisCheckText = "";
								if (response != null) {
									previousAnamnesisCheckText = response.getText();
								}
								init(anamnesisCheckProxy, previousAnamnesisCheckText);								
							}
						});
						}else{
							init(anamnesisCheckProxy, "");
						}
					}

				}
			}
		});
	}

	private void init(AnamnesisCheckProxy anamnesisCheckProxy, String previousAnamnesisCheckText) {
		this.anamnesisCheckProxy = anamnesisCheckProxy;
		view.setValue(anamnesisCheckProxy, previousAnamnesisCheckText);
	}

	@Override
	public void goTo(Place place) {
		placeController.goTo(place);
	}

	@Override
	public void editClicked() {
		Log.info("edit clicked");
		if(anamnesisCheckProxy!=null){
			goTo(new AnamnesisCheckDetailsPlace(anamnesisCheckProxy.stableId(),
				Operation.EDIT));
		}
	}

	@Override
	public void deleteClicked() {
		if (!Window.confirm("Really delete this entry? You cannot undo this change.")) {
			return;
		}
		requests.anamnesisCheckRequest().remove().using(anamnesisCheckProxy).fire(new Receiver<Void>() {

			public void onSuccess(Void ignore) {
//				if (widget == null) {
//					return;
//				}
				if(widget !=null){
					widget.setWidget(null);
				}
				placeController.goTo(new AnamnesisCheckPlace("AnamnesisCheckPlace!DELETED"));
			}
		});
	}
}
