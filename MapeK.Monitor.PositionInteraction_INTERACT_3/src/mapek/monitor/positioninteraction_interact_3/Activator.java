package mapek.monitor.positioninteraction_interact_3;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import sua.autonomouscar.mapek.infrastructure.monitors.MonitorPositionInteraction;

public class Activator implements BundleActivator {

	private static BundleContext context;
	private MonitorPositionInteraction monitor = null;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		this.monitor = new MonitorPositionInteraction(bundleContext,"MonitorPositionInteraction");
		this.monitor.registerThing();
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		if ( this.monitor != null )
			this.monitor.unregisterThing();
	}

}
