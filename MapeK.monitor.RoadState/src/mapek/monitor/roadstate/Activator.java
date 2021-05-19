package mapek.monitor.roadstate;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import sua.autonomouscar.mapek.infrastructure.monitorroadstate.MonitorRoadStatus;


public class Activator implements BundleActivator {

	private static BundleContext context;
	private MonitorRoadStatus monitorRoadStatus = null;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		this.monitorRoadStatus = new MonitorRoadStatus(bundleContext,"monitorRoadStatus");
		this.monitorRoadStatus.registerThing();
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
