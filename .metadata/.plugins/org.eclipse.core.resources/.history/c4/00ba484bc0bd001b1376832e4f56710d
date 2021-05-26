package mapek.probe.fallback_ads_2;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import sua.autonomouscar.devices.interfaces.IRoadSensor;
import sua.autonomouscar.driving.interfaces.IFallbackPlan;

public class Activator implements BundleActivator {

	private static BundleContext context;
	protected Listener controller =null;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		this.controller = new Listener(bundleContext);
		
		String listenerFilter = "(objectclass="+IFallbackPlan.class.getName()+")";
		this.context.addServiceListener(controller, listenerFilter);
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		this.context.removeServiceListener(controller);
		Activator.context = null;
	}

}
