/**
 * 
 */
package ch.unibas.medizin.osce.client.a_nonroo.client.ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ch.unibas.medizin.osce.client.a_nonroo.client.OsMaConstant;
import ch.unibas.medizin.osce.client.i18n.OsceConstants;
import ch.unibas.medizin.osce.client.managed.request.LogEntryProxy;
import ch.unibas.medizin.osce.client.style.resources.MyCellTableResources;
import ch.unibas.medizin.osce.client.style.resources.MySimplePagerResources;
import ch.unibas.medizin.osce.client.style.widgets.QuickSearchBox;

import com.google.gwt.cell.client.AbstractEditableCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.text.shared.AbstractRenderer;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author dk
 *
 */
public class LogViewImpl extends Composite implements LogView {

	private static LogViewUiBinder uiBinder = GWT
			.create(LogViewUiBinder.class);

	interface LogViewUiBinder extends UiBinder<Widget, LogViewImpl> {
	}

	private Delegate delegate;

	@UiField
	SplitLayoutPanel splitLayoutPanel;

	@UiField (provided = true)
	QuickSearchBox searchBox;

	@UiField (provided = true)
	SimplePager pager;

	@UiField (provided = true)
	CellTable<LogEntryProxy> table;

	protected Set<String> paths = new HashSet<String>();

	private Presenter presenter;

	/**
	 * Because this class has a default constructor, it can
	 * be used as a binder template. In other words, it can be used in other
	 * *.ui.xml files as follows:
	 * <ui:UiBinder xmlns:ui="urn:ui:com.google.gwt.uibinder"
	 *   xmlns:g="urn:import:**user's package**">
	 *  <g:**UserClassName**>Hello!</g:**UserClassName>
	 * </ui:UiBinder>
	 * Note that depending on the widget that is used, it may be necessary to
	 * implement HasHTML instead of HasText.
	 */
	public LogViewImpl() {
		CellTable.Resources tableResources = GWT.create(MyCellTableResources.class);
		table = new CellTable<LogEntryProxy>(OsMaConstant.TABLE_PAGE_SIZE, tableResources);

		SimplePager.Resources pagerResources = GWT.create(MySimplePagerResources.class);
		pager = new SimplePager(SimplePager.TextLocation.RIGHT, pagerResources, true, OsMaConstant.TABLE_JUMP_SIZE, true);
		
		searchBox = new QuickSearchBox(new QuickSearchBox.Delegate() {
			@Override
			public void performAction() {
				delegate.performSearch(searchBox.getValue());
			}
		});

		initWidget(uiBinder.createAndBindUi(this));
		init();

		splitLayoutPanel.setWidgetMinSize(splitLayoutPanel.getWidget(0), OsMaConstant.SPLIT_PANEL_MINWIDTH);
	}

	public String[] getPaths() {
		return paths.toArray(new String[paths.size()]);
	}

	public void init() {
		OsceConstants constants = GWT.create(OsceConstants.class);
		// bugfix to avoid hiding of all panels (maybe there is a better solution...?!)
		DOM.setElementAttribute(splitLayoutPanel.getElement(), "style", "position: absolute; left: 0px; top: 0px; right: 5px; bottom: 0px;");

		editableCells = new ArrayList<AbstractEditableCell<?, ?>>();

		paths.add("room_number");
		table.addColumn(new TextColumn<LogEntryProxy>() {

			Renderer<Date> renderer = new AbstractRenderer<Date>() {

				public String render(Date obj) {
					return obj == null ? "" : String.valueOf(obj);
				}
			};

			@Override
			public String getValue(LogEntryProxy object) {
				return renderer.render(object.getLogtime());
			}
		}, constants.dateTime());
		paths.add("length");
		table.addColumn(new TextColumn<LogEntryProxy>() {

			Renderer<Integer> renderer = new AbstractRenderer<Integer>() {

				public String render(Integer obj) {
					return obj == null ? "" : String.valueOf(obj);
				}
			};

			@Override
			public String getValue(LogEntryProxy object) {
				return renderer.render(object.getShibId());
			}
		}, constants.administrator());
		table.addColumn(new TextColumn<LogEntryProxy>() {

			Renderer<String> renderer = new AbstractRenderer<String>() {

				public String render(String obj) {
					return obj == null ? "" : String.valueOf(obj);
				}
			};

			@Override
			public String getValue(LogEntryProxy object) {
				return renderer.render(object.getOldValue() + " > " + object.getNewValue());
			}
		}, constants.logDetails());
		/*addColumn(new ActionCell<LogEntryProxy>(
				OsMaConstant.DELETE_ICON, new ActionCell.Delegate<LogEntryProxy>() {
					public void execute(LogEntryProxy room) {
						//Window.alert("You clicked " + institution.getInstitutionName());
						if(Window.confirm("wirklich löschen?"))
							delegate.deleteClicked(room);
					}
				}), "", new GetValue<LogEntryProxy>() {
			public LogEntryProxy getValue(LogEntryProxy room) {
				return room;
			}
		}, null);*/

		table.addColumnStyleName(2, "iconCol");
	}

	/**
	 * Add a column with a header.
	 *
	 * @param <C> the cell type
	 * @param cell the cell used to render the column
	 * @param headerText the header string
	 * @param getter the value getter for the cell
	 */
	private <C> void addColumn(Cell<C> cell, String headerText,
			final GetValue<C> getter, FieldUpdater<LogEntryProxy, C> fieldUpdater) {
		Column<LogEntryProxy, C> column = new Column<LogEntryProxy, C>(cell) {
			@Override
			public C getValue(LogEntryProxy object) {
				return getter.getValue(object);
			}
		};
		column.setFieldUpdater(fieldUpdater);
		if (cell instanceof AbstractEditableCell<?, ?>) {
			editableCells.add((AbstractEditableCell<?, ?>) cell);
		}
		table.addColumn(column, headerText);
	}

	/**
	 * Get a cell value from a record.
	 *
	 * @param <C> the cell type
	 */
	private static interface GetValue<C> {
		C getValue(LogEntryProxy contact);
	}

	private List<AbstractEditableCell<?, ?>> editableCells;

	@Override
	public CellTable<LogEntryProxy> getTable() {
		return table;
	}

	@Override
	public void setDelegate(Delegate delegate) {
		this.delegate = delegate;
	}

	//	@Override
	//	public SimplePanel getDetailsPanel() {
	//		return detailsPanel;
	//	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}
}
