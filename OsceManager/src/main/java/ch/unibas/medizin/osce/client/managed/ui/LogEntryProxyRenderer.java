package ch.unibas.medizin.osce.client.managed.ui;

import ch.unibas.medizin.osce.client.managed.request.LogEntryProxy;
import com.google.gwt.requestfactory.ui.client.ProxyRenderer;

public class LogEntryProxyRenderer extends ProxyRenderer<LogEntryProxy> {

    private static ch.unibas.medizin.osce.client.managed.ui.LogEntryProxyRenderer INSTANCE;

    protected LogEntryProxyRenderer() {
        super(new String[] { "id" });
    }

    public static ch.unibas.medizin.osce.client.managed.ui.LogEntryProxyRenderer instance() {
        if (INSTANCE == null) {
            INSTANCE = new LogEntryProxyRenderer();
        }
        return INSTANCE;
    }

    public String render(LogEntryProxy object) {
        if (object == null) {
            return "";
        }
        return object.getId() + " (" + object.getId() + ")";
    }
}
