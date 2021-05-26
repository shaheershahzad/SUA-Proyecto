package mapek.monitor.fallbackplannecessary;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import sua.autonomouscar.mapek.infrastructure.monitors.MonitorFallbackPlanNecessary;

public class Activator implements BundleActivator {

	private static BundleContext context;
	private MonitorFallbackPlanNecessary monitorFallbackPlanNecessary = null;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		this.monitorFallbackPlanNecessary = new MonitorFallbackPlanNecessary(bundleContext,"MonitorFallbackPlanNecessary");
		this.monitorFallbackPlanNecessary.registerThing();
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		if ( this.monitorFallbackPlanNecessary != null )
			this.monitorFallbackPlanNecessary.unregisterThing();
	}

}
