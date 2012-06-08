package ch.unibas.medizin.osce.client.a_nonroo.client.ui.role;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ch.unibas.medizin.osce.client.a_nonroo.client.OsMaConstant;
import ch.unibas.medizin.osce.client.i18n.OsceConstants;
import ch.unibas.medizin.osce.client.managed.request.SpecialisationProxy;
import ch.unibas.medizin.osce.client.style.resources.MyCellTableResources;
import ch.unibas.medizin.osce.client.style.resources.MySimplePagerResources;
import ch.unibas.medizin.osce.client.style.widgets.IconButton;
import ch.unibas.medizin.osce.client.style.widgets.QuickSearchBox;

import com.google.gwt.cell.client.AbstractEditableCell;
import com.google.gwt.cell.client.ActionCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
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
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class TopicsAndSpecViewImpl extends Composite implements  TopicsAndSpecView {
	
	private static TopicsAndSpecViewUiBinder uiBinder = GWT
			.create(TopicsAndSpecViewUiBinder.class);

	interface TopicsAndSpecViewUiBinder extends UiBinder<Widget, TopicsAndSpecViewImpl> {
	}
	
	private final OsceConstants constants = GWT.create(OsceConstants.class);

	private Delegate delegate;
	
	
	@UiField
	public SplitLayoutPanel splitLayoutPanel;
	
	@UiField
	public SimplePanel detailsPanel;
	
	@UiField (provided = true)
	public QuickSearchBox searchBox;
	
	@UiField
	Button FilterButton;
	


	@UiField(provided=true)
	CellTable<SpecialisationProxy> table;
	
	protected Set<String> paths = new HashSet<String>();
	@UiField
	TextBox	AddTextBox;
	
	@UiField 
	IconButton AddButton;
	
	@UiField (provided = true)
	SimplePager Pager;
	
	
	
	@UiHandler ("AddButton")
	public void newButtonClicked(ClickEvent event) {
		delegate.newClicked(AddTextBox.getValue());
		AddTextBox.setValue("");
	}
	private Presenter presenter;
	
	public TopicsAndSpecViewImpl() {
		// TODO Auto-generated constructor stub
		CellTable.Resources tableResources = GWT.create(MyCellTableResources.class);
		table = new CellTable<SpecialisationProxy>(OsMaConstant.TABLE_PAGE_SIZE, tableResources);
		
				
		SimplePager.Resources pagerResources = GWT.create(MySimplePagerResources.class);
		Pager = new SimplePager(SimplePager.TextLocation.RIGHT, pagerResources, true, OsMaConstant.TABLE_JUMP_SIZE, true);
		
		searchBox = new QuickSearchBox(new QuickSearchBox.Delegate() {
			@Override
			public void performAction() {
				delegate.performSearch(searchBox.getValue());
			}
		});
				
				initWidget(uiBinder.createAndBindUi(this));
				init();
				splitLayoutPanel.setWidgetMinSize(splitLayoutPanel.getWidget(0), OsMaConstant.SPLIT_PANEL_MINWIDTH);		
				FilterButton.setText("Filter");	
				AddButton.setText("add Specialization");
				
				
	}
	
	
	
	public String getQuery() {
		return searchBox.getValue();
	}
	// @Manish
	public String[] getPaths() {
		return paths.toArray(new String[paths.size()]);
	}
	
	public void init()
	{
		// bugfix to avoid hiding of all panels (maybe there is a better solution...?!)
		DOM.setElementAttribute(splitLayoutPanel.getElement(), "style", "position: absolute; left: 0px; top: 0px; right: 5px; bottom: 0px;");
		
		AddTextBox.addKeyDownHandler(new KeyDownHandler() {
		    @Override
		    public void onKeyDown(KeyDownEvent event) {
		        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER)
		        	newButtonClicked(null);
		    }
		});
		
		
		//By spec for table insert
		
		paths.add("name");
		table.addColumn(new TextColumn<SpecialisationProxy>() {
			{ this.setSortable(true); }

			Renderer<java.lang.String> renderer = new AbstractRenderer<java.lang.String>() {

				public String render(java.lang.String obj) {
					return obj == null ? "" : String.valueOf(obj);
				}
			};

			@Override
			public String getValue(SpecialisationProxy object) {
				return renderer.render(object.getName());
				
			}
		},constants.clinicalSpecialisation());
				
		//Edit Button
		addColumn(new ActionCell<SpecialisationProxy>(
				OsMaConstant.EDIT_ICON, new ActionCell.Delegate<SpecialisationProxy>() {
					public void execute(final SpecialisationProxy specialization) {
										
						delegate.editClicked(specialization);
											
					}
				}), "", new GetValue<SpecialisationProxy>() {
			public SpecialisationProxy getValue(SpecialisationProxy specialization) {
				return specialization;
			}
		}, null);
		table.addColumnStyleName(1, "iconCol");

		// Detete button
		addColumn(new ActionCell<SpecialisationProxy>(
				OsMaConstant.DELETE_ICON, new ActionCell.Delegate<SpecialisationProxy>() {
					public void execute(SpecialisationProxy specialization) {
						//Window.alert("You clicked " + institution.getInstitutionName());
						if(Window.confirm("wirklich löschen?"))
							delegate.deleteClicked(specialization);
					}
				}), "", new GetValue<SpecialisationProxy>() {
			public SpecialisationProxy getValue(SpecialisationProxy specialization) {
				return specialization;
			}
		}, null);
		table.addColumnStyleName(1, "iconCol");

	
	
	}
	
	private <C> void addColumn(Cell<C> cell, String headerText,
			final GetValue<C> getter, FieldUpdater<SpecialisationProxy, C> fieldUpdater) {
		Column<SpecialisationProxy, C> column = new Column<SpecialisationProxy, C>(cell) {
			@Override
			public C getValue(SpecialisationProxy object) {
				return getter.getValue(object);
			}
		};
		column.setFieldUpdater(fieldUpdater);
		if (cell instanceof AbstractEditableCell<?, ?>) {
			editableCells.add((AbstractEditableCell<?, ?>) cell);
		}
		table.addColumn(column, headerText);
	}
	
	private List<AbstractEditableCell<?, ?>> editableCells;
	
	@Override
	public CellTable<SpecialisationProxy> getTable() {
		return table;
	}
	
	private static interface GetValue<C> {
		C getValue(SpecialisationProxy contact);
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
		

}
