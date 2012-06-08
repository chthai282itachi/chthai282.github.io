package ch.unibas.medizin.osce.client.a_nonroo.client.ui.role;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.event.def.AbstractReassociateEventListener;

import ch.unibas.medizin.osce.client.a_nonroo.client.OsMaConstant;
import ch.unibas.medizin.osce.client.a_nonroo.client.ui.role.RoleScriptTemplateView;
import ch.unibas.medizin.osce.client.a_nonroo.client.ui.renderer.EnumRenderer;
import ch.unibas.medizin.osce.client.a_nonroo.client.ui.role.TopicsAndSpecView.Delegate;
import ch.unibas.medizin.osce.client.a_nonroo.client.ui.role.TopicsAndSpecView.Presenter;
import ch.unibas.medizin.osce.client.a_nonroo.client.ui.role.TopicsAndSpecViewImpl.TopicsAndSpecViewUiBinder;
import ch.unibas.medizin.osce.client.i18n.OsceConstants;
import ch.unibas.medizin.osce.client.managed.request.RoleTemplateProxy;
import ch.unibas.medizin.osce.client.managed.request.ScarProxy;
import ch.unibas.medizin.osce.client.style.resources.MyCellTableResources;
import ch.unibas.medizin.osce.client.style.resources.MySimplePagerResources;
import ch.unibas.medizin.osce.client.style.widgets.QuickSearchBox;
import ch.unibas.medizin.osce.shared.TraitTypes;

import com.google.gwt.cell.client.AbstractEditableCell;
import com.google.gwt.cell.client.ActionCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.text.shared.AbstractRenderer;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class RoleScriptTemplateViewImpl extends Composite implements RoleScriptTemplateView {

	
	private static RoleScriptTemplateViewUiBinder uiBinder = GWT
			.create(RoleScriptTemplateViewUiBinder.class);

	interface RoleScriptTemplateViewUiBinder extends UiBinder<Widget, RoleScriptTemplateViewImpl> {
	}
	
	private final OsceConstants constants = GWT.create(OsceConstants.class);

	private Delegate delegate;
	
	
	@UiField
	public SplitLayoutPanel splitLayoutPanel;
	
	@UiField
	public SimplePanel detailsPanel;
	
	private Presenter presenter;
	

	
	@UiField (provided = true)
	SimplePager pager;
	
	@UiField (provided = true)
	CellTable<RoleTemplateProxy> table;
	
	protected ArrayList<String> paths = new ArrayList<String>();
	
	@UiField
	TextBox newBodypart;
	
	@UiField
	Button newButton;
	
	
	@UiHandler ("newButton")
	public void newButtonClicked(ClickEvent event) {
		if(newBodypart.getValue()==null || newBodypart.getValue()=="" || newBodypart.getValue().startsWith(" "))
			Window.alert("Please Enter Appropriate Value For Role Template");
		else
			delegate.newClicked(newBodypart.getValue());
		newBodypart.setValue("");
	}
	
	@UiField(provided=true)
	QuickSearchBox searchBox;
	
	//@UiField (provided = true)
	//QuickSearchBox searchBox;
	
	
	
	public RoleScriptTemplateViewImpl() {
		// TODO Auto-generated constructor stub
		CellTable.Resources tableResources = GWT.create(MyCellTableResources.class);
		table = new CellTable<RoleTemplateProxy>(OsMaConstant.TABLE_PAGE_SIZE, tableResources);
		
		
		
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
	
	public void init()
	{
		newBodypart.addKeyDownHandler(new KeyDownHandler() {
		    @Override
		    public void onKeyDown(KeyDownEvent event) {
		        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER)
		        	newButtonClicked(null);
		    }
		});

		// bugfix to avoid hiding of all panels (maybe there is a better solution...?!)
		DOM.setElementAttribute(splitLayoutPanel.getElement(), "style", "position: absolute; left: 0px; top: 0px; right: 5px; bottom: 0px;");
		
		editableCells = new ArrayList<AbstractEditableCell<?, ?>>();
		paths.add("templateName");
		table.addColumn(new TextColumn<RoleTemplateProxy>() {
			{
				this.setSortable(true);
			}

			Renderer<java.lang.String> renderer = new AbstractRenderer<java.lang.String>() {

				public String render(java.lang.String obj) {
					return obj == null ? "" : String.valueOf(obj);
				}
			};

			@Override
			public String getValue(RoleTemplateProxy object) {
				return renderer.render(object.getTemplateName());
			}
		}, "Role Template");
		paths.add("date_created");
		table.addColumn(new TextColumn<RoleTemplateProxy>() {
			{
				this.setSortable(true);
			}

			Renderer<java.util.Date> renderer = new AbstractRenderer<java.util.Date>() {

				public String render(java.util.Date obj) {
					return obj == null ? "" : String.valueOf(obj);
				}
			};

			@Override
			public String getValue(RoleTemplateProxy object) {
				return DateTimeFormat.getShortDateFormat().format(object.getDate_cretaed());
			}
		}, "Date Created");
		paths.add("date_edited");
		table.addColumn(new TextColumn<RoleTemplateProxy>() {

			{
				this.setSortable(true);
			}
			Renderer<java.util.Date> renderer = new AbstractRenderer<java.util.Date>() {

				public String render(java.util.Date obj) {
					return obj == null ? "" : String.valueOf(obj);
				}
			};

			@Override
			public String getValue(RoleTemplateProxy object) {
				return DateTimeFormat.getShortDateFormat().format(object.getDate_edited());
			}
		}, "Date Edited");
		//Edit Button
				addColumn(new ActionCell<RoleTemplateProxy>(
						OsMaConstant.EDIT_ICON, new ActionCell.Delegate<RoleTemplateProxy>() {
							public void execute(final RoleTemplateProxy roleTemplate) {
												
								delegate.editClicked(roleTemplate);
													
							}
						}), "", new GetValue<RoleTemplateProxy>() {
					public RoleTemplateProxy getValue(RoleTemplateProxy roleTemplate) {
						return roleTemplate;
					}
				}, null);
				table.addColumnStyleName(1, "iconCol");
		addColumn(new ActionCell<RoleTemplateProxy>(
				OsMaConstant.DELETE_ICON, new ActionCell.Delegate<RoleTemplateProxy>() {
					public void execute(RoleTemplateProxy roleTemplate) {
						//Window.alert("You clicked " + institution.getInstitutionName());
						if(Window.confirm("wirklich löschen?"))
							delegate.deleteClicked(roleTemplate);
					}
				}), "", new GetValue<RoleTemplateProxy>() {
			public RoleTemplateProxy getValue(RoleTemplateProxy roleTemplate) {
				return roleTemplate;
			}
		}, null);
		
		table.addColumnStyleName(2, "iconCol");
	}
	
	@Override
	public void setDelegate(Delegate delegate) {
		this.delegate = delegate;
	}
	
	@Override
	public SimplePanel getDetailsPanel() {
		return detailsPanel;
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}
	
	private static interface GetValue<C> {
		C getValue(RoleTemplateProxy contact);
	}

	private List<AbstractEditableCell<?, ?>> editableCells;

	@Override
	public CellTable<RoleTemplateProxy> getTable() {
		return table;
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
			final GetValue<C> getter, FieldUpdater<RoleTemplateProxy, C> fieldUpdater) {
		Column<RoleTemplateProxy, C> column = new Column<RoleTemplateProxy, C>(cell) {
			@Override
			public C getValue(RoleTemplateProxy object) {
				return getter.getValue(object);
			}
		};
		column.setFieldUpdater(fieldUpdater);
		if (cell instanceof AbstractEditableCell<?, ?>) {
			editableCells.add((AbstractEditableCell<?, ?>) cell);
		}
		table.addColumn(column, headerText);
	}
}
