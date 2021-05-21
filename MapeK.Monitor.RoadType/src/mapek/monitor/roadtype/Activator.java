package mapek.monitor.roadtype;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import sua.autonomouscar.mapek.infrastructure.monitors.MonitorRoadType;

public class Activator implements BundleActivator {

	private static BundleContext context;
	private MonitorRoadType monitorRoadType = null;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		this.monitorRoadType = new MonitorRoadType(bundleContext,"MonitorRoadType");
		this.monitorRoadType.registerThing();
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		if ( this.monitorRoadType != null )
			this.monitorRoadType.unregisterThing();
	}

}
