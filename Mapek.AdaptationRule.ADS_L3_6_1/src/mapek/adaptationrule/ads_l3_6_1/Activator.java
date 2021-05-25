package mapek.adaptationrule.ads_l3_6_1;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import sua.autonomouscar.mapek.infrastructure.adaptationrules.ADS_L3_6_1;

public class Activator implements BundleActivator {

	private static BundleContext context;
	protected ADS_L3_6_1 ads_L3_6_1 = null;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		this.ads_L3_6_1 = new ADS_L3_6_1(bundleContext,"ADS_L3_6_1");
		this.ads_L3_6_1.registerThing();
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		if ( this.ads_L3_6_1 != null )
			this.ads_L3_6_1.unregisterThing();
	}

}
