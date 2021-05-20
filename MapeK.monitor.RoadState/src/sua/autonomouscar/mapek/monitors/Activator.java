package sua.autonomouscar.mapek.monitors;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import sua.autonomouscar.mapek.infrastructure.monitors.MonitorRoadStatus;
import sua.autonomouscar.mapek.infrastructure.monitors.MonitorRoadType;


public class Activator implements BundleActivator {

	private static BundleContext context;
	private MonitorRoadStatus monitorRoadStatus = null;
	private MonitorRoadType monitorRoadType = null;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		this.monitorRoadStatus = new MonitorRoadStatus(bundleContext,"MonitorRoadStatus");
		this.monitorRoadStatus.registerThing();
		
		this.monitorRoadType = new MonitorRoadType(bundleContext,"MonitorRoadType");
		this.monitorRoadType.registerThing();
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		if ( this.monitorRoadStatus != null )
			this.monitorRoadStatus.unregisterThing();

		if ( this.monitorRoadType != null )
			this.monitorRoadType.unregisterThing();
	}

}
