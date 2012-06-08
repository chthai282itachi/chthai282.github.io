// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.

package ch.unibas.medizin.osce.client.managed.request;

import com.google.gwt.requestfactory.shared.InstanceRequest;
import com.google.gwt.requestfactory.shared.Request;
import com.google.gwt.requestfactory.shared.RequestContext;
import com.google.gwt.requestfactory.shared.ServiceName;
import org.springframework.roo.addon.gwt.RooGwtMirroredFrom;

@RooGwtMirroredFrom("ch.unibas.medizin.osce.domain.Bankaccount")
@ServiceName("ch.unibas.medizin.osce.domain.Bankaccount")
public interface BankaccountRequest extends RequestContext {

    abstract InstanceRequest<ch.unibas.medizin.osce.client.managed.request.BankaccountProxy, java.lang.Void> persist();

    abstract InstanceRequest<ch.unibas.medizin.osce.client.managed.request.BankaccountProxy, java.lang.Void> remove();

    abstract Request<java.lang.Long> countBankaccounts();

    abstract Request<java.util.List<ch.unibas.medizin.osce.client.managed.request.BankaccountProxy>> findAllBankaccounts();

    abstract Request<ch.unibas.medizin.osce.client.managed.request.BankaccountProxy> findBankaccount(Long id);

    abstract Request<java.util.List<ch.unibas.medizin.osce.client.managed.request.BankaccountProxy>> findBankaccountEntries(int firstResult, int maxResults);
}
