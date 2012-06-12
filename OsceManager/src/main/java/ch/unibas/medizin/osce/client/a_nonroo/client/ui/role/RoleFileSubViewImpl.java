/**
 * 
 */
package ch.unibas.medizin.osce.client.a_nonroo.client.ui.role;

import java.util.ArrayList;

import ch.unibas.medizin.osce.client.a_nonroo.client.OsMaConstant;
import ch.unibas.medizin.osce.client.a_nonroo.client.request.OsMaRequestFactory;
import ch.unibas.medizin.osce.client.a_nonroo.client.ui.VisibleRange;
import ch.unibas.medizin.osce.client.i18n.OsceConstants;
import ch.unibas.medizin.osce.client.managed.request.FileProxy;
import ch.unibas.medizin.osce.client.managed.request.StandardizedRoleProxy;
import ch.unibas.medizin.osce.client.style.resources.MyCellTableResources;
import ch.unibas.medizin.osce.client.style.resources.MySimplePagerResources;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.cell.client.ActionCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
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
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author dk
 * 
 */
public class RoleFileSubViewImpl extends Composite implements RoleFileSubView {

	// private AnamnesisCheckPlace place = null;

	private static SystemStartViewUiBinder uiBinder = GWT
			.create(SystemStartViewUiBinder.class);

	interface SystemStartViewUiBinder extends
			UiBinder<Widget, RoleFileSubViewImpl> {
	}

	private final OsceConstants constants = GWT.create(OsceConstants.class);
	private Delegate delegate;
	private OsMaRequestFactory requests;
	StandardizedRoleProxy proxy;

	// @UiField
	// SplitLayoutPanel splitLayoutPanel;

	@UiField
	Button newButton;

	// @UiField
	// SimplePanel detailsPanel;

	@UiField(provided = true)
	CellTable<FileProxy> table;

	@UiField
	FileUpload fileUpload;

	@UiField
	TextBox fileDescription;

	@UiField
	FormPanel uploadFormPanel;

	public void initList() {

		for (VisibleRange range : VisibleRange.values()) {
			// rangeNum.addItem(range.getName(), range.getName());
		}
		// rangeNum.setItemSelected(1, true);
	}

	protected ArrayList<String> paths = new ArrayList<String>();

	private Presenter presenter;

	@UiHandler("newButton")
	public void newButtonClicked(ClickEvent event) {

		// System.out.println( fileUpload.getFilename() + "*"
		// + fileDescription.getValue() + "*");
		if ((fileUpload.getFilename().trim().compareToIgnoreCase("") == 0)
				|| (fileUpload.getFilename() == null)) {
			Window.confirm("Please select file to upload");
			return;
		}
		if ((fileDescription.getValue() == "")
				|| (fileDescription.getValue() == null)) {
			Window.confirm("Please enter description");
			return;
		}

		delegate.newFileClicked(fileUpload.getFilename(),
				fileDescription.getValue(), this.getValue());
		
		fileDescription.setValue("");

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
	public RoleFileSubViewImpl() {
		CellTable.Resources tableResources = GWT
				.create(MyCellTableResources.class);
		table = new CellTable<FileProxy>(15, tableResources);

		SimplePager.Resources pagerResources = GWT
				.create(MySimplePagerResources.class);

		initWidget(uiBinder.createAndBindUi(this));

		// splitLayoutPanel.setWidgetMinSize(splitLayoutPanel.getWidget(0),
		// OsMaConstant.SPLIT_PANEL_MINWIDTH);

		uploadFormPanel.setEncoding(FormPanel.ENCODING_MULTIPART);
		uploadFormPanel.setMethod(FormPanel.METHOD_POST);
		uploadFormPanel.setAction(GWT.getHostPageBaseURL()
				+ "RoleFileUploadServlet");

		System.out.println("url for RoleFileUploadServlet "
				+ GWT.getHostPageBaseURL() + "RoleFileUploadServlet");

		fileUpload.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				uploadFormPanel.submit();
			}
		});

		// spec video upload]

		uploadFormPanel.addSubmitHandler(new FormPanel.SubmitHandler() {

			@Override
			public void onSubmit(SubmitEvent event) {
				if (!"".equalsIgnoreCase(fileUpload.getFilename())) {
					Log.info("PS UPLOADING");
				} else {
					Log.info("PS UPLOADING cancel");
					event.cancel(); // cancel the event
				}
			}
		});

		uploadFormPanel
				.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {

					@Override
					public void onSubmitComplete(SubmitCompleteEvent event) {
						Log.info("PS Submit is Complete " + event.getResults());
						Window.confirm(constants.imageUpload());
						// setMediaContent(event.getResults());
						// delegate.uploadSuccesfull(event.getResults());
					}
				});
		init();
		newButton.setText(constants.addRoleFile());
	}

	public String[] getPaths() {
		return paths.toArray(new String[paths.size()]);
	}

	public void init() {
		// bugfix to avoid hiding of all panels (maybe there is a better
		// solution...?!)
		// DOM.setElementAttribute(splitLayoutPanel.getElement(), "style",
		// "position: absolute; left: 0px; top: 0px; right: 5px; bottom: 0px;");
		paths.add("path");
		table.addColumn(new TextColumn<FileProxy>() {

			Renderer<java.lang.String> renderer = new AbstractRenderer<java.lang.String>() {

				public String render(java.lang.String obj) {
					return obj == null ? "" : String.valueOf(obj);
				}
			};

			@Override
			public String getValue(FileProxy object) {
				return renderer.render(object.getPath());
			}
		}, constants.filePath());
		paths.add("description");
		table.addColumn(new TextColumn<FileProxy>() {

			Renderer<java.lang.String> renderer = new AbstractRenderer<java.lang.String>() {

				public String render(java.lang.String obj) {
					return obj == null ? "" : String.valueOf(obj);
				}
			};

			@Override
			public String getValue(FileProxy object) {
				return renderer.render(object.getDescription());
			}
		}, constants.fileDescription());
		addColumn(new ActionCell<FileProxy>(OsMaConstant.DOWN_ICON,
				new ActionCell.Delegate<FileProxy>() {
					public void execute(FileProxy proxy) {
						delegate.fileMoveDown(proxy, getValue());
					}
				}), "", new GetValue<FileProxy>() {
			public FileProxy getValue(FileProxy proxy) {
				return proxy;
			}
		}, null);
		addColumn(new ActionCell<FileProxy>(OsMaConstant.UP_ICON,
				new ActionCell.Delegate<FileProxy>() {
					public void execute(FileProxy proxy) {
						delegate.fileMoveUp(proxy, getValue());
					}
				}), "", new GetValue<FileProxy>() {
			public FileProxy getValue(FileProxy proxy) {
				return proxy;
			}
		}, null);

		addColumn(new ActionCell<FileProxy>(OsMaConstant.DELETE_ICON,
				new ActionCell.Delegate<FileProxy>() {
					public void execute(FileProxy file) {
						// Window.alert("You clicked " +
						// institution.getInstitutionName());
						if (Window.confirm("wirklich löschen?"))
							delegate.fileDeleteClicked(file, getValue());
					}
				}), "", new GetValue<FileProxy>() {
			public FileProxy getValue(FileProxy file) {
				return file;
			}
		}, null);

		table.addColumnStyleName(2, "iconCol");

		// initList();
	}

	private <C> void addColumn(Cell<C> cell, String headerText,
			final GetValue<C> getter, FieldUpdater<FileProxy, C> fieldUpdater) {
		Column<FileProxy, C> column = new Column<FileProxy, C>(cell) {
			@Override
			public C getValue(FileProxy object) {
				return getter.getValue(object);
			}
		};
		column.setFieldUpdater(fieldUpdater);
		table.addColumn(column, headerText);
	}

	/**
	 * Get a cell value from a record.
	 * 
	 * @param <C>
	 *            the cell type
	 */
	private static interface GetValue<C> {
		C getValue(FileProxy proxy);
	}

	@Override
	public CellTable<FileProxy> getTable() {
		return table;
	}

	@Override
	public void setDelegate(Delegate delegate) {
		this.delegate = delegate;
	}

	// @Override
	// public SimplePanel getDetailsPanel() {
	// return detailsPanel;
	// }

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void setListBoxItem(String length) {
		int index = 0;
		int selectedIndex = 0;
		for (VisibleRange range : VisibleRange.values()) {
			if (range.getName().equals(length)) {
				selectedIndex = index;

			}
			index++;
		}

		// rangeNum.setItemSelected(selectedIndex, true);
	}

	@Override
	public void setValue(StandardizedRoleProxy proxy) {
		this.proxy = proxy;
	}

	public StandardizedRoleProxy getValue() {
		return proxy;
	}

}
