package ch.unibas.medizin.osce.client.a_nonroo.client.ui.role;

import java.util.ArrayList;
import java.util.Collection;

import ch.unibas.medizin.osce.client.a_nonroo.client.OsMaConstant;
import ch.unibas.medizin.osce.client.a_nonroo.client.request.OsMaRequestFactory;
import ch.unibas.medizin.osce.client.a_nonroo.client.ui.renderer.EnumRenderer;
import ch.unibas.medizin.osce.client.i18n.OsceConstants;
import ch.unibas.medizin.osce.client.managed.request.MaterialListProxy;
import ch.unibas.medizin.osce.client.managed.request.StandardizedRoleProxy;
import ch.unibas.medizin.osce.client.managed.request.UsedMaterialProxy;
import ch.unibas.medizin.osce.client.style.resources.MyCellTableResources;
import ch.unibas.medizin.osce.client.style.resources.MySimplePagerResources;
import ch.unibas.medizin.osce.client.style.widgets.FocusableValueListBox;
import ch.unibas.medizin.osce.client.style.widgets.IconButton;
import ch.unibas.medizin.osce.shared.MaterialUsedFromTypes;

import com.google.gwt.cell.client.ActionCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.text.shared.AbstractRenderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.ValueListBox;
import com.google.gwt.user.client.ui.Widget;

public class RoomMaterialsDetailsSubViewImpl extends Composite implements
		RoomMaterialsDetailsSubView {

	private static RoomMaterialsDetailsSubViewImplUiBinder uiBinder = GWT
			.create(RoomMaterialsDetailsSubViewImplUiBinder.class);

	interface RoomMaterialsDetailsSubViewImplUiBinder extends
			UiBinder<Widget, RoomMaterialsDetailsSubViewImpl> {
	}

	private final OsceConstants constants = GWT.create(OsceConstants.class);
	protected ArrayList<String> paths = new ArrayList<String>();
	Delegate delegate;

	// private List<AbstractEditableCell<?, ?>> editableCells;
	private OsMaRequestFactory requests;

	@UiField(provided = true)
	CellTable<UsedMaterialProxy> table;
	private Presenter presenter;

	@UiField(provided = true)
	FocusableValueListBox<MaterialUsedFromTypes> used_from = new FocusableValueListBox<MaterialUsedFromTypes>(
			new EnumRenderer<MaterialUsedFromTypes>());

	@UiField
	IntegerBox materialCount;

	@UiField(provided = true)
	ValueListBox<MaterialListProxy> materialList = new ValueListBox<MaterialListProxy>(
			new AbstractRenderer<MaterialListProxy>() {
				public String render(MaterialListProxy obj) {
					return obj == null ? "" : String.valueOf(obj.getName());
				}
			});

	@UiField(provided = true)
	SimplePager pager;

	@UiField
	IconButton newButton;

	StandardizedRoleProxy standardizedRoleProxy;

	public RoomMaterialsDetailsSubViewImpl() {
		CellTable.Resources tableResources = GWT
				.create(MyCellTableResources.class);
		table = new CellTable<UsedMaterialProxy>(OsMaConstant.TABLE_PAGE_SIZE,
				tableResources);

		SimplePager.Resources pagerResources = GWT
				.create(MySimplePagerResources.class);
		pager = new SimplePager(SimplePager.TextLocation.RIGHT, pagerResources,
				true, OsMaConstant.TABLE_JUMP_SIZE, true);

		initWidget(uiBinder.createAndBindUi(this));
		init();
		newButton.setText(constants.addMaterial());
		used_from.setAcceptableValues(java.util.Arrays
				.asList(MaterialUsedFromTypes.values()));

	}

	@UiHandler("newButton")
	public void newButtonClicked(ClickEvent event) {
		if (materialList.getValue() == null
				|| materialList.getValue().toString() == "") {
			Window.confirm("Please enter a value for Room material list");
			return;
		}
		if (materialCount.getValue() == null
				|| materialCount.getValue().toString() == "") {
			Window.confirm("Please enter a value for Material Count number");
			return;
		}
		if (used_from.getValue() == null
				|| used_from.getValue().toString() == "") {
			Window.confirm("Please enter a value for \"For who\"");
			return;
		}

		delegate.newUsedMaterialButtonClicked(materialCount.getValue(),
				used_from.getValue(), this.standardizedRoleProxy,
				materialList.getValue());
		materialCount.setValue(null);
		used_from.setValue(null);
		materialList.setValue(null);

	}

	public void init() {

		// editableCells = new ArrayList<AbstractEditableCell<?, ?>>();

		paths.add("materialList");
		table.addColumn(new TextColumn<UsedMaterialProxy>() {

			@Override
			public String getValue(UsedMaterialProxy usedMaterialProxy) {
				MaterialListProxy materialListProxy = usedMaterialProxy
						.getMaterialList();

				return ((materialListProxy.getName() == null) ? ""
						: materialListProxy.getName());
			}
		}, constants.roomMaterialName());

		paths.add("materialCount");
		table.addColumn(new TextColumn<UsedMaterialProxy>() {

			@Override
			public String getValue(UsedMaterialProxy usedMaterialProxy) {

				return ((usedMaterialProxy.getMaterialCount() == null) ? ""
						: usedMaterialProxy.getMaterialCount().toString());
			}
		}, constants.number());

		paths.add("used_from");
		table.addColumn(new TextColumn<UsedMaterialProxy>() {

			@Override
			public String getValue(UsedMaterialProxy usedMaterialProxy) {

				return ((usedMaterialProxy.getUsed_from().name() == null) ? ""
						: usedMaterialProxy.getUsed_from().name());
			}
		}, constants.forWho());

		addColumn(new ActionCell<UsedMaterialProxy>(OsMaConstant.DOWN_ICON,
				new ActionCell.Delegate<UsedMaterialProxy>() {
					public void execute(UsedMaterialProxy proxy) {
						delegate.moveUsedMaterialDown(proxy, getStRoleProxy());
					}
				}), "", new GetValue<UsedMaterialProxy>() {
			public UsedMaterialProxy getValue(UsedMaterialProxy proxy) {
				return proxy;
			}
		}, null);
		addColumn(new ActionCell<UsedMaterialProxy>(OsMaConstant.UP_ICON,
				new ActionCell.Delegate<UsedMaterialProxy>() {
					public void execute(UsedMaterialProxy proxy) {
						delegate.moveUsedMaterialUp(proxy, getStRoleProxy());
					}
				}), "", new GetValue<UsedMaterialProxy>() {
			public UsedMaterialProxy getValue(UsedMaterialProxy proxy) {
				return proxy;
			}
		}, null);

		addColumn(new ActionCell<UsedMaterialProxy>(OsMaConstant.DELETE_ICON,
				new ActionCell.Delegate<UsedMaterialProxy>() {
					public void execute(UsedMaterialProxy usedMaterialProxy) {
						if (Window.confirm("wirklich löschen?"))
							delegate.deleteUsedFromClicked(usedMaterialProxy,
									standardizedRoleProxy);

					}
				}), "", new GetValue<UsedMaterialProxy>() {
			public UsedMaterialProxy getValue(
					UsedMaterialProxy usedMaterialProxy) {
				return usedMaterialProxy;
			}
		}, null);
		table.addColumnStyleName(2, "iconCol");

	}

	@Override
	public void setValue(StandardizedRoleProxy standardizedRoleProxy) {
		this.standardizedRoleProxy = standardizedRoleProxy;
	}

	public StandardizedRoleProxy getStRoleProxy() {
		return this.standardizedRoleProxy;
	}

	private static interface GetValue<C> {
		C getValue(UsedMaterialProxy proxy);
	}

	/**
	 * Add a column with a header.
	 * 
	 * @param <C>
	 *            the cell type
	 * @param cell
	 *            the cell used to render the column
	 * @param headerText
	 *            the header string
	 * @param getter
	 *            the value getter for the cell
	 */
	private <C> void addColumn(Cell<C> cell, String headerText,
			final GetValue<C> getter,
			FieldUpdater<UsedMaterialProxy, C> fieldUpdater) {
		Column<UsedMaterialProxy, C> column = new Column<UsedMaterialProxy, C>(
				cell) {
			@Override
			public C getValue(UsedMaterialProxy object) {
				return getter.getValue(object);
			}
		};
		column.setFieldUpdater(fieldUpdater);
		table.addColumn(column, headerText);
	}

	@Override
	public CellTable<UsedMaterialProxy> getUsedMaterialTable() {
		return table;
	}

	public String[] getPaths() {
		return paths.toArray(new String[paths.size()]);
	}

	@Override
	public void setDelegate(Delegate delegate) {
		this.delegate = delegate;
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;

	}

	@Override
	public void setMaterialListPickerValues(Collection<MaterialListProxy> values) {
		materialList.setAcceptableValues(values);
	}

}
