// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.

package ch.unibas.medizin.osce.client.managed.request;

import com.google.gwt.requestfactory.shared.EntityProxy;
import com.google.gwt.requestfactory.shared.ProxyForName;
import java.util.Set;
import org.springframework.roo.addon.gwt.RooGwtMirroredFrom;

@RooGwtMirroredFrom("ch.unibas.medizin.osce.domain.SpokenLanguage")
@ProxyForName("ch.unibas.medizin.osce.domain.SpokenLanguage")
public interface SpokenLanguageProxy extends EntityProxy {

    abstract String getLanguageName();

    abstract void setLanguageName(String languageName);

    abstract Set<ch.unibas.medizin.osce.client.managed.request.LangSkillProxy> getLangskills();

    abstract void setLangskills(Set<LangSkillProxy> langskills);

    abstract Long getId();

    abstract void setId(Long id);

    abstract Integer getVersion();

    abstract void setVersion(Integer version);
}