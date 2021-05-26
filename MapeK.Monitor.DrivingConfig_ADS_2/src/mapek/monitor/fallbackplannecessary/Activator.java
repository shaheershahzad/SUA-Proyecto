package mapek.monitor.fallbackplannecessary;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import sua.autonomouscar.mapek.infrastructure.monitors.MonitorDrivingConfig_ADS_2;
import sua.autonomouscar.mapek.infrastructure.monitors.MonitorFallbackPlanNecessary;

public class Activator implements BundleActivator {

	private static BundleContext context;
	private MonitorDrivingConfig_ADS_2 monitorDrivingConfig_DS_2 = null;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		this.monitorDrivingConfig_DS_2 = new MonitorDrivingConfig_ADS_2(bundleContext,"MonitorDrivingConfig_ADS_2");
		this.monitorDrivingConfig_DS_2.registerThing();
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		if ( this.monitorDrivingConfig_DS_2 != null )
			this.monitorDrivingConfig_DS_2.unregisterThing();
	}

}
