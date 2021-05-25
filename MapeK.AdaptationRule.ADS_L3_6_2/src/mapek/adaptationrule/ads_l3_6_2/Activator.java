package mapek.adaptationrule.ads_l3_6_2;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import sua.autonomouscar.mapek.infrastructure.adaptationrules.ADS_L3_6_2;

public class Activator implements BundleActivator {

	private static BundleContext context;
	protected ADS_L3_6_2 ads_L3_6_2 = null;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		this.ads_L3_6_2 = new ADS_L3_6_2(bundleContext,"ADS_L3_6_2");
		this.ads_L3_6_2.registerThing();
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		if ( this.ads_L3_6_2 != null )
			this.ads_L3_6_2.unregisterThing();
	}

}
