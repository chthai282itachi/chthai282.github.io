package ch.unibas.medizin.osce.client.managed.ui;

import ch.unibas.medizin.osce.client.managed.request.UsedMaterialProxy;
import com.google.gwt.requestfactory.ui.client.ProxyRenderer;

public class UsedMaterialProxyRenderer extends ProxyRenderer<UsedMaterialProxy> {

    private static ch.unibas.medizin.osce.client.managed.ui.UsedMaterialProxyRenderer INSTANCE;

    protected UsedMaterialProxyRenderer() {
        super(new String[] { "id" });
    }

    public static ch.unibas.medizin.osce.client.managed.ui.UsedMaterialProxyRenderer instance() {
        if (INSTANCE == null) {
            INSTANCE = new UsedMaterialProxyRenderer();
        }
        return INSTANCE;
    }

    public String render(UsedMaterialProxy object) {
        if (object == null) {
            return "";
        }
        return object.getId() + " (" + object.getId() + ")";
    }
}
