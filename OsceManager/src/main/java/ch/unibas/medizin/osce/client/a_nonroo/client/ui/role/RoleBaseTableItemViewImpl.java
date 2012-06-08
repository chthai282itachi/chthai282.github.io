package ch.unibas.medizin.osce.client.a_nonroo.client.ui.role;

import java.util.ArrayList;
import java.util.List;

import ch.unibas.medizin.osce.client.a_nonroo.client.OsMaConstant;
import ch.unibas.medizin.osce.client.i18n.OsceConstants;
import ch.unibas.medizin.osce.client.managed.request.RoleBaseItemProxy;
import ch.unibas.medizin.osce.client.managed.request.RoleTableItemProxy;
import ch.unibas.medizin.osce.client.style.resources.MyCellTableResources;
import ch.unibas.medizin.osce.client.style.resources.UiIcons;
import ch.unibas.medizin.osce.client.style.widgets.IconButton;

import com.google.gwt.cell.client.AbstractEditableCell;
import com.google.gwt.cell.client.ActionCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.text.shared.AbstractRenderer;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class RoleBaseTableItemViewImpl extends Composite implements
RoleBaseTableItemView {

	private static RoleBaseTableItemViewImplUiBinder uiBinder = GWT
			.create(RoleBaseTableItemViewImplUiBinder.class);

	interface RoleBaseTableItemViewImplUiBinder extends
			UiBinder<Widget, RoleBaseTableItemViewImpl> {

	}

	private OsceConstants constants = GWT.create(OsceConstants.class);
	private Presenter presenter;
	private Delegate delegate;

	private final UiIcons uiIcons = GWT.create(UiIcons.class);
	@UiField
	public DisclosurePanel roleBaseItemDisclosurePanel;
//	@UiField
//	HTMLPanel dynamicItemTable;
	
/*	@UiField
	Image arrow;
*/	
	
//	@UiField
//	public RoleBaseTableAccessViewImpl roleBaseAccessViewImpl;
//	
	@Override
	public RoleBaseTableAccessViewImpl getRoleBaseTableAccessViewImpl() {
		return null;
	}
	private  RoleBaseItemProxy roleBasedItemProxy;
	
	
	@UiField
	public HorizontalPanel accessDataPanel;

	
	@Override
	public HorizontalPanel getAccessDataPanel() {
		return accessDataPanel;
	}
	
	@UiField
	public Label baseItemHeaderLable;
	
	@UiField(provided = true)
	public CellTable<RoleTableItemProxy> table;
	
//	@UiField
//	Label hello;
	
	@UiField
	public IconButton AccessButton;
	
	@UiField
	public IconButton AddSubItem;
	
	@UiField
	public IconButton downIcon;
	
	@UiField
	public IconButton upIcon;
	
//	@UiField
//	public Label accessLabel1;
//	
	
	@UiField
	com.google.gwt.user.client.ui.Image arrow;
	
	@UiField
	public IconButton pencil;
	
	@UiField
	public IconButton close;
	
	@UiHandler("AccessButton")
	public void baseItemAccessButtonClicked(ClickEvent event){
		delegate.baseItemAccessButtonClicked(event,roleBasedItemProxy,accessDataPanel);
	}
	
	@UiHandler("upIcon")
	public void baseItemUpButtonClicked(ClickEvent event){
		delegate.baseItemUpButtonClicked(roleBasedItemProxy);
	}
	
	@UiHandler("downIcon")
	public void baseItemDownButtonClicked(ClickEvent event){
		delegate.baseItemDownButtonClicked(roleBasedItemProxy);
	}
	
	@UiHandler("pencil")
	public void pencilButtonClickEvent(ClickEvent event){
		delegate.pencliButtonclickEvent(roleBasedItemProxy);
	}
	
	@UiHandler("arrow")
	void handleClick(ClickEvent e) {
		if(roleBaseItemDisclosurePanel.isOpen()) {
			roleBaseItemDisclosurePanel.setOpen(false);
			arrow.setResource(uiIcons.triangle1East());
		}
		else {
			roleBaseItemDisclosurePanel.setOpen(true);
			arrow.setResource(uiIcons.triangle1South());
		}
	   
	  }
	@UiHandler("AddSubItem")
	public void addRoleBaseSubItemClickHandler(ClickEvent event) {
		System.out.println("Calling method to add Sub items..");
		delegate.addRoleBaseSubItem(roleBasedItemProxy,this.table);
		
	}
	
	@UiHandler("close")
	public void deleteButtonClicked(ClickEvent event){
		if(Window.confirm("wirklich löschen?"))
		delegate.deleteButtonClickEvent(roleBasedItemProxy);
	}
	/**
	 * Because this class has a default constructor, it can be used as a binder
	 * template. In other words, it can be used in other *.ui.xml files as
	 * follows: <ui:UiBinder xmlns:ui="urn:ui:com.google.gwt.uibinder"
	 * xmlns:g="urn:import:**user's package**">
	 * <g:**UserClassName**>Hello!</g:**UserClassName> </ui:UiBinder> Note that
	 * depending on the widget that is used, it may be necessary to implement
	 * HasHTML instead of HasText.
	 */
	public RoleBaseTableItemViewImpl() {

		CellTable.Resources tableResources = GWT.create(MyCellTableResources.class);
		table = new CellTable<RoleTableItemProxy>(OsMaConstant.TABLE_PAGE_SIZE, tableResources);
		
		
		
		initWidget(uiBinder.createAndBindUi(this));
		
		init();
		// todo
		AccessButton.setText("access");
		AddSubItem.setText("Add Subitem");
		//AddSubItem.setStyleName("padding=10");
		
	//	accessLabel1=new Label();
	}
	
	public void setValue(RoleBaseItemProxy roleBaseItemProxy)
	{		
		this.roleBasedItemProxy = roleBaseItemProxy;
		baseItemHeaderLable.setText(roleBaseItemProxy.getItem_name());
		System.out.println("Set value rolebaseItem.id : " + this.roleBasedItemProxy.getId());	
	}

	protected ArrayList<String> paths = new ArrayList<String>();
	public void init(){
		
		
		paths.add("item_name");
		table.addColumn(new TextColumn<RoleTableItemProxy>() {
			{ this.setSortable(true); }

			Renderer<java.lang.String> renderer = new AbstractRenderer<java.lang.String>() {

				public String render(java.lang.String obj) {
					return obj == null ? "" : String.valueOf(obj);
				}
			};

			@Override
			public String getValue(RoleTableItemProxy object) {
				return renderer.render(object.getItemName());
				
			}
		},"item_name");
		
		addColumn(new ActionCell<RoleTableItemProxy>(
				OsMaConstant.UP_ICON, new ActionCell.Delegate<RoleTableItemProxy>() {
					public void execute(RoleTableItemProxy roleTableItem) {
						System.out.println("MoveUp is clicked");
					delegate.roleTableItemMoveUp(roleTableItem,roleBasedItemProxy.getId(),table);
					
					}
				}), "", new GetValue<RoleTableItemProxy>() {
			public RoleTableItemProxy getValue(RoleTableItemProxy roleTableItem) {
				return roleTableItem;
			}
		}, null);
		table.addColumnStyleName(1, "iconCol");
		
		addColumn(new ActionCell<RoleTableItemProxy>(
				OsMaConstant.DOWN_ICON, new ActionCell.Delegate<RoleTableItemProxy>() {
					public void execute(RoleTableItemProxy roleTableItem) {
						delegate.roleTableItemMoveDown(roleTableItem,roleBasedItemProxy.getId(),table);
					}
				}), "", new GetValue<RoleTableItemProxy>() {
			public RoleTableItemProxy getValue(RoleTableItemProxy roleTableItem) {
				return roleTableItem;
			}
		}, null);
		table.addColumnStyleName(1, "iconCol");
		
		addColumn(new ActionCell<RoleTableItemProxy>(
				OsMaConstant.EDIT_ICON, new ActionCell.Delegate<RoleTableItemProxy>() {
					public void execute(final RoleTableItemProxy roleTableItem) {
						delegate.roleTableItemEditButtonClicked(roleTableItem,roleBasedItemProxy.getId(),table);		
					}
				}), "", new GetValue<RoleTableItemProxy>() {
			public RoleTableItemProxy getValue(RoleTableItemProxy roleTableItem) {
				return roleTableItem;
			}
		}, null);
		table.addColumnStyleName(1, "iconCol");

		addColumn(new ActionCell<RoleTableItemProxy>(
				OsMaConstant.DELETE_ICON, new ActionCell.Delegate<RoleTableItemProxy>() {
					public void execute(RoleTableItemProxy roleTableItem) {
						//Window.alert("You clicked " + institution.getInstitutionName());
						if(Window.confirm("wirklich löschen?"))
							delegate.roleTableItemDeleteClicked(roleTableItem,roleBasedItemProxy.getId(),table);
					}
				}), "", new GetValue<RoleTableItemProxy>() {
			public RoleTableItemProxy getValue(RoleTableItemProxy roleTableItem) {
				return roleTableItem;
			}
		}, null);
		table.addColumnStyleName(1, "iconCol");
	}
	
	
	private <C> void addColumn(Cell<C> cell, String headerText,
			final GetValue<C> getter, FieldUpdater<RoleTableItemProxy, C> fieldUpdater) {
		Column<RoleTableItemProxy, C> column = new Column<RoleTableItemProxy, C>(cell) {
			@Override
			public C getValue(RoleTableItemProxy object) {
				return getter.getValue(object);
			}
		};
		column.setFieldUpdater(fieldUpdater);
		if (cell instanceof AbstractEditableCell<?, ?>) {
			editableCells.add((AbstractEditableCell<?, ?>) cell);
		}
		table.addColumn(column, headerText);
	}
		
	
	@Override
	public void setDelegate(Delegate delegate) {
		this.delegate = delegate;
	}

	@Override
	public void setPresenter(Presenter StandardizedPatientActivity) {
		this.presenter = StandardizedPatientActivity;
	}

	private List<AbstractEditableCell<?, ?>> editableCells;

	/*@Override
	public void setValue(RoleTemplateProxy proxy) {
		
	}*/
	
	private static interface GetValue<C> {
		
		C getValue(RoleTableItemProxy object);
	}

	public Widget asWidget() {
		return this;
	}
	
	

	@Override
	public Delegate getDelegate() {

		return delegate;
	}

	@Override
	public void setBaseItemMidifiedValue(String value) {
		
		baseItemHeaderLable.setText(value);
	}

	@Override
	public CellTable<RoleTableItemProxy> getTable() {
		return this.table;
	}
//	public Label getLabel(){
//		return this.accessLabel1;
//	}

	
	
}
