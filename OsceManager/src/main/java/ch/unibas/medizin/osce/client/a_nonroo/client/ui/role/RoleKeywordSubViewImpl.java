package ch.unibas.medizin.osce.client.a_nonroo.client.ui.role;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ch.unibas.medizin.osce.client.a_nonroo.client.OsMaConstant;
import ch.unibas.medizin.osce.client.a_nonroo.client.ui.renderer.EnumRenderer;
import ch.unibas.medizin.osce.client.a_nonroo.client.ui.role.StandardizedRoleDetailsView.Delegate;
import ch.unibas.medizin.osce.client.i18n.OsceConstants;
import ch.unibas.medizin.osce.client.managed.request.AnamnesisCheckProxy;
import ch.unibas.medizin.osce.client.managed.request.DoctorProxy;
import ch.unibas.medizin.osce.client.managed.request.KeywordProxy;
import ch.unibas.medizin.osce.client.managed.request.LangSkillProxy;
import ch.unibas.medizin.osce.client.managed.request.RoleParticipantProxy;
import ch.unibas.medizin.osce.client.managed.request.RoleTopicProxy;
import ch.unibas.medizin.osce.client.managed.request.SpecialisationProxy;
import ch.unibas.medizin.osce.client.managed.request.SpokenLanguageProxy;
import ch.unibas.medizin.osce.client.managed.request.StandardizedRoleProxy;
import ch.unibas.medizin.osce.client.managed.ui.DoctorProxyRenderer;
import ch.unibas.medizin.osce.client.style.resources.MyCellTableResources;
import ch.unibas.medizin.osce.client.style.resources.MySimplePagerResources;
import ch.unibas.medizin.osce.client.style.widgets.IconButton;
import ch.unibas.medizin.osce.client.style.widgets.ProxySuggestOracle;
import ch.unibas.medizin.osce.client.style.widgets.QuickSearchBox;
import ch.unibas.medizin.osce.shared.AnamnesisCheckTypes;
import ch.unibas.medizin.osce.shared.LangSkillLevel;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.cell.client.AbstractEditableCell;
import com.google.gwt.cell.client.ActionCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.text.shared.AbstractRenderer;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ValueListBox;
import com.google.gwt.user.client.ui.Widget;

public class RoleKeywordSubViewImpl extends Composite implements RoleKeywordSubView {

	private static RoleKeywordSubViewUiBinder uiBinder = GWT.create(RoleKeywordSubViewUiBinder.class);

	interface RoleKeywordSubViewUiBinder extends UiBinder<Widget, RoleKeywordSubViewImpl> {
	}

	private List<AbstractEditableCell<?, ?>> editableCells;
	protected Set<String> paths = new HashSet<String>();
	private boolean addBoxesShown = true;
	private Delegate delegate;
	private final OsceConstants constants = GWT.create(OsceConstants.class);
	public  static MultiWordSuggestOracle keywordoracle = new MultiWordSuggestOracle();
	
	/*private KeywordProxy keywordProxy;
	private IsWidget currentKeywordWidget;*/
	
	@UiField (provided = true)
	public CellTable<KeywordProxy> keywordTable;
	@UiField (provided = true)
	public SimplePager pager;
	
	
	@UiField
	public IconButton KeywordAddButton;
	
	@UiField(provided = true)
	public SuggestBox keywordSugestionBox =  new SuggestBox(keywordoracle);
	
	public TextBox textbox;;
		
		
	public RoleKeywordSubViewImpl() 
	{
				
				
		CellTable.Resources tableResources = GWT.create(MyCellTableResources.class);
		keywordTable = new CellTable<KeywordProxy>(OsMaConstant.TABLE_PAGE_SIZE, tableResources);
		
		SimplePager.Resources pagerResources = GWT.create(MySimplePagerResources.class);
		pager = new SimplePager(SimplePager.TextLocation.RIGHT, pagerResources, true, OsMaConstant.TABLE_JUMP_SIZE, true);
		
		
				
		initWidget(uiBinder.createAndBindUi(this));	
		initTable();
//		initSearchBox();
		initSuggestBox();
		KeywordAddButton.setText("Add Keyword");
				
	}
	
//	private void initSearchBox() 
//	{
//		searchKeywordBox = new QuickSearchBox(new QuickSearchBox.Delegate() {
//			@Override
//			public void performAction() {
//				delegate.performKeywordSearch();
//			}
//		});
//	}
//	
	private void initSuggestBox()
	{
		
		keywordSugestionBox.addSelectionHandler(new SelectionHandler<SuggestOracle.Suggestion>() 
		{			
			@Override
			public void onSelection(SelectionEvent<Suggestion> event) 
			{
				// TODO Auto-generated method stub				
				System.out.println("on Selection");
				keywordSugestionBox.setValue(event.getSelectedItem().getReplacementString());
			}
			
		});
		
		keywordSugestionBox.addChangeListener(new ChangeListener() 
		{			
			@Override
			public void onChange(Widget sender) 
			{
				// TODO Auto-generated method stub
				System.out.println("on Change");
				keywordSugestionBox.setValue(((SuggestBox)sender).getTextBox().getValue());					
			}
		});
	}
	
	
	
	private void initTable() 
	{
		keywordTable.addColumn(new TextColumn<KeywordProxy>() 
		{
			Renderer<java.lang.String> renderer = new AbstractRenderer<String>() 
			{
				public String render(String obj) 
				{
					return obj == null ? "" : String.valueOf(obj);
				}
			};
			
			@Override
			public String getValue(KeywordProxy object) 
			{
				String keyword;
								
				if (object == null) 
				{
					Log.error("KeywordProxy is null");
				}
				else if ((keyword = object.getName()) == null) 
				{
					Log.error("Keyword = null");
				}
				else 
				{
					return renderer.render(keyword);
				} 
				return "";
			}
			
		}, constants.keywords());
		
		addColumn(new ActionCell<KeywordProxy>(
				OsMaConstant.DELETE_ICON, new ActionCell.Delegate<KeywordProxy>() {
					public void execute(KeywordProxy keywordProxy) {
						if(Window.confirm(constants.reallyDelete()))
						{
							delegate.deleteKeywordClicked(keywordProxy);
						}
					}
				}), "", new GetValue<KeywordProxy>() {
					public KeywordProxy getValue(KeywordProxy keyword) {
						return keyword;
					}
		}, null);
		keywordTable.addColumnStyleName(2, "iconCol");
		
	}
	
	@Override
	public void setKeywordAutocompleteValue(List<KeywordProxy> values) {
	
		int index=0;
		while(index<values.size())
		{		
			keywordoracle.add(values.get(index).getName());
			index++;
		}
		textbox=new TextBox();
		//keywordSugestionBox = new SuggestBox(keywordoracle, new TextBox());
		keywordSugestionBox = new SuggestBox(keywordoracle, textbox);
		
	}
	
	private <C> void addColumn(Cell<C> cell, String headerText,	final GetValue<C> getter, FieldUpdater<KeywordProxy, C> fieldUpdater) 
	{
		Column<KeywordProxy, C> column = new Column<KeywordProxy, C>(cell) 
		{
			@Override
			public C getValue(KeywordProxy object) {
				return getter.getValue(object);
			}
		};
		column.setFieldUpdater(fieldUpdater);
		if (cell instanceof AbstractEditableCell<?, ?>) 
		{
			editableCells.add((AbstractEditableCell<?, ?>) cell);
		}
		keywordTable.addColumn(column, headerText);
	}
	
	private static interface GetValue<C> 
	{
		C getValue(KeywordProxy proxyvalue);
	}
	
	@UiHandler("KeywordAddButton")
	public void KeywordAddButton(ClickEvent e) 
	{
		if(keywordSugestionBox.getValue().trim().equals(""))
		{
			Log.info("Suggest Box Value is NULL");
			Window.alert("Please Select/Add new Keyword");
		}
		else
		{
			delegate.addKeywordClicked("");
		}
	}
	
	@Override
	public CellTable<KeywordProxy> getKeywordTable() {
		return keywordTable;
	}
	
	@Override
	public void setDelegate(Delegate delegate) 
	{
		this.delegate=delegate;			
	}
	
	
}
