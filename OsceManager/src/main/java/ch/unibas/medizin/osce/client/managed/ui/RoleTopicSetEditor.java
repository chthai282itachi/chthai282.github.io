package ch.unibas.medizin.osce.client.managed.ui;

import ch.unibas.medizin.osce.client.managed.request.RoleTopicProxy;
import ch.unibas.medizin.osce.client.managed.request.StandardizedRoleProxy;
import ch.unibas.medizin.osce.client.managed.ui.RoleTopicSetEditor.Style;
import ch.unibas.medizin.osce.client.scaffold.ui.CollectionRenderer;
import ch.unibas.medizin.osce.shared.StudyYears;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.Editor.Ignore;
import com.google.gwt.editor.client.EditorDelegate;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.editor.client.ValueAwareEditor;
import com.google.gwt.editor.client.adapters.EditorSource;
import com.google.gwt.editor.client.adapters.ListEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.requestfactory.client.RequestFactoryEditorDriver;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ValueListBox;
import com.google.gwt.user.client.ui.Widget;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RoleTopicSetEditor extends RoleTopicSetEditor_Roo_Gwt {

    @UiField
    FlowPanel container;

    @UiField(provided = true)
    @Ignore
    ValueListBox<RoleTopicProxy> picker = new ValueListBox<RoleTopicProxy>(ch.unibas.medizin.osce.client.managed.ui.RoleTopicProxyRenderer.instance(), new com.google.gwt.requestfactory.ui.client.EntityProxyKeyProvider<RoleTopicProxy>());

    @UiField
    Button add;

    @UiField
    HTMLPanel editorPanel;

    @UiField
    Button clickToEdit;

    @UiField
    HTMLPanel viewPanel;

    @UiField
    Label viewLabel;

    @UiField
    Style style;

    boolean editing = false;

    private Set<RoleTopicProxy> values;

    private final List<RoleTopicProxy> displayedList;

    public RoleTopicSetEditor() {
        initWidget(GWT.<Binder>create(Binder.class).createAndBindUi(this));
        Driver driver = GWT.<Driver>create(Driver.class);
        ListEditor<RoleTopicProxy, NameLabel> listEditor = ListEditor.of(new NameLabelSource());
        driver.initialize(listEditor);
        driver.display(new ArrayList<RoleTopicProxy>());
        displayedList = listEditor.getList();
        editing = false;
    }

    @UiHandler("add")
    public void addClicked(ClickEvent e) {
        if (picker.getValue() == null) {
            return;
        }
        for (RoleTopicProxy proxy : displayedList) {
            if (proxy.getId().equals(picker.getValue().getId())) {
                return;
            }
        }
        displayedList.add(picker.getValue());
        viewLabel.setText(makeFlatList(displayedList));
    }

    @UiHandler("clickToEdit")
    public void clickToEditClicked(ClickEvent e) {
        toggleEditorMode();
    }

    @Override
    public void flush() {
    }

    @Override
    public Set<ch.unibas.medizin.osce.client.managed.request.RoleTopicProxy> getValue() {
        if (values == null && displayedList.size() == 0) {
            return null;
        }
        return new HashSet<RoleTopicProxy>(displayedList);
    }

    public void onLoad() {
        makeEditable(false);
    }

    @Override
    public void onPropertyChange(String... strings) {
    }

    public void setAcceptableValues(Collection<RoleTopicProxy> proxies) {
        picker.setAcceptableValues(proxies);
    }

    @Override
    public void setDelegate(EditorDelegate<Set<RoleTopicProxy>> editorDelegate) {
    }

    @Override
    public void setValue(Set<RoleTopicProxy> values) {
        this.values = values;
        makeEditable(editing = false);
        if (displayedList != null) {
            displayedList.clear();
            if (values != null) {
                for (RoleTopicProxy e : values) {
                    displayedList.add(e);
                }
            }
        }
        viewLabel.setText(makeFlatList(values));
    }

    private void makeEditable(boolean editable) {
        if (editable) {
            editorPanel.setStylePrimaryName(style.editorPanelVisible());
            viewPanel.setStylePrimaryName(style.viewPanelHidden());
            clickToEdit.setText("Done");
        } else {
            editorPanel.setStylePrimaryName(style.editorPanelHidden());
            viewPanel.setStylePrimaryName(style.viewPanelVisible());
            clickToEdit.setText("Edit");
        }
    }

    private String makeFlatList(Collection<RoleTopicProxy> values) {
        return CollectionRenderer.of(ch.unibas.medizin.osce.client.managed.ui.RoleTopicProxyRenderer.instance()).render(values);
    }

    private void toggleEditorMode() {
        editing = !editing;
        makeEditable(editing);
    }

    interface Binder extends UiBinder<Widget, RoleTopicSetEditor> {
    }

    interface Driver extends RequestFactoryEditorDriver<List<RoleTopicProxy>, ListEditor<RoleTopicProxy, NameLabel>> {
    }

    class NameLabel extends Composite implements LeafValueEditor<RoleTopicProxy> {

        final Label nameEditor = new Label();

        private RoleTopicProxy proxy = null;

        public NameLabel() {
            this(null);
        }

        public NameLabel(EventBus eventBus) {
            initWidget(nameEditor);
        }

        public void setValue(RoleTopicProxy proxy) {
            this.proxy = proxy;
            nameEditor.setText(ch.unibas.medizin.osce.client.managed.ui.RoleTopicProxyRenderer.instance().render(proxy));
        }

        @Override
        public RoleTopicProxy getValue() {
            return proxy;
        }
    }

    interface Style extends CssResource {

        String editorPanelHidden();

        String editorPanelVisible();

        String viewPanelHidden();

        String viewPanelVisible();
    }

    private class NameLabelSource extends EditorSource<NameLabel> {

        @Override
        public NameLabel create(int index) {
            NameLabel label = new NameLabel();
            container.insert(label, index);
            return label;
        }

        @Override
        public void dispose(NameLabel subEditor) {
            subEditor.removeFromParent();
        }

        @Override
        public void setIndex(NameLabel editor, int index) {
            container.insert(editor, index);
        }
    }
}
