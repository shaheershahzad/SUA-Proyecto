package mapek.monitor.roadstatus;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import sua.autonomouscar.devices.interfaces.IThing;
import sua.autonomouscar.infrastructure.Thing;
import sua.autonomouscar.mapek.infrastructure.monitors.MonitorRoadStatus;
import sua.autonomouscar.mapek.infrastructure.monitors.MonitorRoadType;

public class Activator implements BundleActivator {

	private static BundleContext context;
	private MonitorRoadStatus monitorRoadStatus = null;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		this.monitorRoadStatus = new MonitorRoadStatus(bundleContext,"MonitorRoadStatus");
		this.monitorRoadStatus.registerThing();
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		if ( this.monitorRoadStatus != null )
			this.monitorRoadStatus.unregisterThing();

	}

}
