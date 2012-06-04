// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.

package ch.unibas.medizin.osce.client.managed.ui;

import ch.unibas.medizin.osce.client.managed.request.LangSkillProxy;
import ch.unibas.medizin.osce.client.managed.request.SpokenLanguageProxy;
import ch.unibas.medizin.osce.client.managed.request.StandardizedPatientProxy;
import ch.unibas.medizin.osce.client.scaffold.place.ProxyDetailsView;
import ch.unibas.medizin.osce.client.scaffold.place.ProxyListView;
import ch.unibas.medizin.osce.shared.LangSkillLevel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class LangSkillDetailsView_Roo_Gwt extends Composite implements ProxyDetailsView<LangSkillProxy> {

    @UiField
    SpanElement skill;

    @UiField
    SpanElement standardizedpatient;

    @UiField
    SpanElement spokenlanguage;

    @UiField
    SpanElement id;

    @UiField
    SpanElement version;

    LangSkillProxy proxy;

    @UiField
    SpanElement displayRenderer;

    public void setValue(LangSkillProxy proxy) {
        this.proxy = proxy;
        skill.setInnerText(proxy.getSkill() == null ? "" : String.valueOf(proxy.getSkill()));
        standardizedpatient.setInnerText(proxy.getStandardizedpatient() == null ? "" : ch.unibas.medizin.osce.client.managed.ui.StandardizedPatientProxyRenderer.instance().render(proxy.getStandardizedpatient()));
        spokenlanguage.setInnerText(proxy.getSpokenlanguage() == null ? "" : ch.unibas.medizin.osce.client.managed.ui.SpokenLanguageProxyRenderer.instance().render(proxy.getSpokenlanguage()));
        id.setInnerText(proxy.getId() == null ? "" : String.valueOf(proxy.getId()));
        version.setInnerText(proxy.getVersion() == null ? "" : String.valueOf(proxy.getVersion()));
        displayRenderer.setInnerText(ch.unibas.medizin.osce.client.managed.ui.LangSkillProxyRenderer.instance().render(proxy));
    }
}
