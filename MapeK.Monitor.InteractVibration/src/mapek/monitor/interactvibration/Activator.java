package mapek.monitor.interactvibration;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import sua.autonomouscar.mapek.infrastructure.monitors.MonitorFallbackPlanNecessary;
import sua.autonomouscar.mapek.infrastructure.monitors.MonitorInteractVibration;

public class Activator implements BundleActivator {

	private static BundleContext context;
	private MonitorInteractVibration monitor = null;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		this.monitor = new MonitorInteractVibration(bundleContext,"MonitorInteractVibration");
		this.monitor.registerThing();
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		if ( this.monitor != null )
			this.monitor.unregisterThing();
	}

}
