package mapek.monitor.vehiclecontext;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import sua.autonomouscar.mapek.infrastructure.monitors.MonitorVehicleContext;

public class Activator implements BundleActivator {

	private static BundleContext context;
	private MonitorVehicleContext monitorVehicleContext = null;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		this.monitorVehicleContext = new MonitorVehicleContext(bundleContext,"MonitorVehicleContext");
		this.monitorVehicleContext.registerThing();
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		if ( this.monitorVehicleContext != null )
			this.monitorVehicleContext.unregisterThing();
	}

}
