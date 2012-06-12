package ch.unibas.medizin.osce.client.a_nonroo.client.activity;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import ch.unibas.medizin.osce.client.a_nonroo.client.place.RoleDetailsPlace;
import ch.unibas.medizin.osce.client.a_nonroo.client.place.RolePlace;
import ch.unibas.medizin.osce.client.a_nonroo.client.request.OsMaRequestFactory;
import ch.unibas.medizin.osce.client.a_nonroo.client.ui.role.RoleEditCheckListSubView;
import ch.unibas.medizin.osce.client.a_nonroo.client.ui.role.RoleEditCheckListSubViewImpl;
import ch.unibas.medizin.osce.client.a_nonroo.client.ui.role.RoleEditView;
import ch.unibas.medizin.osce.client.a_nonroo.client.ui.role.RoleEditViewImpl;
import ch.unibas.medizin.osce.client.managed.request.CheckListProxy;
import ch.unibas.medizin.osce.client.managed.request.CheckListRequest;
import ch.unibas.medizin.osce.client.managed.request.RoleTopicProxy;
import ch.unibas.medizin.osce.client.managed.request.StandardizedRoleProxy;
import ch.unibas.medizin.osce.client.managed.request.StandardizedRoleRequest;
import ch.unibas.medizin.osce.shared.Operation;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.requestfactory.client.RequestFactoryEditorDriver;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.requestfactory.shared.ServerFailure;
import com.google.gwt.requestfactory.shared.Violation;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class RoleEditActivity extends AbstractActivity implements RoleEditView.Presenter, RoleEditView.Delegate,RoleEditCheckListSubView.Delegate,RoleEditCheckListSubView.Presenter {

	private OsMaRequestFactory requests;
	private PlaceController placeController;
	private AcceptsOneWidget widget;
	private RoleEditView view;
	private RoleEditCheckListSubView checkListView;//spec
	
	private RoleDetailsPlace place;	
	public static RoleTopicProxy roleTopic;
	public static RoleTopicProxy oldRoleTopic;
	
	//vigna
	public static RoleActivity roleActivity;

	private StandardizedRoleRequest standardizedRoleRequest;

	private RequestFactoryEditorDriver<StandardizedRoleProxy, RoleEditViewImpl> editorDriver;
	
	private RequestFactoryEditorDriver<CheckListProxy, RoleEditCheckListSubViewImpl> checkListEditorDriver;//spec
	
	
	
	
	
	private RequestFactoryEditorDriver<StandardizedRoleProxy, RoleEditViewImpl> majoreditorDriver;
	
	
	private StandardizedRoleProxy standardizedRole;
	private CheckListProxy checkListProxy;//spec
	
	private StandardizedRoleProxy  proxy;//spec
	private StandardizedRoleProxy  oldProxy;//spec
	private CheckListProxy  checkListProxy1;//spec
	
	private CheckListProxy checkList;//spec
	
	private StandardizedRoleRequest majorRequest;
	private CheckListRequest majorCheckListRequest;//spec
	private StandardizedRoleRequest majorRequest1;
	
	public StandardizedRoleProxy getProxy() {
		return proxy;
	}

	public void setProxy(StandardizedRoleProxy proxy) {
		this.proxy = proxy;
	}

	
	
	public StandardizedRoleProxy getStandardizedRole() {
		return standardizedRole;
	}

	public void setStandardizedRole(StandardizedRoleProxy standardizedRole) {
		this.standardizedRole = standardizedRole;
	}


	private boolean save;
	
	

	public RoleEditActivity(RoleDetailsPlace place,	OsMaRequestFactory requests, PlaceController placeController) 
	{
		Log.info("==Call RoleEditActivity==");
		this.place = place;
		this.requests = requests;
		this.placeController = placeController;
	}

	public RoleEditActivity(RoleDetailsPlace place,
			OsMaRequestFactory requests, PlaceController placeController,
			Operation operation) {
		this.place = place;
		this.requests = requests;
		this.placeController = placeController;
		// this.operation=operation;
	}

	public void onStop() {

	}

	@Override
	public String mayStop() {
		if (!save && changed())
			return "Changes will be discarded!";
		else
			return null;
	}

	// use this to check if some value has changed since editing has started
	private boolean changed() {
		return editorDriver != null && editorDriver.flush().isChanged();
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		Log.info("==Call RoleEditActivity start();==");		
		this.view = new RoleEditViewImpl();

		
		this.widget = panel;
	
		//spec start
		this.checkListView=new RoleEditCheckListSubViewImpl();
		editorDriver = view.createEditorDriver();
		this.view.getRoleEditCheckListPanel().add(this.checkListView);		
			checkListEditorDriver=checkListView.createCheckListEditorDriver();//spec
			checkListView.setDelegate(this);//spec
				
	//spec end
		view.setDelegate(this);
		
		
	//	view.setDelegate(this);
		
		
	

		if (this.place.getOperation() == Operation.EDIT) {
			Log.info("edit");
			//spec start
			requests.find(place.getProxyId()).with("standardizedRoles").with("checkList").with("roleTopic")
					.fire(new Receiver<Object>() {

						public void onFailure(ServerFailure error) {
							Log.error(error.getMessage());
						}

						@Override
						public void onSuccess(Object response) {
							if (response instanceof StandardizedRoleProxy) {
								Log.info(((StandardizedRoleProxy) response)
										.getShortName());
								// init((StandardizedPatientProxy) response);
								standardizedRole = (StandardizedRoleProxy) response;
								checkListProxy=((StandardizedRoleProxy) response).getCheckList();//spec
								//checkListProxy=standardizedRole.getCheckList();
								view.setStandardizedRoleProxy(standardizedRole);
								requests.roleTopicRequestNonRoo().findAllRoleTopic(Integer.parseInt(standardizedRole.getRoleTopic().getId().toString())).fire(new RoleTopicRecevier());
								init();
							}
						}
					});
			
			
		} else {
			Log.info("new StandardizedRole");
			init();
		}
		widget.setWidget(view.asWidget());
	}

	
	private class RoleTopicRecevier extends Receiver<List<RoleTopicProxy>> {
		@Override
		public void onSuccess(List<RoleTopicProxy> response) {
		//	filterView.setSpecialisationBoxValues(response);
			System.out.println("roletopic success");
			view.setRoleTopicListBoxValues(response);
		}
	}
	private void init() {

		StandardizedRoleRequest request = requests.standardizedRoleRequest();
		CheckListRequest checkListRequest=requests.checkListRequest();//spec
	
		

		if (standardizedRole == null) {
			System.out.println("====================standardizedRole=null in RoleEditActivity==================");
			standardizedRole = request.create(StandardizedRoleProxy.class);
			checkListProxy=request.create(CheckListProxy.class);//spec
			standardizedRole.setCheckList(checkListProxy);//spec
			standardizedRole.setSubVersion(1);
			standardizedRole.setMainVersion(1);
			standardizedRole.setActive(true);
			checkListProxy.setVersion(0);//spec
			view.setEditTitle(false);
			requests.roleTopicRequest().findAllRoleTopics().fire(new RoleTopicRecevier());
			//requests.roleTopicRequestNonRoo().findAllRoleTopic(Integer.parseInt(standardizedRole.getRoleTopic().getId().toString())).fire(new RoleTopicRecevier());
			Log.info("create");
		} else {
			//set TabText when edit clicked
			view.getRoleDetailPanel().getTabBar().setTabText(RoleDetailsActivity.getSelecTab(), standardizedRole.getShortName());
			System.out
					.println("====================standardizedRole not null in RoleEditActivity=============");
			standardizedRole = request.edit(standardizedRole);
			//spec start
			//checkListProxy=request.edit(standardizedRole.getCheckList());
		
			//spec end
		
			view.setEditTitle(true);
			Log.info("edit");
		}

		Log.info("edit");
		editorDriver.edit(standardizedRole, request);
//spec start
		checkListEditorDriver.edit(checkListProxy, request);//spec
//spec end
		
	
	//	checkListRequest.persist().using(checkListProxy);//spec
		
		
		Log.info(" persist");
		request.persist().using(standardizedRole);
		

		Log.debug("Create für: " + standardizedRole.getLongName());
	}

	@Override
	public void goTo(Place place) {
		placeController.goTo(place);
	}

	@Override
	public void cancelClicked() 
	{
		
		
		
		if (this.place.getOperation() == Operation.EDIT)
			goTo(new RoleDetailsPlace(roleTopic.stableId(),	Operation.DETAILS));	
			//placeController.goTo(new RoleDetailsPlace(standardizedRole.stableId(), Operation.DETAILS));
		else
			placeController.goTo(new RolePlace("RolePlace!CANCEL"));
	}

	@Override
	public void saveClicked()
	{
		
		Log.info("saveClicked");
		
		Log.info("Long Name "+standardizedRole.getLongName());
		
		Log.info("prev version"+standardizedRole.getPreviousVersion());
		
		Log.info("Sub version"+standardizedRole.getSubVersion());
		
	//	standardizedRole.setRoleTopic(roleTopic);
		//checkListProxy.setTitle("aaa");
		//checkListProxy.setTitle(((RoleEditCheckListSubViewImpl)checkListView).title.getValue());//spec
		
	//	standardizedRole.setCheckList(checkListProxy);//spec
		Log.info("Role Topic"+standardizedRole.getRoleTopic().getName());
		
		
		standardizedRole.setRoleTopic(((RoleEditViewImpl)view).roleTopic.getValue());
//		checkListProxy.setTitle(((RoleEditCheckListSubViewImpl)checkListView).title.getValue());//spec
//		standardizedRole.setCheckList(checkListProxy);//spec
		
		// return '0' means minor clicked and '1' means Major Button Clicked
		
		if(this.place.getOperation() == Operation.EDIT)
		{
			 checkListProxy= standardizedRole.getCheckList();//spec

				checkListProxy.setTitle(((RoleEditCheckListSubViewImpl)checkListView).title.getValue());//spec
				standardizedRole.setCheckList(checkListProxy);//spec
				System.out.println("Checklist----"+checkListProxy.getTitle());
				
				 majorRequest = requests.standardizedRoleRequest();
				 proxy= majorRequest.create(StandardizedRoleProxy.class);
				 
				
				 //spec
				 
				 /*majorCheckListRequest = requests.checkListRequest();
				 checkListProxy= majorCheckListRequest.create(CheckListProxy.class);*/
				 
				 checkListProxy= standardizedRole.getCheckList();//spec
				// checkListProxy.setTitle("ccc");//spec
				//	checkListProxy.setVersion(0);//spec
				 //spec
				 checkListProxy.setTitle(((RoleEditCheckListSubViewImpl)checkListView).title.getValue());//spec
				 proxy.setRoleTopic(roleTopic);
				 //copy(standardizedRole);
				 proxy.setActive(((RoleEditViewImpl)view).active.getValue());
				 proxy.setShortName(((RoleEditViewImpl)view).shortName.getValue());
					proxy.setLongName(((RoleEditViewImpl)view).longName.getValue());
					proxy.setStudyYear(((RoleEditViewImpl)view).studyYear.getValue());
					proxy.setRoleType(((RoleEditViewImpl)view).roleType.getValue());
					
					proxy.setPreviousVersion(standardizedRole);
					proxy.setMainVersion(standardizedRole.getMainVersion()+1);
					proxy.setSubVersion(1);
					
					proxy.setCheckList(checkListProxy);//spec
					proxy.setRoleTopic(((RoleEditViewImpl)view).roleTopic.getValue());
					

					majorRequest1 = this.requests.standardizedRoleRequest();
					oldProxy= majorRequest1.create(StandardizedRoleProxy.class);
					oldProxy.setActive(((RoleEditViewImpl)view).active.getValue());
				//	oldProxy.setActive(false);
					oldProxy.setShortName(((RoleEditViewImpl)view).shortName.getValue());
					oldProxy.setLongName(((RoleEditViewImpl)view).longName.getValue());
					oldProxy.setStudyYear(((RoleEditViewImpl)view).studyYear.getValue());
					oldProxy.setRoleType(((RoleEditViewImpl)view).roleType.getValue());
						
						//proxy1.setPreviousVersion(standardizedRole);
						//proxy1.setMainVersion(standardizedRole.getMainVersion()+1);
					oldProxy.setMainVersion(1);
					oldProxy.setSubVersion(1);
						
					oldProxy.setCheckList(checkListProxy);//spec
					oldProxy.setRoleTopic(roleTopic);
			view.getMajorMinorChange();
			
			
			
		}
		else
		{
			 checkListProxy= standardizedRole.getCheckList();//spec

			checkListProxy.setTitle(((RoleEditCheckListSubViewImpl)checkListView).title.getValue());//spec
			standardizedRole.setCheckList(checkListProxy);//spec
			System.out.println("Checklist----"+checkListProxy.getTitle());
			save();
		}
		
		
		
	}
	
	
	public void copy(StandardizedRoleProxy srp)
	{
		proxy.setActive(srp.getActive());
		proxy.setShortName(srp.getShortName());
		proxy.setLongName(srp.getLongName());
		proxy.setStudyYear(srp.getStudyYear());
		proxy.setRoleType(srp.getRoleType());
		proxy.setPreviousVersion(srp);
		proxy.setMainVersion(srp.getMainVersion()+1);
		proxy.setSubVersion(1);
		
	//	checkListProxy.setTitle(srp.getCheckList().getTitle());//spec 
	//	proxy.setCheckList(checkListProxy);//spec
		
		//previous inactive
		
	}
	
	
	public void finalSave()
	{
		editorDriver.flush().fire(new Receiver<Void>() {
			
			public void onFailure(ServerFailure error) {
				Log.error(error.getMessage());

			}

			@Override
			public void onViolation(Set<Violation> errors) {
				Iterator<Violation> iter = errors.iterator();
				String message = "";
				while (iter.hasNext()) {
					message += iter.next().getMessage() + "<br>";
				}
				Log.warn(" in Role -" + message);
			}

			@Override
			public void onSuccess(Void response) {
				Log.info("Role successfully saved.");

				save = true;
				
			
				RoleDetailsActivity.setSelecTab(findTabIndex());
				roleActivity.initSearch();
				goTo(new RoleDetailsPlace(roleTopic.stableId(),	Operation.DETAILS));				
				
		}
		});
		
		
	}
	
	public void save()
	{
		
		
		if(((RoleEditViewImpl)view).roleTopic.getValue().getId()!=roleTopic.getId())
		{
			 
		 System.out.println("not Same");
			
			System.out.println("role---"+oldProxy);
			
			
		
		
		majorRequest1.persist().using(oldProxy).fire(new Receiver<Void>() {

			@Override
			public void onSuccess(Void response) {
				// TODO Auto-generated method stub
				
				Log.info("new Role successfully saved.");
				
				finalSave();
				
				
				
			}
			
			public void onFailure(ServerFailure error) {
				System.out.println("Error");
				Log.error(error.getMessage());

			}

			@Override
			public void onViolation(Set<Violation> errors) {
				System.out.println("violate");
				Iterator<Violation> iter = errors.iterator();
				String message = "";
				while (iter.hasNext()) {
					message += iter.next().getMessage() + "<br>";
				}
				Log.warn(" in Role -" + message);
			}
		});
		
		//save();
		
		
		}
		
		else
		{
		finalSave();
		
		}
		
		
			
	}
	
	public void saveMajor()
	{
		
		
		
			
		
		 majorRequest.persist().using(proxy).fire(new Receiver<Void>() {
		 
	

			public void onFailure(ServerFailure error) {
				Log.error(error.getMessage());

			}

			@Override
			public void onViolation(Set<Violation> errors) {
				Iterator<Violation> iter = errors.iterator();
				String message = "";
				while (iter.hasNext()) {
					message += iter.next().getMessage() + "<br>";
				}
				Log.warn(" in Role -" + message);
			}

			@Override
			public void onSuccess(Void response) {
				Log.info("Role successfully saved.");
				
				//previous version Inactve
				
				save = true;
				
				
				
			
				
				((RoleEditViewImpl)view).active.setValue(false);
				
				((RoleEditViewImpl)view).shortName.setValue(standardizedRole.getShortName());
				((RoleEditViewImpl)view).longName.setValue(standardizedRole.getLongName());
				((RoleEditViewImpl)view).roleType.setValue(standardizedRole.getRoleType());
				((RoleEditViewImpl)view).studyYear.setValue(standardizedRole.getStudyYear());
				//((RoleEditCheckListSubViewImpl)checkListView).title.setValue("ABC");//spec
				
				save();
				
				
				
			
			}
		});
		 
		 
	

			
	}
	public int findTabIndex()
	{
		int i=0;
		
		Iterator<StandardizedRoleProxy> iterator=roleTopic.getStandardizedRoles().iterator();
		
		while(iterator.hasNext())
		{
			
			if(iterator.next().getId().equals(standardizedRole.getId()))
			{
				
				break;
			}
			i++;
		}
		if(i==roleTopic.getStandardizedRoles().size())
			i=0;
		return i;
	}

	


}
