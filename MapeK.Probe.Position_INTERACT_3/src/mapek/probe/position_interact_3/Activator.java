package mapek.probe.position_interact_3;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import sua.autonomouscar.devices.interfaces.IHumanSensors;
import sua.autonomouscar.devices.interfaces.ISeatSensor;

public class Activator implements BundleActivator {

	private static BundleContext context;
	protected Listener controller =null;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		this.controller = new Listener(bundleContext);
		String listenerFilter = "(objectclass="+ISeatSensor.class.getName()+")";
		this.context.addServiceListener(controller, listenerFilter);
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		this.context.removeServiceListener(controller);
		Activator.context = null;
	}

}
