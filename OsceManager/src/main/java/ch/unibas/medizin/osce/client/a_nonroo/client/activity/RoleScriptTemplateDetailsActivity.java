package ch.unibas.medizin.osce.client.a_nonroo.client.activity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import ch.unibas.medizin.osce.client.a_nonroo.client.place.RoleScriptTemplateDetailsPlace;
import ch.unibas.medizin.osce.client.a_nonroo.client.request.OsMaRequestFactory;
import ch.unibas.medizin.osce.client.a_nonroo.client.ui.role.RoleBaseTableAccessView;
import ch.unibas.medizin.osce.client.a_nonroo.client.ui.role.RoleBaseTableAccessViewImpl;
import ch.unibas.medizin.osce.client.a_nonroo.client.ui.role.RoleBaseTableItemView;
import ch.unibas.medizin.osce.client.a_nonroo.client.ui.role.RoleBaseTableItemViewImpl;
import ch.unibas.medizin.osce.client.a_nonroo.client.ui.role.RoleScriptTemplateDetailsView;
import ch.unibas.medizin.osce.client.a_nonroo.client.ui.role.RoleScriptTemplateDetailsViewImpl;
import ch.unibas.medizin.osce.client.i18n.OsceConstants;
import ch.unibas.medizin.osce.client.managed.request.RoleBaseItemProxy;
import ch.unibas.medizin.osce.client.managed.request.RoleBaseItemRequest;
import ch.unibas.medizin.osce.client.managed.request.RoleItemAccessProxy;
import ch.unibas.medizin.osce.client.managed.request.RoleTableItemProxy;
import ch.unibas.medizin.osce.client.managed.request.RoleTableItemRequest;
import ch.unibas.medizin.osce.client.managed.request.RoleTemplateProxy;
import ch.unibas.medizin.osce.client.managed.ui.RoleItemAccessProxyRenderer;
import ch.unibas.medizin.osce.client.style.widgets.IconButton;
import ch.unibas.medizin.osce.shared.ItemDefination;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.requestfactory.shared.ServerFailure;
import com.google.gwt.user.cellview.client.AbstractHasData;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ValueListBox;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.Range;
import com.google.gwt.view.client.SingleSelectionModel;

@SuppressWarnings("deprecation")
public class RoleScriptTemplateDetailsActivity extends AbstractActivity
		implements RoleScriptTemplateDetailsView.Presenter,
		RoleScriptTemplateDetailsView.Delegate,RoleBaseTableItemView.Delegate,RoleBaseTableAccessView.Delegate {

	private OsMaRequestFactory requests;
	private PlaceController placeController;
	private AcceptsOneWidget widget;
	private RoleScriptTemplateDetailsView view;
	private RoleTemplateProxy roleTemplate;

	
	private RoleScriptTemplateDetailsActivity roleScriptTemplateDetailsActivity;
	

	private PopupPanel toolTip;
	private HorizontalPanel toolTipContentPanel;
	private TextBox toolTipLabel;
	private Button toolTipChange;
		
	private int sizeOfTable;
	private final OsceConstants constants = GWT.create(OsceConstants.class);	
	private RoleScriptTemplateDetailsPlace place;

	private CellTable<RoleBaseItemProxy> baseItemTable;
	private SingleSelectionModel<RoleBaseItemProxy> baseItemselectionModel;
	
	private CellTable<RoleTableItemProxy> table;
	private SingleSelectionModel<RoleTableItemProxy> selectionModel;
	
	
	public RoleScriptTemplateDetailsActivity(
		RoleScriptTemplateDetailsPlace place, OsMaRequestFactory requests,
		PlaceController placeController) {
		this.place = place;
		this.requests = requests;
		this.placeController = placeController;
	}

	public void onStop() {
	
	}

	public RoleBaseTableItemView roleBaseTableItemViewImpl = new RoleBaseTableItemViewImpl();
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		Log.info("RoleScriptTemplateDetailsActivity.start()");
		RoleScriptTemplateDetailsView roleScriptTemplateDetailsView = new RoleScriptTemplateDetailsViewImpl();
		roleScriptTemplateDetailsView.setPresenter(this);
		roleScriptTemplateDetailsActivity=this;
		this.widget = panel;
		this.view = roleScriptTemplateDetailsView;

		widget.setWidget(roleScriptTemplateDetailsView.asWidget());
		
		setTable(view.getTable());

				
		view.setDelegate(this);
		//view.getRoleBaseTableItemViewImpl().setDelegate(this);
		
		//roleScriptTemplateDetailsView.setDelegate(this);
		
		//	view.getRoleBaseTableItemViewImpl().getRoleBaseTableAccessViewImpl().setDelegate(this);
		
		requests.find(place.getProxyId()).with("RoleTemplate")
				.fire(new InitializeActivityReceiver());
	
		ProvidesKey<RoleBaseItemProxy> keyProvider = ((AbstractHasData<RoleBaseItemProxy>) baseItemTable).getKeyProvider();
		baseItemselectionModel = new SingleSelectionModel<RoleBaseItemProxy>(keyProvider);
		baseItemTable.setSelectionModel(baseItemselectionModel);
		
		
					init();
					init2();
	}
		
	public void init2()
	{
		requests.find(place.getProxyId()).with("RoleTemplate").fire(new Receiver<Object>() {

			@Override
			public void onSuccess(Object response) {
				if (response instanceof RoleTemplateProxy) {
					requests.roleBaseItemRequestNoonRoo().findAllDeletedRoleBaseItems(((RoleTemplateProxy) response).getId()).fire(new Receiver<List<RoleBaseItemProxy>>() {

						@Override
						public void onSuccess(List<RoleBaseItemProxy> response) {
							Range range = baseItemTable.getVisibleRange();
							baseItemTable.setRowCount(response.size());
							baseItemTable.setRowData(range.getStart(), response);
							
						}
					});
				}
				}
		});
	}	
		
		
	private void init() {		
		requests.find(place.getProxyId()).with("roleBaseItem").with("roleBaseItem.roleTableItem","roleBaseItem.roleItemAccess").fire(new Receiver<Object>() {
					@Override
			public void onSuccess(Object response) {
			
				if(response instanceof RoleTemplateProxy){
					Log.info("Retriving RoleItemProxy");
					List<RoleBaseItemProxy> setRoleBaseItemProxy = ((RoleTemplateProxy) response).getRoleBaseItem();
					Iterator<RoleBaseItemProxy> iteratorRoleBaseItemProxy = setRoleBaseItemProxy.iterator();
					while(iteratorRoleBaseItemProxy.hasNext())
					{
						Log.info("Retriving priviouswidgets");
						//view.addRoleBaseWidget(iteratorRoleBaseItemProxy.next());
						
						
						RoleBaseTableItemViewImpl roleBaseTableItemViewImpl=new RoleBaseTableItemViewImpl();
						//roleBaseTableItemViewImpl.baseItemHeaderLable.setText(rolebaseItem.getItem_name());
						//	System.out.println("rolebaseItem.id : " + rolebaseItem.getId());
						roleBaseTableItemViewImpl.setDelegate(roleScriptTemplateDetailsActivity);
						
						RoleBaseItemProxy roleBaseItemProxy = iteratorRoleBaseItemProxy.next();
						if(roleBaseItemProxy.getItem_defination().name().equals("table_item"))
						{
							if(roleBaseItemProxy.getDeleted())
								continue;
							Log.info("Total Table cound : " + roleBaseItemProxy.getRoleTableItem().size() );
							roleBaseTableItemViewImpl.setValue(roleBaseItemProxy);						
							view.getTableItem().add(roleBaseTableItemViewImpl);
						
							Range range = roleBaseTableItemViewImpl.getTable().getVisibleRange();
							roleBaseTableItemViewImpl.getTable().setRowCount(roleBaseItemProxy.getRoleTableItem().size());
						
							RoleTableItemProxy[] arrRoleTableItemProxy = new  RoleTableItemProxy[roleBaseItemProxy.getRoleTableItem().size()];								
							roleBaseItemProxy.getRoleTableItem().toArray(arrRoleTableItemProxy);
							List<RoleTableItemProxy> listRoleTableItemProxy = Arrays.asList(arrRoleTableItemProxy);						
							roleBaseTableItemViewImpl.getTable().setRowData(range.getStart(),listRoleTableItemProxy);
						}
						else
						{
							if(roleBaseItemProxy.getDeleted())
								continue;
							roleBaseTableItemViewImpl.setValue(roleBaseItemProxy);
							roleBaseTableItemViewImpl.roleBaseItemDisclosurePanel.setStyleName("border=0");
							roleBaseTableItemViewImpl.table.removeFromParent();
							roleBaseTableItemViewImpl.AddSubItem.removeFromParent();
							view.getTableItem().add(roleBaseTableItemViewImpl);
						}
						
												
						Set<RoleItemAccessProxy> setRoleItemAccessProxy = roleBaseItemProxy.getRoleItemAccess();
						Iterator<RoleItemAccessProxy> roleAccess = setRoleItemAccessProxy.iterator();
						Log.info("RoleItemAccess set size : " + setRoleItemAccessProxy.size());
						while(roleAccess.hasNext())
						{
							RoleItemAccessProxy roleItemAccessProxy = roleAccess.next();
							RoleBaseTableAccessViewImpl roleBaseTableAccessViewImpl = new RoleBaseTableAccessViewImpl();
							roleBaseTableAccessViewImpl.setDelegate(roleScriptTemplateDetailsActivity);	
							roleBaseTableAccessViewImpl.accessDataLabel.setText(roleItemAccessProxy.getName());
							roleBaseTableItemViewImpl.getAccessDataPanel().add(roleBaseTableAccessViewImpl);
							roleBaseTableAccessViewImpl.setRoleBaseItemProxy(roleBaseItemProxy);
							roleBaseTableAccessViewImpl.setRoleItemAccessProxy(roleItemAccessProxy);
						}
						//roleBaseTableItemViewImpl.getRoleBaseTableAccessViewImpl().accessDataLabel
					}
				}
			}
		});	
	}

	/**
	 * Used as a callback for the request that gets the @StandardizedPatientProxy
	 * that is edited in this activities instance.
	 */

	private class InitializeActivityReceiver extends Receiver<Object> {
		@Override
		public void onFailure(ServerFailure error) {
			Log.error(error.getMessage());
			Log.info("Error in InitializeActivityReceiver");
		}

		@Override
		public void onSuccess(Object response) {
			Log.info("sucess in InitializeActivityReceiver");
			if (response instanceof RoleTemplateProxy) {
				
				Log.info(((RoleTemplateProxy) response).getTemplateName());
				roleTemplate = (RoleTemplateProxy) response;
				// init();
			}
		}
	}

	@Override
	public void goTo(Place place) {
		placeController.goTo(place);
	}
	private void setTable(CellTable<RoleBaseItemProxy> basetable) {
		this.baseItemTable = basetable;
		
	}

	@Override
	public void newClicked(String name, final ch.unibas.medizin.osce.shared.ItemDefination item_defination) {
		Log.debug("Add Role Template Base Item");
		Log.info("item Name :" + name);
		Log.info("item defination  :" + item_defination);

		RoleBaseItemRequest roleBaseItemReq = requests.roleBaseItemRequest();
		final RoleBaseItemProxy roleBaseitem = roleBaseItemReq
				.create(RoleBaseItemProxy.class);

		roleBaseitem.setItem_name(name);
		roleBaseitem.setItem_defination(item_defination);
		roleBaseitem.setDeleted(false);
		roleBaseitem.setRoleTemplate(roleTemplate);
		roleBaseitem.setSort_order(view.getTableItem().getWidgetCount()+1);
		
		roleBaseItemReq.persist().using(roleBaseitem)
				.fire(new Receiver<Void>() {
					
					@Override
					public void onSuccess(Void arg0) {
						Log.info("sucessfully RoleBase Item saved");
												
						requests.find(roleBaseitem.stableId()).fire(new Receiver<Object>() {

							@Override
							public void onSuccess(Object response) {
								// TODO Auto-generated method stub
								System.out.println(" response id : " + ((RoleBaseItemProxy)response).getId());
								if(item_defination.name().equals("table_item"))
								{
									RoleBaseTableItemViewImpl roleBaseTableItemViewImpl=new RoleBaseTableItemViewImpl();
								//roleBaseTableItemViewImpl.baseItemHeaderLable.setText(rolebaseItem.getItem_name());																
									roleBaseTableItemViewImpl.setDelegate(roleScriptTemplateDetailsActivity);
									roleBaseTableItemViewImpl.setValue((RoleBaseItemProxy)response);
									view.getTableItem().add(roleBaseTableItemViewImpl);
																
																
								}
								else
								{
									RoleBaseTableItemViewImpl roleText_AreaTableItemViewImpl = new RoleBaseTableItemViewImpl();
									roleText_AreaTableItemViewImpl.setDelegate(roleScriptTemplateDetailsActivity);
									roleText_AreaTableItemViewImpl.setValue((RoleBaseItemProxy)response);
									view.getTableItem().add(roleText_AreaTableItemViewImpl);
									
									roleText_AreaTableItemViewImpl.roleBaseItemDisclosurePanel.setStyleName("border=0");
									roleText_AreaTableItemViewImpl.table.removeFromParent();
									roleText_AreaTableItemViewImpl.AddSubItem.removeFromParent();
								}
							}
							
						});
						
						
					}
				});
		
	}

	
	
	@Override
	public void addRoleBaseSubItem(final RoleBaseItemProxy roleBaseItemProxy,final CellTable<RoleTableItemProxy> table) {
		this.table=table;
		ProvidesKey<RoleTableItemProxy> keyProvider = ((AbstractHasData<RoleTableItemProxy>) table).getKeyProvider();
		selectionModel = new SingleSelectionModel<RoleTableItemProxy>(keyProvider);
		table.setSelectionModel(selectionModel);
		Log.info("Inside of addRoleBaseSubItem() to add and retrive sub item data");
		//final int totalRow = table.getRowCount();
		
		requests.find(roleBaseItemProxy.stableId()).with("roleTableItem").fire(new Receiver<Object>(){
			
				@Override
				public void onFailure(ServerFailure error){
					Log.error("onFilure");
					Log.error(error.getMessage());				
				}
				@Override
				public void onSuccess(Object object) {
						if (object instanceof RoleBaseItemProxy){
							
						Log.info("Retriving RoleBase Proxy");
					
						
						RoleTableItemRequest roleTableItemReq = requests.roleTableItemRequest();
						final RoleTableItemProxy roleTableItemProxy = roleTableItemReq.create(RoleTableItemProxy.class);
						Log.info("RoleBaseItem set list count:" +  ((RoleBaseItemProxy)object).getRoleTableItem().size());
						sizeOfTable=(((RoleBaseItemProxy)object).getRoleTableItem().size());
						sizeOfTable+=1;
						roleTableItemProxy.setItemName("Title "+sizeOfTable);	
						roleTableItemProxy.setSort_order(sizeOfTable);
						roleTableItemProxy.setRoleBaseItem((RoleBaseItemProxy)object);
						final Long roleBaseItemId = ((RoleBaseItemProxy)object).getId(); 
						
						roleTableItemReq.persist().using(roleTableItemProxy).fire(new Receiver<Void>(){
							
							@Override
							public void onFailure(ServerFailure error){
								Log.error("onFilure");
								Log.error(error.getMessage());				
							}
							
							@Override
							public void onSuccess(Void arg0) {
								Log.info("Save RoleTopic values value Succesfully");	
								table.setRowCount(sizeOfTable);
								onRangeChanged(roleBaseItemId);
								
								}
						});					
					}
						else{
								Log.info("Skiped proxy if condition");
						}
				}
			});
		
		
		
		
	}
	
	public void showRoleBaseSubItem(RoleBaseItemProxy roleBaseItemProxy,final CellTable<RoleTableItemProxy> table) {
		this.table=table;
		ProvidesKey<RoleTableItemProxy> keyProvider = ((AbstractHasData<RoleTableItemProxy>) table).getKeyProvider();
		selectionModel = new SingleSelectionModel<RoleTableItemProxy>(keyProvider);
		table.setSelectionModel(selectionModel);
		Log.info("SubItem Proxy Id : " + roleBaseItemProxy.getId());
		onRangeChanged(roleBaseItemProxy.getId());
				
	}
	
	protected void onRangeChanged( Long roleBaseItemId) {
		final Range range = table.getVisibleRange();

		final Receiver<List<RoleTableItemProxy>> callback = new Receiver<List<RoleTableItemProxy>>() {

			@Override
			public void onSuccess(List<RoleTableItemProxy> response) {
				if (view == null) {
					return;
				}
				Log.info("Successfully RoleTemplate result value set in table");
				table.setRowCount(response.size());
				table.setRowData(range.getStart(), response);

				// finishPendingSelection();
				if (widget != null) {
					widget.setWidget(view.asWidget());
				}
			}

		};

		fireRangeRequest(range, callback,roleBaseItemId);
	}
	private void fireRangeRequest(final Range range,
			final Receiver<List<RoleTableItemProxy>> callback,Long roleBaseItemId) {
		Log.info("Inside fireRangeRequest()");
		 requests.roleTableItemRequestNoonRoo().findRoleTableItemByBaseItemId(roleBaseItemId).fire(callback);
		
	}

	@Override
	public void pencliButtonclickEvent(final RoleBaseItemProxy roleBaseItemProxy) {

		
		Log.info("ToolTip opened");
		if(roleBaseItemProxy==null){
			Log.info("RoleBaseProxy is null when pencil clickd");
			return;
		}
		
				Log.info("RoleBase Proxy Item name :" + roleBaseItemProxy.getItem_name());
				toolTip= new PopupPanel(true);
				
				toolTip.setWidth("180px");
				toolTip.setHeight("40px");
			    toolTip.setAnimationEnabled(true);
			    
				toolTipContentPanel=new HorizontalPanel();
				
				toolTipContentPanel.setWidth("160px");
				toolTipContentPanel.setHeight("22px");
				toolTipContentPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
				toolTipContentPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
			
				toolTipLabel=new TextBox();
				
				toolTipLabel.setWidth("120px");
				toolTipLabel.setHeight("25px");
				
				toolTipChange = new Button("Save");
			 
				toolTipChange.setWidth("40px");
				toolTipChange.setHeight("25px");       
				
				
			
				toolTipContentPanel.add(toolTipLabel);
				toolTipContentPanel.add(toolTipChange);
			     
				toolTipLabel.setText(roleBaseItemProxy.getItem_name());
			       
				    
				toolTip.add(toolTipContentPanel);   // you can add any widget here
			        

			   
				toolTip.setPopupPosition(new Integer(constants.TopicsAndSpecViewPopupXPosition()),new Integer(constants.TopicsAndSpecViewPopupYPosition()));
			    
			        toolTip.show();
			        
			        toolTipChange.addClickHandler(new ClickHandler() {
						
						@Override
						public void onClick(ClickEvent event) {
							requests.roleBaseItemRequest().findRoleBaseItem(roleBaseItemProxy.getId()).fire(new Receiver<RoleBaseItemProxy>() {
							
							
										@Override
										public void onSuccess(RoleBaseItemProxy response) {
											RoleBaseItemRequest roleBaseItemReq = requests.roleBaseItemRequest();										
											response = roleBaseItemReq.edit(response);
											response.setItem_name(toolTipLabel.getText());
											roleBaseItemReq.persist().using(roleBaseItemProxy).fire(new Receiver<Void>(){
												
												@Override
												public void onFailure(ServerFailure error){
													Log.error("onFilure");
													Log.error(error.getMessage());				
												}
												
												@Override
												public void onSuccess(Void arg0) {
													Log.info("Save RoleBaseItem value Succesfully according to ToolTip value");
													toolTip.clear();
													toolTip.hide();		
													
													view.getTableItem().clear();
													init();
													init2();
																		
												}
											});					
										}							
									}
								);
											
											
							}
								
					});
								
	}

	@Override
public void roleTableItemEditButtonClicked(final RoleTableItemProxy roleTableItem,final Long roleBaseItemId,final CellTable<RoleTableItemProxy> roleTableItemtable) {
		
		Log.info("RoleBase Proxy Item name to add title of roletable:" + roleTableItem.getItemName());
		Log.info("RoleBase Proxy Item id to add title of roletable:" + roleTableItem.getRoleBaseItem());
		toolTip= new PopupPanel(true);
		
		toolTip.setWidth("180px");
		toolTip.setHeight("40px");
	    toolTip.setAnimationEnabled(true);
	    
		toolTipContentPanel=new HorizontalPanel();
		
		toolTipContentPanel.setWidth("160px");
		toolTipContentPanel.setHeight("22px");
		toolTipContentPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		toolTipContentPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
	
		toolTipLabel=new TextBox();
		
		toolTipLabel.setWidth("120px");
		toolTipLabel.setHeight("25px");
		
		toolTipChange = new Button("Save");
	 
		toolTipChange.setWidth("40px");
		toolTipChange.setHeight("25px");       
		
		
	
		toolTipContentPanel.add(toolTipLabel);
		toolTipContentPanel.add(toolTipChange);
	     
		toolTipLabel.setText(roleTableItem.getItemName());
	       
		    
		toolTip.add(toolTipContentPanel);   // you can add any widget here
	        

	   
		toolTip.setPopupPosition(new Integer(constants.TopicsAndSpecViewPopupXPosition()),new Integer(constants.TopicsAndSpecViewPopupYPosition()));
	    
	        toolTip.show();
	        
	        toolTipChange.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
				//	requests.roleBaseItemRequest().findRoleBaseItem(roleBaseItemProxy.getId()).fire(new Receiver<RoleBaseItemProxy>() {
					requests.roleTableItemRequest().findRoleTableItem(roleTableItem.getId()).fire(new Receiver<RoleTableItemProxy>() {
					
								@Override
								public void onSuccess(RoleTableItemProxy response) {
									RoleTableItemRequest roleTableItemReq = requests.roleTableItemRequest();										
									response = roleTableItemReq.edit(response);
									response.setItemName(toolTipLabel.getText());
								
									roleTableItemReq.persist().using(roleTableItem).fire(new Receiver<Void>(){
										
									@Override
									public void onFailure(ServerFailure error){
										Log.error("onFilure");
										Log.error(error.getMessage());				
									}
										
									@Override
									public void onSuccess(Void arg0) {
									Log.info("Save RoleTableItem value Succesfully according to ToolTip value");
										
											toolTip.clear();
											toolTip.hide();
											
										requests.roleTableItemRequestNoonRoo().findRoleTableItemByBaseItemId(roleBaseItemId).fire(new Receiver<List<RoleTableItemProxy>>() {

												@Override
												public void onSuccess(List<RoleTableItemProxy> response) {
													roleTableItemtable.setRowCount(response.size());
													final Range range = roleTableItemtable.getVisibleRange();
													roleTableItemtable.setRowData(range.getStart(),response);
													}
												});	
													
											}
										});
									}
								});		
								}			

		
								});
					}

	@Override
	public void roleTableItemDeleteClicked(RoleTableItemProxy roleTableItem,final Long roleBaseItemId,final CellTable<RoleTableItemProxy> roleTableItemTable) {
		requests.roleTableItemRequest().remove().using(roleTableItem).fire(new Receiver<Void>() {
			public void onSuccess(Void ignore) {
				Log.debug("role Table Item Sucessfully deleted");
				
		requests.roleTableItemRequestNoonRoo().findRoleTableItemByBaseItemId(roleBaseItemId).fire(new Receiver<List<RoleTableItemProxy>>() {

			@Override
			public void onSuccess(List<RoleTableItemProxy> response) {
				roleTableItemTable.setRowCount(response.size());
				final Range range = roleTableItemTable.getVisibleRange();
				roleTableItemTable.setRowData(range.getStart(),response);
			}
		});	
				
			}
		});

	}

	@Override
	public void deleteButtonClickEvent(RoleBaseItemProxy rolebaseItem) {
		Log.info("RoleTopic delete clicked");
		
		
			RoleBaseItemRequest roleBaseItemReq = requests.roleBaseItemRequest();										
			rolebaseItem = roleBaseItemReq.edit(rolebaseItem);
			rolebaseItem.setDeleted(true);
			roleBaseItemReq.persist().using(rolebaseItem).fire(new Receiver<Void>() {

				@Override
				public void onSuccess(Void response) {
					Log.info("BaseItem Deleted Successfully");
					view.getTableItem().clear();
					init();
					init2();
				}		
				
			});
			
		}

	@Override
	public void restoreButtonClicked(RoleBaseItemProxy roleBaseItem) {
		
		RoleBaseItemRequest roleBaseItemReq = requests.roleBaseItemRequest();										
		roleBaseItem = roleBaseItemReq.edit(roleBaseItem);
		roleBaseItem.setDeleted(false);
		roleBaseItemReq.persist().using(roleBaseItem).fire(new Receiver<Void>() {

			@Override
			public void onSuccess(Void response) {
				Log.info("BaseItem Deleted Successfully");
				view.getTableItem().clear();
				init();
				init2();
			}		
			
		});
		
		
	}

	@Override
	public void roleTableItemMoveUp(RoleTableItemProxy roleTableItem,final Long roleBaseItemId,final CellTable<RoleTableItemProxy> roleTableItemTable) {
		
		
		Log.info("MoveUP inside Activity");
		requests.roleTableItemRequestNoonRoo().roleTableItemMoveUp(roleBaseItemId).using(roleTableItem)
		.fire(new Receiver<Void>() {
			@Override
			public void onSuccess(Void response) {
				Log.info("moved");
			
		requests.roleTableItemRequestNoonRoo().findRoleTableItemByBaseItemId(roleBaseItemId).fire(new Receiver<List<RoleTableItemProxy>>() {

					@Override
					public void onSuccess(List<RoleTableItemProxy> response) {
						roleTableItemTable.setRowCount(response.size());
						final Range range = roleTableItemTable.getVisibleRange();
						roleTableItemTable.setRowData(range.getStart(),response);
					}
				});	
				
			}
		});
		
	}

	@Override
	public void roleTableItemMoveDown(RoleTableItemProxy roleTableItem,final Long roleBaseItemId,final CellTable<RoleTableItemProxy> roleTableItemTable) {
		
		Log.info("MoveDown inside Activity");
		requests.roleTableItemRequestNoonRoo().roleTableItemMoveDown(roleBaseItemId).using(roleTableItem)
		.fire(new Receiver<Void>() {
			@Override
			public void onSuccess(Void response) {
				Log.info("Downed");
				
				requests.roleTableItemRequestNoonRoo().findRoleTableItemByBaseItemId(roleBaseItemId).fire(new Receiver<List<RoleTableItemProxy>>() {

					@Override
					public void onSuccess(List<RoleTableItemProxy> response) {
						roleTableItemTable.setRowCount(response.size());
						final Range range = roleTableItemTable.getVisibleRange();
						roleTableItemTable.setRowData(range.getStart(),response);
					}
				});	
				
			}
		});
		
	}

	@Override
	public void baseItemUpButtonClicked(RoleBaseItemProxy roleBasedItemProxy) {
		Log.info("MoveUP inside Activity");
		requests.roleBaseItemRequestNoonRoo().baseItemUpButtonClicked().using(roleBasedItemProxy)
		.fire(new Receiver<Void>() {
			@Override
			public void onSuccess(Void response) {
				Log.info("moved");
				view.getTableItem().clear();
				init();
				init2();
			}
		});
		
		
	}

	@Override
	public void baseItemDownButtonClicked(RoleBaseItemProxy roleBasedItemProxy) {
		Log.info("MoveDown inside Activity");
		requests.roleBaseItemRequestNoonRoo().baseItemDownButtonClicked().using(roleBasedItemProxy)
		.fire(new Receiver<Void>() {
			@Override
			public void onSuccess(Void response) {
				Log.info("moved");
				view.getTableItem().clear();
				init();
				init2();
			}
		});
		
	}

	@Override
	public void baseItemAccessButtonClicked(ClickEvent event,
			final RoleBaseItemProxy roleBasedItemProxy,final HorizontalPanel accessDataPanel) {
				
		IconButton accessButton =(IconButton) event.getSource();
		
		int xPosition = accessButton.getAbsoluteLeft();
		int yPosition = accessButton.getAbsoluteTop();
		
		toolTip= new PopupPanel(true);
		
		toolTip.setWidth("180px");
		toolTip.setHeight("40px");
	    toolTip.setAnimationEnabled(true);
	    
		toolTipContentPanel=new HorizontalPanel();
		
		toolTipContentPanel.setWidth("160px");
		toolTipContentPanel.setHeight("22px");
		toolTipContentPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		toolTipContentPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
	
		final ValueListBox<RoleItemAccessProxy> accessList = new ValueListBox<RoleItemAccessProxy>(new RoleItemAccessProxyRenderer());
		accessList.setWidth("150px");
		accessList.setHeight("22px");
		
					
		
		requests.roleItemAccessRequest().findAllRoleItemAccesses().fire(new Receiver <List<RoleItemAccessProxy>>() {

			@Override
			public void onSuccess(List<RoleItemAccessProxy> response) {
					accessList.setAcceptableValues(response);
				}
		});
		toolTipContentPanel.add(accessList);
	
		
	     toolTip.add(toolTipContentPanel);   // you can add any widget here
	        
	   
		toolTip.setPopupPosition(xPosition - 100,yPosition-50);
	    
	      toolTip.show();
	      accessList.addValueChangeHandler(new ValueChangeHandler<RoleItemAccessProxy>() {
						
			
			@Override
			public void onValueChange(ValueChangeEvent<RoleItemAccessProxy> event) {
				boolean flag=false;
				
				
				Log.info("Selected data : " +event.getValue().getName());
				
				RoleBaseTableAccessViewImpl roleBaseTableAccssViewImpl = new RoleBaseTableAccessViewImpl();
			
				roleBaseTableAccssViewImpl.setDelegate(roleScriptTemplateDetailsActivity);
			
				//roleBaseTableAccssViewImpl.setRoleItemAccessProxy(event.getValue());
				if(roleBasedItemProxy.getRoleItemAccess() != null)
				{
					
					 Iterator<RoleItemAccessProxy> roleBaseProxy = roleBasedItemProxy.getRoleItemAccess().iterator(); 
					 while(roleBaseProxy.hasNext())
					 {
						 if(roleBaseProxy.next().getId().longValue()==event.getValue().getId().longValue()){
							 flag=true;
						 }
					 }
				}
				if(flag){
					Window.alert("Same Role Access Is not assign twice");
					return ;
				}
				
				roleBaseTableAccssViewImpl.accessDataLabel.setText(event.getValue().getName());
				accessDataPanel.add(roleBaseTableAccssViewImpl);
								
				RoleBaseItemProxy editRoleBasedItemProxy = roleBasedItemProxy;
				
				RoleBaseItemRequest roleBaseItemReq = requests.roleBaseItemRequest();										
				editRoleBasedItemProxy = roleBaseItemReq.edit(editRoleBasedItemProxy);
				
				Set<RoleItemAccessProxy> setRoleItemAccessProxy = editRoleBasedItemProxy.getRoleItemAccess();
		
		
				setRoleItemAccessProxy.add(event.getValue());
				
				editRoleBasedItemProxy.setRoleItemAccess(setRoleItemAccessProxy);
				
				roleBaseItemReq.persist().using(editRoleBasedItemProxy).fire(new Receiver<Void>() {

					@Override
					public void onSuccess(Void response) {
						
						Log.info("Successfully added access : " + response);
						toolTip.hide();
						view.getTableItem().clear();
						init();
						init2();
						
					}
					
				});
				
				
				
				
			}
		});
		
	}

	@Override
	public void deleteAccessType(RoleBaseItemProxy roleBasedItemProxy,Label dataAccessLabel,RoleItemAccessProxy roleItemAccessProxy) {
		
		
		
		RoleBaseTableAccessViewImpl roleBaseTableAccssViewImpl = new RoleBaseTableAccessViewImpl();
	
		roleBaseTableAccssViewImpl.setDelegate(roleScriptTemplateDetailsActivity);
			
		RoleBaseItemProxy editRoleBasedItemProxy = roleBasedItemProxy;
		RoleBaseItemRequest roleBaseItemReq = requests.roleBaseItemRequest();										
		editRoleBasedItemProxy = roleBaseItemReq.edit(editRoleBasedItemProxy);
		
		Set<RoleItemAccessProxy> setRoleItemAccessProxy = editRoleBasedItemProxy.getRoleItemAccess();							
		
		Set<RoleItemAccessProxy> editSetRoleItemAccessProxy = new HashSet<RoleItemAccessProxy>(); 	
		editSetRoleItemAccessProxy.addAll(setRoleItemAccessProxy);	
		
		Log.info("RoleItem access count before delete :" + setRoleItemAccessProxy.size());
		
		
		editRoleBasedItemProxy.getRoleItemAccess().removeAll(setRoleItemAccessProxy);
		Log.info("RoleItem access count After delete :" + setRoleItemAccessProxy.size());
		
		
		Iterator<RoleItemAccessProxy> iterRoleItemProxy = editSetRoleItemAccessProxy.iterator();
		
		while(iterRoleItemProxy.hasNext())
		{
			if(roleItemAccessProxy.getId().longValue() == iterRoleItemProxy.next().getId().longValue())
			{
				Log.info("Removing : " + roleItemAccessProxy.getId() + " : " + roleItemAccessProxy.getName());
				iterRoleItemProxy.remove();
				break;
			}
			else
				Log.info("Other : " + roleItemAccessProxy.getId() + " : " + roleItemAccessProxy.getName());
			
		}
				
	
		
		editRoleBasedItemProxy.setRoleItemAccess(editSetRoleItemAccessProxy);
		roleBaseItemReq.persist().using(editRoleBasedItemProxy).fire(new Receiver<Void>() {
	
			@Override
			public void onSuccess(Void response) {
				view.getTableItem().clear();
				init();
				init2();
			}

	
			
		});
		
	}

	

}

